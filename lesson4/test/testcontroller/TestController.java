package testcontroller;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;

import org.junit.Test;

import dataset.ICommonProperties;
import datavisualization.controller.AddPointController;
import datavisualization.controller.EditPointController;
import datavisualization.controller.RemoveSelectedController;
import datavisualization.controller.ShowGridController;
import datavisualization.controller.ShowXAxisController;
import datavisualization.controller.ShowYAxisController;
import datavisualization.controller.TrendLineController;
import datavisualization.model.Model;
import datavisualization.view.AppGUI;
import datavisualization.view.MyBasicPanel;


public class TestController {

	@Test
	public void test() {
		AppGUI main = new AppGUI ();
		main.setVisible(true);

		Model model = main.getModel();
		MyBasicPanel panel = main.getPanel();
		
		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		assertTrue(model.getDataSet().addPoint("-15 , -23"));
		assertTrue(model.getDataSet().addPoint("24 , -50"));
		assertTrue(model.getDataSet().addPoint("-13 , 18"));
		int size = model.getDataSet().size();
		assertTrue( size > 0);

		ShowXAxisController showXAxis = new ShowXAxisController(model);
		ShowYAxisController showYAxis = new ShowYAxisController(model); 
		ShowGridController showGrid = new ShowGridController(model);
		assertTrue(showYAxis.changeYAxis(main));
		assertTrue(showXAxis.changeXAxis(main));
		assertTrue(showGrid.changeGrid(main));

		TrendLineController trendLine = new TrendLineController(model);
		assertTrue(trendLine.changeTrendline(main));

		
		model.setGraph(ICommonProperties.cartesian);
		model.getGraph().draw(g, panel);
		
		model.setGraph(ICommonProperties.column);
		model.getGraph().draw(g, panel);
		
		model.setGraph(ICommonProperties.horizontalBarGraph);
		model.getGraph().draw(g, panel);
		 
		assertTrue(showYAxis.changeYAxis(main));
		assertTrue(showXAxis.changeXAxis(main));
		assertTrue(showGrid.changeGrid(main));
		assertTrue(trendLine.changeTrendline(main));
		
		model.setGraph(ICommonProperties.cartesian);
		model.getGraph().draw(g, panel);
		
		model.setGraph(ICommonProperties.column);
		model.getGraph().draw(g, panel);
		
		model.setGraph(ICommonProperties.horizontalBarGraph);
		model.getGraph().draw(g, panel);
		
		JTextField x1 = main.getXValue();
		x1.setText("2");
		JTextField y1 = main.getYValue();
		y1.setText("3");

		AddPointController apc = new AddPointController(model);
		assertTrue(apc.addPoint(main));

		int sizenow = model.getDataSet().size();
		assertEquals(sizenow, size + 1);

		main.getJList().setSelectedIndex(0);
		EditPointController epc = new EditPointController(model);
		assertTrue(epc.setEditable(main));
		
		x1.setText("4");
		y1.setText("5");
		assertTrue(epc.editPoint(main));
		
		RemoveSelectedController rsc = new RemoveSelectedController(model);
		rsc.removePoint(main);
		
		try {
			assertTrue(model.getDataSet().loadDataset("sample.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		try {
			assertTrue(model.getDataSet().saveDataset("sample.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
