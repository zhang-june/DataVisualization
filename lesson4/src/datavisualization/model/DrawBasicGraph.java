package datavisualization.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class DrawBasicGraph implements IGraph {
	IDataSet dataset;

	Properties props;

	/** The instantiating applet's width. */
	public int width;

	/** The instantiating applet's height. */
	public int height;

	/** x-axis tick marks. */
	public int tickX;

	/** y-axis tick marks. */
	public int tickY;

	/** Maximum x-axis value. */
	public int maxX;

	/** Maximum y-axis value. */
	public int maxY;

	/**
	 * scale Value
	 */
	protected double scaleValue;

	Graphics2D g2D;

	/**
	 * Helper method which sets the scale on each axis..
	 * 
	 * @param divX
	 *            x-axis scaling parameter.
	 * @param divY
	 *            y-axis scaling parameter.
	 */
	public void setScale(int divX, int divY) {
		tickX = width / divX;
		tickY = height / divY;
		maxX = width / 2;
		maxY = height / 2;
	}

	/**
	 * Helper method which draws the x and y axis..
	 * 
	 * @param g2D
	 *            the graphic context used.
	 * 
	 */
	public void setCoordSystem(Graphics2D g2D) {
		g2D.translate(maxX, maxY);

		g2D.drawString("x", maxX - 10, 10);
		g2D.drawString("y", -10, -maxY + 10);
		g2D.drawString("0", -15, 15);

		g2D.scale(1, -1);
		g2D.drawLine(-width / 2, 0, width / 2, 0);
		g2D.drawLine(0, -height / 2, 0, height / 2);
	}

	/**
	 * Helper method to set Background grid
	 * 
	 * @param g2D
	 */
	public void setBackGrid(Graphics2D g2D) {

		int count = -maxY;
		while (count < maxY + 1) {
			g2D.setColor(Color.GRAY);
			g2D.drawLine(-maxX, count, maxX, count);
			// g2D.drawLine(-maxX, -count, maxX, -count);
			count = count + tickY;
		}
	}

	public void draw(Graphics g, JPanel panel) {

		panel.setSize(400, 400);
		Dimension panelSize = panel.getSize();
		width = panelSize.width;
		height = panelSize.height;
		double rangeX = Math.max(Math.abs(dataset.getMaxX()),
				Math.abs(dataset.getMinX()));
		double rangeY = Math.max(Math.abs(dataset.getMaxY()),
				Math.abs(dataset.getMinY()));
		double maxRange = Math.max(rangeX, rangeY);
		scaleValue = ((width - 30) / 2) / maxRange;

		// width = this.getSize().width;
		// height = this.getSize().height;

		// Hard coding the major subdivisions on each axis.
		// setRange();
		// Hard coding the major subdivisions on each axis.
		this.g2D = (Graphics2D) g;
		setScale(10, 10);
		setCoordSystem(g2D);

		if (props.getProperty(ICommonProperties.horizontalLines).equals("true")) {
			setBackGrid(g2D);
		}
	}

	public void setDataSet(IDataSet ds) {
		this.dataset = ds;
	}

	public void setProperties(Properties p) {
		this.props = p;
	}
}
