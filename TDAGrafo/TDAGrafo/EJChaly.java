package TDAGrafo.TDAGrafo;

import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class EJChaly {

	/*
	 * Dado un grafo dirigido y pesado llamado Grafete, y dos vertices O y D. Hallar el camino de costo minimo entre O y D.
	 */
		
	public <V,E> PositionList<Vertex<V>> caminoCostoMinimoGrafete(Graph<V,E> grafete, Vertex<V> origen, Vertex<V> destino){
		PositionList<Vertex<V>> caminoActual= new Lista_doble_enlazada<Vertex<V>>();
		PositionList<Vertex<V>> caminoMinimo= new Lista_doble_enlazada<Vertex<V>>();
		int costoMin= Integer.MAX_VALUE;
		int costoAct= 0;
		
		for(Vertex<V> v1: grafete.vertices()) {
			v1.setEstado(false);
			caminoMinimo.addLast(v1);
		}
		
			dfsGrafete(grafete, origen, destino, caminoActual, caminoMinimo, costoMin, costoAct);
					
		return caminoMinimo;
	}
	
	private <V,E> void dfsGrafete(Graph<V,E> grafete, Vertex<V> origen, Vertex<V> destino,PositionList<Vertex<V>> caminoActual, PositionList<Vertex<V>> caminoMinimo,int costoMin,int costoAct   ) {
		origen.setEstado(true);
		caminoActual.addLast(origen);
		
		if(origen.equals(destino) && costoMin>costoAct) {
			caminoMinimo.copy(caminoActual);
			costoMin=costoAct;
		}
		
		try {
			for(Edge<E> e: grafete.incidentEdges(origen)) {
				Vertex<V> v1= grafete.opposite(origen, e);
				costoAct= costoAct + e.element();
				if(v1.getEstado()==false) 
					dfsGrafete(grafete, v1, destino, caminoActual, caminoMinimo, costoMin, costoAct);
			}
			
			caminoActual.remove(caminoActual.last());
			origen.setEstado(false);
			
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
		}
	}
}
