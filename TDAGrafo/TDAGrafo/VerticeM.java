package TDAGrafo;

import TDALista.Position;
import TDAMapeo.Mapeo_hash_abierto;

public class VerticeM<V> extends Mapeo_hash_abierto<Object,Object> implements Vertex<V> {
	private V rotulo;
	private int indice;
	private Position<Vertex<V>> posicionEnVertices;
	private boolean estado;
	
	public VerticeM(V rotulo, int indice) {
		this.rotulo = rotulo;
		this.indice = indice;
	}
	//Setters
	public void setPosicionEnVertices(Position<Vertex<V>> p){ posicionEnVertices = p; }
	public void setRotulo(V nuevo) { rotulo = nuevo; }
	
	//Getters
	public int getIndice() { return indice; }
	public Position<Vertex<V>> getPosicionEnVertices() { return posicionEnVertices; }
	public V element() { return rotulo; }
	@Override
	public void setEstado(boolean e) {
		estado = e;
		
	}
	@Override
	public boolean getEstado() {
		
		return estado;
	}
}