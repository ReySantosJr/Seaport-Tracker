/** ------------------------------
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - Thing.java
 *  FILE PURPOSE - The interface needed to help create all the other files/codes
 *  the base properties for most of the classes of the program.
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

package code;

import java.util.Scanner;

public class Thing {
    /*
     * Fields
     */
    protected String name;
    protected int index;
    protected int parent;

    protected Scanner sc;

    /*
     * Constructors
     */
    // default constructor
    public Thing(Scanner sc) {
	this.sc = sc;
	if (sc.hasNext())
	    name = sc.next();
	if (sc.hasNextInt())
	    index = sc.nextInt();
	if (sc.hasNextInt())
	    parent = sc.nextInt();
    }
    // blank constructor
    public Thing() {}

    /*
     * Getter, for fields
     */
    public String getName() {
	return name;
    }

    public int getIndex() {
	return index;
    }

    public int getParent() {
	return parent;
    }

    /*
     * Setter, for fields
     */
    public void setName(String name) {
	this.name = name;
    }

    public void setIndex(int index) {
	this.index = index;
    }

    public void setParent(int parent) {
	this.parent = parent;
    }

    /*
     * toString
     */
    public String toString() {
	return "Name: " + name + ", " + "Index: " + index + ", " + "Parent: " + parent;
    }

}
