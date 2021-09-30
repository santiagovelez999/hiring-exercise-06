package com.scotiabankcolpatria.hiring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
        final double averageData = calculateAverage(paymentDelays);
        final double varianceData = calculateVariance(averageData, paymentDelays);
        return calculateRoot(varianceData);
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        final int inputDataSize = paymentDelays.length;
        int highestPeakValue = paymentDelays[0];
        int highestPeakIndex = 0;
        for(int index = 0; index < inputDataSize; index++){
            if(paymentDelays[index] > highestPeakValue){
                highestPeakValue = paymentDelays[index];
                highestPeakIndex = index;
            }
        }
        return highestPeakIndex;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        final List<Double> finalData = calculateProbabilityOfLateLayment(paymentDelays);
        return convertListToVector(finalData);
    }

    private double[] convertListToVector(List<Double> finalData){
        return finalData.stream().mapToDouble(i -> i).toArray();
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

    private List<Double> calculateProbabilityOfLateLayment(int[][] paymentDelays){
        final int rowSize = paymentDelays.length;
        final int columnSize = paymentDelays[0].length;
        List<Double> finalData = new ArrayList<>();
        int latePaymentsAmount = 0;
        for(int columnIndex = 0; columnIndex < columnSize; columnIndex++){
            for(int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
                if(paymentDelays[rowIndex][columnIndex] != 0){
                    latePaymentsAmount++;
                }
                if(rowIndex == (rowSize - 1)){
                    double dato = (double) latePaymentsAmount/rowSize;
                    finalData.add(dato);
                    latePaymentsAmount = 0;
                }
            }
        }
        return finalData;
    }

    public static void main(String[] args){
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        //double standardDeviation = riskAssessment.standardDeviation(new int[]{-5, 1, 8, 7, 2});
        //int mayor = riskAssessment.paymentDelayMaxPeakIndex(new int[]{-5, 1, 8, 31, 7, 2});
        double[] standardDeviation = riskAssessment.latePaymentProbabilityByPeriod(new int[][]{
                {0, 3, 6, 1, 0, 5, 0, 0},
                {0, 3, 0, 2, 0, 0, 0, 2},
                {0, 0, 1, 0, 3, 0, 2, 0},
                {0, 4, 0, 2, 0, 1, 1, 0}
        });
        Arrays.stream(standardDeviation).forEach(x->{System.out.print(" " + x);});
    }

}
