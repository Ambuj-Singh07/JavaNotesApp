import java.io.*;
import java.util.Scanner;

public class NotesApp {
    static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting the Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    // Function to add a note
    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note + System.lineSeparator());
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Function to view notes
    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasNotes = false;

            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                hasNotes = true;
            }

            if (!hasNotes) {
                System.out.println("(No notes found)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found. Start by adding a note.)");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
