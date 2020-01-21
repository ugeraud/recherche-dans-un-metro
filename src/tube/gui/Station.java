package tube.gui;
/* ---------------------------------------------------------
 * This class is used by TubeView only. 
 * Do not modify it.
 * ---------------------------------------------------------
 */
public class Station {
	private String nom;
	private int x,y;

	public Station(String nm,int a,int b){
		nom=nm;
		x=a;
		y=b;
	}
	public String getName() {
		return nom;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}


}
