package TDAMapeo;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Mapeo_con_lista<K,V> implements Map<K,V>{

	protected Lista_doble_enlazada<Entrada<K,V>> S;

	public Mapeo_con_lista() {
		S = new Lista_doble_enlazada<Entrada<K,V>>();
	}

	@Override 
	public V put( K key, V value )  throws InvalidKeyException {

		if(key == null){
			throw new InvalidKeyException("calve invalida");
		}

		for( Position<Entrada<K,V>> p : S.positions() )

			if( p.element().getKey().equals(key)) {

				V aux = p.element().getValue();

				p.element().setValue( value );

				return aux;

			}

		S.addLast(new Entrada<K,V>(key, value) ); 
		return null; 
	}
	@Override 
	public V get(K key) throws InvalidKeyException {

		if(key == null){
			throw new InvalidKeyException("calve invalida");
		}
		// Para cada posición p de la lista S hacer:
		for( Position<Entrada<K,V>> p : S.positions() )
			// Si la clave de la entrada actual es key:
			if( p.element().getKey().equals( key ) ) {
				// Retornar el valor de la entrada actual:
				return p.element().getValue();

			}
		// Si salí del for-each, quiere decir que no encontré ninguna entrada con clave key.
		return null;
	}
	@Override 
	public V remove(K key ) throws InvalidKeyException {
		if(key == null){
			throw new InvalidKeyException("calve invalida");
		}
		// Para cada posición p de S hacer:
		for( Position<Entrada<K,V>> p : S.positions() )
			// Si la entrada de la posición p tiene clave key:
			if( p.element().getKey().equals( key ) ) {
				// Salvar el valor de la entrada corriente en value:
				V value = p.element().getValue();
				// Eliminar la posición p de la lista S:
				try {
					S.remove( p );
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
				// Retornar el valor de entrada removida del mapeo:
				return value;
			}
		// Si salí del for-each, quiere decir que no encontré ninguna entrada con clave key
		return null;
	}

	@Override
	public int size() {
		return S.size();
	}

	@Override
	public boolean isEmpty() {
		return S.isEmpty();
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> l = new Lista_doble_enlazada<K>();
		for( Position<Entrada<K, V>> p : S.positions() )
			l.addLast( p.element().getKey() );
		return l ;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> l = new Lista_doble_enlazada<V>();
		for( Position<Entrada<K, V>> p : S.positions() )
			l.addLast( p.element().getValue() );
		return l ;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {      
		PositionList<Entry<K,V>> l = new Lista_doble_enlazada<Entry<K,V>>();                    
		for( Position<Entrada<K,V>> p : S.positions() )                
			l.addLast( p.element() );				
		return l;
	}

	public PositionList<Entry<K,V>> notas(Mapeo_con_lista<K,V> m1, Mapeo_con_lista<K,V> m2) {

		PositionList<Entry<K,V>> l= new Lista_doble_enlazada<Entry<K,V>>() ;

		for( Entry<K,V> e1 : m1.entries() ) {
			for( Entry<K,V> e2 : m2.entries() ) {

				if(e1.getKey().equals(e2.getKey()) && !e1.getValue().equals(e2.getValue()) && !pertenece(e1, l)) {

					l.addLast(e1);
					l.addLast(e2);

				}


			}


		}
		return l;
	}

	public boolean pertenece(Entry<K,V> e,PositionList<Entry<K,V>> l) {
		boolean salgo=false;
		try {

			if(!l.isEmpty()) {

				Position<Entry<K,V>> p= l.first(); 


				while(!p.equals(l.last()) && !salgo) {

					if(p.element().equals(e)) 
						salgo=true;

					p= l.next(p);

				}

				if(l.last().element().equals(e))
					salgo=true;
			}

		}catch( EmptyListException |  BoundaryViolationException | InvalidPositionException  e1 ) {

			e1.printStackTrace();  
		}

		return salgo;
	}



	public Map<V,K> invertir(Map<K,V> m){

		Map<V,K> ret= new Mapeo_con_lista<V,K>();

		try {

			if(!m.isEmpty()) {

				for( Entry<K,V> e : m.entries() ) {

					ret.put(e.getValue(), e.getKey());

				}

			}
			else
				System.out.println("error, lista vacia");


		}catch( InvalidKeyException  e1 ) {

			e1.printStackTrace();  
		}

		return ret;
	}

	public boolean MismasClaves(Map<K,V> m1, Map<K,V> m2) {
		boolean todas=true;

		if(!m1.isEmpty() || !m2.isEmpty()) {
			
			for( Entry<K,V> e1 : m1.entries() ) {
				for( Entry<K,V> e2 : m2.entries() ) {

					if(!(e1.getKey().equals(e2.getKey())) && todas) 
						todas=false;

				}
			}
		}else
			todas=false;
			

		return todas;
	}
	
	
	public Map<V,K> mapeoInverso(Map<K,V> m) throws InvalidReverseMapping{
		Map<V,K> ret= new Mapeo_con_lista<V,K>();   // c1
		
		try {
			
		for( Entry<K,V> e : m.entries() ) { //n
			
			if(!perteneceV(e.getValue())) // n + c3
				ret.put(e.getValue(), e.getKey()); 
			else throw new InvalidReverseMapping("no se puede hacer el inverso de un mapeo vacio");  

		}
		}catch(InvalidKeyException e3) {
			
			e3.printStackTrace();
		}
		return ret; //c4
	}
	
	
	
	public boolean perteneceV(V e) {
		boolean salgo=false;
		try {

			if(!S.isEmpty()) { //c1

				Position<Entrada<K,V>> p= S.first();  //c2


				while(!p.equals(S.last()) && !salgo) { //n

					if(p.element().getValue().equals(e))  //c3
						salgo=true;

					p= S.next(p); //c4

				}

				if(S.last().element().getValue().equals(e)) //c5
					salgo=true;
			}
	
}catch(InvalidPositionException | EmptyListException | BoundaryViolationException e3) {
	e3.printStackTrace();
}
		return salgo; //c6
	}
}
