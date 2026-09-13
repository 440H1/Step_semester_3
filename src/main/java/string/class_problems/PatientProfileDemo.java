import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PatientProfileDemo {
    static class PatientProfile {

        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPinHash;
        private boolean patientIdSet;

        public PatientProfile() {
            this(null, null);
        }

        public PatientProfile(String name) {
            this(null, name);
        }

        public PatientProfile(String patientId, String name) {
            this.patientId = patientId;
            this.name = name;
            this.patientIdSet = patientId != null;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String id) {

            if (!patientIdSet) {
                patientId = id;
                patientIdSet = true;
            }
        }

        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        public void setLockerPin(String pin) {

            if (pin == null || !pin.matches("\\d{4,6}"))
                return;

            lockerPinHash = hash(pin);
        }

        private String hash(String pin) {

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] result = md.digest(pin.getBytes());

                StringBuilder hex = new StringBuilder();

                for (byte b : result)
                    hex.append(String.format("%02x", b));

                return hex.toString();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {

        PatientProfile p1 = new PatientProfile("Arjun Iyer");
        System.out.println(p1.getPatientId());

        PatientProfile p2 = new PatientProfile("MT2026-0142", "Arjun Iyer");

        System.out.println(p2.getPatientId());

        PatientProfile p3 = new PatientProfile();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setLockerPin("1234");
    }
}