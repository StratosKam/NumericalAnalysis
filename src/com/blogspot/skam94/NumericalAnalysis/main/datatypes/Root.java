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
package com.blogspot.skam94.NumericalAnalysis.main.datatypes;

public class Root {
    private final double value;
    private final int loops;

    public Root() {
        value = Double.NaN;
        loops = -1;
    }

    public Root(double v, int l) {
        value = v;
        loops = l;
    }

    public double getValue() {
        return value;
    }

    public int getLoops() {
        return loops;
    }
}
