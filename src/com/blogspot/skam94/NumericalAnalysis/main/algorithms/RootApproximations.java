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

import com.blogspot.skam94.NumericalAnalysis.main.datatypes.Function;
import com.blogspot.skam94.NumericalAnalysis.main.datatypes.Root;

public class RootApproximations {

    private final double e;
    private Function function;
    public RootApproximations(double error,Function function){
        this.e=error;
        this.function=function;
    }
    public RootApproximations(Function function){
        this(0.5 * Math.pow(10, -6),function);
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Root bisection(double a, double b,boolean exists) {
        double c;
        int loops = 0;
        while (true) {
            loops++;
            c = (a + b) / 2.0;
            if (Math.abs(b - a) <= e || (function.f(c)==0 && !exists)) {
                return new Root(c, loops);
            }
            if (hasRoot(a, c)) {
                b = c;
            }else if (hasRoot(c, b)){
                a = c;
            }else if(exists){
                a = c;
            }
            else
                return new Root();
        }

    }
    public Root bisection(double a, double b) {
        double c;
        int loops = 0;
        while (true) {
            loops++;
            c = (a + b) / 2.0;
            if (Math.abs(b - a) <= e) {
                return new Root(c, loops);
            }
            if (hasRoot(a, c)) {
                b = c;
            }else if (hasRoot(c, b)){
                a = c;
            }
        }

    }

    public Root newtonRaphson(double starting) {
        double x0 = starting;
        double x1;
        int loops = 0;
        while (true) {
            loops++;
            x1 = newton(x0);
            //System.out.println(""+x1);
            if (Math.abs(x1 - x0) < e ) {
                //  System.out.println("" + x1);
                return new Root(x1, loops);
            }
            x0 = x1;
        }
    }

    private double newton(double x0) {
        return (x0 - (function.f(x0) / function.fd(x0)));
    }

    public Root secant(double starting, double ending) {
        double x0 = starting;
        double x1 = ending;
        double xn;
        int loops = 0;
        while (true) {
            loops++;
            xn = nextSecantValue(x0, x1);
            if (Math.abs(xn - x1)  < e/* * 2*/) {
                // System.out.println("" + xn);
                return new Root(xn, loops);
            }
            x0 = x1;
            x1 = xn;
        }
    }

    private double nextSecantValue(double x0, double x1) {
        return (x1 - (function.f(x1) * ((x1 - x0) / (function.f(x1) - function.f(x0)))));
    }

    /**
     * Bolzano Theorem
     * @param a
     * @param b
     * @return
     */
    private boolean hasRoot(double a, double b) {
        return function.f(a) * function.f(b) < 0;
    }


}
