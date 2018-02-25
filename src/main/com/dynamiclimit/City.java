package main.com.dynamiclimit;

// Imports
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import main.com.dynamiclimit.Car;
import main.com.dynamiclimit.Server;

/**
 * 
 * 
 * @version 2.24.18
 */
public class City {

// Fields
	// ArrayList
		private ArrayList<Car> cars;
	// Server
		private Server server;
	// int
		private int nextCarID;
	// Random
		private Random rand;
	
// Constructors
	/**
	 * Builds a city scheme for our program
	 * 
	 * A city has one server and many cars, cars ping the server for
	 * the appropriate speed limit to travel
	 * 
	 * The server keeps a table of speed limits for each road segment
	 * 
	 * @param name, name of the city
	 */
	public City(String name)
	{
		// Creates the City's Server
		server = new Server();
		
		// An ArrayList to hold the cars that are in the city
		cars = new ArrayList<>();

		// The ID of the next car to create
		nextCarID = 1;
		
		// Start the city with 3 cars: car1, car2, car3
		for(int i = 1; i < 4; i++) 
			addCar();
		
		// Every so often randomly either add or remove a car
		rand = new Random();
		new Timer().schedule(new TimerTask() { 
		      @Override 
		      public void run() { 
		  		if(rand.nextInt(2) == 1) {
		  			addCar();
		  		}else {
		  			removeCar();
		  		}
		      } 
		    }, 5000, 5000); 
		
	}
	
// Methods
	public void addCar()
	{
		cars.add(new Car("car" + nextCarID, server));
		nextCarID++;
	}
	
	public int getNumberOfCars()
	{
		return cars.size();
	}
	
	public void removeCar()
	{
		if(cars.size() == 1) {
			return;
		}
			
		Car carToLeave = cars.get(rand.nextInt(cars.size()));
		cars.remove(carToLeave);
		carToLeave.leaveCity();
	}
	
	public Server getServer()
	{
		return server;
	}
	
	
}
