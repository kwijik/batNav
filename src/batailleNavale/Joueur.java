package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	private Joueur adversaire;
	private GrilleNavale grille;
	private String nom;

	public Joueur(GrilleNavale g, String nom) {
		this.nom = nom;
		grille = g;
	}

	public Joueur(GrilleNavale g) {
		grille = g;
	}
	
	public String getNom(){
		return nom;
	}

	public GrilleNavale getGrille() {
		return grille;
	}

	public void jouerAvec(Joueur j) {
		adversaire = j;
		debutAttaque();
	}

	public void attaque(Coordonnee c) {
		if (adversaire.defense(c)) {
			adversaire.debutAttaque();
		}
	}

	public boolean defense(Coordonnee c) {
		int etat;
		boolean res;
		if (grille.recoitTir(c)){
			if (grille.estTouche(c) && !grille.perdu()){
				etat = TOUCHE;
				res= true;
			} else {
				if (grille.perdu()){
					res = false;
				}
				etat = COULE; // убили
				res = true;
			}
		} 
		else {
			etat = A_L_EAU; // в воду
			perdu();
			adversaire.gagne();	
			res = true;
		}
		
		this.retourDefense(c, etat);
		adversaire.retourAttaque(c, etat);		
		return res;
		
	}

	protected abstract void perdu();

	protected abstract void gagne();

	protected abstract void retourAttaque(Coordonnee c, int etat);

	protected abstract void retourDefense(Coordonnee c, int etat);

	public abstract void debutAttaque();
}