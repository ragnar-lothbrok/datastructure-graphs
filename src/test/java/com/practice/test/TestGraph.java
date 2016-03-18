package com.practice.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.practice.model.Graph;
import com.practice.model.Vertex;
import com.practice.services.TopologicalSort;
import com.practice.services.UnWeightedShortestPathService;

public class TestGraph {

	Graph<Integer> graph = null;

	@Before
	public void test() {
		graph = new Graph<Integer>(6, true);

		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);

		graph.addEdge(5, 2, 1);
		graph.addEdge(4, 1, 1);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(5, 0, 1);
		graph.addEdge(4, 0, 1);
	}

	@Test
	public void testTopologicalSort() {
		TopologicalSort<Integer> topologicalSort = new TopologicalSort<Integer>();
		List<Vertex<Integer>> sortedList = topologicalSort.getTopologicalSortingOrder(graph);
		System.out.println(sortedList);
	}
	
	@Test
	public void shortestDistance(){
		Integer sourceV = 5;
		Integer destinationV = 1;
		UnWeightedShortestPathService<Integer> unWeightedShortestPathService = new UnWeightedShortestPathService<Integer>();
		System.out.println("Distance between S["+sourceV+"] and D["+destinationV+"] is : "+unWeightedShortestPathService.shortedPath(graph, sourceV, destinationV));
	}

}
