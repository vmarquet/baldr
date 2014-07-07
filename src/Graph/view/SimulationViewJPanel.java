package Graph.view;

import Graph.model.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class SimulationViewJPanel extends JPanel implements SimulationView {

	private SimulationModel model = null;

	private int margin_x;
	private int margin_y;
	private int min_size;

	public SimulationViewJPanel(SimulationModel model) {
		this.model = model;
	}

	public void updateDisplay() {
		this.updateUI();
		// this.update();
		// this.repaint();
		// this.revalidate();
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
		computeMargin(g2d);

		// we paint the objetcs
		paintLinks(g2d);
		paintNodes(g2d);
		//paintForce(g2d); // debug
	}
	private void paintNodes(Graphics2D g) {
		for (Node node : model.getNodes()) {
			Color color = node.getColor();
			double diameter = min_size*node.getDiameter();
			// we draw the circle:
			g.setColor(color);
			g.fillOval((int)(margin_x+node.pos_x*min_size-diameter/2), 
			           (int)(margin_y+node.pos_y*min_size-diameter/2), 
			           (int)diameter, (int)diameter);
			// we draw the text (node number / ...)
			g.setColor(Color.BLACK);
			int n = node.getNodeNumber(); int offset;
			if (n<10)
				offset = -4;
			else
				offset = -7;
			g.drawString(Integer.toString(n),
			             (int)(margin_x+node.pos_x*min_size +offset),
			             (int)(margin_y+node.pos_y*min_size +4));
		}

	}
	private void paintLinks(Graphics2D g) {
		for (Link link : model.getLinks()) {
			Color color = link.getColor();
			g.setColor(color);
			Node node1 = link.getStartNode();
			Node node2 = link.getEndNode();
			g.drawLine((int)(margin_x+node1.pos_x*min_size), 
			           (int)(margin_y+node1.pos_y*min_size), 
			           (int)(margin_x+node2.pos_x*min_size), 
			           (int)(margin_y+node2.pos_y*min_size));
		}
	}
	// debug:
	private void paintForce(Graphics2D g) {
		for (Node node : model.getNodes()) {
			g.setColor(Color.BLUE);
			g.drawLine((int)(margin_x+node.pos_x*min_size), 
			           (int)(margin_y+node.pos_y*min_size), 
			           (int)(margin_x+(node.pos_x+node.for_x)*min_size), 
			           (int)(margin_y+(node.pos_y+node.for_y)*min_size));
		}
	}

	public void computeMargin(Graphics2D g) {
		int width = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.decode("#111111"));

		if (width > height) {
			this.min_size = height;
			this.margin_x = (width-height)/2;
			this.margin_y = 0;
			// g.fillRect(0,0,margin_x,height);
			// g.fillRect(width-margin_x,0,margin_x,height);
		}
		else {
			this.min_size = width;
			this.margin_x = 0;
			this.margin_y = (height-width)/2;
			// g.fillRect(0,0,width,margin_y);
			// g.fillRect(0,height-margin_y,width,margin_y);
		}

	}

}
