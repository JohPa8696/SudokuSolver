import java.util.ArrayList;

import javax.swing.JTextField;

public class SudokuSolver {
	
	private ArrayList<JTextField> list;


	public SudokuSolver(ArrayList<JTextField> list){
			this.list=list;
	}
	
	/**
	 * Loop through the board and populate the status board
	 * @param board
	 * @return
	 */
	public boolean solve(int[][] board){
		int[][] status =new int[9][9];
		
		for(int i=0; i<9; i++){
			for (int j=0; j<9; j++){
				status[i][j]=board[i][j]>0?2:0; // 2 for values that are fixed
			}
		}
		return solved(board, status, 0,0);
	}
	
	
	// Recursive function
	public boolean solved(int[][] board, int [][] status, int row, int col){
			
		//Check if the board has been completed
		if(row==9){
			int count =0; 
			for(int i=0; i<9;i++){
				for (int j=0; j<9; j++){
					count +=status[i][j]>0?1:0;
				}
			}
			
			if(count==81){
				return true;
			}else{
				return false;
			}
		}
		
		
		// If the position of interest is not vacant look at the one next to it
		if(status[row][col]>=1){
			
			int nextRow=row;
			int nextCol= col+1;
			// if the position is last in a row move to the next row
			if(nextCol>=9){
				nextRow=row+1;
				nextCol=0;
			}
			// recursive call
			return solved(board, status, nextRow, nextCol);
			
		}else{
			
			boolean[] used = new boolean[9];
			//check row
			for(int i=0; i<9; i++){
				if(status[row][i]>=1){
					used[board[row][i]-1]=true;
				}
					
			}
			//check col
			for(int i=0; i<9; i++){
				if(status[i][col]>=1){
					used[board[i][col]-1]=true;
				}		
			}
			
			//check 3x3 box
			for(int i=row-(row%3);i<row-(row%3)+3;i++){
				for(int j=col-(col%3);j<col-(col%3)+3;j++){
					if(status[i][j]>=1){
						used[board[i][j]-1]=true;
					}		
				}
			}
			
			//Assign value
			for(int i=0; i<used.length;i++){
				if(!used[i]){
					status[row][col]=1;
					board[row][col]= i+1;
					this.list.get(col+row*9).setText(Integer.toString(i+1));
					int nextRow=row;
					int nextCol= col+1;
					if(nextCol>=9){
						nextRow=row+1;
						nextCol=0;
					}
					if(solved(board, status, nextRow, nextCol)){
						return true;
					}
					
					for(int k=0; k<9; k++){
						for(int h=0; h<9; h++){
							if(k>row||k==row&&h>=col){
								if(status[k][h]==1){
									status[k][h]=0;
									board[k][h]=0;
									this.list.get(col+row*9).setText("");
								}
							}
						}
					}
				}
			}
				
		}
		return false;
	}
}
