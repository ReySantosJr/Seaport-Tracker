/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports. 
 *  Use HashMaps, Comparable, & Comparator to help sort the data.
 *  
 *  FILE NAME - SeaPortProgramTwo.java
 *  FILE PURPOSE - Contains GUI of Sea Port Program and listener codes to output.
 */
/** @author Reynaldo Santos
 *  @since 9-28-2019
 */

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;

import code.CargoShip;
import code.Dock;
import code.PassengerShip;
import code.Person;
import code.SeaPort;
import code.Ship;
import code.World;

public class SeaPortTrackerTwo extends SeaPortTrackerOne {
    /*
     * Fields
     */
    // "Select Sorting " sections
    protected JPanel sortMethodPanel = new JPanel();
    protected ButtonGroup docksPersButtonGroup = new ButtonGroup();

    // Sort Sea Port Activity
    // Select Sorting panel, top part
    protected Box selectSortingBox = Box.createVerticalBox();
    protected Box seaPortActivityBox = Box.createHorizontalBox();
    protected JPanel seaPortActivityPanel = new JPanel();
    protected Box spActivityJRadButtonBox = Box.createHorizontalBox();
    protected JLabel sortSeaPortLabel = new JLabel("Sort World by Index");
    protected JRadioButton sortSeaPortRadButton = new JRadioButton("Initiate");

    // Select Sorting panel, middle part
    protected Box categorySortBox = Box.createHorizontalBox();
    protected Box categorySortTitleBox = Box.createVerticalBox();
    protected JTextField enterSortCategoryTextField = new JTextField("Enter type", 7);
    protected JRadioButton namesJRad = new JRadioButton("By Name");
    protected JRadioButton indexesJRad = new JRadioButton("By Index");

    // Select Sorting panel, bottom part
    protected Box deselectShowSortBox = Box.createHorizontalBox();
    protected JButton showSortJButton = new JButton("Show Sort");
    protected JPanel deselectRadBtnButtonPanel = new JPanel();
    protected JButton deselectRadBtnButton = new JButton("Deselect Radio Buttons");

    // "Ships In Que" section
    protected Box shipsInQueBox = Box.createVerticalBox();

    // Key Count section
    protected JPanel keyCountPanel = new JPanel();
    protected Box keyCountBox = Box.createHorizontalBox();
    protected JTextField keyCountTextField = new JTextField(5);
    protected JButton allShipsInQueButton = new JButton("See All Ships in Que");

    // Enter key section
    protected JPanel enterKeyPanel = new JPanel();
    protected JTextField enterKeyTextField = new JTextField(3);
    protected JButton getShipButton = new JButton("              Get Ship             ");

    // Ship dimenion sorting panel
    protected JPanel chooseShipDimensionsPanel = new JPanel();
    protected JTextField chooseShipDimensionsTextField = new JTextField("By what dimensions?", 8);
    protected JButton sortShipQueButton = new JButton("     Sort Ships    ");

    // Instances needed
    protected Ship ship = new Ship();
    protected Dock dock = new Dock();
    protected Person person = new Person();

    /*
     * Constructors
     */
    public SeaPortTrackerTwo() {
	// Sets title & JFrame settings
	setTitle("Sea Port Program 2");
	setSize(525, 555);
	setResizable(false);

	/*
	 * Program 2, LEFT LAYOUT DESIGN
	 */
	// Basic sorting of world activity, docks, personnel
	sortMethodPanel.setBorder(BorderFactory.createTitledBorder("Select Sorting"));
	sortMethodPanel.setPreferredSize(new Dimension(225, 145));

	// Button Group
	docksPersButtonGroup.add(sortSeaPortRadButton);
	docksPersButtonGroup.add(namesJRad);
	docksPersButtonGroup.add(indexesJRad);

	// Sets ups dimensions for sorting and deselect button
	showSortJButton.setPreferredSize(new Dimension(100, 5));
	deselectRadBtnButton.setPreferredSize(new Dimension(100, 25));

	// Sort Sea Port Activity, and initiate radio button
	spActivityJRadButtonBox.add(sortSeaPortLabel);
	spActivityJRadButtonBox.add(sortSeaPortRadButton);

	// Add seaPortActivityBox to seaPortActivityPanel
	seaPortActivityBox.add(spActivityJRadButtonBox);
	seaPortActivityPanel.setBorder(BorderFactory.createEtchedBorder());
	seaPortActivityPanel.add(seaPortActivityBox);

	// Sets ups enter textfield, By Name & By Index radio buttons
	enterSortCategoryTextField.setToolTipText("Enter: SeaPort, Dock, Ship, or Person");
	categorySortBox.add(enterSortCategoryTextField);
	categorySortBox.add(namesJRad);
	categorySortBox.add(indexesJRad);
	categorySortTitleBox.setBorder(BorderFactory.createEtchedBorder());
	categorySortTitleBox.add(categorySortBox);

	// Sets ups showSortButton and deselect radio button
	deselectShowSortBox.add(showSortJButton);
	deselectShowSortBox.add(deselectRadBtnButton);
	deselectRadBtnButtonPanel.setBorder(BorderFactory.createEtchedBorder());
	deselectRadBtnButtonPanel.add(deselectShowSortBox);

	// Sets up select sorting box
	selectSortingBox.add(seaPortActivityPanel);
	selectSortingBox.add(categorySortTitleBox);
	selectSortingBox.add(deselectRadBtnButtonPanel);

	// Completes "Select Sorting" Section
	sortMethodPanel.add(selectSortingBox);

	// Ships in que sorting section
	shipsInQueBox.setBorder(BorderFactory.createTitledBorder("Ships In Que"));
	keyCountPanel.setBorder(BorderFactory.createTitledBorder("Key Count"));

	// Sets up Key Count section
	keyCountBox.add(keyCountTextField);
	keyCountBox.add(allShipsInQueButton);

	allShipsInQueButton.setToolTipText("Click 'Display World' Button first");
	keyCountTextField.setEditable(false);
	keyCountPanel.add(keyCountBox);

	// Sets up enter key section
	enterKeyPanel.setBorder(BorderFactory.createTitledBorder("Enter Key (0 included)"));
	enterKeyPanel.add(enterKeyTextField);
	getShipButton.setToolTipText("Initiate Key Count first");
	enterKeyPanel.add(getShipButton);

	// Sets up dimension sorting panel
	chooseShipDimensionsPanel.setBorder(BorderFactory.createTitledBorder("Enter Weight, Length, Width, or Draft"));
	chooseShipDimensionsPanel.add(chooseShipDimensionsTextField);
	sortShipQueButton.setToolTipText("Initiate 'Display' or 'Sorted' Activity first");
	chooseShipDimensionsPanel.add(sortShipQueButton);

	// Combines all in vertical box
	shipsInQueBox.add(keyCountPanel);
	shipsInQueBox.add(enterKeyPanel);
	shipsInQueBox.add(chooseShipDimensionsPanel);

	/*
	 *  All added to contentPane
	 */
	programFeaturesLayout.add(sortMethodPanel, BorderLayout.CENTER);
	programFeaturesLayout.add(shipsInQueBox, BorderLayout.SOUTH);

    }// End of SeaPortProgramTwo constructor

    /*
     * Record File title
     */
    @Override
    public World recordWorld(String filePath) throws FileNotFoundException {
	String searchFileTerm = "// File: ";
	sc = new Scanner(new File(filePath));
	String lines = "";

	while (sc.hasNextLine()) {
	    // reads line by line
	    lines = sc.nextLine();

	    // get's file title
	    if (lines.contains(searchFileTerm)) {
		String fileName = lines.replace(searchFileTerm, "");
		Scanner scanTitle = new Scanner(fileName.replace(".txt", ""));
		world = new World(scanTitle);
	    }
	}
	sc.close();

	return world;
    }

    /*
     * Presents the world of the ships, sorted
     */
    public void presentSortedWorld() {
	showSortJButton.addActionListener(sb -> {
	    // To order sea ports by index
	    SeaPort.CompareSeaPortIndex compareSeaPortByIndex = seaPort.new CompareSeaPortIndex();
	    Collections.sort(world.getPorts(), compareSeaPortByIndex);

	    // To order persons by index
	    Person.ComparePersonIndex comparePersonByIndex = person.new ComparePersonIndex();
	    Collections.sort(seaPort.getPersons(), comparePersonByIndex);

	    // To order docks by index
	    Dock.CompareDockIndex compareDockByIndex = dock.new CompareDockIndex();
	    Collections.sort(seaPort.getDocks(), compareDockByIndex);

	    // To order ships by index
	    Ship.CompareShipIndex compareShipIndex = ship.new CompareShipIndex();
	    Collections.sort(seaPort.getShips(), compareShipIndex);

	    // To order ships in que by index
	    Ship.CompareShipIndex compareShipInQueIndex = ship.new CompareShipIndex();
	    Collections.sort(seaPort.getQue(), compareShipInQueIndex);

	    if (sortSeaPortRadButton.isSelected()) {
		// File Title
		outputDataFileTextArea.append(">>>>> The " + world.getName() + ":\n\n");

		for (SeaPort sp : world.getPorts()) {
		    outputDataFileTextArea.append("--- SeaPort: " + sp.getName() + " " + sp.getIndex() + "\n");

		    for (Ship s : seaPort.getShips()) {
			for (Dock d : seaPort.getDocks()) {

			    if ((d.getIndex() == s.getParent()) && (d.getDockShipIndex() == s.getIndex())) {
				if (d.getParent() == sp.getIndex()) {

				    if (s instanceof PassengerShip) {
					outputDataFileTextArea
						.append("Dock: " + d.getName() + " " + d.getIndex() + "\n");
					outputDataFileTextArea
						.append("Passenger Ship: " + s.getName() + " " + s.getIndex() + "\n\n");
				    }

				    if (s instanceof CargoShip) {
					outputDataFileTextArea
						.append("Dock: " + d.getName() + " " + d.getIndex() + "\n");
					outputDataFileTextArea
						.append("Passenger Ship: " + s.getName() + " " + s.getIndex() + "\n\n");
				    }
				}
			    }
			}

			if (s.getParent() == sp.getIndex()) {
			    seaPort.getQue().add(s);
			}
		    }

		} // END OF for (SeaPort sp : world.getPorts())

		// Presents ships in que
		outputDataFileTextArea.append(" --- List of all Ships in que:  \n");
		for (Ship siq : seaPort.getQue()) {
		    if (siq instanceof PassengerShip) {
			outputDataFileTextArea
				.append("> Passenger Ship: " + siq.getName() + " " + siq.getIndex() + "\n");
		    }
		}

		for (Ship siq : seaPort.getQue()) {
		    if (siq instanceof CargoShip) {
			outputDataFileTextArea.append("> Cargo Ship: " + siq.getName() + " " + siq.getIndex() + "\n");
		    }
		}
		outputDataFileTextArea.append("\n");

		// List all ships, Cargo & Passenger
		outputDataFileTextArea.append(" --- List of all Ships:  \n");
		for (Ship as : seaPort.getShips()) {

		    if (as instanceof PassengerShip) {
			outputDataFileTextArea.append("> Passenger Ship: " + as.getName() + " " + as.getIndex() + "\n");
		    }

		    else if (as instanceof CargoShip) {
			outputDataFileTextArea.append("> Cargo Ship: " + as.getName() + " " + as.getIndex() + "\n");
		    }
		}
		outputDataFileTextArea.append("\n");

		// Lists all persons at sea port
		outputDataFileTextArea.append(" --- List of all persons:  \n");
		for (Person per : seaPort.getPersons()) {

		    outputDataFileTextArea
			    .append("> Person: " + per.getName() + " " + per.getIndex() + " " + per.getSkill() + "\n");
		}
		outputDataFileTextArea.append("\n");
	    } // END OF if (sortSeaPortRadButton.isSelected())

	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("seaport") && namesJRad.isSelected()) {
		try {
		    sortSeaPortsByName();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("seaport") && indexesJRad.isSelected()) {
		try {
		    sortSeaPortsByIndex();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("dock") && namesJRad.isSelected()) {
		try {
		    sortDocksByName();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("dock") && indexesJRad.isSelected()) {
		try {
		    sortDocksByIndex();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("person") && namesJRad.isSelected()) {
		try {
		    sortPersonsByName();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("person") && indexesJRad.isSelected()) {
		try {
		    sortPersonsByIndex();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("ship") && namesJRad.isSelected()) {
		try {
		    sortShipsByName();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (enterSortCategoryTextField.getText().equalsIgnoreCase("ship") && indexesJRad.isSelected()) {
		try {
		    sortShipsByIndex();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }

	});

    } // END OF presentSortedWorld()

    /*
     * SeaPort, name Let's user decide to see sea ports sorted by name. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortSeaPortsByName() throws IOException {
	SeaPort.CompareSeaPortName compareSeaPortsByName = seaPort.new CompareSeaPortName();
	Collections.sort(world.getPorts(), compareSeaPortsByName);

	outputDataFileTextArea.append("--- All SeaPorts by name:  \n");
	for (SeaPort sp : world.getPorts()) {
	    outputDataFileTextArea.append("> " + sp.getName() + " " + sp.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * SeaPort, index Let's user decide to see sea ports sorted by index. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortSeaPortsByIndex() throws IOException {
	SeaPort.CompareSeaPortIndex compareSeaPortsByIndex = seaPort.new CompareSeaPortIndex();
	Collections.sort(world.getPorts(), compareSeaPortsByIndex);

	outputDataFileTextArea.append("--- All SeaPorts by index:  \n");
	for (SeaPort sp : world.getPorts()) {
	    outputDataFileTextArea.append("> " + sp.getName() + " " + sp.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Dock, name Let's user decide to see docks sorted by name. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortDocksByName() throws IOException {
	Dock.CompareDockName compareDocksByName = dock.new CompareDockName();
	Collections.sort(seaPort.getDocks(), compareDocksByName);

	outputDataFileTextArea.append("--- All Docks by name:  \n");
	for (Dock d : seaPort.getDocks()) {
	    outputDataFileTextArea.append("> " + d.getName() + " " + d.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Dock, index Let's user decide to see docks sorted by index. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortDocksByIndex() throws IOException {
	Dock.CompareDockIndex compareDocksByIndex = dock.new CompareDockIndex();
	Collections.sort(seaPort.getDocks(), compareDocksByIndex);

	outputDataFileTextArea.append("--- All Docks by index:  \n");
	for (Dock d : seaPort.getDocks()) {
	    outputDataFileTextArea.append("> " + d.getName() + " " + d.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Person, name Let's user decide to see persons sorted by name. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortPersonsByName() throws IOException {
	Person.ComparePersonName comparePersonsByName = person.new ComparePersonName();
	Collections.sort(seaPort.getPersons(), comparePersonsByName);

	outputDataFileTextArea.append("--- All Persons by name:  \n");
	for (Person p : seaPort.getPersons()) {
	    outputDataFileTextArea.append("> " + p.getName() + " " + p.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Person, index Let's user decide to see persons sorted by index. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortPersonsByIndex() throws IOException {
	Person.ComparePersonIndex comparePersonsByIndex = person.new ComparePersonIndex();
	Collections.sort(seaPort.getPersons(), comparePersonsByIndex);

	outputDataFileTextArea.append("--- All Persons by index:  \n");
	for (Person p : seaPort.getPersons()) {
	    outputDataFileTextArea.append("> " + p.getName() + " " + p.getIndex() + "\n");
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Ship, name Let's user decide to see ships sorted by name. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortShipsByName() throws IOException {
	Ship.CompareShipName compareShipByName = ship.new CompareShipName();
	Collections.sort(seaPort.getShips(), compareShipByName);

	outputDataFileTextArea.append("--- All Ships by name:  \n");
	for (Ship s : seaPort.getShips()) {
	    if (s instanceof PassengerShip) {
		outputDataFileTextArea.append("> Passenger Ship: " + s.getName() + " " + s.getIndex() + "\n");
	    } else if (s instanceof CargoShip) {
		outputDataFileTextArea.append("> Cargo Ship: " + s.getName() + " " + s.getIndex() + "\n");
	    }
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * Ship, index Let's user decide to see ships sorted by index. 
     * Needed for method: displayNamesAndIndexesSorted()
     */
    public void sortShipsByIndex() throws IOException {
	Ship.CompareShipIndex compareShipByIndex = ship.new CompareShipIndex();
	Collections.sort(seaPort.getShips(), compareShipByIndex);

	outputDataFileTextArea.append("--- All Ships by name:  \n");
	for (Ship s : seaPort.getShips()) {
	    if (s instanceof PassengerShip) {
		outputDataFileTextArea.append("> Passenger Ship: " + s.getName() + " " + s.getIndex() + "\n");
	    } else if (s instanceof CargoShip) {
		outputDataFileTextArea.append("> Cargo Ship: " + s.getName() + " " + s.getIndex() + "\n");
	    }
	}
	outputDataFileTextArea.append("\n");
    }

    /*
     * HashMap functions grouped
     */
    public void presentAllShipsInQue() {
	try // Creates hashmap of ships in que
	{
	    presentQueKeyCount(seaPort.getQue());
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	// Displays all ships in que from HashMap
	allShipsInQueButton.addActionListener(a -> {
	    displayAllShipsInQue();
	    // Places HashMap key count in appropriate text field
	    String keyNumber = "";

	    try {
		keyNumber = String.valueOf(presentQueKeyCount(seaPort.getQue()).size());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    
	    keyCountTextField.setText(keyNumber);

	    if (showSortJButton.isSelected() || sortSeaPortRadButton.isSelected() || displayWorldButton.isSelected()
		    || !seaPort.getShips().isEmpty()) {
		allShipsInQueButton.setEnabled(false);
	    }
	});
    }

    /*
     * Create ship que HashMap
     */
    public HashMap<Integer, Ship> presentQueKeyCount(List<Ship> sp) throws IOException {
	for (int i = 0; i < sp.size(); i++) {
	    seaPort.getShipMap().put(i, sp.get(i));
	    seaPort.getShipMap().get(i);
	}

	return seaPort.getShipMap();
    }

    /*
     * Displays all ships in que, from HashMap
     */
    public void displayAllShipsInQue() {
	seaPort.getShipMap().forEach((k, v) -> {
	    outputDataFileTextArea.append("Key " + k + ") " + v.getName() + " " + v.getIndex() + "\n");
	});
    }

    /*
     * Retrieve Ships in que by key in HashMap
     */
    public void getShipInQue(JTextField enter, JTextArea result) {
	Ship s = new Ship();

	if (seaPort.getShipMap().isEmpty()) {
	    JOptionPane.showMessageDialog(null, "Ship Que is empty.", "ATTENTION",
		    JOptionPane.INFORMATION_MESSAGE);
	} else if (!seaPort.getShipMap().isEmpty()) {
	    getShipButton.setEnabled(true);
	}

	for (int i : seaPort.getShipMap().keySet()) {
	    if (enter.getText().equals(String.valueOf(i))) {
		s = ship.getShipByIndex(i, seaPort.getShipMap());

		result.append("--- KEY " + String.valueOf(i) + " ---\n" + s.toString() + "\n");
		result.append("\n");
	    }
	}
    }

    /*
     * Get ships in que by key
     */
    public void getKeyShipInQue() {
	// Displays ship in que, by the key input
	getShipButton.addActionListener(g -> {
	    getShipInQue(enterKeyTextField, outputDataFileTextArea);
	});
    }

    /*
     * Sorts the ships in weight, length, width & draft
     */
    public void sortShipQueByDimensions(JTextField enter, JTextArea result) throws IOException {
	// Displays sorted que ships, by desired dimensions
	sortShipQueButton.addActionListener(sq -> {
	    if (enter.getText().equalsIgnoreCase("weight")) {
		Ship.CompareShipWeight compareShipsByWeight = ship.new CompareShipWeight();
		Collections.sort(seaPort.getQue(), compareShipsByWeight);

		result.append("--- Ships in que by weight ---  \n");
		for (Ship s : seaPort.getQue()) {
		    result.append("> " + s.toString() + "\n\n");
		}
	    }

	    else if (enter.getText().equalsIgnoreCase("length")) {
		Ship.CompareShipLength compareShipsByLength = ship.new CompareShipLength();
		Collections.sort(seaPort.getQue(), compareShipsByLength);

		result.append("--- Ships in que by length ---  \n");
		for (Ship s : seaPort.getQue()) {
		    result.append("> " + s.toString() + "\n\n");
		}
	    }

	    else if (enter.getText().equalsIgnoreCase("width")) {
		Ship.CompareShipWidth compareShipsByWidth = ship.new CompareShipWidth();
		Collections.sort(seaPort.getQue(), compareShipsByWidth);

		result.append("--- Ships in que by width ---  \n");
		for (Ship s : seaPort.getQue()) {
		    result.append("> " + s.toString() + "\n\n");
		}
	    }

	    else if (enter.getText().equalsIgnoreCase("draft")) {
		Ship.CompareShipDraft compareShipsByDraft = ship.new CompareShipDraft();
		Collections.sort(seaPort.getQue(), compareShipsByDraft);

		result.append("--- Ships in que by draft ---  \n");
		for (Ship s : seaPort.getQue()) {
		    result.append("> " + s.toString() + "\n\n");
		}
	    }
	    result.append("\n");
	});
    } // End of sortShipQueByDimensions(JTextField enter, JTextArea result) throws IOException 

    /*
     * Clear all Radio buttons
     */
    public void clearAllRadioButtons() {
	deselectRadBtnButton.addActionListener(c -> {
	    docksPersButtonGroup.clearSelection();
	});
    }

    /*
     * Let's user select data file
     */
    @Override
    public void selectDataFile() {
	dataFileButton.addActionListener(e -> {
	    // Initates JFileChooser
	    jfc.setCurrentDirectory(new File(System.getProperty(("user.home"))));
	    jfc.setDialogTitle("Data File of Sea Port");
	    jfc.showOpenDialog(this);

	    fileSelected = jfc.getSelectedFile();

	    if (fileSelected == null) {

	    } else {
		// Gets selected file
		dataFileTextField.setText(fileSelected.getAbsolutePath());
		displayWorldButton.setEnabled(true);

		if (!dataFileTextField.getText().contains(".txt")) {
		    JOptionPane.showMessageDialog(null, "File needs to be .txt", "ATTENTION",
			    JOptionPane.INFORMATION_MESSAGE);
		    displayWorldButton.setEnabled(false);
		} else {
		    // Ships in que button enabled with new file
		    allShipsInQueButton.setEnabled(true);

		    // Clears data for the next file
		    clearDataFromFile();

		    try {
			// Get's FIle title;
			recordWorld(fileSelected.getAbsolutePath());

			// Uses data from the file
			worldData();

		    } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		    }
		}
	    } // END OF if statement
	});
    } // END OF selectDataFile() 

    /*
     * MAIN METHOD
     */
    public static void main(String[] args) throws FileNotFoundException {
	// Start program
	SeaPortTrackerTwo startSP2 = new SeaPortTrackerTwo();
	startSP2.DisplaySeaPortProgram();

	// Actionlistener for JFileChooser
	// User needs to choose file first in order to use program correctly
	startSP2.selectDataFile();

	// When activity button is pressed
	// SHOWS WORLD ACTIVITY
	startSP2.displayWorldActivity();

	// SHOWS SORTED WORLD ACTIVITY
	startSP2.presentSortedWorld();

	// Hashmap Functions
	startSP2.presentAllShipsInQue();
	startSP2.getKeyShipInQue();

	try { // Presents ships in que, sorted
	    startSP2.sortShipQueByDimensions(startSP2.chooseShipDimensionsTextField, startSP2.outputDataFileTextArea);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Clears Data output
	// Let's user clear the output data
	startSP2.clearAllRadioButtons();

	// Clears Data output
	// Let's user clear the output data
	startSP2.clearOutput();
    }

}
