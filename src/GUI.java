import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI extends JFrame implements ActionListener{

	private JFrame frame;
	private ArrayList<JTextField> list = new ArrayList<JTextField>();
	private JFileChooser chooser;
	private JPanel buttonPanel;
	private String fileName;
	
	private JButton loadInput;
	private JButton solve;
	
	private int[][] board;
	
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sudoku Solver");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(700, 150, 527, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(0, 0, 520, 530);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		int xPos=10; 
		int yPos=10;
		for (int i=0; i<81; i++){
			JTextField tf= new JTextField();
			tf.setHorizontalAlignment(JTextField.CENTER);
			tf.setFont( new Font("SansSerif", Font.PLAIN, 18));
			if(i%9==0 && i!=0){
				xPos=10; 
				if(i%27==0){
					yPos+=60;
				}else{
					yPos+=55;
				}
			}
			
			tf.setColumns(10);
			tf.setBounds(xPos,yPos,50,50);
			list.add(tf);
			panel.add(tf);
			if(i%3==2){
				xPos+=60;
			}else{
				xPos+=55;
			}
		}
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setBounds(0, 530, 520, 50);
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//load inout button
		loadInput = new JButton("Load Input");
		loadInput.addActionListener(this);
		loadInput.setFont(new Font("Tahoma", Font.BOLD, 18));
		loadInput.setBounds(0, 0, 260, 50);
		buttonPanel.add(loadInput);
		
		solve = new JButton("Solve");
		solve.setForeground(new Color(0, 0, 0));
		solve.setFont(new Font("Tahoma", Font.BOLD, 18));
		solve.setBounds(260, 0, 260, 50);
		solve.addActionListener(this);
		buttonPanel.add(solve);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource()== loadInput){
			fileChooser();
		}else if(e.getSource()== solve){
			
			SudokuSolver solver= new SudokuSolver(list);
			solver.solve(board);
			/*CALL THE ALGORITHM*/
			// initiate a dialoug box : SOLVED!
		}
		
	}
	
	
	public void fileChooser(){
		if (chooser == null) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter;
			filter = new FileNameExtensionFilter("Input file", "txt", "dot");
			chooser.setDialogTitle("Choose an Input File");
			chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
			chooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
			chooser.setAcceptAllFileFilterUsed(false);
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fileName=chooser.getSelectedFile().toString();
				InputProcessor ip= new InputProcessor(fileName);
				try {
					ip.processInput();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				board= ip.getBoard();
				populateBoard(board);
			}else{
			}
			chooser = null;
		}
	}
	
	
    private void populateBoard(int[][] board){
    	// Clear the board
    	
    	for(int i=0; i<list.size(); i++){
    		JTextField tf= list.get(i);
        	tf.setFont(new Font("SansSerif", Font.PLAIN, 18));
        	tf.setText("");
        	tf.setEditable(true);
    	}
    	// Populate the board
    	int k =0;
        for(int i = 0 ; i < 9; i++){
            for( int j = 0; j < 9; j++){
                int num=board[i][j];
                if(num != 0){
                	JTextField tf= list.get(k);
                	tf.setFont(new Font("SansSerif", Font.BOLD, 18));
                	tf.setText(num+"");
                	tf.setEditable(false);
                }
                k++;
            }
        }
    }
    
    
	
	
}
