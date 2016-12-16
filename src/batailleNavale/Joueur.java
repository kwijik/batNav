package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	protected Joueur adversaire;
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
		adversaire.adversaire = this;
		debutAttaque();
		
	}

	public void attaque(Coordonnee c) {
	//	System.out.println(this.getNom() + " attaque");

		if (adversaire.defense(c)) {
			adversaire.debutAttaque();
		}
	}

	public boolean defense(Coordonnee c) {
	//	System.out.println(this.getNom() + " defense");
		int etat;
		
		if (grille.recoitTir(c)){
			if (grille.perdu()){
				perdu();
				adversaire.gagne();
				return false;
			}
			if (grille.estCoule(c)){ // не проиграли пока
				etat = COULE;
			} else {
				
				etat = TOUCHE; // подбили
			}
		} 
		else {
			etat = A_L_EAU; // в воду
			//perdu();
			//adversaire.gagne();	
		}
		
		this.retourDefense(c, etat);
		adversaire.retourAttaque(c, etat);		
		return true;
		
	}

	protected abstract void perdu();

	protected abstract void gagne();

	protected abstract void retourAttaque(Coordonnee c, int etat);

	protected abstract void retourDefense(Coordonnee c, int etat);

	public abstract void debutAttaque();
}