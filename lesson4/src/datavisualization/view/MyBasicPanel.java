package datavisualization.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import dataset.IGraph;
import datavisualization.model.Model;

public class MyBasicPanel extends JPanel {

	Model model;

	public MyBasicPanel(Model model) {
		this.model = model;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		IGraph graph = model.getGraph();
		if (graph == null)
			return;
		if (model.getDataSet().size() > 0)
			graph.draw(g, this);
	}
}