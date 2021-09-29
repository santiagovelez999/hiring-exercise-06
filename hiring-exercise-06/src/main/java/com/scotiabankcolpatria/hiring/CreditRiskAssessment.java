package com.scotiabankcolpatria.hiring;

import java.util.Arrays;

public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
        //TODO implement.
        final float averageData = calculateAverage(paymentDelays);
        System.out.println("Promedio ( " + averageData + " ) ");
        final double varianceData = calculateVariance(averageData, paymentDelays);
        System.out.println("Varianza ( " + varianceData + " ) ");
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


    private float calculateAverage(int[] paymentDelays){
        int sumInputData = 0;
        for (int index:paymentDelays){
            sumInputData = sumInputData + index;
        }
        System.out.println("Suma: ( " + sumInputData + " ) ");
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
        double standardDeviation = riskAssessment.standardDeviation(new int[]{0, 15, 2, 0, 1, 3});
        System.out.println("Desviación estándar: ( " + standardDeviation + " ) ");
    }

}
