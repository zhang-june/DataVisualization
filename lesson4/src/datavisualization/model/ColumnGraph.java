package datavisualization.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IGraph;

public class ColumnGraph extends DrawBasicGraph implements IGraph {

	public void setCoordSystem(Graphics2D g2D) {
		g2D.translate(maxX, maxY);
		g2D.drawString("x", maxX - 10, 10);
		g2D.drawString("y", -maxX + 10, -maxY + 10);
		g2D.drawString("0", -maxX + 10, 10);

		g2D.scale(1, -1);
		g2D.drawLine(-width / 2, 0, width / 2, 0);
		g2D.drawLine(-width / 2, -height / 2, -width / 2, height / 2);
	}

	public void setXAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		// mark x-axis
		int count = 1;
		String value;
		while (count < dataset.size() + 1) {
			value = Integer.toString(count);
			g2D.drawString(value, -maxX + tickX + (count - 1) * tickX
					* (tickX / dataset.size()) / 4, 15);
			count++;
		}
	}

	public void setYAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);

		DecimalFormat deformat = new DecimalFormat("#.#");
		// mark the y-axis
		int count = -maxY;
		String value;
		while (count < maxY + 1) {
			if (count != 0) {
				value = deformat.format(-count / scaleValue);
				g2D.drawString(value, -maxX + 5, count);
				g2D.drawLine(-maxX - 5, -count, -maxX + 5, -count);
			}
			count = count + tickY;
		}
	}

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

		int xCount = -maxX + 3 * tickX / 4;
		int yCount = -maxX + tickX;

		for (int n = 0; n < dataset.size(); n++) {
			double x = dataset.getCoordinate(n, 0) * scaleValue;
			double y = dataset.getCoordinate(n, 1) * scaleValue;

			// draw X values
			g2D.setColor(Color.RED);
			if (x > 0)
				g2D.fillRect(xCount, 0, tickX / 4, (int) x);
			else
				g2D.fillRect(xCount, (int) x, tickX / 4, -(int) x);
			xCount += tickX * (tickX / dataset.size()) / 4;

			g2D.setColor(Color.BLUE);
			if (y > 0)
				g2D.fillRect(yCount, 0, tickX / 4, (int) y);
			else
				g2D.fillRect(yCount, (int) y, tickX / 4, -(int) y);
			yCount += tickX * (tickX / dataset.size()) / 4;
		}
	}
}
