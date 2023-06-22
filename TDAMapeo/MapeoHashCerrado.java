package TDAMapeo;



import TDALista.*;

public class MapeoHashCerrado<K,V> implements Map<K,V> {

	//atributos
	
		private Entrada<K,V>[] T;
		private int size,N;
		private Entrada<K,V> DISPONIBLE;
		
	//constructor
		
		public MapeoHashCerrado() {
			N=1023;
			size=0;
			T= (Entrada<K,V>[]) new Entrada[N];
			DISPONIBLE = new Entrada<K,V>(null,null);
		}
		
	//metodos
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			return size==0;
		}
		
		public V get(K k) throws InvalidKeyException{
			checkKey(k);
			int p = findEntry(k);
			if (p>=0)
				return T[p].getValue();
			else
				return null;
		}
		
		private int findEntry(K k) {
			int p = funcionHash(k);
			boolean encontre=false;
			while(T[p]!=null && !encontre){
				if(T[p].getKey()!=null) 
					if(T[p].getKey().equals(k))
						encontre=true;
				if(!encontre)
					p = (p+1) % N;
			}
			if(T[p]!=null)
				return p;
			else
				return -1;
		}
		
		private int funcionHash(K k) {
			int p = k.hashCode();
			return p % N;
		}
		
		private void checkKey(K k) throws InvalidKeyException {
			if(k==null)
				throw new InvalidKeyException("la clave es nula");
		}
		
		public V put(K k,V v) throws InvalidKeyException{
			
			if(!factorCarga()) {
				rehash();
			}
			
			checkKey(k);
			int p = findAvailableEntry(k);
			if(T[p]!=null && T[p].getKey()!=null) {
				V aux = T[p].getValue();
				T[p].setValue(v);
				return aux;
			}
			size++;
			T[p]=new Entrada<K,V>(k,v);
			return null;
		}
		
		private int findAvailableEntry(K k) {
			int p = funcionHash(k);
			while(T[p]!=null && T[p]!=DISPONIBLE && (!T[p].getKey().equals(k)) ) {
				p= (p+1) % N;
			}
			return p;
		}
		
		private void rehash() {
			N=N*2+1;
			Entrada<K,V> [] A = T;
			T =  (Entrada<K,V>[]) new Entrada[N];
		
			
			for(int i=0 ; i<A.length ; i++) {
				if(A[i]!=null && A[i]!=DISPONIBLE) {
					int p = findAvailableEntry(A[i].getKey());
					T[p]=A[i];
				}
			}
		}
		
		private boolean factorCarga() {
			float i = size/N;
			return i<0.5;
		}
		
		public V remove(K k) throws InvalidKeyException{
			checkKey(k);
			int p = findEntry(k);
			if(p>=0) {
				V aux = T[p].getValue();
				T[p]=DISPONIBLE;
				size--;
				return aux;
			}
			else
				return null;
		}
		
		public Iterable<K> keys(){
			PositionList<K> L = new Lista_doble_enlazada<K>();
			for(int i=0 ; i<N ; i++)
				if(T[i]!=null && T[i].getKey()!=null)
					L.addLast(T[i].getKey());
			return L;
		}
		
		public Iterable<V> values(){
			PositionList<V> L = new Lista_doble_enlazada<V>();
			for(int i=0 ; i<N ; i++)
				if(T[i]!=null && T[i].getKey()!=null)
					L.addLast(T[i].getValue());
			return L;
		}
		
		public Iterable<Entry<K,V>> entries(){
			PositionList<Entry<K,V>> L = new Lista_doble_enlazada<Entry<K,V>>();
			for(int i=0 ; i<N ; i++)
				if(T[i]!=null && T[i].getKey()!=null)
					L.addLast(T[i]);
			return L;
		}
}
