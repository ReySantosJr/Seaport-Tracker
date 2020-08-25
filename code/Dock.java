package code;

/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Dock.java
 *  FILE PURPOSE - Outputs the data for the docks of the sea ports
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.Comparator;
import java.util.Scanner;

public class Dock extends Thing {
    /*
     * Fields
     */
    private int dockShipIndex;

    /*
     * Constructors
     */
    // default constructor
    public Dock(Scanner sc) {
	super(sc);
	if (sc.hasNextInt())
	    dockShipIndex = sc.nextInt();
    }

    // blank constructor
    public Dock() {}

    /*
     * Getter, dockShipIndex
     */
    public int getDockShipIndex() {
	return dockShipIndex;
    }

    public int compareTo(String t) {
	return 0;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Dock: " + name + ", " + "Index: " + index + ", " + "Parent: " + parent;
    }

    /*
     * Inner classes needed for sorting
     */
    // Dock name
    public class CompareDockName implements Comparator<Dock> {
	@Override
	public int compare(Dock d1, Dock d2) {
	    return d1.getName().compareTo(d2.getName());
	}
    }

    // Dock index
    public class CompareDockIndex implements Comparator<Dock> {
	@Override
	public int compare(Dock d1, Dock d2) {
	    return Integer.compare(d1.getIndex(), d2.getIndex());
	}
    }
}
