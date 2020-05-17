import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * Filename: Graph.java Project: p4 Authors:
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

  ArrayList<Graphnode<String>> vertices;
  int size;
  int order;

  @SuppressWarnings("hiding")
  class Graphnode<String> {
    private String data;
    private boolean visited;
    private ArrayList<Graphnode<String>> neighbors;

    // constructor
    Graphnode(String value) {
      this.data = value;
      this.visited = false;
      neighbors = new ArrayList<Graphnode<String>>();
    }
    private String getData() {
      return this.data;
    }
    private ArrayList<Graphnode<String>> getNeighbors(){
      return this.neighbors;
    }
    private boolean isVisited() {
      return visited;
    }
    

  }

  /*
   * Default no-argument constructor
   */
  public Graph() {
    this.vertices = new ArrayList<Graphnode<String>>();
    size = 0;
    order = 0;
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    if (vertex == null || getNode(vertex) != null) {
      return;
    } else {
      Graphnode<String> node = new Graphnode<String>(vertex);
      vertices.add(node);
      this.order++;

    }
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    if (vertex == null || getNode(vertex) == null) {
      return;
    } else {
      vertices.remove(getNode(vertex));
      this.order--;

    }
  }


  /**
   * getter for a node
   */
  private Graphnode<String> getNode(String vertex) {
    for (int i = 0; i < vertices.size(); ++i) {
      if (vertices.get(i).data.equals(vertex)) {
        return vertices.get(i);
      }
    }
    return null;
  }


  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, no edge is added and no exception is thrown. If the edge exists in the
   * graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null)
      return;

    Graphnode<java.lang.String> v1 = this.getNode(vertex1);
    Graphnode<java.lang.String> v2 = this.getNode(vertex2);

    if (v1 == null && v2 == null)
      return;
    else if (v1 == null && v2 != null)
      addVertex(vertex1);
    else if (v2 == null && v1 != null)
      addVertex(vertex2);

    if (getNode(vertex1).neighbors != null && getNode(vertex1).neighbors.contains(getNode(vertex2))) {
      return;
    }

    getNode(vertex1).neighbors.add(getNode(vertex2));
    this.size++;



  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 == null || vertex2 == null)
      return;

    Graphnode<java.lang.String> v1 = this.getNode(vertex1);
    Graphnode<java.lang.String> v2 = this.getNode(vertex2);
    if (v1 == null || v2 == null)
      return;

    if (!v1.neighbors.contains(v2)) {
      return;
    }

    v1.neighbors.remove(v2);
    this.size--;

  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    Set<String> a = new HashSet<String>();
    for (int i = 0; i < vertices.size(); ++i) {
      a.add(vertices.get(i).data);
    }
    return a;
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    Graphnode<String> a = getNode(vertex);
    List<String> adjacencyList = new ArrayList<String>();
    for (int i = 0; i < a.neighbors.size(); ++i) {
      adjacencyList.add(a.neighbors.get(i).data);
    }
    return adjacencyList;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return this.order;
  }
  
//  private void resetVisit() {
//    for (int i=0; i<vertices.size(); i++) {
//        vertices.get(i).visited = false;
//    }
//  }  
//   private void setVisited(String name,boolean visited) {
//      getNode(name).visited = visited;
//    }



}
