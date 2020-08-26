/** --- Project 3 of CMSC 335 ---
 *  Program Name: Sea Port Program
 *  Program Purpose: Read data files of different sea ports. 
 *  Use HashMaps, Comparable, & Comparator to help sort the data.
 *  
 *  FILE NAME - SeaPortProgramThree.java
 *  FILE PURPOSE - Contains GUI of Sea Port Program and listener codes to output.
 
/** @author Reynaldo Santos
 *  @since 10-12-2019
 */


package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import code.CargoShip;
import code.Dock;
import code.PassengerShip;
import code.Person;
import code.SeaPort;
import code.Ship;
import code.World;

public class SeaPortTrackerMain extends SeaPortTrackerTwo {
    /*
     * Fields
     */
    // World Jtree combo
    protected DefaultMutableTreeNode worldTreeNode = new DefaultMutableTreeNode("World");
    protected JTree worldTree = new JTree(worldTreeNode);
    protected JScrollPane worldTreeNodeScrollPane = new JScrollPane(worldTree);
    protected JPanel makeWorldTreePanel = new JPanel();

    // Info text box, toString out put
    protected JTextArea infoBoxTextArea = new JTextArea();
    protected JScrollPane infoScrollPane = new JScrollPane(infoBoxTextArea);
    protected JButton clearInfoButton = new JButton("Clear Info");
    protected Box infoBox = Box.createVerticalBox();
    protected JPanel infoPanel = new JPanel();

    // Center main panel
    protected JPanel treePanel = new JPanel();

    // Tree Nodes
    protected DefaultMutableTreeNode seaPortTreeNode;
    protected DefaultMutableTreeNode workForceTreeNode;
    protected DefaultMutableTreeNode personTreeNode;
    protected DefaultMutableTreeNode dockTreeNode;
    protected DefaultMutableTreeNode shipTreeNode;
    protected DefaultMutableTreeNode shipsInQueTreeNode;

    /*
     * Constructors
     */
    public SeaPortTrackerMain() {
	// Sets title & JFrame settings
	setTitle("Sea Port Program 3");
	setSize(1140, 555);
	setLocation(25, 30);
	setResizable(false);

	/*
	 * Program 3, Center LAYOUT DESIGN
	 */

	// World tree ScrollPane bar setup. Added to makeWorldTreePanel
	worldTreeNodeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	worldTreeNodeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	worldTreeNodeScrollPane.setPreferredSize(new Dimension(275, 300));
	worldTreeNodeScrollPane.setBorder(BorderFactory.createEtchedBorder());
	makeWorldTreePanel.setBorder(BorderFactory.createTitledBorder("World Tree"));
	makeWorldTreePanel.add(worldTreeNodeScrollPane);

	// Info ScrollPane setup, added to infoPanel
	infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	infoScrollPane.setPreferredSize(new Dimension(275, 275));
	infoScrollPane.setBorder(BorderFactory.createTitledBorder("Info: "));
	infoPanel.add(infoScrollPane);

	// Combine info button and generate world tree button
	infoBox.setPreferredSize(new Dimension(275, 310));	
	infoBox.add(infoPanel);
	infoBox.add(clearInfoButton);

	// Both scroll panes added to panel
	makeWorldTreePanel.add(infoBox);

	// Center layout, established
	treePanel.add(makeWorldTreePanel, BorderLayout.EAST);
	treePanel.add(infoBox, BorderLayout.WEST);

	// Added to contentPane
	contentPane.add("North", dataFilePanel).setPreferredSize(new Dimension(0, 40));
	contentPane.add("West", treePanel);
	contentPane.add("Center", programFeaturesLayout);
	contentPane.add("East", outputDataLayout).setPreferredSize(new Dimension(300, 375));

    }// End of SeaPortProgramThree constructor

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
     * Methods for this program. 
     * Generates and presents world tree.
     * 
     * presentWorldTree() 
     * resetWorldTree() 
     * displayNodeTreeInfo()
     * clearInfoBoxTextArea()
     * 
     */
    public void presentWorldTree() throws FileNotFoundException {
	worldTree.addTreeSelectionListener(g -> {
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

	    // Sets JTree name
	    worldTreeNode.setUserObject(world.getName());

	    // Constructs JTree
	    for (SeaPort sp : world.getPorts()) {
		seaPortTreeNode = new DefaultMutableTreeNode("SeaPort: " + sp.getName() + ", " + sp.getIndex());
		worldTreeNode.add(seaPortTreeNode);

		shipsInQueTreeNode = new DefaultMutableTreeNode("Ships in que");
		workForceTreeNode = new DefaultMutableTreeNode("Personnel");

		for (Person p : seaPort.getPersons()) {
		    if (p.getParent() == sp.getIndex()) {
			personTreeNode = new DefaultMutableTreeNode("Person: " + p.getName() + ", " + p.getIndex());
			workForceTreeNode.add(personTreeNode);
		    }
		}

		for (Ship s1 : seaPort.getShips()) {
		    if (s1.getParent() == sp.getIndex()) {
			if (s1 instanceof PassengerShip) {
			    shipTreeNode = new DefaultMutableTreeNode("Passenger Ship: " + s1.getName() + ", " + s1.getIndex());
			    shipsInQueTreeNode.add(shipTreeNode);
			}

			else if (s1 instanceof CargoShip) {
			    shipTreeNode = new DefaultMutableTreeNode("Cargo Ship: " + s1.getName() + ", " + s1.getIndex());
			    shipsInQueTreeNode.add(shipTreeNode);
			}
		    }
		}

		for (Dock d : seaPort.getDocks()) {
		    if (d.getParent() == sp.getIndex()) {
			for (Ship s : seaPort.getShips()) {
			    if (s.getParent() == d.getIndex()) {
				if (s instanceof PassengerShip) {
				    dockTreeNode = new DefaultMutableTreeNode("Dock: " + d.getName() + ", " + d.getIndex());
				    seaPortTreeNode.add(dockTreeNode);
				    shipTreeNode = new DefaultMutableTreeNode("Passenger Ship: " + s.getName() + ", " + s.getIndex());
				    dockTreeNode.add(shipTreeNode);
				}

				else if (s instanceof CargoShip) {
				    dockTreeNode = new DefaultMutableTreeNode("Dock: " + d.getName() + ", " + d.getIndex());
				    seaPortTreeNode.add(dockTreeNode);
				    shipTreeNode = new DefaultMutableTreeNode("Cargo Ship: " + s.getName() + " " + s.getIndex());
				    dockTreeNode.add(shipTreeNode);
				}
			    }
			}
		    }
		}
		
		seaPortTreeNode.add(workForceTreeNode);
		seaPortTreeNode.add(shipsInQueTreeNode);
		
	    } // END OF for (SeaPort sp : world.getPorts()) 
	});

    }

    /*
     * Display info on info text box
     */
    public void displayNodeTreeInfo() {
	worldTree.getSelectionModel().addTreeSelectionListener(x -> {
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) worldTree.getLastSelectedPathComponent();

	    // If user selects ship node
	    for (Ship s : seaPort.getShips()) {
		if (selectedNode.getUserObject().toString().contains(s.getName())) {
		    infoBoxTextArea.append(s.toString() + "\n\n");
		}
	    }

	    // If user selects person node
	    for (Person p : seaPort.getPersons()) {
		if (selectedNode.getUserObject().toString().contains(p.getName())
			&& selectedNode.getUserObject().toString().contains(String.valueOf(p.getIndex()))) {
		    infoBoxTextArea.append(p.toString() + "\n");
		}
	    }

	});
    }

    /*
     * Clears world tree
     */
    public void resetWorldTree() {
	DefaultTreeModel model = (DefaultTreeModel) worldTree.getModel();
	DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
	root.removeAllChildren();
	model.reload();
    }

    /*
     * Clear output info
     */
    public void clearInfoBoxTextArea() {
	clearInfoButton.addActionListener(c -> {
	    infoBoxTextArea.selectAll();
	    infoBoxTextArea.replaceSelection("");
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
		} 
		else {
		    // Clears data for the next file
		    clearDataFromFile();

		    // Resets the world tree
		    resetWorldTree();

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
    }

    /*
     * MAIN METHOD
     */
    public static void main(String[] args) throws FileNotFoundException {
	// Start program
	SeaPortTrackerMain startSP3 = new SeaPortTrackerMain();
	startSP3.DisplaySeaPortProgram();

	// Actionlistener for JFileChooser
	// User needs to choose file first in order to use program correctly
	startSP3.selectDataFile();

	// When activity button is pressed
	// SHOWS WORLD ACTIVITY
	startSP3.displayWorldActivity();

	// SHOWS SORTED WORLD ACTIVITY
	startSP3.presentSortedWorld();

	// Hashmap Functions
	startSP3.presentAllShipsInQue();
	startSP3.getKeyShipInQue();

	try { // Presents ships in que, sorted
	    startSP3.sortShipQueByDimensions(startSP3.chooseShipDimensionsTextField, startSP3.outputDataFileTextArea);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// DISPLAYS WORLD TREE
	startSP3.presentWorldTree();

	// Displays node info
	startSP3.displayNodeTreeInfo();

	// Clears Data output
	// Let's user clear the output data
	startSP3.clearAllRadioButtons();

	// CLears info box related to the World Tree
	startSP3.clearInfoBoxTextArea();

	// Clears Data output
	// Let's user clear the output data
	startSP3.clearOutput();
    }

}
