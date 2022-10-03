import java.util.List;

public class GameRules {

	private List<Ghost> ghosts;
	private Pacman pacman;
	private Maze maze;
	
	public boolean gameOver = false;

	public GameRules(Maze maze, Pacman pacman, List<Ghost> ghosts) {
		this.maze = maze;
		this.pacman = pacman;
		this.ghosts = ghosts;
	}

	public boolean hitWall(Ghost ghost) {
		return maze.isWall(ghost.x + ghost.dx, ghost.y + ghost.dy);
	}
	
	public void eatCookie() {
		maze.eatCookie(pacman.x, pacman.y);
	}

	public void moveElements() {
		for (Ghost ghost: ghosts)
			if (pacman.x == ghost.x && pacman.y == ghost.y)
				gameOver = true;
		
		for (Ghost ghost: ghosts)
			if (hitWall(ghost))
				ghost.changeDirection();
			else
				ghost.moveForward();
		
		eatCookie();
	}

	public void processUserInput(byte userInput) {
		switch(userInput) {
		case 'q':
			gameOver = true;
			break;
		case 'a':
			if (maze.isWall(pacman.x-1, pacman.y) == false)
				pacman.x -= 1;
			break;
		case 'd':
			if (maze.isWall(pacman.x+1, pacman.y) == false)
				pacman.x += 1;
			break;
		case 'w':
			if (maze.isWall(pacman.x, pacman.y-1) == false)
				pacman.y -= 1;
			break;
		case 's': 
			if (maze.isWall(pacman.x, pacman.y+1) == false)
				pacman.y += 1;
			break;
	}
	}
}
