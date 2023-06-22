package TDAGrafo;

import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;

public class aDistancia2 {

	
	/*
	 * Dado un grafo G y un vertice v, retornar un mapeo donde las claves son los vertices
	 * a distancia 2 de v y la clave es el costo del camino desde v a ese vertice 
	 */
	
	public <V,E> Map<Vertex<V>, Float> distanciados(Graph<V,E> g, Vertex<V> v){
		Map<Vertex<V>, Float> map= new Mapeo_hash_abierto<Vertex<V>,Float>();
		int dist=0;
		for(Vertex<V> v1: g.vertices()) {
			v1.setEstado(false);
		}
		
		distanciadosDFS(g,v,map,dist);
		return map;
	}
	
	private <V,E> void distanciadosDFS(Graph<V,E> g, Vertex<V> v,Map<Vertex<V>, Float> map, int dist) {
		float costo=0;
		v.setEstado(true);

		try {
			if(dist!=2) {
				for(Edge<E> e: g.incidentEdges(v)) {
					Vertex<V> v1= g.opposite(v, e);
				
					if(v1.getEstado()==false) {
						costo= costo + e.element();
						distanciadosDFS(g,v,map,dist++);
				}
			}
			}
			else 
				map.put(v,costo);
			
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
		}
	}
}
