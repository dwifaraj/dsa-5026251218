package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        System.out.println("===== Problem 1 =====");

        List<String> playlist = new ArrayList<>();

        try {
            Scanner sc = new Scanner(
                new File("src/lw03/prelab/playlist.txt")
            );

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(" ", 3);
                String operation = data[0];

                if (operation.equals("ADD")) {
                    String song = line.substring(4);
                    playlist.add(song);

                } else if (operation.equals("INSERT")) {
                    int index = Integer.parseInt(data[1]);
                    String song = data[2];

                    if (index >= 0 && index <= playlist.size()) {
                        playlist.add(index, song);
                    }

                } else if (operation.equals("REMOVE")) {
                    String song = line.substring(7);
                    playlist.remove(song);
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("playlist.txt tidak ditemukan.");
            return;
        }

        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    public static void problem2() {
        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        try {
            Scanner sc = new Scanner(
                new File("src/lw03/prelab/participants.txt")
            );

            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    continue;
                }

                if (!participants.add(name)) {
                    duplicates++;
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("participants.txt tidak ditemukan.");
            return;
        }

        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicates);
    }

    // PROBLEM 3: INVENTORY MENGGUNAKAN MAP
    public static void problem3() {
        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            Scanner sc = new Scanner(
                new File("src/lw03/prelab/inventory.txt")
            );

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\s+");

                String operation = data[0];
                String product = data[1];
                int quantity = Integer.parseInt(data[2]);

                if (operation.equals("ADD")) {
                    int stock = inventory.getOrDefault(product, 0);
                    inventory.put(product, stock + quantity);

                } else if (operation.equals("SELL")) {
                    if (inventory.containsKey(product)
                            && inventory.get(product) >= quantity) {

                        int stock = inventory.get(product);
                        inventory.put(product, stock - quantity);

                    } else {
                        failedSales++;
                    }
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("inventory.txt tidak ditemukan.");
            return;
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(
                entry.getKey() + ": " + entry.getValue()
            );
        }

        System.out.println("Failed sales: " + failedSales);
    }
}