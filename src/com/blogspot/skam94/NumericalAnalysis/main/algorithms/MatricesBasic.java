/*
 * This file is part of NumericalAnalysis.
 *
 * NumericalAnalysis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NumericalAnalys is is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NumericalAnalysis.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Stratos Kamadanis
 */
package com.blogspot.skam94.NumericalAnalysis.main.algorithms;

import static java.lang.Math.abs;

/**
 * Static methods for simple matrix calculations.
 */
public class MatricesBasic {
    static public double[][] identity(int n){
        double[][] array=new double[n][n];
        for (int i = 0; i < n; i++) {
            array[i][i]=1;
        }
        return array;
    }

    static public double[][] copy(double[][] A){
        double[][] R=new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                R[i][j]=A[i][j];
            }
        }
        return R;
    }

    static public double[] copy(double[] A){
        double[] R=new double[A.length];
        for (int i = 0; i < A.length; i++) {
            R[i]=A[i];
        }
        return R;
    }

    /**
     * Finding the position of the maximun item in A after start
     * @param A
     * @param from
     * @return
     */
    static public int jMaxOf(double[] A,int from) {
        int mj=from;
        double m= abs(A[from]);
        for (int j = from; j < A.length; j++) {
            if(abs(A[j])>m){
                m= abs(A[j]);
                mj=j;
            }
        }
        return mj;
    }

    static public double[] getColumn(double[][] a,int j){
        double[] r=new double[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i]=a[i][j];
        }
        return r;
    }

    static public int[] getColumn(int[][] a,int j){
        int[] r=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i]=a[i][j];
        }
        return r;
    }

    static public double[][] multiply(double[][] a,double[][] b){
        if(a.length==b.length){
            double[][] p=new double[a.length][a.length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    for (int k = 0; k < a.length; k++) {
                        p[i][j]+=a[i][k]*b[k][j];
                    }
                }
            }
            return p;
        }
        return null;

    }

    static public double[] multiply(double[][] a,double[] b){
        double[] r=new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                r[i]+=a[i][j]*b[j];
            }
        }
        return r;
    }

    static public double[] multiply(int[][] a,double[] b){
        double[] r=new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                r[i]+=a[i][j]*b[j];
            }
        }
        return r;
    }

    public static double[] multiply(double[] a,double d){
        double[] r=new double[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i]=(a[i]*d);
        }
        return r;
    }

    /**
     * Infinity norm
     * @param a
     * @return
     */
    public static double normInf(double[] a){
        double max= abs(a[0]);
        for (int i = 1; i < a.length; i++) {
            if(abs(a[i])>max)
                max=abs(a[i]);
        }
        return max;
    }


    public static double[] dif(double[] a,double[] b){
        double[] r=new double[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i]=a[i]-b[i];
        }
        return r;
    }

    public static int sumOfLine(int[] a){
        int s=0;
        for (int j = 0; j < a.length; j++) {
            s+=a[j];
        }
        return s;
    }

    public static double sumOfLine(double[] a){
        double s=0;
        for (int j = 0; j < a.length; j++) {
            s+=a[j];
        }
        return s;
    }

    public static double[] multiplyAndSumLines(double[] a,double[] b,double m){
        double[] r=multiply(a,m);
        for (int i = 0; i < a.length; i++) {
            r[i]+=b[i];
        }
        return r;
    }

    static public double firstNonZero(double[] b) {
        double r;
        int i = 0;
        do {
            r = b[i++];
        } while (r == 0 && i < b.length);
        return r;
    }

    static public void print(double[][] A){
        for (double[] A1 : A) {
            for (int j = 0; j < A.length; j++) {
                System.out.printf("%f ", A1[j]);
            }
            System.out.println("");
        }
    }

    static public void print(int[][] A){
        for (int[] A1 : A) {
            for (int j = 0; j < A.length; j++) {
                System.out.printf("%d ", A1[j]);
            }
            System.out.println("");
        }
    }

    static public void print(double[] A){
        for (int j = 0; j < A.length; j++) {
            System.out.println(""+A[j]);
        }
    }
}
