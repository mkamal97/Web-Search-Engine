package searchEngine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawler {
	
	// Defining a Queue of Strings which will contain all the URLs that need to be
	// crawled during the execution.
	public static Queue<String> pagesToCrawl = new LinkedList<>();
	
	// Defining a Set of Strings which will contain all the URLs that are already
	// crawled.
	public static Set<String> pagesCrawledSuccesfully = new HashSet<>();
	
	// Defining a Set of Strings which will contain all the URLs that were tried to 
	// crawl but failed.
	public static Set<String> pagesCrawledUnsuccesfully = new HashSet<>();
	
	// Defining the Regex String pattern which will pick all URLs in any web page
	// being crawled
	public static String allURLsRegex = "([\\w-]+://?|www[.])[^\\s()<>]+";
	
	// Defining the maximum number of Web Pages to be crawled
	//private static final int MAX_PAGES_TO_CRAWL = 1000;
	
	public static void crawl(String URL) throws IOException {
		Scanner userInput = new Scanner(System.in);
		
		/*System.out.println("\n	Web Crawling - START");
		System.out.println("	=========================================================");
		System.out.print("		Please enter the Web Page URL to start crawling from: ");
		Scanner s = new Scanner(System.in);
		String baseURL = s.next();*/
		
		// Getting the max pages user wishes to crawl
		System.out.print("\nEnter the number of pages you want to crawl: \n");
		int maxPagesToCrawl = userInput.nextInt();
		
		// Understanding if user wishes to save the files crawled or not
		// 1 - Save Files
		// 2 - Don't save the files
		System.out.print("\nEnter '1' to save pages in the Crawled_Web_Pages : \n");
		
		int saveWebPage = userInput.nextInt();
		
	
		// Populating the Queue as this URL is yet to be crawled 
		pagesToCrawl.add(URL);
		
		int counter = 1;
		
		while(!pagesToCrawl.isEmpty()) {
			if (pagesCrawledSuccesfully.size() >= maxPagesToCrawl) {
				return;
			}
			
			String currentURLBeingCrawled = pagesToCrawl.poll();
			
			if (!pagesCrawledSuccesfully.contains(currentURLBeingCrawled)) {
				if (saveWebPage != 1) {
					System.out.println("\n------------------------------------------------------------------------------");
					System.out.println("\nPages succesfully craweled " + pagesCrawledSuccesfully.size());
					System.out.println("\nPages visited but crawling failed  : " + pagesCrawledUnsuccesfully.size());
					System.out.println("\nNumber of pages to be crawled : " + pagesToCrawl.size());
					System.out.println("\nAttempting to Crawl: " + currentURLBeingCrawled);
				}
							
				Document htmlToDocument = new Document("");
								
				try {
					htmlToDocument = Jsoup.connect(currentURLBeingCrawled).get();
					String webPageToString = htmlToDocument.html();
					Pattern allURLsPatternObject = Pattern.compile(allURLsRegex);
					Matcher matcherObject = allURLsPatternObject.matcher(webPageToString);
					
					BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\HOME\\eclipse-workspace-ACC_Final_Project_\\Search Engine\\Crawled_Web_Pages/"
							+ "Web Page - " + counter + ".html"));
										
					while (matcherObject.find()) {
						String urlIdentified = matcherObject.group();
						urlIdentified = urlIdentified.substring(0, urlIdentified.length()-1);
						if (!(urlIdentified.startsWith("http://") || urlIdentified.startsWith("https://"))) {
							urlIdentified = "http://" + urlIdentified;
						}
						pagesToCrawl.add(urlIdentified);
					}
					if (!pagesCrawledSuccesfully.contains(currentURLBeingCrawled)) {
						pagesCrawledSuccesfully.add(currentURLBeingCrawled);
						write.write(webPageToString);
						write.close();
						counter++;
					}		
				} catch (IOException e) {
					pagesCrawledUnsuccesfully.add(currentURLBeingCrawled);
					if (saveWebPage != 1) {
						System.out.println("\n		------------------------------------------------------------------------------");
						System.out.println("		IOException for the URL: " + currentURLBeingCrawled);
						System.out.println("		Exception Cause: " + e.toString());
					} 
				}	
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		

		System.out.println("------------------------------Welcome to our Web Search Engine-----------------------\n");
		System.out.println("\n----------------------------------Developed by-------------------------------------\n");
		System.out.println("1). Mohammed Muzammil Kamal - 110025905");
		System.out.println("2). RamKumar Lokanandi      - 110030488");
		System.out.println("3). Rushik Santoki          - 110041169");
		System.out.println("4). Satwinder Singh Pal     - 110058714");
		System.out.println("\n-----------------------Web Crawling - START--------------------------\n");
		System.out.print("\nEnter your desired URL to crawl: \n");
		Scanner s = new Scanner(System.in);
		String baseURL = s.next();
		double startTime = System.currentTimeMillis();
		WebCrawler.crawl(baseURL);
		double endTime = System.currentTimeMillis();
		//System.out.println("\nCrawling Completed");
		System.out.println("\nThe website is crawled and crawled web pages are stored in the folder 'Crawled_Web_Pages folder");
		System.out.println("\nTime taken for crawling the website  " + baseURL +  "\t" +((endTime - startTime)/60000)+  " minutes");
		System.out.println("\nNumber of links Crawled Successfully: " + pagesCrawledSuccesfully.size());
		System.out.println("\nNumber of links failed to Crawl:" + pagesCrawledUnsuccesfully.size());
		System.out.println("\n-----------------------	Web Crawling successfully completed ------------------------------------");
		
	System.out.println("\n                              Time for HTML to Text Conversion                                        ");
		
	/*	double startTime = System.currentTimeMillis();
		crawl("https://www.amazon.in/");
	double endTime = System.currentTimeMillis();
	System.out.println("Total Time taken for Crwaling " + allURLsRegex + " Pages: " + ((endTime - startTime)/60000) + " minutes");
	System.out.println("Total Pages Crawled Successfully: " + pagesCrawledSuccesfully.size());
	System.out.println("Total Pages failed to Crawl:" + pagesCrawledUnsuccesfully.size());*/
	}
}