package tableau;

import types.Tableau;

public class Tableau2x<T> implements Tableau<T> {

	private Block<T> tab;
	
	public Tableau2x(int capacite){
		assert(capacite>0):"La capacit� du tableau doit �tre > 0";
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
		assert(0<=i && i<this.size()):"L'indice recherch� doit �tre >= 0 ET inf�rieur au nombre d'�l�ments";
		return tab.get(i);
	}

	@Override
	public void set(int i, T v) {
		assert(0<=i && i<this.size()):"L'indice recherch� doit �tre >= 0 ET inf�rieur au nombre d'�l�ments";
		tab.set(i, v);
	}

	@Override
	public void push_back(T x) {
		assert(!this.full()):"Pour ajouter un �l�ment, le tableau ne doit pas �tre plein";
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
		assert(!this.empty()):"Pour retirer un �l�ment, le tableau ne doit pas �tre vide";
		tab.pop_back();
	}	
}
