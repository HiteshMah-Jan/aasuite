package util;

public class Formula {
	public static double[] forecast(double[] useX, double[] x, double[] y) {
		Regression reg = Regression.getRegressionDetail(useX, x, y);
		return reg.forecast;
	}

	public static double forecast(double useX, double[] x, double[] y) {
		double[] d = {useX};
        d = forecast(d, x, y);
        return d[0];
	}

	public static void main(String[] args) {
		double[] x = {1998, 1999, 2001, 2002, 2003, 2010, 2011, 2012, 2013, 2014};
		double[] xvals = {1998, 1999, 2001, 2002, 2003};
		double[] yvals = {50, 52, 52, 53, 53};
		forecast(x, xvals, yvals);
	}

	public static class Regression {
		public double interceptCoefficient;
		public double xCoefficient;
		public double r2;
		public double residualDF;
		public double rss;
		public double regressionMS;
		public double xStandardError;
		public double interceptStandardError;
		public double totalSS;
		public double sse;
		public double ymean;
		public double xmean;
		public double[] forecast;
		public double[] residuals;
		public double otherTotalSS;
		public double interceptTStat;
		public double xTStat;
		public double residualMS;
		public double regressionF;


		public static Regression getRegressionDetail(double[] useX, double[] x, double[] y) {
			Regression reg = new Regression();
			int n = x.length;

	        // first pass: read in data, compute xbar and ybar
	        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
	        for (int i=0; i<n; i++) {
	            sumx  += x[i];
	            sumx2 += x[i] * x[i];
	            sumy  += y[i];
	        }
	        reg.xmean = sumx / n;
	        reg.ymean = sumy / n;

	        // second pass: compute summary statistics
	        double xxbar = 0.0;
	        for (int i = 0; i < n; i++) {
	            xxbar += (x[i] - reg.xmean) * (x[i] - reg.xmean);
	            reg.totalSS += (y[i] - reg.ymean) * (y[i] - reg.ymean);
	            reg.otherTotalSS += (x[i] - reg.xmean) * (y[i] - reg.ymean);
	        }
	        reg.xCoefficient = reg.otherTotalSS / xxbar;
	        reg.interceptCoefficient = reg.ymean - reg.xCoefficient * reg.xmean;

	        if (useX!=null && useX.length>0) {
	        	reg.forecast = new double[useX.length];
				reg.residuals = new double[useX.length];
		        for (int i=0; i<reg.forecast.length; i++) {
		        	double xtmp = useX[i];
		            double tmp = reg.xCoefficient * xtmp + reg.interceptCoefficient;
		            reg.forecast[i] = tmp;
		            if (y.length>i) {
			            reg.residuals[i] = y[i]-tmp;
		            }
		            System.out.println(xtmp+"="+reg.forecast[i]+":"+reg.residuals[i]);
		        }
	        }

	        // analyze results
	        reg.residualDF = n - 2;
	        reg.rss = 0.0;      // residual sum of squares
	        reg.regressionMS = 0.0;      // regression sum of squares
	        for (int i = 0; i < n; i++) {
	            double fit = reg.xCoefficient*x[i] + reg.interceptCoefficient;
	            reg.rss += (fit - y[i]) * (fit - y[i]);
	            reg.regressionMS += (fit - reg.ymean) * (fit - reg.ymean);
	        }
	        reg.r2 = reg.regressionMS / reg.totalSS;
	        reg.residualMS  = reg.rss / reg.residualDF;
	        double svar1 = reg.residualMS / xxbar;
	        double svar0 = reg.residualMS/n + reg.xmean*reg.xmean*svar1;
	        reg.xStandardError = Math.sqrt(svar1);
	        reg.interceptStandardError = Math.sqrt(svar0);
	        reg.interceptTStat = reg.interceptCoefficient/reg.interceptStandardError;
	        reg.xTStat = reg.xCoefficient/reg.xStandardError;
	        reg.regressionF = reg.regressionMS/reg.residualMS;
	        System.out.println("R^2 = " + reg.r2);
	        System.out.println("std error of M = " + reg.xStandardError);
	        System.out.println("std error of B = " + reg.interceptStandardError);
	        svar0 = reg.residualMS * sumx2 / (n * xxbar);
	        System.out.println("std error of M = " + reg.xStandardError);

	        System.out.println("SSTO = " + reg.totalSS);
	        System.out.println("SSE  = " + reg.rss);
	        System.out.println("SSR  = " + reg.regressionMS);
	        return reg;
		}
	}
}
