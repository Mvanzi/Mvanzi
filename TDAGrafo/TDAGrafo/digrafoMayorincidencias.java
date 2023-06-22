package TDAGrafo.TDAGrafo;

public class digrafoMayorincidencias {

	/*Escriba un método que, dado un digrafo, busque cuál es el vértice con mayor grado de
	incidencia (es decir, al cual apuntan la mayor cantidad de arcos), y luego lo elimine.
	*/
	
	public <V,E> void mayorIncidencias(GraphD<V,E> grafo) {
		int cant=0;
		int actual=0;
		Vertex<V> vertice=null;
		for(Vertex<V> v: grafo.vertices()) {
			v.setEstado(false);
		}
		
		for(Vertex<V> v: grafo.vertices()) {
			if(!v.getEstado())
				dfs(grafo,v, cant, actual, vertice);
		}
		try {
			grafo.removeVertex(vertice);
		} catch (InvalidVertexException e) {e.printStackTrace();
		}
	}
	
	private <V,E> void dfs(GraphD<V,E> grafo, Vertex<V> v, int mayor, int actual, Vertex<V> vertice) {
		v.setEstado(true);
		if(actual>mayor) {
			mayor=actual;
			 vertice.copy(v);
		}
		try {
			for(Edge<E> e: grafo.incidentEdges(v)) {
				actual++;
				Vertex<V> v1=grafo.opposite(v, e);
				if(v1.getEstado()==false)
					dfs(grafo, v1, mayor, actual, vertice);
			}
			
		}
		
		catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
		}
	}
		
}
