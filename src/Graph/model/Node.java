package Graph.model;

import java.awt.Color;

public class Node {

	private int number;   // starts from 0
	private static int totalNodeNumber = 0;

	public double pos_x = 0;  // position
	public double pos_y = 0;
	public double vit_x = 0;  // speed
	public double vit_y = 0;
	public double acc_x = 0;  // acceleration
	public double acc_y = 0;
	public double for_x = 0;  // force
	public double for_y = 0;	

	// physic simulation: a node is a mass:
	private double mass = 1.0;

	private Color color = Color.WHITE;
	private double diameter = 0.04;

	// constructors:
	// if node number < 0, automatic numerotation
	public Node(int number) {
		setNodeNumber(number);
	}
	public Node(int number, double X, double Y) {
		setNodeNumber(number);
		setPosition(X,Y);
	}
	public Node(int number, double X, double Y, double mass) {
		setNodeNumber(number);
		setPosition(X,Y);
		this.mass = mass;
	}
	private void setNodeNumber(int number) {
		if (number < 0)  // automatic numerotation
			this.number = totalNodeNumber;
		else
			this.number = number;
		this.totalNodeNumber++;
	}

	// setters:
	public void setPosition(double X, double Y) {
		this.pos_x = X;
		this.pos_y = Y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public static void resetTotalNodeNumber() {
		totalNodeNumber = 0;
	}

	// getters:
	public int getNodeNumber() {
		return this.number;
	}
	public double getMass() {
		return this.mass;
	}
	public Color getColor() {
		return this.color;
	}
	public double getDiameter() {
		return this.diameter;
	}
}
