package lw01.lw1.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        Map<String, BiFunction<String, Integer, PrintJob>> jobTypes = new HashMap<>();
        jobTypes.put("MONO", MonoPrint::new);
        jobTypes.put("COLOUR", ColourPrint::new);

        Scanner scanner = new Scanner(new File("jobs.txt"));

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            jobs.add(jobTypes.get(type).apply(id, pages));
        }

        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}