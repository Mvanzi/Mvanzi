package TDALista;


/**
 * Class DNodo
 * Define un Nodo y los datos y operciones aplicables sobre el mismo.
 * @author Francisco Arevalo
 * @param <E> tipo de dato
 */
public class DNodo<E> implements Position<E> {
	
	/** @Override **/
	private DNodo<E> anterior, siguiente;
	private E elemento;
	/**
	 * Crea un DNodo iniciando los atributos con los valores pasados  como parametro.
	 * @param p DNodo anterior al nuevo.
	 * @param n DNodo siguiente al nuevo.
	 * @param e elemento del nuevo DNodo.
	 */
	public DNodo(DNodo<E> p, DNodo<E> n,E e) {
		anterior=p;
		siguiente=n;
		elemento=e;
	}
	/**
	 * crea un nuevo DNodo con anterior y siguiente nulos y el elemento pasado como parametro.
	 * @param elem elemento del nuevo DNodo.
	 */
	public DNodo( E elem) {
		anterior= null;
		siguiente= null;
		elemento=elem;
	}
	@Override 
	public E element(){
		return elemento;
	}
	/**
	 * retorna el DNodo siguiente al que recibe el mensaje.
	 * @return DNodo siguiente.
	 */
	public DNodo<E> getSiguiente(){
		return siguiente;
		
	}
	/**
	 * retorna el DNodo anterior al que recibe el mensaje.
	 * @return DNodo anterior.
	 */
	public DNodo<E> getAnterior(){
		return anterior;
		
	}
	/**
	 * Establece el DNodo siguiente.
	 * @param n DNodo siguiente
	 */
	public void setSiguiente(DNodo<E> n) {
		siguiente =n;
		
	}
	/**
	 * Establece el DNodo  anterior.
	 * @param p DNodo anterior.
	 */
	public void setAnterior(DNodo<E> p) {
		anterior=p;
		
	}
	/**
	 * Establece el elemento del DNodo.
	 * @param e elemento a establecer.
	 */
	public void setElemento(E e) {
		elemento = e;
	}
}

