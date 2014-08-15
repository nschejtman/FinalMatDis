package views;

import model.GrafoImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuPrincipal();
        Scanner sc = new Scanner(System.in);
        final int opA = sc.nextInt();
        GrafoImpl grafo = null;
        switch (opA) {
            case 1:
                grafo = new GrafoImpl();
                break;
            case 2:
                grafo = GrafoImpl.grafoRandom();
                break;
        }
        menuGrafo(grafo);
    }

    public static void menuPrincipal() {
        System.out.println("Menu");
        System.out.println("1. Crear grafo");
        System.out.println("2. Generar grafo aleatorio");
    }

    public static void menuGrafo(GrafoImpl grafo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------GRAFO--------------------");
        System.out.println("1. Agregar vertice");
        System.out.println("2. Agregar arista");
        System.out.println("3. Eliminar vertice");
        System.out.println("4. Eliminar arista");
        System.out.println("5. Imprimir grafo");
        System.out.println("6. Orden");
        System.out.println("7. Cantidad de aristas");
        System.out.println("8. Ver vertice");
        System.out.println("9. Valor arista");
        System.out.println("10. Lista de sucesores");
        System.out.println("11. BFS");
        System.out.println("12. DFS");
        System.out.println("13. Fuentes");
        System.out.println("14. Sumideros");
        System.out.println("15. Cantidad de aislados");
        System.out.println("16. ¿Es conexo?");
        System.out.println("17. Costo minimo entre dos vertices");
        System.out.println("18. Excentricidad de un vertice");
        System.out.println("19. Vertice central");
        System.out.println("20. Salir");
        final int opB = sc.nextInt();
        switch (opB) {
            case 1:
                System.out.println("Inserte el valor del vertice");
                final String s = sc.next();
                grafo.agregarVertice(s);
                menuGrafo(grafo);
                break;
            case 2:
                System.out.println("Inserte el n. del vertice origen");
                final int s1 = sc.nextInt();
                System.out.println("Inserte el n. del vertice destino");
                final int s2 = sc.nextInt();
                System.out.println("Inserte la ponderación");
                final double s3 = sc.nextDouble();
                grafo.agregarArista(s1, s2, s3);
                menuGrafo(grafo);
                break;
            case 3:
                System.out.println("Inserte el n. del vertice");
                final int s4 = sc.nextInt();
                grafo.eliminarVertice(s4);
                menuGrafo(grafo);
                break;
            case 4:
                System.out.println("Inserte el n. del vertice origen");
                final int s5 = sc.nextInt();
                System.out.println("Inserte el n. del vertice destino");
                final int s6 = sc.nextInt();
                grafo.eliminarArista(s5, s6);
                menuGrafo(grafo);
                break;
            case 5:
                grafo.print();
                menuGrafo(grafo);
                break;
            case 6:
                System.out.println("Orden: " + grafo.orden());
                menuGrafo(grafo);
                break;
            case 7:
                System.out.println("Cantidad de aristas: " + grafo.cantAristas());
                menuGrafo(grafo);
                break;
            case 8:
                System.out.println("Ingrese el n. del vertice");
                final int s7 = sc.nextInt();
                System.out.println(grafo.verVertice(s7));
                menuGrafo(grafo);
                break;
            case 9:
                System.out.println("Inserte el n. del vertice origen");
                final int s8 = sc.nextInt();
                System.out.println("Inserte el n. del vertice destino");
                final int s9 = sc.nextInt();
                System.out.println(grafo.costoArista(s8, s9));
                menuGrafo(grafo);
                break;
            case 10:
                System.out.println("Ingrese el n. del vertice");
                final int s10 = sc.nextInt();
                final List<Integer> listaSuc = grafo.getListaSuc(s10);
                for (Integer suc : listaSuc) {
                    System.out.println(suc);
                }
                menuGrafo(grafo);
                break;
            case 11:
                grafo.bfs();
                menuGrafo(grafo);
                break;
            case 12:
                grafo.dfs();
                menuGrafo(grafo);
                break;
            case 13:
                final List<Integer> fuentes = grafo.fuentes();
                for (Integer fuente : fuentes) {
                    System.out.println(fuente);
                }
                menuGrafo(grafo);
                break;
            case 14:
                final List<Integer> sumideros = grafo.sumideros();
                for (Integer sumidero : sumideros) {
                    System.out.println(sumidero);
                }
                menuGrafo(grafo);
                break;
            case 15:
                System.out.println("Cantidad aislados: " + grafo.aisladosQty());
                menuGrafo(grafo);
                break;
            case 16:
                System.out.println(grafo.isConexo());
                menuGrafo(grafo);
                break;
            case 17:
                System.out.println("Inserte el n. del vertice origen");
                final int s11 = sc.nextInt();
                System.out.println("Inserte el n. del vertice destino");
                final int s12 = sc.nextInt();
                System.out.println(grafo.costoMinimo(s11, s12));
                menuGrafo(grafo);
                break;
            case 18:
                System.out.println("Inserte el n. del vertice");
                final int s13 = sc.nextInt();
                System.out.println(grafo.excentricidad(s13));
                menuGrafo(grafo);
                break;
            case 19:
                System.out.println("Vertice central: " + grafo.verticeCentral());
                menuGrafo(grafo);
                break;
            case 20:
                break;
        }
    }
}
