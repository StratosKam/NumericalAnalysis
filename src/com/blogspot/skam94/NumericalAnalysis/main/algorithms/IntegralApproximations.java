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


public class IntegralApproximations {
    /**
     *
     * @param x ascending order
     * @param y
     * @return
     */
    static public double trapezoidal(double[] x, double[] y) {
        double s = 0;
        double dif = x[x.length - 1] - x[0];
        int n = x.length - 1;
        for (int i = 1; i <= n - 1; i++) {
            s += y[i];
        }
        s *= 2;
        return (dif / (2 * n)) * (y[0] + y[n] + s);
    }

    /**
     *
     * @param dif total length of the strips
     * @param n number of strips
     * @param max max value of the 2nd derivative
     * @return
     */
    static public double trapezoidalError(double dif, int n, double max) {
        return ((Math.pow(dif, 3) / (12 * n * n)) * max);
    }

    /**
     *
     * @param x ascending order,odd number of points
     * @param y
     * @return
     */
    static public double simpson(double[] x, double[] y) {
        double s1 = 0, s2 = 0;
        double dif = x[x.length - 1] - x[0];
        int n = x.length - 1;
        if (n % 2 != 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i <= (n * 0.5) - 1; i++) {
            s1 += y[2 * i];
        }
        s1 *= 2;
        for (int i = 1; i <= n * 0.5; i++) {
            s2 += y[(2 * i) - 1];
        }
        s2 *= 4;
        return ((dif / (3 * n)) * (y[0] + y[n] + s1 + s2));

    }

    /**
     *
     * @param dif total length of the strips
     * @param n number of strips
     * @param max max value of the 4nd derivative
     * @return
     */
    static public double simpError(double dif, int n, double max) {
        return ((Math.pow(dif, 5) / (180 * n * n * n * n)) * max);
    }

}
