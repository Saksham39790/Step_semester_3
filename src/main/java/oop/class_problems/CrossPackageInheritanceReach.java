package oop.class_problems;

public class CrossPackageInheritanceReach {

    static class AccessRuleEngine {

        static String classifyAccess(
                String fieldModifier,
                String accessorContext) {

            if (fieldModifier.equals("private")) {
                return accessorContext.equals("SAME_CLASS")
                        ? "ALLOWED"
                        : "DENIED";
            }

            if (fieldModifier.equals("default")) {

                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }

                return "DENIED";
            }

            if (fieldModifier.equals("protected")) {

                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }

                return "DENIED";
            }

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        static String describeContext(
                String accessorContext) {

            String[] words =
                    accessorContext.toLowerCase().split("_");

            StringBuilder result =
                    new StringBuilder();

            for (String word : words) {

                if (word.length() == 0) {
                    continue;
                }

                result.append(
                        Character.toUpperCase(word.charAt(0))
                );

                if (word.length() > 1) {
                    result.append(word.substring(1));
                }

                result.append(" ");
            }

            return result.toString().trim();
        }
    }

    public static void main(String[] args) {

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine.describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}
