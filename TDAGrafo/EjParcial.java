package TDAGrafo;

import TDALista.ListaSimplementeEnlazada;
import TDALista.PositionList;

public class EjParcial<V,E> {

	public boolean conexas(Graph<V,E> grafo, int n) {
		boolean cumple=true;
		boolean yaEsta=false;
		int total=0;
			for (Vertex<V> ver : grafo.vertices())
	            ver.setEstado(false);
	        for (Vertex<V> v1 : grafo.vertices())
	            if (v1.getEstado() == false && !yaEsta) {
	            	total++;
	            	if(total>n)
	            		yaEsta=true;
	                conexasRec(grafo, v1);
	            }
		
	        if(n<=total)
	        	cumple=false;
		return cumple;
	}
	
	
	  private static <V, E> void conexasRec(Graph<V, E> grafo, Vertex<V> vert) {
	        try {
	          
	            vert.setEstado(true);
	            Iterable<Edge<E>> adyacentes = grafo.incidentEdges(vert);
	            for (Edge<E> e : adyacentes) {
	                Vertex<V> w = grafo.opposite(vert, e);
	                if (w.getEstado() == false)
	                	conexasRec(grafo, w);
	            }
	        } catch (InvalidVertexException | InvalidEdgeException e) {
	            e.printStackTrace();
	        }

	    }
	  
	  
}
