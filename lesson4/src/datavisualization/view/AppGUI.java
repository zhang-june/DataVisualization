package datavisualization.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datavisualization.controller.AddPointController;
import datavisualization.controller.CartesianController;
import datavisualization.controller.ColumnController;
import datavisualization.controller.EditPointController;
import datavisualization.controller.HorizontalBarController;
import datavisualization.controller.LoadFileController;
import datavisualization.controller.MultibleLinesController;
import datavisualization.controller.RemoveSelectedController;
import datavisualization.controller.SaveFileController;
import datavisualization.controller.ShowGridController;
import datavisualization.controller.ShowXAxisController;
import datavisualization.controller.ShowYAxisController;
import datavisualization.controller.TrendLineController;
import datavisualization.model.Model;

public class AppGUI extends JFrame {
	Model model;

	private MyBasicPanel panel;

	private JButton btn_load_data;
	private JButton btn_save_data;
	private JButton btn_delete_data;
	private JButton btn_edit_data;
	private JLabel label_x;
	private JTextField text_xValue;
	private JLabel label_y;
	private JTextField text_yValue;
	private JButton btn_add_data;
	private JCheckBox chckbx_trend_line;
	private JCheckBox chckbx_grid;
	private JCheckBox chckbxShowXaxisLable;
	private JComboBox comboBox;
	private String[] graphType = { "Cartesian Plot", "Column Plot",
			"HorizonalBar Plot", "MultibleLine Plot" };

	JScrollPane scrollPane;
	JList list;

	public AppGUI() {

		model = new Model();
		panel = new MyBasicPanel(model);

		scrollPane = new JScrollPane();
		list = new JList();
		list.setModel(new DefaultListModel());
		scrollPane.setViewportView(list);
		list.setFixedCellHeight(30);
		list.setFixedCellWidth(80);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		btn_load_data = new JButton("Load Data");
		btn_load_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new LoadFileController(AppGUI.this.model)
						.loadFile(AppGUI.this);
			}
		});

		btn_save_data = new JButton("Save Data");
		btn_save_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveFileController(AppGUI.this.model)
						.saveFile(AppGUI.this);
			}
		});

		btn_delete_data = new JButton("Delete Selected Data");
		btn_delete_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveSelectedController(AppGUI.this.model)
						.removePoint(AppGUI.this);
			}
		});

		btn_add_data = new JButton("Add Data");
		btn_add_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPointController(AppGUI.this.model)
						.addPoint(AppGUI.this);

			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				} else {
					new EditPointController(AppGUI.this.model)
							.setEditable(AppGUI.this);
				}
			}
		});

		btn_edit_data = new JButton("Edit Selected Data");
		btn_edit_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditPointController(AppGUI.this.model)
						.editPoint(AppGUI.this);
			}
		});

		label_x = new JLabel("x:");

		text_xValue = new JTextField();
		text_xValue.setColumns(10);

		label_y = new JLabel("y:");

		text_yValue = new JTextField();
		text_yValue.setColumns(10);

		chckbx_trend_line = new JCheckBox("Show Trend Line");
		chckbx_trend_line.setEnabled(false);
		chckbx_trend_line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TrendLineController(AppGUI.this.model)
						.changeTrendline(AppGUI.this);
			}

		});

		chckbx_grid = new JCheckBox("Show Back Lines");
		chckbx_grid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowGridController(AppGUI.this.model)
						.changeGrid(AppGUI.this);
			}

		});

		chckbxShowXaxisLable = new JCheckBox("Show  xLable");
		chckbxShowXaxisLable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowXAxisController(AppGUI.this.model)
						.changeXAxis(AppGUI.this);
			}
		});

		JCheckBox chckbxShowYaxisLable = new JCheckBox("Show yLable");
		chckbxShowYaxisLable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowYAxisController(AppGUI.this.model)
						.changeYAxis(AppGUI.this);
			}
		});
		
		comboBox = new JComboBox(graphType);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = comboBox.getSelectedIndex();
				switch (pos) {
				case 0:
					chckbx_trend_line.setEnabled(true);
					new CartesianController(AppGUI.this.model)
							.drawCartesian(AppGUI.this);
					break;
				case 1:
					chckbx_trend_line.setEnabled(false);
					new ColumnController(AppGUI.this.model)
							.drawColumn(AppGUI.this);
					break;
				case 2:
					chckbx_trend_line.setEnabled(false);
					new HorizontalBarController(AppGUI.this.model)
							.drawHorizontalBar(AppGUI.this);
					break;
				case 3:
					chckbx_trend_line.setEnabled(false);
					new MultibleLinesController(AppGUI.this.model)
							.drawMultibleLines(AppGUI.this);
					break;
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_x, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text_xValue, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_y, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text_yValue, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(130)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbx_trend_line)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxShowXaxisLable, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxShowYaxisLable)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbx_grid, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_add_data, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_edit_data, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_delete_data, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_load_data)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_save_data)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_x)
						.addComponent(text_xValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbx_trend_line)
						.addComponent(chckbxShowXaxisLable)
						.addComponent(chckbxShowYaxisLable, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbx_grid))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_y)
						.addComponent(text_yValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_add_data)
						.addComponent(btn_edit_data)
						.addComponent(btn_delete_data)
						.addComponent(btn_load_data)
						.addComponent(btn_save_data))
					.addGap(19))
		);

		getContentPane().setLayout(groupLayout);
	}

	public JList getJList() {
		return list;
	}

	public JTextField getXValue() {
		return text_xValue;
	}

	public JTextField getYValue() {
		return text_yValue;
	}

	public MyBasicPanel getPanel() {
		return panel;
	}

	public Model getModel() {	
		return model;
	}
}
