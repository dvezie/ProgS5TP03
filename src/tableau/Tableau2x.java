package tableau;

import types.Array;
import types.Tableau;

public class Tableau2x<T> implements Tableau<T> {

	private int taille;
	private Array<T> tab;
	
	public Tableau2x(int capacite){
		assert(capacite>0);
		this.taille=0;
		this.tab = new Array<T>(capacite);		
	}
	
	@Override
	public int size(){
		return taille;
	}

	@Override
	public boolean empty() {
		return taille==0;
	}

	@Override
	public boolean full() {		
		return taille == tab.length();
	}

	@Override
	public T get(int i) {
		assert(0<=i && i<this.size());
		return tab.get(i);
	}

	@Override
	public void set(int i, T v) {
		assert(0<=i && i<this.size());
		tab.set(i, v);
	}

	@Override
	public void push_back(T x) {
		assert(!this.full());
		tab.set(taille, x);
		taille++;
		if(this.full()){
			Array<T> tab_temp = tab;
			tab = new Array<T>(tab_temp.length()*2);
			for(int i=0;i<tab_temp.length();i++){
				tab.set(i, tab_temp.get(i));
			}			
		}		
	}

	@Override
	public void pop_back() {
		assert(!this.empty());
		taille--;	
	}	
}
