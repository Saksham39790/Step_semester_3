package oop.class_problems;

public class FeeAccountSystem {

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

    static class ScholarshipFeeAccount extends FeeAccount {

        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee,
                              double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {

            double due = getDue();

            return due - (due * scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount(
                "RA001", 150000, 0
        );

        HostelFeeAccount hostel = new HostelFeeAccount(
                "RA002", 200000, 0
        );

        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount(
                "RA003", 180000, 0, 20
        );

        plain.pay(150000);

        hostel.pay(60000);

        scholarship.pay(0);

        FeeAccount[] accounts = {
                plain,
                hostel,
                scholarship
        };

        for (FeeAccount account : accounts) {

            if (account instanceof HostelFeeAccount) {

                HostelFeeAccount hostelAccount =
                        (HostelFeeAccount) account;

                hostelAccount.payInTwoInstallments(0);

                System.out.println(
                        "Hostel account due: Rs "
                                + hostelAccount.getDue()
                );

            } else if (account instanceof ScholarshipFeeAccount) {

                ScholarshipFeeAccount scholarshipAccount =
                        (ScholarshipFeeAccount) account;

                System.out.println(
                        "Scholarship account effective due: Rs "
                                + scholarshipAccount.effectiveDue()
                );

            } else {

                System.out.println(
                        "Plain account due: Rs "
                                + account.getDue()
                );
            }
        }
    }
}