package datavisualization.controller;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class CartesianController {

	Model model;
	MyBasicPanel panel;

	public CartesianController(Model model) {
		this.model = model;
	}

	public boolean drawCartesian(AppGUI mainApplet) {
		panel = mainApplet.getPanel();
		if (model.setGraph(ICommonProperties.cartesian)) {
			panel.repaint();
			return true;
		}
		return false;
	}
}
