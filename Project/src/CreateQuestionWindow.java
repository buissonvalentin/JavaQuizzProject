import javax.swing.JFrame;
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

public class CreateQuestionWindow extends JFrame  {

	
	// We declare all the field we will need;
	JLabel labAnsA;
	JLabel labAnsB;
	JLabel labAnsC;
	JLabel labAnsD;
	JLabel labPropA;
	JLabel labPropB;
	JLabel labPropC;
	JLabel labPropD;
	JTextField ansA;
	JTextField ansB;
	JTextField ansC;
	JTextField ansD;
	JTextField propA;
	JTextField propB;
	JTextField propC;
	JTextField propD;
	JLabel text3;
	JTextField question;
	JComboBox typeChoice;
	ArrayList<JTextField> ansList = new ArrayList<JTextField>();
	ArrayList<JTextField> propList = new ArrayList<JTextField>();
	DataBaseManager dbManager; 
	
	
	
	public CreateQuestionWindow(DataBaseManager dbM){
		super("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // Sets the JFrame's container with a null Layout Manager (Absolute positioning)
		setBounds(0,0,700,400); // Sets the size of the JFrame
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dbManager = dbM;
		
		
		
		
		
		
		// We add the different type of questions to the Combo box 
		typeChoice = new JComboBox();
		typeChoice.addItem("text");
		typeChoice.addItem("multiple");
		typeChoice.addItem("combo");
		typeChoice.addItem("radio");
		
		typeChoice.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				// When a new element is chosen in the combo box, we update the element in the window
				displayRightElement(typeChoice.getSelectedItem().toString());
			}
		});
		typeChoice.setBounds(100,80,100,30);
		getContentPane().add(typeChoice);
		
		
		
		// We then add all the element to the window
		
		question = new JTextField();
		question.setBounds(100, 20, 450, 30);
		getContentPane().add(question);
		
		JLabel text  = new JLabel("Question : ");
		text.setBounds(20, 20, 80, 30);
		getContentPane().add(text);
		
		JLabel text1  = new JLabel("Type : ");
		text1.setBounds(20, 80, 80, 30);
		getContentPane().add(text1);
		
		JLabel text2  = new JLabel("<html>Correct answer (leave blank for no answer<br> at least one must be filled) :</html> ");
		text2.setBounds(20, 120, 300, 30);
		getContentPane().add(text2);
		
		
		 labAnsA  = new JLabel("A : ");
		labAnsA.setBounds(30, 150, 100, 30);
		getContentPane().add(labAnsA);
		 ansA = new JTextField();
		ansA.setBounds(50, 150, 100, 30);
		getContentPane().add(ansA);
		
		
		 labAnsB  = new JLabel("B : ");
		labAnsB.setBounds(30, 190, 100, 30);
		getContentPane().add(labAnsB);
		 ansB = new JTextField();
		ansB.setBounds(50, 190, 100, 30);
		getContentPane().add(ansB);
		
		 labAnsC  = new JLabel("C : ");
		labAnsC.setBounds(30, 230, 100, 30);
		getContentPane().add(labAnsC);
		 ansC = new JTextField();
		ansC.setBounds(50, 230, 100, 30);
		getContentPane().add(ansC);
		
		 labAnsD  = new JLabel("D : ");
		labAnsD.setBounds(30, 270, 100, 30);
		getContentPane().add(labAnsD);
		 ansD = new JTextField();
		ansD.setBounds(50, 270, 100, 30);
		getContentPane().add(ansD);
		
		
		 text3  = new JLabel("<html>Propositions (leave blank for no answer<br> at least one must be filled) :</html> ");
		text3.setBounds(400, 120, 300, 30);
		getContentPane().add(text3);
		
		
		 labPropA  = new JLabel("A : ");
		labPropA.setBounds(430, 150, 100, 30);
		getContentPane().add(labPropA);
		 propA = new JTextField();
		propA.setBounds(450, 150, 100, 30);
		getContentPane().add(propA);
		
		 labPropB  = new JLabel("B : ");
		labPropB.setBounds(430, 190, 100, 30);
		getContentPane().add(labPropB);
		 propB = new JTextField();
		propB.setBounds(450, 190, 100, 30);
		getContentPane().add(propB);
		
		 labPropC  = new JLabel("C : ");
		labPropC.setBounds(430, 230, 100, 30);
		getContentPane().add(labPropC);
		 propC = new JTextField();
		propC.setBounds(450, 230, 100, 30);
		getContentPane().add(propC);
		
		 labPropD  = new JLabel("D : ");
		labPropD.setBounds(430, 270, 100, 30);
		getContentPane().add(labPropD);
		 propD = new JTextField();
		propD.setBounds(450, 270, 100, 30);
		getContentPane().add(propD);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				// If the right field are filled in we create the question
				if(typeChoice.getSelectedItem().toString().equals("text")){
					if(question.getText().equals("") || ansA.getText().equals("")){
						showErrorMessage();
						return;
					}
				}
				
				else{
					if(question.getText().equals("") || ansA.getText().equals("") || propA.getText().equals("")){
						showErrorMessage();
						return;
					}
				}
				
				createQuestion();
				
				
				
			}
		});
		confirm.setBounds(300,310,100,40);
		getContentPane().add(confirm);
		
		
		typeChoice.setSelectedIndex(0);
		
		// We add some field to a list for an easier acces to the data 
		ansList.add(ansA);
		ansList.add(ansB);
		ansList.add(ansC);
		ansList.add(ansD);
		propList.add(propA);
		propList.add(propB);
		propList.add(propC);
		propList.add(propD);
		
	}
	
	 // we display the needed element according to the type of the question
	private void displayRightElement(String type){
		if(type.equals("text")){
			 labAnsB.setVisible(false);
			 labAnsC.setVisible(false);
			 labAnsD.setVisible(false);
			 labPropA.setVisible(false);
			 labPropB.setVisible(false);
			 labPropC.setVisible(false);
			 labPropD.setVisible(false);
			 ansB.setVisible(false);
			 ansC.setVisible(false);
			 ansD.setVisible(false);
			 propA.setVisible(false);
			 propB.setVisible(false);
			 propC.setVisible(false);
			 propD.setVisible(false);
			 text3.setVisible(false);
		}
		else if(type.equals("multiple")){
			 labAnsB.setVisible(true);
			 labAnsC.setVisible(true);
			 labAnsD.setVisible(true);
			 labPropA.setVisible(true);
			 labPropB.setVisible(true);
			 labPropC.setVisible(true);
			 labPropD.setVisible(true);
			 ansB.setVisible(true);
			 ansC.setVisible(true);
			 ansD.setVisible(true);
			 propA.setVisible(true);
			 propB.setVisible(true);
			 propC.setVisible(true);
			 propD.setVisible(true);
			 text3.setVisible(true);
		}
		else{
			labAnsB.setVisible(false);
			 labAnsC.setVisible(false);
			 labAnsD.setVisible(false);
			 ansB.setVisible(false);
			 ansC.setVisible(false);
			 ansD.setVisible(false);
			 labPropA.setVisible(true);
			 labPropB.setVisible(true);
			 labPropC.setVisible(true);
			 labPropD.setVisible(true);
			 propA.setVisible(true);
			 propB.setVisible(true);
			 propC.setVisible(true);
			 propD.setVisible(true);
			 text3.setVisible(true);
		}
	}
	
	// Display a JPanel error message
	private void showErrorMessage(){ 
		JPanel panel = new JPanel();
	    JOptionPane.showMessageDialog(panel, "You must fill the required field", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	// We create a new instance of Question using all the informations filled in by the user
	private void createQuestion(){ 
		String q = question.getText();
		String correctAns = "";
		String type = typeChoice.getSelectedItem().toString();
		String[] correctAnswers;
		String[] propositions;
		Question newQuestion;
		
		if(type.equals("text")){
			correctAns = ansA.getText();
			 newQuestion = new Question(type,correctAns,q,null,null);
		}
		if(type.equals("multiple")){
			
			// We need to create a array containing the input of the user
			int index = 0;
			correctAnswers = new String[getNumberOfAns()]; // We need to define the length of the array
			for(int i = 0; i< correctAnswers.length; i++ ){
				if(!ansList.get(i).getText().equals("")){
					correctAnswers[index] = ansList.get(i).getText();
					index ++;
				}
			}
			
			// We need to create a array containing the input of the user
			index = 0;
			propositions = new String[getNumberOfProp()]; // We need to define the length of the array
			for(int i = 0; i< propositions.length; i++ ){
				if(!propList.get(i).getText().equals("")){
					propositions[index] = propList.get(i).getText();
					System.out.println(propList.get(i).getText());
					index ++;
				}
			}
			 newQuestion = new Question(type,"",q,correctAnswers, propositions);
			
		}
		else{
			// We need to create a array containing the input of the user
			int index = 0;
			propositions = new String[getNumberOfProp()]; // We need to define the length of the array
			for(int i = 0; i< propositions.length; i++ ){
				if(!propList.get(i).getText().equals("")){
					propositions[index] = propList.get(i).getText();
					System.out.println(propList.get(i).getText());
					
					index ++;
				}
			}
			 newQuestion = new Question(type,correctAns,q,null, propositions);
		}
		
		// We then save the question in the database and close the window
		dbManager.insertNewQuestion(newQuestion);
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	 // return the number of answers field filed by the user
	private int getNumberOfAns(){
		int count = 0;
		for(int i = 0; i< 4; i++ ){
			if(!ansList.get(i).getText().equals("")){
				count ++;
			}
		}
		return count;
	}
	
	// return the number of propositions field filed by the user
	private int getNumberOfProp(){ 
		int count = 0;
		for(int i = 0; i< 4; i++ ){
			if(!propList.get(i).getText().equals("")){
				count ++;
			}
		}
		return count;
	}
}
