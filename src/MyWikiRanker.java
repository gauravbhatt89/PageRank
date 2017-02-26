/*
 * MyWikiRanker class has the main method, which will use the web graph 
 * created by crawling /wiki/Football and call methods to output pages with highest pagerank,
 * indegree and outdegree etc.
 * @author: Dipanjan Karmakar
 * @author: Gaurav Bhatt
 */
public class MyWikiRanker {
	
	public static void main(String [] args) {
		try{
		
	
			System.out.println("PageRank start...");
			String fileName = "MyWikiGraph.txt";
		
			
			System.out.println("Experiments with Approximation factor = 0.01");
			Float approximation = 0.01f;
			PageRank p = new PageRank(fileName, approximation);
		    
		    String[] topIn = p.topKInDegree(1);
		    
		    String[] topOut = p.topKOutDegree(1);
		    
		    String[] topPage = p.topKPageRank(1);
		    
		    System.out.println("\nTop inDegree pages are:");    
		    for(String s : topIn){
		    	System.out.println(s);
		    }
		    
		    System.out.println("\nTop outDegree pages are: ");    
		    for(String s : topOut){
		    	System.out.println(s);
		    }
		    
		    System.out.println("\nTop pageRank pages(with aprox. factor = 0.01) are: ");    
		    for(String i : topPage){
		    	System.out.println(i);
		    }
		    	    
		    System.out.println("\n\n----------------------------");
		    String[] topIn_100 = p.topKInDegree(100);
		    
		    String[] topOut_100 = p.topKOutDegree(100);
		    
		    String[] topPage_100 = p.topKPageRank(100);
		    
		    System.out.println("\nTop 100 inDegree pages are:");    
		    for(String s : topIn_100){
		    	System.out.println(s);
		    }
		    
		    System.out.println("\nTop 100 outDegree pages are: ");    
		    for(String s : topOut_100){
		    	System.out.println(s);
		    }
		    
		    System.out.println("\nTop 100 pageRank pages(with aprox. factor = 0.01) are: ");    
		    for(String i : topPage_100){
		    	System.out.println(i);
		    }
		    
		    // Calculate Jaccard Similarity
		    System.out.println("Calculating Jaccard Similarity with approximation factor 0.01 : ");
		    Float jack1 = p.calculate_jack(topIn_100, topOut_100);
		    System.out.println("Jaccard Similarity b/w inDegree and outDegree: "+jack1);
		    
		    Float jack2 = p.calculate_jack(topIn_100, topPage_100);
		    System.out.println("Jaccard Similarity b/w inDegree and pageRank: "+jack2);
		    
		    Float jack3 = p.calculate_jack(topOut_100, topPage_100);
		    System.out.println("Jaccard Similarity b/w outDegree and pageRank: "+jack3);
		    
		    System.out.println("\n\n\n-----------------------------------------------");
			System.out.println("Experiments with Approximation factor = 0.005");
			Float approximation2 = 0.005f;
			PageRank p2 = new PageRank(fileName, approximation2);
			
			String[] topPage2_100 = p2.topKPageRank(15);
			
			System.out.println("\nTop 100 pageRank pages(with aprox. factor = 0.005) are: ");    
		    for(String i : topPage2_100){
		    	System.out.println(i);
		    }
			
		    String[] topPage2 = p2.topKPageRank(1);
		    System.out.println("\nTop pageRank pages(with aprox. factor = 0.005) are: ");    
		    for(String i : topPage2){
		    	System.out.println(i);
		    }
		    
		    // Calculate jaccard similarity
		    System.out.println("Calculating Jaccard Similarity with approximation factor 0.005 : ");
		    Float jack11 = p2.calculate_jack(topIn_100, topOut_100);
		    System.out.println("Jaccard Similarity b/w inDegree and outDegree: "+jack11);
		    
		    Float jack12 = p2.calculate_jack(topIn_100, topPage2_100);
		    System.out.println("Jaccard Similarity b/w inDegree and pageRank: "+jack12);
		    
		    Float jack13 = p2.calculate_jack(topOut_100, topPage2_100);
		    System.out.println("Jaccard Similarity b/w outDegree and pageRank: "+jack13);
		   
		} catch (Exception me) {
			System.out.println(me); 
			me.printStackTrace();
		}
	}
	
}