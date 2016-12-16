import java.util.Scanner;

public class JoueurTexte extends Joueur {
	private Scanner sc;

	public JoueurTexte(GrilleNavale g, String nom) {
		super(g,nom);
	}

	public void jouerAvec(Joueur j) {
		super.jouerAvec(j);
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

