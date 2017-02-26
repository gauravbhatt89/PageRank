/*
 * The MyWikiCrawler will crawl the en.wikipedia.org website for keywords "football" and "goal"
 * <p> Seed Url is /wiki/Football
 * <p> number of pages to be visited =1000
 * <p> edges to be saved in the fileName specified
 * @author Dipanjan Karmakar
 * @author Gaurav Bhatt
 * 
 */

import java.io.IOException;

public class MyWikiCrawler {
	
	public static void main(String[] args) throws IOException {
		
		String[] set = { "football", "goal" };
		
		long startTime = System.currentTimeMillis();

		WikiCrawler crawlObj = new WikiCrawler("/wiki/Football", set, 10, "MyWikiGraph.txt");

		crawlObj.crawl();
		
		long endTime = System.currentTimeMillis();
		
		float timeElapsed = (float)(endTime - startTime)/(float)(1000);
		System.out.println("Time taken : "+ timeElapsed);
	}
}
