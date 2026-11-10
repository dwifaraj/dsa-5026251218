package lw04.unquided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void bubbleSort(int[] arr, int startPass, int totalPasses) {
        int n = arr.length;

        for (int i = startPass; i < totalPasses; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void copyArray(int[] source, int[] destination) {
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    public static int[] binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                return new int[]{mid, steps};
            } else if (target > arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{-1, steps};
    }

    public static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("src/lw04/unquided/parcels.txt"));

            int N = input.nextInt();
            int[] parcels = new int[N];

            for (int i = 0; i < N; i++) {
                parcels[i] = input.nextInt();
            }

            int M = input.nextInt();
            int[] targets = new int[M];

            for (int i = 0; i < M; i++) {
                targets[i] = input.nextInt();
            }

            input.close();

            int[] afterTwoPasses = new int[N];

            bubbleSort(parcels, 0, 2);
            copyArray(parcels, afterTwoPasses);

            bubbleSort(parcels, 2, N - 1);

            System.out.println("==== After 2 Passes ====");
            displayArray(afterTwoPasses);

            System.out.println();
            System.out.println("==== Fully Sorted ====");
            displayArray(parcels);

            System.out.println();
            System.out.println("==== Search Results ====");

            for (int i = 0; i < M; i++) {
                int[] result = binarySearch(parcels, targets[i]);

                if (result[0] != -1) {
                    System.out.println(targets[i] +
                            ": FOUND at index " + result[0] +
                            " (steps: " + result[1] + ")");
                } else {
                    System.out.println(targets[i] +
                            ": NOT FOUND (steps: " + result[1] + ")");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File src/lw04/unquided/parcels.txt not found.");
        }
    }
}
