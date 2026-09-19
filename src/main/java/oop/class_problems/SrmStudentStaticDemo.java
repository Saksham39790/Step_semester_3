package oop.class_problems;

public class SrmStudentStaticDemo {

    // Broken version: all fields are static.
    static class BrokenSrmStudent {

        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }
    }

    // Fixed version: student-specific data is instance data.
    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        static String university = "SRM Institute of Science and Technology";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {

            this.name = name;
            this.attendance = attendance;

            admissionCount++;

            this.regNo = "RA2311003010" + admissionCount;
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println(
                    "Students admitted so far: " + admissionCount
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA001", 82);

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Meera", "RA002", 74);

        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        /*
         * name, regNo and attendance must not be static because
         * each student needs independent values.
         * Static fields are shared by all objects, so creating
         * the second student overwrites the first student's data.
         */

        System.out.println();
        System.out.println("Fixed version:");

        SrmStudent ravi = new SrmStudent("Ravi", 82);
        SrmStudent meera = new SrmStudent("Meera", 74);

        ravi.printIdCard();
        meera.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}
