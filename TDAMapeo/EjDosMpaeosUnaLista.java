package TDAMapeo;

import TDALista.ListaSimplementeEnlazada;

public class EjDosMpaeosUnaLista<K,V> {
//Falta pulir!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public ListaSimplementeEnlazada<V> listaMapeos(Map<K,V> m1, Map<K,V> m2){
		
		ListaSimplementeEnlazada<V> l= new ListaSimplementeEnlazada<V>();
		
		for(Entry<K,V> e: m1.entries()) {
			
			if(pertenece(e,m2))
				l.addLast(e.getValue());
			
		}
		
		return l;
		
	}
	
	private boolean pertenece(Entry<K,V> e1, Map<K,V> m2) {
		boolean salir=false;
		for(Entry<K,V> e2: m2.entries()) {
			
			if(e2.getKey()!=e1.getKey() && e2.getValue()==e1.getValue())
				salir=true;
			
		}
		
		return salir;
	}
	
}
