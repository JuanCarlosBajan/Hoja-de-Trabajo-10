import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> ciudades = new ArrayList<>();
        Graph grafo = new Graph(100);
        Scanner sc = new Scanner(System.in);

        try {
            File spanishTxt = new File ("C:\\Users\\juanc\\OneDrive\\Documents\\UnidirectionalWeightedGraph\\src\\guategrafo.txt");
            FileReader fr = new FileReader(spanishTxt);
            BufferedReader br1 = new BufferedReader(fr);
            String linea = "";
            Scanner scanner = new Scanner(fr);

            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                //System.out.println(linea.split(" ")[0] + " " + linea.split(" ")[1]);
                grafo.addEdge(linea.split(" ")[0],linea.split(" ")[1], Integer.parseInt(linea.split(" ")[2]));
                if (!ciudades.contains(linea.split(" ")[0])) ciudades.add(linea.split(" ")[0]);
            }
            fr.close();
            br1.close();

        } catch ( Exception e){
            System.out.println("No se pudo abrir el archivo");
        }

        grafo.verifyVerts();
        grafo.GenerateMatrix();

        boolean principal = true;
        while (principal) {
            System.out.println("***********Bienvenido al Programa***********");
            System.out.println("1. Consultar la ruta mas corta entre dos ciudades");
            System.out.println("2. Consultar la ciudad central");
            System.out.println("3. Modificar el Grafo");
            System.out.println("4. Finalizar");
            System.out.println("*********************************************");
            System.out.println("Por favor ingrese el numero de la opcion que desea:");
            int opcion = 0;
            boolean run = true;
            while (run) {
                try {
                    System.out.print("->");
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion > 0 && opcion < 5) run = false;
                } catch (Exception e) {
                    sc.nextLine();
                }
            }

            switch (opcion) {
                case 1:
                    System.out.println("Por favor ingrese la ciudad de salida");
                    String source = sc.nextLine();
                    System.out.println("Por favor ingrese la ciudad de destino");
                    String destination = sc.nextLine();
                    System.out.print("La ruta mas rapida entre estas ciudades es: ");
                    if (ciudades.contains(source) && ciudades.contains(destination)) {
                        System.out.println(grafo.askForRoute(source, destination));
                    }
                    break;
                case 2:
                    System.out.println(grafo.getCenter());
                    break;
                case 3:
                    System.out.println("Por favor ingrese la ciudad de salida");
                    String s = sc.nextLine();
                    System.out.println("Por favor ingrese la ciudad de destino");
                    String d = sc.nextLine();
                    System.out.print("La ruta mas es de: ");
                    Integer w = 9999;
                    boolean run2 = true;
                    while (run2){
                        try {
                            w = sc.nextInt();
                            sc.nextLine();
                            run2 = false;
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Ha habido un error con el valor ingresado");
                        }
                    }
                    grafo.modify(s,d,w);
                    break;
                case 4:
                    System.out.println("ADIOSSSSSSSSSSSS");
                    principal = false;
            }
        }

    }
}
