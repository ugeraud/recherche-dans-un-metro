/**
 * 
 */
package ugeraud;

/**
 * @author gerau
 *
 */
public class Step {

	/**
	 * station1 represent one station of the metro line
	 */
	private String station1;
	
	/**
	 * station2 represent another else station of the metro line
	 */
	private String station2;
	
	/**
	 * ligne represent one line of the metro
	 */
	private String ligne;
	
	/**
	 * @construct init Step object
	 * @param station1
	 * @param ligne
	 * @param station2
	 */
	public Step(String station1, String ligne, String station2) {
		this.station1 = station1;
		this.station2 = station2;
		this.ligne = ligne;
	}
	
	/**
	 * @param station
	 * @return String | opposite of one station 
	 * ( For example if (station1,ligne,station2) getNext(station1) will return station2 )
	 */
	public String getNext(String station) {
		if(station.equalsIgnoreCase(this.getStation1())) {
			return this.getStation2();
		}else if(station.equalsIgnoreCase(this.getStation2())) {
			return this.getStation1();
		}
		return null;
	}
	
	/**
	 * @return the station1
	 */
	public String getStation1() {
		return station1;
	}

    /**
	 * @return the station2
	 */
	public String getStation2() {
		return station2;
	}


	/**
	 * @return the ligne
	 */
	public String getLigne() {
		return ligne;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Step step = new Step("Paddington","Bakerloo_line","Marylebone");
		
		String station = step.getNext("Marylebone");
		
		System.out.println(station);

	}

}
