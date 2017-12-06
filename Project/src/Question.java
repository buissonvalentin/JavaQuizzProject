
public class Question {
	
	 private String type;
	 private String correctAns;
	 private String question;
	 private String[] correctAnswers;
	 private String[] propositions;
	
	public Question(String type, String correctAnswer, String question, String[] correctAnswers, String[] propositions){
		this.type = type;
		this.correctAns = correctAnswer;
		this.question = question;
		this.correctAnswers = correctAnswers;
		this.propositions = propositions;
	}
	
	// Return true if the answer matcch the attempt
	public boolean isGoodAnswer(String attempt){
		return attempt.toLowerCase().equals(correctAns.toLowerCase()); 
	}
	
	
	// If we have not only one corrrect answer, we test that the array correct answer contain all the value of the array atttempt
	public boolean isGoodAnswer(String[] attempt){
		
		if(attempt.length != correctAnswers.length){
			return false;
		}
		for(int i = 0; i < correctAnswers.length; i++){
			if(!contains(attempt, correctAnswers[i])) return false;
		}
		return true;
	}
	
	/// return true if the array contain the string in parameters
	private boolean contains(String[] arr, String value){
		for(int i = 0; i < arr.length; i++){
			if(arr[i].toLowerCase().equals(value.toLowerCase())) return true;
		}
		return false;
	}
	
	// return a string containing all the data of the array correctANswers, each value is separated by "/" to recreate the array later
	public String dispCorrectAns(){
		String  temp = "Correct answer is : ";
		if(type.equals("multiple")){
			for(int i =0; i < correctAnswers.length; i++){
				temp += correctAnswers[i];
			}
		}
		else{
			temp += correctAns;
		}
		return temp;
	}
	
	// return a string containing all the data of the array propositions, each value is separated by "/" to recreate the array later
	public String arrayToStringPropositions(){
		String temp = "";
		if(propositions == null) return temp;
		for(int i = 0 ; i<propositions.length;i++){
			temp += propositions[i] + "/";
		}
		
		return temp;
	}
	
	
	// return a string containing all the data of the array correctANswers, each value is separated by "/" to recreate the array later
	public String arrayToStringCorrectAnswers(){
		String temp = "";
		if(correctAnswers == null) return temp;
		for(int i = 0 ; i<correctAnswers.length;i++){
			temp += correctAnswers[i] + "/";
		}
		
		return temp;
	}
	
	
	/// all the getters 
	public String getType(){
		return type;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getCorrectAnswer(){
		return correctAns;
	}
	
	public String[] getCorrectAns(){
		return correctAnswers;
	}
	
	public String[] getPropositions(){
		return propositions;
	}
	
	
}
