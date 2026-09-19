package oop.assigment_problems;

public class CompanyEmployeeRecordSystem {

    static class Employee {

        private int empId;
        private String empName;
        private double salary;

        Employee(int empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {

        private double teamBonus;

        ManagerEmployee(
                int empId,
                String empName,
                double salary,
                double teamBonus) {

            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class ParkingSlot {

        String slotNo;
        int capacity;
        int occupiedCount;

        ParkingSlot(
                String slotNo,
                int capacity,
                int occupiedCount) {

            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot() {
            if (occupiedCount < capacity) {
                occupiedCount++;
            }
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static ParkingSlot safeAllot(ParkingSlot[] slots) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot();
            return slot;
        }

        return null;
    }

    static class CompanyEmployeeRecord {

        String name;
        String empId;
        Employee employee;
        ParkingSlot slot;

        static int totalRecords = 0;

        CompanyEmployeeRecord(
                String name,
                String empId,
                Employee employee) {

            this.name = name;
            this.empId = empId;
            this.employee = employee;

            totalRecords++;
        }

        String fullProfile() {

            double pay;

            if (employee instanceof ManagerEmployee) {
                ManagerEmployee manager =
                        (ManagerEmployee) employee;

                pay = manager.effectiveSalary();
            } else {
                pay = employee.getSalary();
            }

            String slotStatus;

            if (slot == null) {
                slotStatus = "no parking assigned";
            } else {
                slotStatus = slot.slotNo;
            }

            return name
                    + " | Pay: Rs "
                    + pay
                    + " | Slot: "
                    + slotStatus;
        }
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = {
                new ParkingSlot("A1", 1, 0),
                new ParkingSlot("A2", 1, 0)
        };

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E001",
                        new ManagerEmployee(
                                101,
                                "Divya",
                                70000,
                                8000
                        )
                );

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E002",
                        new Employee(
                                102,
                                "Karan",
                                40000
                        )
                );

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E003",
                        new Employee(
                                103,
                                "Meera",
                                10000
                        )
                );

        divya.slot = safeAllot(slots);
        karan.slot = safeAllot(slots);

        // Meera intentionally receives no parking.

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println(
                "Total records: "
                        + CompanyEmployeeRecord.totalRecords
        );
    }
}