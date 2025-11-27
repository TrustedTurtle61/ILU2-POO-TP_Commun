package model;

public class CalendrierAnnuel {
	private Mois[] calendrier;
	private String[] nomMois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
			"Octobre", "Novembre", "Décembre" };
	private int[] joursMois = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public CalendrierAnnuel() {
		calendrier = new Mois[12];
		for (int i = 0; i < 12; i++) {
			calendrier[i] = new Mois(nomMois[i], joursMois[i]);
		}
	}

	protected class Mois {
		private String nom;
		private boolean[] jours;

		public Mois(String nom, int nbJours) {
			this.nom = nom;
			this.jours = new boolean[nbJours];
			for (int i = 0; i < jours.length; i++) {
				jours[i] = false;
			}
		}

		private boolean estLibre(int jour) {
			return !(jours[jour - 1]);
		}

		private boolean reserver(int jour) throws IllegalStateException {
			if (estLibre(jour)) {
				jours[jour - 1] = true;
				return true;
			} else {
				throw new IllegalStateException();
			}
		}
	}

	public boolean estLibre(int jour, int mois) {
		if (0 < mois && mois < 13 && jour <= joursMois[mois - 1]) {
			return calendrier[mois - 1].estLibre(jour);
		} else {
			return false; // comportement arbitraire
		}
	}

	public boolean reserver(int jour, int mois) {
		if (estLibre(jour, mois)) {
			return calendrier[mois - 1].reserver(jour);
		} else {
			return false;
		}
	}

}
