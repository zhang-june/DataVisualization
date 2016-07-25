package datavisualization.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import dataset.IDataSet;

public class TrendLine {

	// linear regression formula
	String equation;

	// two points to draw linear regression graph
	double startPointX;
	double startPointY;
	double endPointX;
	double endPointY;
	
	public void drawTrendline(Graphics2D g2D, IDataSet dataset) {
		g2D.setColor(Color.BLACK);
		
		if (linearRegression(dataset)) {
			g2D.scale(1, -1);
			g2D.drawString(equation, -170, -170);
			
			g2D.scale(1, -1);
			g2D.drawLine((int) startPointX, (int) startPointY, (int) endPointX, (int) endPointY);
		}
	}
	
	/**
	 * linear regression function to estimate trend line
	 */
	public boolean linearRegression(IDataSet dataset) {
		int MAXN = 1000;
		int n = 0;
		double[] x = new double[MAXN];
		double[] y = new double[MAXN];

		
		if (dataset.size() > 0) {
			// first pass: read in data, compute xbar and ybar
			double sumx = 0, sumy = 0;
			for (n = 0; n < dataset.size(); n++) {
				x[n] = dataset.getCoordinate(n, 0);
				y[n] = dataset.getCoordinate(n, 1);
				sumx += x[n];
				sumy += y[n];
			} 
			double xbar = sumx / n;
			double ybar = sumy / n;

			// second pass: compute summary statistics
			double xxbar = 0, xybar = 0;
			for (int i = 0; i < n; i++) {
				xxbar += (x[i] - xbar) * (x[i] - xbar);
				xybar += (x[i] - xbar) * (y[i] - ybar);
			}
			double beta1 = xybar / xxbar;
			double beta0 = ybar - beta1 * xbar;

			beta1 = Double.parseDouble(new DecimalFormat("##.##").format(beta1));
			beta0 = Double.parseDouble(new DecimalFormat("##.##").format(beta0));

			equation = "y = " + beta1 + " * x + " + beta0;

			startPointX = -200;
			startPointY = beta1 * (-200) + beta0;
			endPointX = 200;
			endPointY = beta1 * 200 + beta0;
			
			return true;
		}
		return false;
	}
}
