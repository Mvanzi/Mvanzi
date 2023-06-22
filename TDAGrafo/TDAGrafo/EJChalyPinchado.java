package TDAGrafo.TDAGrafo;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class EJChalyPinchado {
/*
 * Dado un Grafo no dirigido llamado German, y dos vértices vizcacha1 y vizcacha2, retornar TRUE si hay camino entre los dos, FALSE en caso contrario
 */
	
	public <V,E> boolean pinchado(Graph<V,E> german, Vertex<V> viz1, Vertex<V> viz2) {
		PositionList<Vertex<V>> camino= new Lista_doble_enlazada<Vertex<V>>();
		
		for(Vertex<V> v1: german.vertices()) {
			v1.setEstado(false);
		}

		return dfs(german,camino, viz1, viz2);
	}
	
	private <V,E> boolean dfs(Graph<V,E> german, PositionList<Vertex<V>> camino, Vertex<V> viz1, Vertex<V> viz2) {
		viz1.setEstado(true);
		camino.addLast(viz1);
		boolean hay=false;
		
		if(viz1.equals(viz2))
			return hay;	
		
		try {
			for(Edge<E> e: german.incidentEdges(viz1)) {
				Vertex<V> v1= german.opposite(viz1, e);
				
				if(v1.getEstado()==false)
					hay = dfs(german,camino, v1, viz2);
				if(hay)
					return hay;
			}
			
			camino.remove(camino.last());
			
			return hay;
			
		} catch (InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {e.printStackTrace();
		return hay;
		}
	}
}
