package code;

/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Person.java
 *  FILE PURPOSE - Outputs the data of Person
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

import java.util.Comparator;
import java.util.Scanner;

public class Person extends Thing {
    /*
     * Fields
     */
    private String skill;

    /*
     * Constructors
     */
    // default constructor
    public Person(Scanner sc) {
	super(sc);
	if (sc.hasNext())
	    skill = sc.next();
    }
    // blank constructor
    public Person() {}

    /*
     * Getter, skill
     */
    public String getSkill() {
	return skill;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
	return "Name: " + name + ", Skill: " + skill;
    }

    /*
     * Inner classes needed for sorting
     */
    public class ComparePersonName implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
	    return p1.getName().compareTo(p2.getName());
	}
    }

    public class ComparePersonIndex implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
	    return Integer.compare(p1.getIndex(), p2.getIndex());
	}
    }

}
