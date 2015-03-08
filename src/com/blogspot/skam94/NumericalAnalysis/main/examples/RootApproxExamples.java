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

import com.blogspot.skam94.NumericalAnalysis.main.algorithms.RootApproximations;
import com.blogspot.skam94.NumericalAnalysis.main.datatypes.Root;

public class RootApproxExamples {
    private RootApproximations approximations;

    public RootApproxExamples(RootApproximations rootApproximations){
        this.approximations=rootApproximations;
    }
    public void Bisection1(){
        Root x1 = approximations.bisection(-2, -1, false);
        Root x2 = approximations.bisection(-1, 0.000001, true);
        Root x3 = approximations.bisection(1, 2, false);
        print("Bisection method",x1, x2, x3);
    }
    public void NewtonRaphson1(){
        Root x1 = approximations.newtonRaphson(-1.21);
        Root x2 = approximations.newtonRaphson(0.0000001);
        Root x3 = approximations.newtonRaphson(1.59);
        print("Newton-Raphson method",x1, x2, x3);
    }
    public void Secant1(){
        Root x1 = approximations.secant(-2, -1);
        Root x2 = approximations.secant(-1, 0.0000001);
        Root x3 = approximations.secant(1.2, 2);
        print("Secant method",x1, x2, x3);
    }

    public void CompareBisectionNewton2(final int loops){
        final long[] newtonRaphsonTime = new long[1];
        final long[] bisectionTime = new long[1];
        Thread t1 = new Thread() {
            @Override
            public void run() {
                long started = System.currentTimeMillis();
                for (int i = 0; i < loops; i++) {
                    Root r = approximations.newtonRaphson(0.7);
                }
                newtonRaphsonTime[0] = System.currentTimeMillis() - started;
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                long started = System.currentTimeMillis();
                for (int i = 0; i < loops; i++) {
                    Root r = approximations.bisection(0.5, 0.9);
                }
                bisectionTime[0] = System.currentTimeMillis() - started;
            }
        };
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("Benchmark(milliseconds) "+loops+" loops");
            System.out.println("Newton Raphson: "+newtonRaphsonTime[0]);
            System.out.println("Bisection: "+bisectionTime[0]);
        } catch (InterruptedException ex) {

        }
    }


    private void print(String text,Root x1, Root x2, Root x3) {
        System.out.println((text));
        System.out.printf("x1=%.7f,%d loops%n", x1.getValue(), x1.getLoops());
        System.out.printf("x2= %.7f ,%d loops%n", x2.getValue(), x2.getLoops());
        System.out.printf("x3= %.7f,%d loops%n", x3.getValue(), x3.getLoops());
    }

    public static void main(String[] args){
        RootApproxExamples rootApproxExamples1 =new RootApproxExamples(new RootApproximations(new Function1()));
        rootApproxExamples1.Bisection1();
        rootApproxExamples1.NewtonRaphson1();
        rootApproxExamples1.Secant1();
        RootApproxExamples rootApproxExamples2 =new RootApproxExamples(new RootApproximations(new Function2()));
        rootApproxExamples2.CompareBisectionNewton2(50);
        rootApproxExamples2.CompareBisectionNewton2(500);
        rootApproxExamples2.CompareBisectionNewton2(5000);
        rootApproxExamples2.CompareBisectionNewton2(50000);

    }

}
