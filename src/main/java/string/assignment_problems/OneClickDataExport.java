public class OneClickDataExport {

    interface Exportable {
        String exportData();
    }

    static class ExportCounter {
        private static int totalExports = 0;

        static void increment() {
            totalExports++;
        }

        static int getTotalExports() {
            return totalExports;
        }
    }

    static class ReportGenerator implements Exportable {
        private String reportName;

        ReportGenerator(String reportName) {
            this.reportName = reportName;
        }

        public String exportData() {
            ExportCounter.increment();
            return "Exported report: " + reportName;
        }

        static int getTotalExports() {
            return ExportCounter.getTotalExports();
        }
    }

    static class UserProfile implements Exportable {
        private String username;

        UserProfile(String username) {
            this.username = username;
        }

        public String exportData() {
            ExportCounter.increment();
            return "Exported profile: " + username;
        }
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales 01");
        System.out.println(r.exportData());

        UserProfile u = new UserProfile("jane_doe");
        System.out.println(u.exportData());

        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println(ReportGenerator.getTotalExports());
    }
}
