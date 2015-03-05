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

import static java.lang.Math.*;

/**
 * Created by Stratos on 05-Mar-15.
 */
public class Function1 implements Function{

    @Override
    public double f(double x) {
        return (exp(pow(sin(x), 3)) + pow(x, 6) - 2 * pow(x, 4) - pow(x, 3) - 1);
    }

    @Override
    public double fd(double x) {
        return ((6 * pow(x, 3) - 8 * x - 3) * pow(x, 2) + 3 * exp(pow(sin(x), 3)) * pow(sin(x), 2) * cos(x));
    }
}
