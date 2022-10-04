import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class WinGame extends JFrame {

	Pacman pacman;
	List<Ghost> ghosts;
	Maze maze;
	GameRules rules;
	
	BufferedImage ghostImage;
	BufferedImage pacmanImage;
	BufferedImage wallImage;
	
	public WinGame() throws FileNotFoundException, IOException {
		super.setPreferredSize(new Dimension(1500, 900));
		super.pack();
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pacmanImage = ImageIO.read(new FileInputStream("img/pacman-open.png"));
		ghostImage = ImageIO.read(new FileInputStream("img/ghost.png"));
		wallImage = ImageIO.read(new FileInputStream("img/wall.png"));
		
		pacman = new Pacman(5, 5);

		ghosts = new ArrayList<>();
		ghosts.add(new Ghost(6, 7, -1, 0));
		ghosts.add(new Ghost(10, 17, 0, 1));		
		
		maze = new Maze();
		
		rules   = new GameRules(maze, pacman, ghosts);
		
		super.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				char command = e.getKeyChar();
				repaint();
				
				rules.processUserInput(command);
			}
		});
		
		new Timer(300, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rules.moveElements();
				if (rules.gameOver)
					System.exit(1);
				repaint();
			}
		}).start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(pacmanImage, 
						50 + pacman.x*20, 
						50 + pacman.y*20,
						20,20,null);
		
		for (Ghost ghost: ghosts)
			g.drawImage(ghostImage, 
					50 + ghost.x*20, 
					50 + ghost.y*20,
					20,20,null);
		
		for (int y=0; y<maze.getHeight(); y++) {
			for (int x=0; x<maze.getWidth(); x++) {
				if (maze.isWall(x, y))
					g.drawImage(wallImage,
							50 + x*20, 
							50 + y*20,
							20,20,null);
			}
		}
	}
	
	public static void main(String[] s) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				try {
					new WinGame();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
