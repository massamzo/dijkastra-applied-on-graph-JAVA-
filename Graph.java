import java.util.ArrayList;

class Graph{
    
    private Node nodes[];
    private Node unvisited[];
    private Node visited[];
    private Node previous[];
    private Node copyNodes[];
    private Double distances[];
    
    public Graph(int length){
        nodes = new Node[length];
    }
    
    public Node getNode(int i){
        return nodes[i];
    }
    
    public void setNode(Node n){
        for(int i=0; i < nodes.length; i++){
            if(nodes[i] == null){
                nodes[i] = n;
                break;
            }
        }
    }
    
    public String toString(){
        String txt = "\n--------  START GRAPH ---------\n";
        for(int i=0; i< nodes.length; i++){
            txt += nodes[i].toString();
        }
        
        txt += "\n--------- END GRAPH ---------\n";
        
        return txt;
    }
    
    // aggiustare ciclo infinito
    public void createNodesConnection(String[] valori){
        
        // fill the nodes array
        for(int i=0; i < valori.length; i++){
            Node n = new Node(valori[i]);
            setNode(n); 
        }
        
        // make the connections between them
        for(int i=0; i < nodes.length; i++){
            
            Node nodo = nodes[i];
            int latest = -1;
            
            while(nodo.getSize() < 2){
                int index = (int)Math.floor(Math.random() * nodes.length);
                if(index != i && latest != index){
                    
                    Node n2 = nodes[index];
                    System.out.println(n2.getSize());
                    if(n2.getSize() < 2){
                        System.out.println("trovatissimo");
                        int dis = (int)Math.floor(Math.random() * 100 + 1);
                        // sostituisco i campi di entrambi
                        
                        nodo.setNodes(n2, dis);
                        n2.setNodes(nodo, dis);
                        
                        latest = index;
                        
                    }
                }
            }
        }
    }
    
    private void copyArray(){
        unvisited = nodes.clone();
        distances = new Double[nodes.length];
    }
    
    private Node findStartNode(String start){
        for(int i=0; i < nodes.length; i++){
            if(nodes[i].getValue().equals(start)){
                return nodes[i];
            }
        }
        
        return nodes[0];
    }
    
    public void st(){
        for(int i=0; i < nodes.length; i++){
            System.out.println(nodes[i].toString());
        }
    }
    
    private void arrangeOrder(String start){
        
        copyArray();
        
        visited = new Node[unvisited.length];
        previous = new Node[unvisited.length];
        copyNodes = nodes.clone();
        // define the distances
        distances[0] = 0.0;
        for(int i=1; i < distances.length; i++){
            distances[i] = Double.POSITIVE_INFINITY;
        }
        
        Node st = findStartNode(start);
        
        // arrange array
        
        for(int i=0; i < unvisited.length; i++){
            if(unvisited[i].equals(st)){
                if(i==0){
                    break;
                }else{
                    Node temp = unvisited[0];
                    unvisited[0] = unvisited[i];
                    unvisited[i] = temp;
                    
                    Node n = copyNodes[0];
                    copyNodes[0] = copyNodes[i];
                    copyNodes[i] = n;
                    
                }
            }
        }
        
        
        
    }
    
    private int shortestDistance(int stIndex){
        Double min = Double.POSITIVE_INFINITY;
        int index = 0;
        for(int i=stIndex; i < distances.length; i++){
            if(distances[i] < min && unvisited[i] != null){
                min = distances[i];
                index = i;
            }
        }
        
        return index;
    }
    
    
    private boolean isInVisited(Node n){
        
        for(int i=0; i < visited.length; i++){
            if(visited[i] != null){
                if(visited[i].equals(n)){
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    private void updateDistance(Double d, String val, Node node){
        int ind = 0;
        for(int i=0; i < unvisited.length; i++){
           if(unvisited[i] != null){
                if(unvisited[i].getValue() == val){
                    ind = i;
                    break;
                }
           }
            
        }
        
        if(d < distances[ind]){
            distances[ind] = d;
            previous[ind] = node;
        }
        
    }
    
    private void addIntoVisited(Node n){
        for(int i=0; i < visited.length; i++){
            if(visited[i] == null){
                visited[i] = n;
                break;
            }
        }
        
        for(int i=0; i < unvisited.length; i++){
            
            if(unvisited[i] != null){
                if(unvisited[i].equals(n)){
                    unvisited[i] = null;
                    break;
                }
            }
        }
    }
    
    private void visitNode(Node node, int ind){
        ArrayList<Node> connected = node.getConnectedNodes();
        ArrayList<Integer> connDis = node.getDistanza();
        
        for(int i=0; i < connected.size(); i++){
            Node n = connected.get(i);
            if(!isInVisited(n)){
                
                // update the visited and unvisited along with distances and previous
                
                Double d = distances[ind]+connDis.get(i);
                
                //check if the distance is smaller than the value, update previous node as well
                
                
                updateDistance(d, n.getValue(), node);
                
                // sign this node as visited and remove from visited
                
            }
        }
        addIntoVisited(node);
    }
    
    
    private void showUnvisited(){
        System.out.println("\n----- UNVISITED ----\n");
        for(int i=0; i < unvisited.length; i++){
            if(unvisited[i] != null){
                System.out.println(unvisited[i].getValue());
            }else{
                System.out.println("null");
            }
        }
    }
    
    private void showVisited(){
        System.out.println("\n----- VISITED ----\n");
        for(int i=0; i < visited.length; i++){
            if(visited[i] != null){
                System.out.println(visited[i].getValue());
            }else{
                System.out.println("null");
            }
        }
    }
    
    
    private void showPrevious(){
        System.out.println("\n----- PREVIOUS ----\n");
        for(int i=0; i < previous.length; i++){
            if(previous[i] != null){
                System.out.println(previous[i].getValue());
            }else{
                System.out.println("null");
            }
        }
    }
    
    
    private void showDistances(){
        System.out.println("\n----- DISTANCES ----\n");
        for(int i=0; i < distances.length; i++){
            System.out.println(distances[i]);
        }
    }
    
    private boolean unvisitedIsNotEmpty(){
        for(int i=0; i < unvisited.length; i++){
            if(unvisited[i] != null){
                return true;
            }
        }
        
        return false;
    }
    
    private void distanceCreator(String start, String end){
        
      
        int seen = 0;
        while(unvisitedIsNotEmpty()){
            int minValueNode = shortestDistance(seen);
            if(unvisited[minValueNode] != null){
                System.out.println(unvisited[minValueNode].getValue());
                visitNode(unvisited[minValueNode], minValueNode);
                
                if(seen == 0){
                    seen = 1;
                }
            }
            
        }
    }
    
    // using copynodes (clone of unvisited) because unvisited at the end will contain null values only
    private int findNewEnd(String end){
      
        for(int i=0; i < copyNodes.length; i++){
            if(copyNodes[i] != null){
               if(copyNodes[i].getValue().equals(end)){
                   return i;
               }
            }else{
                System.out.println("null");
            }
        }
        
        return -1;
    }
    
    private String findPath(String start, String end){
        
        if(start.equals(end)){
            return end;
        }
        
        String newEnd = previous[findNewEnd(end)].getValue();
        
        return findPath(start, newEnd)+" ---> "+end;
    }
    
    
    
    public String shortestPath(String start, String end){
        arrangeOrder(start);
        
        showUnvisited();
        showVisited();
        showDistances();
        showPrevious();
        
        distanceCreator(start, end);
        
        showUnvisited();
        showVisited();
        showDistances();
        showPrevious();
        
        
        return findPath(start, end);
    }
    
}