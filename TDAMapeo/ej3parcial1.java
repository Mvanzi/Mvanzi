package TDAMapeo;

public class ej3parcial1<K,V> {

	
	public Map<V,K> mapeoInverso(Map<K,V> m) throws InvalidReverseMapping{
		Map<V,K> ret= new Mapeo_con_lista<V,K>();   // c1
		try {
		for(Entry<K,V> e: m.entries()) {
			if(ret.get(e.getValue())!=m.get(e.getKey()))
			ret.put(e.getValue(), e.getKey());
			else throw new InvalidReverseMapping("no se puede hacer el inverso del mapeo");
		}
		}catch(InvalidReverseMapping | InvalidKeyException e1) {
			
			e1.printStackTrace();
			
		}
		
		return ret;
		
	}
	
}
