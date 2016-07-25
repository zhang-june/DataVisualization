package datavisualization.controller;

import java.util.Properties;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class ShowGridController {
	Model model;
	MyBasicPanel panel;

	public ShowGridController(Model model) {
		this.model = model;
	}

	public boolean changeGrid(AppGUI mainApplet) {
		Properties pp = model.getProps();
		if (pp.getProperty(ICommonProperties.horizontalLines).equals("false"))
			pp.setProperty(ICommonProperties.horizontalLines, "true");
		else
			pp.setProperty(ICommonProperties.horizontalLines, "false");

		panel = mainApplet.getPanel();
		panel.repaint();

		return true;
	}
}
