package TDAGrafo;

import java.util.Iterator;

import TDALista.*;

public class Grafo_lista_adyacencia<V, E> implements Graph<V, E> {

	protected PositionList<VerticeAd<V, E>> nodos;
	protected PositionList<ArcoAd<V, E>> arcos;

	public Grafo_lista_adyacencia() {
		nodos = new Lista_doble_enlazada<VerticeAd<V, E>>();
		arcos = new Lista_doble_enlazada<ArcoAd<V, E>>();
	}

	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new Lista_doble_enlazada<Vertex<V>>();
		for (VerticeAd<V, E> pos : nodos) {
			lista.addLast(pos);
		}
		return lista;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new Lista_doble_enlazada<Edge<E>>();
		for (ArcoAd<V, E> pos : arcos) {
			lista.addLast(pos);
		}
		return lista;
	}

	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new Lista_doble_enlazada<Edge<E>>();
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) v;
		for (ArcoAd<V, E> arco : vertice.getAdyacentes()) {
			lista.addLast(arco);
		}
		return lista;
	}
	public Iterable<Edge<E>> emergentEdges(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new Lista_doble_enlazada<Edge<E>>();
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) v;
		for (ArcoAd<V, E> arco : vertice.getAdyacentes()) {
			lista.addLast(arco);
		}
		return lista;
	}

	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		Vertex<V> salida = null;
		@SuppressWarnings("unchecked")
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		if (arco.getSuces() == v)
			salida = arco.getPred();
		else {
			if (arco.getPred() == v)
				salida = arco.getSuces();
			else
				throw new InvalidEdgeException("Ninguno de los extremos coincide con el vertice");
		}
		return salida;
	}

	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		@SuppressWarnings("unchecked")
		VerticeAd<V, E>[] salida = (VerticeAd<V, E>[]) new VerticeAd[2];
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		@SuppressWarnings("unchecked")
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		salida[0] = arco.getPred();
		salida[1] = arco.getSuces();
		return salida;
	}

	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		boolean salida = false;
		ArcoAd<V, E> a;
		@SuppressWarnings("unchecked")
		VerticeAd<V,E> vv = (VerticeAd<V,E>)v;
		Iterator<ArcoAd<V,E>> itArcos = vv.getAdyacentes().iterator();
		while (itArcos.hasNext() && !salida) {
			a = itArcos.next();
			if (a.getPred() == v && a.getSuces() == w)
				salida = true;
			else if (a.getPred() == w && a.getSuces() == v)
				salida = true;
		}
		return salida;
	}

	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V salida = null;
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) v;
		salida = vertice.element();
		vertice.setElement(x);
		return salida;
	}

	public Vertex<V> insertVertex(V x) {
		VerticeAd<V, E> nuevo = new VerticeAd<V, E>(x);
		nodos.addLast(nuevo);
		try {
			nuevo.setPosicion(nodos.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return nuevo;
	}

	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vv = (VerticeAd<V, E>) v;
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> ww = (VerticeAd<V, E>) w;
		ArcoAd<V, E> nuevo = new ArcoAd<V, E>(e, vv, ww);
		try {
			vv.getAdyacentes().addLast(nuevo);
			nuevo.setPosPred(vv.getAdyacentes().last());
			ww.getAdyacentes().addLast(nuevo);
			nuevo.setPosSuces(ww.getAdyacentes().last());
			arcos.addLast(nuevo);
			nuevo.setPosicionEnAdyacentes(arcos.last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return nuevo;
	}

	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V removed = v.element();
		@SuppressWarnings("unchecked")
		VerticeAd<V, E> vv = (VerticeAd<V, E>) v;
		try {
			for (ArcoAd<V, E> arco : vv.getAdyacentes()) {
				arcos.remove(arco.getPosicion());
				arco.getPred().getAdyacentes().remove(arco.getPosPred());
				arco.getSuces().getAdyacentes().remove(arco.getPosSuces());
			}
			nodos.remove(vv.getPosicion());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return removed;
	}

	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		E removed = e.element();
		@SuppressWarnings("unchecked")
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		try {
			arcos.remove(arco.getPosicion());
			arco.getPred().getAdyacentes().remove(arco.getPosPred());
			arco.getSuces().getAdyacentes().remove(arco.getPosSuces());
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return removed;
	}
	
	/* Implementar un método que dado un grafo g, un vertice v y un vertice w, calcule el camino minimo de v a w. Imprimir el camino.
	   Asumir que se tiene el tda grafo implementado.
	   */
	
	
	
	public int caminimo(Graph<V,E> g, Vertex<V> v, Vertex<V> w) {
		PositionList<Vertex<V>> camino= new Lista_doble_enlazada<Vertex<V>>();
		v.setEstado(true);
		camino.addLast(v);
		if(v.equals(w))
		
		return 0;
	}
	
}