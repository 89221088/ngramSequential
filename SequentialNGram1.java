import java.io.*;
import java.util.*;

public class SequentialNGram1 {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();

        try {
            System.out.println("Welcome to sequential mode!");
            System.out.print("Enter the file name: ");
            Scanner sc = new Scanner(System.in);
            String filepath = sc.nextLine();

            // Example file path: C:/Users/tinao/OneDrive/Radna površina/tina.txt
            // "C:\Users\tinao\Downloads\tina444.txt"
            File input = new File(filepath);

            Scanner sc1 = new Scanner(input);
            StringBuilder textBuilder = new StringBuilder();
            while (sc1.hasNextLine()) {
                textBuilder.append(sc1.nextLine()).append(" ");
            }
            String text = textBuilder.toString().trim();

            System.out.println("Enter n: ");
            int n = sc.nextInt();
            sc.nextLine();  // Consume newline left-over from nextInt()


            NGramInterface nGramAnalyzer = new Sequential();
            Map<String, Float> nGrams = nGramAnalyzer.count(text, n);

            Sequential calculator = new Sequential();
            Map<String, Float> probabilities = calculator.probability(nGrams);
            Map<String, Float> elements = calculator.weight(nGrams, probabilities);


                  /*  System.out.println("NGrams:  " + nGrams);
                    Map<String, Float> probabilities1 = nGramAnalyzer.probability(nGrams);
                    System.out.println("Probabilities : " + probabilities1);
                      System.out.println("weight: " + elements); */
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\tinao\\Downloads\\outputt.txt"))) {
                // Writing the nGrams to the file
                //System.out.println("NGrams: " + nGrams);
                System.out.println("NGram count : " + nGrams.size());
                writer.write("NGrams:  " + nGrams);
                writer.newLine(); // New line

                // Writing the probabilities to the file
                Map<String, Float> probabilities1 = nGramAnalyzer.probability(nGrams);

               // System.out.println("Probabilities : " + probabilities1);
                writer.write("Probabilities : " + probabilities1);
                writer.newLine(); // New line

                // Writing the weight (elements) to the file
               // System.out.println("Weight : " + elements);
                writer.write("Weight: " + elements);
                writer.newLine(); // New line
                long endTime = System.currentTimeMillis();

                // Calculate the time difference
                long duration = endTime - startTime;
                System.out.println("Execution time in milliseconds: " + duration);

                writer.write("execution time: " + duration);
                writer.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }




        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Record the end time


    }
}