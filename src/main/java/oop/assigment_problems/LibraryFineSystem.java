package oop.assigment_problems;

public class LibraryFineSystem {

    static class BookIssue {

        String title;
        String borrowerName;
        int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {

            if (daysOverdue > 0) {
                return daysOverdue * 5;
            }

            return 0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        // This method works on multiple BookIssue objects,
        // so it belongs to the class rather than one particular book.
        // fineAmount() is not static because it depends on one book's data.
        static double totalFineCollected(BookIssue[] issues) {

            double total = 0;

            for (BookIssue issue : issues) {
                total += issue.fineAmount();
            }

            return total;
        }
    }

    public static void main(String[] args) {

        BookIssue[] issues = {
                new BookIssue("Clean Code", "Aditi", 18),
                new BookIssue("Effective Java", "Rohan", 5),
                new BookIssue("Refactoring", "Karan", 0),
                new BookIssue("DSA Handbook", "Meera", 21),
                new BookIssue("Design Patterns", "Arjun", 9)
        };

        for (BookIssue issue : issues) {

            String status;

            if (issue.isSeverelyOverdue()) {
                status = "Severely overdue";
            } else {
                status = "OK";
            }

            System.out.println(
                    issue.title
                            + " - "
                            + issue.daysOverdue
                            + " days - "
                            + status
            );
        }

        double total =
                BookIssue.totalFineCollected(issues);

        System.out.println(
                "Total fine collected: Rs " + total
        );
    }
}
