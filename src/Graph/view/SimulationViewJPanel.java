package Graph.view;

import Graph.model.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.lang.Math;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SimulationViewJPanel extends JPanel implements SimulationView {

	private SimulationModel model = null;

	private int margin_x;
	private int margin_y;
	private int min_size;  // minimum between window size and height
	private double scale = 1.0;
	private boolean drawLinks = true;

	public SimulationViewJPanel(SimulationModel model) {
		this.model = model;
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				searchNodeInformation(x,y);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
		});
		// we add a button to choose display parameters
		this.setLayout(null);
		JButton button = new JButton("Draw links");
		button.setBounds(5,5,100,20);
		this.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				drawLinks = ! drawLinks;
			}
		});
	}

	public void updateDisplay() {
		this.updateUI();
	}
	@Override
	public void paintComponent(Graphics g) {
		// on caste l'objet Graphics en Graphics2D car plus de fonctionnalités
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setRenderingHints(Graphics2D.ANTIALIASING,Graphics2D.ANTIALIAS_ON);

		// we clear the background
		Color backgroundColor = Color.decode("#000000");
		g2d.setBackground(backgroundColor);
		g2d.clearRect(0,0,this.getWidth(),this.getHeight());

		// we compute the ratio for the display
		computeMargin();

		// we paint the objects
		if (drawLinks == true)
			paintLinks(g2d);
		paintNodes(g2d);
	}
	private void paintNodes(Graphics2D g) {
		for (Node node : model.getNodes()) {
			Color color = node.getColor();
			double diameter = convertNodeDiameterToPixel(node);
			// we draw the circle:
			g.setColor(color);
			g.fillOval((int)(convertNodePositionToPixelX(node) -diameter/2), 
			           (int)(convertNodePositionToPixelY(node) -diameter/2), 
			           (int)diameter, (int)diameter);
			// we draw the node number inside the circle
			g.setColor(Color.BLACK);
			int n = node.getNodeNumber(); int string_offset;
			if (n<10) // one digit number
				string_offset = -4;
			else if (n>= 10 && n<100) // two digit number
				string_offset = -7;
			else // 3 or more digits number
				string_offset = -10;
			g.drawString(Integer.toString(n),
			             (int)(convertNodePositionToPixelX(node) +string_offset),
			             (int)(convertNodePositionToPixelY(node) +4));
		}

	}
	private void paintLinks(Graphics2D g) {
		g.setStroke(new BasicStroke(2)); // to draw thicker lines
		for (Link link : model.getLinks()) {
			Color color = link.getColor();
			g.setColor(color);
			Node node1 = link.getStartNode();
			Node node2 = link.getEndNode();
			g.drawLine((int)(convertNodePositionToPixelX(node1)), 
			           (int)(convertNodePositionToPixelY(node1)), 
			           (int)(convertNodePositionToPixelX(node2)), 
			           (int)(convertNodePositionToPixelY(node2)));
		}
	}

	// computeMargin permet de trouver la position du plus grand carré
	// qui tient à l'intérieur de la fenêtre (celle-ci étant un rectangle)
	private void computeMargin() {
		int width = this.getWidth();
		int height = this.getHeight();

		if (width > height) {
			this.min_size = height;
			this.margin_x = (width-height)/2;
			this.margin_y = 0;
		}
		else {
			this.min_size = width;
			this.margin_x = 0;
			this.margin_y = (height-width)/2;
		}
	}

	// the values used for the simulation and stored in the node class are NOT pixel values
	// because the simulation should not depend on the size of the window
	// so the following functions convert these values to pixels
	// these functions return the position of the center of the node
	private double convertNodePositionToPixelX(Node node) {
		return margin_x+node.pos_x*min_size;
	}
	private double convertNodePositionToPixelY(Node node) {
		return margin_y+node.pos_y*min_size;
	}
	private double convertNodeDiameterToPixel(Node node) {
		return min_size*node.getDiameter();
	}

	// to search informations about a node when we click on him
	private void searchNodeInformation(int x, int y) {
		Node n = null;
		// we search into the list of nodes if one of them is where the user clicked
		for (Node node : model.getNodes()) {
			double X = convertNodePositionToPixelX(node);
			double Y = convertNodePositionToPixelY(node);
			double gap_x = X - x;
			double gap_y = Y - y;
			double distance = Math.sqrt(gap_x*gap_x + gap_y*gap_y);
			if (distance <= convertNodeDiameterToPixel(node)/2) {
				n = node;
				break;
			}
		}
		if (n == null)
			return;
		// we open a popup with node information
		JLabel label = new JLabel("File name: " + n.getFileName());
		JFrame frame = new JFrame();
		frame.setTitle("File information");
		frame.setMinimumSize(new Dimension(500,100));
		frame.setVisible(true);
		frame.add(label);
		frame.requestFocus();
	}
}
