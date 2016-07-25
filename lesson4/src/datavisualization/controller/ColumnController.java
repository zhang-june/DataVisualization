package datavisualization.controller;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class ColumnController {

	Model model;
	MyBasicPanel panel;
	
	public ColumnController(Model model) {
		this.model = model;
	}
	public boolean drawColumn(AppGUI mainApplet) {
		panel = mainApplet.getPanel();
		if (model.setGraph(ICommonProperties.column)) {
			panel.repaint();
			return true;
		}
		return false;
	}
}
