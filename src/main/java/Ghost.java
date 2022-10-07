
public class Ghost {
	int x;
	int y;
	int dx;
	int dy;

	public Ghost(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;

	}

	public void moveForward() {
		x = x + dx;
		y = y + dy;
	}

	public void changeDirection() {
		int temp = dx;
		dx = -dy;
		dy = temp;
	}

}
