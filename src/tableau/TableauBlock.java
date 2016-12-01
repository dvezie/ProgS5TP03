package tableau;

import types.Tableau;

/**
 * sp�cification de la classe TableauBlock ; T est le type des �l�ments du tableau
 * 
 * Ce tableau contient des blocks d'une certaine capacit� pouvant stocker des �l�ments de type T. 
 * Lorsqu'un block est plein, on ajoute un nouveau block au tableau.
 * Lorsque tous les blocks du tableau sont pleins et que le tableau lui-m�me est plein, 
 * le tableau double de capacit�, r�cup�re les blocs du tableau pr�c�dent et en ajoute un nouveau.
 * 
 * @author Louis Boureau et D�borah V�zie
 */
public class TableauBlock<T> implements Tableau<T> {

	private int capBlock;
	private Tableau2x<Block<T>> tab;
	
	/**
	   * Instancier un nouveau tableau de type TableauBlock
	   * 
	   * @param capInitTab : capacit� initiale du tableau
	   * @param capBlock : capacit� des blocks
	   * @pre capInitTab>0
	   * @pre capBlock>0
	   */
	public TableauBlock(int capInitTab, int capBlock) {
		assert(capInitTab>0 && capBlock>0):"La capacit� du tableau et celle des blocs doivent �tre > 0";
		this.capBlock = capBlock;	
		this.tab = new Tableau2x<Block<T>>(capInitTab);
		tab.push_back(new Block<T>(capBlock));
	}
	
	/**
	   * Instancier un nouveau tableau de type TableauBlock
	   * avec une capacit� des blocks par d�fault
	   * 
	   * @param capInitTab : capacit� initiale du tableau
	   */
	public TableauBlock(int capInitTab) {
		this(capInitTab, 128);		
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
		
		// Si le dernier block est plein, on ajoute un nouveau block.
		// Le cas ou le tableau est plein est g�r� dans classe Tableau2x.
		if(this.full()) {	
			tab.push_back(new Block<T>(capBlock));
		}
	}

	@Override
	public void pop_back() {
		assert(!this.empty()):"Pour retirer un �l�ment, le tableau ne doit pas �tre vide";
		
		// Si le dernier block est vide (il vient juste d'�tre ajout� mais n'a pas encore �t� rempli),
		// on suprime ce block ET on enl�ve aussi de dernier �l�ment du block pr�c�dent.
		if(tab.get(tab.size()-1).size()==0){
			tab.pop_back();
			tab.get(tab.size()-1).pop_back();
		} else {
			
			// Sinon, on enl�ve juste le dernier �l�ment du dernier block.
			tab.get(this.size()/capBlock).pop_back();
		}
	}
}
