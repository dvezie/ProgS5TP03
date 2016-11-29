package tableau;

import types.Tableau;

public class TableauBlock<T> implements Tableau<T> {

	private int capBlock;
	private Tableau2x<Block<T>> tab;
	
	public TableauBlock(int capInitTab, int capBlock) {
		assert(capInitTab>0 && capBlock>0):"La capacit� du tableau et celle des blocs doivent �tre > 0";
		this.capBlock = capBlock;	
		this.tab = new Tableau2x<Block<T>>(capInitTab);
		tab.push_back(new Block<T>(capBlock));
	}
	
	public TableauBlock(int capInitTab) {
		assert(capInitTab>0);
		//new TableauBlock<T>(capInitTab, 128);
		this.capBlock = 128;
		this.tab = new Tableau2x<Block<T>>(capInitTab);
		tab.push_back(new Block<T>(capBlock));		
	}
	
	@Override
	public int size() {
		return (tab.size()-1)*capBlock+tab.get(tab.size()-1).size();
	}

	@Override
	public boolean empty() {
		return this.size()==0;
	}

	@Override
	public boolean full() {
		return this.size()==tab.size()*capBlock;
	}

	@Override
	public T get(int i) {
		assert(0<=i && i<this.size()):"L'indice recherch� doit �tre >= 0 ET inf�rieur au nombre d'�l�ments";
		return tab.get(i/capBlock).get(i%capBlock);
	}

	@Override
	public void set(int i, T v) {
		assert(0<=i && i<this.size()):"L'indice recherch� doit �tre >= 0 ET inf�rieur au nombre d'�l�ments";
		tab.get(i/capBlock).set(i%capBlock, v);
	}

	@Override
	public void push_back(T x) {
		assert(!this.full()):"Pour ajouter un �l�ment, le tableau ne doit pas �tre plein";				
		tab.get((this.size())/capBlock).push_back(x);
		if(this.full()) {	
			tab.push_back(new Block<T>(capBlock));
		}
	}

	@Override
	public void pop_back() {
		assert(!this.empty()):"Pour retirer un �l�ment, le tableau ne doit pas �tre vide";
		if(tab.get(tab.size()-1).size()==0){
			tab.pop_back();
			tab.get(tab.size()-1).pop_back();
		} else {
			tab.get(this.size()/capBlock).pop_back();
		}
	}
}
