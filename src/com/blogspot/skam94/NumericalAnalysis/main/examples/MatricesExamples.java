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

import static com.blogspot.skam94.NumericalAnalysis.main.algorithms.Matrices.*;
import static com.blogspot.skam94.NumericalAnalysis.main.algorithms.MatricesBasic.print;

public class MatricesExamples {

    public static void main(String[] args) {
        linear();
        adjacency();

    }
    private static void linear(){
        double[][] A=new double[][]{{5,1,0},{2,4,-4},{3,3,1}};
        double[] b=new double[]{5,0,6};
        double[][] C=new double[][]{{25,15,-5},{15,18,0},{-5,0,11}};

        print(gauss(A,b));
        print(cholesky(C));

        int n=100;
        double[][] gsA=new double[n][n];
        double[] gsB=new double[n];
        double[] gsS=new double[n];
        for (int i = 0; i < n; i++) {
            gsA[i][i]=3;
            gsB[i]=1;
            gsS[i]=2;
            if(i!=0){
                gsA[i][i-1]=-1;

            }else{
                gsB[i]++;
            }
            if(i!=n-1){
                gsA[i][i+1]=-1;
            }else{
                gsB[i]++;
            }
        }
        print(gaussSeidel(gsA,gsB,gsS));
    }

    private static void adjacency() {
        int[][] adjMatrix = new int[][]{
               /*1  2  3  4  5  6  7  8  9 10 11 12 13 14 15*/
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},//1
                {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},//2
                {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},//3
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},//4
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},//5
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},//6
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},//7
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},//8
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},//9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},//10
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//11
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0},//12
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},//13
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1},//14
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},//15
        };
        double q = 0.15;
        double[][] googleMatrix = getGoogleMatrix(adjMatrix,q);
        print(getNormEigenvector(googleMatrix));//node 15 is the most important

        adjMatrix[9][2]=1;
        adjMatrix[10][2]=1;
        googleMatrix = getGoogleMatrix(adjMatrix,q);
        print(getNormEigenvector(googleMatrix));//node 3 is now the most important

    }

}
