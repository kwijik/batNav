package batailleNavale;

public class Coordonnee {

	private int ligne;
	private int colonne;
	
	/* Méthodes de classe
	 * 
	 * String toString()
	 * int getLigne() 
	 * int getColonne()
	 * boolean equals(Object obj)
	 * boolean voisine(Coordonnee c)
	 * int compareTo(Coordonnee c)
	 */

	public Coordonnee(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public Coordonnee(String s) {
		if (s.equals("")) {
			throw new IllegalArgumentException("La chaine est vide."); 
		}
		colonne = (int)s.charAt(0) - (int)'A' + 1;
	
		try {
			ligne = Integer.parseInt(s.substring(1));
		} catch(NumberFormatException e){ // si c'est pas le nombre
			e.printStackTrace(); // 
			ligne = 0; // 
		}
	
	}

	public String toString() {
		 return Character.toString((char)(colonne + (int)'A' -1)) + Integer.toString(ligne);
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Coordonnee){
			if ( ((Coordonnee)obj).ligne == ligne && ((Coordonnee)obj).colonne == colonne ){
				return true;
			}
		}
		return false;
	}

	public boolean voisine(Coordonnee c) {
		if (c.colonne + 1 == this.colonne && c.ligne == this.ligne){
			return true;
		} else if (c.colonne  - 1 == this.colonne && c.ligne == this.ligne) {
			return true;
		} else if (c.colonne == this.colonne && c.ligne + 1 == this.ligne) {
			return true;
		} else if (c.colonne == this.colonne && c.ligne - 1 == this.ligne) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Coordonnee c){
		return (ligne - c.ligne) * 100 + (colonne - c.colonne);
	}

	public static void main(String[] args) {
		//Coordonnee c = new Coordonnee(12, 5);
		//System.out.println(c);
		Coordonnee c2 = new Coordonnee("");
		System.out.println(c2.voisine(c2));
	}
}
