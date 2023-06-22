package TDAGrafo;

public class punto_5a_grafos {
	
	/*PUNTO GRAFOS: Implemente en Java un algoritmo que dado un grafo con arcos pesados y dos vértices A y
	B, encuentre el camino más económico de A a B. Determine el orden del tiempo de
	ejecución de su solución considerando la complejidad temporal de la implementación del
	grafo y de cualquier otra clase que necesitara. */

	/*
	 * calculamos el costo de un camino de A a B
	 * 	lo insertamos en una lista
	 * 	retornamos el costo mas bajo
	 */
	
	public <V, E> int caminoCercano(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2) {
		int actual=0;
		int minimo=Integer.MAX_VALUE;;
		for(Vertex<V> op:g.vertices()) {
			op.setEstado(false);
		}
		hallarMin( g,  v1, v2,  minimo,  actual);
		return minimo;
	}
	
	public <V, E> void hallarMin(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino, int minimo, int actual) {
		
		origen.setEstado(true);
		if(origen.equals(destino)) {
		
			if(actual<minimo)
				minimo=actual;
		}
		try {
			for(Edge<E> e:g.incidentEdges(origen)) {
                Vertex<V> v1=g.opposite(origen, e);
                if(v1.getEstado()==false) {
                	
                	actual=actual + e.getCosto();
                    hallarMin(g,v1,destino,minimo, actual);
                    
                }
                
            }
			origen.setEstado(false);
			
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
		}
		
	}
}
