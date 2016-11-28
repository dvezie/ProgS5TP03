package types;

/**
 * sp�cification du TA Tableau ; T est le type des �l�ments du tableau
 * 
 * @author Jean-Christophe Engel
 * @version 1.2
 */
public interface Tableau<T> {
	  /**
	   * D�terminer la taille du tableau
	   * @return nombre d'�l�ments pr�sents dans le tableau
	   */
	  public int size();

	  /**
	   * D�terminer si le tableau est vide
	   * @return vrai si le tableau est vide
	   */
	  public boolean empty();

	  /**
	   * D�terminer si le tableau est plein
	   * @return vrai s'il n'est plus possible d'ajouter d'�l�ment dans le tableau
	   */
	  public boolean full();

	  /**
	   * Renvoyer l'�l�ment d'indice i
	   * 
	   * @param i : indice de l'�l�ment � consulter
	   * @pre 0 <= i < this.size()
	   * @return valeur de l'�l�ment d'indice i
	   */
	  public T get(int i);

	  /**
	   * Modifier l'�l�ment d'indice i
	   * 
	   * @param i : indice de l'�l�ment � modifier
	   * @pre 0 <= i < this.size()
	   * @param v : nouvelle valeur de l'�l�ment d'indice i
	   */
	  public void set(int i, T v);

	  /**
	   * Ajouter un �l�ment en fin de tableau
	   * 
	   * @param x : �l�ment � ajouter en fin de tableau
	   * @pre : ! this.full()
	   */
	  public void push_back(T x);

	  /**
	   * Supprimer le dernier �l�ment du tableau
	   * @pre : ! this.empty()
	   */
	  public void pop_back();
	
}
