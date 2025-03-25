import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class zaadanie {

public static int longest(String word) {
    ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('A', 'I', 'U', 'E', 'O', 'Y'));
    int maxLen = 0, currentLen = 0;
    for (char c : word.toUpperCase().toCharArray()) {
        if (!vowels.contains(c)) {
            currentLen++;
            maxLen = Math.max(maxLen, currentLen);
        } else {
            currentLen = 0;
        }
    }
    return maxLen;
}

    public static void main(String[] args){
        Scanner scanner;
        String text = "";
        try {
            scanner = new Scanner(new File("tekst.txt"));
            text = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // A)

        String[] words = text.split(" ");
        String[] letters;
        int num = 0;

        for(int i=0; i<words.length; i++){
            letters = words[i].split("");                               

            for(int j=0; j<letters.length-1; j++){
                if(letters[j].equals(letters[j + 1])){
                    num++;
                    break;
                }
            }
        }
        System.out.println("Slowa z dwoma kolejnymi literami: " + num);

        // B)

        String[] chars = text.replaceAll(" ", "").split("");
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, 0);
        int length = chars.length;

        for(int i=0; i<length; i++){
            alphabet[chars[i].charAt(0) - 'A']++;
        }
        
        for(int i=0; i<26; i++){
            double percentage = Math.round((double) alphabet[i] * 10000 / length) / 100.0;
            System.out.println((char) (i + 'A') + ": " + alphabet[i] + " (" + percentage + "%)");
        }

        // C)

        int maxLength = 0;
        int wordCount = 0;
        String firstMatchingWord = "";

        String[] wordss = text.split(" ");
        for (String word : wordss) {
            int longestInWord = longest(word);
            if (longestInWord > maxLength) {
                maxLength = longestInWord;
                wordCount = 1;
                firstMatchingWord = word;
            } else if (longestInWord == maxLength && longestInWord > 0) {
                wordCount++;
            }
        }
        
        System.out.println("Najdłuższa długość podsłowa ze spółgłoskami: " + maxLength);
        System.out.println("Liczba słów z takim podsłowem: " + wordCount);
        System.out.println("Pierwsze słowo z takim podsłowem: " + (!firstMatchingWord.isEmpty() ? firstMatchingWord : "Brak"));
        
}
}
