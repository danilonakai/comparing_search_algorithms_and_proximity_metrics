import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The Main class serves as the entry point for the application.
 */
public class Main {

    /**
     * Generates and displays the output for Part A of the analysis.
     *
     * @param totalOfWords the total number of words in the book
     * @param uniqueWords the total number of unique words in the book
     * @param filename the name of the file being analyzed
     * @param linearSearchWords the number of words not found in the dictionary using linear search
     * @param linearSearchTime the time taken for linear search in milliseconds
     * @param binarySearchWords the number of words not found in the dictionary using binary search
     * @param binarySearchTime the time taken for binary search in milliseconds
     * @param hashsetSearchWords the number of words not found in the dictionary using hashset search
     * @param hashsetSearchTime the time taken for hashset search in milliseconds
     * @param totalTimeA the total time taken for all searches in Part A in milliseconds
     */

    public static void generateOutputA(int totalOfWords, int uniqueWords, String filename, int linearSearchWords, long linearSearchTime, int binarySearchWords, long binarySearchTime, int hashsetSearchWords, long hashsetSearchTime, long totalTimeA){
        System.out.println("\n\nWord Search and Proximity Analysis: A Comprehensive Study of War and Peace\n");
        System.out.println("PART A\n======");
        System.out.printf("There are a total of %,d words in %s\n", totalOfWords, filename);
        System.out.printf("There are a total of %,d unique words in %s\n\n", uniqueWords, filename);

        System.out.println("Dictionary Search, timing for 3 methods:");
        System.out.printf("LINEAR SEARCH - %,d words not found in dictionary - time = %,d ms\n", linearSearchWords, linearSearchTime);
        System.out.printf("BINARY SEARCH - %,d words not found in dictionary - time = %,d ms\n", binarySearchWords, binarySearchTime);
        System.out.printf("HASHSET SEARCH - %,d words not found in dictionary - time = %,d ms\n", hashsetSearchWords, hashsetSearchTime);

        System.out.printf("TOTAL TIME for all of PART A = %,d ms\n\n", totalTimeA);
    }

    /**
     * Generates and displays the output for Part B of the analysis.
     *
     * @param totalSumOfDistances the total sum of distances between matched pairs 'war' and 'peace'
     * @param totalAvgDistance the average distance between matched pairs 'war' and 'peace'
     * @param totalTimeB the total runtime for Part B in milliseconds
     */
    public static void generateOutputB(long totalSumOfDistances, long totalAvgDistance, long totalTimeB) {
        System.out.println("PART B\n======");
        System.out.printf("The total sum of distances between the matched pairs 'war' and 'peace' = %,d\n", totalSumOfDistances);
        System.out.printf("The average distance between the matched pairs 'war' and 'peace' = %,d\n", totalAvgDistance);
        System.out.printf("TOTAL RUNTIME (Part A + Part B) = %,d ms\n", totalTimeB);
    }


    /**
     * The main method that serves as the entry point of the program.
     */
    public static void main(String[] args) {
        // =========================== PART A ===========================
        String bookFilename = "src/WarAndPeace.txt";
        String dictionaryFilename = "src/US.txt";

        ArrayList<BookWord> bookWords = loadFile(bookFilename);
        ArrayList<BookWord> uniqueWords = getUniqueWords(bookWords);
        ArrayList<BookWord> dictionaryList = loadFile(dictionaryFilename);
        Collections.sort(dictionaryList);
        SimpleHashSet<BookWord> dictionarySet = new SimpleHashSet<>();

        // Insert all words from the dictionary into a hashset for fast searching
        for (BookWord word : dictionaryList) {
            dictionarySet.insert(word);
        }

        // Count total words and unique words
        int totalWords = bookWords.size();
        int totalUniqueWords = uniqueWords.size();

        // LINEAR SEARCH
        long startTimeA = System.currentTimeMillis();
        int linearSearchWords = linearSearch(uniqueWords, dictionaryList);
        long endTimeA = System.currentTimeMillis();
        long linearSearchTime = endTimeA - startTimeA;

        // BINARY SEARCH
        startTimeA = System.currentTimeMillis();
        int binarySearchWords = binarySearch(uniqueWords, dictionaryList);
        endTimeA = System.currentTimeMillis();
        long binarySearchTime = endTimeA - startTimeA;

        // HASHSET SEARCH
        startTimeA = System.currentTimeMillis();
        int hashsetSearchWords = hashsetSearch(uniqueWords, dictionarySet);
        endTimeA = System.currentTimeMillis();
        long hashsetSearchTime = endTimeA - startTimeA;

        long totalTimeA = linearSearchTime + binarySearchTime + hashsetSearchTime;

        // Generate output for Part A
        generateOutputA(totalWords, totalUniqueWords, bookFilename, linearSearchWords, linearSearchTime, binarySearchWords, binarySearchTime, hashsetSearchWords, hashsetSearchTime, totalTimeA);


        // =========================== PART B ===========================
        long startTimeB = System.currentTimeMillis();
        int[] resultsB = calculateProximity(bookWords);
        int totalSumOfDistances = resultsB[0];
        int matches = resultsB[1];
        long totalAvgDistance = Math.round(totalSumOfDistances / (double) matches);
        long endTimeB = System.currentTimeMillis();
        long totalTimeB = endTimeB - startTimeB;
        long totalTimeAB = totalTimeA + totalTimeB;

        generateOutputB(totalSumOfDistances, totalAvgDistance, totalTimeAB);
    }

    /**
     * Performs a linear search to count the number of words in the book
     * that are not found in the provided dictionary.
     *
     * @param bookWords the list of unique words from the book
     * @param dictionarySet the list of words from the dictionary
     * @return the number of words from the book not found in the dictionary
     */
    public static int linearSearch(ArrayList<BookWord> bookWords, ArrayList<BookWord> dictionarySet) {
        int count = 0;

        for (BookWord bookWord : bookWords) {
            if (!dictionarySet.contains(bookWord)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Performs a binary search to count the number of words in the book
     * that are not found in the provided dictionary.
     *
     * @param bookWords the list of unique words from the book
     * @param dictionarySet the sorted list of words from the dictionary
     * @return the number of words from the book not found in the dictionary
     */
    public static int binarySearch(ArrayList<BookWord> bookWords, ArrayList<BookWord> dictionarySet) {
        int count = 0;

        for (BookWord bookWord : bookWords) {
            int index = Collections.binarySearch(dictionarySet, bookWord, (word1, word2) -> word1.getText().compareTo(word2.getText()));

            if (index < 0) {
                count++;
            }
        }

        return count;
    }

    /**
     * Uses a hashset to count the number of words in the book
     * that are not found in the provided dictionary.
     *
     * @param bookWords the list of unique words from the book
     * @param dictionarySet the hashset of words from the dictionary
     * @return the number of words from the book not found in the dictionary
     */
    public static int hashsetSearch(ArrayList<BookWord> bookWords, SimpleHashSet<BookWord> dictionarySet) {
        int count = 0;

        for (BookWord bookWord : bookWords) {
            if (!dictionarySet.contains(bookWord)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Loads the words from a specified file and returns them as an
     * ArrayList of {@code BookWord} objects. It uses a regex pattern
     * to delimit words.
     *
     * @param filename the path to the file to be loaded
     * @return an ArrayList of {@code BookWord} objects containing the words from the file
     */
    public static ArrayList<BookWord> loadFile(String filename){
        ArrayList<BookWord> words = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n";
            scanner.useDelimiter(regEx);

            while (scanner.hasNext()) {
                String word = scanner.next();

                if(!word.isEmpty()){
                    words.add(new BookWord(word));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    /**
     * Extracts unique words from the provided list of book words.
     */
    public static ArrayList<BookWord> getUniqueWords(ArrayList<BookWord> bookWords) {
        ArrayList<BookWord> uniqueWords = new ArrayList<>();

        for (BookWord word : bookWords) {
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords;
    }

    /**
     * Calculates the total proximity distance between occurrences of the words "war" and "peace" in a text.
     *
     * @param bookWords an ArrayList of BookWord objects representing the words in a book
     * @return An int array where the first element is the total distance between matched pairs, and the second element is the number of matches found.
     */
    public static int[] calculateProximity(ArrayList<BookWord> bookWords) {
        ArrayList<Integer> warList = new ArrayList<>();
        ArrayList<Integer> peaceList = new ArrayList<>();

        for (int i = 0; i < bookWords.size(); i++) {
            String wordText = bookWords.get(i).getText().toLowerCase();
            if (wordText.equals("war")) {
                warList.add(i + 1);
            } else if (wordText.equals("peace")) {
                peaceList.add(i + 1);
            }
        }

        int totalDistance = 0;
        int matches = 0;
        boolean[] usedWar = new boolean[warList.size()];

        for (int peacePos : peaceList) {
            int closestWarIndex = -1;
            int minDistance = 1_000_000;

            for (int i = 0; i < warList.size(); i++) {
                if (!usedWar[i]) {
                    int warPos = warList.get(i);
                    int distance = Math.abs(warPos - peacePos);

                    if (distance < minDistance) {
                        minDistance = distance;
                        closestWarIndex = i;
                    }
                }
            }

            if (closestWarIndex != -1) {
                totalDistance += minDistance;
                matches++;
                usedWar[closestWarIndex] = true;
            }
        }

        return new int[] {totalDistance, matches};
    }
}