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
		
	}

	public void attaque(Coordonnee c) {
		if (adversaire.defense(c)) {
			adversaire.debutAttaque();
		}
	}

	public boolean defense(Coordonnee c) {
		if(this.grille.recoitTir(c)){
			if (!this.grille.perdu()){
				
			}
		}
		
	}

	protected abstract void perdu();

	protected abstract void gagne();

	protected abstract void retourAttaque(Coordonnee c, int etat);

	protected abstract void retourDefense(Coordonnee c, int etat);

	public abstract void debutAttaque();
}