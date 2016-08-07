import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputProcessor {
	private int[][] board= new int[9][9];
	private String fileName;
	public InputProcessor(String name){
		this.fileName=name;
	}
	
	public void processInput() throws FileNotFoundException{
		Scanner scan= new Scanner(new File(fileName));
		int i=0;
		while(scan.hasNext()){
			String line=scan.nextLine();
			//System.out.println(line);
			if(line.contains("-")){
				continue;
			}else{
				line=line.replace("|", "");
				line=line.replace(" ", "");
				System.out.println(line);
				for(int j=0; j<9; ++j){
					board[i][j]=Character.getNumericValue(line.charAt(j));
				}
				i++;
			}
		}
	}
	
	
}


