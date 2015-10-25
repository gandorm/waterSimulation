package gerstner;

public class Point {

	private double x = 0;
	private double y = 0;
	private double h = 0;

	public Point(double x, double y, double h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}

	public void changeHeightOverTime(double amplitude, double[] wavevector, double omega, double time) {
		double y = -666;
		double vectorProduct = wavevector[0] * x + wavevector[0] * y
				+ wavevector[1] * x + wavevector[1] * y;
		y = amplitude * Math.cos(vectorProduct - (omega * time));

		this.h = y;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}

	@Override
	public String toString() {
		return String.valueOf(this.h);
	}



}
