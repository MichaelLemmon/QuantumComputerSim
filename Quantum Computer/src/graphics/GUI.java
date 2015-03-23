package graphics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import core.Qubit;
import core.Register;
import core.State;
import algorithm.Grover;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Dimension DEFAULT_SIZE = new Dimension(600, 230);
	public static final int MODIFIER_MINIMUM = 0;
	public static final int MODIFIER_MAXIMUM = 15;
	public static final int RESOLUTION_MINIMUM = 0;
	public static final int RESOLUTION_MAXIMUM = 15;
	private JFrame frame;

	// components - 12 total
	private JTextArea qubitsArea;
	private JTextArea searchIndexArea;
	private JButton startButton;
	private SimpleGraph plot; // TODO update to pre-built package if need be

	private JLabel xAxisLabel;
	private JLabel yAxisLabel;
	private JLabel qubitsLabel;
	private JLabel searchIndexLabel;
	private JLabel yMaxLabel;
	private JLabel yMinLabel;
	private JLabel xMinLabel;
	private JLabel xMaxLabel;

	// component locations
	private Point qubitsAreaLocation;
	private Point searchIndexAreaLocation;
	private Point startButtonLocation;
	private Point plotLocation;

	private Point xAxisLabelLocation;
	private Point yAxisLabelLocation;
	private Point qubitsLabelLocation;
	private Point searchIndexLabelLocation;
	private Point yMaxLabelLocation;
	private Point yMinLabelLocation;
	private Point xMinLabelLocation;
	private Point xMaxLabelLocation;

	/**
	 * Constructor
	 */
	public GUI() {
		super();
		setSize(DEFAULT_SIZE);
		initialise();
	}

	/**
	 * Resets component to default state
	 */
	public void reset() {
		// TODO
	}

	/**
	 * Returns parent frame
	 * 
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Sets parent frame
	 * 
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Builds the GUI
	 */
	private void initialise() {
		initComponents();
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ensures file handlers and running threads are terminated properly.
	 */
	protected void exit() {
		onExit();
		System.exit(0);
	}

	private void onExit() {
		// Do nothing for now
	}

	/**
	 * Builds components to a state where they can be added to the Panel, then
	 * adds them.
	 */
	private void initComponents() {
		setLayout(null);
		buildComponents();
		calculateComponentLocations();
		updateComponentLocations();
		addComponents();
	}

	/**
	 * Adds each component to the container.
	 */
	private void addComponents() {
		add(qubitsArea);
		add(searchIndexArea);
		add(startButton);
		add(plot);
		add(qubitsLabel);
		add(searchIndexLabel);
		add(xAxisLabel);
		add(yAxisLabel);
		add(xMaxLabel);
		add(xMinLabel);
		add(yMaxLabel);
		add(yMinLabel);
	}

	private void updateComponentLocations() {
		qubitsArea.setLocation(qubitsAreaLocation);
		searchIndexArea.setLocation(searchIndexAreaLocation);
		startButton.setLocation(startButtonLocation);
		plot.setLocation(plotLocation);
		qubitsLabel.setLocation(qubitsLabelLocation);
		searchIndexLabel.setLocation(searchIndexLabelLocation);
		xAxisLabel.setLocation(xAxisLabelLocation);
		yAxisLabel.setLocation(yAxisLabelLocation);
		xMaxLabel.setLocation(xMaxLabelLocation);
		xMinLabel.setLocation(xMinLabelLocation);
		yMaxLabel.setLocation(yMaxLabelLocation);
		yMinLabel.setLocation(yMinLabelLocation);
	}

	/**
	 * Determines the locations for each component.
	 */
	private void calculateComponentLocations() {
		qubitsAreaLocation = new Point(150, 50); //
		searchIndexAreaLocation = new Point(150, 10); //
		startButtonLocation = new Point(50, 120); //
		plotLocation = new Point(240, 10);
		qubitsLabelLocation = new Point(10, 50); //
		searchIndexLabelLocation = new Point(30, 10);
		xAxisLabelLocation = new Point(360, 165);
		yAxisLabelLocation = new Point(215, 65);
		yMaxLabelLocation = new Point(230, 0);
		yMinLabelLocation = new Point(230, 138);
		xMaxLabelLocation = new Point(560, 150);
		xMinLabelLocation = new Point(238, 150);

	}

	/**
	 * Constructs each component and sets up the contents of each component.
	 */
	private void buildComponents() {
		qubitsArea = new JTextArea();
		qubitsArea.setSize(50, 30);
		qubitsArea.setText("6");

		searchIndexArea = new JTextArea();
		searchIndexArea.setSize(50, 30);
		searchIndexArea.setText("4");

		startButton = new JButton("Start");
		startButton.setSize(100, 30); //
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		int graphMin = 0;
		int graphMax = 1;
		int numberOfPoints = 64;
		plot = new SimpleGraph(numberOfPoints, graphMin, graphMax);
		plot.setSize(330, 145); // IRRELEVANT, WILL BE RESET ON START()

		xAxisLabel = new JLabel("Step Number");
		xAxisLabel.setSize(200, 30);

		yAxisLabel = new JLabel("P");
		yAxisLabel.setSize(200, 30);

		xMaxLabel = new JLabel("6"); // number of default simulation
		xMaxLabel.setSize(200, 30);

		xMinLabel = new JLabel("0");
		xMinLabel.setSize(200, 30);

		yMaxLabel = new JLabel("1");
		yMaxLabel.setSize(200, 30);

		yMinLabel = new JLabel("0");
		yMinLabel.setSize(200, 30);

		qubitsLabel = new JLabel("Number of Qubits");
		qubitsLabel.setSize(200, 30);

		searchIndexLabel = new JLabel("Search Index:");
		searchIndexLabel.setSize(200, 20);
	}

	/**
	 * Begins running a Grover simulation with the inputs from the appropriate
	 * Text Areas. Updates Graph component with the results of the simulation.
	 */
	protected void start() {
		boolean valid = checkArgumentValidity();

		if (valid) {
			if (frame != null) {
				frame.setTitle("Running");
			}
			int numberOfQubits = Integer.parseInt(qubitsArea.getText());
			int searchIndex = Integer.parseInt(searchIndexArea.getText());

			Qubit[] qubits = new Qubit[numberOfQubits];
			for (int i = 0; i < numberOfQubits; i++) {
				qubits[i] = new Qubit(new State(0));
			}

			Register testR = new Register(qubits);
			Grover g = new Grover(testR, searchIndex);
			testR = g.act();
			// System.out.println(testR);
			// System.out.println(testR.getProb(searchIndex));
			// double[] values = g.getValues();
			// plot.setValues(values);
			double[] values = g.getValues();
			plot.setValues(values);
			String stepCountString = "" + values.length;
			xMaxLabel.setText(stepCountString);
			repaint();

			if (frame != null) {
				frame.setTitle("");
			}

		} else {
			System.out.println("invalid");
		}
	}

	/**
	 * Returns true if the limit is less than 2^n, where n is the number of
	 * qubits.
	 * 
	 * @return
	 */
	private boolean checkArgumentValidity() {
		int numberOfQubits = Integer.parseInt(qubitsArea.getText());
		int searchIndex = Integer.parseInt(searchIndexArea.getText());

		int limit = (int) Math.pow(2, numberOfQubits);
		if (searchIndex > limit) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		GUI panel = new GUI();
		Dimension size = GUI.DEFAULT_SIZE;

		frame.setSize(size);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		panel.setFrame(frame);
		frame.setVisible(true);
	}
}
