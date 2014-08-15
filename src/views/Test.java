package views;

import model.Dummy;
import model.GrafoImpl;
import model.MatrizDijkstra;

public class Test {
    public static void main(String[] args) {
        GrafoImpl grafo = new GrafoImpl();

        for (int i = 0; i < 5; i++) {
            Dummy dummy = new Dummy("dummy " + i);
            grafo.agregarVertice(dummy);
        }

        grafo.agregarArista(0, 1, 5);
        grafo.agregarArista(0, 3, 10);
        grafo.agregarArista(0, 4, 20);
        grafo.agregarArista(1, 3, 3);
        grafo.agregarArista(1, 4, 5);

        grafo = grafo.grafoInverso();

       grafo.print();


    }
}
