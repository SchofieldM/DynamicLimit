package main.com.dynamiclimit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Begins the demonstration
 * 
 * @author Matthew Schofield
 *@version 2.26.18
 */
public class Start {

// Fields
	// JFrame
		private static JFrame mainWindow;
		
	/**
	 * Entrance point of the program
	 * 
	 * @param args, currently unused
	 */
	public static void main(String[] args)
	{
		
		mainWindow = new JFrame();
		mainWindow.add(new GUI(new City("Tiny City")));
	    mainWindow.setSize(750, 750);
	    mainWindow.setResizable(false);
	    mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    mainWindow.setTitle("Dynamic Limit");
		mainWindow.setVisible(true);
	}

}
