package batailleNavale;

import java.util.Iterator;
import java.util.Random;

public class GrilleNavale {

	private Navire[] navires;  // массив кораблей
	private int nbNavires; // количество кораблей ?? Какие корабли?
	private int tailleGrille; //размер поля
	private Coordonnee[] tirsRecus; // координаты клеток куда стреляли
	private int nbTirsRecus; // количество выстрелов

	
	/* Méthodes de classe
	 * 
	 * String toString() 
	 * boolean ajouteNavire(Navire n)
	 * void placementAuto(int[] taillesNavires) 
	 * boolean estDansGrille(Coordonnee c) 
	 * boolean estDansTirsRecus(Coordonnee c)
	 * boolean ajouteDansTirsRecus(Coordonnee c)
	 * boolean recoitTir(Coordonnee c) 
	 * boolean estTouche(Coordonnee c)
	 *  boolean estALEau(Coordonnee c)
	 *  boolean estCoule(Coordonnee c)
	 *  boolean perdu()
	 */
	
	//taillesNavires {4,2,3} -> три корабля разного размера. Цифра - размер корабля
	public GrilleNavale(int taille, int[] taillesNavires) {
		//this(taille);	
		this.tailleGrille = taille;
		navires = new Navire[taillesNavires.length];
		nbNavires = 0;
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
		placementAuto(taillesNavires);
	}
	
	public GrilleNavale(int taille,  int nbNavires) {
	    // возвращает пустое поле размера taille и он может вместить nbNavires
		this.tailleGrille = taille;
		navires = new Navire[nbNavires];
		this.nbNavires = 0;
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
	}
	
	/*
	public GrilleNavale(int taille) {
		this.tailleGrille = taille;
		navires = new Navire[0];
		nbNavires = 0;
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
	}
	
	*/
	
	 
	 public String toString(){
		 String cell = "";
		 for (int y = 0; y < tailleGrille; y++) {
			for (int x = 0; x < tailleGrille; x++) {
				boolean estNavire = false; 
				boolean estTouche = false;
				
				Coordonnee c = new Coordonnee(x+1, y+1);
				for(Navire n: navires){
					if(n.contient(c)){
						estNavire = true;
						break;
					}
				}
				for (int i = 0; i < nbTirsRecus; i++) {
					if (c.equals(tirsRecus[i])){
						estTouche = true;
						break;
					}
				
				}
				if (estNavire && estTouche){
					cell += "X ";
				}
				if (!estNavire && estTouche){
					cell += "O ";
				}
				if (!estNavire && !estTouche){
					cell += ". ";
				}
				if (estNavire && !estTouche){
					cell += "# ";
				}
			}
			cell += "\n";
		}
		 return cell;
	 }
		 /*
  A B C D E F G H I J
1 . . . . . . . . . .
2 . . . # # # . . . .
3 . . . . . . # X # .
4 . . O . . . . O . .
5 . . . . . # . . . .
6 . . . O . # . . . .
7 . # # . . # . . . .
8 . . . . . # . . . .
9 . . . . . . . O . .
10 . . . . . . . . . .

//точка - свободная клетка, в которую не стреляли; # - занятая кораблем, O - свободная клетка получившая выстрел, X - подбитая часть корабля
 * 
	}
	*/
	public boolean ajouteNavire(Navire n) {
		if (nbNavires == navires.length){
			return false;
		}
		for (int i = 0; i < nbNavires; i++) {
			if(navires[i].chevauche(n) || navires[i].estVoisin(n)){
				return false;
			}
		}
		
		if (nbNavires == navires.length){
			return false;
		}
		navires[nbNavires] = n;
		nbNavires += 1;
		return true;
	}
	
	 
	public void placementAuto(int[] taillesNavires) {
		// автоматически и случайно расставляет корабли
		Random r = new Random();
		for(int i: taillesNavires){
			Navire n;
			do {
				if (r.nextBoolean()) { //vertical or 
					// r.nextInt(tailleGrille - i) отступ от края
					// r.nextInt(tailleGrille)+ 1  чтобы генерировал
					n = new Navire(new Coordonnee(r.nextInt(tailleGrille)+ 1, r.nextInt(tailleGrille - i + 1) + 1), i, true);
				} else {
					n = new Navire(new Coordonnee(r.nextInt(tailleGrille - i + 1)+ 1, r.nextInt(tailleGrille ) + 1), i, false);

				}
			} while(!ajouteNavire(n)); 
			
		}
		
	}
	private boolean estDansGrille(Coordonnee c) {
		// true если с в this
		return c.getLigne() >0 && c.getLigne() <= tailleGrille && c.getColonne() >0 && c.getColonne() <= tailleGrille;
	}
	
	private boolean estDansTirsRecus(Coordonnee c) {
		for (int i = 0; i < nbTirsRecus; i++){
			if (c.equals(tirsRecus[i])){
				return true;
			}
		}
		return false;
	}
	
	private boolean ajouteDansTirsRecus(Coordonnee c) {
		//прибавляет с к полученным выстреллам this если необходимо. 
		 // возвращает true если this был изменен 
		if (estDansTirsRecus(c)){
			return false;
		}
		tirsRecus[nbTirsRecus] = c;
		nbTirsRecus += 1;
		return true;
		
	}
	public boolean recoitTir(Coordonnee c) {
		// добавляет с к выстрелам полученным this если необходимо. Возвращает true
		// если c не получала выстрелы и может задеть корабль this
	}
	public boolean estTouche(Coordonnee c) {
		// true если корабль this был подбит в c
	}
	public boolean estALEau(Coordonnee c) { // на воде
		// true если this стрельнул в с и попал в воду
	}
	
	public boolean estCoule(Coordonnee c) { // подбит
		// true если корабль this был задет в c
	}
	public boolean perdu() { // потоплен
		// this.perdu() -> true / false
	}
	
	 public static void main(String[] args) {
		 int[] i = {1, 2, 4, 1, 2, 3};
		GrilleNavale gn = new GrilleNavale(10, i);
		
		System.out.println(gn);
		
	}
}

//среда 8-30 (10-30 по Москве) 
