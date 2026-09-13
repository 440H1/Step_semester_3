

public class MembershipFieldReachChecker {
    static class AccessChecker {

        static String classifyAccess(String fieldModifier, String accessorContext) {
            if (fieldModifier.equals("public"))
                return "ALLOWED";

            if (fieldModifier.equals("private"))
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            if (fieldModifier.equals("default"))
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

            if (fieldModifier.equals("protected"))
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

            return "DENIED";
        }

        static String summarizeByModifier(String[][] attempts) {
            String[] modifiers = {"private", "default", "protected", "public"};
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < modifiers.length; i++) {
                int allowed = 0, denied = 0;

                for (String[] attempt : attempts) {
                    if (attempt[0].equals(modifiers[i])) {
                        if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                            allowed++;
                        else
                            denied++;
                    }
                }

                if (i > 0)
                    result.append(" | ");

                result.append(modifiers[i]).append(": ").append(allowed).append(" allowed / ").append(denied).append(" denied");
            }

            return result.toString();
        }
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
            String id = membershipId.trim();

            if (id.isEmpty() || id.length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.membershipId = id;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }
    public static void main(String[] args) {

        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}