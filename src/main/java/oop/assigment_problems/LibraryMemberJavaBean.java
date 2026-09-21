package oop.assigment_problems;

public class LibraryMemberJavaBean {

    static class LibraryMember {

        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswer;

        public LibraryMember() {
            this(null, null);
        }

        public LibraryMember(String name) {
            this(null, name);
        }

        public LibraryMember(
                String membershipId,
                String name) {

            this.membershipId = membershipId;
            this.name = name;
            this.premiumMember = false;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {

            // Write-once property.
            if (this.membershipId == null) {
                this.membershipId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(
                boolean premium) {

            this.premiumMember = premium;
        }

        public void setSecurityAnswer(
                String answer) {

            if (answer != null) {
                // Deterministic one-way transformation.
                this.securityAnswer =
                        Integer.toHexString(
                                answer.hashCode()
                        );
            }
        }
    }

    public static void main(String[] args) {

        LibraryMember nameOnly =
                new LibraryMember("Priya Nair");

        System.out.println(
                "Name-only ID: "
                        + nameOnly.getMembershipId()
        );

        LibraryMember fullMember =
                new LibraryMember(
                        "LIB-8841",
                        "Priya Nair"
                );

        System.out.println(
                "Full member ID: "
                        + fullMember.getMembershipId()
        );

        LibraryMember member =
                new LibraryMember();

        member.setMembershipId("LIB-8841");
        member.setMembershipId("FAKE-0000");

        System.out.println(
                "Write-once ID: "
                        + member.getMembershipId()
        );

        member.setSecurityAnswer("blue");

        System.out.println(
                "Security answer stored successfully."
        );
    }
}