package TDAArbol;

import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class Ej1parcial2<E> {
	
	protected TNodo<E> raiz;
	protected int size;

	public Ej1parcial2() {
		raiz=null;
		size=0;
	}

	public boolean isEmpty() {
		return raiz==null;
	}
	
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo=checkPosition(v);
		TNodo<E> padre=nodo.getPadre();
		if(nodo==raiz)
			throw new BoundaryViolationException("NO tiene padre");

		return padre;
	}

	public Iterable<Position<E>> hijos(Position<E> v) throws InvalidPositionException{
		TNodo<E> p=checkPosition(v);
		PositionList<Position<E>> lista= new Lista_doble_enlazada<Position<E>>();
		for(TNodo<E> n:p.getHijos())
			lista.addLast(n);
		return lista; 
	}
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null || isEmpty())
			throw new InvalidPositionException("Posición nula");
		return  (TNodo<E>) p;
}
	
	public void podarSubArbolConsicional(Position<E> p, int s) {

	
			
		try {
			TNodo<E> nodo=checkPosition(p);
			
			
			if(tamañoSubarbol(nodo)==s && cantHijos(p)==2) {

				for(  Position<E> hijos : hijos(parent(p)) ) {  
					
					if(hijos==p) {
						
						hijos=null;
						
					}
						
				}
			}
		}catch(ClassCastException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

	private int tamañoSubarbol(TNodo<E> n) {
		PositionList<E> listaSubarbol = new Lista_doble_enlazada<E>();
		recPreorden(n, listaSubarbol);
		return listaSubarbol.size();
	}
	
	/**
	 * Recorre en preorden el árbol y agrega todos los nodos a una lista
	 * @param r Nodo
	 * @param l Lista
	 */
	private void recPreorden(TNodo<E> r, PositionList<E> l) {
		l.addLast(r.element());
		for(TNodo<E> p: r.getHijos())
			recPreorden(p, l);
	}
	private int cantHijos(Position<E> p) {
		int cant = 0;
		try {
			Iterable<Position<E>> L = hijos(p);
			for(Position<E> h : L)
				cant++;
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cant;
		
	}
	
}
