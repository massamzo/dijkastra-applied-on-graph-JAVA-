/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		String valori[] = {"A", "B","C","D","E","F","G","H","I", "J","K"};
		Graph graph = new Graph(valori.length);
		
		graph.createNodesConnection(valori);
		
		/*for(int i=0; i < valori.length; i++){
		    
		    Node nodo = new Node(valori[i]);
		    
		    // connect this node to others in a random way
		    int connectedNodes = 0;
		    int latest = -1;
		    while(connectedNodes < 2){
		        int index = (int)Math.floor(Math.random() * valori.length);
		        if(index != i && index != latest){
		            latest = index;
		            
		            // create node
		            Node n = new Node(valori[index]);
		            int dis = (int)Math.floor(Math.random() * 100 + 1);
		            nodo.setNodes(n, dis);
		            connectedNodes++;
		        }
		        
		    }
		    
		    graph.setNode(nodo);
		}*/
		
		int random_int = (int)Math.floor(Math.random() * 10 + 1);
		
		System.out.println(graph.toString());
	}
}
