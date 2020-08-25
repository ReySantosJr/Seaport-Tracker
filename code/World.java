/** ------------------------------
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - World.java
 *  FILE PURPOSE - Reads the file heading to make sure it follows correct guidelines.
 *  Holds sea port information
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

package code;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class World extends Thing {
    /*
     * Fields
     */
    protected ArrayList<SeaPort> ports = new ArrayList<>();
    protected PortTime time;

    /*
     * Constructors
     */
    // default constructor
    public World(Scanner sc) {
	super(sc);
    }
    // blank constructor
    public World() {}

    /*
     * Record Seaports
     */
    // Records sea ports
    public ArrayList<SeaPort> recordSeaPorts(String filePath) throws FileNotFoundException {
	String searchPortTerm = "port";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets sea port data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchPortTerm)) {
		lines = sc.nextLine();
		String seaPortData = lines.replaceAll(searchPortTerm, "").trim();

		String[] seaPortDataArray = seaPortData.split("\\r?\\n");
		List<String> seaPortList = new ArrayList<String>();

		Collections.addAll(seaPortList, seaPortDataArray);

		for (int i = 0; i < seaPortList.size(); i++) {
		    Scanner scanSeaPorts = new Scanner(seaPortList.get(i));
		    ports.add(new SeaPort(scanSeaPorts));
		}
	    }
	}

	sc.close();

	return ports;
    }

    /*
     * Getter, ports
     */
    public ArrayList<SeaPort> getPorts() {
	return ports;
    }

    /*
     * Setter, ports
     */
    public void setPorts(ArrayList<SeaPort> ports) {
	this.ports = ports;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return name + " \n" + ports.toString() + "\n";
    }

}
