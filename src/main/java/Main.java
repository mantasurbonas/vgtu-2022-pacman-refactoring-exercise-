import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
		
	public static void main(String a[]) throws IOException {
		
		Pacman pacman = new Pacman(5, 5);

		List<Ghost> ghosts = new ArrayList<>();
		ghosts.add(new Ghost(6, 7, -1, 0));
		ghosts.add(new Ghost(10, 17, 0, 1));		
		
		Maze maze = new Maze();
		
		Renderer renderer = new Renderer(maze, pacman, ghosts);
		GameRules rules   = new GameRules(maze, pacman, ghosts);
		
		while(rules.gameOver == false) {
		
			renderer.renderGame();
			
			byte userInput = readUserInput();
			
			rules.processUserInput(userInput);
			
			rules.moveElements();
		}
		
	}

	private static byte readUserInput() throws IOException {
		byte bytes[] = new byte[10];
		System.in.read(bytes);
		byte userInput = bytes[0];
		return userInput;
	}
	
}