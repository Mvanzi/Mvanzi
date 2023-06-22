package TDALista;

import java.util.Iterator;

import TDAIterador.ElementIterator;

/**
 * Class Lista_doble_enlazada.
 * Define una lista implementada con dos nodos (uno al frente y otro al final) y los datos y metodos aplicables a una lista heredados por la Interface PositionList.
 * @author Matias Vanzini.
 * @param <E> Tipo de dato a almacenar en  la lista.
 */
public class Lista_doble_enlazada<E> implements PositionList<E> {
	
	/* @Override */
	protected DNodo<E>	cabeza, rabo;
	protected int tamaño;
	
	/**
	 * Crea una lista doble enlazada con todos sus atributos nulos.
	 */
	public Lista_doble_enlazada() {
		cabeza= new DNodo<E>(null);
		rabo= new DNodo<E>(null);
		cabeza.setSiguiente(rabo);
		rabo.setAnterior(cabeza);
		tamaño=0;
	}
	@Override 
	public void addAfter(Position<E> p,E e) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(e);
		nuevo.setSiguiente(pos.getSiguiente());
		nuevo.getSiguiente().setAnterior(nuevo);
		pos.setSiguiente(nuevo);
		nuevo.setAnterior(pos);
		tamaño++;
	}
	/**
	 * Chequea se la posicion pasada como parametro es valida y la castea a un DNodo.
	 * @param p Posicion a chequear.
	 * @return DNodo correspondiente a la posicion p
	 * @throws InvalidPositionException
	 */
	private DNodo<E> checkPosition(Position<E> p)  throws InvalidPositionException{
		if ( isEmpty() )
			throw new InvalidPositionException("Lista vacia");
		if( p == null )
			throw new InvalidPositionException("Posición nula");
		if (p.element() == null)
			throw new InvalidPositionException("p eliminada previamente");
		try {
	
			return (DNodo<E>) p;
		
		} catch (ClassCastException e) {
			throw new InvalidPositionException("No fue posible el casteo.");
		}
	}
	
	@Override 
	public int size() {
		return tamaño;
	}
	@Override 
	public boolean isEmpty() {
		return (tamaño==0);
	}
	@Override 
	public Position<E> first() throws EmptyListException{
		if (this.tamaño==0)
			throw new EmptyListException("Lista vacia");
		return cabeza.getSiguiente();
	}
	@Override 
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> v = checkPosition(p);
		DNodo<E> anterior = v.getAnterior();
		if (v.getAnterior() == cabeza)
			throw new BoundaryViolationException("Primera posicion");
		return anterior;
	}
	@Override 
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> v =checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setAnterior(v.getAnterior());
		nuevo.setSiguiente(v);
		v.getAnterior().setSiguiente(nuevo);
		v.setAnterior(nuevo);
		tamaño++;
	}
	@Override 
	public void addFirst(E element) {
		tamaño++;
		DNodo<E> newNodo = new DNodo<E>(cabeza, cabeza.getSiguiente(), element);
		cabeza.getSiguiente().setAnterior(newNodo);
		cabeza.setSiguiente(newNodo);
	}
	@Override 
	public E remove(Position<E> p) throws InvalidPositionException{
		DNodo<E> v = checkPosition(p);
		DNodo<E> vAnt = v.getAnterior();
		DNodo<E> vSig = v.getSiguiente();
		vAnt.setSiguiente(vSig);
		vSig.setAnterior(vAnt);
		E vElem = v.element();
		v.setSiguiente(null);
		v.setAnterior(null);
		v.setElemento(null);
		tamaño--;
		return vElem;
	}
	@Override 
	public E set(Position<E> p, E element) throws InvalidPositionException{
		DNodo<E> v = checkPosition(p);
		E el = v.element();
		v.setElemento(element);
		return el;
		
	}
	@Override 
	public Position<E> last() throws EmptyListException{
		if (tamaño == 0)
			throw new EmptyListException("last:: Lista vacía");
		return rabo.getAnterior();
	}

	
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> sig = n.getSiguiente();
		if (sig==rabo)
			throw new BoundaryViolationException("Fuera de limites");
		return sig;
	}
	

	@Override
	public void addLast(E element) {
		tamaño++;
		DNodo<E> newNodo = new DNodo<E>(rabo.getAnterior(),rabo, element);
		rabo.getAnterior().setSiguiente(newNodo);
		rabo.setAnterior(newNodo);
		}
	

	@Override
	public Iterator<E> iterator() {
		return (new ElementIterator<E>(this));
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable;
		iterable = new Lista_doble_enlazada<Position<E>>();
		
		DNodo<E> actual = cabeza.getSiguiente();
		
		while(actual != rabo) {
			iterable.addLast(actual);
			actual = actual.getSiguiente();
		}
		return iterable;
		}
	
	public void eliminar(PositionList<E> L) {
		
	}
	
//  public void concatenar(PositionList<E> L){
//  DNodo<E> nuevo;
//  try {
//      if ( !L.isEmpty() ) {
//          Position<E> n = L.first();
//          while( n != L.last() ) {
//              nuevo = new DNodo<E>(n.element(), rabo.getPrevio(), rabo);
//              (rabo.getPrevio()).setSiguiente(nuevo);
//              rabo.setPrevio(nuevo);
//              n = L.next(n);
//              cant++;
//          }
//          nuevo = new DNodo<E>(n.element(), rabo.getPrevio(), rabo);
//          (rabo.getPrevio()).setSiguiente(nuevo);
//          rabo.setPrevio(nuevo);
//      }
//  } catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
//      System.out.println(e.getMessage());
//  }
//}
	
	/*public E remove(Position<E> p) throws InvalidPositionException{
		DNodo<E> v = checkPosition(p);
		DNodo<E> vAnt = v.getAnterior();
		DNodo<E> vSig = v.getSiguiente();
		vAnt.setSiguiente(vSig);
		vSig.setAnterior(vAnt);
		E vElem = v.element();
		v.setSiguiente(null);
		v.setAnterior(null);
		v.setElemento(null);
		tamaño--;
		return vElem;
	}
	*/
	public void removeConditional(Position<E> p, E e) {
		
		try {
			DNodo<E> v = checkPosition(p);
			if(v.getSiguiente().element().equals(e)) {
				remove(p);
				tamaño--;
			}
		}catch(InvalidPositionException e1) {
			
			e1.printStackTrace();
			
		}
		
	}
	
}

