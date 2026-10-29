
package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Read input from branches.txt
        Scanner sc = new Scanner(new File("branches.txt"));

        int N = sc.nextInt();
        int[] branches = new int[N];

        for (int i = 0; i < N; i++) {
            branches[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        // Insertion Sort descending for exactly 3 passes
        for (int i = 1; i <= 3; i++) {
            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }

        // Display the array after 3 passes
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }
        System.out.println();

        // Linear Search for each target
        for (int i = 0; i < M; i++) {
            int index = -1;

            for (int j = 0; j < N; j++) {
                if (branches[j] == targets[i]) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                System.out.println(
                    targets[i] + ": FOUND at index " + index
                );
            } else {
                System.out.println(
                    targets[i] + ": NOT FOUND"
                );
            }
        }
    }
}
