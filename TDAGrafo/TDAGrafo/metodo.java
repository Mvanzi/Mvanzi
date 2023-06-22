package TDAGrafo.TDAGrafo;
import TDADiccionario.DiccionarioHashAbierto;
import TDADiccionario.Dictionary;
import TDALista.PositionList;
import TDAMapeo.Map;
import TDAMapeo.Mapeo_hash_abierto;

public class metodo {
/*
 * Usando el lenguaje de programacion Java 1.5 o superior, usando solamente los TDAs vistos en la teoria en terminos de sus operaciones,
 *  escriba un metodo que reciba un grafo dirigido G, y retorna un diccionario.
 *  El diccionario debe para cada vertice v del grafo, devolver las hojas alcanzables de v. Una hoja en un grafo dirigido es un vertice sin adyacentes
 */
	
	public  <K, V, E> Dictionary<Vertex<V>,PositionList<Vertex<V>>> messi(Graph<V,E> gd){
		Dictionary<Vertex<V>,PositionList<Vertex<V>>> dic= new DiccionarioHashAbierto<Vertex<V>, PositionList<Vertex<V>>>();
		
		for(Vertex<V> v1: gd.vertices()) {
			v1.setEstado(false);
		}
		
		for(Vertex<V> v1: gd.vertices()) {
			if(v1.getEstado()==false)
				messidfs(gd,v1,dic);
		}
		return dic;
	}
	
	private <V,E> void messidfs(Graph<V,E> gd, Vertex<V> v, M) {
		
	}
}

