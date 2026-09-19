package oop.assigment_problems;

public class MultiKitchenReconciliationEngine {

    static class DeliveryAccount {

        private static String kitchenName;

        private String orderId;
        private double orderValue;
        private double amountPaid;

        static {
            kitchenName = "Central Kitchen";
        }

        public DeliveryAccount(
                String orderId,
                double orderValue) {

            if (orderValue < 0) {
                throw new IllegalArgumentException(
                        "Order value cannot be negative"
                );
            }

            this.orderId = orderId;
            this.orderValue = orderValue;
            this.amountPaid = 0;
        }

        public DeliveryAccount(String orderId) {
            this(orderId, 0);
        }

        void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        final double calculateSurgeFee(
                int delayMinutes) {

            if (delayMinutes < 0) {
                throw new IllegalArgumentException(
                        "Delay minutes cannot be negative"
                );
            }

            if (delayMinutes == 0) {
                return 0.0;
            }

            double surgeFee = 0.0;

            // Minutes 1-5: 0.5%
            int firstTier =
                    Math.min(delayMinutes, 5);

            surgeFee +=
                    firstTier * orderValue * 0.005;

            // Minutes 6-15: 1%
            if (delayMinutes > 5) {

                int secondTier =
                        Math.min(delayMinutes, 15) - 5;

                surgeFee +=
                        secondTier * orderValue * 0.01;
            }

            // Minutes 16 onward: 2%
            if (delayMinutes > 15) {

                int thirdTier =
                        delayMinutes - 15;

                surgeFee +=
                        thirdTier * orderValue * 0.02;
            }

            return surgeFee;
        }

        void processAccount(
                DeliveryAccount account,
                double amount,
                int delayMinutes) {

            if (account == null) {
                return;
            }

            account.pay(amount);

            double surgeFee =
                    account.calculateSurgeFee(
                            delayMinutes
                    );

            System.out.println(
                    account.orderId
                            + " | Surge Fee: Rs "
                            + surgeFee
            );
        }

        static void processBatch(
                DeliveryAccount[] accounts,
                double[] amounts,
                int[] delays) {

            int processed = 0;
            int nullSkipped = 0;
            int premiumCount = 0;
            int regularCount = 0;

            double grandTotalSurgeFees = 0.0;

            int limit = Math.min(
                    accounts.length,
                    Math.min(
                            amounts.length,
                            delays.length
                    )
            );

            for (int i = 0; i < limit; i++) {

                DeliveryAccount account =
                        accounts[i];

                if (account == null) {
                    nullSkipped++;
                    continue;
                }

                account.pay(amounts[i]);

                double surgeFee =
                        account.calculateSurgeFee(
                                delays[i]
                        );

                grandTotalSurgeFees += surgeFee;
                processed++;

                if (account instanceof PremiumDeliveryAccount) {
                    premiumCount++;
                } else {
                    regularCount++;
                }
            }

            System.out.println(
                    processed
                            + " processed | "
                            + nullSkipped
                            + " null skipped | "
                            + premiumCount
                            + " premium | "
                            + regularCount
                            + " regular |"
            );

            System.out.println(
                    "grand total surge fees = Rs "
                            + grandTotalSurgeFees
            );
        }
    }

    static class PremiumDeliveryAccount
            extends DeliveryAccount {

        public PremiumDeliveryAccount(
                String orderId,
                double orderValue) {

            super(orderId, orderValue);
        }

        public PremiumDeliveryAccount(
                String orderId) {

            this(orderId, 0);
        }
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount(
                        "STU001",
                        500
                ),
                null,
                new DeliveryAccount(
                        "STU002",
                        300
                )
        };

        double[] amounts = {
                500,
                400,
                300
        };

        int[] delays = {
                10,
                5,
                0
        };

        DeliveryAccount.processBatch(
                accounts,
                amounts,
                delays
        );
    }
}