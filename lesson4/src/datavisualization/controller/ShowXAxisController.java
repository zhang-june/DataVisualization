package datavisualization.controller;

import java.util.Properties;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class ShowXAxisController {
	Model model;
	MyBasicPanel panel;
	public ShowXAxisController(Model model) {
		this.model = model;
	}

	public boolean changeXAxis(AppGUI mainApplet) {
		Properties pp = model.getProps();
		
		if (pp.getProperty(ICommonProperties.xAxisLabel).equals("false")) 
			pp.setProperty(ICommonProperties.xAxisLabel, "true");
		else 
			pp.setProperty(ICommonProperties.xAxisLabel, "false");
		
		panel = mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
