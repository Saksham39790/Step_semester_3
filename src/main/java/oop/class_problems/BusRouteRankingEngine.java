package oop.class_problems;

public class BusRouteRankingEngine {

    static class BusRoute {

        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(
                String routeCode,
                String routeName,
                int priority) {

            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(
                String routeCode,
                String routeName) {

            this(routeCode, routeName, 0);
        }

        public int compareTo(BusRoute other) {

            // Higher priority comes first.
            if (this.priority != other.priority) {
                return other.priority - this.priority;
            }

            // If priority is tied, compare route codes
            // without changing their original case.
            int codeComparison =
                    this.routeCode.compareToIgnoreCase(
                            other.routeCode
                    );

            if (codeComparison != 0) {
                return codeComparison;
            }

            // If code is also tied, shorter route name comes first.
            if (this.routeName.length()
                    != other.routeName.length()) {

                return this.routeName.length()
                        - other.routeName.length();
            }

            // Returning 0 preserves the original order
            // when every comparison rule is tied.
            return 0;
        }

        static BusRoute[] rankRoutes(BusRoute[] routes) {

            BusRoute[] ranked = routes.clone();

            // Stable insertion sort.
            for (int i = 1; i < ranked.length; i++) {

                BusRoute current = ranked[i];
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

        String getRouteCode() {
            return routeCode;
        }
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute(
                        "RT205L",
                        "Airport Express",
                        3
                ),
                new BusRoute(
                        "rt201j",
                        "City Central",
                        4
                ),
                new BusRoute(
                        "RT299T",
                        "Night Service"
                )
        };

        BusRoute[] ranked =
                BusRoute.rankRoutes(routes);

        System.out.print("Ranked routes: [");

        for (int i = 0; i < ranked.length; i++) {

            System.out.print(
                    "\"" + ranked[i].getRouteCode() + "\""
            );

            if (i < ranked.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}