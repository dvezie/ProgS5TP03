package test;

import tableau.Block;
import types.Tableau;

public class TU_NombresPremiersBlock extends ATU_NombresPremiers {

  // cr�ation de tableau
  public Tableau<Integer> makeTableau(int capinit) {
    return new Block<Integer>(capinit);
  }

  // Fonctions � tester : � changer selon signature

  // D�terminer si n est premier
  boolean estPremier(int n, Tableau<Integer> nombresPremiers) {
    return main.NombresPremiers.estPremier(n, nombresPremiers);
  }
  // Calculer les nombres premiers dans l'intervalle [2,N]
  int calculerNombresPremiers(int N, Tableau<Integer> nombresPremiers) {
    return main.NombresPremiers.calculerNombresPremiers(N, nombresPremiers);
  }
  // remplir un tableau avec nb nombres tir�s au hasard dans [0..nb[
  Tableau<Integer> remplirHasard(int nb) {
    return main.NombresPremiers.remplirHasard(nb);
  }
  // �liminer du tableau t1 les �l�ments du tableau t2 (tri�)
  int eliminerPremiers(Tableau<Integer> t, Tableau<Integer> nombresPremiers) {
    return main.NombresPremiers.eliminerPresents(t, nombresPremiers);
  }
}