/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		String valori[] = {"A", "B","C","D","E","F","G","H","I"};
		Graph graph = new Graph(valori.length);
		
		String start = "E";
		String end = "A";
	
		graph.createNodesConnection(valori);
		System.out.println(graph.toString());
	  
	    System.out.println(graph.shortestPath(start,end));
	}
}