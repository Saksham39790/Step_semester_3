package oop.assigment_problems;

public class DeliverySlotBooking {

    static class DeliverySlot {

        private String orderId;
        private String timeSlot;

        public DeliverySlot(
                String orderId,
                String timeSlot) {

            this.orderId = orderId;
            this.timeSlot = timeSlot;
        }

        public DeliverySlot(String orderId) {
            this(orderId, "ASAP");
        }

        public boolean isPeakHour() {

            return timeSlot.equals("12:00-13:00")
                    || timeSlot.equals("13:00-14:00")
                    || timeSlot.equals("19:00-20:00")
                    || timeSlot.equals("20:00-21:00");
        }

        public void display() {

            System.out.println(
                    "Order ID: " + orderId
                            + " | Time Slot: " + timeSlot
                            + " | Peak Hour: " + isPeakHour()
            );
        }
    }

    public static void main(String[] args) {

        DeliverySlot scheduled =
                new DeliverySlot(
                        "ORD001",
                        "13:00-14:00"
                );

        DeliverySlot asap =
                new DeliverySlot("ORD002");

        scheduled.display();
        asap.display();
    }
}