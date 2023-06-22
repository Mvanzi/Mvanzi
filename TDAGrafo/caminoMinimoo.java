package TDAGrafo;

import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class caminoMinimoo {

	
	  public <V, E> PositionList<Vertex<V>> HallarCaminoMinimo(Graph<V,E> g,Vertex<V> origen,Vertex<V> destino){
	        PositionList<Vertex<V>> caminoActual = new Lista_doble_enlazada<Vertex<V>>();
	        PositionList<Vertex<V>> caminoMinimo = new Lista_doble_enlazada<Vertex<V>>();
	        
	        for(Vertex<V> v : g.vertices()) {
	            v.setEstado(false);
	            caminoMinimo.addLast(v);
	        }
	        try {
	            HallarCaminoMinimoRec(g,origen,destino,caminoActual,caminoMinimo);
	        } catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();
	        }
	        return caminoMinimo;
	    }
	    
	    private <V, E> void HallarCaminoMinimoRec(Graph<V,E> g,Vertex<V> origen,Vertex<V> destino,PositionList<Vertex<V>> cA,PositionList<Vertex<V>> cM) throws InvalidPositionException, EmptyListException {
	        origen.setEstado(true);
	        cA.addLast(origen);
	        if(origen.equals(destino)) {
	            if(cA.size() < cM.size()) {
	                System.out.println("HAGO SWAP");
	                cA.copy(cM);
	            }
	        }
	        else {
	            try {

	                for(Edge<E> a : g.incidentEdges(origen)) {
	                    Vertex<V> x = g.opposite(origen, a);
	                    if(x.getEstado()==false)
	                        HallarCaminoMinimoRec(g,x,destino,cA,cM);
	                }
	            } catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
	            }
	        }
	        try {
	            cA.remove(cA.last());
	            origen.setEstado(false);
	        } catch (InvalidPositionException | EmptyListException e1) { e1.printStackTrace();
	        }

	    }
}
