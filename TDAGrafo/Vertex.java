package TDAGrafo;

import TDALista.Position;

public interface Vertex<E> extends Position<E> {

	public void setEstado(boolean b);
	public boolean getEstado();
}