package com.scotiabankcolpatria.hiring;


public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
        final double averageData = calculateAverage(paymentDelays);
        final double varianceData = calculateVariance(averageData, paymentDelays);
        return calculateRoot(varianceData);
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        //TODO implement.
        return -1;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        //TODO implement.
        return null;
    }


    private double calculateAverage(int[] paymentDelays){
        double sumInputData = 0;
        for (int index:paymentDelays) {
            sumInputData = sumInputData + index;
        }
        return sumInputData / paymentDelays.length;
    }

    private double calculateVariance(double average, int[] paymentDelays){
        double totalSumVariance = 0;
        for (int index:paymentDelays){
            double sumIndex = 0;
            double elevationVariance = 0;
            sumIndex = (index - average);
            elevationVariance = Math.pow(sumIndex, 2);
            totalSumVariance = totalSumVariance + elevationVariance;
        }
        return  totalSumVariance/paymentDelays.length;
    }

    private double calculateRoot(double variance){
        return Math.sqrt(variance);
    }


    public static void main(String[] args){
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        double standardDeviation = riskAssessment.standardDeviation(new int[]{-5, 1, 8, 7, 2});
        System.out.println("Desviación estándar: ( " + standardDeviation + " ) ");
    }

}
