// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.util.*;

class Node{
	private State state;	// the state
	Node parent;	// the parent node
	private int action;	// the number of the action 
				// that lead to this state
	private int path_cost;	// the cost spent so far to reach this node
	private int depth;	// the depth of the node in the tree
	
	// CONSTRUCTOR :
	// THIS CONSTRUCTOR WILL CREATE A NODE GIVEN A STATE
	Node( State st, Node pa, int a, int c, int d){
		state = st;	
		parent = pa;
		setAction(a);
		path_cost = c;
		depth = d;
	}
/*	Node( Node f){
		state = f.state;	
		parent =f.parent;
		setAction(f.action);
		path_cost = f.path_cost;
		depth = f.depth;
	}
	*/
	
	// THIS METHOD RETURNS TRUE IS THE 
	// NODE'S STATE IS THE SAME AS THE OTHER NODE'S STATE
	public boolean hasSameState(Node n) {
		return (state.equal(n.state));
	}
	public int getbattary(){
		return state.battery;
	}
	
	
 	// THIS METHOD WILL RETURN THE NEIGHBORING NODES 
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
	public Node[] expand() {
		int nb=0;
		int temp;
		int temp2;
		Node next_nodes[] = new Node[5];	// there are 5 actions
		State next_states[] = state.successors();
		for (int i=0; i<5; i++) {		// create nodes
			if (next_states[i]!=null){
				next_nodes[nb++]=new Node (next_states[i],this,i,path_cost+1,depth+1);
		}
		}
		return next_nodes;
		
	}
	// GOAL TEST: THIS WILL TELL 
	// WHETHER THE NODE'S STATE IS A GOAL.	
	public boolean isGoal() {
		return state.foundTreasure();
	}
	
	// MANHATTAN DISTANCE HEURISTIC
	  public int h_md() {
			int huerstic = Integer.MAX_VALUE;
		//  state.getthegoal();
			///int s=0;
			//int k=0;
			//int w;
		 // while (!state.goalx.isEmpty()&&(!state.goaly.isEmpty())){
			  
			 int  w=   Math.abs(state.getX()-state.g1)+Math.abs(state.getY()-state.g2);	
			 return w;
			//  if(w<huerstic)
				//	 huerstic=w;
					}
				//	return huerstic;
					//}
		 
	//	int huerstic = Integer.MAX_VALUE;
		//for(int i =0 ;i<state.xx.length;i++){
	   // w=   Math.abs(state.getX()-state.xx[i])+Math.abs(state.getY()-state.yy[i]);		
     	 
	public int objectivefunction(){
		return 500-h_md();
	}
	public int  Calculater(){
			return h_md()+path_cost;
	}
	// DISPLAY THE NODE'S INFO
	public void display() {
		state.display();
		System.out.println("path cost is " + path_cost);
		System.out.println("num of action "+getAction() );
		System.out.println("the depth "+depth);
		
		// ...
		
	}
	public void  printthegoal(String d) throws IOException {
		state.FinalMap(d);
	}
	//public Node  tryto(){
	//return	state.display();
	//}
	public int getAction() {
		return action;
	}


	public void setAction(int action) {
		this.action = action;
	}
}
