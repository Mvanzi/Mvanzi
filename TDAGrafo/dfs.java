package TDAGrafo;

public class dfs {

	public static <V, E> void DFS(Graph<V, E> grafo) {
        for (Vertex<V> ver : grafo.vertices())
            ver.setEstado(false);
        
        for (Vertex<V> v1 : grafo.vertices())
            if (v1.getEstado() == false)
                DFSrec(grafo, v1);
    }

    private static <V, E> void DFSrec(Graph<V, E> grafo, Vertex<V> v) {
        try {
            v.setEstado(true);
            
            for (Edge<E> e : grafo.incidentEdges(v)) {
               
            	Vertex<V> w = grafo.opposite(v, e);

                if (w.getEstado() == false)
                    DFSrec(grafo, w);
            }
        } catch (InvalidVertexException | InvalidEdgeException e) {
            e.printStackTrace();
        }

    }
	
}
