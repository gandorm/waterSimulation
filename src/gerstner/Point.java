package gerstner;

public class Point {

	private int x = 0;
	private int y = 0;
	private int h = 0;

	public Point(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	@Override
	public String toString() {
		return String.valueOf(this.h);
	}



}
