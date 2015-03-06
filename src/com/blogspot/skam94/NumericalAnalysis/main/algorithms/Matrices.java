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

import java.util.Random;

import static com.blogspot.skam94.NumericalAnalysis.main.algorithms.MatricesBasic.*;
import static java.lang.Math.*;

public class Matrices {
    /**
     * Solving ax=b using pa=lu decomposition.
     * @param a
     * @param b
     * @return
     */
    static public double[] gauss(double[][] a,double b[]){
        int n=a.length;
        double[][] p=identity(n);
        double[][] l=identity(n);
        double[][] u=pivot(a,p);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(u[i][j]!=0){
                    l[i][j]=u[i][j]/u[j][j];
                    u[i]=multiplyAndSumLines(u[j],u[i],-l[i][j]);
                }
            }
        }
        double[] y=new double[n];
        double[] z=multiply(p,b);
        double s;
        for (int i = 0; i < n; i++) {
            s=0;
            for (int j = 0; j < i; j++) {
                s+=l[i][j]*y[j];
            }
            y[i]=(z[i]-s)/l[i][i];
        }
        double[] x=new double[n];
        for (int i = n-1; i >= 0; i--) {
            s=0;
            for (int j = n-1; j > i; j--) {
                s+=u[i][j]*x[j];
            }
            x[i]=(y[i]-s)/u[i][i];
        }
        return x;
    }

    /**
     * Cholesky decomposition
     * @param a
     * @return
     */
    static public double[][] cholesky(double[][] a){
        double[][] l=new double[a.length][a.length];
        double s;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                s=0;
                for (int k = 0; k < j; k++) {
                    s+=l[i][k]*l[j][k];
                }
                l[i][j]=(a[i][j]-s)/l[j][j];
            }
            s=0;
            for (int k = 0; k < i; k++) {
                s+= pow(l[i][k],2);
            }
            l[i][i]=sqrt((a[i][i]-s));
        }
        return l;
    }

    private static double s1;
    private static double s2;
    private static int loop;
    private static double[] x;

    /**
     * Gauss-Seidel method for ax=b with 6 decimals accuracy
     * @param a
     * @param b
     * @param start
     * @return
     */
    static public double[] gaussSeidel(final double[][] a,double[] b,double[] start){
        double e=0.5*pow(10,-6);
        x=copy(start);
        final int n=start.length;
        double[] prev;
        do{
            prev=copy(x);
            for (loop = 0; loop < n; loop++) {
                s1=0;
                s2=0;
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        for (int j = 0; j < loop; j++) {
                                s1 += a[loop][j] * x[j];
                        }
                    }
                };
                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        for (int k = loop + 1; k < n; k++) {
                                s2 += a[loop][k] * x[k];
                        }
                    }
                };
                t1.start();
                t2.start();
                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException ex) {

                }
                    x[loop] = (1.0 / a[loop][loop]) * (b[loop]-s1-s2);
            }
        }while(normInf(dif(x,prev))>=e);
        return x;
    }

    /**
     * Pivoting so the maximun values are located in the diagonal.
     * @param a
     * @param p
     * @return
     */
    public static double[][] pivot(double[][] a, double[][] p) {
        double[][] acopy=copy(a);
        for (int i = 0; i < a.length; i++) {
            int imax=jMaxOf(getColumn(acopy,i),i);
            if(imax!=i){
                double temp;
                for (int j = 0; j < a.length; j++) {
                    temp=p[i][j];
                    p[i][j]=p[imax][j];
                    p[imax][j]=temp;

                    temp=acopy[i][j];
                    acopy[i][j]=acopy[imax][j];
                    acopy[imax][j]=temp;
                }
            }
        }
        return acopy;
    }

    /**
     * Power iteration method calculating the largest eigenvector of a.
     * @param a
     * @return
     */
    static public double[] powerIt(double[][] a) {
        int n=a.length;
        double e = 0.5 * pow(10, -7);
        double[] b = new double[n];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            b[i] = r.nextDouble();
        }

        double l0;
        double l1 = firstNonZero(b);
        do {
            l0 = l1;
            b = multiply(a, b);
            l1 = firstNonZero(b);
            b=multiply(b,1/l1);
        } while (abs(l1 - l0) > e);
        return b;
    }

    /**
     * Normalizing a,resulting at sum of elements=1 with accuracy of 8 decimals.
     * @param a
     * @return
     */
    public static double[] normalize(double[] a){
        double s=0;
        for (int i = 0; i < a.length; i++) {
            s+=abs(a[i]);
        }
        double[] n;
        n=multiply(a,1/s);
        for (int i = 0; i < n.length; i++){
            n[i]=(double) round(n[i] * 100000000) / 100000000;
        }
        return n;
    }

    static public double[] getNormEigenvector(double[][] a){
        return normalize(powerIt(a));
    }

    static public double[][] getGoogleMatrix(int[][] a,double q) {
        int n=a.length;
        double[][] g = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = ((q / n) + ((a[j][i] * (1 - q)) / sumOfLine(a[j])));
            }
        }
        return g;
    }

}
