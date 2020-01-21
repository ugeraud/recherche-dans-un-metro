/**
 * 
 */
package tube;

import ugeraud.Step;
import utilitaires.TabFileReader;
import utilitaires.TermList;

/**
 * @author gerau
 *
 */
public class Network {

	/**
	 * array to save metro data
	 */
	private Step [] steps;
	
	/**
	 * current size of step array
	 */
	private int stepSize;
	
	/**
	 * save satation list in terms array
	 */
	public TermList listStation;
	
	/**
	 * @construct to init Network object
	 */
	public Network() {
		this.steps = new Step[1000];
		this.listStation = new TermList();
		this.stepSize = 0;
	}
	
	/**
	 * method to read and save metro data
	 */
	public void readAndSaveMetroData() {
		String filename="steps.txt";
		String[] temp = new String[3];
		TabFileReader.readTextFile(filename,'\t',"data");
		for (int i=0;i<TabFileReader.nrow();i++){
			for (int j=0; j<TabFileReader.ncol();j++) {
				temp[j] = TabFileReader.wordAt(i,j);
			}
			Step step = new Step(temp[0],temp[1],temp[2]);
			this.steps[this.stepSize++] = step;
			for (int k = 0; k < temp.length; k++) {
				temp[k] = " ";
			}
		}
	}
	
	/**
	 * @return the stepSize current size of step array
	 */
	public int nsteps() {
		return stepSize;
	}
	
	/**
	 * 
	 * @param index
	 * @return Step object
	 */
	public Step stepAt(int index) {
		return this.steps[index];
	}
	
	/**
	 * 
	 * @return TermList | return metro station list
	 */
	public TermList listStation() {	
		for (int i = 0; i < this.nsteps(); i++) {
			Step s = this.stepAt(i);
			String station1 = s.getStation1();
			String station2 = s.getStation2();
			if(!this.listStation.exists(station1)) {
				this.listStation.add(station1);
			}
			if(!this.listStation.exists(station2)) {
				this.listStation.add(station2);
			}
		}	
		return this.listStation;
	}
	
	/**
	 * mandatory method : should return the correct number of stations.
	 */
	public int numberOfStations(){
		return listStation.size();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Network network = new Network();
		
		
		network.readAndSaveMetroData();
		
		System.out.println("=========================================================");
		System.out.println("\tDonnées(itinéraires) du métro ");
		System.out.println("==========================================================");
		for (int i = 0; i < network.nsteps(); i++) {
			Step s = network.stepAt(i);
			System.out.println(s.getStation1() + " --> " + s.getLigne() + " --> "+s.getStation2() );
		}

		TermList termList = network.listStation();
		System.out.println("=========================================================");
		System.out.println("\tStations du métro | "+network.numberOfStations()+" station(s)");
		System.out.println("==========================================================");
		for (int i = 0; i < termList.size(); i++) {
			String term=termList.termAt(i);
    		System.out.println(term);
		}
	}

}
