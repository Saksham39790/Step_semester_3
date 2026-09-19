package oop.class_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicketBookingValidator {

    static class BusTicket {

        private String passengerName;
        private String destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {

            if (passengerName == null ||
                    passengerName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Invalid passenger name"
                );
            }

            if (!passengerName.trim().matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException(
                        "Invalid passenger name"
                );
            }

            if (destination == null ||
                    destination.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Invalid destination"
                );
            }

            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
            this.checkedIn = false;
        }

        public void markCheckedIn() {

            if (checkedIn) {
                System.out.println(
                        passengerName
                                + " is already checked in."
                );
            } else {
                checkedIn = true;

                System.out.println(
                        passengerName
                                + " checked in successfully."
                );
            }
        }

        static void processBatch(String[][] rawBookings) {

            int valid = 0;
            int rejected = 0;
            int duplicates = 0;

            Set<String> acceptedBookings = new HashSet<>();

            for (String[] booking : rawBookings) {

                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                try {

                    BusTicket ticket =
                            new BusTicket(
                                    booking[0],
                                    booking[1]
                            );

                    String key =
                            ticket.passengerName
                                    + "|"
                                    + ticket.destination;

                    if (acceptedBookings.contains(key)) {
                        duplicates++;
                    } else {
                        acceptedBookings.add(key);
                        valid++;
                    }

                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            System.out.println(
                    "Valid: " + valid
                            + " | Rejected: " + rejected
                            + " | Duplicates skipped: " + duplicates
            );
        }
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(bookings);

        System.out.println();

        BusTicket ticket =
                new BusTicket("Karan", "Chennai");

        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}