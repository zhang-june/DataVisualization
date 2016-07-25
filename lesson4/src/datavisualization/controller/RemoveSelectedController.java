package datavisualization.controller;

import javax.swing.DefaultListModel;

import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;

public class RemoveSelectedController {
	Model model;
	MyBasicPanel panel;
	public RemoveSelectedController(Model model) {
		this.model = model;
	}

	/**
	 * Remove selected data points
	 * @param mainApplet
	 * @return
	 */
	public boolean removePoint(AppGUI mainApplet) {
		int[] row = mainApplet.getJList().getSelectedIndices();
		if (row.length == 0) {
			return false;
		}

		DefaultListModel list = (DefaultListModel) mainApplet.getJList().getModel();
		for (int idx = row.length - 1; idx >= 0; idx--) {
			list.remove(row[idx]);
			model.getDataSet().removePoint(row[idx]);
		}

		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");
		
		panel = mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
