package ru.smak.polynoms;

import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
	//Набор коэффициентов полинома
	protected ArrayList<Double> c;

	public Polynom(){
		c  = new ArrayList<>();
		c.add(0.0);
	}

	public Polynom(ArrayList<Double> coeffs){
		c = new ArrayList<>();
		c.addAll(coeffs);
		correctDeg();
	}

	public Polynom(double[] coeffs){
		c = new ArrayList<>();
		for (double el: coeffs){
			c.add(el);
		}
		correctDeg();
	}

	public int deg(){
		return c.size()-1;
	}

	private void correctDeg(){
		int i = c.size()-1;
		while (i>0 && Math.abs(c.get(i))<1e-6){
			c.remove(i);
			i--;
		}
	}

	public Polynom plus(Polynom other){
		int max_d = Math.max(deg(), other.deg());
		int min_d = Math.min(deg(), other.deg());
		ArrayList<Double> cf = new ArrayList<>();
		for (int i = 0; i<=min_d; i++){
			cf.add(c.get(i)+other.c.get(i));
		}
		Polynom mp = (deg()>other.deg())?this:other;
		for (int i = min_d+1; i<=max_d; i++){
			cf.add(mp.c.get(i));
		}
		return new Polynom(cf);
	}

	public Polynom times(double x){
		double[] cf = new double[c.size()];
		for (int i = 0; i<cf.length; i++){
			cf[i] = c.get(i) * x;
		}
		return new Polynom(cf);
	}

	public Polynom divide(double x){
		return times(1.0/x);
	}

	public double getValue(double x){
		double s = c.get(0);
		double px = x;
		for (int i = 1; i<c.size(); i++){
			s += px*c.get(i);
			px*=x;
		}
		return s;
	}

	public Polynom times(Polynom other){
		double[] cf = new double[deg()+other.deg()+1];
		for (int i = 0; i<=this.deg(); i++){
			for (int j = 0; j<=other.deg(); j++){
				cf[i+j]+=c.get(i)*other.c.get(j);
			}
		}
		return new Polynom(cf);
	}

	public Polynom minus(Polynom other){
		return plus(other.times(-1));
	}

	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		for (int i = c.size()-1; i>=0; i--){
			if (Math.abs(c.get(i))>1e-6) {
				if (c.get(i) < 0) res.append(" - ");
				else if (c.get(i) > 0 && i != c.size() - 1)
					res.append(" + ");
				if ((Math.abs(c.get(i)) - 1) > 1e-6 ||
					i == 0
				) res.append(Math.abs(c.get(i)));
				if (i > 0) {
					res.append('x');
					if (i > 1) {
						res.append("^");
						res.append(i);
					}
				}
			} else if (c.size()==1){
				res.append("0");
			}
		}
		return res.toString();
	}
}
