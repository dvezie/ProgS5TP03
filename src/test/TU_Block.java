package test;

import org.junit.Assert;
import org.junit.Test;

import tableau.Block;
import types.Tableau;

public class TU_Block extends ATU_Tableau {

  public Tableau<Integer> makeTableau(int capacite) {
    return new Block<Integer>(capacite);
  }
  // push_back : test assertion  !full 
  @Test(expected=AssertionError.class)
  public void testPush_back_assertion() {
    System.out.print("\npush_back        : test assertion  !full : ");
    Tableau<Integer> b2 = makeTableau(10);
    for (int i = 0; i < 100; ++i) {
      b2.push_back(100);
    }
  }

  // push_back : v�rifier que le tableau devient plein
  @Test
  public void testPush_back_full() {
    System.out.print("\npush_back        : v�rifier que le tableau devient plein : ");
    // ajouter des �l�ments dans un tableau initialement vide
    Tableau<Integer> b1 = makeTableau(10);
    for (int taille = 0; taille < 10; ++taille) {
      b1.push_back(taille);
    }
    Assert.assertTrue("Erreur : le tableau devrait �tre plein (taille = " + b1.size(), b1.full());
    System.out.println("test r�ussi");
  }

  // full : v�rifier qu'un tableau non plein le devient quand on le remplit :)
  // et qu'un tableau plein ne l'est plus quand on enl�ve un �l�ment
  @Test
  public void testFull() {
    System.out.print("\nfull             : ajout => full, retrait => !full : ");
    Tableau<Integer> b1 = makeTableau(10);
    Assert.assertFalse("Erreur : le tableau ne devrait pas �tre plein : " + b1.full(), b1.full());
    // ajouter des �l�ments
    for (int i = 1; i <= 9; ++i) {
      b1.push_back(5);
      Assert.assertFalse("Erreur : le tableau ne devrait pas �tre plein : " + b1.full(), b1.full());
    }
    // finir de remplir le tableau
    b1.push_back(5);
    Assert.assertTrue("Erreur : le tableau devrait �tre plein : " + b1.full(), b1.full());
    // �ter un �l�ment
    b1.pop_back();
    Assert.assertFalse("Erreur : le tableau ne devrait pas �tre plein : " + b1.full(), b1.full());
    System.out.println("test r�ussi");
  }

}