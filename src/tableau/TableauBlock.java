package tableau;

import types.Tableau;

/**
 * spécification de la classe TableauBlock ; T est le type des éléments du tableau
 * 
 * Ce tableau contient des blocks d'une certaine capacité pouvant stocker des éléments de type T. 
 * Lorsqu'un block est plein, on ajoute un nouveau block au tableau.
 * Lorsque tous les blocks du tableau sont pleins et que le tableau lui-même est plein, 
 * le tableau double de capacité, récupère les blocs du tableau précédent et en ajoute un nouveau.
 * 
 * @author Louis Boureau et Déborah Vézie
 */
public class TableauBlock<T> implements Tableau<T> {

	private int capBlock;
	private Tableau2x<Block<T>> tab;
	
	/**
	   * Instancier un nouveau tableau de type TableauBlock
	   * 
	   * @param capInitTab : capacité initiale du tableau
	   * @param capBlock : capacité des blocks
	   * @pre capInitTab>0
	   * @pre capBlock>0
	   */
	public TableauBlock(int capInitTab, int capBlock) {
		assert(capInitTab>0 && capBlock>0):"La capacité du tableau et celle des blocs doivent être > 0";
		this.capBlock = capBlock;	
		this.tab = new Tableau2x<Block<T>>(capInitTab);
		tab.push_back(new Block<T>(capBlock));
	}
	
	/**
	   * Instancier un nouveau tableau de type TableauBlock
	   * avec une capacité des blocks par défault
	   * 
	   * @param capInitTab : capacité initiale du tableau
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
		assert(0<=i && i<this.size()):"L'indice recherché doit être >= 0 ET inférieur au nombre d'éléments";
		return tab.get(i/capBlock).get(i%capBlock);
	}

	@Override
	public void set(int i, T v) {
		assert(0<=i && i<this.size()):"L'indice recherché doit être >= 0 ET inférieur au nombre d'éléments";
		tab.get(i/capBlock).set(i%capBlock, v);
	}

	@Override
	public void push_back(T x) {
		assert(!this.full()):"Pour ajouter un élément, le tableau ne doit pas être plein";				
		tab.get((this.size())/capBlock).push_back(x);
		
		// Si le dernier block est plein, on ajoute un nouveau block.
		// Le cas ou le tableau est plein est géré dans classe Tableau2x.
		if(this.full()) {	
			tab.push_back(new Block<T>(capBlock));
		}
	}

	@Override
	public void pop_back() {
		assert(!this.empty()):"Pour retirer un élément, le tableau ne doit pas être vide";
		
		// Si le dernier block est vide (il vient juste d'être ajouté mais n'a pas encore été rempli),
		// on suprime ce block ET on enlève aussi de dernier élément du block précédent.
		if(tab.get(tab.size()-1).size()==0){
			tab.pop_back();
			tab.get(tab.size()-1).pop_back();
		} else {
			
			// Sinon, on enlève juste le dernier élément du dernier block.
			tab.get(this.size()/capBlock).pop_back();
		}
	}
}
