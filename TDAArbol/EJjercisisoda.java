package TDAArbol;

import TDAColaCP.PriorityQueue;

public class EJjercisisoda<E> {

	/*
	 * Implemente en java una consulta que reciba un �rbol de enteros e imprima por pantalla los r�tulos de sus nodos
	ordenados en forma ascendente en base a la cantidad de hijos de cada nodo. Es decir, considere que un nodo A
	es mayor que un nodo B si la cantidad de hijos de A es mayor que la cantidad de hijos de B. Para resolver este
	ejercicio utilice una cola con prioridad sin implementarla, pero s� deber� implementar los m�todos auxiliares
	utilizados. CALCULAR TIEMPO DE EJECUCI�N
	 */
	
	public <K,V> void pritnArbolAscendente(Tree<Integer> t) {
		PriorityQueue<Integer,Integer> cola= new ColaCP_con_heap<Integer,Integer>();
		
		for(Position<Integer> p: t.positions()) {
			cola.insert(p.element(), cantHijos(t,p));
			
		}
	}
	
	private int cantHijos(Tree<Integer> t, Position<Integer> p) {
		int cont=0;
		try {
			for(Position<Integer> h: t.children(p)) {
				cont++;
			}
		} catch (InvalidPositionException e) {e.printStackTrace();
		}
		return cont;
	}
}
