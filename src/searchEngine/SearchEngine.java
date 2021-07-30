package searchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {

	Hashtable<String, Hashtable<String, List<Integer>>> hashTable;
	Hashmap myHashMap;

	public static void main(String[] args) {
		
		System.out.println("---------------------------------Page Ranking and Pattern Matching Started------------------------------\n");
		File inputDirectory = new File(Settings.TEXT_FILES_DIR);
		SearchEngine searchengine = new SearchEngine();
		searchengine.hashTable = new Hashtable<>();
		searchengine.myHashMap = new Hashmap();
		searchengine.myHashMap.scanFolder(searchengine.hashTable, inputDirectory);
		while (true) { // get input from user;
			String query = "";
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				do {
					int nums = 0;
					try {
						nums = searchingContinued();
					} catch (Exception e) {
						System.err.println("Sorry entered wrong input. Bye Bye !!!!");
						return;
					}
					if (nums == 0) {
						System.out.println("Bye Bye !!!!");

						System.out.println("\n--------------------------------Page Ranking and Pattern Matching ended----------------------------\n");
						System.out.println("\n                                     Web Search Engine Terminated                                    ");
						System.exit(0);
						
					} else {
						System.out.print("\nEnter a word for Searching : \n");
						query = br.readLine();
						String[] queryList = SearchEngine.splitTheString(query);
						printResponse(queryList, "Entered Word : ");
						SpellingChecker.spellChecker(queryList);
						searchengine.search(queryList);
					}
				} while (query != null);

			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

	public static String[] splitTheString(String query) {
		query = query.toLowerCase();
		query = query.replaceAll("[^a-zA-Z0-9 ]", " ");
		query = query.replaceAll("\\s+", " ").replaceAll("^\\s+", "");
		String[] queryList = query.split(" ");
		return queryList;
	}

	public static void printResponse(String[] input, String message) {
		System.out.print(message);
		for (int i = 0; i < input.length; i++) {
			System.out.print("[" + i + "]:" + input[i] + " ");
		}
		System.out.println("");
	}

	private static int searchingContinued() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.err.println("Enter any '1' to continue or '0' to Exit \n");
		return sc.nextInt();

	}

	private void search(String[] query) {
		List<Result> results = new ArrayList<Result>();
		Enumeration<String> names = hashTable.keys();
		while (names.hasMoreElements()) {
			String fileName = (String) names.nextElement();
			float pagerank = myHashMap.searchWordsInsideHashTable(hashTable.get(fileName), query);

			if (pagerank > 0) {
				Result r = new Result(fileName, pagerank);
				results.add(r);
			}
		}

		int numResults = results.size();
		System.out.println("\nNumber of files where the searched word is found : " + numResults);

		Collections.sort(results, new Comparator<Result>() {
			public int compare(Result a, Result b) {
				return (int) (b.pageRank - a.pageRank);
			}
		});

		// sorting  the results here
		for (int i = 0; i < Math.min(numResults, Settings.NUM_RESULTS_TO_DISPLAY); i++) {
			System.out.println("\nPage Rank = "+(i + 1));
		System.out.println("Occurences for the entered word are : "+ results.get(i).frequency);
		System.out.println("The word is found in the page  : "+ results.get(i).fileName.replace(Settings.TEXT_FILES_DIR + "/", ""));
		
		}
	}
}
