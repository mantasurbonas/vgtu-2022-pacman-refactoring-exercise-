
public class Ghost {
	int x;
	int y;
	int dx;
	int dy;

	public Ghost(int x, int y, int dx, int dy) {
		this.x = 6;
		this.y = 7;
		this.dx = -1;
		this.dy = 0;

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
