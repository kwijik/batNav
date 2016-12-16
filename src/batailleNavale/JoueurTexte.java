package batailleNavale;

import java.util.Scanner;
// где инициализация и цикл while (!game) -> play(); ?
// 
public class JoueurTexte extends Joueur {
	private Scanner sc;

	public JoueurTexte(GrilleNavale g, String nom) {
		super(g,nom);
		sc  = new Scanner(System.in);
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
		//System.out.println(super.getNom() + " retourAttaque");
		
		System.out.println(super.getNom() + " attacks " + c);
		
		if(etat ==  TOUCHE){
			System.out.println("touche");
			//debutAttaque();
		} else if (etat == COULE){
			System.out.println("kill");
			//debutAttaque();
		} else {
			System.out.println("missing");
		}
	}

	protected void retourDefense(Coordonnee c, int etat) {
		//System.out.println(super.getNom() + " retourDefense" );

		System.out.println(super.getNom() + " defends " + c);

		if(etat ==  TOUCHE){
			System.out.println("opponent touche");
			
		} else if (etat == COULE){
			System.out.println("opponent killed");
			
		} else {
			System.out.println("opponent missed");
			//debutAttaque();
		}
		System.out.println(super.getGrille());

	}

	public void debutAttaque() {
		//System.out.println(super.getNom() + " debutAttaque");

		//System.out.println(super.adversaire.getGrille());
		System.out.print("enter new cell to attack please: ");
		String cell = sc.nextLine();
		Coordonnee c = new Coordonnee(cell.trim());
		attaque(c);
	}
	
	public static void main(String[] args) {
		int[] i = { 1};
		GrilleNavale g1 = new GrilleNavale(10, i);
		
		Joueur joe = new JoueurTexte(g1, "Joe");
		//System.out.println(g1);
		GrilleNavale g2 = new GrilleNavale(10, i);
		Joueur kate = new JoueurAuto(g2, "Kate");
		//System.out.println(g2);
	    joe.jouerAvec(kate);
		
	}
}

