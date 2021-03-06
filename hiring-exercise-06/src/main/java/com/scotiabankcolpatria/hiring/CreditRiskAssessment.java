package com.scotiabankcolpatria.hiring;

import java.util.ArrayList;
import java.util.List;

public class CreditRiskAssessment {

    /**
     * Fecha: 09/29/2021
     * Calcula la desviación estándar.
     * @author Santiago Vélez Pulgarin
     * @param paymentDelays Vector de datos
     * @return double
     **/
    public double standardDeviation(int[] paymentDelays) {
        final double averageData = calculateAverage(paymentDelays);
        final double varianceData = calculateVariance(averageData, paymentDelays);
        final double rootData = calculateRoot(varianceData);
        return rootData;
    }

    /**
     * Fecha: 09/29/2021
     * Calcula la anomalía con el pico más alto.
     * @author Santiago Vélez Pulgarin
     * @param paymentDelays Vector de datos
     * @return int
     **/
    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        int sizeData = paymentDelays.length;
        int anomalyPosition = -1;
        for (int currentIndex = 0; currentIndex < sizeData; currentIndex++){
            boolean endOfVector = ((sizeData - 1) == currentIndex);
            int currentValue = paymentDelays[currentIndex];
            int nextValue = !endOfVector?paymentDelays[currentIndex + 1]:0;
            if(currentIndex == 0){
                if(currentValue > nextValue){
                    anomalyPosition = currentIndex;
                }
            }else{
                int previousValue = paymentDelays[currentIndex - 1];
                if(endOfVector){
                    if((currentValue > previousValue) &&
                            (currentValue > 0)){
                        anomalyPosition = currentIndex;
                    }
                }else {
                    if ((currentValue > previousValue) &&
                            (currentValue > nextValue)) {
                        anomalyPosition = currentIndex;
                    }
                }
            }
        }
        return anomalyPosition;
    }

    /**
     * Fecha: 09/29/2021
     * Realiza el cálculo de probabilidad de pago tardío.
     * @author Santiago Vélez Pulgarin
     * @param paymentDelays Vector de datos
     * @return double[]
     **/
    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        final List<Double> finalData = calculateProbabilityOfLateLayment(paymentDelays);
        return convertListToVector(finalData);
    }

    /**
     * Fecha: 09/29/2021
     * Calcula el promedio del vector enviado por parametro.
     * @author Santiago Vélez Pulgarin
     * @param paymentDelays Vector de datos
     * @return double
     **/
    private double calculateAverage(int[] paymentDelays){
        double sumInputData = 0;
        for (int index:paymentDelays) {
            sumInputData = sumInputData + index;
        }
        return sumInputData / paymentDelays.length;
    }

    /**
     * Fecha: 09/29/2021
     * Calcula el valor de la varianza del vector enviado por parametro.
     * @author Santiago Vélez Pulgarin
     * @param average Promedio de datos
     * @param paymentDelays Vector de datos
     * @return double
     **/
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

    /**
     * Fecha: 09/29/2021
     * Calcula el valor de la raiz de la varianza enviada por parametro.
     * @author Santiago Vélez Pulgarin
     * @param variance valor de Varianza
     * @return double
     **/
    private double calculateRoot(double variance){
        return Math.sqrt(variance);
    }

    /**
     * Fecha: 09/29/2021
     * Calcula la probabilidad de pago tardío.
     * @author Santiago Vélez Pulgarin
     * @param paymentDelays Arreglo de datos
     * @return List<Double>
     **/
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
                    double calculateLatePayment = (double) latePaymentsAmount/rowSize;
                    finalData.add(calculateLatePayment);
                    latePaymentsAmount = 0;
                }
            }
        }
        return finalData;
    }

    /**
     * Fecha: 09/29/2021
     * transforma una lista a vector
     * @author Santiago Vélez Pulgarin
     * @param finalData Lista de datos
     * @return double[]
     **/
    private double[] convertListToVector(List<Double> finalData){
        return finalData.stream().mapToDouble(i -> i).toArray();
    }
}
