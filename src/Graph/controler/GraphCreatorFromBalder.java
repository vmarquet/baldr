package Graph.controler;

import Graph.model.SimulationModel;
import Graph.model.Node;
import Graph.model.Link;
import java.io.*;
import java.util.Scanner;
import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class GraphCreatorFromBalder {

	// the table given as argument is the table of the match score
	// between files found by Baldr
	public GraphCreatorFromBalder(float[][] tab, File[] files, SimulationModel model) {
		// we search min and max match values, we need them to compute the colors
		float min = 10;
		float max = 0;
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length-1; j++) {
				if (tab[i][j] < min)
					min = tab[i][j];
				if (tab[i][j] > max)
					max = tab[i][j];
			}
		}

		// for each file, we create a node
		for (int i=0; i<tab.length; i++) {
			Node node = new Node(i, files[i].getName());
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
			float coef_min = 10; float coef;
			for (int j=0; j<tab.length; j++) {
				if (i==j)
					continue;
				if (j > i)
					coef = tab[j][i];
				else
					coef = tab[i][j];
				if (coef < coef_min)
					coef_min = coef;
			}

			Color color = convertCoefToColor(coef_min, min, max);
			node.setColor(color);
		}

		// for each node, we create a link with every other node (file)
		// proportionnate to the distance between the files
		ArrayList<Link> links = new ArrayList<Link>();
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length-1; j++) {
				Link link = new Link(i,j,model);
				links.add(link);

				link.setLength((double)tab[i][j]);

				// we set the link color, depending on how the files are close
				Color color = convertCoefToColor(tab[i][j], min, max);
				link.setColor(color);
			} 
		}

		// we sort links from greatest length to smallest length
		// because we want links with smallest length to be draw last
		// because then it's easier to see which files are close
		Collections.sort(links, new Comparator<Link>() {
			@Override
			public int compare(Link link1, Link link2) {
				if (link1.getLength() == link2.getLength())
					return 0;
				else if (link1.getLength() > link2.getLength())
					return -1;
				else
					return 1;
			}
		});

		// we add the links to the model
		model.setLinks(links);
	}


	// this function converts the match score to a color
	// exemple: 0 => red    5.0 => yellow    10.0 => green
	private Color convertCoefToColor(float coef, float min, float max) {
		float H, S = 0.5F, B = 1.0F;  // Hue Saturation Brightness
		H = 0.37F * (coef - min) / (max - min);
		return Color.getHSBColor(H,S,B);
	}
}
