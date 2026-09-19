package oop.assigment_problems;

public class CanteenTrustScoreRankingEngine {

    static class Canteen {

        private String canteenCode;
        private String canteenName;
        private int trustScore;

        public Canteen(
                String canteenCode,
                String canteenName,
                int trustScore) {

            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(
                String canteenCode,
                String canteenName) {

            this(canteenCode, canteenName, 3);
        }

        public int compareTo(Canteen other) {

            // Higher trust score comes first.
            if (this.trustScore != other.trustScore) {
                return other.trustScore - this.trustScore;
            }

            // If score is tied, compare code.
            int codeComparison =
                    this.canteenCode.compareToIgnoreCase(
                            other.canteenCode
                    );

            if (codeComparison != 0) {
                return codeComparison;
            }

            // If code is also tied, shorter name comes first.
            return this.canteenName.length()
                    - other.canteenName.length();
        }

        static Canteen[] rankCanteens(Canteen[] canteens) {

            Canteen[] ranked = canteens.clone();

            // Manual stable insertion sort.
            for (int i = 1; i < ranked.length; i++) {

                Canteen current = ranked[i];
                int j = i - 1;

                while (j >= 0
                        && ranked[j].compareTo(current) > 0) {

                    ranked[j + 1] = ranked[j];
                    j--;
                }

                ranked[j + 1] = current;
            }

            return ranked;
        }

        public String getCanteenCode() {
            return canteenCode;
        }
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
                new Canteen(
                        "HB3-C",
                        "Spice Junction",
                        3
                ),
                new Canteen(
                        "hb1-c",
                        "Grand Mess",
                        5
                ),
                new Canteen(
                        "HB2-C",
                        "Southern Treats"
                )
        };

        Canteen[] ranked =
                Canteen.rankCanteens(canteens);

        System.out.print("Ranked canteens: [");

        for (int i = 0; i < ranked.length; i++) {

            System.out.print(
                    "\"" + ranked[i].getCanteenCode() + "\""
            );

            if (i < ranked.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}