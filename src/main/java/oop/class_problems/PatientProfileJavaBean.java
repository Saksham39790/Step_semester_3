package oop.class_problems;

public class PatientProfileJavaBean {

    static class PatientProfile {

        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPin;

        public PatientProfile() {
            this(null, null);
        }

        public PatientProfile(String name) {
            this(null, name);
        }

        public PatientProfile(
                String patientId,
                String name) {

            this.patientId = patientId;
            this.name = name;
            this.discharged = false;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String id) {

            // Write-once property.
            if (this.patientId == null) {
                this.patientId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        public void setLockerPin(String pin) {

            if (pin != null
                    && pin.matches("\\d{4,6}")) {

                // Store only a deterministic one-way transformation.
                this.lockerPin =
                        Integer.toHexString(pin.hashCode());
            }
        }
    }

    public static void main(String[] args) {

        PatientProfile nameOnly =
                new PatientProfile("Arjun Iyer");

        System.out.println(
                "Name-only ID: "
                        + nameOnly.getPatientId()
        );

        PatientProfile fullProfile =
                new PatientProfile(
                        "MT2026-0142",
                        "Arjun Iyer"
                );

        System.out.println(
                "Full profile ID: "
                        + fullProfile.getPatientId()
        );

        PatientProfile profile =
                new PatientProfile();

        profile.setPatientId("MT2026-0142");
        profile.setPatientId("HACKED-0000");

        System.out.println(
                "Write-once ID: "
                        + profile.getPatientId()
        );

        profile.setLockerPin("1234");

        System.out.println(
                "Locker PIN setter executed successfully."
        );
    }
}
