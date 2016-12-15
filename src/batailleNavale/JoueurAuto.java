package batailleNavale;

public class JoueurAuto extends Joueur {
	public JoueurAuto(GrilleNavale g, String nom) {
		super(g,nom);
	}

	protected void perdu() {
		System.out.println("encore fois?");
	}

	protected void gagne() {
		System.out.println("victoire");
	}

	protected void retourAttaque(Coordonnee c, int etat) {
	}

	protected void retourDefense(Coordonnee c, int etat) {
	}

	public void debutAttaque() {

	}
}