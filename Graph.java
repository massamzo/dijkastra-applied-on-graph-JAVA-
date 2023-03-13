class Graph{
    
    private Node nodes[];
    
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
    
}