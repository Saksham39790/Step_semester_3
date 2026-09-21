package oop.assigment_problems;

public class BookCopyCirculationGuard {

    static class BookInventory {

        private int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {

            if (copiesTotal <= 0) {
                throw new IllegalArgumentException(
                        "Copies total must be positive"
                );
            }

            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        public void checkOut() {

            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        public void checkIn() {

            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        public int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {

        BookInventory book =
                new BookInventory(3);

        book.checkOut();
        book.checkOut();
        book.checkOut();
        book.checkOut();

        System.out.println(
                "After 4 checkouts: "
                        + book.getCopiesAvailable()
        );

        book.checkIn();
        book.checkIn();
        book.checkIn();
        book.checkIn();

        System.out.println(
                "After 4 check-ins: "
                        + book.getCopiesAvailable()
        );

        try {

            new BookInventory(0);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected."
            );
        }
    }
}
