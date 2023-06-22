package TDALista;

public class Nodo<E> implements Position<E> {

	private E elemento;
	private Nodo<E> siguiente;

	//constructores

	public Nodo(E item, Nodo<E> sig) {
		elemento=item;
		siguiente=sig;
	}
	public Nodo(E item) {
		this(item,null);
	}

	//setters

	public void setElemento(E elemento) {
		this.elemento=elemento;
	}
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente=siguiente;
	}

	//getters

	public E getElemento() {
		return elemento;
	}
	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	public E element() {
		return elemento;
	}
	
}
