package tube;

import javax.swing.JOptionPane;

import tube.gui.TubeView;
import ugeraud.Itinerary;
import ugeraud.Lines;
import utilitaires.TermList;

public class Control {
	private TubeView tv;
	private String begin, end;
	
	public Control(Network tub, TubeView tuv){
		tv=tuv;
		begin=end=null;
	}
	public void clearItinerary(){
      	System.out.println("USER ACTION: clear the map");
		begin=end=null;
	}
	 /* -----------------------------------------------------------
	  * show the stations belonging to a line.
	  * -----------------------------------------------------------
	  */
	  public void showLine(int numLigne){
     	System.out.println("USER ACTION: line selection, index= "+numLigne);
     	Network network = new Network();
		Lines lines = new Lines(network);
     	TermList list= lines.findStations(numLigne);    	
     	tv.show(list); 	
	  }
	/* -----------------------------------------------------------
	 * show an itinerary between two stations.
	 * -----------------------------------------------------------
	 */	  
	  public void showItinerary(int x, int y){
     	String station=tv.findClosestStation(x,y);
     	TermList sel=new TermList();
     	if (begin==null) {
     		begin=station;
         	System.out.println("USER ACTION: departure selection = "+station);
     		sel.add(begin);
     		tv.show(sel);
     	}
     	else if (end==null) {
     		end=station;
         	System.out.println("USER ACTION: arrival selection = "+station);
         	Itinerary itinerary = new Itinerary();
    		TermList selection=itinerary.searchItineraryN(begin, end);
     		if (selection==null) {
     			JOptionPane.showMessageDialog(tv, "No path has been found between "+begin+" and "+end);
     			sel=null;
     			begin=null;
     			end=null;
     		}
     		tv.show(selection);
     	}
	  }
}
