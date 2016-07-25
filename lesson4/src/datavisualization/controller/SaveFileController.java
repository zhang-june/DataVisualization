package datavisualization.controller;

import java.io.IOException;

import javax.swing.JFileChooser;

import datavisualization.model.Model;
import datavisualization.view.AppGUI;

public class SaveFileController {

	Model model;

	public SaveFileController(Model model) {
		this.model = model;
	}

	public boolean saveFile(AppGUI main) {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(main) != JFileChooser.APPROVE_OPTION) {
			return false;
		}
		
		String FILENAME; 
		FILENAME = fc.getSelectedFile().toString(); 
		
		try {
			model.getDataSet().saveDataset(FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}