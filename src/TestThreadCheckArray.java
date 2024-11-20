import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TestThreadCheckArray class demonstrates the use of threads to process an array of integers
 * with a shared data object. It accepts input from the user, initializes shared data, and 
 * creates threads to perform operations on the array.
 * 
 * @author BADER BOSHNAK
 */
public class TestThreadCheckArray {

    /**
     * The main method initializes the shared data and threads, performs operations, 
     * and displays the results.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;

            // Prompt the user to enter the size of the array.
            System.out.println("Enter array size");
            int num = input.nextInt();

            // Initialize the array and populate it with user input.
            ArrayList<Integer> array = new ArrayList<>();
            System.out.println("Enter numbers for array");
            for (int index = 0; index < num; index++) 
                array.add(input.nextInt());

            // Prompt the user to enter a specific number for processing.
            System.out.println("Enter number");
            num = input.nextInt();

            // Create a SharedData object with the array and number.
            SharedData sd = new SharedData(array, num);

            // Initialize two threads that will work on the shared data.
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");

            // Start the threads.
            thread1.start();
            thread2.start();

            // Wait for both threads to finish execution.
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check the flag in shared data to determine the outcome.
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }

            // Display the results.
            System.out.println("Solution for b: " + sd.getB() + ", n = " + sd.getArray().size());
            System.out.print("I:    ");
            for (int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();
            System.out.print("A:    ");
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }

            System.out.println();
            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");
            }
        }  
    }  
}  
