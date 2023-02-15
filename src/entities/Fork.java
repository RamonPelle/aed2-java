package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Fork<TYPE> {
	private ArrayList<Vertex<TYPE>> vertexes;
	private ArrayList<Edge<TYPE>> edges;

	public Fork() {
		this.vertexes = new ArrayList<Vertex<TYPE>>();
		this.edges = new ArrayList<Edge<TYPE>>();
	}

	public void addVertex(TYPE data) {
		if (this.vertexes.size() < 20) {
			Vertex<TYPE> newVertex = new Vertex<TYPE>(data);
			this.vertexes.add(newVertex);
		} else {
			System.out.println("You cannot add any other vertices to this fork. Maximum: 20.");
		}

	}

	public boolean addEdge(int weigth, TYPE dataBegin, TYPE dataEnd) {

		if (dataBegin == null || dataEnd == null) {
			System.out.println("One of the vertexes does not exist.");
			return false;
		}

		Vertex<TYPE> begin = this.getVertex(dataBegin);
		Vertex<TYPE> end = this.getVertex(dataEnd);

		for (Edge<TYPE> edge : edges) {
			if ((edge.getBegin() == begin && edge.getEnd() == end)
					|| (edge.getBegin() == end && edge.getEnd() == begin)) {
				System.out.println("An edge already exists between the given vertices.");
				return false;
			}
		}

		Edge<TYPE> edge = new Edge<TYPE>(weigth, begin, end);
		begin.addEdgeOut(edge);
		end.addEdgeIn(edge);
		this.edges.add(edge);

		return true;
	}

	public Vertex<TYPE> getVertex(TYPE data) {
		Vertex<TYPE> vertex = null;

		for (int i = 0; i < this.vertexes.size(); i++) {
			if (this.vertexes.get(i).getData().equals(data)) {
				vertex = this.vertexes.get(i);
				break;
			}
		}
		return vertex;
	}

	public int getforkSize(Fork<TYPE> fork) {
		return this.vertexes.size();
	}

	public void bfs(int starter, Fork<TYPE> fork) {
		try {
			Set<Vertex<TYPE>> checked = new HashSet<>();
			LinkedList<Vertex<TYPE>> queue = new LinkedList<>();

			Vertex<TYPE> actual = this.vertexes.get(starter);
			checked.add(actual);
			System.out.println("Visited: " + actual.getData());
		    printEdges(actual);
			queue.add(actual);

			while (!queue.isEmpty()) {
				Vertex<TYPE> visited = queue.removeFirst();

				for (Edge<TYPE> edge : visited.getEdgesOut()) {
					Vertex<TYPE> next = edge.getEnd();

					if (!checked.contains(next)) {
					    checked.add(next);
					    System.out.println("Visited: " + next.getData());
					    printEdges(next);
					    queue.add(next);
					}
				}

				if (queue.size() > 0) {
					queue.add(queue.removeFirst());
				}
			}

			
		} catch (Exception e) {
			System.out.println("starter index data: " + this.vertexes.get(starter).getData());
			System.out.println(e);
		}
	}

	private void printEdges(Vertex<TYPE> visited) {
	    System.out.print("Edges for vertex " + visited.getData() + ": ");

	    for (Edge<TYPE> edge : visited.getEdgesOut()) {
	        System.out.print("[" + edge.getEnd().getData() + ", " + edge.getWeigth() + "]");
	    }

	    System.out.println();
	}
	
	

	public boolean isVertex(String name) {
		for (int i = 0; i < vertexes.size(); i++) {
			if (vertexes.get(i).getData().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
