package oop.assigment_problems;

public class LibraryMembershipSystem {

    static class BrokenLibraryMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(
                String name,
                String memberId,
                int booksIssued) {

            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {

        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "Central Library";
        static int memberCount = 0;

        LibraryMember(String name, int booksIssued) {

            this.name = name;
            this.booksIssued = booksIssued;

            memberCount++;

            this.memberId = "LM-100" + memberCount;
        }

        void printMemberCard() {
            System.out.println(
                    name + " | " + memberId
            );
        }

        static void printTotalMembers() {
            System.out.println(
                    "Total members: " + memberCount
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember(
                        "Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember(
                        "Rohan", "LM-1002", 3);

        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        /*
         * name, memberId and booksIssued must be instance fields
         * because every library member has independent data.
         *
         * If they are static, all objects share the same values.
         * Therefore, creating Rohan overwrites Aditi's data.
         */

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember aditi =
                new LibraryMember("Aditi", 2);

        LibraryMember rohan =
                new LibraryMember("Rohan", 3);

        aditi.printMemberCard();
        rohan.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}
