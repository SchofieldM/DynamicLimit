package main.com.dynamiclimit.database;

import java.util.HashMap;

public class PsuedoDatabase {
	
	private HashMap<String, Table> tables;
	
	public static final int numberOfRoadSegments = 4;
	
	public PsuedoDatabase()
	{
		tables = new HashMap<>();
		
		Table RSToSLtable = new Table();
		RSToSLtable.addColumn("SL");
		addTable("RSToSL", RSToSLtable);
		
		for(int i = 1; i <= numberOfRoadSegments; i++)
		{
			Table table = new Table();
			table.addColumn("SL");
			addTable("RS" + i + "RCToSL", table);
		}
		for(int i = 1; i <= numberOfRoadSegments; i++)
		{
			Table table = new Table();
			table.addColumn("RC");
			table.addColumn("SL");
			addTable("RS" + i + "AccidentLogs", table);
		}
		
	}

	
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
			tables.get(tableName).display();
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		
		(new PsuedoDatabase()).displayDataBase();
	}
}
	
	

