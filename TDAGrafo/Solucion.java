package TDAGrafo;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Solucion<E> {
    
    private Float costo;
    private PositionList<E> lista;
    
    
    public Solucion(Float costo) {
        this.costo = costo;
        lista = new Lista_doble_enlazada<E>();
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(float cosA) {
        this.costo = cosA;
    }

    public PositionList<E> getLista() {
        return lista;
    }

    public void setLista(PositionList<E> lista) {
        this.lista = lista;
    }
    
    public void copy(PositionList<E> l) throws InvalidPositionException, EmptyListException {
        while(!lista.isEmpty()) {
            lista.remove(lista.last());
        }
        for(Position<E> n : l.positions()) {
            lista.addLast(n.element());
        }
    }
    
    
}
