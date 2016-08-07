
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		String fileName="input.txt";
		InputProcessor ip=new InputProcessor(fileName);
		ip.processInput();
		
	}
}

