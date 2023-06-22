package TDAGrafo;

import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;

public class ej3parcialseg<V, E> {

	public  Map<Vertex<V>,Vertex<V>> DFS(Graph<V, E> grafo) {
		Map<Vertex<V>,Vertex<V>> mapa= new Mapeo_hash_abierto<Vertex<V>,Vertex<V>>();
        for (Vertex<V> ver : grafo.vertices())
            ver.setEstado(false);
        for (Vertex<V> v1 : grafo.vertices())
            if (v1.getEstado() == false)
                DFSrec(grafo, v1,mapa);
        return mapa;
    }

    private void DFSrec(Graph<V, E> grafo, Vertex<V> vert, Map<Vertex<V>,Vertex<V>> m) {
        try {
            vert.setEstado(true);
            Iterable<Edge<E>> adyacentes = grafo.incidentEdges(vert);
            for (Edge<E> e : adyacentes) {
                Vertex<V> w = grafo.opposite(vert, e);
                
                if(cantVecinos(grafo,vert)==1)
                	m.put(vert, grafo.opposite(vert, e));
                
                if (w.getEstado() == false)
                    DFSrec(grafo, w, m);
            }
        } catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
            e.printStackTrace();
        }

    }
    
    private int cantVecinos(Graph<V,E> grafo ,Vertex<V> vert) {
    	int cant=0;
    	 try {
    		 
			Iterable<Edge<E>> adyacentes = grafo.incidentEdges(vert);
			for(Edge<E> e: adyacentes) {
				cant++;
			}
			
		} catch (InvalidVertexException e) {
			e.printStackTrace();
		}
    	return cant;
    }
	
}
