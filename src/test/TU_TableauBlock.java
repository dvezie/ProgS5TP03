package test;

import org.junit.Assert;
import org.junit.Test;

import tableau.TableauBlock;
import types.Tableau;

public class TU_TableauBlock extends TU_Tableau2x {

  // � changer selon besoin
  public Tableau<Integer> makeTableau(int capinit, int capabloc) {
    return new TableauBlock<Integer>(capinit);
  }
  public Tableau<Integer> makeTableau(int capinit) {
    return new TableauBlock<Integer>(capinit, 13);
  }

  // constructeur : test taille correcte
  @Test
  public void testConstructeur1param() {
    System.out.print("\nconstructeur capa bloc par d�faut : empty, size, full : ");
    int capinit = 2500;
    TableauBlock<Integer> b1 = (TableauBlock<Integer>) makeTableau(capinit, 128);
    Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de 0", b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    Assert.assertTrue("Erreur : le tableau ne devrait pas �tre plein, taille = " + b1.size(), !b1.full());
    System.out.println("test r�ussi");
  }

  // constructeur : test taille correcte
  @Test
  public void testConstructeur2param() {
    System.out.print("\nconstructeur capa bloc impos� : empty, size, full : ");
    int capinit = 2500;
    TableauBlock<Integer> b1 = (TableauBlock<Integer>) makeTableau(capinit);
    Assert.assertTrue("Erreur : taille incorrecte : " + b1.size() + " au lieu de 0", b1.size() == 0);
    Assert.assertTrue("Erreur : le tableau devrait �tre vide : " + b1.empty(), b1.empty());
    Assert.assertTrue("Erreur : le tableau ne devrait pas �tre plein (taille = " + b1.size(), !b1.full());
    System.out.println("test r�ussi");
  }
}