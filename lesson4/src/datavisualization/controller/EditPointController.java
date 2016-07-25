package datavisualization.controller;

import javax.swing.DefaultListModel;

import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class EditPointController {
	Model model;
	private int idx;
	DefaultListModel list;
	MyBasicPanel panel;

	public EditPointController(Model model) {
		this.model = model;
	}

	public boolean setEditable(AppGUI mainApplet) {
		idx = mainApplet.getJList().getSelectedIndex();
		list = (DefaultListModel) mainApplet.getJList().getModel();

		if (idx < 0)
			return false;

		String data = (String) list.getElementAt(idx);
		String[] parts = data.split(" , ");
		String x = parts[0];
		String y = parts[1];

		mainApplet.getXValue().setText(x);
		mainApplet.getYValue().setText(y);
 
		return true;
	}

	public boolean editPoint(AppGUI mainApplet) {
		idx = mainApplet.getJList().getSelectedIndex();
		if (idx < 0)
			return false;
		
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();
		if (pointXValue.length() <= 0 || pointYValue.length() <= 0)
			return false;
		
		String point = pointXValue + " , " + pointYValue;

		list = (DefaultListModel) mainApplet.getJList().getModel();
		list.set(idx, point);
		model.getDataSet().editPoint(idx, point);

		panel = mainApplet.getPanel();
		panel.repaint();

		return true;
	}
}
