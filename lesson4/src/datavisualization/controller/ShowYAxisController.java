package datavisualization.controller;

import java.util.Properties;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class ShowYAxisController {
	Model model;
	MyBasicPanel panel;
	
	public ShowYAxisController(Model model) {
		this.model = model;
	}

	public boolean changeYAxis(AppGUI mainApplet) {
		Properties pp = model.getProps();
		
		if (pp.getProperty(ICommonProperties.yAxisLabel).equals("false"))
			pp.setProperty(ICommonProperties.yAxisLabel, "true");
		else 
			pp.setProperty(ICommonProperties.yAxisLabel, "false");
		
		panel = mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
