package tableau;

import types.Tableau;

public class Tableau2x<T> implements Tableau<T> {

	private Block<T> tab;
	
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
