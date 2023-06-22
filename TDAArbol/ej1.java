package TDAArbol;

public class ej1<E> {
	
	public Position<E> ancestroComun(){
	
	if(!(t.isRoot(p1)  t.isRoot(p2))) {
        if(p1==p2  t.parent(p1)==t.parent(p2))
            ret = t.parent(p1);
        else if(t.parent(p1)==p2 || t.parent(p2)==p1) {
            if(t.parent(p1)==p2) 
                ret = t.parent(p2);
            else
                ret = t.parent(p1);
        }

        boolean encontre=true;
        Position<E> padre=t.parent(p1);
        Position<E> padre2=t.parent(p2);
        
        while(!padre.isRoot() && !encontre) {
        	
        	while(!padre2.isRoot() && !encontre) {
        		
        		if(padre==padre2)
        		
        	}
        	
        }
        
        
    }

}
