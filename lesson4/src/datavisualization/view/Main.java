package datavisualization.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	AppGUI app;
	
	public AppGUI getApp() {
		return app;
	}

	public static void main(String[] args) {
		AppGUI app = new AppGUI ();
		app.setSize(900, 600);
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		app.setVisible(true);
	}
	
	
}
