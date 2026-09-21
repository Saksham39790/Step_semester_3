package oop.assigment_problems;

public class ImmutableLoanReceipt {

    static class LoanReceipt {

        private static String libraryName;

        private final String memberId;
        private final String[] bookIds;

        static {
            libraryName = "PageTurner Library";
        }

        public LoanReceipt(
                String memberId,
                String[] bookIds) {

            if (memberId == null) {
                throw new IllegalArgumentException(
                        "Member ID cannot be null"
                );
            }

            if (bookIds == null) {
                throw new IllegalArgumentException(
                        "Book IDs cannot be null"
                );
            }

            for (String bookId : bookIds) {

                if (bookId == null
                        || !bookId.matches("BK-\\d{3}")) {

                    throw new IllegalArgumentException(
                            "Invalid book ID"
                    );
                }
            }

            this.memberId = memberId;
            this.bookIds = bookIds.clone();
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index,
                String newId) {

            if (index < 0
                    || index >= bookIds.length) {

                throw new IllegalArgumentException(
                        "Invalid book ID index"
                );
            }

            if (newId == null
                    || !newId.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                        "Invalid book ID"
                );
            }

            String[] corrected =
                    bookIds.clone();

            corrected[index] = newId;

            return new LoanReceipt(
                    memberId,
                    corrected
            );
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt
                    instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt receipt =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{
                                "BK-100",
                                "BK-101"
                        }
                );

        String[] ids =
                receipt.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                "Original first book ID: "
                        + receipt.getBookIds()[0]
        );

        LoanReceipt corrected =
                receipt.withCorrectedBookId(
                        0,
                        "BK-999"
                );

        System.out.println(
                "Corrected first book ID: "
                        + corrected.getBookIds()[0]
        );

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );

        try {

            new LoanReceipt(
                    "LIB-8841",
                    new String[]{
                            "BK-100",
                            "bad"
                    }
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected."
            );
        }
    }
}