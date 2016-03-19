package com.practice.services.shortestpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.practice.model.Graph;
import com.practice.model.Vertex;

public class UnWeightedShortestPathService<T> {

	/**
	 * Shortest distance between source and destination node in directed unweighed graph.
	 * 
	 * Here vertex will be visited only once. So complexity O(V+E)
	 * 
	 * 
	 * @param graph
	 * @param sourceV
	 * @param destinationV
	 * @return
	 */
	public Integer shortedPath(Graph<Integer> graph, Integer sourceV, Integer destinationV) {
		int distance[] = new int[graph.getNumOfVertex()];
		for (int i = 0; i < graph.getVertexList().size(); i++) {
			distance[i] = -1;
		}

		List<Vertex<Integer>> visitedVertexList = new ArrayList<Vertex<Integer>>();
		Vertex<Integer> sourceVertex = new Vertex<Integer>(sourceV);

		Queue<Vertex<Integer>> queue = new LinkedList<Vertex<Integer>>();
		queue.add(sourceVertex);
		distance[graph.getVertexList().indexOf(sourceVertex)] = 0;
		while (!queue.isEmpty()) {
			Vertex<Integer> poppedVertex = queue.poll();
			visitedVertexList.add(poppedVertex);
			List<Vertex<Integer>> adjacentVertexList = graph.getAdjacentNodes(poppedVertex);
			for (int i = 0; i < adjacentVertexList.size(); i++) {
				if (!visitedVertexList.contains(adjacentVertexList.get(i))) {
					queue.add(adjacentVertexList.get(i));
					distance[graph.getVertexList().indexOf(
							adjacentVertexList.get(i))] = distance[graph.getVertexList().indexOf(poppedVertex)] + 1;
				}
			}
		}
		return distance[graph.getVertexList().indexOf(new Vertex<Integer>(destinationV))];
	}
}
