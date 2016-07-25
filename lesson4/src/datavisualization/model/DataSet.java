package datavisualization.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dataset.IDataSet;

public class DataSet implements IDataSet {

	ArrayList<String> dataset = new ArrayList<String>();
	ArrayList<Double> xValue = new ArrayList<Double>();
	ArrayList<Double> yValue = new ArrayList<Double>();
	
	final int maxNum = 2048;

	public boolean addPoint(String data) {
		if (size() < 2048) {
			dataset.add(data);
			
			String[] parts = data.split(" , ");
			String x = parts[0];
			String y = parts[1];
			
			xValue.add(Double.parseDouble(x));
			yValue.add(Double.parseDouble(y));
		}
		return true;
	}

	public void removePoint(int index) {
		dataset.remove(index);
		xValue.remove(index);
		yValue.remove(index);
	}

	public void editPoint(int index, String data) {
		dataset.remove(index);
		dataset.add(index, data);

		xValue.remove(index);
		yValue.remove(index);
		
		String[] parts = data.split(" , ");
		String x = parts[0];
		String y = parts[1];
		
		xValue.add(index, Double.parseDouble(x));
		yValue.add(index, Double.parseDouble(y));
	}

	public int size() {
		return dataset.size();
	}

	public double getMinX() {
		double minX = xValue.get(0);
		for (double x : xValue)
			if (x < minX)
				minX = x;
		return minX;
	}

	public double getMaxX() {
		double maxX = xValue.get(0);
		for (double x : xValue)
			if (x > maxX)
				maxX = x;
		return maxX;
	}

	public double getMinY() {
		double minY = yValue.get(0);
		for (double y : yValue)
			if (y < minY)
				minY = y;
		return minY;
	}

	public double getMaxY() {
		double maxY = yValue.get(0);
		for (double y : yValue)
			if (y > maxY)
				maxY = y;
		return maxY;
	}

	/**
	 * Retrieves the desired coordinate for the value of the data at the given
	 * index location.
	 * 
	 * index must be between 0 and size() dimension must be either 0 (for X) or
	 * 1 (for Y)
	 * 
	 * If index is invalid, throws ArrayIndexOutOfBoundsException If dimension
	 * is invalid (0 or 1 only) throws IllegalArgumentException
	 */
	public double getCoordinate(int index, int dimension)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		if (index < 0 || index > (this.size() - 1))
			throw new ArrayIndexOutOfBoundsException();
		if (dimension == 0)
			return xValue.get(index);
		else if (dimension == 1)
			return yValue.get(index);
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Read file from local file
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean loadDataset(String filepath)
			throws FileNotFoundException {

		Scanner s = new Scanner(new File(filepath));

		int index = 0;
		dataset.clear();
		xValue.clear();
		yValue.clear();
		String data;

		// make sure no more than 2048
		while (s.hasNext() && index < maxNum) {
			data = s.nextLine();
			addPoint(data);
			index++;
		}
		s.close();
		
		return true;
	}

	public boolean saveDataset(String filepath) throws IOException {
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		FileWriter fstream = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter writer = new BufferedWriter(fstream);
		for (String data : dataset) {
			writer.write(data);
			writer.newLine();
		}
		writer.close();
		
		return true;
	}

	public ArrayList<String> getDataset() {
		return dataset;
	}

}
