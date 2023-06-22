package TDAMapeo;


public class Entrada<K,V> implements Entry<K,V> {
	
	private K clave;
	private V valor;
	
	// Constructor
	public Entrada(K k, V v) { 
		clave = k; 
		valor = v; }

	public K getKey() { 
		return clave;
	} 
	public V getValue() {
		return valor;
	}
	public void setKey( K k ) { 
		clave = k;
	} 
	public void setValue(V v) {
		valor = v;
	}
	// Para mostrar entradas
	public String toString( ) { 
		return "("+getKey()+","+getValue()+")" ;
	}
	}
