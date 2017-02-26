
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import javax.print.attribute.standard.OutputDeviceAssigned;

public class PageRank2 {
	
	LinkedHashMap<String, ArrayList<String>> curr_list;
	LinkedHashMap<String, Integer>  fileIndex;
	LinkedHashMap<Integer, ArrayList<Integer>> adjList; 
	LinkedHashMap<String, Integer>  inDegree;
	LinkedHashMap<String, Integer> outDegree;
	static int noOfNodes;
 	
	public PageRank2(String fileName, double approximation ){
		
		Path file = Paths.get(fileName);
		curr_list = new LinkedHashMap<String, ArrayList<String>>();
		adjList = new LinkedHashMap<Integer,ArrayList<Integer>>();
		fileIndex  = new LinkedHashMap<String, Integer>();
		outDegree = new LinkedHashMap<String,Integer>();
		inDegree = new LinkedHashMap<String,Integer>();
		
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader =	new BufferedReader(new InputStreamReader(in))) {
			String line = null; 
			 
			ArrayList<String> edge=null;
			ArrayList<String> newEdge=null;
			
			String temp = null;
			boolean first =true;
			String ff=null;
			String prev_vertices=null;
			boolean isFirst=true;
			int fileIdxCount=0;;

	    	while ((line = reader.readLine()) != null) {
		    	if(isFirst){
		    		isFirst=false;
		    		continue;
		    	}
	    		
	    		String strArr[]=line.split(" ");
		    	String fromNode=strArr[0].trim();
		    	String toNode=strArr[1].trim();
		    	if(!fileIndex.containsKey(fromNode))
		    		fileIndex.put(fromNode, fileIdxCount++);
		    	if(!fileIndex.containsKey(toNode))
		    		fileIndex.put(toNode, fileIdxCount++);
		    		
		    	
		    	ArrayList<String> toNodeList;
		    	if(!curr_list.keySet().contains(fromNode))
		    	{
		    		toNodeList=curr_list.get(fromNode);
		    		if(toNodeList==null) {
		    			toNodeList=new ArrayList<String>();
		    		}
		    		toNodeList.add(toNode);
		    		curr_list.put(fromNode, toNodeList);
		    	} 
		    	else {
		    		toNodeList=curr_list.get(fromNode);
		    		toNodeList.add(toNode);
		    		curr_list.put(fromNode, toNodeList);
		    	}
		    	
		    	// Fill outDegree
		    	if(!outDegree.containsKey(fromNode)){
		    		outDegree.put(fromNode, 1);
		    	} 
		    	else {
		    		outDegree.put(fromNode,(outDegree.get(fromNode)+1));
		    	}
		    	
		    	//Fill InDegree
		    	if (!inDegree.containsKey(toNode)){
		    		inDegree.put(toNode, 1);
		    	} 
		    	else {
		    		inDegree.put(toNode,(inDegree.get(toNode)+1));
		    	}
	    	}
	    	
	    	//printing for debugging
	    	for (String key : outDegree.keySet()){
	    		//	System.out.println(key +" : "+curr_list.get(key));	
	    		//	System.out.println(key +" : "+outDegree.get(key));
	    		//	System.out.println(key +" : "+inDegree.get(key));
	    	}
	    	
	    	calculatePageRank();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
	

	private void calculatePageRank() {
		// TODO Auto-generated method stub
		noOfNodes = fileIndex.size();
		Float pZeroVector[] = new Float[noOfNodes],iniVal=(float)1/noOfNodes,pNVector[],pN1Vector[],epsi=0.85f;
		boolean converged=false;
		Arrays.fill(pZeroVector,iniVal);
		pNVector=pZeroVector;
		while(!converged)
		{
			pN1Vector=innerFunc(pNVector);
			if(norm(pN1Vector,pN1Vector)<=epsi)
				converged=true;
			pNVector=pN1Vector;
		}
		System.out.println("Page Ranks :) >>");
		for(Float f:pNVector)
			System.out.println(f);
	}


	private Float norm(Float[] pN1Vector, Float[] pNVector) {
		DoubleStream ds1 = IntStream.range(0, pN1Vector.length).mapToDouble(i -> pN1Vector[i]);
		DoubleStream ds = IntStream.range(0, pNVector.length).mapToDouble(j -> pNVector[j]);
		Double diff=Math.abs(ds1.sum()-ds.sum());
		return diff.floatValue();
	}


	private Float[] innerFunc(Float[] pNVector) 
	{
		Float beta=0.85f;
		noOfNodes = fileIndex.size();
		Float pN1Vector[]=new Float[noOfNodes];
		float iniVal=(float)(1-beta)/noOfNodes;
		Arrays.fill(pN1Vector, iniVal);
		for(String linkP:curr_list.keySet())
		{
			ArrayList<String> outNodesList=curr_list.get(linkP);
			if(outNodesList.size()>0)
			{
				for(String page:outNodesList)
				{
					int q=fileIndex.get(page);
					int p=fileIndex.get(linkP);
					pN1Vector[q]=pN1Vector[q]+ (beta*pNVector[p]/outNodesList.size());
				}
			}
			else{
				for(String lowerPage:curr_list.keySet())
				{
					int q=fileIndex.get(lowerPage);
					int p=fileIndex.get(linkP);
					pN1Vector[q]=pN1Vector[q]+ (beta*pNVector[p]/noOfNodes);
					
				}
			}
		}
		
		return pN1Vector;
	}



	double PageRankOf(String vertex){
		double pageRank = 0;
		
		
		return pageRank;
	}
	
	public int outDegreeOf(String vertex){
        int out=0;
        out = outDegree.get(vertex);
		return out;
	}
	
	public int inDegreeOf(String vertex){
		int in =0;
		in = inDegree.get(vertex);
		return in;
	}
	
	public int numEdges(){
		int numEdges = curr_list.size();	
		System.err.println("No. of edges : "+numEdges);
		return numEdges;
	}
	
	public String [] topKPageRank(int k) {
		String[] PageRankArr = null;
		
		return PageRankArr;
	}
	
	
	
	//return top k in degree
	@SuppressWarnings("unchecked")
	public ArrayList<String> topKInDegree(int k){
		ArrayList<String> InDegreeArr=new ArrayList<String>();
		int i=0;
		Object[] a = inDegree.entrySet().toArray();
		
	    Arrays.sort(a, new Comparator<Object>() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                    ((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
	    for (Object e : a) {
	        //System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
	        //        + ((Map.Entry<String, Integer>) e).getValue());
	        if(i<k){
	        	InDegreeArr.add(((Map.Entry<String, Integer>) e).getKey());
	        	i++;
	        }
	    }
		return InDegreeArr;
	}
	
	
	// Return top k out degrees
	public ArrayList<String> topKOutDegree(int k){
		ArrayList<String> outDegreeList=new ArrayList<String>();
		int i=0;
		Object[] a = outDegree.entrySet().toArray();
		
	    Arrays.sort(a, new Comparator<Object>() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                    ((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
	    for (Object e : a) {
	       // System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
	       //         + ((Map.Entry<String, Integer>) e).getValue());
	        if(i<k){
	        	outDegreeList.add(((Map.Entry<String, Integer>) e).getKey());
	        	i++;
	        }
	    }
		
		return outDegreeList;
	}
	
}