package TDAGrafo;

import TDALista.*;
import TDAMapeo.Mapeo_hash_abierto;

public class VerticeD<V, E>extends Mapeo_hash_abierto<Object,Object> implements Vertex<V> {
	
	private Position<VerticeD<V, E>> posicionEnNodos;
	private V rotulo;
	private PositionList<ArcoD<V,E>> emergentes , incidentes;
	private boolean estado;
	
	public VerticeD(V rotulo) {
		this.rotulo = rotulo;
		setEmergentes(new Lista_doble_enlazada<ArcoD<V, E>>());
		setIncidentes(new Lista_doble_enlazada<ArcoD<V, E>>());
		estado = false;
	}
	
	
	@Override
	public V element() {
		return rotulo;
	}
	
	public void setRotulo(V e) {
		rotulo = e;
	}
	@Override
	public void setEstado(boolean e) {
		estado = e;
	}
	@Override
	public boolean getEstado() {
		return estado;
	}

	public PositionList<ArcoD<V,E>> getEmergentes() {
		return emergentes;
	}
	
	public void setEmergentes(PositionList<ArcoD<V,E>> emergentes) {
		this.emergentes = emergentes;
	}


	public Position<VerticeD<V, E>> getPosEnNodos() {
		return posicionEnNodos;
	}


	public void setPosEnNodos(Position<VerticeD<V, E>> posicionEnNodos) {
		this.posicionEnNodos = posicionEnNodos;
	}


	public PositionList<ArcoD<V,E>> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(PositionList<ArcoD<V,E>> incidentes) {
		this.incidentes = incidentes;
	}
	
	
}
