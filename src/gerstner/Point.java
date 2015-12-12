package gerstner;

import java.util.List;

public class Point {

	private double x = 0;
	private double y = 0;
	private double h = 0;

	public Point(double x, double y, double h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}

	public void changeHeightOverTime(double amplitude, double[] wavevector, double time) {
		double y = -666;
		double omega = (2 / Math.PI);
		
		double vectorProduct = (wavevector[0] * this.x) + (wavevector[1] * this.y);
		y = amplitude * Math.cos(vectorProduct - (omega * time));

		this.h = y;
	}
	
	public void changeHeightOverTime(List<Wave> waves, double time) {
		double sum = 0.0;
		double vectorProduct = -7.7;
		double omega = (2 / Math.PI);
		
		for(Wave wave : waves) {
			vectorProduct = (wave.getWavevector()[0] * this.x) + (wave.getWavevector()[1] * this.y);
			sum += (wave.getAmplitude() * Math.cos(vectorProduct - (omega * time)));
		}
		
		this.h = sum;
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
