package com.unicom.fmos.utils;

import Jama.Matrix;
import com.google.gson.Gson;

import java.util.*;

/**
 * Created by zhaojb on 2016/12/7.
 */
public class UtilFactory {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public UtilFactory() {
    }

    private static double getAverage(double [] arr) {
        int length = arr.length;
        return getSum(arr) / length;
    }

    private static double getSum(double[] arr) {
        double sum = 0.;
        for(double a : arr)
            sum = sum + a;
        return sum;
    }

    private static double[] getMultiResult(double[] arrA, double[] arrB) {
        double[] result = new double[arrA.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = arrA[i] * arrB[i];
        }
        return result;
    }

    private static double getMultiSum(double[] arrA, double[] arrB) {
        double[] result = {0,0};
        for (int i = 0; i < arrA.length ; i++) {
            double min = arrA[i] < arrB[i] ? arrA[i] : arrB[i];
            double max = arrA[i] > arrB[i] ? arrA[i] : arrB[i];
            result[0] = result[0] + min;
            result[1] = result[1] + max;
        }
        double rij = result[0] / result[1];
        return rij;
    }

    private static double getSigma(double[] arr, double averageXi) {
        System.out.println("接受到的参数为");
        System.out.println(Arrays.toString(arr));
        System.out.println("以及");
        System.out.println(averageXi);
        double[] tmpArr = new double[arr.length];
        for(int i = 0 ; i < arr.length; i++) {
            tmpArr[i] = (arr[i] - averageXi) * (arr[i] - averageXi);
        }
        System.out.println("tmpArr为");
        System.out.println(Arrays.toString(tmpArr));
        double sum = getSum(tmpArr);
        System.out.println("tmpArr的和为");
        System.out.println(sum);

        double result = Math.pow(sum/(arr.length-1), 0.5);
        System.out.println("result为");
        System.out.println(result);

        return result;
    }

    private static double[] getMaxAndMin(double[] arr) {
        double[] copyaArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copyaArr);
        double[] maxAndMin = new double[2];
        maxAndMin[0] = copyaArr[0];
        maxAndMin[1] = copyaArr[copyaArr.length - 1];
        return maxAndMin;
    }

    public static double[][] toFuzzyEquivalentMatrix(Matrix fuzzySimilarMatrix) {

        double[][] fuzzySimilarArr = fuzzySimilarMatrix.getArray();

        int row = fuzzySimilarArr.length;
        int column = fuzzySimilarArr[0].length;

        double[][] tmpArr = new double[row][column];

        while (true) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    double[] tmpResult = new double[row];
                    for (int k = 0; k < row; k++) {
                        tmpResult[k] = fuzzySimilarArr[i][k] < fuzzySimilarArr[k][j] ? fuzzySimilarArr[i][k] : fuzzySimilarArr[k][j];
                    }
                    tmpArr[i][j] = getMaxAndMin(tmpResult)[1];
                }
            }
            if (arrEqualsChecked(new Matrix(tmpArr), new Matrix(fuzzySimilarArr)) == false) {
                fuzzySimilarArr = tmpArr;
                continue;
            } else {
                break;
            }
        }

        return tmpArr;
    }

    public static Map<String,Object> clusterAnalysis(double[][]arr, double lamda){

        System.out.println("接受到的参数数组为");
        System.out.println(Arrays.deepToString(arr));
        int row = arr.length;
        int column = arr[0].length;
        System.out.println("阈值为");
        System.out.println(lamda);
        System.out.println("数组维数为 " + row + " * " + column);

        System.out.println("开始进行数据正规化");
        double[] averageXi = new double[row];
        double[] sigmai = new double[row];
        for(int i = 0;i < row; i++) {
            averageXi[i] = getAverage(arr[i]);
            sigmai[i] = getSigma(arr[i], averageXi[i]);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (sigmai[i] == 0) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = (arr[i][j] - averageXi[i]) / sigmai[i];
                }
            }
        }
        System.out.println("结束数据正规化");
        System.out.println(Arrays.deepToString(arr));

        System.out.println("开始进行极值标准化");
        for (int i = 0; i < row; i++) {
            double[] maxAndMin = getMaxAndMin(arr[i]);
            for (int j = 0; j < column; j++) {
                if (maxAndMin[1] == maxAndMin[0] ) {
                    arr[i][j] = 1;
                    continue;
                }
                arr[i][j] = (arr[i][j] - maxAndMin[0]) / (maxAndMin[1] - maxAndMin[0]);
            }
        }
        System.out.println("结束极值标准化");
        System.out.println(Arrays.deepToString(arr));

        System.out.println("开始建立模糊相似矩阵");
        double[][] fuzzySimilarArr = new double[row][row];
        List<Double> tmpList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if ( i == j) {
                    fuzzySimilarArr[i][j] = 1.0;
                } else {
                    double sum = getSum(getMultiResult(arr[i],arr[j]));
                    fuzzySimilarArr[i][j] = sum;
                    tmpList.add(sum);
                }
            }
        }
        System.out.println("初步建立模糊相似矩阵");
        System.out.println(Arrays.deepToString(fuzzySimilarArr));
        if (tmpList.size() == 0) {
            tmpList.add(1.0);
        }
        double max = tmpList.get(0);

        for (Double d : tmpList) {
            if (d > max) {
                max = d;
            }
        }

        System.out.println("最大值是 " + max);
        //作偏移
        max = max +0.1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if ( i == j) {
                    continue;
                } else {
                    fuzzySimilarArr[i][j] = fuzzySimilarArr[i][j] / max;
                }
            }
        }
        System.out.println("结束建立模糊相似矩阵");
        System.out.println(Arrays.deepToString(fuzzySimilarArr));

        System.out.println("开始建立模糊相似等价矩阵");
        System.out.println(Arrays.deepToString(fuzzySimilarArr));
        double[][] fuzzySimilarEquivalentArr = toFuzzyEquivalentMatrix(new Matrix(fuzzySimilarArr));
        System.out.println("结束建立模糊相似等价矩阵");
        System.out.println(Arrays.deepToString(fuzzySimilarEquivalentArr));

        double maxThreadHold = fuzzySimilarEquivalentArr[0][1];
        double minThreadHold = fuzzySimilarEquivalentArr[0][1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i != j) {
                    maxThreadHold = fuzzySimilarEquivalentArr[i][j] > maxThreadHold ? fuzzySimilarEquivalentArr[i][j] : maxThreadHold;
                    minThreadHold = fuzzySimilarEquivalentArr[i][j] < minThreadHold ? fuzzySimilarEquivalentArr[i][j] : minThreadHold;
                }
            }
        }

        System.out.println("开始聚类分析");
        for (int i = 0; i < fuzzySimilarEquivalentArr.length; i++) {
            for (int j = 0; j < fuzzySimilarEquivalentArr[i].length; j++) {
                if (fuzzySimilarEquivalentArr[i][j] >= lamda) {
                    fuzzySimilarEquivalentArr[i][j] = 1;
                } else {
                    fuzzySimilarEquivalentArr[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(fuzzySimilarEquivalentArr));
        ArrayList<Integer[]> clusterResult = new ArrayList<>();
        ArrayList<Integer> checkFlagArr = new ArrayList<>();
        int k = -1;
        for (int i = 0; i < fuzzySimilarEquivalentArr.length; i++) {
            if (checkFlagArr.contains(i))
                continue;
            else{
                checkFlagArr.add(i);
                Integer[] tmpArr = new Integer[]{i};
                k++;
                clusterResult.add(tmpArr);
            }
            for (int j = i + 1; j < fuzzySimilarEquivalentArr.length; j++) {
                if (Arrays.equals(fuzzySimilarEquivalentArr[i],fuzzySimilarEquivalentArr[j])){
                    Integer[] tmpArr = clusterResult.get(k);
                    Integer[] newArr = Arrays.copyOf(tmpArr,tmpArr.length + 1);
                    newArr[tmpArr.length] = j;
                    clusterResult.set(k, newArr);
                    checkFlagArr.add(j);
                }
                continue;
            }
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("clusterResult",clusterResult);
        resultMap.put("maxThreadHold",maxThreadHold);
        resultMap.put("minThreadHold",minThreadHold);
        return resultMap;
    }

    public static boolean arrEqualsChecked(Matrix a, Matrix b){
        double[][] arrA = a.getArray();
        double[][] arrB = b.getArray();
        int row = arrA.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (arrA[i][j] == arrB[i][j]){
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
