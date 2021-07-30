# Web-Search-Engine
Created Web Search Engine in Java using data structures and algorithms.

The Web web search Engine will perform web crawling , followed by HTML to Text Conversion, Page Ranking, Pattern matching and Spell Checker.

Concepts used:

->Queues

->HashSet

->Regex

->Hash Maps

->JSoup

->Boyer Moore

->Merge Sort

Working of the web search engine :

->The Web Crawler crawls the entered URL by the user and obtains different unique links.

->using Queue of Strings which will contain all the URLs that need to be crawled during the execution.

->HashSet data structure is used to ensure all Unique links have been downloaded in the system.

->The HTML to text convertor converts the crawled web pages into text and stored using Hash Maps.

-> The word is searched using Boyer-Moore algorithm

->The Page ranker will use merge sort and sorts the pages based on occurences of the word.

->Pattern Matching will match the search word and show where the word is present.

->Spell Checker uses edit distance to suggest other revelant words.
