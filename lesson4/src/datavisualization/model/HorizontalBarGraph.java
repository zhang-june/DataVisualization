package datavisualization.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.swing.JPanel;
import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class HorizontalBarGraph implements IGraph {
	IDataSet dataset;
	Properties props;
	
	double maxX;
	double minX;
	double maxY;
	double minY;
	int originX;
	int originY;
	double width;
	double height;
	double absmaxX;
	Graphics2D g;
	
	public void setCoordSystem(Graphics2D g2D) {
		g.setColor(Color.black);
		
		maxX = Math.ceil(dataset.getMaxX());
		minX = Math.floor(dataset.getMinX());
		maxY = Math.ceil(dataset.getMaxY());
		minY = Math.floor(dataset.getMinY());
		
		absmaxX = 2 * Math.max(Math.max(Math.abs(maxX), Math.abs(maxY)), Math.max(Math.abs(minX), Math.abs(minY)));
		
		originX = (int) (width / 2) + 15;
		originY = 15;
		
		g2D.translate(originX,  originY);
		
		g2D.drawLine((int) -width / 2, (int) height - 20, (int) width / 2, (int) height - 20);
		g2D.drawLine(0, 20, 0, (int) height - 20);
		g2D.drawString("x Axis", -10, (int) height + 6);
		g2D.drawString("y Axis", -10, 5);
	}
		
	public void setXAxis(Graphics2D g2D) {
	
		g.setColor(Color.black);
		double tickX;
		int xLable;
		int tickNum = 10;
		xLable = 0;
		tickX = (int) (width / tickNum);
		DecimalFormat deformat = new DecimalFormat("#.#");
		
		g2D.drawLine(xLable, (int) height - 20, xLable, (int) height - 15);
		g2D.drawString(deformat.format((double)xLable*absmaxX*2/width), xLable - 3, (int) height - 4);
		
		xLable += tickX;
		while (xLable <= width / 2) {
			g2D.drawLine(xLable, (int) height - 20, xLable, (int) height - 15);
			g2D.drawString(deformat.format((double)xLable*absmaxX*2/width), xLable - 10, (int) height - 4);
			
			xLable += tickX;
		}
		
		tickX = (int) (width / tickNum);
		xLable = (int) -tickX;
		while (xLable >= (-width / 2)) {
			g2D.drawLine(xLable, (int) height - 20, xLable, (int) height - 15);
			g2D.drawString(deformat.format((double)xLable*absmaxX*2/width), xLable - 5, (int) height - 4);
			
			xLable -= tickX;
		}

	}
	
	public void setYAxis(Graphics2D g2D) {
		
		g2D.setColor(Color.black);
		g2D.drawLine((int) - width / 2, 20, (int) - width / 2, (int) height - 20);
		
		double tickY = Math.ceil((height - 40) / dataset.size());
		double count = height - 20 - Math.ceil(tickY  / 2);
		
		for (int n = 0; n < dataset.size(); n++) {
			g2D.drawLine((int) - width / 2, (int) count, (int) - width / 2 + 6, (int) count);
			g2D.drawString(Integer.toString(n + 1), (int) - width / 2 + 8, (int) count + 3);
			count -= tickY;
		}
	}
	
	public void setGrid(Graphics2D g2D) {
		g.setColor(Color.black);
		
		double tickX;
		int xLable;
		int tickNum = 10;
		
		xLable = 0;
		tickX = (int) (width / tickNum);

		while (xLable <= width / 2) {
			g2D.drawLine(xLable, (int) height - 20, xLable, 20);
			xLable += tickX;
		}
		
		tickX = (int) (width / tickNum);
		xLable = (int) -tickX;
		while (xLable >= (-width / 2)) {
			g2D.drawLine(xLable, (int) height - 20, xLable, 20);
			xLable -= tickX;
		}
	}
		
	public void draw(Graphics gg, JPanel panel) {
//		panel.setSize(410, 410);
		Dimension panelSize = panel.getSize();
		width = panelSize.width - 30;
		height = panelSize.height - 30;
		g = (Graphics2D) gg;
		setCoordSystem(g);
		
		if (props.getProperty(ICommonProperties.horizontalLines) == null) {
			props.setProperty(ICommonProperties.horizontalLines, "true");
		}

		if (props.getProperty(ICommonProperties.xAxisLabel) == null) {
			props.setProperty(ICommonProperties.xAxisLabel, "true");
		}

		if (props.getProperty(ICommonProperties.yAxisLabel) == null) {
			props.setProperty(ICommonProperties.yAxisLabel, "true");
		}
		
		if (props.getProperty(ICommonProperties.horizontalLines).equals("true")) {
			setGrid(g);
		}

		if (props.getProperty(ICommonProperties.xAxisLabel).equals("true")) {
			setXAxis(g);
		}

		if (props.getProperty(ICommonProperties.yAxisLabel).equals("true")) {
			setYAxis(g);
		}
		
		double scaleValue = width / (absmaxX * 2);
		double tickY = Math.ceil((height - 40) / dataset.size());
		double count = height - 20 - Math.ceil(tickY * 3 / 4);
		int rectWidth = (int) Math.ceil(tickY / 2);
		
		for (int n = 0; n < dataset.size(); n++) {
			double x = dataset.getCoordinate(n, 0) * scaleValue;
			double y = dataset.getCoordinate(n, 1) * scaleValue;

			if (x > 0 && y > 0) {
				g.setColor(Color.RED);
				g.fillRect(0, (int) count, (int) x, rectWidth);
				g.setColor(Color.BLUE);
				g.fillRect((int) x, (int) count, (int) y, rectWidth);
			} else if (x > 0 && y < 0) {
				g.setColor(Color.RED);
				g.fillRect(0, (int) count, (int) x, rectWidth);
				g.setColor(Color.BLUE);
				g.fillRect((int) y, (int) count, (int) -y, rectWidth);
			} else if (x < 0 && y > 0) {
				g.setColor(Color.RED);
				g.fillRect((int) x, (int) count, (int) -x, rectWidth);
				g.setColor(Color.BLUE);
				g.fillRect(0, (int) count, (int) y, rectWidth);
			} else {
				g.setColor(Color.RED);
				g.fillRect((int) x, (int) count, (int) -x, rectWidth);
				g.setColor(Color.BLUE);
				g.fillRect((int) (x + y), (int) count, (int) -y, rectWidth);
			}
			count -= tickY;
		}
	}

	public void setDataSet(IDataSet ds) {
		this.dataset = ds;
	}

	public void setProperties(Properties p) {
		this.props = p;
	}
} 
