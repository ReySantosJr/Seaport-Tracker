package code;

/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - CargoShips.java
 *  FILE PURPOSE - Outputs the data about cargo ships
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.Scanner;

public class CargoShip extends Ship {
    /*
     * Fields
     */
    private double cargoValue;
    private double cargoVolume;
    private double cargoWeight;

    /*
     * Constructors
     */
    // default constructor
    public CargoShip(Scanner sc) {
	super(sc);

	if (sc.hasNextDouble())
	    cargoValue = sc.nextDouble();
	if (sc.hasNextDouble())
	    cargoVolume = sc.nextDouble();
	if (sc.hasNextDouble())
	    cargoWeight = sc.nextDouble();
    }
    // blank constructor
    public CargoShip() {}

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Cargo Ship: " + name + ", " + "Index: " + index + ", " + "Parent: " + parent + "\n" + "Draft: " + draft
		+ ", " + "Length: " + length + ", " + "Weight: " + weight + ", " + "Width: " + width + "\n"
		+ "Cargo Value: " + cargoValue + ", " + "Cargo Volume: " + cargoVolume + ", " + "Cargo Weight: "
		+ cargoWeight;
    }
}
