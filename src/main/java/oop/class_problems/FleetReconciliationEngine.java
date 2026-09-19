package oop.class_problems;

public class FleetReconciliationEngine {

    static class BusTicketAccount {

        private static String depotName;

        private String bookingId;
        private double ticketFare;
        private double amountPaid;

        static {
            depotName = "Central Depot";
        }

        public BusTicketAccount(
                String bookingId,
                double ticketFare) {

            if (ticketFare < 0) {
                throw new IllegalArgumentException(
                        "Ticket fare cannot be negative"
                );
            }

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
            this.amountPaid = 0;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0);
        }

        void pay(double amount) {
            if (amount > 0) {
                amountPaid += amount;
            }
        }

        final double calculatePenalty(int minutesLate) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                        "Minutes late cannot be negative"
                );
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            double penalty = 0.0;

            int firstTier = Math.min(minutesLate, 5);
            penalty += firstTier * ticketFare * 0.005;

            if (minutesLate > 5) {
                int secondTier =
                        Math.min(minutesLate, 15) - 5;

                penalty += secondTier * ticketFare * 0.01;
            }

            if (minutesLate > 15) {
                int thirdTier = minutesLate - 15;

                penalty += thirdTier * ticketFare * 0.02;
            }

            return penalty;
        }

        void processAccount(
                BusTicketAccount account,
                double amount,
                int minutesLate) {

            if (account == null) {
                return;
            }

            account.pay(amount);

            System.out.println(
                    account.bookingId
                            + " | Penalty: Rs "
                            + account.calculatePenalty(minutesLate)
            );
        }

        static void processBatch(
                BusTicketAccount[] accounts,
                double[] amounts,
                int[] minutesLateArray) {

            int processed = 0;
            int nullSkipped = 0;
            int sleeperCount = 0;
            int regularCount = 0;

            double grandTotalPenalty = 0.0;

            int limit = Math.min(
                    accounts.length,
                    Math.min(
                            amounts.length,
                            minutesLateArray.length
                    )
            );

            for (int i = 0; i < limit; i++) {

                BusTicketAccount account =
                        accounts[i];

                if (account == null) {
                    nullSkipped++;
                    continue;
                }

                account.pay(amounts[i]);

                double penalty =
                        account.calculatePenalty(
                                minutesLateArray[i]
                        );

                grandTotalPenalty += penalty;
                processed++;

                if (account instanceof SleeperAccount) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }
            }

            System.out.println(
                    processed
                            + " processed | "
                            + nullSkipped
                            + " null skipped | "
                            + sleeperCount
                            + " sleeper | "
                            + regularCount
                            + " regular |"
            );

            System.out.println(
                    "grand total penalties = Rs "
                            + grandTotalPenalty
            );
        }
    }

    static class SleeperAccount
            extends BusTicketAccount {

        public SleeperAccount(
                String bookingId,
                double ticketFare) {

            super(bookingId, ticketFare);
        }

        public SleeperAccount(String bookingId) {
            this(bookingId, 0);
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
                new SleeperAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLateArray = {
                10,
                5,
                0
        };

        BusTicketAccount.processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}