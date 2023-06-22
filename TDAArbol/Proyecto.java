package TDAArbol;

import Ejercicios.Pair;

public class Proyecto {

	Tree<Pair<Iterable<String>,String>> arbol= new Arbol<Pair<Iterable<String>,String>>();
	
	public void crearArbol(String directorio) {
		
		arbol.createRoot(directorio);
		
		
	}
	
	
}
