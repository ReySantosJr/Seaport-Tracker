/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - SeaPort.java
 *  FILE PURPOSE - Outputs the data for the entire sea port (docks, ships, persons).
 *  Contains many of the codes used for sorting and recording the data read from the file
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

package code;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SeaPort extends Thing {
    /*
     * Fields
     */
    private ArrayList<Dock> docks = new ArrayList<>();
    private ArrayList<Ship> que = new ArrayList<>(); // the list of ships waiting to dock
    private ArrayList<Ship> ships = new ArrayList<>(); // a list of all the ships at this port
    private ArrayList<Person> persons = new ArrayList<>(); // people with skills at this port
    private HashMap<Integer, Ship> shipMap = new HashMap<>();

    // Instances
    World world = new World();
    Ship ship = new Ship();

    /*
     * Constructors
     */
    // default constructor
    public SeaPort(Scanner sc) {
	super(sc);
    }
    // blank constructor
    public SeaPort() {}

    /*
     * Record Docks
     */
    public ArrayList<Dock> recordDocks(String filePath) throws FileNotFoundException {
	String searchDockTerm = "dock";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets dock data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchDockTerm)) {
		lines = sc.nextLine();
		String dockData = lines.replaceAll(searchDockTerm, "").trim();

		String[] dockDataArray = dockData.split("\\r?\\n");

		List<String> dockList = new ArrayList<String>();
		Collections.addAll(dockList, dockDataArray);

		for (int i = 0; i < dockList.size(); i++) {
		    Scanner scanDocks = new Scanner(dockList.get(i));
		    if (scanDocks.hasNext()) {
			docks.add(new Dock(scanDocks));
		    }
		}
	    }
	}

	sc.close();

	return docks;
    }

    /*
     * Record Passenger Ships
     */
    public ArrayList<Ship> recordPassengerShips(String filePath) throws FileNotFoundException {
	String searchPShipTerm = "pship";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets Passenger Ship data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchPShipTerm)) {
		lines = sc.nextLine();
		String pShipData = lines.replaceAll(searchPShipTerm, "").trim();

		String[] pShipDataArray = pShipData.split("\\r?\\n");

		List<String> pShipList = new ArrayList<String>();
		Collections.addAll(pShipList, pShipDataArray);

		for (int i = 0; i < pShipList.size(); i++) {
		    Scanner scanPShips = new Scanner(pShipList.get(i));
		    if (scanPShips.hasNext()) {
			ships.add(new PassengerShip(scanPShips));
		    }
		}
	    }
	}

	return ships;
    }

    /*
     * Record Cargo Ships
     */
    public ArrayList<Ship> recordCargoShips(String filePath) throws FileNotFoundException {
	String searchCShipTerm = "cship";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets Cargo Ship data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchCShipTerm)) {
		lines = sc.nextLine();
		String cShipData = lines.replaceAll(searchCShipTerm, "").trim();

		String[] cShipDataArray = cShipData.split("\\r?\\n");

		List<String> cShipList = new ArrayList<String>();
		Collections.addAll(cShipList, cShipDataArray);

		for (int i = 0; i < cShipList.size(); i++) {
		    Scanner scanCShips = new Scanner(cShipList.get(i));
		    if (scanCShips.hasNext()) {
			ships.add(new CargoShip(scanCShips));
		    }
		}
	    }
	}
	return ships;
    }

    /*
     * Record Persons
     */
    public ArrayList<Person> recordPersons(String filePath) throws FileNotFoundException {
	String searchPersonTerm = "person";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets Person data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchPersonTerm)) {
		lines = sc.nextLine();
		String personData = lines.replaceAll(searchPersonTerm, "").trim();

		String[] personDataArray = personData.split("\\r?\\n");

		List<String> personList = new ArrayList<String>();
		Collections.addAll(personList, personDataArray);

		for (int i = 0; i < personList.size(); i++) {
		    Scanner scanPersons = new Scanner(personList.get(i));
		    if (scanPersons.hasNext()) {
			persons.add(new Person(scanPersons));
		    }
		}
	    }
	}

	sc.close();

	return persons;
    }

    /*
     * Inner classes needed for sorting
     */
    public class CompareSeaPortName implements Comparator<SeaPort> {
	@Override
	public int compare(SeaPort s1, SeaPort s2) {
	    return s1.getName().compareTo(s2.getName());
	}
    }

    public class CompareSeaPortIndex implements Comparator<SeaPort> {
	@Override
	public int compare(SeaPort s1, SeaPort s2) {
	    return Integer.compare(s1.getIndex(), s2.getIndex());
	}
    }

    /*
     * Getters, for fields
     */
    public ArrayList<Dock> getDocks() {
	return docks;
    }

    public ArrayList<Ship> getQue() {
	return que;
    }

    public ArrayList<Ship> getShips() {
	return ships;
    }

    public ArrayList<Person> getPersons() {
	return persons;
    }

    public HashMap<Integer, Ship> getShipMap() {
	return shipMap;
    }

    /*
     * Setters, for fields
     */
    public void setDocks(ArrayList<Dock> docks) {
	this.docks = docks;
    }

    public void setQue(ArrayList<Ship> que) {
	this.que = que;
    }

    public void setShips(ArrayList<Ship> ships) {
	this.ships = ships;
    }

    public void setPersons(ArrayList<Person> persons) {
	this.persons = persons;
    }

    public void setShipMap(HashMap<Integer, Ship> shipMap) {
	this.shipMap = shipMap;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return "SeaPort: " + name + " " + index + ", " + "Parent: " + parent + "\n" + "Docks: " + docks.toString()
		+ "\n" + "Ships in que: " + que.toString() + "\n" + "All Ships: " + ships.toString() + "\n"
		+ "Persons: " + persons.toString() + "\n";
    }

}
