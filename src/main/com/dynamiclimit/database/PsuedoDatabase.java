package main.com.dynamiclimit.database;

import java.util.Random;

import java.util.ArrayList;
import java.util.HashMap;

public class PsuedoDatabase {
	
	private HashMap<String, Table> tables;
	private Random rand;
	
	public static final int numberOfRoadSegments = 4;
	
	public PsuedoDatabase()
	{
		// Holds the tables by their name
		tables = new HashMap<>();
		rand = new Random();
		/*
		 * The RoadSegment to SpeedLimit table creation
		 */
		Table RSToSLtable = new Table();
		RSToSLtable.addColumn("SL");
		addTable("RSToSL", RSToSLtable);
		
		ArrayList<Integer> RS1ToSLData = new ArrayList<>();
		RS1ToSLData.add(10);		
		RSToSLtable.addRow(RS1ToSLData);
		RSToSLtable.update(0, 0, 15);
		
		ArrayList<Integer> RS2ToSLData = new ArrayList<>();
		RS2ToSLData.add(10);
		RSToSLtable.addRow(RS2ToSLData);
		
		ArrayList<Integer> RS3ToSLData = new ArrayList<>();
		RS3ToSLData.add(10);
		RSToSLtable.addRow(RS3ToSLData);
		
		ArrayList<Integer> RS4ToSLData = new ArrayList<>();
		RS4ToSLData.add(10);
		RSToSLtable.addRow(RS4ToSLData);
		
		
		/*
		 * Creates a RoadCondition to SpeedLimit table for every RoadSegment 
		 */
		for(int i = 1; i <= numberOfRoadSegments; i++)
		{
			Table table = new Table();
			table.addColumn("SL");
			
			for(int j = 0; j <= 10; j++) {
				ArrayList<Integer> SLRow = new ArrayList<>();
				SLRow.add(5*j + rand.nextInt(6));
				table.addRow(SLRow);
			}
			
			addTable("RS" + i + "RCToSL", table);
		}
		
		Table municipalitiesSpeedCaps = new Table();
		municipalitiesSpeedCaps.addColumn("SL");
		addTable("Speeds", municipalitiesSpeedCaps);
		for(int j = 1; j < 4; j++) {
			ArrayList<Integer> SLRow = new ArrayList<>();
			SLRow.add(50 + rand.nextInt(6)-3);
			municipalitiesSpeedCaps.addRow(SLRow);
		}
	}

	
	/**
	 * Accesor method for a table in the database by name
	 * 
	 * @param name
	 * @return
	 */
	public Table getTable(String name)
	{
		return tables.get(name);
	}
	
	public void addTable(String name, Table table)
	{
		tables.put(name, table);
	}
	
	public void displayDataBase()
	{
		for(String tableName : tables.keySet())
		{
			System.out.println(tableName);
			System.out.println("---------------");
			tables.get(tableName).display();
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		(new PsuedoDatabase()).displayDataBase();
	}
}
	
	

