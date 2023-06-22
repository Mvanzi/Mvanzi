package TDAArbol;

import TDALista.ListaSimplementeEnlazada;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;


public class TNodo<E> implements Position<E> {

	private E elemento;
	private TNodo<E> padre;
	private PositionList<TNodo<E>> hijos;
	
	public TNodo(E elemento, TNodo<E> padre) {
		this.elemento=elemento;
		this.padre=padre;
		hijos=new Lista_doble_enlazada<TNodo<E>>();
	}
	
	public TNodo(E elemento) {
		this(elemento,null);
	}
	
	public TNodo<E> getPadre(){
		return padre;
	}
	
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	public void setPadre(TNodo<E> padre) {
		this.padre=padre;
	}
}

	public class Arbol<E> implements Tree<E> {

		protected TNodo<E> raiz;
		protected int size;

		public Arbol() {
			raiz=null;
			size=0;
		}
	}
	
public class parcialll<E> {

	public void addAfterConditional(Position<TNodo<E>> p, E e, int s) throws InvalidTamañoSubarbol {
		PositionList<TNodo<E>> l= new Lista_doble_enlazada<TNodo<E>>();
		boolean encontre=false;
		try {
			TNodo<E> n = p.element();
			TNodo<E> hermano= new TNodo<E>(e);
		if(raiz!= p) {
		if (tamañoSubarbol(n)<= s) 
			throw new InvalidTamañoSubarbol("El tamaño del sub arbol no es mayor a s");
		else {
			
			 l=n.getPadre().getHijos();
			
			 l.addBefore(p, hermano);
			 hermano.setPadre(n.getPadre());
			 size++;
		}
		}else throw new InvalidOperatioException("no se le puede agragar un hermano a la raiz");
		}catch(InvalidPositionException e1) {
			
			e1.printStackTrace();
			
		}
		
	}
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null || isEmpty())
			throw new InvalidPositionException("Posición nula");
		return  (TNodo<E>) p;
	}
	
	private int tamañoSubarbol(TNodo<E> n) {
		PositionList<E> l = new ListaSimplementeEnlazada<E>();
		recPreorden(n, l);
		return l.size();
	}
	
	
	private void recPreorden(TNodo<E> r, PositionList<E> l) {
		l.addLast(r.element());
		for(TNodo<E> p: r.getHijos())
			recPreorden(p, l);
	}
	
}
