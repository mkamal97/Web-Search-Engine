package searchEngine;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

public class SpellingChecker implements SpellCheckListener {
	private static SpellChecker spellCheck = null;

	public SpellingChecker() {
		try {
			SpellDictionary dictionary = new SpellDictionaryHashMap(new File(Settings.DICT_FILE));
			spellCheck = new SpellChecker(dictionary);
			spellCheck.addSpellCheckListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void spellingError(SpellCheckEvent event) {
		List suggestions = event.getSuggestions();
		if (suggestions != null && suggestions.size() > 0) {
			System.out.print("\nThe word you searched is misspelled  : " + event.getInvalidWord()+"\n");
			System.out.print("\nDid you mean?: ");
			int count = 0;
			for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
				if (count++ < 5) {
					System.out.print(suggestedWord.next());
				} else {
					break;
				}
				if (suggestedWord.hasNext() && count < 5) {
					System.out.print(", ");
				}
			}
		} else {
			System.out.println("No suggestions");
		}
	}

	// This method check user input spelling and return true or false, if false
	// print suggested input.
	public static Boolean spellChecker(String[] words) {
		new SpellingChecker();
		for (String word : words) {
			spellCheck.checkSpelling(new StringWordTokenizer(word));
		}
		return true;
	}
}
