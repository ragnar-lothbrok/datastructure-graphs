package com.practice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

	private Integer numOfVertex;
	private Set<Vertex<T>> vertexSet;

	private int adjacentMat[][] = null;
	private Map<Vertex<T>, List<Vertex<T>>> adjacencyListMap = new HashMap<Vertex<T>, List<Vertex<T>>>();

	private boolean isDirectedGraph;

	public Graph(int numOfVertex, boolean isDirectedGraph) {
		this.numOfVertex = numOfVertex;
		this.isDirectedGraph = isDirectedGraph;
		this.adjacentMat = new int[numOfVertex][numOfVertex];
		this.vertexSet = new LinkedHashSet<Vertex<T>>(numOfVertex);
	}

	public void addVertex(T t) {
		adjacencyListMap.put(new Vertex<T>(t), new ArrayList<Vertex<T>>());
		vertexSet.add(new Vertex<T>(t));
	}

	public ArrayList<Vertex<T>> getVertexList() {
		return new ArrayList<Vertex<T>>(this.vertexSet);
	}

	public boolean addEdge(T source, T destination, int weight) {
		Vertex<T> sourceVertex = new Vertex<T>(source);
		Vertex<T> destinationVertex = new Vertex<T>(destination);
		int sourceIndex = getVertexList().indexOf(sourceVertex);
		int destinationIndex = getVertexList().indexOf(destinationVertex);
		adjacentMat[sourceIndex][destinationIndex] = weight;
		adjacencyListMap.get(sourceVertex).add(destinationVertex);
		if (!this.isDirectedGraph) {
			adjacentMat[destinationIndex][sourceIndex] = weight;
			adjacencyListMap.get(destinationVertex).add(sourceVertex);
		}
		return true;
	}

	public Integer getNumOfVertex() {
		return this.numOfVertex;
	}

	public void displayAdjacencyMatrix() {
		for (int i = 0; i < numOfVertex; i++) {
			for (int j = 0; j < numOfVertex; j++) {
				System.out.print(adjacentMat[i][j] + " ");
			}
			System.out.println();
		}
	}

	public Integer getInDegree(Vertex<T> vertex) {
		int count = 0;
		for (int i = 0; i < numOfVertex; i++) {
			if (i != this.getVertexList().indexOf(vertex)
					&& adjacentMat[i][this.getVertexList().indexOf(vertex)] != 0) {
				count++;
			}
		}
		return count;
	}

	public List<Vertex<T>> getAdjacentNodes(Vertex<T> vertex) {
		return this.adjacencyListMap.get(vertex);
	}
}
