package TDAArbol;

import java.util.Iterator;

import TDAArbolBinario.PriorityQueue;
import TDAArbolBinario.TNodo;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDALista.EmptyListException;
import TDALista.ListaSimplementeEnlazada;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;
import TDAPila.EmptyStackException;
import TDAPila.Stack;
import TDAPila.pilaConEnlaces;

public class Arbol<E> implements Tree<E> {

	protected TNodo<E> raiz;
	protected int size;

	public Arbol() {
		raiz=null;
		size=0;
	}

	public boolean isEmpty() {
		return raiz==null;
	}

	public Position<E> root() throws EmptyTreeException {
		if (raiz == null)
			throw new EmptyTreeException("Arbol vacio");
		return  raiz;
	}

	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null || isEmpty())
			throw new InvalidPositionException("Posición nula");
		return  (TNodo<E>) p;
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo=checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	public Position<E> addFirst (Position<E> p, E e) throws InvalidPositionException{
		TNodo<E> nodo=checkPosition(p);
		TNodo<E> nuevo= new TNodo<E>(e,nodo);
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition( p );
		TNodo<E> hd = checkPosition( rb );

		if(hd.getPadre() != n)
			throw new InvalidPositionException("p no es padre de rb");

		TNodo<E> nuevo = new TNodo<E>( e, n );
		PositionList<TNodo<E>> hijos = n.getHijos();
		boolean encontre = false;
		try {
			TDALista.Position<TNodo<E>> pp = hijos.first();
			while( pp != null && !encontre ) {
				if( hd == pp.element() )
					encontre = true; 
				else pp = (pp != hijos.last() ? hijos.next(pp) : null);
			}

			if( !encontre ) 
				throw new InvalidPositionException( "p no es padre de rb" );
			hijos.addBefore( pp, nuevo ); 
		}
		catch (TDALista.InvalidPositionException | EmptyListException | TDALista.BoundaryViolationException e1) {}
		size++; 
		return nuevo; 
	}

	public Position<E> addFirstChild(Position<E> p,E e)throws InvalidPositionException{
		TNodo<E> nodo=checkPosition(p);
		TNodo<E> nuevo=new TNodo<E> (e,nodo);
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if(!n.getHijos().isEmpty())
			throw new InvalidPositionException("No es un nodo externo");
		removeNode(n);

	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNodo<E> p=checkPosition(v);
		PositionList<Position<E>> lista= new Lista_doble_enlazada<Position<E>>();
		for(TNodo<E> n:p.getHijos())
			lista.addLast(n);
		return lista; 
	}

	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> l=new Lista_doble_enlazada<Position<E>>();
		if(!isEmpty())
			pre(raiz,l);
		return l;
	}

	private void pre(TNodo<E> v, PositionList<Position<E>> l) {
		l.addLast(v);
		for(TNodo<E> h:v.getHijos())
			pre(h,l);
	}

	public Iterator<E> iterator(){
		PositionList<E> l=new Lista_doble_enlazada<E>();
		for(Position<E> p: positions())
			l.addLast(p.element());
		return l.iterator();
	}


	public int size() {
		return size;
	}


	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		E rem = (E) pos.element();
		pos.setElemento(e);
		return rem;
	}


	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo=checkPosition(v);
		TNodo<E> padre=nodo.getPadre();
		if(nodo==raiz)
			throw new BoundaryViolationException("NO tiene padre");

		return padre;
	}


	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo=checkPosition(v);
		boolean tiene=false;
		if(nodo.getPadre()!=null && nodo.getHijos()!=null)
			tiene=true;
		return tiene;
	}


	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return pos.getPadre() == null;
	}

	public void createRoot(E e) throws InvalidOperationException {
		if (raiz != null)
			throw new InvalidOperationException("El arbol ya tiene raiz");
		TNodo<E> nodo = new TNodo<E>(e,null);
		raiz = nodo;
		size++;
	}


	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>( e, nodo );
		nodo.getHijos().addLast( nuevo );
		size++;
		return nuevo;
	}


	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition( p );
		TNodo<E> hi = checkPosition( lb );

		if(hi.getPadre() != n)
			throw new InvalidPositionException("p no es padre de lb");

		TNodo<E> nuevo = new TNodo<E>( e, n );
		PositionList<TNodo<E>> hijos = n.getHijos();
		boolean encontre = false;
		try {
			TDALista.Position<TNodo<E>> pp = hijos.first();
			while( pp != null && !encontre ) {
				if( hi == pp.element() ) 
					encontre = true; 
				else pp = (pp != hijos.last() ? hijos.next(pp) : null);
			}

			if( !encontre ) 
				throw new InvalidPositionException( "p no es padre de rb" );
			hijos.addAfter( pp, nuevo ); 
		}
		catch (TDALista.InvalidPositionException | EmptyListException | TDALista.BoundaryViolationException e1) {}
		size++; 
		return nuevo;

	}


	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if(n.getHijos().isEmpty())
			throw new InvalidPositionException("No es un nodo interno");
		removeNode(n);	
	}


	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nEliminar = checkPosition(p);
		TNodo<E> padre = nEliminar.getPadre();
		PositionList<TNodo<E>> hijos = nEliminar.getHijos();
		try{
			if (nEliminar == raiz){
				if (hijos.size() == 0){
					raiz = null;
				}else{
					if (hijos.size() == 1){
						TNodo<E> hijo = hijos.remove(hijos.first());
						hijo.setPadre(null);
						raiz = hijo;
					}else
						throw new InvalidPositionException("No se puede eliminar raíz con hijos > 1");
				}
			}
			else{
				PositionList<TNodo<E>> hermanos = padre.getHijos();

				TDALista.Position<TNodo<E>> posListaHermanos = hermanos.isEmpty() ? null : hermanos.first();
				while(posListaHermanos != null && posListaHermanos.element() != nEliminar){
					posListaHermanos = (hermanos.last() == posListaHermanos) ? null : hermanos.next(posListaHermanos);
				}

				if (posListaHermanos == null)
					throw new InvalidPositionException("La posición p no se encuentra en la lista del padre");

				while(!hijos.isEmpty()){
					TNodo<E> hijo = hijos.remove(hijos.first());
					hijo.setPadre(padre);
					hermanos.addBefore(posListaHermanos, hijo);
				}
				hermanos.remove(posListaHermanos);
			}
			nEliminar.setPadre(null);
			nEliminar.setElemento(null);
			size--;
		}catch(EmptyListException | TDALista.BoundaryViolationException | TDALista.InvalidPositionException e){}
	}	

	public void podarSubArbolConsicional(Position<E> p, int s) {

		if(p.element()) {


		}

	}
	
	private int tamañoSubarbol(Position<E> p, int t) {
		t++;
		try {
			if( p == raiz )
				t = size;
			else {
				for (Position<E> hijo : children(p)) {
					t = tamañoSubarbol(hijo,t);
				}
			}
		} catch (InvalidPositionException e) {
			
			e.printStackTrace();
		}
		return t;
	}

	public Queue<TNodo<E>> rotuloIgual(TNodo<E> n, int h){
		Queue<TNodo<E>> cola= new ColaConArregloCircular<TNodo<E>>(30);
		TNodo<E> nodo=n;
		int altura=h;
		if(raiz!=null) {

			rotuloIgualRec(raiz,cola,nodo,altura);

		}

		return cola;
	}

	private void rotuloIgualRec(TNodo<E> n, Queue<TNodo<E>> cola, TNodo<E> nodo, int altura) {
		
		for(TNodo<E> hijo: n.getHijos()) {
			
			if(n==nodo && altura(n)==altura) {
				
				cambiarHijos(hijo,cola);
				
			}
			
			rotuloIgualRec(hijo,cola,nodo,altura);
			
		}
		
	}
	
	public int altura( Position<E> p) {
		int ret=0;
		int aux;
		try {
		if(isExternal(p)) {
			ret = 0;
		}
		else {
			for(Position<E> hijo : children(p)) {
				aux = altura(hijo);
				if(ret<aux)
					ret = aux;
			}
			ret = 1+ret; 
		}
		}catch(InvalidPositionException e) {}
		return ret;
	}
	
	private void cambiarHijos(TNodo<E> hijo, Queue<TNodo<E>> cola) {
		TNodo<E> aux;
		for(TNodo<E> hijos: hijo.getHijos()) {
			
			if(hijos.getHijos()==null) {
				aux=hijo;
				hijo=hijos;
				hijos=aux;
				cola.enqueue(hijo);
				cola.enqueue(hijos);
			}
			else {
				cambiarHijosAux(hijo, hijos, cola);
			}
		}
		
	}
	
	private void cambiarHijosAux(TNodo<E> hijo, TNodo<E> hijos, Queue<TNodo<E>> cola) {
		
		TNodo<E> aux;
		for(TNodo<E> hijosAux: hijos.getHijos()) {
			
			if(hijosAux.getHijos()==null) {
				aux=hijo;
				hijo=hijosAux;
				hijosAux=aux;
				cola.enqueue(hijo);
				cola.enqueue(hijosAux);
			}
			else {
				cambiarHijosAux(hijo, hijosAux, cola);
			}
		}
		
	}
	
	public PositionList<Position<Integer>> lista(Tree<Integer> arbol){
		PositionList<Position<Integer>> l= new ListaSimplementeEnlazada<Position<Integer>>();
		
		try {
		if(!arbol.isEmpty()) {
			
			
			listarec(arbol.root(), arbol, l);
			
		}
		}catch(EmptyTreeException e) {
			
			e.printStackTrace();
			
		}
		return l;
	}
	
	private void listarec(Position<Integer> n, Tree<Integer> a, PositionList<Position<Integer>> l) {
		try {
		TNodo<E> nodo= checkPosition((Position<E>) n);
		for(Position<E> hijos: nodo.getHijos()) {
			
			if(cantHijo(hijos)==1)
				l.addLast((Position<Integer>) hijos);
			
			
			listarec((Position<Integer>) hijos,a,l);
		}
		
		}catch(InvalidPositionException e) {
			
			e.printStackTrace();
			
		}
	}
	
	private int cantHijo(Position<E> p) throws InvalidPositionException {
        TNodo <E> nodo = checkPosition(p);
        PositionList<TNodo<E>> l = nodo.getHijos();

        return l.size();
    }
	
	/*Implemente en Java una operación para la clase Árbol definida en el ejercicio 4 que, recibiendo
	un rótulo R, modifique el Árbol A receptor del mensaje de forma tal que, por cada nodo de A
	que sea interno y tenga rótulo R, se invierta el orden de sus hijos. Esta operación no debe
	generar un nuevo árbol sino, modificar el árbol que recibe el mensaje. Recuerde que está
	agregando un método a la clase Árbol, por lo tanto tiene total acceso a su estructura.
	*/
	
	public void modificar(E rotulo) {
		Stack<Position<E>> pila= new pilaConEnlaces<Position<E>>(); 
		for(Position<E> e: positions()) {
			try {
				if(e.element().equals(rotulo) && isInternal(e)) {
					for(Position<E> h: children(e)) {
						pila.push(h);
					}
					
					for(Position<E> h: children(e)) {
						replace(h,pila.pop().element());
					}
				}
			
			} catch (InvalidPositionException e1) {e1.printStackTrace();
			} catch (EmptyStackException e1) {
				
				e1.printStackTrace();
			}
		}
	}
	
	
	
}
