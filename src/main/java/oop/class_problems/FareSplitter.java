package oop.class_problems;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(
            String tripId,
            double totalFare,
            int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException(
                    "Fare cannot be negative"
            );
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                    "Passenger count must be positive"
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(
            String tripId,
            double totalFare) {

        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {

        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] breakdown =
                new double[passengerCount];

        if (totalFare == 0) {
            return breakdown;
        }

        double baseShare =
                Math.floor((totalFare / passengerCount) * 100)
                        / 100.0;

        double assigned = 0;

        for (int i = 0; i < passengerCount - 1; i++) {

            breakdown[i] = baseShare;
            assigned += baseShare;
        }

        breakdown[passengerCount - 1] =
                Math.round((totalFare - assigned) * 100)
                        / 100.0;

        return breakdown;
    }

    public boolean isConfirmationOverdue(
            int confirmed,
            int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter split =
                new FareSplitter(
                        "TRIP001",
                        100000,
                        3
                );

        double[] breakdown =
                split.fareBreakdown();

        System.out.print("Fare breakdown: [");

        for (int i = 0; i < breakdown.length; i++) {

            System.out.print(breakdown[i]);

            if (i < breakdown.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        double[] provisionalBreakdown =
                provisional.fareBreakdown();

        System.out.print(
                "Provisional breakdown: ["
        );

        for (int i = 0;
             i < provisionalBreakdown.length;
             i++) {

            System.out.print(provisionalBreakdown[i]);

            if (i < provisionalBreakdown.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        System.out.println(
                "Confirmation overdue: "
                        + split.isConfirmationOverdue(2, 3)
        );
    }
}