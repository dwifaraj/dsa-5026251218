package lw02.lw2.Prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> q = new LinkedList<>();
        Stack<String[]> fails = new Stack<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = scanner.next(); // name
            transaction[1] = scanner.next(); // type
            transaction[2] = scanner.next(); // amount

            transactions.add(transaction);
        }

        scanner.close();

        q.addAll(transactions);

        while (!q.isEmpty()) {
            String[] transaction = q.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;
            for (String[] data : customers) {
                if (data[0].equals(name)) {
                    customer = data;
                    break;
                }
            }

            if (customer == null) {
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);
            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else {
                if (balance >= amount) {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    fails.push(transaction);
                }
            }
        }
        System.out.println("=== Final Balances ===");
        for (String[] hi : customers) {
            System.out.println (hi[0] + " : " + hi[1]);
        }
        System.out.println("=== Failed Transactions ===");
        while (!fails.isEmpty()) {
            String[] y = fails.pop();
            System.out.println(y[0] + " " + y[1] + " " + y[2]);
        }
    }
}