package cse531;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;
	

public class MST_Ashwin_Nikam_anikam {

static int s=0;
static int kv[];
static int a[];
static int p[];
	
		public static void main(String args[]) throws IOException{
			
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			
			
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
			    
			    sb.append(line);
		        sb.append(System.lineSeparator());
		        System.out.println(line);
		        String[] arr = line.split(" ");
		        if(arr.length!=2){
		        	
		        	System.out.println("Invalid input"); //To check whether input is valid or not
		        	
		        }else{
		        	
		        	int n = Integer.parseInt(arr[0]); //n is the number of vertices
			        int e = Integer.parseInt(arr[1]); //e is the number of edges
			        
			        a = new int[n + 1];
			        kv = new int[n + 1];
			        p = new int[n + 1];
			        
			        line = br.readLine();  
				 
			        LinkedList<Integer>[] list = new LinkedList[2*e+1];
			        LinkedList<Integer> mst = new LinkedList();
			        LinkedList<Integer> V = new LinkedList();
			        LinkedList<Integer> V1 = new LinkedList();
			        
			        int point = 1;
			        while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        String[] temp = line.split(" ");
				        int u = Integer.parseInt(temp[0]);
				        int v = Integer.parseInt(temp[1]);
				        int w = Integer.parseInt(temp[2]);
				        list[point] = new LinkedList(); //To store edge u,v
				        list[point].add(u);
				        list[point].add(v);
				        list[point].add(w);
				        V.add(u); V1.add(u);
				        V.add(v); V1.add(v);
				        System.out.println(list[point]);
				        point++;
				        list[point] = new LinkedList(); //To store another edge v,u 
				        list[point].add(v);
				        list[point].add(u);
				        list[point].add(w);
				        System.out.println(list[point]);
				        point++;
				        line = br.readLine();  
				    }
			        
			        
			        TreeSet ts = new TreeSet(); 
			        ts.addAll(V);
			        V.clear();
			        V.addAll(ts); //V contains all the vertices in sorted order
			        
			        ts.clear();
			        ts.addAll(V1);
			        V1.clear();
			        V1.addAll(ts);
			        
			        Collections.sort(V);
			        System.out.println("Initial V: "+V);
				    String everything = sb.toString();
				
				    br.close();
				   
				    int root = 1;
				    int key=0;
				    s=0;
				    insert(root,key);
				    for(int i=2;i<=n;i++){
				    	insert(i,9999);
				    }
				    
				    int parent[] = new int[n+1];
				    int v;
				    
				    while(mst.containsAll(V)==false){                                    //Our main while loop
				    	int u = extract_min();
				    	mst.add(u);
				    	System.out.println("MST: "+mst);
				    	if(V.contains(u)){
				    		int i = V.indexOf(u);
				    		V.remove(i);
				    		System.out.println("V :"+V);
				    	}
				    	
				    	for(int i=1;i<=2*e;i++){ 
				    		if(list[i].get(0)==u){ 
				    			if(mst.contains(list[i].get(1))){
				    					continue;
					    		}
				    			else{
				    				v=list[i].get(1);
					    			if(kv[v]>list[i].get(2)){
					    				decrease_key(v,list[i].get(2));
					    				parent[v]=u;
					    				System.out.println("d(v) of "+v+" is "+kv[v]+", Parent of "+v+" is "+parent[v]);
					    				}
				    				}
				    			}
				    	
				    		}
				    
				    	}
				    
				   
				    int weight = 0;
				    int total_weight = 0;
				    int j;
				    int count=0;
				    LinkedList<Integer>[] mainlist = new LinkedList[mst.size()];
				    for(int i=1;i<mst.size();i++){
				    	for(j=1;j<=2*e;j++){
				    		if(list[j].get(0)==parent[mst.get(i)] && list[j].get(1)==mst.get(i)){
				    			weight = list[j].get(2);
				    			total_weight += weight; //Incrementing the total weight of the MST
				    			count++;
				    			mainlist[count] = new LinkedList();
						    	mainlist[count].add(parent[mst.get(i)]);
						    	mainlist[count].add(mst.get(i));
						    	mainlist[count].add(weight);
				    			break;
				    			}
				    		}
				    	}
				   
				    
				    File f = new File("output.txt");
				    PrintWriter pw = new PrintWriter(f);
				    
				    System.out.println("Final answer");
				    System.out.println(total_weight);
				    pw.println(total_weight);
				    for(int i=1;i<=count;i++){
				    	System.out.println(mainlist[i].get(0)+"  "+mainlist[i].get(1)+"  "+mainlist[i].get(2));
				    	pw.println(mainlist[i].get(0)+" "+mainlist[i].get(1)+" "+mainlist[i].get(2));
				    }
				    
				    pw.close();
		        	
		        	
		        } //End of else statement
		        
		} //End of main loop
		
		public static void insert(int root, int key){
			s++;
			a[s]=root;
			p[root] = s;
			kv[root] = key;
			heapify_up(s);
		}
		
		public static void heapify_up(int i){
			
			while(i>1){
				int j=i/2;
				if(kv[a[i]]<kv[a[j]]){
					int sample =a[i];
					a[i]=a[j];
					a[j]=sample;
					p[a[i]]=i; 
					p[a[j]]=j;
					i=j;
				}else
					break;
				
			}
			
		}
		
		public static int extract_min(){
			int ret = a[1];
			a[1]=a[s];
			p[a[1]]=1;
			s--;
			if(s>=1){
				heapify_down(1);
			}
			return ret;
		}
		
		public static void heapify_down(int i){
			int j=0;
			while(2*i<=s){
				if(2*i==s || kv[a[2*i]]<= kv[a[2*i+1]]){
					j=2*i;
				}else{
					j=2*i+1;
				}
				
				if(kv[a[j]] < kv[a[i]]){
					int sample =a[i];
					a[i]=a[j];
					a[j]=sample;
					p[a[i]]=i;
					p[a[j]]=j;
					i=j;
				}else
					break;
			}
				
		}
		
		public static void decrease_key(int v, int key_value){
			kv[v]=key_value;
			heapify_up(p[v]);
		}
		
	
}
