 /**
 * 
 */
package ugeraud;

import java.util.Scanner;

import tube.Network;
import utilitaires.TermList;

/**
 * @author gerau
 *
 */
public class Lines {

	private Network net;
	
	/**
	 * @return the net
	 */
	public Network getNet() {
		return net;
	}


	private TermList lines;
	/**
	 * 
	 * @param network
	 */
	public Lines(Network network) {
		this.net = network;
		this.lines = new TermList();
		this.listLigne();
	}
	
	/**
	 * 
	 * @param numLigne
	 * @return
	 */
	public TermList findStations(int numLigne) {
		TermList t = new TermList();
		for (int i = 0; i < this.lines.size(); i++) {
			String line = this.lines.termAt(numLigne);
			for (int j = 0; j < this.net.nsteps(); j++) {
				Step step = this.net.stepAt(j);
				if(line.equalsIgnoreCase(step.getLigne())) {
					String station1 = step.getStation1();
					String station2 = step.getStation2();
					if(!t.exists(station1)) {
						t.add(station1);
					}
					if(!t.exists(station2)) {
						t.add(station2);
					}
				}
			}
		}
		return t;
	}
	
	/**
	 * 
	 * @return TermList object
	 */
	private void listLigne() {
		this.net.readAndSaveMetroData();
		for (int i = 0; i < this.net.nsteps(); i++) {
			Step s = this.net.stepAt(i);
			String line = s.getLigne();
			if(!this.lines.exists(line)) {
				this.lines.add(line);
			}
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Network network = new Network();
		
		Lines lines = new Lines(network);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer un numéro : ");
		int choix = sc.nextInt();
		TermList termList = lines.findStations(choix);
		
		System.out.println("=========================================================");
		System.out.println("\tListes des stations de la ligne "+choix);
		System.out.println("==========================================================");
		for (int i = 0; i < termList.size(); i++) {
			String term = termList.termAt(i);
			System.out.println(term);
		}
		sc.close();
	}

}
