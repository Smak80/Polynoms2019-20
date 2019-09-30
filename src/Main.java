import ru.smak.polynoms.Polynom;

public class Main {
	public static void main(String[] args) {
		double[] c1 = {1, 1e-8, 2, 0, 0, 0};
		double[] c2 = {-1, 1, -1, 2, -1};
		Polynom p1 = new Polynom(c1);
		Polynom p2 = new Polynom(c2);
		Polynom p3 = p1.plus(p2);
		Polynom p4 = p2.plus(p1);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p4.times(-1));
		System.out.println(p4.times(0));
		System.out.println(p2.minus(p2));
		double[] cm1 = {1, 0, 1, 1};
		double[] cm2 = {4, 3, -1};
		Polynom pm1 = new Polynom(cm1);
		Polynom pm2 = new Polynom(cm2);
		Polynom pm3 = pm1.times(pm2);
		System.out.println(pm1);
		System.out.println(pm2);
		System.out.println(pm3);
		System.out.println(pm3.getValue(2.0));
	}
}
