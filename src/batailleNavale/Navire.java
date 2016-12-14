package batailleNavale;

import java.util.ArrayList;

public class Navire {

	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	
	// nouveaux variables
	private boolean estVertical;
	private int longueur;
	/* Méthodes de classe
	 * 
	 * String toString()
	 * Coordonnee getDebut()
	 * Coordonnee getFin()
	 * boolean contientt(Coordonnee c) 
	 * boolean touche(Navire n)
	 * boolean chevauchee(Navire n)
	 * boolean recoitTirr(Coordonnee c)
	 * boolean estTouchee(Coordonnee c)
	 * boolean estTouche() 
	 * c boolean estCoule() 
	 */
	// add voisine method
	// boolean - estVertical, int - longueur -> ?
	
	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.estVertical = estVertical;
		this.longueur = longueur;
		this.debut = debut;
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0;
		if (estVertical) {
			fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne());
		} else {
			fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
		}
	}
	
	public String toString(){
		if (this.estVertical){
		    return "Navire(" + this.debut + "," + this.longueur + ",Vertical)";
		} else{
			return "Navire(" + this.debut + "," + this.longueur + "Horizontal)";
		}
	}

	public Coordonnee getDebut() {
		return debut;
	}

	public Coordonnee getFin() {
		return fin;
	}

	public boolean contient(Coordonnee c) { // проверка для горизонтального
		if (!estVertical) {
			if (debut.getColonne() <= c.getColonne() && fin.getColonne() >= c.getColonne() && c.getLigne() == debut.getLigne()) {
				return true;
			}
		} else { // проверка для вертикального
			if (debut.getLigne() <= c.getLigne() && fin.getLigne() >= c.getLigne() && c.getColonne() == debut.getColonne()) {
				return true;
			}
		}
		return false;

	}

	public boolean touche(Navire n) {
		// true если this прилегает к n. По диагонали не считается за соседство
		ArrayList<Coordonnee> navire1, navire2;
		navire1 = new ArrayList<Coordonnee>();
		navire2 = new ArrayList<Coordonnee>();
		if (debut.getLigne() == fin.getLigne()) {
			// идем по колонкам
			for (int i = debut.getColonne(); i <= fin.getColonne(); i++) {
				Coordonnee c = new Coordonnee(debut.getLigne(), i);
				// System.out.println(c);
				navire1.add(c);
			}
		} else {
			for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
				Coordonnee c = new Coordonnee(i, debut.getColonne());
				// System.out.println(c);
				navire1.add(c);
			}
		}
		System.out.println();
		if (n.debut.getLigne() == n.fin.getLigne()) {
			// идем по колонкам
			for (int i = n.debut.getColonne(); i <= n.fin.getColonne(); i++) {
				Coordonnee c = new Coordonnee(n.debut.getLigne(), i);
				// System.out.println(c);
				navire2.add(c);
			}
		} else {

			for (int i = n.debut.getLigne(); i <= n.fin.getLigne(); i++) {
				Coordonnee c = new Coordonnee(i, n.debut.getColonne());
				// System.out.println(c);
				navire2.add(c);
			}
		}

		for (Coordonnee a : navire1) {
			for (Coordonnee b : navire2) {
				if (a.voisine(b)) {
					return true;
				}
			}
		}
		return false;
	}
// переписать проверить длину обеих кораблей
 
	public boolean chevauche(Navire n) {
		Navire n1, n2;
		// 4 cases:
		//1 -> (this -ver n-vert -> n.col= this.col && );
		if (estVertical && n.estVertical){
			
			if (this.debut.getLigne() <= n.debut.getLigne()){
				n1 = this;
				n2 = n;
			} else {
				n1 = n;
				n2 = this;
			}
			if (n2.debut.getLigne() <= n1.fin.getLigne()){
				return true;
			} else {
				return false;
			}
		} else if(!estVertical && !n.estVertical){
			if (this.debut.getColonne()<= n.debut.getColonne()){
				n1 = this;
				n2 = n;
			} else {
				n1 = n;
				n2 = this;
			}
			if (n2.debut.getColonne() <= n1.fin.getColonne()){
				return true;
			} else {
				return false;
			} 
		} else {
			if(n.estVertical){
				n1 = n;
				n2 = this;
			} else {
				n2 = n;
				n1 = this;
			}
			if (n1.debut.getLigne() <= n2.debut.getLigne() && n1.fin.getLigne() >= n2.debut.getLigne() && 
				n2.debut.getColonne() <= n1.debut.getColonne() && n2.fin.getColonne() >= n1.debut.getColonne() ){
				return true;
			} else {
				return false;
			}
				
		}
		/*
		// true если this и n занимают как минимум одну общую координату
		ArrayList<Coordonnee> navire1, navire2; // все координаты кораблей
		navire1 = new ArrayList<Coordonnee>(); 
		navire2 = new ArrayList<Coordonnee>();
		if (estVertical) {
			// идем по колонкам
			for (int i = debut.getColonne(); i <= fin.getColonne(); i++) {
				Coordonnee c = new Coordonnee(debut.getLigne(), i);
				// System.out.println(c);
				navire1.add(c);
			}
		} else {
			for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
				Coordonnee c = new Coordonnee(i, debut.getColonne());
				// System.out.println(c);
				navire1.add(c);
			}
		}
		
		if (n.estVertical) {
			// идем по колонкам
			for (int i = n.debut.getColonne(); i <= n.fin.getColonne(); i++) {
				Coordonnee c = new Coordonnee(n.debut.getLigne(), i);
				// System.out.println(c);
				navire2.add(c);
			}
		} else {

			for (int i = n.debut.getLigne(); i <= n.fin.getLigne(); i++) {
				Coordonnee c = new Coordonnee(i, n.debut.getColonne());
				// System.out.println(c);
				navire2.add(c);
			}
		}

		for (Coordonnee a : navire1) {
			for (Coordonnee b : navire2) {
				if (a.equals(b)) {
					return true;
				}
			}
		}
		return false;
		*/
	}
	
	public boolean estVoisin(Navire n){
		// true если this и n занимают как минимум одну общую координату
				ArrayList<Coordonnee> navire1, navire2; // все координаты кораблей
				navire1 = new ArrayList<Coordonnee>(); 
				navire2 = new ArrayList<Coordonnee>();
				if (estVertical) {
					// идем по колонкам
					for (int i = debut.getColonne(); i <= fin.getColonne(); i++) {
						Coordonnee c = new Coordonnee(debut.getLigne(), i);
						// System.out.println(c);
						navire1.add(c);
					}
				} else {
					for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
						Coordonnee c = new Coordonnee(i, debut.getColonne());
						// System.out.println(c);
						navire1.add(c);
					}
				}
				
				if (n.estVertical) {
					// идем по колонкам
					for (int i = n.debut.getColonne(); i <= n.fin.getColonne(); i++) {
						Coordonnee c = new Coordonnee(n.debut.getLigne(), i);
						// System.out.println(c);
						navire2.add(c);
					}
				} else {

					for (int i = n.debut.getLigne(); i <= n.fin.getLigne(); i++) {
						Coordonnee c = new Coordonnee(i, n.debut.getColonne());
						// System.out.println(c);
						navire2.add(c);
					}
				}

				for (Coordonnee a : navire1) {
					for (Coordonnee b : navire2) {
						if (a.voisine(b)) {
							return true;
						}
					}
				}
				return false;
	}

	public boolean recoitTir(Coordonnee c) {
		// true если this содержит
		ArrayList<Coordonnee> navire1;
		navire1 = new ArrayList<Coordonnee>();
		if (debut.getLigne() == fin.getLigne()) {
			// идем по колонкам
			for (int i = debut.getColonne(); i <= fin.getColonne(); i++) {
				Coordonnee v = new Coordonnee(debut.getLigne(), i);
				// System.out.println(c);
				navire1.add(v);
			}
		} else {
			for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
				Coordonnee v = new Coordonnee(i, debut.getColonne());
				// System.out.println(c);
				navire1.add(v);
			}
		}

		for (Coordonnee a : navire1) {

			if (a.equals(c)) {
				partiesTouchees[nbTouchees] = a;
				nbTouchees += 1; 
				return true;
			}
		}
		
		return false;

	}

	
	 public boolean estTouche(Coordonnee c) { 
		 //true если this подбита на координате с 
		 for (int i=0; i < nbTouchees; i++){
			 if (c.equals(partiesTouchees[i])){
				 return true;
			 }
		 }
		 return false;
	}
	  
	  public boolean estTouche() { //true если у this хоть одна клетка подбита
		  return nbTouchees != 0;
	  }
	  
	 public boolean estCoule() { 
		 return partiesTouchees.length == nbTouchees;
		 
	 }
	 /*
	public static void main(String[] args) {
		Navire n1 = new Navire(new Coordonnee(6, 7), 2, true);
		Navire n2 = new Navire(new Coordonnee(7, 7), 3, false);
		System.out.println(n1.touche(n2));
		System.out.println(n1);
		System.out.println(n2);
	}
	*/
}
// 18-30 - 20-30
// 15-15 - 16-45 вторник
//
