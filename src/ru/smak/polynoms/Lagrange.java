package ru.smak.polynoms;

import java.util.Hashtable;
import java.util.Map;

public class Lagrange extends Polynom {
	private Hashtable<Double, Double> vals;
	public Lagrange(Hashtable<Double, Double> vals) throws PolynomException {
		this.vals = new Hashtable<>();
		this.vals.putAll(vals);
		createLagrange();
	}

	public Lagrange(double[] x, double[] y) throws PolynomException {
		if (x.length!=y.length) throw new PolynomException(1);
		vals = new Hashtable<>();
		for (int i = 0; i<x.length; i++){
			vals.putIfAbsent(x[i], y[i]);
		}
		createLagrange();
	}

	private void createLagrange() throws PolynomException {
		if (vals.size()==0) throw new PolynomException(2);
		Polynom res = new Polynom();
		int k = 0;
		for (Map.Entry<Double, Double>e: vals.entrySet()){
			res = res.plus(
				createFundamentalLagrange(
						e.getKey()
				).times(
						e.getValue()
				)
			);
			k++;
		}
		c.clear();
		c.addAll(res.c);
	}

	private Polynom createFundamentalLagrange(double x_k) {
		double[] x = {1};
		Polynom res = new Polynom(x);
		for (Map.Entry<Double, Double>e: vals.entrySet()){
			if (Math.abs(x_k-e.getKey())<1e-7) continue;
			double[] p = {-e.getKey(), 1};
			res = res.times(
					new Polynom(p).divide(
							x_k-e.getKey()
					)
			);
		}
		return res;
	}
}
