package com.practice.services.shortestpath;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.practice.model.Graph;
import com.practice.model.Vertex;

public class WeightedShortestPathService<T> {

	/**
	 * Shortest distance between source and destination node in directed weighed
	 * graph. Here we will initialize distance array to Maximum value because
	 * here A vertex can be visited multiple times.
	 * 
	 * But here condition is there should not be any negative edges.
	 * 
	 * Here vertex will be visited only once. So complexity O(E + V^2)
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
			distance[i] = Integer.MAX_VALUE;
		}

		Vertex<Integer> sourceVertex = new Vertex<Integer>(sourceV);

		Queue<Vertex<Integer>> queue = new LinkedList<Vertex<Integer>>();
		queue.add(sourceVertex);
		distance[graph.getVertexList().indexOf(sourceVertex)] = 0;
		int count =0;
		while (!queue.isEmpty()) {
			count++;
			Vertex<Integer> poppedVertex = queue.poll();
			List<Vertex<Integer>> adjacentVertexList = graph.getAdjacentNodes(poppedVertex);
			for (int i = 0; i < adjacentVertexList.size(); i++) {
				int updatedDistance = distance[graph.getVertexList().indexOf(poppedVertex)]
						+ graph.getAdjacentMat()[graph.getVertexList().indexOf(poppedVertex)][graph.getVertexList()
								.indexOf(adjacentVertexList.get(i))];
				if (updatedDistance < distance[graph.getVertexList().indexOf(adjacentVertexList.get(i))]) {
					queue.add(adjacentVertexList.get(i));
					distance[graph.getVertexList().indexOf(adjacentVertexList.get(i))] = updatedDistance;
				}
			}
		}
		System.out.println("###"+count);
		return distance[graph.getVertexList().indexOf(new Vertex<Integer>(destinationV))];
	}
}
