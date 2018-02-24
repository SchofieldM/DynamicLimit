package main.com.dynamiclimit.database;

import java.util.ArrayList;

public class Table {

	private ArrayList<ArrayList<Integer>> table;
	private ArrayList<String> columnNames;
	private int numberOfColumns;
	
	public Table()
	{
		table = new ArrayList<ArrayList<Integer>>();
		columnNames = new ArrayList<>();
		numberOfColumns = 0;
	}
	
	public void addColumn(String name)
	{
		table.add(new ArrayList<Integer>());
		columnNames.add(name);
		numberOfColumns++;
	}
	
	public void addRow(ArrayList<Integer> listOfIntegers)
	{
		for(int i = 0; i < numberOfColumns; i++) {
			table.get(i).add(listOfIntegers.get(i));
		}
	} 
	
	public ArrayList<Integer> getRow(int id)
	{
		ArrayList<Integer> row = new ArrayList<>();
		for(int i = 0; i < numberOfColumns; i++)
			row.add(table.get(i).get(id));
		return row;
	}
	
	public void display()
	{
		for(int i = 0; i < table.size();i++) {
			System.out.print(columnNames.get(i));
			for(int k = 0; k < table.get(i).size(); k++) {
				System.out.print(table.get(i).get(k) + " | ");
			}
			System.out.println();
		}
	}
	
	
}
