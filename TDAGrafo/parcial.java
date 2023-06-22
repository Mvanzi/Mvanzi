package TDAGrafo;

import TDALista.Lista_doble_enlazada;

public class parcial<V,E> {

	
	private boolean conexo(Graph<V,E> g) {
		boolean conexo = false;
		for( Vertex<V> v : g.vertices() ) {
			conexo = false;
			try {
				Lista_doble_enlazada<Edge<E>> li = (Lista_doble_enlazada<Edge<E>>) g.incidentEdges(v);
				if( !li.isEmpty() ) {

					if( !conexo ) {

						for( Vertex<V> v1 : g.vertices() ) {

							if( caminoDFS(v1, g, v) )
								conexo = true;

						}
					}
				}
			} catch (InvalidVertexException e1) {

				e1.printStackTrace();
			}
		}
		return conexo;
	}
	
	public boolean caminoDFS( Vertex<V> o, Graph<V, E> g, Vertex<V> f) {

		for (Vertex<V> vert : g.vertices())
			vert.setEstado(false);

		return CaminoDFSrec(o, g, f);

	}
	
	private boolean CaminoDFSrec(Vertex<V> o, Graph<V, E> g, Vertex<V> f) {
		try {

			o.setEstado(true);
			if (o == f)
				return true;

			else {

				boolean esta = false;
				for (Edge<E> e : g.incidentEdges(o)) {

					Vertex<V> w = g.opposite(o, e);

					if (w.getEstado() == false) {
						esta = CaminoDFSrec(w, g, f);

						if (esta)
							return true;

					}
				}
			}
			return false;
		} catch (InvalidEdgeException | InvalidVertexException e1) {

			e1.printStackTrace();

		}
		return false;
	}
	
	public boolean conexas (Graph<V,E> g, int n) {

		return (totalConexas(g)> n);

	}
	
	private int totalConexas(Graph<V,E> g ) {
		Lista_doble_enlazada<V> l = new Lista_doble_enlazada<V>();

		if(!conexo(g)) {

			for( Vertex<V> v1 : g.vertices() ) {

				for( Vertex<V> v2 : g.vertices() ) {

					if ( v1 != v2 ) {
						if( caminoDFS(v2, g, v1) ) {

							l.addLast((V) v1);
							l.addLast((V) v2);

						}
					}
				}
			}

		}
		return l.size();
	}


}
