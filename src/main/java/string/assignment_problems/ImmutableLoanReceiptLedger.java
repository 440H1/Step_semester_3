

public class ImmutableLoanReceiptLedger {
    static class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        private static int receiptCount;

        static {
            receiptCount = 0;
        }

        public LoanReceipt(String memberId, String[] bookIds) {

            if (memberId == null || bookIds == null)
                throw new IllegalArgumentException("construction rejected");

            for (String id : bookIds) {
                if (id == null || !id.matches("BK-\\d{3}"))
                    throw new IllegalArgumentException("construction rejected");
            }

            this.memberId = memberId;
            this.bookIds = bookIds.clone();
            receiptCount++;
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {

            if (index < 0 || index >= bookIds.length)
                throw new IndexOutOfBoundsException();

            if (newId == null || !newId.matches("BK-\\d{3}"))
                throw new IllegalArgumentException("invalid book ID");

            String[] corrected = bookIds.clone();
            corrected[index] = newId;

            return new LoanReceipt(memberId, corrected);
        }
    }

    static class ReferenceOnlyLoanReceipt extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {

        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}