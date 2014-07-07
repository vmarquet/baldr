package Graph.controler;

import Graph.model.*;
import Graph.view.*;

import java.util.*; // pour les math

// this class is the heart of the physics simulation
// it's where all the calculation is made
// "implements runnable" allows to create a thread

public class SimulationControler implements Runnable {

	private final static int timeStep = 50;  // milliseconds
	// when using timeStep during equations, timeStep should be in seconds
	// so use (this.timeStep/1000.0)
	// WARNING: if you use 1000 and not 1000.0, the result is false

	private SimulationModel model = null;
	private SimulationView view = null;

	public SimulationControler(SimulationView view, SimulationModel model) {
		this.model = model;
		this.view = view;
	}

	// threaded function, the heart of the physics simulation
	public void run() {
		try {
			// initialization
			boolean wantToQuit = false;

			// main loop
			while ( ! wantToQuit ) {

				// on remet la somme des forces à zéro:
				for (Node node : model.getNodes()) {
					node.for_x = 0;
					node.for_y = 0;
				}

				// Force du ressort: f = -k(x-lrepos)
				// on parcourt la liste des liens
				for (Link link : model.getLinks()) {
					// on récupère les noeuds de chaque bout du lien
					Node node_start = link.getStartNode();
					Node node_end   = link.getEndNode();

					// on récupère la distance entre les deux noeuds:
					double distance = link.getDistance();

					// pour éviter de diviser par 0:
					if (distance <= 0)
						continue;

					// on en déduit la force:
					// négatif = ressort étiré, positif = ressort contracté
					double lrepos = link.getLength();
					double k = link.getRigidity();
					double f = -k*(distance-lrepos);
					link.applyForceBetweenNodes(f);
				}

				// force de frottement fluide: f = -lambda*v
				double lambda = model.getLambda();
				for (Node node : model.getNodes()) {
					node.for_x += -lambda*node.vit_x;
					node.for_y += -lambda*node.vit_y;
				}

				// Accélération: m*a = somme(forces) => a = somme(forces)/m
				for (Node node : model.getNodes()) {
					double m = node.getMass();
					node.acc_x = node.for_x*(1/m);
					node.acc_y = node.for_y*(1/m);
				}

				// Pour calculer la vitesse et la position
				// on utilise la méthode de Newton

				// Vitesse: a = dv/dt => dv = a*dt
				for (Node node : model.getNodes()) {
					node.vit_x += node.acc_x*(this.timeStep/1000.0);
					node.vit_y += node.acc_y*(this.timeStep/1000.0);
				}

				// Position: v = dx/dt => dx = v*dt
				for (Node node : model.getNodes()) {
					node.pos_x += node.vit_x*(this.timeStep/1000.0);
					node.pos_y += node.vit_y*(this.timeStep/1000.0);
				}


				// we resize the graph, so that the maximum difference of nodes positions
				// on x axis is 1 and same thing for y axis
				// after, we center the graph around the point (0.5, 0.5)
				// so all node positions will be >= 0 and <= 1
				// after that, it's very easy to compute pixels positions
				// even if the user resizes the window
				double min_x = 0.5, max_x = 0.5;
				double min_y = 0.5, max_y = 0.5;
				for (Node node : model.getNodes()) {
					if (node.pos_x < min_x)
						min_x = node.pos_x;
					if (node.pos_x > max_x)
						max_x = node.pos_x;
					if (node.pos_y < min_y)
						min_y = node.pos_y;
					if (node.pos_y > max_y)
						max_y = node.pos_y;
				}
				double diff_x = max_x - min_x;
				double diff_y = max_y - min_y;
				for (Node node : model.getNodes()) {
					if (diff_x > 0.9)
						node.pos_x /= diff_x*1.15;
					if (diff_y > 0.9)
						node.pos_y /= diff_y*1.15;
				}


				// afin que le graphe ne se déplace pas hors du champ de la fenêtre,
				// on fait en sorte que son barycentre soit toujours au milieu de la fenêtre

				// on calcule le barycentre des points
				double moyenne_x = 0;
				double moyenne_y = 0;
				for (Node node : model.getNodes()) {
					moyenne_x += node.pos_x;
					moyenne_y += node.pos_y;
				}
				int n = model.getNumberOfNodes();
				if (n != 0) {
					moyenne_x /= n;
					moyenne_y /= n;
				}

				// on calcule l'écart par rapport au centre de la fenêtre
				double ecart_x = 0.5 - moyenne_x;
				double ecart_y = 0.5 - moyenne_y;

				// on déplace tous les noeuds
				for (Node node : model.getNodes()) {
					node.pos_x += ecart_x;
					node.pos_y += ecart_y;
				}


				view.updateDisplay();
				Thread.sleep(timeStep);  // Synchronize the simulation with real time
			}
		}
		catch(InterruptedException ex) {
			System.err.println(ex.getMessage());
		}
		return;
	}
}
