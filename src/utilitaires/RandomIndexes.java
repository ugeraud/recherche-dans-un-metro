package utilitaires;
import java.util.Random;
/*
 * This class is used to perform random ordering of indexes.
 * Do not modify it.
 * Run the main method to see how you can use it.
 */

public class RandomIndexes {
	private int[] order;
	private Random rand;
	
	public RandomIndexes(int n,long seed){
		order=new int[n];
		rand=new Random(seed);
		for (int i=0;i<n;i++)
			order[i]=i;
	}
	public int[] swap(){
		int n=order.length;
		for (int i=0;i<n;i++){
			int i1=rand.nextInt(n);
			int temp=order[i];
			order[i]=order[i1];
			order[i1]=temp;
		}
		return order;
	}

	public static void main(String[] args){
		// how indexes are permuted:
		RandomIndexes rind=new RandomIndexes(5,0);
		System.out.println("Random order of the indexes:");
		for (int i=0;i<10;i++){
			int[]order=rind.swap();
			for (int j=0;j<order.length;j++)
				System.out.print(order[j]+" ");
			System.out.println();
		}
		// how to use it to permute the display of values of another array
		String []tab={"A","B","C","D","E"};
		RandomIndexes rtab=new RandomIndexes(tab.length,0);
		System.out.println("Random order of the array values:");
		for (int i=0;i<10;i++){
			int[]order=rtab.swap();
			for (int j=0;j<order.length;j++){
				int pos=order[j];
				System.out.print(tab[pos]+" ");
			}
			System.out.println();
		}		
	}
}
