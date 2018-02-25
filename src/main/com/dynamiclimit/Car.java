package main.com.dynamiclimit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Car {

	private int currentRoadSegment;
	private int targetSpeed;
	private String name;
	private Server server;
	private Random rand;
	private boolean inCity;
	
	public Car(String name, Server server)
	{
		this.name = name;
		this.server = server;
		rand = new Random();
		currentRoadSegment = rand.nextInt(4)+1;
		inCity = true;
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() { 
		      @Override 
		    public void run() { 
		    	  if(!inCity) {
		    		  timer.cancel();
		    	  }
		  		targetSpeed = server.request(currentRoadSegment);
		  		System.out.println(name + "'s target speed: " + targetSpeed + " on road segment " + currentRoadSegment);
		    } 
		 }, 1000, 1000); 
	}
	
	public String getName()
	{
		return name;
	}
	
	public void leaveCity()
	{
		inCity = false;
	}
	
}
