package TDAGrafo;

import TDAMapeo.Map;
import TDAMapeo.Mapeo_con_lista;
import TDAMapeo.Mapeo_hash_abierto;

public class punto1<K> {
		
		public <V, E> void forintobi(GraphD<V,E>g, int k ) {
			Map<K,V> mapa= new Mapeo_hash_abierto<K,V>();
			for(Vertex<V> v: g.vertices() ) 
				v.setEstado(false);
			for(Vertex<V> v1: g.vertices()) {
				if(!v1.getEstado())
					dfsrec(g, v1, k);
			}
			}
			
		

private <V, E> void dfsrec(GraphD<V,E>g,Vertex<V> v, int k ) {
		int cont=0;
		v.setEstado(true);	
		try {
			for(Edge<E> e: g.succesorEdges(v)) {
				Vertex<V> v1= g.opposite(v, e);
				
				if()
			}
		
		
		
		
		} catch (InvalidVertexException | InvalidEdgeException e) {
			
			e.printStackTrace();
		}
}
		

public class punto1<K> {
    
    public <V, E> void forintobi(GraphD<V,E>g, int k ) {
        Map<Vertex<V>,PositionList<Vertex<V>>> mapa= new MapeoConHashAbierto2022<Vertex<V>,PositionList<Vertex<V>>>();
        for(Vertex<V> v: g.vertices() ) 
            v.setEstado(false);
        for(Vertex<V> v1: g.vertices()) {
            if(!v1.getEstado())
                dfsrec(g, v1, k, mapa);
        }
        }
        
    
private <V, E> void dfsrec(GraphD<V,E>g,Vertex<V> v, int k,Map<Vertex<V>,PositionList<Vertex<V>>> mapa) {
    PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada2022<Vertex<V>>();
    int cont=0;
    v.setEstado(true);    
            try {
               
                       
                 for(Edge<E> e: g.succesorEdges(v)) {
                      Vertex<V> v1= g.opposite(v, e);
                      cont++;
                      lista.addLast(v1);
                      
                      if(v1.getEstado()==false)
                          dfsrec(g,v1,k,mapa);
                      
                      if(cont==k) //Si el grado del vertice es igual a K, lo ponemos en un mapeo
                          mapa.put(v, lista);
                 }
            
            } catch (InvalidVertexException | InvalidEdgeException e) {
                
                e.printStackTrace();
            }
    }
    

}

}//llaveFinal