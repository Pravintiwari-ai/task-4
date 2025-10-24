import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to Notes Manager ===");

        while (true) {
            System.out.println("\n1. Write a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Clear all notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    writeNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to write notes to file
    private static void writeNote() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("✅ Note saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // Method to read notes from file
    private static void readNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found. Create one first!");
            return;
        }

        System.out.println("\n=== Your Notes ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                System.out.println(count + ". " + line);
            }
            if (count == 0) {
                System.out.println("No notes yet!");
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // Method to clear all notes
    private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write("");
            System.out.println("🧹 All notes cleared!");
        } catch (IOException e) {
            System.out.println("❌ Error clearing notes: " + e.getMessage());
        }
    }
}