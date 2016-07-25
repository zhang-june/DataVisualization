package datavisualization.controller;

import java.util.Properties;

import dataset.ICommonProperties;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class TrendLineController {
	Model model;
	MyBasicPanel panel;
	public TrendLineController(Model model) {
		this.model = model;
	}

	public boolean changeTrendline(AppGUI mainApplet) {
		
		Properties pp = model.getProps();
		if (pp.getProperty(ICommonProperties.trendLineVisible).equals("false"))
			pp.setProperty(ICommonProperties.trendLineVisible, "true");
		else pp.setProperty(ICommonProperties.trendLineVisible, "false");
		
		panel = mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
