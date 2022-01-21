
import java.util.*;

public class GraphSSSP {

    private int numNodes;
    private int numEdges;
    private int[][] edges; // edge list
    private Neighbour[][] adjList; //adjacency list

    public final int UVW = 3;
    public final int INFINITE = Integer.MAX_VALUE;

    /*
    ----------------- IN
    n m q s
    4 3 4 0
    0 1 2
    1 2 2
    3 0 2
    0
    1
    2
    3
    2 1 1 0
    0 1 100
    1
    0 0 0 0
    ----------------- OUT
    0
    2
    4
    Impossible

    100
    -----------------
    */

    //edge list has size (m row, 3 col) 
    //m = numEdges
    //  u   v   w
    //  u   v   w
    //  u   v   w
    //  u   v   w
    //  .   .   .
    //  .   .   .
    //  .   .   .
    //if a row in the edgelist represents an edge from node u to Node v with weight w,
    //then that row is [u,v,w]
    public GraphSSSP(int numNodes, int numEdges, int[][] edges){
        //call the buildAdjList method ...
        this.buildAdjList();
    }

    private void buildAdjList(){
        //use the edge list stored in edges to allocate and fill adjList
        //The adjacency list has numNodes rows, and the number of entries in each row is equal
        //to the out degree of the node with the same index.
        int m = this.edges.length;
        if(m == 0)
            return;
        else{
            adjList = new Neighbour[numNodes][UVW];
            int u = 0;
            int v = 0;
            int w = 0;
            int j = 0;
            for(int i = 0; i < m; i++){
                j = 0;
                u = edges[i][j];
                v = edges[i][j+1];
                w = edges[i][j+2];
                adjList[u][v] = new Neighbour(v, w); 
            }
        }
    }
    public int[] dijkstra(int startNode){

        //PriorityQueue<Entry> pq = new PriorityQueue<>();
        //PriorityQueue<Neighbour> pq = new PriorityQueue<>();
        int numNodes = this.numNodes;
        boolean[] visited = new boolean[numNodes];
        int[] dist = new int[numNodes];

        for(int i = 0; i < numNodes;i++){
            visited[i] = false;
            dist[i] = INFINITE;
        }
        dist[startNode] = 0;
        //pq.add(new Entry(startNode, 0));
        //pq.add(new Neighbour(startNode, 0));

        for(int i = 0; i < dist.length; i++){
            int u = getMinDist(dist,visited);
            visited[u] = true;
            for(int j = 0; j < dist.length; j++){
                if(!(visited[j]) && this.adjList[u][j].getWeight() != 0 && dist[u] != INFINITE && dist[u] + this.adjList[u][j].getWeight() < dist[u]){
                    dist[j] = dist[u] + this.adjList[u][j].getWeight();
                }   
            } 
        }
    }

    private int getMinDist(int d[], boolean v[]){
        int min = INFINITE;
        int index = -1;

        for(int i = 0; i < d.length; i++){
            if(d[i] <= min && v[i] == false){
                index = i;
                min = d[i];
            }
        }
        return index;
        
    }






//==========================================================================================
    class Neighbour{
        private int nodeID;//identifies which neighbor of x this is
        private int weight;//the weight of the edge from x to the neighbor

        public Neighbour(int nodeID, int weight){
            this.nodeID = nodeID;
            this.weight = weight;
        }
        public int getNodeID(){
            return this.nodeID;
        }
        public int getWeight(){
            return this.weight;
        }
    }
//==========================================================================================
    class Entry implements Comparable<Entry>{
        private int nodeID;
        private int dist;

        public Entry(int nodeID, int dist){
            this.nodeID = nodeID;
            this.dist = dist;
        }
        public int getNodeID(){
            return this.nodeID;
        }
        public int getDist(){
            return this.dist;
        }
        @Override
        public int compareTo(Entry e){
            if(this.dist < e.dist)
                return -1;
            else if (this.dist > e.dist)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) throws Exception{
        boolean executing = true;
        Scanner sc = new Scanner(System.in);
        while(executing){
            String in = sc.nextLine();
            if(in.equals("\n")){
                in = sc.nextLine(); 
            }
            if(in.equals("0 0 0 0")){
                return;
            }
            String[] inArr = in.split(" ");
        
        }
    }
}

