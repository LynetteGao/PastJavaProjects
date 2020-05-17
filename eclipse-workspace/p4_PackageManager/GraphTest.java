import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	Graph graph;
	@Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test00_sizeAndOrder() {
    	try {
    		graph = new Graph();
        	graph.addVertex("v1");
        	graph.addVertex("v2");
        	graph.addEdge("v1", "v2");

        	if (graph.order() != 2)
        		fail("Incorrect Order");
        	if (graph.size() != 1)
        		fail("Incorrect Size");

    	} catch (Exception e){
    		fail("Unexpected Exception");
    	}
    }

    @Test
    public void test01_AddVertices() {
    	boolean hasV1 = false;
    	boolean hasV2 = false;
    	try {
    		graph = new Graph();
        	graph.addVertex("v1");
        	graph.addVertex("v2");
        	ArrayList<String> allV = new ArrayList<String>();
        	allV.addAll(graph.getAllVertices());
        	
        	if (!allV.contains("v1") || !allV.contains("v2"))
        		fail("Vertices missed");

    	} catch (Exception e){
    		fail("Unexpected Exception");
    	}
    }
    
    @Test
    public void test02_AddEdges() {
    	boolean hasE1 = false;
    	boolean hasE2 = false;
    	try {
    		graph = new Graph();
        	graph.addVertex("v1");
        	graph.addVertex("v2");
        	graph.addVertex("v3");
        	graph.addEdge("v1", "v2");
        	graph.addEdge("v1", "v3");
        	ArrayList<String> adjV = (ArrayList)graph.getAdjacentVerticesOf("v1");
        	
        	assertEquals(adjV.size(), 2);
        	if (!adjV.contains("v2") || !adjV.contains("v3"))
        		fail("Edges missed");

    	} catch (Exception e){
    		fail("Unexpected Exception");
    	}
    }
    
    @Test
    public void test03_RemoveVertices() {
    	try {
    		graph = new Graph();
        	graph.addVertex("v1");
        	graph.addVertex("v2");
        	graph.addVertex("v3");
        	graph.removeVertex("v1");
        	graph.removeVertex("v3");
        	ArrayList<String> allV = new ArrayList<String>();
        	allV.addAll(graph.getAllVertices());
        	
        	if (allV.contains("v1") || allV.contains("v3"))
        		fail("Vertices failed to be removed");


    	} catch (Exception e){
    		fail("Unexpected Exception");
    	}
    }

    @Test
    public void test04_RemoveEdges() {
    	boolean hasE1 = false;
    	boolean hasE2 = false;
    	try {
    		graph = new Graph();
        	graph.addVertex("v1");
        	graph.addVertex("v2");
        	graph.addVertex("v3");
        	graph.addVertex("v4");
        	graph.addEdge("v1", "v2");
        	graph.addEdge("v1", "v3");
        	graph.addEdge("v3", "v4");
        	graph.removeEdge("v1", "v3");
        	ArrayList<String> adjV = (ArrayList)graph.getAdjacentVerticesOf("v1");

        	if (adjV.contains("v3") || adjV.contains("v4"))
        		fail("Edges failed to be removed");
        	
        	if (!adjV.contains("v2"))
        		fail("Wrong edge removed");
        	
    	} catch (Exception e){
    		fail("Unexpected Exception");
    	}
    }
}
