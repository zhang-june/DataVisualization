package datavisualization.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IGraph;

public class CartesianGraph extends DrawBasicGraph implements IGraph {

	@Override
	public void draw(Graphics g, JPanel panel) {

		super.draw(g, panel);
		g2D = (Graphics2D) g;

		if (props.getProperty(ICommonProperties.xAxisLabel).equals("true")) {
			g2D.scale(1, -1);
			setXAxis(g2D);
			g2D.scale(1, -1);
		}

		if (props.getProperty(ICommonProperties.yAxisLabel).equals("true")) {
			g2D.scale(1, -1);
			setYAxis(g2D);
			g2D.scale(1, -1);
		}

		if (dataset.size() > 0)
			g2D.setColor(Color.RED);
		for (int n = 0; n < dataset.size(); n++) {
			g2D.fillRect((int) (dataset.getCoordinate(n, 0) * scaleValue),
					(int) (dataset.getCoordinate(n, 1) * scaleValue), 4, 4);
		}

		if (props.getProperty(ICommonProperties.trendLineVisible)
				.equals("true")) {
			new TrendLine().drawTrendline(g2D, dataset);
		}
	}

	/**
	 * Helper method to set Axis
	 * 
	 * @param g2D
	 */
	public void setXAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);

		DecimalFormat deformat = new DecimalFormat("#.#");
		// Draw Cartesian for default or cartesian coordinate
		// mark the x-axis
		double count = -maxX;
		String value;
		while (count < maxX + 1) {
			if (count != 0) {
				value = deformat.format(count / scaleValue);
				g2D.drawString(value, (int) count - 5, 15);
				g2D.drawLine((int) (-count), -5, (int) (-count), 5);
			}
			count = count + tickX;
		}
	}

	public void setYAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);

		DecimalFormat deformat = new DecimalFormat("#.#");
		// mark the y-axis
		double count = -maxY;
		String value;
		while (count < maxY + 1) {
			if (count != 0) {
				value = deformat.format(-count / scaleValue);
				g2D.drawString(value, -40, (int) count + 5);
				g2D.drawLine(-5, (int) (-count), 5, (int) (-count));
			}
			count = count + tickY;
		}
	}

}
