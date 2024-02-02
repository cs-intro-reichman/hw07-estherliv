
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() <= 1) {
			return "";
		} else {
			return str.substring(1);
		}
	}

	public static int levenshtein(String word1, String word2) {
		int m = word1.length();
    	int n = word2.length();

    	int[][] distanceArray = new int[m + 1][n + 1]; // distance array, to keep the edit distances
    	for (int i = 0; i <= m; i++) {
			distanceArray[i][0] = i;
		}
    	for (int j = 1; j <= n; j++) {
        	distanceArray[0][j] = j;
    	}

    	for (int i = 1; i <= m; i++) {
        	for (int j = 1; j <= n; j++) {
            	int substitutions = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1; // calculates the substitutions 
            	int minDistance = Math.min(Math.min(distanceArray[i - 1][j] + 1, distanceArray[i][j - 1] + 1), distanceArray[i - 1][j - 1] + substitutions);
            	distanceArray[i][j] = minDistance;
        	}
    	}
    	return distanceArray[m][n];
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

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closestWord = word;
		int minDistance = -1;

		for (int i = 0; i < dictionary.length; i++) {
			String dictWord = dictionary[i];
			int distance = levenshtein(word, dictWord);
			if (minDistance == -1 || distance < minDistance) {
				minDistance = distance;
				closestWord = dictWord;
			}
		}
		if (minDistance <= threshold) {
			return closestWord;
		} else {
			return word;
		}
	}

}
