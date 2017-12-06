import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DataBaseManager {
	private static final String URL = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net:3306/buisson_valentin";
	private static final String USERNAME = "b52086132cfd87";
	private static final String PASSWORD = "e924a3b6";
	//Private variables to store connection and prepared statements
	private Connection connection = null;
	
	private PreparedStatement insertResult = null;
	private PreparedStatement selectquizzs = null;
	private PreparedStatement selectresults = null;
	private PreparedStatement selectAllQuestions = null;
	private PreparedStatement insertQuestion = null;
	
	public DataBaseManager(){
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); 		
		
			selectAllQuestions = connection.prepareStatement("SELECT * FROM questions");
			
			selectresults = connection.prepareStatement("SELECT * FROM results");
			
			insertResult = connection.prepareStatement("INSERT INTO results (user, score, date) VALUES (?,?,?);");
			
			insertQuestion = connection.prepareStatement("INSERT INTO questions ( type, correctAnswer, question, correctAnswers, propositions) VALUES (?,?,?,?,?) ");
			
			
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		};
		
	}
	
	// We insert a new result containing : score , name and date 
	public void insertNewResult(String user, int score){ 
		
		// We get the current date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate).toString();
		
		try{
			insertResult.setString(1, user);
			insertResult.setInt(2, score);
			insertResult.setString(3, date);
			
			insertResult.executeUpdate();
			
		}
		catch(SQLException e){

			e.printStackTrace();
		}
		
	}
	
	// We save a new question in database
	public void insertNewQuestion(Question q){ 
		
		String correctAnswers = q.arrayToStringCorrectAnswers();
		String propositions = q.arrayToStringPropositions();
		
		try{
			insertQuestion.setString(1, q.getType());
			insertQuestion.setString(2, q.getCorrectAnswer());
			insertQuestion.setString(3, q.getQuestion());
			insertQuestion.setString(4, correctAnswers);
			insertQuestion.setString(5, propositions);
			
			insertQuestion.executeUpdate();
			
		}
		catch(SQLException e){

			e.printStackTrace();
		}
	}
	
	// We retrieve all saved questions in database
	public ArrayList<Question> getAllQuestions() 
	{
		ArrayList<Question> results = null;
		ResultSet resultSet = null;
		
		try
		{
			resultSet = selectAllQuestions.executeQuery(); // Here is where we actually execute the select query. resultSet contains the rows returned by the query
			results = new ArrayList<Question>();
		
			while(resultSet.next()) // for each row returned by the select query...
			{
				// We take different data according to the type of question we have
				if(resultSet.getString("type").equals("text")){
					results.add(new Question(
							resultSet.getString("type"), // get the value associated to the type
							resultSet.getString("correctAnswer"), // get the value associated to the answer
							resultSet.getString("question"), // get the value associated to the question
							null,
							null)); 
				}
				else if(resultSet.getString("type").equals("multiple")){
					
					
					results.add(new Question(
							resultSet.getString("type"), // get the value associated to the type
							"", // get the value associated to the answer
							resultSet.getString("question"), // get the value associated to the question
							resultSet.getString("correctAnswers").split("/"),
							resultSet.getString("propositions").split("/"))); 
				}
				else{
					results.add(new Question(
							resultSet.getString("type"), // get the value associated to the type
							resultSet.getString("correctAnswer"), // get the value associated to the answer
							resultSet.getString("question"), // get the value associated to the question
							null,
							resultSet.getString("propositions").split("/"))); 
				}
			}
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		} // end finally
		
		return results;
	} 
	
	 // We retreive result from the database
	public String getAllResult()
	{
		// We use html balise to be able to break to an other line at each new result
		String text = "<html>";
		ResultSet resultSet = null;
		ArrayList<String> result = new ArrayList<String>(); 
		
		try
		{
			resultSet = selectresults.executeQuery(); // Here is where we actually execute the select query. resultSet contains the rows returned by the query		
			
			while(resultSet.next()) // for each row returned by the select query...
			{			
				// We keep all the result in an arrayList
				result.add("<br>" +  resultSet.getString("user") + " : " +  resultSet.getString("score") + "/10 (" + resultSet.getString("date") + ")");
			}
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			
			try
			{
				resultSet.close();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		} // end finally
		
		// We only select the  5 last result 
		int begin = 0;
		if(result.size() > 5 ){
			begin = result.size() - 5;
		}
		for(int i = begin ; i < result.size(); i++){
			text += result.get(i).toString();
		}
		
		text += "</html>";
		
		return text;
	} 
	
	


}
