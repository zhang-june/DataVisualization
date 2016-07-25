package datavisualization.controller;


import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class HorizontalBarController {

	Model model;
	MyBasicPanel panel;
	
	public HorizontalBarController(Model model) {
		this.model = model;
	}

	public boolean drawHorizontalBar(AppGUI mainApplet) {
		panel = mainApplet.getPanel();
		if (model.setGraph(ICommonProperties.horizontalBarGraph)) {
			panel.repaint();
			return true;
		}
		return false;
	}
}
