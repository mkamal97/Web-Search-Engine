package searchEngine;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.Jsoup;

public class html2textconverter {

	public static void main(String[] args) 
	
	{
		
		System.out.println("--------------------------------HTML to Text Conversion Started-----------------------------------\n");
		
		System.out.println("\nConverting the Html pages obtained through WebCrawler into Text files in 'Text_Files_Converted' folder using Jsoup");
		
		
		
		System.out.println("\nPlease Wait while the files are Converted...................\n");
		
		
		
		html2textconverter HtoT= new html2textconverter();  						

		try
		{
			HtoT.htmlconverter();
		}
		catch(IOException e)
		{
			System.out.println("IO exception "+e);
		}
	}
	public void htmlconverter() throws IOException
	{
		File F = new File("C:\\Users\\HOME\\eclipse-workspace-ACC_Final_Project_\\Search Engine\\Crawled_Web_Pages/");
		File[] All = F.listFiles(); 
		
		for(int i=0; i < All.length; i++)
	    {
			if(All[i].isFile())
	     	{
	     		File fname = new File("C:\\Users\\HOME\\eclipse-workspace-ACC_Final_Project_\\Search Engine\\Crawled_Web_Pages/"+All[i].getName());
	     		org.jsoup.nodes.Document doc = Jsoup.parse(fname,"UTF-8");
	     		String t=doc.text();
	   			PrintWriter output = new PrintWriter("C:\\Users\\HOME\\eclipse-workspace-ACC_Final_Project_\\Search Engine\\Crawled_Web_Pages\\Text_Files_Converted/" +All[i].getName()+".txt");
	   			output.println(t);	     			
	   			output.close();
	   		
     		}
	    }
		System.out.println("Text files generated and stored in 'Text_Files_Converted' folder\n");
		System.out.println("\n----------------------------HTML_TO_TEXT CONVERSION SUCCESSFUL--------------------------------\n");
		System.out.println("\n                         Time for Page Ranking and Pattern Matching                              ");                         
	
	}
}