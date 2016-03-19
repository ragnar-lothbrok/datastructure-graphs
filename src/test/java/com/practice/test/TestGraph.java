package com.practice.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.practice.model.Graph;
import com.practice.model.Vertex;
import com.practice.services.TopologicalSort;
import com.practice.services.shortestpath.UnWeightedShortestPathService;
import com.practice.services.shortestpath.WeightedShortestPathService;
import com.practice.services.shortestpath.WeightedShortestPathServiceWithPath;
import com.practice.services.shortestpath.WeightedShortestPathServiceWithPathUsingPQ;

public class TestGraph {

	Graph<Integer> unweightedGraph = null;
	Graph<Integer> weightedGraph = null;

	@Before
	public void test() {
		unweightedGraph = new Graph<Integer>(6, true);

		unweightedGraph.addVertex(0);
		unweightedGraph.addVertex(1);
		unweightedGraph.addVertex(2);
		unweightedGraph.addVertex(3);
		unweightedGraph.addVertex(4);
		unweightedGraph.addVertex(5);

		unweightedGraph.addEdge(5, 2, 1);
		unweightedGraph.addEdge(4, 1, 1);
		unweightedGraph.addEdge(2, 3, 1);
		unweightedGraph.addEdge(3, 1, 1);
		unweightedGraph.addEdge(5, 0, 1);
		unweightedGraph.addEdge(4, 0, 1);

		// Weighted Graph

		weightedGraph = new Graph<Integer>(5, true);

		weightedGraph.addVertex(1);
		weightedGraph.addVertex(2);
		weightedGraph.addVertex(3);
		weightedGraph.addVertex(4);
		weightedGraph.addVertex(5);

		weightedGraph.addEdge(1, 2, 2);
		weightedGraph.addEdge(1, 3, 3);
		weightedGraph.addEdge(3, 5, 6);
		weightedGraph.addEdge(5, 4, 4);
		weightedGraph.addEdge(2, 4, 14);
		weightedGraph.addEdge(5, 2, 2);
	}

	@Test
	public void testTopologicalSort() {
		TopologicalSort<Integer> topologicalSort = new TopologicalSort<Integer>();
		List<Vertex<Integer>> sortedList = topologicalSort.getTopologicalSortingOrder(unweightedGraph);
		System.out.println(sortedList);
	}

	@Test
	public void shortestDistance() {
		Integer sourceV = 5;
		Integer destinationV = 1;
		UnWeightedShortestPathService<Integer> unWeightedShortestPathService = new UnWeightedShortestPathService<Integer>();
		System.out.println("Distance between S[" + sourceV + "] and D[" + destinationV + "] is : "
				+ unWeightedShortestPathService.shortedPath(unweightedGraph, sourceV, destinationV));
	}

	@Test
	public void shortestDistanceWeighted() {
		Integer sourceV = 1;
		Integer destinationV = 4;
		WeightedShortestPathService<Integer> weightedShortestPathService = new WeightedShortestPathService<Integer>();
		System.out.println("[Weighted Only Distance]Distance between S[" + sourceV + "] and D[" + destinationV + "] is : "
				+ weightedShortestPathService.shortedPath(weightedGraph, sourceV, destinationV));
	}
	
	@Test
	public void shortestDistanceWeightedPlusPath() {
		Integer sourceV = 1;
		Integer destinationV = 4;
		WeightedShortestPathServiceWithPath<Integer> weightedShortestPathService = new WeightedShortestPathServiceWithPath<Integer>();
		System.out.println("[Weighted + PATH]Distance between S[" + sourceV + "] and D[" + destinationV + "] is : "
				+ weightedShortestPathService.shortestPath(weightedGraph, sourceV, destinationV));
	}
	
	@Test
	public void shortestDistanceWeightedPQ() {
		Integer sourceV = 1;
		Integer destinationV = 4;
		WeightedShortestPathServiceWithPathUsingPQ<Integer> weightedShortestPathService = new WeightedShortestPathServiceWithPathUsingPQ<Integer>();
		System.out.println("[Weighted + PATH PQ]Distance between S[" + sourceV + "] and D[" + destinationV + "] is : "
				+ weightedShortestPathService.shortestPath(weightedGraph, sourceV, destinationV));
	}

}
