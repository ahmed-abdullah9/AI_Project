// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.util.*;

public class State {

	// THE FOLLOWING IS AN EXAMPLE OF THE ATTRIBUTES 
	// THAT YOU NEED TO PUT IN A STATE.
	// YOU SHOULD CHANGE IT:
	
	private int x;		// THIS IS X (MAYBE NOT NEEDED)
	private int y;		// THIS IS Y (MAYBE NOT NEEDED)
	//private int z; 		// THIS IS Z (MAYBE NOT NEEDED)
	private Map map;	// THE MAP
	private char tile [][];
	private int n;		
	private int m;	
	public  int battery;
	public  int g1;
	public   int g2;
	public  int []xx;
	public int []yy ;
	// -----------------------------
	 int countx=0;
	 int county=0;
	 public Queue <Integer>goalx;
	 public Queue<Integer> goaly;
 public int countgoal ;
	//THE FOLLOWING ARE THE CONSTRUCTORS
	// YOU CAN CHANGE OR REPALCE THEM.
	
	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A STATE FROM FILE.
	Scanner s=null;
	State(String fileName) throws IOException {
		// ..
		goalx =new LinkedList<Integer>();
		goaly =new LinkedList<Integer>();
		File e =new File (fileName);
		 s =  new Scanner(e);
		 n=s.nextInt();
		 m=s.nextInt();

		 tile =new char[n][m];
		
		 s.nextLine();
		 int x1=0;
		 int y1=0;
		 x=0;
         while (s.hasNext())
           {
        	   
   			String str = s.nextLine(); 
   			for(y=0;y<str.length();y++)
   			{
   			tile[x][y]=str.charAt(y);
   			if(tile[x][y]=='R'){
   				x1=x;
   				y1=y;
   			}
				if(tile[x][y]=='E'||tile[x][y]=='T'||tile[x][y]=='Y'||tile[x][y]=='F'||tile[x][y]=='U'||tile[x][y]=='Z')
				{
				//	goalx.add(x);
					//goaly.add(y);
					//countgoal++;
					g1=x;
					g2=y;
				}
   			}
   		    x++;
   			// do something
           }
           
           	s.close();
           
           	x=x1; 
           y=y1;
           battery = n+m;
       //    xx=new int [countgoal];
  		 //yy=new int [countgoal];
	}
	
	
	// CONSTRUCTOR 2:
	// THIS CONSTRUCTOR WILL CREATE A RANDOM STATE.
	//State(int n, int m, int rseed) {
		// ...
	//}
	
	
	// CONSTRUCTOR 3:
	// COPY CONSTRUCTOR.
	State( State k) {
		x = k.x;
		y = k.y;
		map=k.map;
		g1=k.g1;
		g2=k.g2;
		n=k.n;
		m=k.m;
		countgoal=k.countgoal;
		xx=new int [k.countgoal];
		//for(int i=0;i<countgoal;i++){
			//xx[i]=k.xx[i];
		//}
		 //yy=new int [k.countgoal];
		 //for(int i =0;i<countgoal;i++){
			// yy[i]=k.yy[i];
		 //}
		battery = k.battery;
		goalx=new LinkedList<>();
		goaly=new LinkedList<>();
		 tile=new char[k.tile.length][k.tile.length];
	     for(int i = 0; i < n; i++){
			 for(int j =0 ; j<m;j++){
				 
		       tile[i][j] =k.tile[i][j];
			 }  
			 }
		///for(int i = 0; i < tile.length; i++)
		    //myInt[i] = tile[i].clone();	
	/*	char[][] myInt = new char[tile.length][];
		for(int i = 0; i < tile.length; i++)
		{
		  myInt[i] = new char[tile[i].length];
		  for (int j = 0; j < tile[i].length; j++)
		  {
		    myInt[i][j] = tile[i][j];
		  }
		}*/
		//g1=s.g1;
		//g2=s.g2;
	//	z = s.z;
		// ...
	}
	public void getthegoal(){
		               
		for(int i = 0; i < n; i++){
			 for(int j =0 ; j<m;j++){
		if(tile[i][j]=='E'||tile[i][j]=='T'||tile[i][j]=='Y'||tile[i][j]=='F'||tile[i][j]=='U'||tile[i][j]=='Z'){
			xx[countx++]=i;
			yy[county++]=j;
			 }
			 }
		}
	}
	// -----------------------------aa
	// STATE GETTERS AND SETTERS
	// YOU CAN & SHOULD CHANGE THEM.
	// IF YOU HAVE QUESTIONS ASK THE INSTRUCTOR.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	public Map getMap() { 
		return map;
	}
	
	
	// METHOD THAT TELLS WHETHER THIS STATE IS EQUAL
	// TO ANOTHER STATE.
	public boolean equal(State s) {
		return ((x==s.x)&&(y==s.y)&&(battery>=s.battery)&&(tile[x][y]==s.tile[x][y] ));
	}
	
	// -----------------------------
	
	// THE ACTIONS:
	// YOU CAN CHANGE THE ACTIONS CONTENTS,
	// WHAT THE ACTIONS RETURN, ETC.
	
	public boolean charger (){
		if(battery>0){
			return true;}
		return false ; 
	}
	public boolean  recharge(){
		if(tile[x][y]=='D'||tile[x][y]=='F'){
		battery =n+m;
		return true; 
	}
		return false;
	}
	// ACTION 1
	// RETURNS BOOLEAN: 
	//     TRUE MEANS ACTION WAS APPLIED, 
	//     FALSE MEANS ACTOIN COULD NOT AND WAS NOT APPLIED.
	 public String  moveNorth() {
		 //display();
		 if( x!=0  && tile[x-1][y] !='B' &&tile[x][y]!='X'&& tile[x][y]!='Z'&& charger() ){						   // 	Hole cell with a robot in it: ‘X’ // Hole cell with a treasure and a robot in it: ‘Z’
		   //System.out.println(" Northالشروط صحيحة بس ماندري هل نفذها او لا" );
			   if(tile[x][y]=='D'&&tile[x-1][y]==' '){//• Charger cell with a robot only on it: ‘D’.
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='C';
				    	  x--;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
			   
				if(tile[x][y]=='D' &&tile[x-1][y]=='T'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='C';
					  x--;
			        	battery--;
					  tile[x][y]='U';
					  return "GOAL";
				}
				if(tile[x][y]=='D'&&tile[x-1][y]=='H'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='C';
					  x--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}					
				if(tile[x][y]=='F'&&tile[x-1][y]==' '){ //• Charger cell with a treasure and a robot on it: ‘F’
				  tile[x-1][y]=tile[x][y];
				  tile[x][y]='E';
				     x--;
		        	battery--;
				  tile[x][y]='R';
				  return "DONE";
			}
				if(tile[x][y]=='F'&&tile[x-1][y]=='H'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='E';
					     x--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x-1][y]==' '){
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='T';
					  x--;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='U'&&tile[x-1][y]=='H'){
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='T';
					  x--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x-1][y]=='C'){
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='T';
					  x--;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}

		   if(tile[x-1][y]=='T' ){//
			   tile[x-1][y]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	x--;
		        	battery--;
			tile[x][y]='U'; //Empty cell with a treasure and a robot: ‘U’.
			return "GOAL" ;
            }
			 if(tile[x-1][y]=='Y'){// Hole cell with a treasure in it: ‘Y’.
				  tile[x-1][y]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	x--;
			        	battery--;
				tile[x][y]='Z';// Hole cell with a treasure and a robot in it: ‘Z’.
				return "GOAL" ;
				}
			if( tile [x-1][y] =='H'){
				  tile[x-1][y]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	x--;
			        	battery--;
				tile[x][y]='X';// 	   Hole cell with a robot in it: ‘X’ 
				return "HOLE" ;
			}
			if(tile[x-1][y]=='C' ){//charger
				   tile[x-1][y]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	x--;
			        	battery--;
			        //	recharge();
				   tile[x][y]='D'; //Charger cell with a robot only on it
				return "DONE" ;
	}
			if(tile[x-1][y]=='E' ){//• Charger cell with a treasure only on it: ‘E’.
				   tile[x-1][y]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	x--;
			        	battery--;
			//        	recharge();
				tile[x][y]='F'; //Charger cell with a robot and treasure  on it
				return "GOAL" ;
	}
			if( tile [x-1][y]==' '){
				  tile[x-1][y]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	x--; 		        	
			        	battery--;
			        	tile[x][y] ='R';
						return "DONE" ;
			}
		   }
		   //System.out.println("ماراح فوق ");
			return "FAIL" ;
		   
			//System.out.println(11);
		//	return "FAIL";
		   }
	

// ACTION 2
	public String MoveSouth() {
		
	   if( x!=tile.length-1  &&  tile[x+1][y] !='B'  &&tile[x][y]!='X' && tile[x][y]!='Z' && charger()){						   // 	Hole cell with a robot in it: ‘X’ // Hole cell with a treasure and a robot in it: ‘Z’
		   //if(tile[x][y] =='R' )
			//	   System.out.println("أخيرااا قرأ المسافة ");
		   
		   //System.out.println("الشروط صحيحة بس ماندري راح تحت او لا");
		//   System.out.println(x);
		   //System.out.println(y);
		    	if(tile[x][y]=='D'&&tile[x+1][y]==' '){//• Charger cell with a robot only on it: ‘D’.
				  tile[x+1][y]=tile[x][y];
				  tile[x][y]='C';
				  x++;
		        	battery--;
				  tile[x][y]='R';
				  return "DONE";
			}
			if(tile[x][y]=='D'&&tile[x+1][y]=='T'){//• Charger cell with a robot only on it: ‘D’. 
				  tile[x+1][y]=tile[x][y];
				  tile[x][y]='C';
				  x++;
		        	battery--;
				  tile[x][y]='U';
				  return "GOAL";
			}
        
				if(tile[x][y]=='D'&&tile[x+1][y]=='H'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x-1][y]=tile[x][y];
					  tile[x][y]='C';
					  x++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
					if(tile[x][y]=='F'&&tile[x+1][y]==' '){ //• Charger cell with a treasure and a robot on it: ‘F’
				  tile[x+1][y]=tile[x][y];
				  tile[x][y]='E';
				     x++;
		        	battery--;
				  tile[x][y]='R';
				  return "DONE";
			}
				if(tile[x][y]=='F'&&tile[x+1][y]=='H'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x+1][y]=tile[x][y];
					  tile[x][y]='E';
					     x++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x+1][y]==' '){
					  tile[x+1][y]=tile[x][y];
					  tile[x][y]='T';
					  x++;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='U'&&tile[x+1][y]=='H'){
					  tile[x+1][y]=tile[x][y];
					  tile[x][y]='T';
					  x++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x+1][y]=='C'){
					  tile[x+1][y]=tile[x][y];
					  tile[x][y]='T';
					  x++;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}
				////////////////
		if(tile[x+1][y]=='T' ){//
		   tile[x+1][y]=tile[x][y];
		   tile [x][y]= ' ' ;
	        	x++;
	        	battery--;
		tile[x][y]='U'; //Empty cell with a treasure and a robot: ‘U’.
		return "GOAL" ;
} 
			 if(tile[x+1][y]=='Y'){// Hole cell with a treasure in it: ‘Y’.
			  tile[x+1][y]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	x++;
		        	battery--;
			tile[x][y]='Z';//• Hole cell with a treasure and a robot in it: ‘Z’.
			return "GOAL" ;
}
		 if( tile [x+1][y] =='H'){
			  tile[x+1][y]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	x++;
		        	battery--;
			tile[x][y]='X';// 	Hole cell with a robot in it: ‘X’ 
			return "HOLE" ;
		}
		
		 if(tile[x+1][y]=='C' ){//
			   tile[x+1][y]=tile[x][y];
			   tile [x ][y]= ' ' ;
		        	x++;
		        	battery--;
		       // 	recharge();
			tile[x][y]='D'; //• Charger cell with a robot only on it: ‘D’.
			return "DONE" ;
	}  if(tile[x+1][y]=='E' ){//• Charger cell with a treasure only on it: ‘E’.
		   tile[x+1][y]=tile[x][y];
		   tile [x ][y]= ' ' ;
	        	x++;
	        	battery--;
	        	//recharge();
		tile[x][y]='F'; //• Charger cell with a treasure and a robot on it: ‘F’
		return "GOAL" ;
} 
     		if( tile [x+1][y] ==' '){
		  tile[x+1][y]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	x++;
		      //  	System.out.println("He goes south");
		        	battery--;
		        	tile[x][y] ='R' ;
	               return "DONE";}
     		
			}
	//}
	// do something
		//return " ";
		   //System.out.println("ماتحققت الشروط ومانزل تحت  ");
     	   return "FAIL" ; 

} 
    public String MoveEast() {
//	Hole cell with a robot in it: ‘X’ 
	   if(y!=tile[y].length-1 && tile[x][y+1] !='B'&& tile[x][y]!='X' && tile[x][y]!='Z'  && charger()){
		 
	//	   System.out.println("تحققت الشروط بس ماندري رآح شرق او لا ");
		   if(tile[x][y]=='D'&&tile[x][y+1]==' '){//• Charger cell with a robot only on it: ‘D’.
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='C';
				    	  y++;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='D'&&tile[x][y+1]=='T'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='C';
					  y++;
			        	battery--;
					  tile[x][y]='U';
					  return "GOAL";
				}
				if(tile[x][y]=='D'&&tile[x][y+1]=='H'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='C';
					  y++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='F'&&tile[x][y+1]==' '){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='E';
					     y++;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='F'&&tile[x][y+1]=='C'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='E';
					     y++;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}
				if(tile[x][y]=='F'&&tile[x][y+1]=='H'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='E';
					     y++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x][y+1]==' '){
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='T';
					  y++;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='U'&&tile[x][y+1]=='H'){
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='T';
					  y++;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x][y+1]=='C'){
					  tile[x][y+1]=tile[x][y];
					  tile[x][y]='T';
					  y++;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}
		if(tile[x][y+1]=='T' ){//
		   tile[x][y+1]=tile[x][y];
		   tile [x][y]= ' ' ;
	        	y++;
	        	battery--;
		tile[x][y]='U'; //Empty cell with a treasure and a robot: ‘U’.
		return "GOAL" ;
		} 
if(tile[x][y+1]=='Y'){// Hole cell with a treasure in it: ‘Y’.
			  tile[x][y+1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y++;
		        	battery--;
			tile[x][y]='Z';//• Hole cell with a treasure and a robot in it: ‘Z’.
			return "GOAL" ;
}
		if( tile [x][y+1] =='H'){
			  tile[x][y+1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y++;
		        	battery--;
			tile[x][y]='X';// 	Hole cell with a robot in it: ‘X’ 
			return "HOLE" ;
}
		if(tile[x][y+1]=='C' ){//
			   tile[x][y+1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y++;
		        	battery--;
		       // 	recharge();
			tile[x][y]='D'; 
			return "DONE" ;
			}
		if(tile[x][y+1]=='E' ){//
				   tile[x][y+1]=tile[x][y];
				   tile [x][y]= ' ' ;
			        	y++;
			        	battery--;
			        	System.out.println("goes east");
			        	//recharge();
				tile[x][y]='F'; 
				return "GOAL" ;
				} 
	
		if(tile[x][y+1] ==' '){
			  tile[x][y+1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y++;
		        	battery--;
		        	 tile[x][y] ='R';
     	return "DONE" ; 
     	}
	   }
	//   if(y!=tile[y].length-1 && tile[x][y+1] !='B'|| tile[x][y]!='X' && tile[x][y]!='Z' &&tile[x][y]!='U' && charger()|| tile[x][y]!='F'){

//		System.out.println("he couldn't go east");
	   return "FAIL";
	      }
	// do something
	  
public String MoveWest() {
//	Hole cell with a robot in it: ‘X’ 
	   if(y!=0  && tile[x][y-1] !='B'  && tile[x][y]!='X' && tile[x][y]!='Z' && charger() ){/////////chess[x][y]=='U' ,,, Don't forget it 
	//			   System.out.println("تحققت الشروط بس ماندري راح يسار او لا ");
		    if(tile[x][y]=='D'&&tile[x][y-1]==' '){//• Charger cell with a robot only on it: ‘D’.
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='C';
				    	  y--;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='D'&&tile[x][y-1]=='T'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='C';
					  y--;
			        	battery--;
					  tile[x][y]='U';
					  return "GOAL";
				}
				if(tile[x][y]=='D'&&tile[x][y-1]=='H'){//• Charger cell with a robot only on it: ‘D’. 
					  tile[x][y+-1]=tile[x][y];
					  tile[x][y]='C';
					  y--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='F'&&tile[x][y-1]==' '){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='E';
					     y--;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='F'&&tile[x][y-1]=='H'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='E';
					     y--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='F'&&tile[x][y-1]=='C'){ //• Charger cell with a treasure and a robot on it: ‘F’
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='E';
					     y--;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}
				if(tile[x][y]=='U'&&tile[x][y-1]==' '){
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='T';
					  y--;
			        	battery--;
					  tile[x][y]='R';
					  return "DONE";
				}
				if(tile[x][y]=='U'&&tile[x][y-1]=='H'){
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='T';
					  y--;
			        	battery--;
					  tile[x][y]='X';
					  return "HOLE";
				}
				if(tile[x][y]=='U'&&tile[x][y-1]=='C'){
					  tile[x][y-1]=tile[x][y];
					  tile[x][y]='T';
					  y--;
			        	battery--;
					  tile[x][y]='D';
					  return "DONE";
				}
		if(tile[x][y-1]=='T' ){//
		   tile[x][y-1]=tile[x][y];
		   tile [x][y]= ' ' ;
	        	y--;
	        	battery--;
		tile[x][y]='U';//Empty cell with a treasure and a robot: ‘U’.
		return "GOAL" ;
}  
		 if(tile[x][y-1]=='Y'){// Hole cell with a treasure in it: ‘Y’.
			  tile[x][y-1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y--;
		        	battery--;
			tile[x][y]='Z';//• Hole cell with a treasure and a robot in it: ‘Z’. 
			return "GOAL" ;
}
		if( tile [x][y-1] =='H'){
			  tile[x][y-1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y--;
		        	battery--;
			tile[x][y]='X';// 	Hole cell with a robot in it: ‘X’ 
			return "HOLE" ;
}
		if(tile[x][y-1]=='C' ){//
			   tile[x][y-1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y--;
		        	battery--;
		        //	recharge();
			tile[x][y]='D';
			return "DONE" ;
	}  
		if(tile[x][y-1]=='E' ){//
			   tile[x][y-1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y--;
		        	battery--;
		        	//recharge();
			tile[x][y]='F';
			return "GOAL" ;
	}   
		if	(tile[x][y-1] ==' '){
			  tile[x][y-1]=tile[x][y];
			   tile [x][y]= ' ' ;
		        	y--;
		        	battery--;
		         tile[x][y] ='R';
return "DONE";
}
	   }
	  // System.out.println("Fail to go west");
	   return "FAIL";
}


	public boolean foundTreasure() {
		if (tile[x][y]=='U' ||  tile[x][y]=='Z'|| tile[x][y]=='F')
			return true;
		return false;		
		}

	// -----------------------------


	// DISPLAY THE STATE
	public void display() {
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
			System.out.print(tile[i][j]);
			}
			System.out.println();
}
	
		//	System.out.println(tile.length-1);
	
		// ...
	}
	
	
	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public void doCommandAndLog(String cmd,String Yourfilename) throws IOException {
		File q =new File (cmd);
		 String h=" ";
		 s =  new Scanner(q);
		 File file = new File(Yourfilename);
			FileWriter fileWriter = new FileWriter(file);
           while (s.hasNextLine())
           {
   			String str = s.nextLine(); 
		//String log="ERROR";
		switch (str) {
		 case "move-N" :	
			 h=moveNorth();
			 if(h.equals("DONE")) {
				 fileWriter.write(h +"\n" );
			 }
			   if(h.equals("HOLE")){
				 fileWriter.write( h+"\n" );
			 }
			   if(h.equals("FAIL")){
				 fileWriter.write(h+"\n" );
			 }
			   if(h.equals("GOAL")){
					 fileWriter.write(h +"\n");
			 }		// case ... :	//System.out.println("3");
			 break ; 
		 case "move-S": 
			 h=MoveSouth();
			 	 if(h.equals("DONE")) {
				 fileWriter.write("DONE"+"\n" );
		 }
			  if(h.equals("HOLE")){
			 fileWriter.write("HOLE"+"\n" );
		 }
		 	  if(h.equals("FAIL")){
			 fileWriter.write("FAIL"+"\n" );
		 }
		 	  if(h.equals("GOAL")){
			 fileWriter.write("GOAL"+"\n" );
		 }break;
		 case "move-E": 
			 h=MoveEast();
			 if(h.equals("DONE")) {
			 fileWriter.write(h+"\n");
		 }
			   if(h.equals("HOLE")){
			 fileWriter.write(h+"\n");
		 }
			 if(h.equals("FAIL")){
			 fileWriter.write(h+"\n" );
		 }
			  if(h.equals("GOAL")){
				 fileWriter.write(h +"\n");
		}
			 break;
		 case "move-W":  
			  h=MoveWest();
			 if(h.equals("DONE")) {
			 fileWriter.write("DONE"+"\n");
		 }
			 else if(h.equals("HOLE")){
			 fileWriter.write(h+"\n");
		 }
			 else 	 if(h.equals("FAIL")){
			 fileWriter.write(h+"\n");
		 }
			 else  if(h.equals("GOAL")){
				 fileWriter.write(h+"\n" );
		}
		 break;
		 case "recharge" :
			 if(recharge()){
			 fileWriter.write("DONE"+"\n" );
		}
			 else {
				 fileWriter.write("FAIL"+"\n" );
			 }
		}
           }
          fileWriter.close();
	}

	  public void FinalMap(String ff)throws IOException{
	        	File file = new File(ff);
				FileWriter fileWriter = new FileWriter(file);
				
	        	for(int i=0 ; i<n;i++){
	        		for(int j=0;j<m;j++){
	  
	        			fileWriter.write(tile[i][j]);
	        		}
	        		fileWriter.write("\n");

	        	}
	        	fileWriter.close();
	        }

	
	// -----------------------------


 	// THIS METHOD WILL RETURN THE SUCCESSOR STATES 
	  // OF COURSE, YOU CAN & SHOULD CHANGE IT
        public State[] successors() {
        	State children[] = new State[5]; // we have 5 actions
		// action 1		

		children[0] = new State(this);
		if (children[0].moveNorth().equals("FAIL")){
			children[0] =null ;
			}
		// action 2
		 children[1] = new State(this);	
			if (children[1].MoveSouth().equals("FAIL")){
			children[1] = null;
			}
		// action 3
		children[2] = new State(this);
		if (children[2].MoveWest().equals("FAIL")){
			children[2] = null;
		}
		// action 4
		children[3] = new State(this);
		if (children[3].MoveEast().equals("FAIL"))
			children[3] = null;
		children[4] = new State(this);
		if (!children[4].recharge()){
			children[4] = null;}
        	return children;
        }
        
       	// -----------------------------

	// ADD EXTRAS HERE ...
		
}