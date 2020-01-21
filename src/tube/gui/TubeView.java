    /* ----------------------------------------------------------------------------
     * This class must not be modified.
     * TubeView shows the map of London Tube as well as the stations indicated
     * by the user. The map is interactive:
     * - a click on the underground icon clears the map,
     * - a click on a line shows the corresponding stations,
     * - a click on stations is treated only if:
     * 		- the departure station of an itinerary is selected or,
     * 		- the arrival station is selected (this action launches the itinerary search).  
     * ----------------------------------------------------------------------------
     */
package tube.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import tube.Control;
import tube.Network;
import utilitaires.TabFileReader;
import utilitaires.TermList;

@SuppressWarnings("serial")
public class TubeView  extends JFrame  implements MouseListener {
	private Image image;
	private Network tube;
	private Control control;
	private boolean []selected;
	private Station[] stations;
	private int nstations;
	private int[]xlines={880,880,880,880,994,994,994,994};
	private int[]ylines={56,86,112,140,56,86,112,140};	

	 public TubeView(Network t) {
		tube=t;
		control=new Control(t,this);
		readStations();
		String fileimage="./data/londonTube.jpg";
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle(" LONDON TUBE MAP");
		this.setSize(1100,570);
	    ImageIcon icon = new ImageIcon(fileimage);
	    image = icon.getImage();
	    addMouseListener(this);
	 }  
	 private void readStations(){
			TabFileReader.readTextFile("stations.txt",'\t',"data");
			nstations=TabFileReader.nrow();
			stations=new Station[nstations];
			for (int i=0;i<nstations;i++){
				int x=Integer.parseInt(TabFileReader.wordAt(i,1));
				int y=Integer.parseInt(TabFileReader.wordAt(i,2));			
				stations[i]=new Station(TabFileReader.wordAt(i,0),x,y);
			}		
		} 
		
	 public void init(TermList selection) {
		  int nbStations=tube.numberOfStations();
		  selected=new boolean[tube.numberOfStations()];
		  if (selection!=null){
          for (int i=0;i<nbStations;i++) {
        	  	  String stationName=stations[i].getName();
        		  boolean found=false;
        		  for (int j=0;j<selection.size() && !found;j++)
        			  if (selection.termAt(j)!=null){
        				  if (selection.termAt(j).equals(stationName)) found=true;
        			  }
        		  if (found)  selected[i]=true;
        	  }
		  }
		}
	    public void mousePressed(MouseEvent e) { }
	    public void mouseReleased(MouseEvent e) { }
	    public void mouseEntered(MouseEvent e) { }
	    public void mouseExited(MouseEvent e) { }

	     public void mouseClicked(MouseEvent e) {
	        int x=e.getX();
	        int y=e.getY();
	        // clear : show an empty map
	        if ((x>=31)&&(x<=146)&&(y>=117)&&(y<=214)) clear();
	        // show line stations
	        else if ((x>=812)&&(x<=1054)&&(y>=41)&&(y<=157)) showLine(x,y);
	        // last option: itinerary search
	        else control.showItinerary(x,y);
		    repaint();
	     }
      public void paint(Graphics g) {
    	  g.drawImage(image,10,35,this);
    	  int nbStations=tube.numberOfStations();
          for (int i=0;i<nbStations;i++) {
        	  if (selected[i]){
        		Station station=stations[i]; 
        		int xa=station.getX();
        		int ya=station.getY();
	            g.setColor(Color.BLACK);
	            g.fillOval(xa-13,ya-13,26,26);
	            g.setColor(Color.RED);
	            g.fillOval(xa-11,ya-11,22,22);
	            g.setColor(Color.BLACK);
	            g.fillOval(xa-7,ya-7,14,14);
	            g.setColor(Color.YELLOW);
	            g.fillOval(xa-4,ya-4,8,8);
        	  }
          }
        	  
      }
    	/* -----------------------------------------------------------
    	 * Show the stations selected in array selection
    	 * -----------------------------------------------------------
    	 */
      public void show(TermList selection){
    	  init(selection);
    	  setVisible(true);
      }
  	 /* -----------------------------------------------------------
  	  * show the stations belonging to a line.
  	  * -----------------------------------------------------------
  	  */
  	  public void showLine(int x, int y){
       	int pos=-1,mindist=Integer.MAX_VALUE;
       	for (int k=0;k<xlines.length;k++){
       		int dx=x-xlines[k];
       		int dy=y-ylines[k];
       		int dist2=dx*dx+dy*dy;
       		if (dist2<mindist){
       			mindist=dist2;
       			pos=k;
       		}
       	}
       control.showLine(pos);
  } 
  	/* -----------------------------------------------------------
  	 * the main shows an empty map
  	 * -----------------------------------------------------------
  	 */
	  public static void main(String[] args) {
		  Network network = new Network();
		  network.readAndSaveMetroData();
	      network.listStation();
		  TubeView tv=new TubeView(network);
		  tv.show(null);
	  }

	 /* -----------------------------------------------------------
	  * clear the map.
	  * -----------------------------------------------------------
	  */	  
	  public void clear(){
      	control.clearItinerary();
      	show(null);		  
	  }
	  
		public String findClosestStation(int x, int y) {
			int pos=-1,mindist=Integer.MAX_VALUE;
	    	for (int k=0;k<nstations;k++){
	    		int dx=x-stations[k].getX();
	    		int dy=y-stations[k].getY();
	    		int dist2=dx*dx+dy*dy;
	    		if (dist2<mindist){
	    			mindist=dist2;
	    			pos=k;
	    		}
	    	}
			return stations[pos].getName();
		}

}
