import java.util.*;

// Course Class
class Course {

    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudents = new ArrayList<>();

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    boolean registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }

    void removeStudent(String studentId) {
        registeredStudents.remove(studentId);
    }

    int availableSlots() {
        return capacity - registeredStudents.size();
    }
}

// Student Class
class Student {

    String studentId;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    Student(String id, String name) {
        this.studentId = id;
        this.name = name;
    }
}

// Main System Class
public class StudentCourseRegistration {

    static List<Course> courses = new ArrayList<>();
    static Student student;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample Courses
        courses.add(new Course("CS101", "Java Programming", "Learn Java Basics", 3, "Mon 10AM"));
        courses.add(new Course("CS102", "Web Development", "HTML CSS JS", 2, "Tue 11AM"));
        courses.add(new Course("CS103", "Database Systems", "DBMS Concepts", 2, "Wed 2PM"));

        // Student Info
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        student = new Student(id, name);

        int choice;

        do {
            System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    displayCourses();
                    break;

                case 2:
                    registerCourse(sc);
                    break;

                case 3:
                    dropCourse(sc);
                    break;

                case 4:
                    viewRegisteredCourses();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    // Display Courses
    static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            System.out.println("\nCode: " + c.code);
            System.out.println("Title: " + c.title);
            System.out.println("Description: " + c.description);
            System.out.println("Schedule: " + c.schedule);
            System.out.println("Available Slots: " + c.availableSlots());
        }
    }

    // Register Course
    static void registerCourse(Scanner sc) {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();

        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {

                if (student.registeredCourses.contains(c)) {
                    System.out.println("Already Registered!");
                    return;
                }

                if (c.registerStudent(student.studentId)) {
                    student.registeredCourses.add(c);
                    System.out.println("Registration Successful!");
                } else {
                    System.out.println("Course Full!");
                }
                return;
            }
        }

        System.out.println("Course Not Found!");
    }

    // Drop Course
    static void dropCourse(Scanner sc) {
        System.out.print("Enter Course Code to Drop: ");
        String code = sc.nextLine();

        Iterator<Course> iterator = student.registeredCourses.iterator();

        while (iterator.hasNext()) {
            Course c = iterator.next();

            if (c.code.equalsIgnoreCase(code)) {
                c.removeStudent(student.studentId);
                iterator.remove();
                System.out.println("Course Dropped Successfully!");
                return;
            }
        }

        System.out.println("You are not registered in this course!");
    }

    // View Registered Courses
    static void viewRegisteredCourses() {
        System.out.println("\nRegistered Courses:");

        if (student.registeredCourses.isEmpty()) {
            System.out.println("No Courses Registered.");
            return;
        }

        for (Course c : student.registeredCourses) {
            System.out.println(c.code + " - " + c.title);
        }
    }
}

