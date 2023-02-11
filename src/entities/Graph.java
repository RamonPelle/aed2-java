package entities;

import java.util.ArrayList;

public class Graph<TYPE> {
	private ArrayList<Vertex<TYPE>> vertexes;
	private ArrayList<Edge<TYPE>> edges;

	public Graph() {
		this.vertexes = new ArrayList<Vertex<TYPE>>();
		this.edges = new ArrayList<Edge<TYPE>>();
	}

	public void addVertex(TYPE data) {
		if (this.vertexes.size() < 20) {
			Vertex<TYPE> newVertex = new Vertex<TYPE>(data);
			this.vertexes.add(newVertex);
		} else {
			System.out.println("You cannot add any other vertices to this graph. Maximum: 20.");
		}

	}

	public void addEdge(int weigth, TYPE dataBegin, TYPE dataEnd) {
		Vertex<TYPE> begin = this.getVertex(dataBegin);
		Vertex<TYPE> end = this.getVertex(dataEnd);
		Edge<TYPE> edge = new Edge<TYPE>(weigth, begin, end);

		begin.addEdgeOut(edge);
		end.addEdgeIn(edge);

		this.edges.add(edge);

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

	public int getGraphSize(Graph<TYPE> graph) {
		return this.vertexes.size();
	}

	public void bfs(int starter, Graph<TYPE> graph) {
		try {
			ArrayList<Vertex<TYPE>> checked = new ArrayList<Vertex<TYPE>>();
			ArrayList<Vertex<TYPE>> queue = new ArrayList<Vertex<TYPE>>();

			Vertex<TYPE> actual = this.vertexes.get(starter);
			checked.add(actual);
			System.out.println(actual.getData());
			queue.add(actual);

			int queueIndex = 0;
			while (queue.size() > 0) {
			
				Vertex<TYPE> visited = queue.get(queueIndex);
				for (int i = 0; i < visited.getEdgesOut().size(); i++) {	
					Vertex<TYPE> next = visited.getEdgesOut().get(i).getEnd(); // final da aresta
					if (!checked.contains(next)) { // se o next ainda nao foi visitado
						checked.add(next);
						System.out.println(next.getData());
						queue.add(next);
					}
				} // passei por todas as arestas do vertice
				
				//faz a verificacao pra nao dar IndexOutOfBounds ex << tentar entender
				if (queueIndex == queue.size() - 1) {
				    queue.remove(queueIndex);
				    queueIndex = 0;
				  } else {
				    queue.remove(queueIndex);
				    queueIndex++;
				  }
			}
		} catch (Exception e) {
			System.out.println("starter index data: " + this.vertexes.get(starter).getData());
			System.out.println(e);
		}

	}
}
