package code;

/** 
 *  Program Name -Sea Port Program
 *  Program Purpose -Read data files of different sea ports.
 *  FILE NAME - PassengerShip.java
 *  FILE PURPOSE - Outputs the data about passenger ships
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.Scanner;

public class PassengerShip extends Ship {
    /*
     * Fields
     */
    private int numberOfOccupiedRooms;
    private int numberOfRooms;
    private int numberOfPassengers;

    /*
     * Constructors
     */
    // default constructor
    public PassengerShip(Scanner sc) {
	super(sc);
	if (sc.hasNextInt())
	    numberOfOccupiedRooms = sc.nextInt();
	if (sc.hasNextInt())
	    numberOfRooms = sc.nextInt();
	if (sc.hasNextInt())
	    numberOfPassengers = sc.nextInt();
    }
    // blank constructor
    public PassengerShip() {}

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Passenger Ship: " + name + " \n" + 
		"Index: " + index + " \n" + 
		"Parent - " + parent + "\n" + 
		"Draft - " + draft + "\n" + 
		"Length - " + length + " \n" + 
		"Weight - " + weight + " \n" + 
		"Width - " + width + "\n" + 
		"Number of OccupiedRooms: " + numberOfOccupiedRooms + "\n" + 
		"Number of Rooms: " + numberOfRooms + "\n" + 
		"Number of Passengers: " + numberOfPassengers;
    }
}
