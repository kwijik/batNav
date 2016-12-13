package batailleNavale;

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
		
	}
	
	public GrilleNavale(int taille,  int nbNavires) {
	    // возвращает пустое поле размера taille и он может вместить nbNavires
		this.tailleGrille = taille;
		navires = new Navire[10];
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
				
				Coordonnee c = new Coordonnee(x, y);
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
	public boolean ajouteNavire(Navire n) {
		// true если это возможно, false если уже есть корабль или
	}
	public void placementAuto(int[] taillesNavires) { СПРОСИТЬ!!! Откуда взялся массив, как его сделать
		// автоматически и случайно расставляет корабли
	}
	private boolean estDansGrille(Coordonnee c) {
		// true если с в this
	}
	private boolean estDansTirsRecus(Coordonnee c) {
		
	}
	private boolean ajouteDansTirsRecus(Coordonnee c) {
		//прибавляет с к полученным выстреллам this если необходимо. 
		 // возвращает true если this был изменен 
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
	*/
	 public static void main(String[] args) {
		GrilleNavale gn = new GrilleNavale(10, 4);
		System.out.println(gn);
	}
}

//среда 8-30 (10-30 по Москве) 
