package application;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Fork;
import entities.Vertex;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String dataVertex, origin, destiny;
		int dataEdge;
		Fork<String> fork = new Fork<String>();
		
		loop: while (1 > 0) {
			System.out.println("----- What do you want to do -----");
			System.out.println("1 - Add vertex");
			System.out.println("2 - Add edge");
			System.out.println("3 - BFS");
			System.out.println("4 - Dijkstra");
			System.out.println("5 - Exit");
			int flag = sc.nextInt();
			switch (flag) {
			case 1: {
				// Utilities.cleanConsole();
				System.out.println("Enter the vertex name");
				sc.nextLine();
				dataVertex = sc.nextLine();
				System.out.println();
				fork.addVertex(dataVertex);
				System.out.println();

				break;
			}
			case 2: {
				// Utilities.cleanConsole();
				System.out.println("Enter the edge data: ");

				System.out.printf("- Weigth: ");
				dataEdge = sc.nextInt();
				System.out.printf("- Origin: ");
				sc.nextLine();
				origin = sc.nextLine();
				System.out.printf("- Destiny: ");
				destiny = sc.nextLine();

				System.out.println();
				if (fork.isVertex(destiny) == true && fork.isVertex(origin) == true) {
					fork.addEdge(dataEdge, origin, destiny);

				} else {
					System.out.println("One of the vertexes do not exist.");
				}

				System.out.println();

				break;
			}
			case 3: {
				// Utilities.cleanConsole();
				sc.nextLine(); // pega o enter do switch
				System.out.println();
				System.out.println("From witch node do you want to start the BFS?");
				int indexBfs = sc.nextInt();
				fork.bfs(indexBfs, fork);

				System.out.println();
				break;
			}
			case 4: {
				// Utilities.cleanConsole();
				System.out.println();
				System.out.println("Closing");
				break loop;
			}
			case 5: {
				// Utilities.cleanConsole();
				sc.nextLine();
				System.out.println();
				System.out.println("Enter the origin and destiny vertices: ");
				System.out.printf("- Origin: ");
				origin = sc.nextLine();
				System.out.printf("- Destiny: ");
				destiny = sc.nextLine();

				ArrayList<Vertex<String>> shortestPath = fork.dijkstra(origin, destiny);
				
				if (shortestPath == null) {
					System.out.println("No path found between the given vertices.");
				} else {
					System.out.printf("Shortest path between %s and %s: ", origin, destiny);
					for (Vertex<String> vertex : shortestPath) {
						System.out.printf("%s ", vertex.getData());
					}
					System.out.println();
				}

				System.out.println();
				break;
			}
			default:
				System.out.println("Unexpected input, try again.");
				break;
			}
		}

		sc.close();
	}

}
