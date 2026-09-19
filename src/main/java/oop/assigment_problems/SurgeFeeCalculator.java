package oop.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                    "Order value cannot be negative"
            );
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay minutes cannot be negative"
            );
        }

        // No surge fee when there is no delay.
        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgeFee = 0.0;

        // Minutes 1-5: 0.5% per minute
        int firstTier = Math.min(delayMinutes, 5);
        surgeFee += firstTier * orderValue * 0.005;

        // Minutes 6-15: 1% per minute
        if (delayMinutes > 5) {
            int secondTier =
                    Math.min(delayMinutes, 15) - 5;

            surgeFee += secondTier * orderValue * 0.01;
        }

        // Minute 16 onward: 2% per minute
        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;

            surgeFee += thirdTier * orderValue * 0.02;
        }

        // Minimum surge floor applies only when delayed.
        double minimumSurge =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(surgeFee, minimumSurge);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                "Surge fee for 0 minutes: Rs "
                        + calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
                "Surge fee for 1 minute: Rs "
                        + calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
                "Surge fee for 16 minutes: Rs "
                        + calculator.calculateSurgeFee(500, 16)
        );
    }
}