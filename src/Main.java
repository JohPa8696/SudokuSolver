

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 * Main Class launches the launcher frame and ask user for a project directory
 */
public class Main {
	
	
	public static void main(String[] args){
		
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}
	

}