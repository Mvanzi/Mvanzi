package TDALista;

public class TesterIntercalar2Listas<E> {

	public static  class teste {
		public static void main(String[] args) {
			ListaSimplementeEnlazada<Integer> l4= new ListaSimplementeEnlazada<Integer>();
			ListaSimplementeEnlazada<Integer> l1= new ListaSimplementeEnlazada<Integer>();
			ListaSimplementeEnlazada<Integer> l2= new ListaSimplementeEnlazada<Integer>();
			l2.addLast(3);l2.addLast(6);l2.addLast(8);
			
			l1.addLast(2);l1.addLast(4);l1.addLast(5);
			l4=(ListaSimplementeEnlazada<Integer>) intercalar(l1,l2);
			System.out.println(l4);
			
		}
		
		public static PositionList<Integer> intercalar(PositionList<Integer> l1, PositionList<Integer> l2){
			
			PositionList<Integer> ret = new ListaSimplementeEnlazada<Integer>();

			try {

				Position<Integer> p1= l1.first();
				Position<Integer> p2=l2.first();

				while(p1!=l1.last() && p2!=l2.last()) {

					if(p1.element()<=p2.element()) {
						ret.addLast(p1.element());
						p1=l1.next(p1);
					}
					else {
						ret.addLast(p2.element());
						p2=l2.next(p2);
					}

				}

				

				if(p2==l2.last() && p1!=l1.last()) {

					while(p1!=l1.last()) {

						if(p1.element()<=p2.element()) {
							ret.addLast(p1.element());
							p1=l1.next(p1);
						}
						else 
							ret.addLast(p2.element());
						
					}

				}
			
				
				
				if(p1==l1.last() && p2!=l2.last()) {

					while(p2!=l2.last()) {

						if(p1.element()<=p2.element())
							ret.addLast(p1.element());
							
						else {
							ret.addLast(p2.element());
							p2=l2.next(p2);
						}
				}

				}
				
				System.out.println(ret);
				
				if(p2==l2.last() && p1==l1.last())
					if(p1.element()<=p2.element()) {
						ret.addLast(p1.element());
						ret.addLast(p2.element());
					}
					else {
						ret.addLast(p1.element());
						ret.addLast(p2.element());
					}
				
			}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e1) {

				e1.printStackTrace();

			}
			return ret;

		}

	}
}
