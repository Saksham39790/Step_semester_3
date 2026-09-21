package oop.class_problems;

public class VitalsMonitoringEncapsulationGuard {

    static class PatientVitals {

        private double[] readings;
        private int count;

        public PatientVitals(double[] initialReadings) {

            readings = new double[500];
            count = 0;

            if (initialReadings != null) {

                for (double reading : initialReadings) {
                    recordReading(reading);
                }
            }
        }

        public void recordReading(double reading) {

            if (reading <= 0 || reading > 45) {
                return;
            }

            if (count < readings.length) {
                readings[count] = reading;
                count++;
            }
        }

        public double getAverage() {

            if (count == 0) {
                return 0.0;
            }

            double sum = 0;

            for (int i = 0; i < count; i++) {
                sum += readings[i];
            }

            return sum / count;
        }

        public double[] getAllReadings() {

            double[] result =
                    new double[count];

            for (int i = 0; i < count; i++) {
                result[i] = readings[i];
            }

            return result;
        }
    }

    public static void main(String[] args) {

        PatientVitals vitals =
                new PatientVitals(
                        new double[]{
                                36.5,
                                -2,
                                37.1
                        }
                );

        double[] readings =
                vitals.getAllReadings();

        System.out.print("Readings: [");

        for (int i = 0; i < readings.length; i++) {

            System.out.print(readings[i]);

            if (i < readings.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        System.out.println(
                "Average: " + vitals.getAverage()
        );

        readings[0] = 999;

        System.out.println(
                "After modifying copy: "
                        + vitals.getAllReadings()[0]
        );

        vitals.recordReading(38.2);

        System.out.println(
                "Updated average: "
                        + vitals.getAverage()
        );
    }
}
