package main.com.dynamiclimit;


//Java Lang
	// swing
         import javax.swing.*;
         import java.awt.*;
         import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JPanel{
	private City city;
	
	public GUI(City city)
	{
		this.city = city;
	}
	
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.setBackground(Color.GREEN);
		g.setColor(Color.BLACK);
		g.fillRect(0, 300, 750, 100);
		g.fillRect(300, 0, 100, 750);
		
		g.setColor(Color.RED);
		g.fillRect(100, 315, 75, 75);
		g.fillRect(550, 315, 75, 75);
		g.fillRect(315, 75, 75, 75);
		g.fillRect(315, 550, 75, 75);
		
		g.setColor(Color.GRAY);
		g.fillRect(170, 135, 100, 150);
		g.setColor(Color.BLACK);
		int fontSize = 20;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		g.drawString("Server: ", 190, 150);
		g.drawString("Condtions: ", 180, 180);
		g.drawString(Integer.toString(city.getServer().getRoadConditions()), 200, 210);
		g.drawString("Cars in city: " , 170, 235);
		g.drawString(Integer.toString(city.getNumberOfCars()) , 200, 260);
		g.drawString("SL: " + Integer.toString(city.getServer().getDb().getTable("RS1RCToSL").getRow(city.getServer().getRoadConditions()).get(0)), 105, 330);
		g.drawString("RS: 1", 105, 360);
		
		g.drawString("SL: " + Integer.toString(city.getServer().getDb().getTable("RS2RCToSL").getRow(city.getServer().getRoadConditions()).get(0)), 555, 330);
		g.drawString("RS: 2", 555, 360);
		
		g.drawString("SL: " + Integer.toString(city.getServer().getDb().getTable("RS3RCToSL").getRow(city.getServer().getRoadConditions()).get(0)), 330, 100);
		g.drawString("RS: 3", 330, 125);
		
		g.drawString("SL: " + Integer.toString(city.getServer().getDb().getTable("RS4RCToSL").getRow(city.getServer().getRoadConditions()).get(0)), 320, 575);
		g.drawString("RS: 4", 320, 600);


		fontSize = 50;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		g.drawString(city.getName(), 550, 50);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
		g.drawString("Key: ", 0, 25);
		g.drawString("SL - Speed Limit, RS - Road Segment ", 0, 50);
		g.drawString("Conditions - A numeric value 0(Worst) - 10(Best)", 0, 75);
		g.drawString("      to represent road conditions/weather/time", 0, 100);
		g.drawString("      and thereby calculate appropriate speed limits", 0, 125);
		
		new Timer().schedule(new TimerTask() { 
		      @Override 
		      public void run() { 
		    	  repaint();
		      } 
		    }, 500, 500); 
	}
	
}


