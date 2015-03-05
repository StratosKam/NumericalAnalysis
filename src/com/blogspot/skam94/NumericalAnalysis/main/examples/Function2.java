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

import com.blogspot.skam94.NumericalAnalysis.main.datatypes.Function;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class Function2 implements Function{
    @Override
    public double f(double x) {
        return (94 * pow(cos(x), 3)
                - 24 * cos(x)
                + 177 * pow(sin(x), 2)
                - 108 * pow(sin(x), 4)
                - 72 * pow(cos(x), 3) * pow(sin(x), 2)
                - 65);
    }

    @Override
    public double fd(double x) {
        return (3 / 2 * pow((1 - 2 * cos(x)), 2) * (sin(x) + 6 * sin(2 * x) - 15 * sin(3 * x)));
    }
}
