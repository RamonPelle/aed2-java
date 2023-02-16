package application;

import java.util.Scanner;

import entities.Fork;
import entities.Utilities;

public class Week1_forks {

	public static void main(String[] args) {
		/*
		 * TODO: 1. Permitir o armazenamento de até 20 vértices: OK
		 * 
		 * 2. Fazer a leitura dos pesos das arestas de cada vértice: OK
		 * 
		 * 3. Considerar sempre vértices positivos: OK
		 * 
		 * 4. Mostrar os dados armazenados: OK
		 */

		Scanner sc = new Scanner(System.in);
		String dataVertex, origin, destiny;
		int dataEdge;
		Fork<String> fork = new Fork<String>();

		loop: while (1 > 0) {
			System.out.println("----- O QUE VOCÊ QUER FAZER -----");
			System.out.println("1 - Adicionar vértice");
			System.out.println("2 - Adicionar aresta");
			System.out.println("3 - Buscar (BFS)");
			System.out.println("4 - Sair");
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

			default:
				System.out.println("Unexpected input, try again.");
				break;
			}
		}

		sc.close();
	}

}
