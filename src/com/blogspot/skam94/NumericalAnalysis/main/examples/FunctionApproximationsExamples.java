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
package com.blogspot.skam94.NumericalAnalysis.main.examples;

import com.blogspot.skam94.NumericalAnalysis.main.algorithms.FunctionApproximations;

import java.util.Random;

public class FunctionApproximationsExamples {
    public static void main(String[] args){

        /**
         * Using 12 points to approximate sine with 6 decimals accuracy
         */
        int n = 11;
        double[] stx = new double[n + 1];
        double[] y = new double[n + 1];
        double dif = (Math.PI * 2) / (n);
        double start = -Math.PI;
        for (int i = 0; i < n + 1; i++) {
            stx[i] = start;
            start += dif;
            y[i] = (double) Math.round(Math.sin(stx[i]) * 100000) / 100000;
        }
        double[] newtonPolCoef = FunctionApproximations.newtonPolCoef(stx, y);
        double[] splineCoef = FunctionApproximations.splineCoef(stx, y, 3);
        double[] lsCoef = FunctionApproximations.leastSqCoeff(stx, y, 5);

        Random random=new Random();
        double val,correct,newton,spline,leastsq;
        for (int i = 0; i < 10; i++) {
            val=random.nextDouble();
            correct=Math.sin(val);
            newton=FunctionApproximations.newtonPolVal(newtonPolCoef, stx, getX(val));
            spline=FunctionApproximations.splineVal(splineCoef, stx, getX(val));
            leastsq=FunctionApproximations.leastSqVal(lsCoef, getX(val));
            System.out.println("sin("+val+")");
            System.out.println("Newton approximation :     "+newton+"    off by   "+(correct-newton));
            System.out.println("Cubic splines :            "+spline+"    off by   "+(correct-spline));
            System.out.println("Least squares 5th degree : " +leastsq+"    off by   "+(correct-leastsq));
            System.out.println("----");
        }

    }

    /**
     * Getting the equivalent of x in [0,2p]
     * @param x
     * @return
     */
    static private double getX(double x) {
        if (Math.abs(x) <= Math.abs(Math.PI)) {
            return x;
        } else {
            if (x > 0) {
                x -= Math.round(x / (2 * Math.PI)) * (2 * Math.PI);

            } else {
                x += Math.round(Math.abs(x) / (2 * Math.PI)) * (2 * Math.PI);
            }
            return x;
        }
    }
}
