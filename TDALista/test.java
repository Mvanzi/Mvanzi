package TDALista;

import TDAPila.EmptyStackException;
import TDAPila.PilaArreglo;
import TDAPila.Stack;

public class test<E> {

	
	public static  class teste {
		public static void main(String[] args) {
			ListaSimplementeEnlazada<Integer> l4= new ListaSimplementeEnlazada<Integer>();
			ListaSimplementeEnlazada<Integer> l1= new ListaSimplementeEnlazada<Integer>();
			ListaSimplementeEnlazada<Integer> l2= new ListaSimplementeEnlazada<Integer>();
			l2.addLast(3);l2.addLast(6);l2.addLast(8);
			
			l1.addLast(2);l1.addLast(4);l1.addLast(5);l1.addLast(7);l1.addLast(4);
			l4=(ListaSimplementeEnlazada<Integer>) concatenar123(l1,l2);
			System.out.println(l4);
			
			
		}
		
	
	public static PositionList<Integer> concatenar123(ListaSimplementeEnlazada<Integer> l1, ListaSimplementeEnlazada<Integer> l2){
    	Stack<Integer> pila= new PilaArreglo<Integer>();
    	PositionList<Integer> l3= new ListaSimplementeEnlazada<Integer>();


    	try {
    		int total = l1.size() + l2.size();
    		Position<Integer> p1=l1.first();
    		Position<Integer> p2=l2.first();

    		while(p2!=l2.last()) { //paso l2 a una pila asi al momento de desapilar me queda al reves

    			pila.push(p2.element());
    			p2=l2.next(p2);

    		}
    		
    		if(p2==l2.last()) // meto el ultimo elemento de l2 en la pila, que habia quedado fuera
    			pila.push(p2.element());

    		while(p1!=l1.last() && !pila.isEmpty()) { //concateno l1 y la pila en l3

    			l3.addLast(p1.element());
    			
    			p1=l1.next(p1);

    			l3.addLast(pila.pop());
    			
    		}

    		if(p1==l1.last() && !pila.isEmpty()) {// si se vacio  l1, inserto el ultimo elemento de l1 en l3 y continuo insertando la pila en l3.
    			l3.addLast(p1.element());
    			
    			while(!pila.isEmpty()) {
    				l3.addLast(pila.pop());
    			}
    		}

    		if(pila.isEmpty() && p1!=l1.last()) {// si se vacio la pila, continuo insertando l1 en l3.
    			while(p1!=l1.last()) {

    				l3.addLast(p1.element());
    				p1=l1.next(p1);

    			}
    		}

    		if(p1==l1.last() && pila.isEmpty() && total!=l3.size()) // si estamos en el final de l1 y se vacio la pila, inserto el ultimo elem de l1 en l3
    			l3.addLast(p1.element());
    		

    	}catch(InvalidPositionException | EmptyListException | BoundaryViolationException | EmptyStackException e1) {

    		e1.printStackTrace();

    	}
    	return l3;
    }
	}
}
