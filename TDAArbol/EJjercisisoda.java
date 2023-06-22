package TDAArbol;

import TDAColaCP.PriorityQueue;

public class EJjercisisoda<E> {

	/*
	 * Implemente en java una consulta que reciba un árbol de enteros e imprima por pantalla los rótulos de sus nodos
	ordenados en forma ascendente en base a la cantidad de hijos de cada nodo. Es decir, considere que un nodo A
	es mayor que un nodo B si la cantidad de hijos de A es mayor que la cantidad de hijos de B. Para resolver este
	ejercicio utilice una cola con prioridad sin implementarla, pero sí deberá implementar los métodos auxiliares
	utilizados. CALCULAR TIEMPO DE EJECUCIÓN
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
