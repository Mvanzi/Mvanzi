package TDALista;

import java.util.Comparator;


import TDAMapeo.Map;

public class EJ2parcial {

	public PositionList<Map<Integer,Character>> kMasPequeños(PositionList<Map<Integer,Character>> l, int k){
		
		Comparator<Integer> compa = new DefaultComparator<Integer>(); //c2
		PriorityQueue<Integer,PositionList> colaCP= new ColaCP_con_heap<Integer,PositionList>(compa);
		
	}
	
}
