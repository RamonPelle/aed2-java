package application;

import java.util.Scanner;

import entities.Graph;

public class week1_graphs {

	public static void main(String[] args) {
		/*TODO: 
		 * 1. Permitir o armazenamento de até 20 vértices
		 * 
		 * 2. Fazer a leitura dos pesos das arestas de cada vértice
		 * 
		 * 3. Considerar sempre vértices positivos
		 * 
		 * 4. Mostrar os dados armazenados
		 */
		Graph<String> graph = new Graph<String>();
		graph.addVertex("Ramon");
		graph.addVertex("Joao");
		graph.addVertex("Anna");
		graph.addVertex("Bernardo");

		graph.addEdge(3, "Ramon", "Anna");
		graph.addEdge(2, "Ramon", "Joao");
		graph.addEdge(2, "Ramon", "Bernardo");
		graph.addEdge(1, "Bernardo", "Anna");
		graph.addEdge(1, "Bernardo", "Joao");
		graph.addEdge(1, "Joao", "Bernardo");

		
		Scanner sc = new Scanner(System.in);
		System.out.println("From witch node do you want to start the BFS?");
		graph.bfs(sc.nextInt(), graph);
		
		// System.out.println(graph.getGraphSize(graph));

		sc.close();
	}

}
