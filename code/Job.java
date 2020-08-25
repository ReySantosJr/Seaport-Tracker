package code;

/**
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Job.java
 *  FILE PURPOSE - Outputs the data of the job.
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Job extends Thing {
    /*
     * Fields
     */
    private double duration;
    private ArrayList<String> requirements = new ArrayList<>(); // should be some of the skills of the persons

    /*
     * Constructors
     */
    // default constructor
    public Job(Scanner scan) {
	super(scan);
	if (scan.hasNextDouble())
	    duration = scan.nextDouble();
	while (scan.hasNext()) {
	    requirements.add(scan.next());
	}

	setRequirements(requirements);
    }
    // blank constructor
    public Job() {}

    /*
     * Getters for fields
     */
    public double getDuration() {
	return duration;
    }

    public ArrayList<String> getRequirements() {
	return requirements;
    }

    /*
     * Setters for fields
     */
    public void setDuration(double duration) {
	this.duration = duration;
    }

    public void setRequirements(ArrayList<String> requirements) {
	this.requirements = requirements;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Job Title: " + name + ", Index: " + index + "\n" + "Duration: " + duration + "\n" + "Requirements: "
		+ requirements.toString();
    }

    /*
     * Inner classes for Job sorting
     */
    // to compare ship draft
    public class CompareJobIndex implements Comparator<Job> {
	@Override
	public int compare(Job j1, Job j2) {
	    return Double.compare(j1.getIndex(), j2.getIndex());
	}
    }

}
