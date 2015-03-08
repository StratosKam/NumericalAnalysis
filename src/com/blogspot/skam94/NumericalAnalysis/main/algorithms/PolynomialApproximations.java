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

public class PolynomialApproximations {

    /**
     * Newton method
     * @param x ascending order
     * @param y corresponding to x
     * @return the coefficients
     */
    static public double[] newtonPolCoef(double[] x, double[] y) {
        int n = x.length - 1;
        double dd[][] = new double[n][];
        double[] a = new double[n + 1];
        for (int i = 0; i < n; i++) {
            dd[i] = new double[n - i];
        }
        for (int j = 0; j < n; j++) {
            dd[0][j] = (y[j + 1] - y[j]) / (x[j + 1] - x[j]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dd[i][j] = (dd[i - 1][j + 1] - dd[i - 1][j]) / (x[j + i + 1] - x[j]);
            }
        }
        a[0] = y[0];
        for (int i = 1; i < n + 1; i++) {
            a[i] = dd[i - 1][0];
        }
        return a;
    }

    /**
     * Calculates P(val)
     * @param coef the result of newtonPolCoef
     * @param x initial values
     * @param val
     * @return
     */
    static public double newtonPolVal(double[] coef, double[] x, double val) {
        double r = coef[0];
        double mul = 1;
        for (int i = 1; i < x.length; i++) {
            mul *= (val - x[i - 1]);
            r += coef[i] * mul;
        }
        return r;
    }

    /**
     *
     * @param x ascending order
     * @param y
     * @param degree 2 or 3
     * @return
     */
    static public double[] splineCoef(double[] x, double[] y, int degree) {
        if (degree != 2 && degree != 3) {
            throw new IllegalArgumentException();
        }
        int n = (x.length - 1) * (degree + 1);
        double[][] a = new double[n][n];
        double[] b = new double[n];
        double[] coef;
        //s
        int k = 0;
        for (int i = 0; i < (x.length - 1) * 2; i += 2, k++) {
            for (int j = 0; j < degree + 1; j++) {
                for (int l = 0; l < 2; l++) {
                    a[i + l][j + ((i / 2) * (degree + 1))] = Math.pow(x[k + l], degree - j);
                }
            }
            for (int l = 0; l < 2; l++) {
                b[i + l] = y[k + l];
            }
        }
        //s'
        int line = (x.length - 1) * 2;
        int column = 0;
        for (int i = 1; i < x.length - 1; i++) {
            for (int j = 0; j < degree; j++) {
                a[line + i - 1][column + j] = (degree - j) * Math.pow(x[i], degree - 1 - j);
                a[line + i - 1][(column + j) + degree + 1] = -a[line + i - 1][column + j];
            }
            column += degree + 1;
        }
        //s"
        if (degree > 2) {
            line += x.length - 2;
            column = 0;
            for (int i = 1; i < x.length - 1; i++) {
                for (int j = 0; j < degree - 1; j++) {
                    a[line + i - 1][column + j] = ((degree - j) * (degree - 1 - j)) * Math.pow(x[i], degree - 2 - j);
                    a[line + i - 1][(column + j) + degree + 1] = -a[line + i - 1][column + j];
                }
                column += degree + 1;
            }
            if (degree == 3) {
                a[n - 2][0] = 6 * x[0];
                a[n - 2][1] = 2;
                a[n - 1][n - 4] = 6 * x[x.length - 1];
                a[n - 1][n - 3] = 2;
            }

        } else {
            a[n - 1][0] = 1;
        }
        Jama.Matrix A = new Jama.Matrix(a);
        Jama.Matrix c = new Jama.Matrix(b, n);
        Jama.Matrix sol = A.solve(c);
        coef = sol.getColumnPackedCopy();
        return coef;
    }

    /**
     *
     * @param coef the result of splineCoef
     * @param x initial values
     * @param val
     * @return
     */
    static public double splineVal(double[] coef, double[] x, double val) {
        if (val < x[0]) {
            return Double.NaN;
        }
        int degree = coef.length / x.length;
        for (int i = 1; i < x.length; i++) {
            if (val <= x[i]) {
                int indx = (i - 1) * (degree + 1);
                double s = 0;
                for (int j = 0; j < degree + 1; j++) {
                    s += coef[indx + j] * Math.pow(val, degree - j);
                }
                return s;
            }
        }
        return Double.NaN;
    }

    /**
     *
     * @param x ascending order
     * @param y
     * @param degree >0
     * @return
     */
    static public double[] leastSqCoeff(double[] x, double[] y, int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        }
        ++degree;
        int n = x.length;
        double[][] a = new double[n][degree];
        double[] b = new double[degree];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < degree; j++) {
                a[i][j] = Math.pow(x[i], j);
            }
        }
        double[][] ata = new double[degree][degree];
        for (int i = 0; i < degree; i++) {
            for (int j = 0; j < degree; j++) {
                ata[i][j] = MatricesBasic.vectorM(MatricesBasic.getColumn(a, i), MatricesBasic.getColumn(a, j));
            }
            b[i] = MatricesBasic.vectorM(MatricesBasic.getColumn(a, i), y);
        }
        b = Matrices.gauss(ata, b);
        return b;

    }

    /**
     *
     * @param coef the result of leastSqCoeff
     * @param val
     * @return
     */
    static public double leastSqVal(double[] coef, double val) {
        double res = 0;
        for (int i = 0; i < coef.length; i++) {
            res += coef[i] * Math.pow(val, i);
        }
        return res;
    }



}
