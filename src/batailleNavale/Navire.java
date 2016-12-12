package batailleNavale;

public class Navire {

	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0;
		if (estVertical) {
			fin = new Coordonnee(debut.getLigne() + longueur, debut.getColonne());
		} else {
			fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur);
		}
	}

	public Coordonnee getDebut() {
		return debut;
	}

	public Coordonnee getFin() {
		return fin;
	}

	public boolean contient(Coordonnee c) { //проверка для горизонтального
		if (debut.getLigne() == fin.getLigne()) {
			if (debut.getColonne() <= c.getColonne() && fin.getColonne() >= c.getColonne()) {
				return true;
			}
		} else { //проверка для вертикального
			if (debut.getLigne() <= c.getLigne() && fin.getLigne() >= c.getLigne()) {
				return true;
			}
		}
		return false;
		
	}
/*
	public boolean touche(Navire n) {
		
	}

	public boolean chevauche(Navire n) {
		
	}

	public boolean recoitTir(Coordonnee c) {
		
	}

	public boolean estTouche(Coordonnee c) {
		
	}

	public boolean estTouche() {
		
	}

	public boolean estCoule() {
		
	}
*/
}
