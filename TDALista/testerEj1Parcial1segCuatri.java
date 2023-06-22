package TDALista;

public class testerEj1Parcial1segCuatri<E> {

	
	
		public static void main(String[] args) {
			
			ListaSimplementeEnlazada<Integer> l1= new ListaSimplementeEnlazada<Integer>();
			ListaSimplementeEnlazada<Integer> l2= new ListaSimplementeEnlazada<Integer>();
			l2.addLast(1);l2.addLast(3);l2.addLast(3);
			
			l1.addLast(2);l1.addLast(3);l1.addLast(5);l1.addLast(7);l1.addLast(4);
			System.out.println(repetidos(l1,l2)); 
		
			
		}
		
		
		private static Nodo<Integer> checkPosition(Position<Integer> p) throws InvalidPositionException{
			try {
				if(p==null)
					throw new InvalidPositionException("posicioon nula");
				if(p.element()==null)
					throw new InvalidPositionException("p eliminada previamente");
				return (Nodo<Integer>)p;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("p no es un nodo de la lista");
			}
		}
		
		public static Position<Integer> next(Position<Integer> p) throws InvalidPositionException, BoundaryViolationException {
			Nodo<Integer> n=checkPosition(p);
			if(n.getSiguiente()==null)
				throw new BoundaryViolationException("Next:: Siguiente de ultimo");

			return n.getSiguiente();
		}
		
		public static PositionList<Integer> repetidos(PositionList<Integer> l1, PositionList<Integer> l2){
			
			try {
				
			Position<Integer> p2= l2.first();
			
			while(p2!=l2.last()) {
				
				if(cantRep(l2,p2.element())>=2 && pertenece(l1, p2.element())) {
					
					l1.addFirst(p2.element());
					p2=l2.next(p2);
					
				}else
					p2=l2.next(p2);
				
			}
			
			if(cantRep(l2,p2.element())==2 && pertenece(l1, p2.element())) //Ultimo elemento de l2
				l1.addFirst(p2.element());
			
			}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e2) {

				e2.printStackTrace();

			}
			return l1;
		}
		
		private static boolean pertenece(PositionList<Integer> l1, Integer e) {
			boolean esta=false;
			try {
			Position<Integer> p= l1.first();


			if(!(p.element()==e)) {

				while(p!=l1.last() && !esta) {
					p=next(p);

					if(p.equals(e))
						esta=true;
				}
				if(p.equals(e))
					esta=true;
			}else
				esta=true;
			
			}catch(InvalidPositionException | EmptyListException | BoundaryViolationException e2) {

				e2.printStackTrace();

			}
			return esta;
		}
		
		private static int cantRep(PositionList<Integer> l, Integer e) {
			int cant=0;
			try {
			Position<Integer> p= l.first();

			if(p.element()==e)
				cant++;

				while(p!=l.last() && cant<2) {
					p=next(p);

					if(p.element()==e)
						cant++;
				}
				if(p.element()==e)
					cant++;
			
			}catch(InvalidPositionException | EmptyListException | BoundaryViolationException e2) {

				e2.printStackTrace();

			}
			
			return cant;
		}
	
}
