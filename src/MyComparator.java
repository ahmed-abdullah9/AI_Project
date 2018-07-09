import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

class MyComparator implements Comparator<Node>
{
   	@Override
    public int compare(Node e1, Node e2)
    {
	    if (e1.Calculater()>e2.Calculater()) return 1;
	    
	    if (e1.Calculater()< e2.Calculater()) return -1;             
	    	
	 //   if(e1.Calculater()==e2.Calculater())
       return 0;
  }
  
}