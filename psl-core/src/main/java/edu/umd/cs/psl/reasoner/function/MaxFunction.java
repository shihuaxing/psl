/*
 * This file is part of the PSL software.
 * Copyright 2011 University of Maryland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.umd.cs.psl.reasoner.function;

import java.util.*;

public class MaxFunction implements Iterable<FunctionTerm>, FunctionTerm {

	private final List<FunctionTerm> terms;
	
	public MaxFunction() {
		terms = new ArrayList<FunctionTerm>();
	}
	
	public void add(FunctionTerm t) {
		terms.add(t);
	}

	@Override
	public Iterator<FunctionTerm> iterator() {
		return terms.iterator();
	}
	
	public int size() {
		return terms.size();
	}
	
	public FunctionTerm get(int pos) {
		return terms.get(pos);
	}

	@Override
	public double getValue() {
		if (terms.isEmpty()) throw new AssertionError("Undefined max value for zero terms!");
		double val = Double.NEGATIVE_INFINITY;
		for (FunctionTerm t : terms) val = Math.max(val, t.getValue());
		return val;
	}
	
	@Override
	public double getValue(Map<? extends FunctionVariable,Double> values, boolean assumeDefaultValue) {
		if (terms.isEmpty()) throw new AssertionError("Undefined max value for zero terms!");
		double val = Double.NEGATIVE_INFINITY;
		for (FunctionTerm t : terms) val = Math.max(val, t.getValue(values,assumeDefaultValue));
		return val;
	}


	
	@Override
	public boolean isLinear() {
		for (FunctionTerm t : terms) {
			if (!t.isLinear()) return false;
		}
		return true;
	}
	
	@Override
	public boolean isConstant() {
		for (FunctionTerm t : terms) {
			if (!t.isConstant()) return false;
		}
		return true;
	}
	
	public static MaxFunction of(FunctionTerm...terms) {
		MaxFunction max = new MaxFunction();
		for (FunctionTerm t : terms) max.add(t);
		return max;
	}
	
	
}