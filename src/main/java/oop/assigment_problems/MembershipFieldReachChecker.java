package oop.assigment_problems;

public class MembershipFieldReachChecker {

    static class AccessChecker {

        static String classifyAccess(String fieldModifier, String accessorContext) {

            if (fieldModifier.equals("private")) {
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            }

            if (fieldModifier.equals("default")) {
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";
            }

            if (fieldModifier.equals("protected")) {
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";
            }

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        static String summarizeByModifier(String[][] attempts) {

            int privateAllowed = 0, privateDenied = 0;
            int defaultAllowed = 0, defaultDenied = 0;
            int protectedAllowed = 0, protectedDenied = 0;
            int publicAllowed = 0, publicDenied = 0;

            for (String[] attempt : attempts) {

                String modifier = attempt[0];
                String context = attempt[1];

                boolean allowed =
                        classifyAccess(modifier, context).equals("ALLOWED");

                switch (modifier) {

                    case "private":
                        if (allowed) privateAllowed++;
                        else privateDenied++;
                        break;

                    case "default":
                        if (allowed) defaultAllowed++;
                        else defaultDenied++;
                        break;

                    case "protected":
                        if (allowed) protectedAllowed++;
                        else protectedDenied++;
                        break;

                    case "public":
                        if (allowed) publicAllowed++;
                        else publicDenied++;
                        break;
                }
            }

            return "private: " + privateAllowed + " allowed / "
                    + privateDenied + " denied | default: "
                    + defaultAllowed + " allowed / "
                    + defaultDenied + " denied | protected: "
                    + protectedAllowed + " allowed / "
                    + protectedDenied + " denied | public: "
                    + publicAllowed + " allowed / "
                    + publicDenied + " denied";
        }
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(
                String membershipId,
                String branchCode,
                double finesOwed,
                String displayName) {

            if (membershipId == null
                    || membershipId.trim().length() < 4) {

                throw new IllegalArgumentException(
                        "Invalid membership ID"
                );
            }

            this.membershipId = membershipId.trim();
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );

        LibraryMember member =
                new LibraryMember(
                        "LB94",
                        "BR1",
                        0,
                        "Priya Nair"
                );

        System.out.println(
                "Library member created successfully."
        );

        try {

            new LibraryMember(
                    "LB9",
                    "BR1",
                    0,
                    "Priya Nair"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected."
            );
        }
    }
}