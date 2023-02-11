package entities;

public class Edge<TYPE> {
	private int weight;
	private Vertex<TYPE> begin;
	private Vertex<TYPE> end;
	
	public Edge(int weight, Vertex<TYPE> begin, Vertex<TYPE> end) {
		this.weight = weight;
		this.begin = begin;
		this.end = end;
	}
	
	public int getWeigth() {
		return weight;
	}
	public void setWeigth(int weight) {
		this.weight = weight;
	}
	public Vertex<TYPE> getBegin() {
		return begin;
	}
	public void setBegin(Vertex<TYPE> begin) {
		this.begin = begin;
	}
	public Vertex<TYPE> getEnd() {
		return end;
	}
	public void setEnd(Vertex<TYPE> end) {
		this.end = end;
	}
	
	
}
