import java.io.*;
import java.util.*;

public class StudentManagement {

    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Student Record Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(sc); break;
                case 4: deleteStudent(sc); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addStudent(Scanner sc) throws IOException {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(id + "," + name + "," + course);
        writer.newLine();
        writer.close();

        System.out.println("Student added successfully.");
    }

    static void viewStudents() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        System.out.println("\nID\tName\tCourse");
        System.out.println("--------------------------------");
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            System.out.println(parts[0] + "\t" + parts[1] + "\t" + parts[2]);
        }
        reader.close();
    }

    static void searchStudent(Scanner sc) throws IOException {
        System.out.print("Enter ID to search: ");
        String searchId = sc.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(searchId)) {
                System.out.println("Record Found: " + parts[0] + " | " + parts[1] + " | " + parts[2]);
                found = true;
                break;
            }
        }
        reader.close();

        if (!found) System.out.println("No record found.");
    }

    static void deleteStudent(Scanner sc) throws IOException {
        System.out.print("Enter ID to delete: ");
        String deleteId = sc.nextLine();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean deleted = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (!parts[0].equals(deleteId)) {
                writer.write(line);
                writer.newLine();
            } else {
                deleted = true;
            }
        }

        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        if (deleted) System.out.println("Student deleted.");
        else System.out.println("ID not found.");
    }
}
