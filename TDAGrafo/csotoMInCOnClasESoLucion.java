package TDAGrafo;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class csotoMInCOnClasESoLucion {

	
	/*
	 * hallar costo minimo de caminos
	 */
	
	public <V,E> PositionList<Vertex<V>> costoMin(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino){
		PositionList<Vertex<V>> caminoAct=  new Lista_doble_enlazada<Vertex<V>>();
		Solucion<Vertex<V>> costo= new Solucion<Vertex<V>>(Float.MAX_VALUE);
		float costo2=0;;
		for(Vertex<V> v: g.vertices()) {
			v.setEstado(false);
			costo.getLista().addLast(v);
		}
		
		Dfs(g,origen,destino, costo, costo2,caminoAct);
		return costo.getLista();
	}
	
	private <V,E> void Dfs(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino, Solucion<Vertex<V>> costo, float costo2, PositionList<Vertex<V>> caminoAct){
		origen.setEstado(true);
		caminoAct.addLast(origen);
		
		if(origen.equals(destino) && costo.getCosto()>costo2) {
			costo.getLista().clonar(caminoAct);
			costo.setCosto(costo2);
		}
		
		try {
			
			for(Edge<E> e: g.incidentEdges(origen)) {
				Vertex<V> v= g.opposite(origen, e);
				
				if(v.getEstado()==false)
					Dfs(g,v,destino, costo, costo2 + e.element(), caminoAct);
			}
			
			caminoAct.remove(caminoAct.last());
			origen.setEstado(false);
			
		} catch (InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {e.printStackTrace();
		}
		
		
	}
}
