import java.io.IOException;

/*
 * The WikiTennisCrawler will crawl the en.wikipedia.org website for keywords "tennis" and "grand slam"
 * <p> Seed Url is /wiki/Tennis
 * <p> number of pages to be visited =1000, change as needed
 * <p> edges to be saved in the fileName specified , change as needed
 * @author Dipanjan Karmakar
 * @author Gaurav Bhatt
 * 
 */

public class WikiTennisCrawler {
	
	public static void main(String[] args) throws IOException {
		
		String[] set = { "tennis", "grand slam" };
		
		long startTime = System.currentTimeMillis();

		WikiCrawler crawlObj = new WikiCrawler("/wiki/Tennis", set, 1000, "WikiTennisGraph.txt");

		crawlObj.crawl();
		
		long endTime = System.currentTimeMillis();
		
		float timeElapsed = (float)(endTime - startTime)/(float)(1000);
		System.out.println("Time taken : "+ timeElapsed);
		
	}
}
