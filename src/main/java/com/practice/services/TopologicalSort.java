package com.practice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.practice.model.Graph;
import com.practice.model.Vertex;

public class TopologicalSort<T> {

	/**
	 * In this first we are searching for the vertexes whose InDegree in zero
	 * and inserting them in Queue.
	 * 
	 * We will be popping vertexes from Queue and then decrease the InDegrees of
	 * Adjacent Vertexes.
	 * 
	 * In last if number of popped vertex size is not matching with vertex count
	 * then there must be a cycle.
	 * 
	 * O(V+E)
	 * 
	 * TODO : Find all possible Topological sorts.
	 * 
	 * @param graph
	 * @return
	 */

	public List<Vertex<T>> getTopologicalSortingOrder(Graph<T> graph) {
		List<Vertex<T>> topologicalSortedList = new ArrayList<Vertex<T>>();
		Map<Vertex<T>, Integer> vertexInDegreeMap = new HashMap<Vertex<T>, Integer>();
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		for (int i = 0; i < graph.getVertexList().size(); i++) {
			if (graph.getInDegree(graph.getVertexList().get(i)) != 0) {
				vertexInDegreeMap.put(graph.getVertexList().get(i), graph.getInDegree(graph.getVertexList().get(i)));
			} else {
				queue.add(graph.getVertexList().get(i));
			}
		}

		while (!queue.isEmpty()) {
			Vertex<T> poppedV = queue.poll();
			topologicalSortedList.add(poppedV);
			List<Vertex<T>> adjacentVetexes = graph.getAdjacentNodes(poppedV);
			for (int i = 0; i < adjacentVetexes.size(); i++) {
				int updatedInDegree = vertexInDegreeMap.get(adjacentVetexes.get(i)) - 1;
				if (updatedInDegree == 0) {
					vertexInDegreeMap.remove(adjacentVetexes.get(i));
					queue.add(adjacentVetexes.get(i));
				} else {
					vertexInDegreeMap.put(adjacentVetexes.get(i), updatedInDegree);
				}
			}
		}

		if (topologicalSortedList.size() != graph.getNumOfVertex()) {
			System.out.println("Cycle is present.");
			return null;
		}
		return topologicalSortedList;
	}

}
