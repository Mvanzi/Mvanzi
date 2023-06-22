package TDAGrafo;

import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;

public class punto1FRAN {

	
	/**
     * Escriba un metodo que reciba un grafo dirigido G y un entero K, cuyos rotulos de vertice son de tipo generico y los arcos contienen con pesos de tipo generico.
     * El metodo debe construir un mapeo M de tal forma que para cada vertice V de G, el mapeo M retorna como imagen de V al conjunto de los vertices W alcanzables desde V
     * mediante un recorrido DFS pero tal que W tiene grado exactamente igual a K. Resuelvo el problema exclusivamente en terminos de los TDAs dados en la teoria. 
	 * @param <V>
	 * @param <K>
     */
	
	public <V, E> Map<Vertex<V>, PositionList<Vertex<V>>> metodo(Graph<V,E> g, int k){
		Map<Vertex<V>, PositionList<Vertex<V>>> mapa= new Mapeo_hash_abierto<Vertex<V>, PositionList<Vertex<V>>>();
		for(Vertex<V> v: g.vertices()) {
			v.setEstado(false);
		}
		for(Vertex<V> v: g.vertices()){
			if(!v.getEstado())
				metodoRec(g,k,v, mapa);
		}
		return null;
		
	}
	
	private <V, E> void metodoRec(Graph<V,E> g, int k, Vertex<V> v,Map<Vertex<V>, PositionList<Vertex<V>>> mapa) {
		
		v.setEstado(true);
		try {
			for(Edge<E> e: g.incidentEdges(v)) {
				Vertex<V> v1= g.opposite(v, e);
				mapa.put(v1,alcanzables(g,v1));
				
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	private <V, E> PositionList<Vertex<V>> alcanzables(Graph<V,E> g, Vertex<V> v){
		PositionList<Vertex<V>> lista= new Lista_doble_enlazada<Vertex<V>>();
		try {
			for(Edge<E> e: g.incidentEdges(v)) {
				lista.addLast(g.opposite(v, e));
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
