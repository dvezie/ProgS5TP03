package test;

import org.junit.Assert;
import org.junit.Test;

import tableau.Tableau2x;
import types.Tableau;

public class TU_Tableau2x extends ATU_Tableau {

  public Tableau<Integer> makeTableau(int capinit) {
    return new Tableau2x<Integer>(capinit);
  }

  // push_back sans agrandissement : empty, size, full
  @Test
  public void testPush_back_not_full() {
    System.out.print("\npush_back        : empty, size, full sans agrandissement : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // remplir la capacit� initiale sans agrandir le tableau
    for (int i = 0; i < capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre vide  : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : la taille devrait �tre " + (i+1) + " et non " + b1.size(), b1.size() == i+1);
    }
    Assert.assertTrue("Erreur : la taille devrait �tre " + capinit + " et non " + b1.size(), b1.size() == capinit);
    System.out.println("test r�ussi");
  }

  // push_back avec agrandissement : empty, size, full
  @Test
  public void testPush_back_agrandissement_taille() {
    System.out.print("\npush_back        : empty, size, full avec agrandissement : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // v�rifier l'agrandissement de la capacit�
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre vide : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : la taille devrait �tre " + (i+1) + " et non " + b1.size(), b1.size() == i+1);
    }
    Assert.assertTrue("Erreur : la taille devrait �tre " + (100*capinit) + " et non " + b1.size(), b1.size() == (100*capinit));
    System.out.println("test r�ussi");
  }

  // push_back avec agrandissement : valeur
  @Test
  public void testPush_back_agrandissement_valeur() {
    System.out.print("\npush_back        : valeur avec agrandissement : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    System.out.println("test r�ussi");
  }

  // pop_back : v�rifier qu'apr�s retrait la taille diminue 
  @Test
  public void testPop_back_apres_agrandissement() {
    System.out.print("\npop_back         : taille apr�s agrandissement : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    // ajouter des �l�ments dans le tableau de fa�on � l'agrandir
    for (int i = 0; i < 100*capinit; ++i) {
      b1.push_back(i);
    }
    // retirer les �l�ments
    for (int i = 0; i < 100*capinit; ++i) {
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre vide : " + b1.empty(), ! b1.empty());
      b1.pop_back();
      Assert.assertTrue("Erreur : la taille devrait �tre " + (100*capinit-i-1) + " et non " + b1.size(), b1.size() == 100*capinit-i-1);
    }
    Assert.assertTrue("Erreur : la taille devrait �tre " + 0 + " et non " + b1.size(), b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    System.out.println("test r�ussi");
  }


  // get : v�rifier que les �l�ments ajout�s dans un tableau ont bien la valeur pr�vue
  @Test
  public void testGet_complet() {
    System.out.print("\nget              : valeur apr�s agrandissemenet et pop : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    int nb = 1000;
    for (int i = 0; i < nb; ++i) {
      b1.push_back(i);
    }
    // retirer des �l�ments et v�rifier la valeur des �l�ments restants
    for (int i = 0; i < nb/2; ++i) {
      b1.pop_back();
      for (int j = 0; j < b1.size(); ++j) {
	Assert.assertTrue("b1[" + j + "]=" + b1.get(j) + " au lieu de " + j, b1.get(j) == j);
      }
    }
    System.out.println("test r�ussi");
  }

  // set : v�rifier qu'apr�s modification un �l�ment a bien sa nouvelle valeur
  @Test
  public void testSet_apres_agrandissement() {
    System.out.print("\nset              : valeur apr�s agrandissement : ");
    int capinit = 120;
    Tableau<Integer> b1 = makeTableau(capinit);
    int nb = 100000;
    for (int i = 0; i < nb; ++i) {
      b1.push_back(i);
    }
    // v�rifier la valeur des �l�ments
    for (int i = 0; i < nb; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    // modifier la valeur des �l�ments
    for (int i = 0; i < nb; ++i) {
      b1.set(i, b1.get(i)*b1.get(i));
    }
    // v�rifier la valeur des �l�ments
    for (int i = 0; i < nb; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + (i*i), b1.get(i) == i*i);
    }
    // retirer des �l�ments
    for (int i = 0; i < nb/2; ++i) {
      b1.pop_back();
    }
    // v�rifier la valeur des �l�ments
    for (int i = 0; i < nb/2; ++i) {
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + (i*i), b1.get(i) == i*i);
    }
    System.out.println("test r�ussi");
  }
}