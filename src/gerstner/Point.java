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
	
	public void evaluateGerstnerWaveMovement(List<Wave> waves, double time) {
		double sum = 0.0;
		double vectorProduct;
		double wavenumber;
		double angularFrequency;
		double basicFrequency;
		double quantizedFrequency;
		
		for(Wave wave : waves) {
			
			// calculating wavenumber based on wavevector
			wavenumber = Math.sqrt((Math.pow(wave.getWavevector()[0], 2) + Math.pow(wave.getWavevector()[1], 2)));
			
			// calculating angular frequency considering the dispersion relation
			// assuming that it's a deep sea, so h -> infinity
			// then tanh(kh) -> 1
			angularFrequency = Math.sqrt(Parameters.GRAVITY * wavenumber);
			
			// calculating basic frequency based on particular moment in time
			basicFrequency = ((2 * Math.PI) / time);
			
			// quantizing the angular frequency to allow repeating sequences to occur
			quantizedFrequency = ((int) angularFrequency / basicFrequency) * basicFrequency;
			
			// calculating vector product and summing the waves
			vectorProduct = (wave.getWavevector()[0] * this.x) + (wave.getWavevector()[1] * this.y);
			sum += (wave.getAmplitude() * Math.cos(vectorProduct - (quantizedFrequency * time)));
		}
		
		// updating the height of current point
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
