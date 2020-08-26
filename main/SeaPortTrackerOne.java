/** 
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports.
 *  FILE NAME - SeaPortProgram.java
 *  FILE PURPOSE - Contains GUI of Sea Port Program and action listener codes.
 */
/**@author Reynaldo Santos
 *  @since 9-13-2019
 */

package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import code.CargoShip;
import code.Dock;
import code.PassengerShip;
import code.Person;
import code.SeaPort;
import code.Ship;
import code.World;

public class SeaPortTrackerOne extends JFrame {
    /*
     * Fields
     */
    // Window layout
    protected Container contentPane = getContentPane();

    // Establishes Layout
    protected JPanel programFeaturesLayout; // Left side
    protected Box outputDataLayout = Box.createVerticalBox(); // Right side

    // Top layers
    protected Box dataFileTextFieldBox = Box.createHorizontalBox();
    protected JPanel dataFilePanel = new JPanel();
    protected JButton dataFileButton = new JButton("Select Data File");
    protected JLabel dataFileLabel = new JLabel("Data File: ");
    protected JTextField dataFileTextField = new JTextField(28);

    // Left side, Search World Panel
    protected Box searchWorldPanel = Box.createVerticalBox();
    protected Box searchPanelBox = Box.createVerticalBox();
    protected Box categoryNameIDBox = Box.createHorizontalBox();

    protected JPanel enterCategoryPanel = new JPanel();
    protected JTextField enterCategoryTextField = new JTextField(10);
    protected JPanel enterNameIDPanel = new JPanel();
    protected JTextField enterNameIDTextField = new JTextField(10);
    protected JPanel nameIDResultPanel = new JPanel();
    protected JButton getResultButton = new JButton("Get");
    protected JTextField nameIDResultTextField = new JTextField(15);

    // Right Layout
    protected JPanel showActivityPanel = new JPanel();
    protected JButton clearSortingBtn = new JButton("Clear");
    protected JButton displayWorldButton = new JButton("Display World");
    protected JTextArea outputDataFileTextArea = new JTextArea(30, 25);

    // JFileChooser
    protected JFileChooser jfc = new JFileChooser();
    protected File fileSelected = null;

    // Scanner
    protected Scanner sc;

    // Instances needed
    protected World world = new World();
    protected SeaPort seaPort = new SeaPort();

    /*
     * Constructors
     */
    public SeaPortTrackerOne() {
	// Set title & JFrame settings
	super("Sea Port Program");
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setSize(510, 500);
	setLocationRelativeTo(null);
	setResizable(false);

	/*
	 * Top Panel
	 */
	// Top Layout using Box outputDataLayout
	dataFileTextField.setEditable(false);
	dataFileLabel.setBorder(BorderFactory.createEtchedBorder());
	dataFileTextFieldBox.add(dataFileLabel);
	dataFileTextFieldBox.add(dataFileTextField);
	dataFilePanel.setBorder(BorderFactory.createRaisedBevelBorder());
	dataFilePanel.add(dataFileButton);
	dataFilePanel.add(dataFileTextFieldBox);

	/*
	 * LEFT LAYOUT DESIGN
	 */
	// Left layout established
	programFeaturesLayout = new JPanel(new BorderLayout());

	// Enter Category, Enter Name or ID
	enterCategoryPanel.setBorder(BorderFactory.createTitledBorder("Enter Category"));
	enterCategoryPanel.add(enterCategoryTextField);
	enterCategoryTextField.setToolTipText("SeaPort, Dock, Ship, Person");
	enterNameIDPanel.setBorder(BorderFactory.createTitledBorder("Enter Name or ID"));
	enterNameIDPanel.add(enterNameIDTextField);
	categoryNameIDBox.add(enterCategoryPanel);
	categoryNameIDBox.add(enterNameIDPanel);

	// Name/ID Result
	nameIDResultPanel.setBorder(BorderFactory.createTitledBorder("Name/ID Result"));
	nameIDResultTextField.setEditable(false);
	nameIDResultPanel.add(getResultButton);
	nameIDResultPanel.add(nameIDResultTextField);
	nameIDResultPanel.setPreferredSize(new Dimension(40, 60));

	// Enter Category, Enter Name or ID and
	// Name/ID Result added together in vertical box
	searchPanelBox.add(categoryNameIDBox);
	searchPanelBox.add(nameIDResultPanel);

	// searchPanelBox added to searchWorldPanel
	searchWorldPanel.add(searchPanelBox);
	searchWorldPanel.setBorder(BorderFactory.createTitledBorder("Search World"));
	searchWorldPanel.setPreferredSize(new Dimension(250, 145));

	programFeaturesLayout.add(searchWorldPanel, BorderLayout.NORTH);

	/*
	 * RIGHT LAYOUT DESIGN
	 */
	// Sets up output text area with vert and horiz scrollpane
	JScrollPane outputDataFileScrollPane = new JScrollPane(outputDataFileTextArea);
	outputDataFileScrollPane.getVerticalScrollBar().setUnitIncrement(20);
	outputDataFileScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	outputDataFileTextArea.setLineWrap(true);
	outputDataFileTextArea.setWrapStyleWord(true);
	outputDataFileTextArea.setEditable(false);

	// Add display world button and clear button together
	showActivityPanel.add(displayWorldButton);
	showActivityPanel.add(clearSortingBtn);
	showActivityPanel.setBorder(BorderFactory.createEtchedBorder());

	// Combines ShowActivityPanel and
	outputDataLayout.add(showActivityPanel);
	outputDataLayout.add(outputDataFileScrollPane);

	/*
	 *  All added to contentPane
	 */
	contentPane.add("North", dataFilePanel).setPreferredSize(new Dimension(0, 40));
	contentPane.add("Center", programFeaturesLayout);
	contentPane.add("East", outputDataLayout);

    }// End of SeaPortProgram constructor

    /*
     * Makes program visible
     */
    public void DisplaySeaPortProgram() {
	setVisible(true);
    }

    /*
     * File selected
     */
    public File getFileSelected() {
	return fileSelected;
    }

    /*
     * Record World
     */
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
		sc = new Scanner(fileName.replace(".txt", ""));
		world = new World(sc);
	    }
	}

	sc.close();

	return world;
    }

    /*
     * Project 1 Starts programs functions:
     * 
     * displayWorldActivity() { 
     *	presentWorldActivity() 
     * 	showNameIDResults() 
     * }
     */
    public void displayWorldActivity() throws FileNotFoundException {
	// Display World button
	displayWorldButton.addActionListener(sab -> {
	    /*
	     * SHOWS WORLD ACTIVITY
	     */
	    presentWorldActivity();

	    // To search name/ ID of ports, docks, ships, & persons
	    getResultButton.addActionListener(db -> {
		// Searches names and ID's
		showNameIDResults();
	    });
	});

    } // END OF method void displayWorldActivity()

    /*
     * Presents default file output
     */
    public void presentWorldActivity() {
	// File Title
	outputDataFileTextArea.append(">>>>> The " + world.getName() + ":\n\n");

	for (SeaPort sp : world.getPorts()) {
	    outputDataFileTextArea.append("--- SeaPort: " + sp.getName() + " " + sp.getIndex() + "\n");

	    for (Ship s : seaPort.getShips()) {
		for (Dock d : seaPort.getDocks()) {

		    if ((d.getIndex() == s.getParent()) && (d.getDockShipIndex() == s.getIndex())) {
			if (d.getParent() == sp.getIndex()) {

			    if (s instanceof PassengerShip) {
				outputDataFileTextArea.append("Dock: " + d.getName() + " " + d.getIndex() + "\n");
				outputDataFileTextArea
					.append("Passenger Ship: " + s.getName() + " " + s.getIndex() + "\n\n");
			    }

			    if (s instanceof CargoShip) {
				outputDataFileTextArea.append("Dock: " + d.getName() + " " + d.getIndex() + "\n");
				outputDataFileTextArea
					.append("Cargo Ship: " + s.getName() + " " + s.getIndex() + "\n\n");
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
		outputDataFileTextArea.append("> Passenger Ship: " + siq.getName() + " " + siq.getIndex() + "\n");
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

    }

    /*
     * Display dock's name or ID when user enters correct input
     */
    public void showNameIDResults() {
	for (SeaPort sp : world.getPorts()) {
	    if (enterCategoryTextField.getText().equalsIgnoreCase("SeaPort") && enterNameIDTextField.getText().equalsIgnoreCase(sp.getName())) {
		nameIDResultTextField.setText(String.valueOf(sp.getIndex()));
	    } 
	    else if (enterCategoryTextField.getText().equalsIgnoreCase("SeaPort") && enterNameIDTextField.getText().equalsIgnoreCase(String.valueOf(sp.getIndex()))) {
		nameIDResultTextField.setText(sp.getName());
	    }
	}

	for (Dock d : seaPort.getDocks()) {
	    if (enterCategoryTextField.getText().equalsIgnoreCase("Dock") && enterNameIDTextField.getText().equalsIgnoreCase(d.getName())) {
		nameIDResultTextField.setText(String.valueOf(d.getIndex()));
	    } 
	    else if (enterCategoryTextField.getText().equalsIgnoreCase("Dock") && enterNameIDTextField.getText().equalsIgnoreCase(String.valueOf(d.getIndex()))) {
		nameIDResultTextField.setText(d.getName());
	    }
	}

	for (Ship s : seaPort.getShips()) {
	    if (enterCategoryTextField.getText().equalsIgnoreCase("Ship") && enterNameIDTextField.getText().equalsIgnoreCase(s.getName())) {
		nameIDResultTextField.setText(String.valueOf(s.getIndex()));
	    } 
	    else if (enterCategoryTextField.getText().equalsIgnoreCase("Ship") && enterNameIDTextField.getText().equalsIgnoreCase(String.valueOf(s.getIndex()))) {
		nameIDResultTextField.setText(s.getName());
	    }
	}

	for (Person p : seaPort.getPersons()) {
	    if (enterCategoryTextField.getText().equalsIgnoreCase("Person") && enterNameIDTextField.getText().equalsIgnoreCase(p.getName())) {
		nameIDResultTextField.setText(String.valueOf(p.getIndex()));
	    } 
	    else if (enterCategoryTextField.getText().equalsIgnoreCase("Person") && enterNameIDTextField.getText().equalsIgnoreCase(String.valueOf(p.getIndex()))) {
		nameIDResultTextField.setText(p.getName());
	    }
	}
    } // END OF showNameIDResults()

    /*
     * Data Used for the program
     */
    public void worldData() throws FileNotFoundException {
	/* Provides data for the method */
	recordWorld(fileSelected.getAbsolutePath());
	world.setPorts(world.recordSeaPorts(fileSelected.getAbsolutePath()));
	seaPort.setDocks(seaPort.recordDocks(fileSelected.getAbsolutePath()));
	seaPort.setShips(seaPort.recordPassengerShips(fileSelected.getAbsolutePath()));
	seaPort.setShips(seaPort.recordCargoShips(fileSelected.getAbsolutePath()));
	seaPort.setPersons(seaPort.recordPersons(fileSelected.getAbsolutePath()));
    }

    /*
     * CLEARS DATA OF THE PROGRAM
     */
    public void clearDataFromFile() {
	while (!world.getPorts().isEmpty()) {
	    world.getPorts().removeAll(world.getPorts());
	}

	while (!seaPort.getDocks().isEmpty()) {
	    seaPort.getDocks().removeAll(seaPort.getDocks());
	}

	while (!seaPort.getShips().isEmpty()) {
	    seaPort.getShips().removeAll(seaPort.getShips());
	}

	while (!seaPort.getPersons().isEmpty()) {
	    seaPort.getPersons().removeAll(seaPort.getPersons());
	}

	while (!seaPort.getQue().isEmpty()) {
	    seaPort.getQue().removeAll(seaPort.getQue());
	}

	world.getPorts().trimToSize();
	seaPort.getDocks().trimToSize();
	seaPort.getShips().trimToSize();
	seaPort.getPersons().trimToSize();
	seaPort.getQue().trimToSize();
    }

    /*
     * Let's user select data file
     */
    public void selectDataFile() {
	dataFileButton.addActionListener(e -> {
	    // Initates JFileChooser
	    jfc.setCurrentDirectory(new File(System.getProperty(("user.home"))));
	    jfc.setDialogTitle("Data File of Sea Port");
	    jfc.showOpenDialog(this);

	    fileSelected = jfc.getSelectedFile();

	    if (fileSelected == null) {

	    } 
	    else {
		// Gets selected file
		dataFileTextField.setText(fileSelected.getAbsolutePath());
		displayWorldButton.setEnabled(true);

		if (!dataFileTextField.getText().contains(".txt")) {
		    JOptionPane.showMessageDialog(null, "File needs to be .txt", "ATTENTION",
			    JOptionPane.INFORMATION_MESSAGE);
		    displayWorldButton.setEnabled(false);
		} else {
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
	    } // END OF else code, from 'if (fileSelected == null)'
	});
    } // END OF selectDataFile()

    /*
     * Clears data output
     */
    public void clearOutput() {
	clearSortingBtn.addActionListener(c -> {
	    outputDataFileTextArea.selectAll();
	    outputDataFileTextArea.replaceSelection("");
	});
    }

    /*
     * MAIN METHOD
     */
    public static void main(String[] args) throws FileNotFoundException {
	// Start program
	SeaPortTrackerOne startSP = new SeaPortTrackerOne();
	startSP.DisplaySeaPortProgram();

	// Selects file
	startSP.selectDataFile();

	// When activity button is pressed
	// SHOWS WORLD ACTIVITY
	startSP.displayWorldActivity();

	// Clears Data output
	// Let's user clear the output data
	startSP.clearOutput();
    }
    
}
