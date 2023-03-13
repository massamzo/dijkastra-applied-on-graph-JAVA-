import java.util.ArrayList;

class Node{
    private String value;
     private ArrayList<Node> connectedNodes;
    private ArrayList<Integer> distanza;
    
    
    public Node(String value){
        this.value = value;
         connectedNodes = new ArrayList<Node>();
         distanza = new ArrayList<Integer>();
    }
    
    public int getSize(){
        return connectedNodes.size();
    }
    public ArrayList getConnectedNodes(){
        return connectedNodes;
    }
    
    
    
    public void setNodes(Node n, int d){
        distanza.add(d);
        connectedNodes.add(n);
    }
    
    
    public String toString(){
        String txt =  "node : "+value;
        txt+= "\nconnected to : ";
        for(int i=0; i < connectedNodes.size(); i++){
            txt += connectedNodes.get(i).value+" , ";
        }
        txt += "\ndistance : ";
        for(int i=0; i < distanza.size(); i++){
            txt += distanza.get(i)+" , ";
        }
        
        txt += "\n\n----------\n\n";
        
        return txt;
    }
}