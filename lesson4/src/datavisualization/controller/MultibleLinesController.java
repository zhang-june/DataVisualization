package datavisualization.controller;


import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class MultibleLinesController {

	Model model;
	MyBasicPanel panel;
	
	public MultibleLinesController(Model model) {
		this.model = model;
	}

	public boolean drawMultibleLines(AppGUI mainApplet) {
		panel = mainApplet.getPanel();
		if (model.setGraph(ICommonProperties.multipleLines)) {
			panel.repaint();
			return true;
		}
		return false;
	}
}
