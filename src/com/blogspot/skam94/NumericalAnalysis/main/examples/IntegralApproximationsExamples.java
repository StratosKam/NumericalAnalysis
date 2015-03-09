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

import static com.blogspot.skam94.NumericalAnalysis.main.algorithms.IntegralApproximations.*;

public class IntegralApproximationsExamples {
    public static void main(String[] args){
        /*
        Approximating sine integral in [0,pi/2] using 2 strips
         */
        int n = 2;
        double[] x = new double[n + 1];
        double[] y = new double[n + 1];
        x[0] = 0.28559933214452704;
        x[1] = 0.8567979964335803;
        x[2] = 1.4279966607226338;
        for (int i = 0; i < n + 1; i++) {
            y[i] = (double) Math.round(Math.sin(x[i]) * 1000000) / 1000000;
        }
        System.out.println("Trapezoidal approximation : " + trapezoidal(x, y));
        System.out.println("Simpson approximation     : " + simpson(x, y));

        /*
        Calculating the error
         */
        double e1, e2;
        double[] xe = new double[199];
        double[] ye = new double[199];
        double start = 0;
        double dif = x[0] / 198;
        for (int i = 0; i < 199; i++) {
            xe[i] = start;
            start += dif;
            ye[i] = Math.sin(xe[i]);
        }
        e1 = simpson(xe, ye) + simpError(x[0], 198, Math.sin(x[0]));

        start = x[2];
        dif = ((Math.PI / 2) - x[2]) / 198;
        for (int i = 0; i < 199; i++) {
            xe[i] = start;
            start += dif;
            ye[i] = Math.sin(xe[i]);
        }
        e2 = simpson(xe, ye) + simpError(Math.PI / 2 - x[2], 198, 1);
        System.out.println("Maximum theoretical error (trapezoidal) : " + (e1 + e2 + trapError(x[2] - x[0], n, Math.sin(x[2]))));
        System.out.println("Actual error (trapezoidal) : " + (1 - trapezoidal(x, y)));
        System.out.println("Maximum theoretical error (simpson)     : " + (e1 + e2 + simpError(x[2] - x[0], n, Math.sin(x[2]))));
        System.out.println("Actual error (simpson)     : " + (1 - simpson(x, y)));

    }
}
