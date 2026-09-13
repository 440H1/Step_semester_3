

public class ReferenceDeskSubclassReach {
    static class AccessChecker {

        static String classifyAccess(String fieldModifier, String accessorContext) {
            if (fieldModifier.equals("public"))
                return "ALLOWED";

            if (fieldModifier.equals("private"))
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            if (fieldModifier.equals("default"))
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

            if (fieldModifier.equals("protected"))
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE") || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) ? "ALLOWED" : "DENIED";

            return "DENIED";
        }

        static String describeContext(String accessorContext) {
            String[] words = accessorContext.toLowerCase().split("_");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }

            return result.toString().trim();
        }
    }
    public static void main(String[] args) {

        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(AccessChecker.describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}