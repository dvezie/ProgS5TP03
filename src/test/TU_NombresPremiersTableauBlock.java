package test;

import tableau.TableauBlock;
import types.Tableau;

public class TU_NombresPremiersTableauBlock extends TU_NombresPremiersBlock {

  // cr�ation de tableau
  public Tableau<Integer> makeTableau(int capinit) {
    return new TableauBlock<Integer>(capinit);
  }
}