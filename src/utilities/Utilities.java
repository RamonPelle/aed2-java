package utilities;

public class Utilities {
	//TODO cleanConsole not working :C try jansi library to do it later.
	public static void cleanConsole() {
		  try {
		        String os = System.getProperty("os.name");
		        if (os.contains("Windows")) {
		            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		        } else {
		            Runtime.getRuntime().exec("clear");
		        }
		    } catch (Exception e) {
	            System.out.println("Error clearing console: " + e.getMessage());
		    }
	}
}
