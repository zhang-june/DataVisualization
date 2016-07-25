package datavisualization.model;

import java.util.Properties;

import dataset.IGraph;

public class Model {
	DataSet dataSet;
	IGraph graph;
	Properties props;
	
	public Model() {
		this.dataSet = new DataSet();
		ApplicationProperties appProp = ApplicationProperties.instance();
		props = appProp.getProps();
	}

	public DataSet getDataSet() {
		return dataSet;
	}


	public boolean setGraph(String graphType) {
		IGraph gg = null;
		try {
			Class clazz = Class.forName(props.getProperty(graphType));
			gg = (IGraph) clazz.newInstance();
			gg.setDataSet(this.getDataSet());
			gg.setProperties(this.getProps());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		graph = gg;
		
		return true;
	}
	public IGraph getGraph() {
		return graph;
	}

	public Properties getProps() {
		return props;
	}

}
