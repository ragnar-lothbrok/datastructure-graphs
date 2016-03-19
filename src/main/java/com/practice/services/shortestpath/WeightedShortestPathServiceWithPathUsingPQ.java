package com.practice.services.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.practice.model.Graph;
import com.practice.model.Vertex;

public class WeightedShortestPathServiceWithPathUsingPQ<T> {

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

		PriorityQueue<VertexInfo<T>> queue = new PriorityQueue<VertexInfo<T>>();

		VertexInfo<T> sourceVertexInfo = new VertexInfo<T>(0, sourceVertex);
		queue.add(sourceVertexInfo);
		vertexDistanceInfoMap.get(sourceVertex).setDistance(0);

		while (!queue.isEmpty()) {
			VertexInfo<T> poppedVertexInfo = queue.poll();
			List<Vertex<T>> adjacentVertexList = graph.getAdjacentNodes(poppedVertexInfo.getVertex());
			for (int i = 0; i < adjacentVertexList.size(); i++) {
				int updatedDistance = vertexDistanceInfoMap.get(poppedVertexInfo.getVertex()).getDistance()
						+ graph.getAdjacentMat()[graph.getVertexList().indexOf(poppedVertexInfo.getVertex())][graph
								.getVertexList().indexOf(adjacentVertexList.get(i))];
				if (updatedDistance < vertexDistanceInfoMap.get(adjacentVertexList.get(i)).getDistance()) {
					VertexInfo<T> tempVertexInfo = new VertexInfo<T>(updatedDistance, adjacentVertexList.get(i));
					if (queue.contains(tempVertexInfo)) {
						queue.remove(tempVertexInfo);
					}
					queue.add(tempVertexInfo);
					vertexDistanceInfoMap.get(adjacentVertexList.get(i)).setDistance(updatedDistance);
					vertexDistanceInfoMap.get(adjacentVertexList.get(i))
							.setNeighbourVertex(poppedVertexInfo.getVertex());
				}
			}
		}

		List<Vertex<T>> shortestPathNodes = new ArrayList<Vertex<T>>();
		Vertex<T> tempVertex = destinationVertex;
		while (sourceVertex != tempVertex) {
			shortestPathNodes.add(tempVertex);
			tempVertex = vertexDistanceInfoMap.get(tempVertex).getNeighbourVertex();
		}
		shortestPathNodes.add(sourceVertex);
		Collections.reverse(shortestPathNodes);
		System.out.println("####Shortest Path : " + shortestPathNodes);
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

	static class VertexInfo<T> implements Comparable<VertexInfo<T>> {
		private Integer distance = Integer.MAX_VALUE;
		private Vertex<T> vertex;

		public Integer getDistance() {
			return distance;
		}

		public void setDistance(Integer distance) {
			this.distance = distance;
		}

		public Vertex<T> getVertex() {
			return vertex;
		}

		public void setCurrentVertex(Vertex<T> currentVertex) {
			this.vertex = currentVertex;
		}

		public VertexInfo(Integer distance, Vertex<T> currentVertex) {
			super();
			this.distance = distance;
			this.vertex = currentVertex;
		}

		public int compareTo(VertexInfo<T> o) {
			return o.getDistance().compareTo(this.getDistance());
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			VertexInfo<T> other = (VertexInfo<T>) obj;
			if (vertex == null) {
				if (other.vertex != null)
					return false;
			} else if (!vertex.equals(other.vertex))
				return false;
			return true;
		}

	}
}
