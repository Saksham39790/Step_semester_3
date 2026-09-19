package oop.class_problems;

public class SrmAttendanceSystem {

    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void addAttendanceUpdate(int newAttendance) {
            attendance = newAttendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        // classAverage works on multiple students, so it belongs to the class.
        // isEligible depends on one particular student's attendance, so it is an instance method.
        static double classAverage(SrmStudent[] students) {

            int total = 0;

            for (SrmStudent student : students) {
                total += student.attendance;
            }

            return (double) total / students.length;
        }
    }

    public static void main(String[] args) {

        SrmStudent[] students = {
                new SrmStudent("Ravi", "RA001", 82),
                new SrmStudent("Anitha", "RA002", 68),
                new SrmStudent("Karthik", "RA003", 91),
                new SrmStudent("Meera", "RA004", 74),
                new SrmStudent("Suresh", "RA005", 60)
        };

        for (SrmStudent student : students) {

            String status;

            if (student.isEligible()) {
                status = "Eligible";
            } else {
                status = "Detained";
            }

            System.out.println(
                    student.name + " - "
                            + student.attendance + "% - "
                            + status
            );
        }

        double average = SrmStudent.classAverage(students);

        System.out.println("Class average: " + average + "%");
    }
}
