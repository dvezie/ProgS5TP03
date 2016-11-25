package test;

import org.junit.Assert;
import org.junit.Test;

import types.Tableau;

public abstract class ATU_Tableau
{
  // constructeur : test assertion 0 < capacite
  @Test(expected=AssertionError.class)
  public void testConstructeurEchecAssertionCapacite() {
    System.out.print("\nconstructeur     : v�rifier assertion 0 < capacite : ");
    // capacit� <= 0 => assertion
    makeTableau(0);
  }

  // constructeur : v�rifier l'�tat initial du tableau
  @Test
  public void testEtatInitial()
  {
    System.out.print("\nconstructeur     : v�rifier l'�tat initial du tableau : taille, vide et non plein : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de 0", b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    Assert.assertTrue("Erreur : ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
    System.out.println("test r�ussi");
  }

  // push_back 1 �lt : v�rifier size, empty, full
  @Test
  public void testPush_back_1elt() {
    System.out.print("\npush_back 1 �lt  : v�rifier size, empty, full : ");
    // ajouter 1 �l�ment dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    b1.push_back(999);
    // v�rifier size, empty, full
    Assert.assertTrue("Erreur : la taille devrait �tre " + 1 + " et non " + b1.size(), b1.size() == 1);
    Assert.assertTrue("Erreur : le tableau ne devrait plus �tre vide : " + b1.empty(), ! b1.empty());
    Assert.assertTrue("Erreur : ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
    System.out.println("test r�ussi");
  }

  // push_back n �lts : v�rifier size, empty, full
  @Test
  public void testPush_back_nelts() {
    System.out.print("\npush_back n �lts : v�rifier size, empty, full : ");
    // ajouter des �l�ments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    for (int taille = 0; taille < 99; ++taille) {
      b1.push_back(taille);
      // v�rifier size, empty, full
      Assert.assertTrue("Erreur : la taille devrait �tre " + (taille+1) + " et non " + b1.size(), b1.size() == taille+1);
      Assert.assertTrue("Erreur : le tableau ne devrait plus �tre vide : " + b1.empty(), ! b1.empty());
      Assert.assertTrue("Erreur : ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
    }
    System.out.println("test r�ussi");
  }

  // push_back : v�rifier que l'�l�ment ajout� a la bonne valeur
  @Test
  public void testPush_back_valeur() {
    System.out.print("\npush_back        : v�rifier que l'�l�ment ajout� a la bonne valeur : ");
    // ajouter des �l�ments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(100);
    for (int i = 0; i < 100; ++i) {
      b1.push_back(i);
      // v�rifier la valeur de l'�l�ment ajout�
      Assert.assertTrue("b1[" + i + "]=" + b1.get(i) + " au lieu de " + i, b1.get(i) == i);
    }
    System.out.println("test r�ussi");
  }

  // size : v�rifier que la taille augmente correctement apr�s chaque ajout 
  // et diminue correctement apr�s chaque retrait
  @Test
  public void testSize() {
    System.out.print("\nsize             : v�rifier augmentation et diminution taille : ");
    Tableau<Integer> b1 = makeTableau(10);
    // ajouter 7 �l�ments
    int taille_b1 = 0;
    for (int nb = 0; nb < 7; ++nb) {
      b1.push_back(5);
      ++taille_b1;
      Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de "+ taille_b1, b1.size() == taille_b1);
    }
    // retirer 7 �l�ments
    for (int nb = 0; nb < 7; ++nb) {
      b1.pop_back();
      --taille_b1;
      Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de "+ taille_b1, b1.size() == taille_b1);
    }
    System.out.println("test r�ussi");
  }

  // empty : v�rifier qu'un tableau vide ne l'est plus apr�s ajout d'un �l�ment
  // et qu'un tableau non vide le devient apr�s retrait de tous ses �l�ments
  @Test
  public void testEmpty() {
    System.out.print("\nempty            : vrai avant ajout, faux apr�s, vrai apr�s retraits : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    // ajouter 7 �l�ments
    for (int nb = 0; nb < 7; ++nb) {
      b1.push_back(5);
      Assert.assertTrue("Erreur : le tableau ne devrait pas �tre vide : " + b1.empty(), !b1.empty());
    }
    // retirer 7 �l�ments
    for (int nb = 0; nb < 7; ++nb) {
      b1.pop_back();
    }
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    System.out.println("test r�ussi");
  }

  // get : test assertion  0 <= i 
  @Test(expected=AssertionError.class)
  public void testGetEchecAssertion1() {
    System.out.print("\nget              : test assertion  0 <= i : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.get(-2);
  }

  // get : test assertion  i < size()
  @Test(expected=AssertionError.class)
  public void testGetEchecAssertion2() {
    System.out.print("\nget              : test assertion  i < size() : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.get(8);
  }

  // get : v�rifier qu'un �l�ment ajout� a bien la bonne valeur
  @Test
  public void testGet() {
    System.out.print("\nget              : v�rifier qu'un �l�ment ajout� a bien la bonne valeur : ");
    Tableau<Integer> b2 = makeTableau(120);
    // ajouter un �l�ment et v�rifier sa valeur
    b2.push_back(66);
    Assert.assertTrue("b2[" + (b2.size()-1) + "]=" + b2.get(b2.size()-1) + " au lieu de 66", b2.get(b2.size()-1) == 66);
    System.out.println("test r�ussi");
  }

  // set : test assertion  0 <= i 
  @Test(expected=AssertionError.class)
  public void testSetEchecAssertion1() {
    System.out.print("\nset              : test assertion  0 <= i  : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.set(-2, -2);
  }

  // set : test assertion  i < size() 
  @Test(expected=AssertionError.class)
  public void testSetEchecAssertion2() {
    System.out.print("\nset              : test assertion i < size()  : ");
    Tableau<Integer> b2 = makeTableau(10);
    b2.set(8, -2);
  }

  // set : v�rifier qu'apr�s modification un �l�ment a bien sa nouvelle valeur
  @Test
  public void testSet() {
    System.out.print("\nset              : v�rifier qu'apr�s modification un �l�ment a bien sa nouvelle valeur : ");
    Tableau<Integer> b2 = makeTableau(10);
    // ajouter un �l�ment et v�rifier sa valeur
    b2.push_back(66);
    Assert.assertTrue("b2[" + (b2.size()-1) + "]=" + b2.get(b2.size()-1) + " au lieu de 66", b2.get(b2.size()-1) == 66);
    for (int i = 0; i < b2.size(); ++i) {
      b2.set(i, 3*i+1);
      Assert.assertTrue("b2[" + i + "]=" + b2.get(i) + " au lieu de " + (3*i+1), b2.get(i) == 3*i+1);
    }
    System.out.println("test r�ussi");
  }

  // pop_back : test assertion  !empty 
  @Test(expected=AssertionError.class)
  public void testPop_backEchecAssertion() {
    System.out.print("\npop_back         : test assertion  !empty : ");
    Tableau<Integer> b1 = makeTableau(10);
    b1.pop_back();
  }

  // pop_back : v�rifier que la taille a diminu� d'une unit� 
  @Test
  public void testPop_back() {
    System.out.print("\npop_back         : v�rifier que la taille a diminu� d'une unit� : ");
    // ajouter des �l�ments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(10);
    for (int taille = 0; taille < 10; ++taille) {
      b1.push_back(taille);
      Assert.assertTrue("Erreur : la taille devrait �tre " + (taille+1) + " et non " + b1.size(), b1.size() == taille+1);
    }
    b1.pop_back();
    Assert.assertTrue("Erreur : la taille devrait �tre " + 9 + " et non " + b1.size(), b1.size() == 9);
    System.out.println("test r�ussi");
  }

  // m�thode de cr�ation de tableau qui d�pend de l'impl�mentation
  public abstract Tableau<Integer> makeTableau(int capacite);

}