package TDAGrafo;

import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;

public class ej3parcial<V,E> {

	public Map<Integer,Integer> resultado(Graph<Integer,Integer> grafo){
		Map<Integer,Integer> mapa= new Mapeo_hash_abierto<Integer,Integer>();
		
		for (Vertex<Integer> ver : grafo.vertices())
            ver.setEstado(false);
       
		for (Vertex<Integer> v1 : grafo.vertices())
            if (v1.getEstado() == false)
                DFSrec(grafo, v1,mapa);
		
		return mapa;
	}
	
	private static <V, E> void DFSrec(Graph<Integer, Integer> grafo, Vertex<Integer> vert, Map<Integer,Integer> m) {
        try {
           
            vert.setEstado(true);
            m.put(vert.element(), cantRPar(vert, grafo));
            Iterable<Edge<Integer>> adyacentes = grafo.incidentEdges(vert);
            for (Edge<Integer> e : adyacentes) {
                Vertex<Integer> w = grafo.opposite(vert, e);
                
                if (w.getEstado() == false)
                    DFSrec(grafo, w,m);
            }
        } catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
            e.printStackTrace();
        }

    }
	
	private static int cantRPar(Vertex<Integer> vert, Graph<Integer,Integer> g) {
		int total=0;
		try {
		for(Edge<Integer> e: g.incidentEdges(vert)) {
			
			if(e.element()%2==0)
				total++;
			
		}
		}catch(InvalidVertexException e1) {
			
			e1.printStackTrace();
			
		}
		return total;
	}
	
	public Map<V,E> ej3parcial2(Graph<V,E> grafo){
		Map<V,E> mapa= new Mapeo_hash_abierto<V,E>();
		
		for (Vertex<V> ver : grafo.vertices())
            ver.setEstado(false);
       
		for (Vertex<V> v1 : grafo.vertices())
            if (v1.getEstado() == false)
                DFSrec2(grafo, v1,mapa);
		
		return mapa;
	}
	
	private static <V, E> void DFSrec2(Graph<V,E> grafo, Vertex<V> vert, Map<V,E> m) {
        try {
           
            vert.setEstado(true);
            E grado= incidentEdges(vert);
            m.put(vert.element(), grado);
            Iterable<Edge<E>> adyacentes = grafo.incidentEdges(vert);
            for (Edge<E> e : adyacentes) {
                Vertex<V> w = grafo.opposite(vert, e);
                
                if (w.getEstado() == false)
                    DFSrec2(grafo, w,m);
            }
        } catch (InvalidVertexException | InvalidEdgeException e) {
            e.printStackTrace();
        }

    }
	
	public int incidentEdges(Vertex<V> vert) throws InvalidVertexException {
		if (vert == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new Lista_doble_enlazada<Edge<E>>();
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) vert;
		for (ArcoAd<V, E> arco : vertice.getAdyacentes()) {
			lista.addLast(arco);
		}
		return lista.size();
	}
	
}
