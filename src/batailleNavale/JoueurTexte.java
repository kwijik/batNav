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
		if(etat ==  TOUCHE){
			System.out.println("opponent touche");
			
		} else if (etat == COULE){
			System.out.println("opponent killed");
			
		} else {
			System.out.println("opponent missed");
			//debutAttaque();
		}
	}

	public void debutAttaque() {
		System.out.print("enter new cell to attack please: ");
		String cell = sc.nextLine();
		Coordonnee c = new Coordonnee(cell.trim());
		boolean def = defense(c);
		
	}
}

