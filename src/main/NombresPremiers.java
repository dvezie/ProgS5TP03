package main;

import types.Tableau;

import java.util.Random;
import java.util.Scanner;

import tableau.Block;

public class NombresPremiers {
	
	public static void main(String[] args) {
		
		Tableau<Integer> tab = new Block<Integer>(100);
		Scanner input = new Scanner(System.in);
		
		System.out.print("Saisir un entier N >= 2 : ");
		int N = input.nextInt();

		input.close();
		
		int iLastTest = calculerNombresPremiers(N, tab);
		System.out.println("\nEntiers premiers trouvés dans l'intervalle [2 ; " + N + "] :");
		afficher(tab);
		System.out.println("\nDernier entier testé " + iLastTest);
		
		System.out.println("\nTableau remplit aléatoirement dans l'intervalle [0 ; " + iLastTest + "[ :");
		Tableau<Integer> tabRdm = remplirHasard(iLastTest);
		afficher(tabRdm);
		
		System.out.println("\n\nÉlimination des entiers premiers de ce tableau : ");
		System.out.print("Nombre d'éléments éliminés : " + eliminerPresents(tabRdm, tab));
		System.out.println("\nTableau après élimination : ");
		afficher(tabRdm);
	}
	
	/**
	 * Affiche un tableau
	 * @param tab : tableau à afficher
	 */
	static void afficher(Tableau<Integer> tab) {
		for(int i = 0 ; i < tab.size() ; i++) {
			System.out.print(tab.get(i) + " ");
		}
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
	
	/**
	 * Retourne un tableau de type Block de capacité nb, remplit avec des entiers tirés au 
	 * hasard dans l’intervalle [0 ; nb[
	 * @param nb : entier
	 * @return un tableau de type Block
	 */
	public static Tableau<Integer> remplirHasard(int nb) {
		Tableau<Integer> tab = new Block<Integer>(nb);
		Random r = new Random();
		
		while(!tab.full())
			tab.push_back(r.nextInt(nb));
		return tab;
	}
	
	/**
	 * Élimine du tableau t1 tous les éléments présents dans le tableau t2
	 * @param t1 : tableau d'entiers quelconque
	 * @param t2 : tableau d'entiers trié en ordre croissant
	 * @return le nombre d’éléments éliminés
	 */
	public static int eliminerPresents(Tableau<Integer> t1, Tableau<Integer> t2) {
		if(t1.empty() || t2.empty())
			return 0;
		
		int iEltsRmv = 0;
		
		for(int i = t1.size() - 1; i >= 0 ; i--) {
			if(estPresent(t1.get(i), t2, 0, t2.size() - 1)) {
				iEltsRmv++;
				t1.set(i, t1.get(t1.size() - 1));
				t1.pop_back();
			}
		}
		
		return iEltsRmv;
	}
	
	static boolean estPresent(int iTest, Tableau<Integer> tab, int iFirst, int iLast) {
		if(iFirst == iLast) {
			return tab.get(iFirst).equals(iTest);
		}
		else {
			// iMid = valeur de l'élément au milieu du tableau
			int iMid = tab.get((iFirst + iLast) / 2);
			
			/*
			 * return true si iMid == iTest
			 * sinon, si iMid > iTest, et que iMid n'est pas le premier élément de la recherche,
			 * 		on recherche dans les éléments précédents
			 * sinon, si iMid < iTest, et que iMid n'est pas le dernier élément de la recherche,
			 * 		on recherche dans les éléments suivants
			 * return false sinon
			 */
			return iMid == iTest ? true : (
					iTest < iMid && iFirst <= ((iFirst + iLast) / 2) - 1 ? estPresent(iTest, tab, iFirst, ((iFirst + iLast) / 2) - 1) : (
						iTest > iMid && iLast >= ((iFirst + iLast) / 2) + 1 ? estPresent(iTest, tab, ((iFirst + iLast) / 2) + 1, iLast) : false));
		}
	}
}
