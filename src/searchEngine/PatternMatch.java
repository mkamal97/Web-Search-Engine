package searchEngine;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

public class PatternMatch {
	
	private static File folder = new File(Settings.TEXT_FILES_DIR);	
	
	public static void main( String args[] ) throws IOException{
		String search_pattern = "(.*)Standards Participate Membership About W3C Site(.*)";
		Pattern patternSearch = Pattern.compile(search_pattern, Pattern.CASE_INSENSITIVE);
		System.out.println("The given word is found in the converted text file and the name of the path is : \n");
		findMatch(patternSearch);
	}

	public static void findMatch(Pattern searchPattern) {
		File[] listOff = folder.listFiles();
		for (int i = 0; i < listOff.length; i++) {
			File file = listOff[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				String fileContent;
				try {
					fileContent = FileUtils.readFileToString(file);
					String textofFile = Jsoup.parse(fileContent.toString()).text();				
					System.out.print("Return Value :" );
					System.out.println(textofFile.matches("(.*)W3C technical report development process(.*)"));

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}