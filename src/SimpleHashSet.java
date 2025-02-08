import java.util.ArrayList;

public class SimpleHashSet<T> {

   // Array of buckets (ArrayList) to store elements
   private ArrayList<T>[] buckets;
   // Number of buckets in the hash set (default is 10)
   private int numberOfBuckets = 10;
   // Size of the hash set (number of elements)
   private int size = 0;
   // Threshold for resizing: when average bucket size exceeds this value, resize occurs
   private static final double AVERAGE_BUCKET_SIZE = 3;

   /**
    * Constructor to initialize the hash set with a default number of buckets.
    * Initializes all the buckets as empty ArrayLists.
    */
   public SimpleHashSet() {
      // Create an array of ArrayLists as buckets
      buckets = new ArrayList[numberOfBuckets];
      // Initialize each bucket as a new ArrayList
      for (int i = 0; i < numberOfBuckets; i++) {
         buckets[i] = new ArrayList<T>();
      }
      size = 0; // Initialize the size to 0
   }

   /**
    * Calculates the hash value for the element 'x' based on the specified hash size.
    * This is used to determine the bucket index for the element.
    *
    * @param x the element to be hashed
    * @param hashSize the size of the hash (number of buckets)
    * @return the computed hash value (bucket index)
    */
   private int getHash(T x, int hashSize) {
      // Return the hash value using modulus operation to map the element to a bucket
      return Math.abs(x.hashCode() % hashSize);
   }

   /**
    * Resizes the hash set by doubling the number of buckets and rehashing the elements.
    * This is called when the average bucket size exceeds the threshold.
    */
   private void resize() {
      // Double the number of buckets
      int newBucketsSize = numberOfBuckets * 2;
      ArrayList<T>[] newBuckets = new ArrayList[newBucketsSize];

      // Create new empty buckets
      for (int i = 0; i < newBucketsSize; i++) {
         newBuckets[i] = new ArrayList<T>();
      }

      // Rehash all elements from the old buckets and add them to the new buckets
      for (int i = 0; i < numberOfBuckets; i++) {
         for (T y : buckets[i]) {
            int hash = getHash(y, newBucketsSize);
            newBuckets[hash].add(y);
         }
      }

      // Update the buckets and number of buckets to reflect the new sizes
      buckets = newBuckets;
      numberOfBuckets = newBucketsSize;
   }

   /**
    * Inserts an element into the hash set.
    * If the element is already in the set, it won't be added again.
    * If necessary, the set will be resized to maintain a reasonable average bucket size.
    *
    * @param x the element to insert
    * @return true if the element was successfully added, false if it already exists
    */
   public boolean insert(T x) {
      // Calculate the hash value (bucket index) for the element
      int hash = getHash(x, numberOfBuckets);
      ArrayList<T> curBucket = buckets[hash];

      // If the element already exists in the bucket, return false
      if (curBucket.contains(x)) {
         return false;
      }

      // Otherwise, add the element to the bucket
      curBucket.add(x);

      // Resize the hash set if the average bucket size exceeds the threshold
      if ((float) size / numberOfBuckets > AVERAGE_BUCKET_SIZE) {
         resize();
      }

      // Increment the size and return true (element added)
      size++;
      return true;
   }

   /**
    * Checks if the element exists in the hash set.
    *
    * @param x the element to check
    * @return true if the element exists, false otherwise
    */
   public boolean contains(T x) {
      // Calculate the hash value (bucket index) for the element
      int hash = getHash(x, numberOfBuckets);
      ArrayList<T> curBucket = buckets[hash];

      // Return whether the element is found in the corresponding bucket
      return curBucket.contains(x);
   }

   /**
    * Removes an element from the hash set.
    *
    * @param x the element to remove
    * @return true if the element was successfully removed, false if it doesn't exist
    */
   public boolean remove(T x) {
      // Calculate the hash value (bucket index) for the element
      int hash = getHash(x, numberOfBuckets);
      ArrayList<T> curBucket = buckets[hash];

      // Try to remove the element from the bucket and return whether it was removed
      return curBucket.remove(x);
   }

   /**
    * Returns the total number of buckets in the hash set.
    *
    * @return the number of buckets
    */
   public int getNumberOfBuckets() {
      return numberOfBuckets;
   }

   /**
    * Returns the number of empty buckets in the hash set.
    *
    * @return the number of empty buckets
    */
   public int getNumberOfEmptyBuckets() {
      int empty = 0;
      // Iterate through all the buckets to count empty ones
      for (ArrayList<T> bucket : buckets) {
         if (bucket.isEmpty()) {
            empty++;
         }
      }
      return empty;
   }

   /**
    * Returns the current size of the hash set (number of elements).
    *
    * @return the size of the hash set
    */
   public int size() {
      return size;
   }

   /**
    * Returns the size of the largest bucket in the hash set.
    *
    * @return the size of the largest bucket
    */
   public int getLargestBucketSize() {
      int maxSize = 0;
      // Iterate through all the buckets to find the maximum size
      for (ArrayList<T> bucket : buckets) {
         if (bucket.size() > maxSize) {
            maxSize = bucket.size();
         }
      }
      return maxSize;
   }

   /**
    * Returns the average size of the buckets in the hash set.
    * This value is the total size divided by the number of buckets.
    *
    * @return the average bucket size
    */
   public double getAverageBucketSize() {
      return (double) size / numberOfBuckets;
   }

   /**
    * Optional method to print the current state of the hash set for debugging purposes.
    */
   public void printSet() {
      for (int i = 0; i < numberOfBuckets; i++) {
         System.out.println("Bucket " + i + ": " + buckets[i]);
      }
   }
}