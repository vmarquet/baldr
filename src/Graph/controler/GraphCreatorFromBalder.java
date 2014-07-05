package Graph.controler;

import Graph.model.SimulationModel;
import Graph.model.Node;
import Graph.model.Link;
import java.io.*;
import java.util.Scanner;
import java.awt.Color;

public class GraphCreatorFromBalder {

	// the table given as argument is the table of the match score
	// between files found by Baldr
	public GraphCreatorFromBalder(float[][] tab, SimulationModel model) {
		// for each file, we create a node
		for (int i=0; i<tab.length; i++) {
			Node node = new Node(i);
			model.addNode(node);

			// we set it's departure position randomly
			node.pos_x = Math.random();
			node.pos_y = Math.random();

			// we search for the color to give to the node, proportionnate to
			// the minimum of the score between this file and other files
			// if a student cheated, and his file is close to another student file,
			// his node's color will be red
			// if his file is not close to any other file, it will be green

			// we get the minimum of the scores between this file and other files:
			float coef_min = 10;
			for (int j=0; j<i+1; j++) {
				if (i==j)
					continue;
				if (tab[i][j] < coef_min)
					coef_min = tab[i][j];
			}

			Color color = convertCoefToColor(coef_min);
			node.setColor(color);
		}

		// for each node, we create a link with every other node (file)
		// proportionnate to the distance between the files
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<i; j++) {
				Link link = new Link(i,j,model);
				model.addLink(link);

				link.setLength((double)tab[i][j]);

				// we set the link color, depending on how the files are close
				Color color = convertCoefToColor(tab[i][j]);
				link.setColor(color);
			} 
		}

	}


	// this function converts the match score to a color
	// exemple: 0 => red    5.0 => yellow    10.0 => green
	private Color convertCoefToColor(float coef) {
		int R, G, B;
		if (coef >= 0.0 && coef <= 5.0) {
			R = 255;
			G = (int)(255*(coef/10.0));
			B = 0;
		}
		else if (coef > 5.0 && coef < 10.0) {
			R = (int)(255*(1-(coef/10.0)));
			G = 255;
			B = 0;
		}
		else {
			// default: white (the background is black)
			R = 255;
			G = 255;
			B = 255;
		}
		Color color = new Color(R,G,B);
		return color;
	}
}
