package tableau;

import types.Tableau;
import types.Array;

/*
 * La classe Block<T> représente un tableau de capacité fixe mais de taille variable. 
 * Cette classe dispose d’un constructeur prenant en paramètre la capacité du tableau 
 * (la taille initiale du tableau est égale à 0).
 */
public class Block<T> implements Tableau<T> {
	private Array<T> _ary;
	private int _size;
	
	/**
	 * Initialiser un Block
	 * @param iCapacity : capacité du Block
	 * @pre iCapacity > 0
	 */
	public Block(int iCapacity) {
		assert iCapacity > 0 : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : iCapacity doit être > 0";
		this._size = 0;
		this._ary = new Array<T>(iCapacity);
	}

	@Override
	public int size() {
		return this._size;
	}

	@Override
	public boolean empty() {
		return this.size() == 0;
	}

	@Override
	public boolean full() {
		return this.size() == this._ary.length();
	}

	@Override
	public T get(int i) {
		assert 0 <= i && i < this.size() : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : 0 <= i < size()";
		return this._ary.get(i);
	}

	@Override
	public void set(int i, T v) {
		assert 0 <= i && i < this.size() : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : 0 <= i < size()";
		this._ary.set(i, v);
	}

	@Override
	public void push_back(T x) {
		assert ! this.full() : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : ! this.full()";
		this._ary.set(this.size(), x);
		this._size ++;
	}

	@Override
	public void pop_back() {
		assert ! this.empty() : "*** PRÉ-CONDITION NON VÉRIFIÉE *** : ! this.empty()";
		this._ary.set(this.size() - 1, null);
		this._size --;
	}

}
