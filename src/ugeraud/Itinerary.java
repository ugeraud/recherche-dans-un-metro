/**
 * 
 */
package ugeraud;

import java.util.Arrays;

import tube.Network;
import utilitaires.RandomIndexes;
import utilitaires.TermList;

/**
 * @author gerau
 *
 */
public class Itinerary {

	final int NB_ITINERARY = 12;
	
	/**
	 * @construct
	 */
	public Itinerary() {}
	
	public TermList searchItinerary(String departure, String arrival) {
		TermList visited = new TermList();
		
		String current = departure;
		
		boolean finish = false;
		
		while(!finish) {
			
			visited.add(current);
			
			if(current.equalsIgnoreCase(arrival)) {
				return visited;
			}else {
				Network network = new Network();
				network.readAndSaveMetroData();
				
				RandomIndexes ramdom = new RandomIndexes(network.nsteps(), 2);
				int [] order = ramdom.swap();
				String next = null;
				int i = 0;
				while(null == next && i < network.nsteps()) {
					int index = order[i];
					Step step = network.stepAt(index);
					String nextS = step.getNext(current);
					if(null != nextS && !visited.exists(nextS)) {
						next = nextS;
					}
					i++;
				}
				if(null == next) {
					finish = true;
				}else {
					current = next;
				}
			}
		}		
		return null;
	}
	
	
	public TermList searchItineraryN(String departure, String arrival) {
		
		TermList [] temp = new TermList[NB_ITINERARY];
		
		for (int i = 0; i < NB_ITINERARY; i++) {
			TermList t = searchItinerary(departure, arrival);
			temp[i] = t;
		}
		
		//Arrays.sort(temp);
		
		return temp[0];
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Itinerary itinerary = new Itinerary();
		
		TermList term = itinerary.searchItineraryN("Paddington", "Oxford Circus");
		
		if(null != term) {
			for (int i = 0; i < term.size(); i++) {
				String t = term.termAt(i);
				System.out.println(t);
			}
		}else {
			System.out.println("Aucun résultat");
		}

	}

}
