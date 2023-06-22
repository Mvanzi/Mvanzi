package TDALista;

import java.util.Iterator;

import TDAIterador.ElementIterator;
import TDAPila.EmptyStackException;
import TDAPila.PilaArreglo;
import TDAPila.Stack;

public class ListaSimplementeEnlazada<E> implements PositionList<E>{

	protected Nodo<E> cabeza;
	protected int tam;

	public ListaSimplementeEnlazada() {
		cabeza=null;
		tam=0;
	}

	public int size() {
		return tam;
	}


	public boolean isEmpty() {
		return tam==0;
	}


	public Position<E> first() throws EmptyListException {
		if(cabeza==null)
			throw new EmptyListException("first()::Lista vacia");
		return cabeza;
	}


	@SuppressWarnings("unchecked")
	public Position<E> last() throws EmptyListException {
		if(cabeza==null)
			throw new EmptyListException("last()::Lista vacia");
		else {
			Nodo<E>p = cabeza;
			while(p.getSiguiente()!=null)
				p=p.getSiguiente();
			return p;

		}
	}


	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n=checkPosition(p);
		if(n.getSiguiente()==null)
			throw new BoundaryViolationException("Next:: Siguiente de ultimo");

		return n.getSiguiente();
	}

	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p==null)
				throw new InvalidPositionException("posicioon nula");
			if(p.element()==null)
				throw new InvalidPositionException("p eliminada previamente");
			return (Nodo<E>)p;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("p no es un nodo de la lista");
		}
	}

	@SuppressWarnings("unchecked")
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		checkPosition(p);

		if(p == cabeza) throw new BoundaryViolationException("posicion primera");

		Nodo<E> aux=cabeza;
		while(aux.getSiguiente() !=p && aux.getSiguiente()!=null)
			aux=aux.getSiguiente();
		if(aux.getSiguiente()==null)
			throw new InvalidPositionException("posicion no pertenece a la lista");

		return aux;
	}


	public void addFirst(E element) {
		cabeza= new Nodo<E>(element, cabeza);
		tam++;
	}


	public void addLast(E element) {
		if(isEmpty())
			addFirst(element);
		else {
			Nodo<E>p=cabeza;
			while(p.getSiguiente()!=null)
				p=p.getSiguiente();
			p.setSiguiente(new Nodo<E>(element));
			tam++;
		}
	}


	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E>n= checkPosition(p);
		Nodo<E>nuevo=new Nodo<E>(element);
		nuevo.setSiguiente(n.getSiguiente());
		n.setSiguiente(nuevo);
		tam++;
	}


	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		checkPosition(p);
		try {
			if(p==first()) addFirst(element);
			else
				addAfter(prev(p),element);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> n=checkPosition(p);
		try {
			if(p==first())
				cabeza=n.getSiguiente();
			else
				checkPosition(prev(p)).setSiguiente(n.getSiguiente());
		} catch (EmptyListException e) {

			e.printStackTrace();
		} catch (InvalidPositionException e) {

			e.printStackTrace();
		} catch (BoundaryViolationException e) {

			e.printStackTrace();
		}
		tam--;
		E aux=p.element();
		n.setElemento(null);
		n.setSiguiente(null);
		return aux;
	}


	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n=checkPosition(p);
		E aux =p.element();
		n.setElemento(element);
		return aux;
	}


	public Iterator<E> iterator() {
		return (new ElementIterator<E>(this));
	}


	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> P = new ListaSimplementeEnlazada<Position<E>>();
		if (this.cabeza!=null) {
			first_to_last(P, this.cabeza);
		}

		return P;
	}

	private void first_to_last(PositionList<Position<E>> list_to_iterate, Nodo<E> pointer) {
		list_to_iterate.addLast(pointer);

		if (pointer.getSiguiente() != null) {
			first_to_last(list_to_iterate, pointer.getSiguiente());
		}
	}

	//Parcial

	public void addFirstConditional(E e, E x) {
		if(cabeza.element()==x)
			addFirst(e);
	}

	public PositionList<E> concatenar(PositionList<E> l1, PositionList<E> l2){
		Stack<E> pila= new PilaArreglo<E>();
		PositionList<E> l3= new ListaSimplementeEnlazada<E>();


		try {
			int total = l1.size() + l2.size();
			Position<E> p1=l1.first();
			Position<E> p2=l2.first();

			while(p2!=l2.last()) { //paso l2 a una pila asi al momento de desapilar me queda al reves

				pila.push(p2.element());
				p2=l2.next(p2);

			}

			if(p2==l2.last()) // meto el ultimo elemento de l2 en la pila, que habia quedado fuera
				pila.push(p2.element());

			while(p1!=l1.last() && !pila.isEmpty()) { //concateno l1 y la pila en l3

				l3.addLast(p1.element());
				p1=l1.next(p1);

				l3.addLast(pila.pop());

			}

			if(p1==l1.last() && !pila.isEmpty()) {// si se vacio  l1, inserto el ultimo elemento de l1 en l3 y continuo insertando la pila en l3.
				l3.addLast(p1.element());
				p1=l1.first();
				while(!pila.isEmpty()) {
					l3.addLast(pila.pop());
				}
			}

			if(pila.isEmpty() && p1!=l1.last()) {// si se vacio la pila, continuo insertando l1 en l3.
				while(p1!=l1.last()) {

					l3.addLast(p1.element());
					p1=l1.next(p1);

				}
			}

			if(p1==l1.last() && pila.isEmpty() && total!=l3.size()) // si estamos en el final de l1 y se vacio la pila, inserto el ultimo elem de l1 en l3
				l3.addLast(p1.element());

		}catch(InvalidPositionException | EmptyListException | BoundaryViolationException | EmptyStackException e1) {

			e1.printStackTrace();

		}
		return l3;
	}

	public String toString() {
		Iterator<E> it = iterator(); // Le pido el iterador a la lista this.
		String s = "[";
		while( it.hasNext() ) {
			s += it.next(); // Hay un cast implícito de E a String, equivale a: s+=it.next().toString();
			if( it.hasNext() ) // Hago append de una coma si quedan elementos
				s +=  ", ";
		}
		s += "]";
		return s;
	}

	public PositionList<Integer> intercalar(PositionList<Integer> l1, PositionList<Integer> l2){
		
		PositionList<Integer> ret = new ListaSimplementeEnlazada<Integer>();

		try {

			Position<Integer> p1= l1.first();
			Position<Integer> p2=l2.first();

			while(p1.element()!=l1.last().element() || p2.element()!=l2.last().element()) {

				if(p1.element()<=p2.element())
					ret.addLast(p1.element());
				else
					ret.addLast(p2.element());

				p1=l1.next(p1);
				p2=l2.next(p2);


			}
			
			if(p2.element()==l2.last().element() && p1.element()==l1.last().element())
				if(p1.element()<=p2.element())
					ret.addLast(p1.element());
				else
					ret.addLast(p2.element());

			if(p2.element()==l2.last().element() && p1.element()!=l1.last().element()) {

				while(p1.element()!=l1.last().element()) {

					if(p1.element()<=p2.element())
						ret.addLast(p1.element());
					else
						ret.addLast(p2.element());

					p1=l1.next(p1);
				}

			}

			if(p1.element()==l1.last().element() && p2.element()!=l2.last().element()) {

				while(p2.element()!=l2.last().element()) {

					if(p1.element()<=p2.element())
						ret.addLast(p1.element());
					else
						ret.addLast(p2.element());

					p2=l2.next(p2);
				}

			}


		}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e1) {

			e1.printStackTrace();

		}
		return ret;

	}

}
