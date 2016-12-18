import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;


public class LCS_Ashwin_Nikam_anikam {

	public static void main(String args[]) throws IOException{
		
		String sCurrentLine; 
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		String[] str = new String[2];
		int num=0;
		
		while ((sCurrentLine = br.readLine()) != null) {
		str[num]=sCurrentLine;
		num++;
		//System.out.println(sCurrentLine);
		}
		
		
		int i,j;
		
		int n = str[0].length();
		int m = str[1].length();
		

		String[] A1 = str[0].split("");
		String[] B1 = str[1].split("");
		
		String[] A = new String[n+1];
		String[] B = new String[m+1];
		
		int index = 1;
		for(int z=0;z<A1.length;z++){
			A[index] = A1[z];
			index++;
		}
		
		index = 1;
		for(int z=0;z<B1.length;z++){
			B[index] = B1[z];
			index++;
		}
		

		System.out.println("Input");
		for(int z =1;z<=A1.length;z++){
			System.out.print(A[z]);
		}
		
		System.out.println();
		for(int z =1;z<=B1.length;z++){
			System.out.print(B[z]);
		}
		
		int opt[][] = new int[1001][1001];
		int parent[][] = new int[1001][1001];
		
		for(j=0 ; j<=m; j++){
			opt[0][j]=0;
		}
		
		
		
		for(i=1; i<=n ; i++){
			opt[i][0]=0;
			for(j=1 ; j<=m ; j++){
				if(A[i].equals(B[j])){
					opt[i][j] = opt[i-1][j-1] + 1;
					parent[i][j] = 0;
				}else if(opt[i][j-1]>=opt[i-1][j]){
					opt[i][j]=opt[i][j-1];
					parent[i][j] = 1;
				}else{
					opt[i][j] = opt[i-1][j];
					parent[i][j] = 2;
				}
			}
		}
		
		
		i=n;
		j=m;
		LinkedList<String> S = new LinkedList();
		//String[] S = new String[1000];
		while(i>0 && j>0){
			if(parent[i][j]==0){
				//System.out.println(A[i]);
				S.add(A[i]);
				i--;
				j--;
			}else if(parent[i][j]==2){
				i--;
			}else{
				j--;
			}
		}
		
		
		//System.out.print(S);
		Collections.reverse(S);
		String[] S1 = new String[S.size()];
		for(int z=0;z<S1.length;z++){
			S1[z]=S.get(z);
		}
		
		System.out.println("\n\nOutput");
		System.out.print(S1.length);
		System.out.println();

		for(int z=0;z<S1.length;z++){
			System.out.print(S1[z]);
		}
		
		PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
		out.println(S1.length);
		for(int z=0;z<S1.length;z++){
			out.print(S1[z]);
		}
	    out.close();
		
	}
	
}
