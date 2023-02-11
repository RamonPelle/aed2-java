package entities;

import java.util.ArrayList;

public class Vertex<TYPE> {
	private TYPE data;
	private ArrayList<Edge<TYPE>> edgesIn;
	private ArrayList<Edge<TYPE>> edgesOut;
	
	public Vertex(TYPE value) {
		this.data = value;
		this.edgesIn = new ArrayList<Edge<TYPE>>();
		this.edgesOut = new ArrayList<Edge<TYPE>>();
	}

	public TYPE getData() {
		return data;
	}

	public void setData(TYPE data) {
		this.data = data;
	}
	
	public void addEdgeIn(Edge<TYPE> edge) {
		this.edgesIn.add(edge);
	}
	
	public void addEdgeOut(Edge<TYPE> edge) {
		this.edgesOut.add(edge);
	}

	public ArrayList<Edge<TYPE>> getEdgesIn() {
		return edgesIn;
	}

	public void setEdgesIn(ArrayList<Edge<TYPE>> edgesIn) {
		this.edgesIn = edgesIn;
	}

	public ArrayList<Edge<TYPE>> getEdgesOut() {
		return edgesOut;
	}

	public void setEdgesOut(ArrayList<Edge<TYPE>> edgesOut) {
		this.edgesOut = edgesOut;
	}
	
	
}