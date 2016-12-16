package batailleNavale;

import java.util.ArrayList;
import java.util.Random;

public class JoueurAuto extends Joueur {
	
	private Random r;
	ArrayList<Coordonnee> coords;
	
	public JoueurAuto(GrilleNavale g, String nom) {
		super(g,nom);
		coords = new ArrayList<>();
		r = new Random();
	}

	protected void perdu() {
		//System.out.println("encore fois?");
	}

	protected void gagne() {
		//System.out.println("victoire");
	}

	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println(super.getNom() + " attacks " + c);

	}

	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println(super.getNom() + " defends " + c);

	}

	public void debutAttaque() {
		int num1;
		int num2;
		Coordonnee c;
		do {
			 num1 = r.nextInt(super.getGrille().getTaille())+1;
			 num2 = r.nextInt(super.getGrille().getTaille())+1;
			 c = new Coordonnee(num1, num2);
		} while (coords.contains(c));
		attaque(c);
	}
}