package datavisualization.controller;

import javax.swing.DefaultListModel;

import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class AddPointController {
	Model model;
	MyBasicPanel panel;
	
	public AddPointController(Model model) {
		this.model = model;
	}

	/**
	 * Get point from user, add to JLists
	 * 
	 * @param mainApplet
	 * @return
	 */
	public boolean addPoint(AppGUI mainApplet) {
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();
		String data = pointXValue + " , " + pointYValue;
		if (pointXValue.length() <= 0 || pointYValue.length() <= 0)
			return false;
		
		
		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");

		if (pointXValue.length() == 0 || pointYValue.length() == 0) {
			return false;
		}

		DefaultListModel list;
		list = (DefaultListModel) mainApplet.getJList().getModel();
		int idx = list.getSize();
		list.add(idx, data);
		model.getDataSet().addPoint(data);
		
		panel = mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
