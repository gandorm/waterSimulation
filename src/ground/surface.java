package ground;

import java.util.LinkedList;
import java.util.Random;

import basics.cell;

public class surface {
	static float[][] listaR = new float[32][32];
	static float[][] listaI = new float[32][32];

	public static void main(String[] args) {
		setInitPos();
		for (int i = 0; i<5; i++){	
			
			showSurface();
	}
		}
	
	public static void setInitPos(){
		for(int i = listaR.length-1;  i > 1; i--){
			for(int j = listaR[i].length-1; j > 1; j--){				
				Random generator = new Random();				 
				listaR[i][j] = generator.nextFloat();
				listaI[i][j] = generator.nextFloat();				
			}			
			
		}
	}
	
	public static void showSurface(){		
		for(int i = listaR.length-1;  i > 1; i--){
			for(int j = listaR[i].length-1; j > 1; j--){
				Fft.complexToComplex(1, 32, listaR[i], listaI[i]);
				System.out.print(getColor(Fft.computeSampleValue(listaR[i][j], listaI[i][j])));				
			}
			System.out.println("");
			
		}
	}
	
	public static int getColor(float sth){
		if (sth < 0.1){
			return 1;
		}
		if (sth < 0.2){
			return 2;
		}
		if (sth < 0.3){
			return 2;
		}
		if (sth < 0.4){
			return 3;
		}
		if (sth < 0.5){
			return 4;
		}
		if (sth < 1){
			return 5;
		}
		return 0;
	}
	
	static class Fft {
		
		private static float computeSampleValue(float real, float imag) {
			float sv = 0.0f;
			float v;
			v = real;			
			float si = v;
			if (-si>sv) sv = -si;
			else if (si>sv) sv = si;			
			v = imag;			
			si = v;
			if (-si>sv) sv = -si;
			else if (si>sv) sv = si;			
			return sv;
		}
		
		public static void complexToComplex(int sign, int n, float ar[], float ai[]) {
			float scale = (float)Math.sqrt(1.0f/n);
			int i,j;
			for (i=j=0; i<n; ++i) {
				if (j>=i) {
					float tempr = ar[j]*scale;
					float tempi = ai[j]*scale;
					ar[j] = ar[i]*scale;
					ai[j] = ai[i]*scale;
					ar[i] = tempr;
					ai[i] = tempi;
				}
				int m = n/2;
				while (m>=1 && j>=m) {
					j -= m;
					m /= 2;
				}
				j += m;
			}
			int mmax,istep;
			for (mmax=1,istep=2*mmax; mmax<n; mmax=istep,istep=2*mmax) {
				float delta = (float)sign*3.141592654f/(float)mmax;
				for (int m=0; m<mmax; ++m) {
					float w = (float)m*delta;
					float wr = (float)Math.cos(w);
					float wi = (float)Math.sin(w);
					for (i=m; i<n; i+=istep) {
						j = i+mmax;
						float tr = wr*ar[j]-wi*ai[j];
						float ti = wr*ai[j]+wi*ar[j];
						ar[j] = ar[i]-tr;
						ai[j] = ai[i]-ti;
						ar[i] += tr;
						ai[i] += ti;
					}
				}
				mmax = istep;
			}
		}
	}

}
