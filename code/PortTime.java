package code;

/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - PortTime.java
 *  FILE PURPOSE - Outputs the output time of the sea port..
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.Scanner;

public class PortTime {
    /*
     * Fields
     */
    private int time = 0;
    private Scanner sc;

    /*
     * Constructors
     */
    // default constructor
    public PortTime(Scanner scan) {
	this.sc = scan;
	if (sc.hasNextInt())
	    time = sc.nextInt();
    }
    // blank constructor
    public PortTime() {}

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Port Time: " + time;
    }

}
