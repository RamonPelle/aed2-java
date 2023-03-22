package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Fork<TYPE> {
	private ArrayList<Vertex<TYPE>> vertexes;
	private ArrayList<Edge<TYPE>> edges;

	public Fork() {
		this.vertexes = new ArrayList<Vertex<TYPE>>();
		this.edges = new ArrayList<Edge<TYPE>>();
	}

	public boolean addVertex(TYPE data) {
		if (this.vertexes.size() < 20) {
			Vertex<TYPE> newVertex = new Vertex<TYPE>(data);
			this.vertexes.add(newVertex);
			System.out.println("Sucessfully added.");
			return true;
		} else {
			System.out.println("You cannot add any other vertices to this fork. Maximum: 20.");
			return false;
		}

	}

	public boolean addEdge(int weight, TYPE dataBegin, TYPE dataEnd) {

		if (dataBegin == null || dataEnd == null) {
			System.out.println("One of the vertexes does not exist.");
			return false;
		}

		if (weight < 0) {
			System.out.println("The edge's weight is smaller than 0. Try again with a valid number");
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

		Edge<TYPE> edge = new Edge<TYPE>(weight, begin, end);
		begin.addEdgeOut(edge);
		end.addEdgeIn(edge);
		this.edges.add(edge);
		System.out.println("Sucessfully added.");
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
			System.out.print("[" + edge.getEnd().getData() + ", " + edge.getWeight() + "]");
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

	public ArrayList<Edge<TYPE>> getAllEdges() {
		return edges;
	}

	public ArrayList<Vertex<TYPE>> getAllVertexes() {
		return vertexes;
	}

	// DIJKSTRA
	public ArrayList<Vertex<TYPE>> dijkstra(TYPE dataBegin, TYPE dataEnd) {
		Vertex<TYPE> begin = this.getVertex(dataBegin);
		Vertex<TYPE> end = this.getVertex(dataEnd);

		List<Vertex<TYPE>> unvisited = new ArrayList<Vertex<TYPE>>();
		for (Vertex<TYPE> vertex : this.vertexes) {
			vertex.setDistance(Integer.MAX_VALUE);
			vertex.setPrevious(null);
			unvisited.add(vertex);
		}
		begin.setDistance(0);

		while (!unvisited.isEmpty()) {
			Vertex<TYPE> current = null;
			int minDistance = Integer.MAX_VALUE;

			for (Vertex<TYPE> vertex : unvisited) {
				if (vertex.getDistance() < minDistance) {
					minDistance = (int) vertex.getDistance();
					current = vertex;
				}
			}

			if (current == end) {
				break;
			}

			unvisited.remove(current);

			for (Edge<TYPE> edge : current.getEdgesOut()) {
				Vertex<TYPE> neighbor = edge.getEnd();
				int distanceThroughCurrent = (int) (current.getDistance() + edge.getWeight());
				if (distanceThroughCurrent < neighbor.getDistance()) {
					neighbor.setDistance(distanceThroughCurrent);
					neighbor.setPrevious(current);
				}
			}
		}

		ArrayList<Vertex<TYPE>> path = new ArrayList<Vertex<TYPE>>();
		Vertex<TYPE> current = end;
		while (current != null) {
			path.add(0, current);
			current = current.getPrevious();
		}

		if (path.isEmpty() || path.get(0) != begin) {
			return null;
		}

		return path;
	}

	// KRUSKAL
	public Fork<TYPE> kruskal(Fork<TYPE> fork) {
	    // Criando a MST vazia
	    Fork<TYPE> mst = new Fork<TYPE>();

	    // Listando todas as arestas
	    ArrayList<Edge<TYPE>> edges = fork.getAllEdges();

	    // Ordenar de forma crescente
	    Collections.sort(edges, new Comparator<Edge<TYPE>>() {
	        public int compare(Edge<TYPE> e1, Edge<TYPE> e2) {
	            return e1.getWeight() - e2.getWeight();
	        }
	    });

	    // Cria um set para cada vertice e adciona na MST
	    HashMap<TYPE, HashSet<TYPE>> sets = new HashMap<TYPE, HashSet<TYPE>>();
	    for (Vertex<TYPE> vertex : fork.getAllVertexes()) {
	        HashSet<TYPE> set = new HashSet<TYPE>();
	        set.add(vertex.getData());
	        sets.put(vertex.getData(), set);
	        mst.addVertex(vertex.getData());
	    }

	    // Adiciona só as arestas que nao formam ciclo
	    for (Edge<TYPE> edge : edges) {
	        TYPE begin = edge.getBegin().getData();
	        TYPE end = edge.getEnd().getData();

	        // Verifica se begin e end estão em sets diferentes
	        HashSet<TYPE> beginSet = sets.get(begin);
	        HashSet<TYPE> endSet = sets.get(end);

	        if (!beginSet.equals(endSet)) {
	            beginSet.addAll(endSet);
	            for (TYPE vertex : endSet) {
	                sets.put(vertex, beginSet);
	            }
	            // Aprovado, adicionar na MST
	            mst.addEdge(edge.getWeight(), begin, end);
	        }
	    }

	    return mst;
	}

}
