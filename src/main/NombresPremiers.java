package main;

import types.Tableau;

import java.util.Scanner;

import tableau.Block;

public class NombresPremiers {
	
	public static void main(String[] args) {
		/*
		 * Écrivez un programme principal qui crée un tableau de type Block d’une capacité 
		 * de 100 éléments.
		 * Demandez à l’utilisateur d’entrer un entier N et calculez les entiers premiers 
		 * dans l’intervalle [2 ; N]. Le programme affichera (à l’aide d’une fonction) 
		 * les entiers premiers trouvés ainsi que le dernier entier testé.
		 */
		
		Tableau<Integer> tab = new Block<Integer>(100);
		Scanner input = new Scanner(System.in);
		
		System.out.print("Saisir un entier N >= 2 : ");
		int N = input.nextInt();
		
		int iLastTest = calculerNombresPremiers(N, tab);
		afficher(N, iLastTest, tab);
		
		input.close();
	}
	
	
	static void afficher(int N, int iLastTest, Tableau<Integer> tab) {
		System.out.println("\nEntiers premiers trouvés dans l'intervalle [2 ; " + N + "] :");
		for(int i = 0 ; i < tab.size() ; i++) {
			System.out.print(tab.get(i) + " ");
		}
		System.out.println("\nDernier entier testé " + iLastTest);
	}
	
	/**
	 * Détermine si un entier n ≥ 2 est premier. 
	 * @param n : entier à tester
	 * @param ary : tableau contenant tous les entiers premiers compris
	 * dans l’intervalle [2 ; n-1]
	 * @pre n >= 2
	 * @return true si l’entier testé est premier, false sinon
	 */
	public static boolean estPremier(int n, Tableau<Integer> tab) {
		assert n >= 2 : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : n >= 2";
		return estPremier(n, tab, 0, tab.size() - 1);
	}
	/**
	 * Détermine si un entier n ≥ 2 est premier. 
	 * @param n : entier à tester
	 * @param ary : tableau contenant tous les entiers premiers compris
	 * dans l’intervalle [2 ; n-1]
	 * @param iFirst : indice de début de la partie du tableau à tester
	 * @param iLast : indice de fin de la partie du tableau à tester
	 * @return
	 */
	private static boolean estPremier(int n, Tableau<Integer> tab, int iFirst, int iLast) {
		if(tab.empty()) {
			return true;
		}
		if(iFirst == iLast) {
			return n % tab.get(iFirst) != 0;
		}
		else {
			return estPremier(n, tab, iFirst, (iLast + iFirst) / 2) &&
					estPremier(n, tab, (iLast + iFirst) / 2 + 1, iLast);
		}
	}
	
	/**
	 * Calcule l’ensemble des entiers premiers dans l’intervalle [2 ; N] à l’aide de la 
	 * fonction estPremier.
	 * @param N : entier
	 * @param tab : tableau, initialement vide, qui servira à stocker l’ensemble des entiers 
	 * premiers trouvés dans l'intervalle
	 * @pre tab.empty()
	 * @pre N >= 2
	 * @return le dernier entier testé si le tableau est plein avant la fin du calcul, 
	 * l’entier N sinon
	 */
	public static int calculerNombresPremiers(int N, Tableau<Integer> tab) {
		assert tab.empty() : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : tab.empty()";
		assert N >= 2 : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : N >= 2";
		
		tab.push_back(2);
		if(tab.full())
			return 2;
		for(int i = 3; i <= N; i++) {
			if(estPremier(i, tab))
				tab.push_back(i);
			if(tab.full())
				return i;
		}
		return N;
	}
	
	/*
	 * Écrivez une fonction remplirHasard qui prend en paramètre un nombre entier nb, 
	 * crée un tableau de type Block de capacité nb et le remplit avec des entiers tirés au 
	 * hasard dans l’intervalle [0 ; nb[ (voir la classe Random dans l’API java). 
	 * La fonction renverra le tableau ainsi créé et initialisé.
	 * Complétez le programme principal précédent pour créer puis afficher un tableau de 
	 * nombres entiers aléatoires dans l’intervalle [0 ; dernier[ où dernier est le nombre 
	 * renvoyé par la fonction calculerNombresPremiers.
	 */
}
