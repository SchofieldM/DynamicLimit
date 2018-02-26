package main.com.dynamiclimit;



import java.util.Timer; 
import java.util.TimerTask;
import java.util.Random;

import main.com.dynamiclimit.database.PsuedoDatabase;
import main.com.dynamiclimit.database.Table;


/**
 * Server that handles most of the operations of the program
 * 
 * @author Matthew Schofield
 * @version 2.26.18
 */
public class Server {

	private int roadCondition;
	private double time;
	private PsuedoDatabase db; 
	private Random rand;
	private String precipitationType;
	private int precipitationAmount;
	private int windGusts;
	private int currentWeatherState;
	private Object[][] weathers;
	
	public Server()
	{
		rand = new Random();
		 db = new PsuedoDatabase();
		 time = 12.0;
		 roadCondition = 0;
			precipitationType = "";
			precipitationAmount = 0;
			windGusts = 0;
			currentWeatherState = 0;
			weathers = new Object[14][3];
			populateWeathers();
		 new Timer().schedule(new TimerTask() { 
		      @Override 
		      public void run() { 
		  		calculateRoadCondtion(); 
		  		updateTopLevelTable();
		  		updateEachRCToSL();
		      } 
		    }, 100, 3300); 
	  } 
	
	public PsuedoDatabase getDb()
	{
		return db;
	}
	
	public int getRoadConditions()
	{
		return roadCondition;
	}
	
	public void populateWeathers()
	{
		weathers[0][0] = "Clear";
		weathers[0][1] = 0;
		weathers[0][2] = 10;
		
		weathers[1][0] = "Rain";
		weathers[1][1] = 1;
		weathers[1][2] = 12;
		
		weathers[2][0] = "Rain";
		weathers[2][1] = 2;
		weathers[2][2] = 17;
		
		weathers[3][0] = "Snow";
		weathers[3][1] = 1;
		weathers[3][2] = 15;
		

		weathers[4][0] = "Snow";
		weathers[4][1] = 2;
		weathers[4][2] = 21;
		
		weathers[5][0] = "Snow";
		weathers[5][1] = 1;
		weathers[5][2] = 15;
		
		weathers[6][0] = "Rain";
		weathers[6][1] = 1;
		weathers[6][2] = 12;
		
		weathers[7][0] = "Rain";
		weathers[7][1] = 2;
		weathers[7][2] = 15;
		
		weathers[8][0] = "Rain";
		weathers[8][1] = 1;
		weathers[8][2] = 3;
		
		weathers[9][0] = "Clear";
		weathers[9][1] = 0;
		weathers[9][2] = 7;
		
		weathers[10][0] = "Clear";
		weathers[10][1] = 0;
		weathers[10][2] = 5;
		

		weathers[11][0] = "Clear";
		weathers[11][1] = 0;
		weathers[11][2] = 9;
		
		weathers[12][0] = "Rain";
		weathers[12][1] = 1;
		weathers[12][2] = 4;
		
		weathers[13][0] = "Rain";
		weathers[13][1] = 1;
		weathers[13][2] = 12;
		
		
		
	}
	
	public int request(int roadSegment)
	{
		return db.getTable("RSToSL").getRow(roadSegment-1).get(0);
	}
	
	private void calculateRoadCondtion()
	{	
		double rate = .01;
		double b = .05;
		double a = 100000.0*(rate*rate*rate);
		double timeSquared = (time-12.0)*(time-12.0);
		roadCondition = (int) ( ((1.0 - (a)*timeSquared) )* weatherValue());
		System.out.println("Road condition: " + roadCondition);
	}
	
	
	
	private int timeValue()
	{
		if(time <= 6 || time >= 20) {
			return 1;
		}else if((time > 6 && time < 10) || (time > 16 && time < 19)) {
			return 2;
		}else {
			return 0;
		}
	}
	
	private void nextWeatherState()
	{
		currentWeatherState++;
		if(currentWeatherState > 6) {
			currentWeatherState = 0;
		}
		precipitationType = (String) weathers[currentWeatherState][0];
		precipitationAmount = (Integer) weathers[currentWeatherState][1];
		windGusts = (Integer) weathers[currentWeatherState][2];
	}
	
	private int weatherValue()
	{
		int value = 8;
		nextWeatherState();
		if(precipitationType.equals("Snow")) {
			value -= precipitationAmount * 2;
		}else if(precipitationType.equals("Rain")) {
			value -= precipitationAmount;
		}
		value -= windGusts/10;
		if(value < 0) {
			value = 0;
		}
		return value;
	}

	
	private void updateTopLevelTable()
	{
		Table RSToSLTable = db.getTable("RSToSL");
		Table currentRSRCToSLTable;
		for(int i = 0; i <= 3; i++) {
			currentRSRCToSLTable = db.getTable("RS" + (i+1) + "RCToSL");
			RSToSLTable.update(0, i, currentRSRCToSLTable.getRow(roadCondition).get(0));
		}
	}
	
	private void updateEachRCToSL()
	{
		for(int i = 1; i < 4; i++) {
			Table currentTable = db.getTable("RS" + i + "RCToSL");
			for(int j = 0; j < 3; j++) {
				int baseSpeed = db.getTable("Speeds").getRow(j).get(0);
				int newSpeed = (int) ((int) (baseSpeed) - ((double)((10-roadCondition))*.05* ((double)baseSpeed)));
				currentTable.update(0, j, newSpeed);
			}
		}
	}
	
}
