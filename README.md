# Word Search and Proximity Analysis: Comparing Search Algorithms and Proximity Metrics

This project explores the performance of different word search algorithms and analyzes proximity metrics to better understand word occurrences in large textual datasets. Through this analysis, we compare the efficiency of multiple search techniques and examine how word proximity can reveal deeper patterns within a text.

## Key Features

- **Word Search Comparisons:**
  - The project compares three search methods: **Linear Search**, **Binary Search**, and **HashSet Search**.
  - We track the execution time of each method to assess their efficiency in finding words within large datasets.
  - The performance comparison provides insights into the strengths and weaknesses of each search algorithm, especially for large volumes of text.

- **Proximity Analysis:**
  - The proximity analysis calculates the distance between occurrences of specific words, offering valuable insights into word relationships and textual patterns.
  - By analyzing the distances between words like "war" and "peace" (or any other pair of words), we gain a better understanding of how concepts are interrelated and how far apart they tend to appear within the text.
  - The project shows how proximity can reveal thematic connections, context, and the spread of key ideas within a document.

## Performance Analysis

The performance results align with theoretical expectations:

- **HashSet Search** was the fastest at **6 ms**, which matches its O(1) average time complexity. This method provides direct access via hash codes, making it highly efficient.
- **Binary Search** took **14 ms**, showing efficient performance with sorted data due to its O(log n) complexity. This method halves the data range with each search, making it significantly faster than linear approaches for large datasets.
- **Linear Search** was significantly slower, taking **3,229 ms**, which is consistent with its O(n) complexity. It checks each element sequentially, resulting in a linear increase in time with the size of the dataset.

The total time of **3,229 ms** demonstrates the significant time cost of linear search. Without it, the combined execution time of Binary Search and HashSet Search would have been only **20 ms**, proving the clear efficiency advantages of optimized search methods (Binary and HashSet) when working with large datasets.

## What We Can Learn
- **Efficiency of Search Algorithms:** Understand the trade-offs between different search techniques when dealing with large-scale text analysis. Linear Search might work for smaller datasets, but HashSets and Binary Search offer significant performance improvements as data grows.
- **Proximity Insights:** Learn how proximity analysis helps identify the spread of key terms within a text and offers a deeper understanding of textual relationships.
- **Performance Metrics Comparison:** By comparing execution times across algorithms, we gain insights into which search method is optimal based on different use cases.

## Development Environment
- **Java Version**: 15 (Liberica JDK 15) or higher
- **IDE**: IntelliJ IDEA (Community Edition)

## How to Run
1. Clone the repository to your local machine.
2. Open the project in IntelliJ IDEA (Community Edition).
3. Ensure that you are using Java 15 (Liberica JDK 15) or higher.
4. Run the `Main.java` file to test the project in your machine.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
