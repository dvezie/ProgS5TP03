package tableau;

import types.Tableau;

/**
 * spécification de la classe Tableau2x ; T est le type des éléments du tableau
 * 
 * ce tableau double automatiquement de capacité lorsqu'il est plein
 * 
 * @author Louis Boureau et Déborah Vézie
 */
public class Tableau2x<T> implements Tableau<T> {

	private Block<T> tab;
	
	/**
	   * Instancier un nouveau tableau de type Tableau2x
	   * 
	   * @param capacite : capacité initiale du tableau
	   * @pre capacite>0
	   */
	public Tableau2x(int capacite){
		assert(capacite>0):"La capacité du tableau doit être > 0";
		this.tab = new Block<T>(capacite);
	}

	@Override
	public int size(){
		return tab.size();
	}

	@Override
	public boolean empty() {
		return tab.empty();
	}

	@Override
	public boolean full() {		
		return tab.full();
	}

	@Override
	public T get(int i) {
		assert(0<=i && i<this.size()):"L'indice recherché doit être >= 0 ET inférieur au nombre d'éléments";
		return tab.get(i);
	}

	@Override
	public void set(int i, T v) {
		assert(0<=i && i<this.size()):"L'indice recherché doit être >= 0 ET inférieur au nombre d'éléments";
		tab.set(i, v);
	}

	@Override
	public void push_back(T x) {
		assert(!this.full()):"Pour ajouter un élément, le tableau ne doit pas être plein";
		tab.push_back(x);
		
		// Après avoir ajouter un élément au tableau, on regarde si celui-ci est plein.
		// Si il est plein, on double sa capacité en créant un bloc du double de sa taille
		// et on recopie ensuite les éléments de l'ancien block dans le nouveau.
		if(this.full()){
			Block<T> tab_temp = tab;
			tab = new Block<T>(tab_temp.size()*2);
			for(int i=0;i<tab_temp.size();i++){
				tab.push_back(tab_temp.get(i));
			}
		}
	}

	@Override
	public void pop_back() {
		assert(!this.empty()):"Pour retirer un élément, le tableau ne doit pas être vide";
		tab.pop_back();
	}	
}
