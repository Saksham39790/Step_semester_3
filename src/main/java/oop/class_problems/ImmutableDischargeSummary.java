package oop.class_problems;

public class ImmutableDischargeSummary {

    static class DischargeSummary {

        private static String hospitalName;

        private final String patientId;
        private final String[] medicationCodes;

        static {
            hospitalName = "MediTrack Central";
        }

        public DischargeSummary(
                String patientId,
                String[] medicationCodes) {

            if (patientId == null) {
                throw new IllegalArgumentException(
                        "Patient ID cannot be null"
                );
            }

            if (medicationCodes == null) {
                throw new IllegalArgumentException(
                        "Medication codes cannot be null"
                );
            }

            for (String code : medicationCodes) {

                if (code == null
                        || !code.matches("MED-[A-Z]")) {

                    throw new IllegalArgumentException(
                            "Invalid medication code"
                    );
                }
            }

            this.patientId = patientId;
            this.medicationCodes =
                    medicationCodes.clone();
        }

        public String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(
                int index,
                String newCode) {

            if (index < 0
                    || index >= medicationCodes.length) {

                throw new IllegalArgumentException(
                        "Invalid medication index"
                );
            }

            if (newCode == null
                    || !newCode.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException(
                        "Invalid medication code"
                );
            }

            String[] corrected =
                    medicationCodes.clone();

            corrected[index] = newCode;

            return new DischargeSummary(
                    patientId,
                    corrected
            );
        }
    }

    static class CriticalCareDischargeSummary
            extends DischargeSummary {

        private final int icuDays;

        public CriticalCareDischargeSummary(
                String patientId,
                String[] medicationCodes,
                int icuDays) {

            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary
                    instanceof CriticalCareDischargeSummary) {

                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + criticalCare
                + " critical-care | "
                + routine
                + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary summary =
                new DischargeSummary(
                        "MT2026-0142",
                        new String[]{
                                "MED-A",
                                "MED-B"
                        }
                );

        String[] codes =
                summary.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                "Original first code: "
                        + summary.getMedicationCodes()[0]
        );

        DischargeSummary corrected =
                summary.withCorrectedMedication(
                        0,
                        "MED-C"
                );

        System.out.println(
                "Corrected first code: "
                        + corrected.getMedicationCodes()[0]
        );

        DischargeSummary[] summaries = {
                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),
                null,
                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                processNightlyBatch(summaries)
        );

        try {

            new DischargeSummary(
                    "MT2026-0142",
                    new String[]{
                            "MED-A",
                            "bad"
                    }
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected."
            );
        }
    }
}