package TDAGrafo;

import TDALista.*;
import TDAMapeo.Mapeo_hash_abierto;

public class VerticeAd<V, E> extends Mapeo_hash_abierto<Object,Object> implements Vertex<V>{

	private V rotulo;
	private PositionList<ArcoAd<V, E>> adyacentes;
	private Position<VerticeAd<V, E>> posicionEnNodos;
	private boolean estado;

	public VerticeAd(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new Lista_doble_enlazada<ArcoAd<V, E>>();
		estado = false;
	}

	public V element() {
		return rotulo;
	}

	public PositionList<ArcoAd<V, E>> getAdyacentes() {
		return adyacentes;
	}

	public Position<VerticeAd<V, E>> getPosicion() {
		return posicionEnNodos;
	}

	public void setElement(V elem) {
		rotulo = elem;
	}

	public void setPosicion(Position<VerticeAd<V, E>> pos) {
		posicionEnNodos = pos;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}