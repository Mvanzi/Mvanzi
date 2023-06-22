package TDALista;

public class ej3 {
	 
	ListaSimplementeEnlazada<Integer> l1= new ListaSimplementeEnlazada<Integer>();
	ListaSimplementeEnlazada<Integer> l2= new ListaSimplementeEnlazada<Integer>();
	
	public ListaSimplementeEnlazada<Integer> intercalar(ListaSimplementeEnlazada<Integer> l1, ListaSimplementeEnlazada<Integer> l2) {
		ListaSimplementeEnlazada<Integer> u= new ListaSimplementeEnlazada<Integer>();
		try {
		Position<Integer> p1= l1.first(), ultima=l1.last();
		Position<Integer> p2= l2.first(), ultima2=l2.last();
		
		if(p1.element()<p2.element()) {
			u.addFirst(p1.element());
			p1= l1.next(p1);
		}else {
			u.addFirst(p2.element());
			p2=l2.next(p2);
		}
		
		while(p1!=ultima && p2!=ultima2) {
			
			if(p1.element()<p2.element()) {
				
				if(u.last().element()== p1.element())
					p1= l1.next(p1);
				
				else {
				u.addAfter(p1, p1.element());
				p1= l1.next(p1);
			}
			}
			else {
				
				if(u.last().element()== p2.element())
					p2= l2.next(p2);
				
				else {
				u.addAfter(p2, p2.element());
				p2= l2.next(p2);
				}
			}
		}
		
		if(l1.isEmpty()) {
			while(p2!=ultima2) {
				if(u.last().element()== p2.element())
					p2= l2.next(p2);
				
				else {
				u.addAfter(p2, p2.element());
				p2= l2.next(p2);
				}
			}
		}
		
		if(l2.isEmpty()) {
			while(p1!=ultima) {
				if(u.last().element()== p1.element())
					p1= l1.next(p1);
				
				else {
				u.addAfter(p1, p1.element());
				p1= l1.next(p1);
			}
			}
		}
		
	}catch(EmptyListException  | BoundaryViolationException | InvalidPositionException e) {System.out.println("ta mal");};
	
	return u;	
		
}
	
	
}
		
	
	



	


	    	
	    

