package TDAMapeo;

import java.util.Iterator;

import TDALista.*;

/**
 * Class Mapeo_hash_abierto - implementa Map
 * @author Matias Vanzini
 * Define mapeos a traves de una tabla de hash, y los datos y metodos aplicables a un mapeo heredados por la Interface Map.
 * @param <K> Tipo de dato de las claves.
 * @param <V> Tipo de dato de los valores.
 */
public class Mapeo_hash_abierto <K,V> implements Map<K,V>{
	
	protected PositionList<Entrada<K,V>> [] A;
	protected int tamaño = 13;
	protected static final double fc = 0.9; 
	protected int cant_pares;
	
	/**
	 * Crea una tabla de hash vacia de tamaño 13, inicializando una lista en cada bucket.
	 */
	@SuppressWarnings("unchecked")
	public Mapeo_hash_abierto() {
		A = (PositionList<Entrada<K,V>> []) new PositionList [tamaño];
		for(int i=0; i<tamaño; i++){
			A[i] =  new Lista_doble_enlazada<Entrada<K,V>>();
		}
		cant_pares=0;
		}
	
	/**
	 * Devuelde el hashcode de la clave pasada por parametro	
	 * @param clave
	 * @return hash code de la clave
	 */
	protected int hash(K clave){
		return Math.abs(clave.hashCode() % tamaño);
		}
	
	@Override
	public int size() {
		return cant_pares;
	}
	
	@Override
	public boolean isEmpty() {
		return (cant_pares==0);
	}
	
	@Override
	public V get(K key) throws InvalidKeyException {
		V valor = null;
		boolean esta = false;
		
		//Chequea que la clave sea valida
		if (key == null)
			throw new InvalidKeyException("Clave invalida");
		
		PositionList<Entrada<K,V>> l =  A[hash(key)];
		Position<Entrada<K, V>> p =null; //pos actual
		
		try {
		if (!l.isEmpty())
			p=l.first();

		//Busco la clave y guardo su valor
		while (!esta && p!=null) {
			if (p.element().getKey().equals(key)) {
				valor = p.element().getValue();
				esta = true;
			}
			else if(p != l.last())
				p = l.next(p);
			else  p = null;
		}
		}
			catch (EmptyListException e) {}
			catch (InvalidPositionException e) {}
			catch (BoundaryViolationException e) {}
		
		return valor;
	}
	
	@Override
	public V put(K key, V value) throws InvalidKeyException{
		V val = null;
		boolean esta = false;
	
		//Chequeo que la clave sea valida
		if (key == null) {
			throw new InvalidKeyException("Clave invalida");
		}	
		
		PositionList<Entrada<K,V>> l =  A[hash(key)];
		Position<Entrada<K,V>> p ;
		
		try {
		
			if(l.isEmpty()) 
				p= null;
				
			else p =l.first();
			
		
		while(!esta && p != null) {
			if(p.element().getKey().equals(key)) {
				val = p.element().getValue();
				p.element().setValue(value);
				esta= true;
			}
			else if(p != l.last()) 
					p = l.next(p);
				else p=null;
		}
		}
		catch (EmptyListException e) {}
		catch(InvalidPositionException e) {}
		catch(BoundaryViolationException e) {}
	
		if (!esta) {
			l.addLast(new Entrada<K,V>(key, value));
			cant_pares++;
			}
				
		//controlo el factor de carga
				if ((cant_pares  / A.length) >= fc) {
					redimensionar();
					}	
			
		return val;
	}
	
	/**
	 * Agranda el arreglo usando de tamaño el proximo numero primo al doble del tamaño actual.
	 * Reacomoda los mapeos en el arreglo nuevo
	 */
	@SuppressWarnings("unchecked")
	private void redimensionar()  {
		tamaño = proxprimo(tamaño*2);
		PositionList<Entrada<K,V>>[] arregloviejo = A;
		A = (PositionList<Entrada<K,V>> []) new PositionList[tamaño]; //Redefino el arreglo agrandandolo
		
		for (int i =0; i< A.length ; i++) {
			A[i]= new Lista_doble_enlazada<Entrada<K,V>>();
		}
		//Reacomodo los mapeos en el arreglo redefinido
		for(int i = 0; i < arregloviejo.length; i++) {
            PositionList<Entrada<K,V>> bucket = arregloviejo[i];

            while(!bucket.isEmpty()) {

                Entrada<K, V> e;
				try {
					e = bucket.remove(bucket.first());
			
                int h = hash(e.getKey());
                this.A[h].addLast(e);
				} catch (InvalidPositionException | EmptyListException e1) {}
            }
            arregloviejo[i] = null;
            }
        

        arregloviejo = null;
        }
	
	/**
	 * Devuelve el proximo numero primo del numero pasado como parametro
	 * @param num
	 * @return proximo primo
	 */
	private int proxprimo(int num) {
		boolean encontre = false;
		int primo = 0 ;
		int divisores = 0;
		int cont = num;
		while(!encontre) {
			for(int i = 1; i <= cont; i++) {
				if(cont % i == 0 ) 
					divisores ++;
			}
			if (divisores == 2) {
				encontre = true;
				primo = cont;
			}
			else {
				divisores = 0;
				cont++;
			}
			}
	return primo;	
	}
	
	@Override
	public V remove(K key) throws InvalidKeyException {
		V valor = null;
        boolean esta = false;

        //Chequea que la clave sea valida
        if (key == null)
            throw new InvalidKeyException("Clave invalida");

        Iterator<Position<Entrada<K, V>>> it = A[hash(key)].positions().iterator(); //Creo un iterador del bucket correspondiante a la clave
        Position<Entrada<K, V>> act =  null; //pos actual


        if (it.hasNext() )
            act = it.next();

        try {
            //Busco el valor asociado a la clave
            while (!esta && act != null) {
                if (act.element().getKey().equals(key)) { //Si encuentro la clave, la elimino y guardo el valor asociado 
                    valor = act.element().getValue();
                    esta = true;
                    A[hash(key)].remove(act);
                    cant_pares--;
                } else {
                    if(it.hasNext())
                        act = it.next();
                    else act = null;
                }
            }
        } catch (InvalidPositionException e) {}
        return valor;

	}
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> lista = new Lista_doble_enlazada<K>();
		for (int i = 0; i < tamaño; i++) {
			for (Position<Entrada<K, V>> en : A[i].positions()) { 
				lista.addLast(en.element().getKey());
			}
		}
		return lista;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> lista = new Lista_doble_enlazada<V>();
		for (int i = 0; i < tamaño; i++) {
			for (Position<Entrada<K, V>> en : A[i].positions()) {
				lista.addLast(en.element().getValue());
			}
		}
		return lista;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> lista = new Lista_doble_enlazada<Entry<K, V>>();
		for (int i = 0; i < tamaño; i++) {
			for (Position<Entrada<K, V>> en : A[i].positions()) {
				lista.addLast(en.element());
			}
		}
		return lista;
		
	}
}



