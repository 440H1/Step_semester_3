

public class CrossPackageAccessDemo {
    static class AccessRuleEngine {

        static String classifyAccess(String fieldModifier, String accessorContext) {

            switch (fieldModifier) {

                case "public":
                    return "ALLOWED";

                case "private":
                    return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

                case "default":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

                case "protected":
                    if (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE") || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                        return "ALLOWED";

                    return "DENIED";
            }

            return "DENIED";
        }

        static String describeContext(String accessorContext) {

            String[] words = accessorContext.toLowerCase().split("_");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1));
                result.append(" ");
            }

            return result.toString().trim();
        }
    }
    public static void main(String[] args) {

        System.out.println(AccessRuleEngine.classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(AccessRuleEngine.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(AccessRuleEngine.describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}