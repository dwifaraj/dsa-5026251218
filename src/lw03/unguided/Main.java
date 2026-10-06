package lw03.unguided;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();

        int rejectedAttempts = 0;

        InputStream registrationFile =
                Main.class.getResourceAsStream("registrations.txt");

        if (registrationFile == null) {
            System.out.println("registrations.txt tidak ditemukan.");
            return;
        }

        Scanner registrationScanner = new Scanner(registrationFile);

        while (registrationScanner.hasNextLine()) {
            String studentId = registrationScanner.nextLine();
            registeredStudents.add(studentId);
        }
        registrationScanner.close();

        System.out.println("===== Event Check-In Results =====");

        InputStream checkinFile =
                Main.class.getResourceAsStream("checkins.txt");

        if (checkinFile == null) {
            System.out.println("checkins.txt tidak ditemukan.");
            return;
        }
        Scanner checkinScanner = new Scanner(checkinFile);

        while (checkinScanner.hasNextLine()) {
            String studentId = checkinScanner.nextLine();

            if (!registeredStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedInStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedInStudents.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }
        checkinScanner.close();

        int registeredStudentsCount = registeredStudents.size();
        int successfulCheckins = checkedInStudents.size();
        int absentStudents = registeredStudentsCount - successfulCheckins;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudentsCount);
        System.out.println("Successful check-ins: " + successfulCheckins);
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}