package utilitaires;
/*
 * This class is used to store a list of String terms.
 * Do not modify it.
 * Run the main method to see how you can use it.
 */


public class TermList {
    private String[]terms;
    private int nb;
    
  public TermList(){
        terms=new String[10000];
        nb=0;
}

public boolean isEmpty( ) { return nb==0; }


	public void add(String name){
		terms[nb++]=name;
	}

    public boolean exists(String term) {
        for (int i=0;i<nb;i++) {
            if (terms[i].equals(term))
            	return true;
        }
        return false;
    }
    public String termAt(int index){
    	return terms[index];
    }
    
    public int size() {return nb; }
    

    public static void main(String[] args) {
    	TermList set=new TermList();
    	set.add("A");set.add("B");set.add("C");
    	if (set.exists("C"))
    		System.out.println("C already exists in the list");
    	for (int i=0;i<set.size();i++){
    		String term=set.termAt(i);
    		System.out.println(term);
    	}	
	}
} 



