package de.panschk.omby.calc;
/**
 * Klasse zur Berechnung der Standardnormalverteilung mit einstellbarer
 * Präzision.
 */
public class StandardNormalVerteilung {

	/**
	 * Berechnet die Gaussfunktion (Wahrscheinlichkeitsdichte) mit
	 * Erwartungswert 0 und Varianz 1 für ein gegebenes x.
	 * 
	 * @param x
	 *            Stelle an der der Funktionswert berechnet werden soll
	 * @return Funktionswert an der Stelle x
	 */
	private static double gauss(double x) {
		return 1 / (Math.sqrt(2 * Math.PI)) * Math.exp(-Math.pow(x, 2) / 2);
	}

	/**
	 * Berechnet die Standardnormalverteilung für ein gegebenes x mittels
	 * Integralapproximierung (Simpson-Verfahren).
	 * 
	 * @param x
	 *            Stelle x an der die Standardnormalverteilung berechnet werden
	 *            soll
	 * @param N
	 *            Präzisionsparameter für das Simpson-Verfahren (je höher desto
	 *            genauer das Ergebnis)
	 * @return (Approximierte) Standardnormalverteilung an der Stelle x
	 */
	public static double normalVerteilung(double x, int N) {
		double limitLinks = -10;
		if (x <= limitLinks)
			return 0;

		double h = Math.abs(limitLinks - x) / N;
		double summe = 0;

		// 1. Schleife in normalVerteilung()
		for (int i = 1; i <= N - 1; i++) {
			double xi = limitLinks + i * h;
			summe += gauss(xi);
		}

		// 2. Schleife in normalVerteilung()
		for (int i = 1; i <= N; i++) {
			double xi_vorher = limitLinks + (i - 1) * h;
			double xi = limitLinks + i * h;
			summe += 2 * gauss((xi_vorher + xi) / 2);
		}

		summe = (h / 3) * (0.5 * gauss(limitLinks) + summe + 0.5 * gauss(x));
		return summe;
	}

	public static void main(String[] args) {
		double x1 = normalVerteilung(-1, 100);
		double x2 = normalVerteilung(0, 100);
		double x3 = normalVerteilung(1, 100);

		System.out.println("normalVerteilung(-1) ~ " + x1);
		System.out.println("normalVerteilung(0) ~ " + x2);
		System.out.println("normalVerteilung(1) ~ " + x3);
	}

}
