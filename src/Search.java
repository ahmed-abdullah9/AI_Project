// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.util.*;

//import org.omg.CORBA.Current;
public class Search {


	// CONSTANTS:
	private static final int CLOSED_MAX_SIZE = 100000;
	private static final int FRINGE_MAX_SIZE = 100000;
	
	// ATTRIBUTES:
	private Node root;			// the root node
		private Queue<Node> fringe;		// the BFS frings: change it 							// for the other searches.
	private Node goal;			// the goal node
	private int numNodesExpanded;		// number of nodes expanded
	
	
	// ALTHOUGH YOU ARE NOT REQUIRED TO, BUT IT IS
	// HIGHLY RECOMMENDED THAT YOU IMPLEMENT THE 
	// CLOSED LIST MECHANISM (THE GRAPH SEARCH).
	// THE NEED FOR IT IS DUE TO THE VERY LARGE NUMBER OF 
	// REDUNDANT STATES THAT YOU WILL GENERATE BEFORE 
	// REACHING THE GOAL.
	// THE FOLLOWING IS A SIMPLE IMPLEMENTATION OF THE CLOSED
	// LIST, REPRESENTED AS A SIMPLE ARRAY OF NODES:

	private Node closed[];			// the closed list containing 							// visited nodes
	private int n_closed;			// size of closed list
	
	
	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A SEARCH OBJECT.
	Search( State init_state ) {
		root = new Node(init_state, null, -1, 0, 0); // make the root node
		fringe = new LinkedList<>();	// initialize Queue
		closed = null;
		n_closed = 0;
		goal = null;
		numNodesExpanded = 0;		
	}
	
	// THIS METHOD INITIALIZES THE CLOSED LIST
	private void initialize_closed() {
		if (closed==null)
			closed = new Node[CLOSED_MAX_SIZE];
		n_closed=0;
	}
	
	// THIS METHOD TESTS WHETHER THE NODE WAS
	// VISITED OR NOT USING A SIMPLE FOR LOOP.
	// YOU CAN CHANGE IT.
	private boolean visited(Node n) {
		for (int i=0; i<n_closed; i++) {
			if(closed[i].hasSameState(n)){
				return true;
			}
		}
		return false;
	}
	

	// THIS METHOD ADDS A NODE TO THE CLOSED LIST.
	// IT SIMPLY ADDS IT TO THE ND OF THE LIST. YOU
	// CAN CHANGE IT TO A MORE SOPHISTICATED METHOD.
	private void mark_as_visited(Node n) {
		// if the list is  full do a left shift:
		if (n_closed==CLOSED_MAX_SIZE) {
			for(int i=0; i<CLOSED_MAX_SIZE-1; i++)
				closed[i] = closed[i+1];
		}
		else
System.out.println(closed);
			n_closed++;
		closed[n_closed-1] = n;
	}
	
	// THIS METHOD WILL DO THE SEARCH AND CAN
	// RETURN THE GOAL NODE. YOU CAN EXTRACT
	// THE SOLUTION BY FOLLOWING THE PARENT NODES	
	  public Node BFS() {
		  numNodesExpanded = 0;
		Node nodesList[];
		Node current = root;
		initialize_closed();
	    fringe.add(current);		
	      	while (!fringe.isEmpty()) {
	      		current.display();
	      		current = fringe.remove();
     			mark_as_visited(current);
	      		if (current.isGoal()) {
	      			System.out.println("(&*^&&^*I%%%$%$##@#@$#^%&");
	      			current.display();
	      			goal=current;
	      		System.out.println(current.getbattary());
				return current;	
		
	      		}
			
	      		nodesList = current.expand();
			numNodesExpanded++;			
			for (int i=0; i<5; i++) {	// we have 5 actions
				if (nodesList[i]!=null){		
					if(!visited(nodesList[i])){
						System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					fringe.add(nodesList[i]);
					}
					
			 //     	if(visited(nodesList[i])){
				//			System.out.println("hass the same state ");
					//	fringe.add(nodesList[i]);
				///	}
					//if(visited(nodesList[i])){
					//	if(equal(visited(nodesList[i]))
				///	}
				}
			}			
		}
		return null;
		
	}

	public Node HillClimbing(){
		Node neighbor[];
		Node max = null;
		int h=0;
		Node current = root;
		ArrayList<Node> HC=new ArrayList<Node> ();
		numNodesExpanded = 0;
		HC.add(current);
		h=current.objectivefunction();
		while(!HC.isEmpty()){
			current.display();
			System.out.println("*********************************");
			if (current.isGoal()) {
				current.display();
				goal=current;
				System.out.println("#$%@^$");
				return current;	
			}
		    neighbor = current.expand();
	      	numNodesExpanded++;
		    for(int i =0 ;i<5;i++){
			if(neighbor[i]!=null){
			//	if(neighbor[i].h_md()>=current.h_md()){
				//	max=neighbor[i];'
					//System.out.println(neighbor[i].h_md());
					//System.out.println(current.h_md());
					if(neighbor[i].objectivefunction()>=h){
						System.out.println(777);
						max=neighbor[i];
					h=neighbor[i].objectivefunction();
				//	System.out.println(5555555);
					}
				}
		}
		if(current.objectivefunction()==h){
			System.out.println(1);
			System.out.println("samilarity");
			goal=current;
			return null;
		}
		HC.remove(current);
		current=max;
		HC.add(current);
		}
		goal=current;
		return null;
		}	

	public Node Astar(){
		    numNodesExpanded = 0;
			Node nodesList[] ;
			Node current = root;
			 initialize_closed();
			PriorityQueue<Node> pq = new PriorityQueue<Node>(new MyComparator());
			pq.add(current);  
			while (!pq.isEmpty()){
			//	current.display();
				mark_as_visited(current);
			System.out.println("******************************");
			current= pq.remove();
			
			if (current.isGoal()) {
      			System.out.println("(&*^&&^*I%%%$%$##@#@$#^%&");
      			//	current.display();
      			goal=current;	
      		System.out.println(current.getbattary());
			return current;	
			}
			nodesList = current.expand();
			for(int i=0;i<5;i++){
				if(nodesList[i]!=null){
					if(!visited(nodesList[i])){
						pq.add(nodesList[i]);
						}
					}
			}
			}
			goal=current;
				return null;
			
	}
	
	public void FinalMap(int s,String d)throws IOException{
      //	File file = new File(ff);
			//FileWriter fileWriter = new FileWriter(file);
			//if(goal!=null )
		switch(s){
		case 1: BFS();goal.printthegoal(d);
		break;
		case 2: Astar(); goal.printthegoal(d);
		break;
		case 3: HillClimbing();goal.printthegoal(d);
		break;
		}
		
		//System.out.println("sids");
      	//		fileWriter.write();
      		
      		//fileWriter.write("\n");

      	
      	//fileWriter.close();
	  }

	// GIVEN THE GOAL NODE, THIS METHOD WILL EXTRACT
	// THE SOLUTION, WHICH IS A SEQUENCE OF ACTIONS.	
	public String[] extractSolution( Node goalNode ) {
				if(goalNode!=null){
		// first find solution length;
		int len=0;	
		Node n = goalNode;
		while (n!=null) {
			n = n.parent;
			len++;
		}
		
		//declare an array of strings
		String sol[] = new String[len-1];

		n = goalNode;
		for (int i=len-2; i>=0; i--) {
			switch (n.getAction()) {
				case 0: sol[i] = new String("move-N");
					break;
				case 1: sol[i] = new String("move-S");
					break;
				case 2: sol[i] = new String("move-W");
					break;
				case 3: sol[i] = new String("move-E");
					break;
				case 4: sol[i] = new String("recharge");
			}
			n = n.parent;
		}
		return sol;	
		}
				return null;
	}

	// THIS METHOD WILL DISPLAY THE SOLUTION
	public void displaySolution(Node goalNode) {
		String sol[] = extractSolution(goalNode);
		if(sol!=null){
		for (int i=0; i<sol.length; i++) 
			System.out.println(sol[i]);
		}else
		System.out.println("NOOP");
	}
	   public void write_the_possible_action(int S,String filename) throws IOException{
		File file = new File(filename);
		FileWriter fileWriter = new FileWriter(file);
		switch(S){
	
	case 1:
		if(BFS()!=null){
			String s[]= extractSolution( BFS());
			for(int i=0 ; i< s.length-1;i++){
				fileWriter.write(s[i]+"\n");	
			}
//			fileWriter.close();
			}
			else 
				fileWriter.write("NOOP"+"\n");	
		fileWriter.close();
		break;
	case 2:if(Astar()!=null){
		String s[]= extractSolution( Astar());
		for(int i=0 ; i< s.length-1;i++){
			fileWriter.write(s[i]+"\n");	
		}
//		fileWriter.close();
		}
		else 
			fileWriter.write("NOOP"+"\n");	
	fileWriter.close();
	break;
		case 3: 
			if(HillClimbing()!=null){
				String s[]= extractSolution( HillClimbing());
				for(int i=0 ; i< s.length-1;i++){
					fileWriter.write(s[i]+"\n");	
				}
//				fileWriter.close();
				}
				else 
					fileWriter.write("NOOP"+"\n");	
			fileWriter.close();
		break;
	
	   }
		}
	
}