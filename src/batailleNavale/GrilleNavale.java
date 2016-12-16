package batailleNavale;

import java.util.Iterator;
import java.util.Random;

public class GrilleNavale {

	private Navire[] navires; 
	private int nbNavires; 
	private int tailleGrille; 
	private Coordonnee[] tirsRecus; 
	private int nbTirsRecus; 

	/*
	 * Méthodes de classe
	 * 
	 * String toString()
	 * boolean ajouteNavire(Navire n)
	 * void placementAuto(int[] taillesNavires) 
	 * boolean estDansGrille(Coordonnee c) 
	 * boolean estDansTirsRecus(Coordonnee c) 
	 * boolean ajouteDansTirsRecus(Coordonnee c)
	 * boolean recoitTir(Coordonnee c) 
	 * boolean estTouche(Coordonnee c) 
	 * boolean estALEau(Coordonnee c) 
	 * boolean estCoule(Coordonnee c) 
	 * boolean perdu()
	 */

	// taillesNavires {4,2,3}
	
	public GrilleNavale(int taille, int[] taillesNavires) {
		// this(taille);
		this.tailleGrille = taille;
		navires = new Navire[taillesNavires.length];
		nbNavires = 0;
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
		placementAuto(taillesNavires);
	}

	public GrilleNavale(int taille, int nbNavires) {
		this.tailleGrille = taille;
		navires = new Navire[nbNavires];
		this.nbNavires = 0;
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
	}

	/*
	 * public GrilleNavale(int taille) { this.tailleGrille = taille; navires =
	 * new Navire[0]; nbNavires = 0; tirsRecus = new Coordonnee[taille *
	 * taille]; nbTirsRecus = 0; }
	 * 
	 */

	public String toString() {
		String cell = "";
		for (int y = 0; y < tailleGrille; y++) {
			for (int x = 0; x < tailleGrille; x++) {
				boolean estNavire = false;
				boolean estTouche = false;

				Coordonnee c = new Coordonnee(y + 1, x + 1);
				for (Navire n : navires) {
					if (n.contient(c)) {
						estNavire = true;
						break;
					}
				}
				for (int i = 0; i < nbTirsRecus; i++) {
					if (c.equals(tirsRecus[i])) {
						estTouche = true;
						break;
					}

				}
				if (estNavire && estTouche) {
					cell += "X ";
				}
				if (!estNavire && estTouche) {
					cell += "O ";
				}
				if (!estNavire && !estTouche) {
					cell += ". ";
				}
				if (estNavire && !estTouche) {
					cell += "# ";
				}
			}
			cell += "\n";
		}
		return cell;
	}

	/*
	 * A B C D E F G H I J 1 . . . . . . . . . . 2 . . . # # # . . . . 3 . . . .
	 * . . # X # . 4 . . O . . . . O . . 5 . . . . . # . . . . 6 . . . O . # . .
	 * . . 7 . # # . . # . . . . 8 . . . . . # . . . . 9 . . . . . . . O . . 10
	 * . . . . . . . . . .
	 * 
	 * //точка - свободная клетка, в которую не стреляли; # - занятая кораблем,
	 * O - свободная клетка получившая выстрел, X - подбитая часть корабля
	 * 
	 * }
	 */
	public boolean ajouteNavire(Navire n) {
		if (nbNavires == navires.length) {
			return false;
		}
		for (int i = 0; i < nbNavires; i++) {
			if (navires[i].chevauche(n) || navires[i].estVoisin(n)) {
				return false;
			}
		}

		if (nbNavires == navires.length) {
			return false;
		}
		navires[nbNavires] = n;
		nbNavires += 1;
		return true;
	}

	public void placementAuto(int[] taillesNavires) {
		// автоматически и случайно расставляет корабли
		Random r = new Random();
		
		for (int i : taillesNavires) {
			
			Navire n;
			do {
				int randomNum1 = r.nextInt(tailleGrille - i + 1) + 1;
				int randomNum2 = r.nextInt(tailleGrille) + 1;
				boolean randomBool = r.nextBoolean();
				System.out.println("i = " + i + ", vertical: " + randomBool + ", number1: " + randomNum1 + ", number2: " + randomNum2);
				if (randomBool) { // vertical or
					// r.nextInt(tailleGrille - i) distance du bout
					// r.nextInt(tailleGrille)+ 1 -> [1...10]
					n = new Navire(new Coordonnee(randomNum1, randomNum2), i,
							true);
				} else {
					n = new Navire(new Coordonnee(randomNum2, randomNum1), i,
							false);
				}
			} while (!ajouteNavire(n));

		}

	}

	private boolean estDansGrille(Coordonnee c) {
		return c.getLigne() > 0 && c.getLigne() <= tailleGrille && c.getColonne() > 0 && c.getColonne() <= tailleGrille;
	}

	private boolean estDansTirsRecus(Coordonnee c) {
		for (int i = 0; i < nbTirsRecus; i++) {
			if (c.equals(tirsRecus[i])) {
				return true;
			}
		}
		return false;
	}

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if (estDansTirsRecus(c)) {
			return false;
		}
		tirsRecus[nbTirsRecus] = c;
		nbTirsRecus += 1;
		return true;

	}

	public boolean recoitTir(Coordonnee c) {
		return ajouteDansTirsRecus(c);
	}

	public boolean estTouche(Coordonnee c) {
		return estDansTirsRecus(c);
	}

	public boolean estALEau(Coordonnee c) {
		return !recoitTir(c);
	}

	public boolean estCoule(Coordonnee c) { 
		for (Navire n : navires) {
			if (n.contient(c)) { 
				if (n.getNbTouchees() == n.getLongueur()) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean perdu() { 
		// this.perdu() -> true / false
		for (Navire n : navires) {
			if (!n.estCoule()) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] i = { 1, 2,  3, 1 , 4, 2, 3, 4, 3};
		GrilleNavale gn = new GrilleNavale(10, i);

		System.out.println(gn);

	}
}
