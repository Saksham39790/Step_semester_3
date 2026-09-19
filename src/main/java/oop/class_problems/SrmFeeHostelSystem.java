package oop.class_problems;

public class SrmFeeHostelSystem {

    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {
            pay(amount);
            pay(amount);
        }
    }

    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static HostelRoom safeAllot(
            HostelRoom[] rooms,
            String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
            return room;
        }

        return null;
    }

    static class SrmStudent {

        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(
                String name,
                String regNo,
                HostelFeeAccount feeAccount) {

            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;

            totalStudents++;
        }

        String fullStatus() {

            String roomStatus;

            if (room == null) {
                roomStatus = "unallotted";
            } else {
                roomStatus = room.roomNo;
            }

            return name
                    + " | Due: Rs "
                    + feeAccount.getDue()
                    + " | Room: "
                    + roomStatus;
        }
    }

    public static void main(String[] args) {

        HostelRoom[] rooms = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 1)
        };

        SrmStudent ravi = new SrmStudent(
                "Ravi",
                "RA001",
                new HostelFeeAccount("RA001", 200000, 0)
        );

        SrmStudent anitha = new SrmStudent(
                "Anitha",
                "RA002",
                new HostelFeeAccount("RA002", 180000, 0)
        );

        SrmStudent karthik = new SrmStudent(
                "Karthik",
                "RA003",
                new HostelFeeAccount("RA003", 200000, 0)
        );

        // Valid payment
        ravi.feeAccount.pay(60000);

        // Rejected payment because the amount is negative
        anitha.feeAccount.pay(-5000);

        ravi.room = safeAllot(rooms, ravi.name);
        anitha.room = safeAllot(rooms, anitha.name);

        // Karthik is intentionally left without a room.

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
                "Total students: " + SrmStudent.totalStudents
        );
    }
}
