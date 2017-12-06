import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mainWindow extends JFrame {
	

	static mainWindow gui;
	static DataBaseManager dbManager;
	
	public mainWindow(){
		super("Quizz");
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // Sets the JFrame's container with a null Layout Manager (Absolute positioning)
		setBounds(0,0,550,400); // Sets the size of the JFrame
		setLocationRelativeTo(null);
		
		
		
		
		
		JLabel txt = new JLabel("Latest quizz result");
		txt.setBounds(350, 20, 200, 20);
		getContentPane().add(txt);
		
		JLabel results = new JLabel("");
		results.setBounds(350, 40, 200, 100);
		getContentPane().add(results);
		
		
		JButton newquizz = new JButton(); // start a new quizz and update the result display
		newquizz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(dbManager != null){ // We need the database to be connected to load the last result but the test can be done "offline" 
					results.setText(dbManager.getAllResult());
				}	
				QuestionWindow wind = new QuestionWindow(dbManager);
				wind.setVisible(true);
			}
		});
		newquizz.setText("Start a new quizz");
		newquizz.setBounds(50, 100, 200, 40);
		getContentPane().add(newquizz);
		
		JButton createquizz = new JButton(); // Open the window to create a new quizz
		createquizz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(dbManager != null){ // since we want to save question on database, we to to wait for it to connect
					dbManager.getAllResult();
					CreateQuestionWindow wind = new CreateQuestionWindow(dbManager);
					wind.setVisible(true);
				}
				else{
					showErrorMessage();
				}
				
			}
		});
		createquizz.setText("Create your own quizz");
		createquizz.setBounds(50,150,200,40);
		getContentPane().add(createquizz);
		
			
		
		// Create a menu with an exit option
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu menu = new JMenu();
		menu.setText("File");
        
		menuBar.add(menu);
       
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(item);
		
		
	}
	
	public static void main(String[] args){
		gui = new mainWindow();
		gui.setVisible(true);
		
		dbManager = new DataBaseManager();
		System.out.println(dbManager);
		
		
	}
	
	// Show an error pane
	private void showErrorMessage(){ 
		JPanel panel = new JPanel();
	    JOptionPane.showMessageDialog(panel, "Wait until connection to database is established", "Error", JOptionPane.ERROR_MESSAGE);
	}
	


}
