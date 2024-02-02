

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		int index = 0;

    	while (!in.isEmpty() && index < 3000) {
        	String word = in.readString();
        	dictionary[index++] = word;
    	}
    	in.close();
    	return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			String dictWord = dictionary[i];
			if (dictWord != null && dictWord.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0, i);
			if (existInDictionary(prefix, dictionary)) {
				System.out.println(prefix);
        	}
			breakHashTag(hashtag.substring(i), dictionary);
		}
		return;
	}
}
