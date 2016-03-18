package com.practice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.practice.model.Graph;
import com.practice.model.Vertex;

public class WeightedShortestPathServiceUsingPQ<T> {

	/**
	 * Shortest distance between source and destination node in directed weighed
	 * graph. Here we will initialize distance array to Maximum value because
	 * here A vertex can be visited multiple times.
	 * 
	 * But here condition is there should not be any negative edges.
	 * 
	 * Here vertex will be visited only once. So complexity O(E LogV)
	 * 
	 * 
	 * @param graph
	 * @param sourceV
	 * @param destinationV
	 * @return
	 */
	public Integer shortestPath(Graph<T> graph, T sourceV, T destinationV) {
		Vertex<T> sourceVertex = new Vertex<T>(sourceV);
		Vertex<T> destinationVertex = new Vertex<T>(destinationV);
		Map<Vertex<T>, DistanceInfo<T>> vertexDistanceInfoMap = new HashMap<Vertex<T>, DistanceInfo<T>>();
		for (int i = 0; i < graph.getVertexList().size(); i++) {
			vertexDistanceInfoMap.put(graph.getVertexList().get(i), new DistanceInfo<T>());
		}
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		queue.add(sourceVertex);
		vertexDistanceInfoMap.get(sourceVertex).setDistance(0);

		while (!queue.isEmpty()) {
			Vertex<T> poppedVertex = queue.poll();
			List<Vertex<T>> adjacentVertexList = graph.getAdjacentNodes(poppedVertex);
			for (int i = 0; i < adjacentVertexList.size(); i++) {
				int updatedDistance = vertexDistanceInfoMap.get(poppedVertex).getDistance()
						+ graph.getAdjacentMat()[graph.getVertexList().indexOf(poppedVertex)][graph.getVertexList()
								.indexOf(adjacentVertexList.get(i))];
				if (updatedDistance < vertexDistanceInfoMap.get(adjacentVertexList.get(i)).getDistance()) {
					queue.add(adjacentVertexList.get(i));
					vertexDistanceInfoMap.get(adjacentVertexList.get(i)).setDistance(updatedDistance);
					vertexDistanceInfoMap.get(adjacentVertexList.get(i)).setNeighbourVertex(poppedVertex);
				}
			}
		}
		
		List<Vertex<T>> shortestPathNodes = new ArrayList<Vertex<T>>();
		Vertex<T> tempVertex = destinationVertex;
		while(sourceVertex != tempVertex){
			shortestPathNodes.add(tempVertex);
			tempVertex = vertexDistanceInfoMap.get(tempVertex).getNeighbourVertex();
		}
		shortestPathNodes.add(sourceVertex);
		Collections.reverse(shortestPathNodes);
		System.out.println("####Shortest Path : "+shortestPathNodes);
		return vertexDistanceInfoMap.get(new Vertex<T>(destinationV)).getDistance();
	}

	static class DistanceInfo<T> {
		private Integer distance = Integer.MAX_VALUE;
		private Vertex<T> neighbourVertex;

		public Integer getDistance() {
			return distance;
		}

		public void setDistance(Integer distance) {
			this.distance = distance;
		}

		public Vertex<T> getNeighbourVertex() {
			return neighbourVertex;
		}

		public void setNeighbourVertex(Vertex<T> neighbourVertex) {
			this.neighbourVertex = neighbourVertex;
		}

		public DistanceInfo(Integer distance, Vertex<T> neighbourVertex) {
			super();
			this.distance = distance;
			this.neighbourVertex = neighbourVertex;
		}

		@Override
		public String toString() {
			return "DistanceInfo [distance=" + distance + ", neighbourVertex=" + neighbourVertex + "]";
		}

		public DistanceInfo() {
		}

	}
}
