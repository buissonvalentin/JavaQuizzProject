import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class QuestionWindow extends JFrame{

	ArrayList <Question> questionsList = new ArrayList<Question>();
	Question currentQ;
	JTextField ansText = new JTextField();
	ArrayList<JCheckBox> listBox = new ArrayList<JCheckBox>();
	ArrayList<JRadioButton> listRadio = new ArrayList<JRadioButton>();
	JCheckBox ansA = new JCheckBox();
	JCheckBox ansB = new JCheckBox();
	JCheckBox ansC = new JCheckBox();
	JCheckBox ansD = new JCheckBox();
	JRadioButton radioA = new JRadioButton();
	JRadioButton radioB = new JRadioButton();
	JRadioButton radioC = new JRadioButton();
	JRadioButton radioD = new JRadioButton();
	ButtonGroup group = new ButtonGroup();
	JLabel number;
			
	JComboBox<String> ansCombo = new JComboBox();
	JLabel question;
	JLabel answer;
	
	DataBaseManager dbManager;
	
	int result = 0;
	int countQ = 0;
	Boolean hasAnswered = false;
	
	public QuestionWindow(DataBaseManager dbManager){
		super("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // Sets the JFrame's container with a null Layout Manager (Absolute positioning)
		setBounds(0,0,700,600); // Sets the size of the JFrame
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // we set so window so the program won't exit when the window is closed
		
		this.dbManager = dbManager;
		
		number = new JLabel();
		number.setText("Question  1/10");
		number.setBounds(20,20,150,30);
		getContentPane().add(number);
		
		
		// We add the check box and radio button in 2 list for an easier access when we need to retrieve information from them
		listBox.add(ansA);
		listBox.add(ansB);
		listBox.add(ansC);
		listBox.add(ansD);
		listRadio.add(radioA);
		listRadio.add(radioB);
		listRadio.add(radioC);
		listRadio.add(radioD);
		
		
		question = new JLabel();
		question.setText("<html>Hello World!blah</html>");
		question.setBounds(50,100,600,100);
		getContentPane().add(question);
		
		JButton validate = new JButton();
		validate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				boolean isCorrectAns = false;
				
				if(!hasAnswered){  // we use the same button to go to the next question and to validate answer so we need to see, what action is needed when clicked
					hasAnswered = true;
					
					// We retrieve the answers from the right field according to the getType() of question, then test is it is the correct answer
					if(currentQ.getType().equals("text")){// we just need to read the text
						isCorrectAns = currentQ.isGoodAnswer(ansText.getText());
					}
					
					if(currentQ.getType().equals("multiple")){ // we need to gather the value of all selected checkbox
						int value = 0;
						String[] temp = new String[getNumberOfCheckedBox()];
						for(int i =0; i < currentQ.getPropositions().length; i++){
							if(listBox.get(i).isSelected()){
								temp[value] = listBox.get(i).getText();
								value++;
							}
							
						}
						isCorrectAns = currentQ.isGoodAnswer(temp);
					}
					
					if(currentQ.getType().equals("combo")){ // we take the selected item and test if it's the good answer
						isCorrectAns = currentQ.isGoodAnswer(ansCombo.getSelectedItem().toString());
					}
					
					if(currentQ.getType().equals("radio")){
						String temp = "";
						for(int i = 0; i < currentQ.getPropositions().length; i++){ // we look for the selected radio button and retrieve its value
							if(listRadio.get(i).isSelected()) temp = listRadio.get(i).getText();
						}
						isCorrectAns = currentQ.isGoodAnswer(temp);
					}
					
					if(isCorrectAns){  // if the answer is correct we display correct answer text
						answer.setText("Correct Answer !");
						answer.setVisible(true);
						answer.setBackground(Color.GREEN);
						result ++; // we add a point to the result
					}
					else{
						// If wrong answer we will diplay the correct one
						answer.setText(currentQ.dispCorrectAns());
						answer.setVisible(true);
						answer.setBackground(Color.RED);
					}
					
					validate.setText("Next Question");
					
				}
				else{ // if the question has been, answeredn the response is then displayed until user click again, then we load a new question
					
					hasAnswered = false;
					hideAllElements();
					LoadNextQuestion();
					validate.setText("Validate");
				}
				
				
			}
		});
		
		// We had all the element we will need to the window 
		
		validate.setText("Validate");
		validate.setBounds(500, 500, 150, 40);
		getContentPane().add(validate);
		
		ansText.setBounds(50,200, 200,40);
		getContentPane().add(ansText);
		ansText.setVisible(false);
		
		ansA.setBounds(50,250,200,40);
		getContentPane().add(ansA);
		ansA.setVisible(false);
		
		ansB.setBounds(50,300,200,40);
		getContentPane().add(ansB);
		ansB.setVisible(false);
		
		ansC.setBounds(50,350,200,40);
		getContentPane().add(ansC);
		ansC.setVisible(false);
		
		ansD.setBounds(50,400,200,40);
		getContentPane().add(ansD);
		ansD.setVisible(false);
		
		ansCombo.setBounds(50,200,200,40);
		getContentPane().add(ansCombo);
		ansCombo.setVisible(false);
		
		radioA.setBounds(50,250,200,40);
		getContentPane().add(radioA);
		radioA.setVisible(false);
		
		radioB.setBounds(50,300,200,40);
		getContentPane().add(radioB);
		radioB.setVisible(false);
		
		radioC.setBounds(50,350,200,40);
		getContentPane().add(radioC);
		radioC.setVisible(false);
		
		radioD.setBounds(50,400,200,40);
		getContentPane().add(radioD);
		radioD.setVisible(false);
		
		group.add(radioA);
		group.add(radioB);
		group.add(radioC);
		group.add(radioD);
		
		answer = new JLabel();
		answer.setBounds(50,500,400,40);
		answer.setOpaque(true);
		answer.setBackground(Color.green);
		answer.setVisible(false);
		getContentPane().add(answer);
		
		
		GenerateQuestions(); // We load the question into the list
		LoadNextQuestion(); // and launch the first question
		
		
	}
	


	// We add hard coded questions and those save on the DB to a list containing all questions
	private void GenerateQuestions(){ 	
		questionsList.add(new Question("text", "1917", "In which year finland became independent ?", null ,  null));
		questionsList.add(new Question("text", "Mercury", "Which of the Planets is Closest to the Sun?",null, null));
		questionsList.add(new Question("combo","True", "During the 20 centure, mondial population increased by 360% ?",null, new String[]{"True","False" }));
		questionsList.add(new Question("radio","True", "Sputnik 1  was the first artificial Earth satellite? ",null, new String[]{"True","False"}));
		questionsList.add(new Question("radio","Ra","Which of the following is th God of the Sun in egyptian mythologie ?", null,new String[]{"Ra", "Anubis", "Seth", "Osiris"}));
		questionsList.add(new Question("combo","Dog","What animal first reached Earth orbits alive ?", null,new String[]{"Ape", "Dog", "Cockroach"}));
		questionsList.add(new Question("multiple","","Which of the following are currently on Mars ?", new String[]{"Curiosity", "Beagle 2"},new String[]{"Curiosity", "Explorer 1", "Lunokhod 2", "Beagle 2"}));
		questionsList.add(new Question("radio","1946","Whatis the birth year of Steven Spielberg ?", null,new String[]{"1940", "1946", "1949", "1959"}));
		questionsList.add(new Question("multiple","","What programming languages were use to developp thi application ?", new String[]{"Java", "MySQL"},new String[]{"Java", "MySQL", "C++", "Python"}));
		questionsList.add(new Question("radio","Awesome","How cool is this quizz ? ", null,new String[]{"Awesome", "Awesome", "Awesome", "Awesome"}));


		if(dbManager == null){ //If the database wasn't connected when the window open, the instance will be null so we don't retrieve the question from the database
			return;
		}
		ArrayList<Question> databaseQuestion = dbManager.getAllQuestions();
		for(int  i = 0; i < databaseQuestion.size(); i++ ){
			questionsList.add(databaseQuestion.get(i));
		}
	}
	
	 // We Display the right element on the window according to the next question
	private void LoadNextQuestion(){
		
		if( countQ == 10){ // If we reach the end of the quizz, we save the result in database then close the quizz windo
			insertResult();		
			processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			
			return;
		}
		countQ++;
		
		number.setText("Question " + countQ + "/10"); // we update the question counter
		int index =  getRandomIndex();
		
		currentQ = questionsList.get(index); // We take the next question randomly from the list
		Question removed = questionsList.remove(index);    // Then we remove the question from the list so it won't appear twice or more
		
		
		// We will display all the element needed ( question , textfield, combo box ... )
		
		question.setText("<html> " + currentQ.getQuestion() + "</html>");
		if(currentQ.getType().equals("text")){
			ansText.setVisible(true);
		}
		if(currentQ.getType().equals("multiple")){
			
			for(int i = 0; i < currentQ.getPropositions().length; i++){
				listBox.get(i).setVisible(true);
				listBox.get(i).setText(currentQ.getPropositions()[i]);
			}
		
			
		}
		if(currentQ.getType().equals("combo")){
			
			
			for(int i = 0; i < currentQ.getPropositions().length; i++){
				ansCombo.addItem(currentQ.getPropositions()[i]);
			}
			ansCombo.setVisible(true);
		}
		
		if(currentQ.getType().equals("radio")){
			
			for(int i = 0; i< currentQ.getPropositions().length; i++){
				listRadio.get(i).setText(currentQ.getPropositions()[i]);
				listRadio.get(i).setVisible(true);
			}
		}
		
	}
	
	 // Retrieve the number of checkbox selected 
	private int getNumberOfCheckedBox(){
		int count = 0;
		for(int i =0; i < currentQ.getPropositions().length; i++){
			if(listBox.get(i).isSelected()){
				count ++;
			}
		}
		return count;
	}
	
	// Hide all the element on the window so we will just have to display the right one for the next question
	private void hideAllElements(){
		ansText.setVisible(false);
		ansA.setVisible(false);
		ansB.setVisible(false);
		ansC.setVisible(false);
		ansD.setVisible(false);
		ansCombo.setVisible(false);
		radioA.setVisible(false);
		radioB.setVisible(false);
		radioC.setVisible(false);
		radioD.setVisible(false);
		answer.setVisible(false);
		ansText.setText("");
		ansCombo.removeAllItems();
		
	}
	
	
	//Open an option pane to ask user for a name to save his result in database
	private void insertResult(){
		String name = JOptionPane.showInputDialog("<html>You have a score of " + result + "/10 <br> Type in your name </html>");
		
		if(name != null){ //  if user click cancel, the name will be null, therefore, we won't save it
			
			if(dbManager != null){ // the database may not be connected when the window open 
				dbManager.insertNewResult(name, result);
			}
			else{  // we show an error message if database was not connected
				JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Could not connect to database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	 // return a random index, the max value will be less than the list length;
	private int getRandomIndex(){
		return (int)(Math.random() * questionsList.size());
	}
}
