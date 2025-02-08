/**
 * The BookWord class represents a word from a book, along with its occurrence count.
 */
public class BookWord implements Comparable<BookWord> {
    private String text;
    private int count;

    /**
     * Constructs a new {@code BookWord} instance with the specified word text.
     *
     * @param wordText the text of the word to be stored
     */
    public BookWord(String wordText) {
        this.text = wordText.toLowerCase();
        this.count = 1;
    }

    /**
     * Retrieves the text of the word stored in this {@code BookWord}.
     *
     * @return the text of the word in lowercase
     */
    public String getText() {
        return text;
    }

    /**
     * Retrieves the count of occurrences of the word.
     *
     * @return the count of this word
     */
    public int getCount() {
        return count;
    }

    /**
     * Increments the count of occurrences of this word by 1.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Compares this {@code BookWord} to another object for equality.
     *
     * @param wordToCompare the object to compare with this {@code BookWord}
     * @return {@code true} if the specified object is equal to this {@code BookWord}
     */
    @Override
    public boolean equals(Object wordToCompare) {
        if (wordToCompare != null && getClass() == wordToCompare.getClass()) {
            BookWord otherWord = (BookWord) wordToCompare;
            return text.equals(otherWord.text);
        }
        return false;
    }

    /**
     * Returns a hash code value for this {@code BookWord}.
     *
     * @return a hash code value for this {@code BookWord}
     */
    @Override
    public int hashCode() {
        //Reference: https://hostman.com/tutorials/overriding-the-hashcode-method-in-java/

        int total = 31;
        total = total * 31 + (text == null ? 0 : text.hashCode());
        return total;
    }

    /**
     * Returns a string representation of this {@code BookWord}.
     * The string format is "word: count"
     *
     * @return a string representation of this {@code BookWord}
     */
    @Override
    public String toString() {
        return text + ": " + count;
    }

    /**
     * Compares this {@code BookWord} with another {@code BookWord} for order.
     *
     * @param o the {@code BookWord} to be compared
     * @return a the relative order of this {@code BookWord} to the specified {@code BookWord}
     */
    @Override
    public int compareTo(BookWord o) {
        return text.compareTo(o.text);
    }
}
