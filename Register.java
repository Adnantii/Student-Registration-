// package project;

import java.util.Scanner;

abstract class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public abstract void display();
}

class RegularStudent extends Student {
    private int semester;

    public RegularStudent(String name, int id, int semester) {
        super(name, id);
        this.semester = semester;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void display() {
        System.out.println("Regular Student:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Semester: " + semester);
    }
}

class HonorsStudent extends Student {
    private String honorsProgram;

    public HonorsStudent(String name, int id, String honorsProgram) {
        super(name, id);
        this.honorsProgram = honorsProgram;
    }

    public String getHonorsProgram() {
        return honorsProgram;
    }

    public void setHonorsProgram(String honorsProgram) {
        this.honorsProgram = honorsProgram;
    }

    public void display() {
        System.out.println("Honors Student:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Honors Program: " + honorsProgram);
    }
}
class StudentRegistrationSystem {
    private static final int MAX_STUDENTS = 100;
    private Student[] students;
    private int numStudents;

    public StudentRegistrationSystem() {
        students = new Student[MAX_STUDENTS];
        numStudents = 0;
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====================");
            System.out.println("---- Edit Menu ----");
            System.out.println("====================");

            System.out.println("1. Update Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    System.out.println("Returning to the Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < numStudents; i++) {
            if (students[i].getId() == id) {
                System.out.println("Student found. Enter updated information:");
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                students[i].setName(name);

                if (students[i] instanceof RegularStudent) {
                    RegularStudent regularStudent = (RegularStudent) students[i];
                    System.out.print("Enter semester: ");
                    int semester = scanner.nextInt();
                    scanner.nextLine();
                    regularStudent.setSemester(semester);
                } else if (students[i] instanceof HonorsStudent) {
                    HonorsStudent honorsStudent = (HonorsStudent) students[i];
                    System.out.print("Enter honors program: ");
                    String honorsProgram = scanner.nextLine();
                    honorsStudent.setHonorsProgram(honorsProgram);
                }

                System.out.println("Student information updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < numStudents; i++) {
            if (students[i].getId() == id) {
                System.out.println("Student found. Deleting student...");
                for (int j = i; j < numStudents - 1; j++) {
                    students[j] = students[j + 1];
                }
                numStudents--;
                System.out.println("Student deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("\n------ Register Student -----");
        System.out.println("++++++++++++++++++++++++++++++++++");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select student type:");
        System.out.println("1. Regular Student");
        System.out.println("2. Honors Student");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Student student;
        if (choice == 1) {
            System.out.print("Enter semester: ");
            int semester = scanner.nextInt();
            scanner.nextLine();
            student = new RegularStudent(name, id, semester);
        } else if (choice == 2) {
            System.out.print("Enter honors program: ");
            String honorsProgram = scanner.nextLine();
            student = new HonorsStudent(name, id, honorsProgram);
        } else {
            System.out.println("Invalid choice. Registration canceled.");
            return;
        }

        if (numStudents < MAX_STUDENTS) {
            students[numStudents] = student;
            numStudents++;
            System.out.println("Student registered successfully.");
        } else {
            System.out.println("Maximum number of students reached. Registration failed.");
        }
    }

    public void view() {
        System.out.println("=================================");
        System.out.println("\n--- Registered Students ---");
        System.out.println("=================================");

        if (numStudents == 0) {
            System.out.println("No students registered.");
        } else {
            for (int i = 0; i < numStudents; i++) {
                System.out.println();
                students[i].display();
            }
        }
    }
}

public class Register {
    public static void main(String[] args) {
        StudentRegistrationSystem system = new StudentRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println("-------- Student Registration Board ---------");
            System.out.println("-----------------------------------------------");

            System.out.println("1. Register Student");
            System.out.println("2. View Registered Students");
            System.out.println("3. Edit");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    system.register();
                    break;
                case 2:
                    system.view();
                    break;
                case 3:
                    system.edit();
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}




// Adnan Tahir