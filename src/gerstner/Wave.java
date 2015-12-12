package gerstner;

public class Wave {

	private double amplitude;
	private double[] wavevector;
	
	public Wave() {
		this.amplitude = 0.0;
		this.wavevector = new double[2];
	}
	
	public double getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}
	public double[] getWavevector() {
		return wavevector;
	}
	public void setWavevector(double x, double y) {
		this.wavevector[0] = x;
		this.wavevector[1] = y;
	}
}
