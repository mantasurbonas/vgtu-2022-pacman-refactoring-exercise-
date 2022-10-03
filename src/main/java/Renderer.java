import java.util.List;

public class Renderer {

	private List<Ghost> ghosts;
	private Pacman pacman;
	private Maze maze;

	public Renderer(Maze maze, Pacman pacman, List<Ghost> ghosts) {
		this.maze = maze;
		this.pacman = pacman;
		this.ghosts = ghosts;
	}
	
	public void renderGame() {
		for (int y=0; y<maze.getHeight(); y++) {
			for (int x=0; x<maze.getWidth(); x++) {
				if (y == pacman.y && x == pacman.x)
					System.out.print("C");
				else
				if (isGhost(x, y))
					System.out.print("@");
				else
				if (maze.isWall(x, y))
					System.out.print("#");
				else
				if (maze.isCookie(x, y))
					System.out.print(".");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	private boolean isGhost(int x, int y) {
		for (Ghost ghost: ghosts)
			if (ghost.x == x && ghost.y == y)
				return true;
		
		return false;
	}
}
