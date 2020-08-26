/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Ship.java
 *  FILE PURPOSE - Outputs the ship data
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

package code;

import java.io.File;
import java.io.FileNotFoundException;

/** --- Project 1 of CMSC 335 ---
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Ship.java
 *  FILE PURPOSE - Needed to help create the two types of ship classes of cargo and passenger
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Ship extends Thing {
    /*
     * Fields
     */
    protected PortTime arrivalTime, dockTime;
    protected double draft, length, weight, width;
    protected ArrayList<Job> jobs = new ArrayList<>();

    /*
     * Constructors
     */
    // default constructor
    public Ship(Scanner scan) {
	super(scan);
	if (scan.hasNextDouble())
	    draft = scan.nextDouble();
	if (scan.hasNextDouble())
	    length = scan.nextDouble();
	if (scan.hasNextDouble())
	    weight = scan.nextDouble();
	if (scan.hasNextDouble())
	    width = scan.nextDouble();
    }
    // blank constructor
    public Ship() {}

    /*
     * Get ship by index for for HashMap shipMap
     */
    public Ship getShipByIndex(int x, HashMap<Integer, Ship> hms) {
	return hms.get(x);
    }

    /*
     * Records Jobs
     */
    public ArrayList<Job> recordJobs(String filePath) throws FileNotFoundException {
	String searchJobTerm = "job";
	sc = new Scanner(new File(filePath));
	String lines = "";

	// gets sea port data
	while (sc.hasNextLine()) {
	    lines = sc.nextLine();

	    while (sc.hasNext(searchJobTerm)) {
		lines = sc.nextLine();
		String jobData = lines.replaceAll(searchJobTerm, "").trim();

		String[] jobDataArray = jobData.split("\\r?\\n");

		List<String> jobList = new ArrayList<String>();
		Collections.addAll(jobList, jobDataArray);

		for (int i = 0; i < jobList.size(); i++) {
		    Scanner scanJobs = new Scanner(jobList.get(i));
		    if (scanJobs.hasNext()) {
			jobs.add(new Job(scanJobs));
		    }
		}
	    }
	}
	sc.close();

	return jobs;
    }

    /*
     * Getter, for fields
     */
    public double getWidth() {
	return width;
    }

    public void setWidth(double width) {
	this.width = width;
    }

    public double getDraft() {
	return draft;
    }

    public double getLength() {
	return length;
    }

    public double getWeight() {
	return weight;
    }

    /*
     * Get jobs
     */
    public ArrayList<Job> getJobs() {
	return jobs;
    }

    /*
     * Set Jobs
     */
    public void setJobs(ArrayList<Job> jobs) {
	this.jobs = jobs;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Ship: " + name + " \n" + 
		"Index: " + index + " \n" + 
		"Parent: " + parent + "\n" + 
		"Draft: " + draft + " \n" +
		 "Length: " + length + ", " + 
		"Weight: " + weight + " \n" + 
		 "Width: " + width + "\n" + jobs;
    }

    /*
     * Inner classes for ship sorting
     */
    // to compare ship draft
    public class CompareShipDraft implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return Double.compare(s1.getDraft(), s2.getDraft());
	}
    }

    // to compare ship index
    public class CompareShipIndex implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return Integer.compare(s1.getIndex(), s2.getIndex());
	}
    }

    // to compare ship length
    public class CompareShipLength implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return Double.compare(s1.getLength(), s2.getLength());
	}
    }

    // to compare ship name
    public class CompareShipName implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return s1.getName().compareTo(s2.getName());
	}
    }

    // to compare ship weight
    public class CompareShipWeight implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return Double.compare(s1.getWeight(), s2.getWeight());
	}
    }

    // to compare ship width
    public class CompareShipWidth implements Comparator<Ship> {
	@Override
	public int compare(Ship s1, Ship s2) {
	    return Double.compare(s1.getWidth(), s2.getWidth());
	}
    }

}
