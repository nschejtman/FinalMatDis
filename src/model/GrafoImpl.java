package model;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrafoImpl implements Grafo {

    private Object[] vertices;
    private double[][] aristas;
    private int n;
    private int alfa;

    public GrafoImpl() {
        vertices = new Object[10];
        aristas = new double[10][10];
        n = 0;
        alfa = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                aristas[i][j] = -1;
            }
        }
    }

    private void agrandarLista(int tamano) {
        final int M = vertices.length;
        final int N = M + tamano;

        Object[] auxV = new Object[N];
        double[][] auxW = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                auxW[i][j] = -1;
            }
        }

        for (int i = 0; i < M; i++) {
            auxV[i] = vertices[i];
            for (int j = 0; j < M; j++) {
                auxW[i][j] = aristas[i][j];
            }
        }

        vertices = auxV;
        aristas = auxW;
    }

    @Override
    /**
     * Mejor caso: O(1)
     * Peor caso: O(n2)
     */
    public void agregarVertice(Object x) {
        if (n == vertices.length) agrandarLista(10);
        vertices[n] = x;
        n++;
    }

    public int catidadVertices() {
        return n;
    }

    //Orden lineal
    @Override
    public void agregarArista(int v, int w, double costo) {
        if (v >= n || w >= n) throw new IndexOutOfBoundsException("No existe alguno de los vertices");
        aristas[v][w] = costo;
        alfa++;
    }

    //Orden lineal
    @Override
    public void eliminarArista(int v, int w) {
        if (v >= n || w >= n) throw new IndexOutOfBoundsException("No existe alguno de los vertices");
        aristas[v][w] = -1;
        alfa--;
    }

    //Orden n2
    @Override
    public void eliminarVertice(int v) {
        final int M = n;

        Object[] auxV = new Object[M];
        double[][] auxW = new double[M][M];

        for (int i = 0; i < M; i++) {
            if (i < v) {
                //Copio todos los menores tal cual
                auxV[i] = vertices[i];
                for (int j = 0; j < M; j++) {
                    if (j < v) {
                        auxW[i][j] = aristas[i][j];
                    } else if (j > v) {
                        auxW[i][j - 1] = aristas[i][j];
                    } else {
                        if (hayArista(i, j)) alfa--;
                    }
                }
            } else if (i > v) {
                //Retraso todos los mayores una posicion
                auxV[i - 1] = vertices[i];
                for (int j = 0; j < M; j++) {
                    if (j < v) {
                        auxW[i - 1][j] = aristas[i][j];
                    } else if (j > v) {
                        auxW[i - 1][j - 1] = aristas[i][j];
                    } else {
                        if (hayArista(i, j)) alfa--;
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    if (hayArista(i, j)) alfa--;

                }
            }
        }
        vertices = auxV;
        aristas = auxW;
        n--;
    }

    //Orden lineal
    @Override
    public boolean hayArista(int v, int w) {
        if (v >= n || w >= n) throw new IndexOutOfBoundsException("No existe alguno de los vertices");
        return aristas[v][w] != -1;
    }

    //Orden lineal
    @Override
    public int orden() {
        return n;
    }

    //Orden lineal
    @Override
    public int cantAristas() {
        return alfa;
    }

    //Orden lineal
    @Override
    public Object verVertice(int v) {
        if (v >= n) throw new IndexOutOfBoundsException("No existe tal vertice!");
        return vertices[v];
    }

    //Orden n
    @Override
    public List<Integer> getListaSuc(int v) {
        List<Integer> listaSuc = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (aristas[v][i] != -1) listaSuc.add(i);
        }
        return listaSuc;
    }

    public void print() {
        System.out.println("vertices qty: " + n);
        System.out.println("aristas qty: " + alfa);
        System.out.println("Lista de vertices");
        System.out.println("--------------------");
        for (int i = 0; i < vertices.length; i++) {
            System.out.println(i + " -> " + vertices[i]);
        }
        System.out.println("Matriz de costos");
        System.out.println("--------------------");
        for (int i = 0; i < n; i++) {
            String line = "";
            for (int j = 0; j < n; j++) {
                line = line + " " + aristas[i][j];
            }
            System.out.println(line);
        }

    }

    //Orden lineal
    @Override
    public double costoArista(int v, int w) {
        if (v >= n) throw new IndexOutOfBoundsException("No existe tal vertice!");
        if (!hayArista(v, w)) return -1;
        return aristas[v][w];
    }

    public void busquedaPlana() {
        for (int i = 0; i < n; i++) {
            System.out.println(vertices[i]);
        }
    }

    public void dfs() {
        dfs(0, new boolean[n]);
    }

    private void dfs(int v, boolean[] visitado) {
        //Visito el vertice
        System.out.println(vertices[v]);
        visitado[v] = true;

        //Me fijo si todos fueron visitados
        boolean todosVisitados = false;
        for (int i = 0; i < visitado.length; i++) {
            if (!visitado[i]) {
                todosVisitados = false;
                break;
            }
        }

        //Sigo la línea
        if (!todosVisitados) {
            for (int i : getListaSuc(v)) {
                if (!visitado[i]) {
                    dfs(i, visitado);
                    break;
                }
            }
        }

        //Visito los que quedaron sin visitar
        if (!todosVisitados) {
            for (int i = 0; i < n; i++) {
                if (!visitado[i]) dfs(i, visitado);
            }
        }

    }

    public void bfs() {
        bfs(0, new boolean[n], new Queue());
    }

    private void bfs(int v, boolean[] visitado, Queue queue) {
        if (visitado[v]) return;
        System.out.println(vertices[v]);
        visitado[v] = true;


        for (int i : getListaSuc(v)) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            try {
                final Object next = queue.dequeue();
                bfs((Integer) next, visitado, queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Busco que alguno que no haya sido visitado
        final int length = visitado.length;
        for (int i = 0; i < length; i++) {
            if (!visitado[i]) bfs(i, visitado, queue);
        }

    }

    public List<Integer> fuentes() {
        List<Integer> fuentes = new ArrayList<Integer>();
        for (int j = 0; j < n; j++) {
            boolean esFuente = true;
            for (int i = 0; i < n; i++) {
                if (aristas[i][j] != -1) esFuente = false;
            }
            if (esFuente) fuentes.add(j);
        }
        return fuentes;
    }

    public List<Integer> sumideros() {
        List<Integer> fuentes = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            boolean esSumidero = true;
            for (int j = 0; j < n; j++) {
                if (aristas[i][j] != -1) esSumidero = false;
            }
            if (esSumidero) fuentes.add(i);
        }
        return fuentes;
    }

    public int aisladosQty() {
        final List<Integer> sumideros = sumideros();
        final List<Integer> fuentes = fuentes();
        int aisladosQty = 0;
        for (int i = 0; i < n; i++) {
            if (sumideros.contains(i) && fuentes.contains(i)) aisladosQty++;
        }
        return aisladosQty;
    }

    public boolean[] agregarConexiones(int v, boolean[] conectado) {
        final List<Integer> listaSuc = getListaSuc(v);

        for (int a : listaSuc) {
            if (!conectado[a]) {
                conectado[a] = true;
                conectado = agregarConexiones(a, conectado);
            }
        }

        return conectado;
    }

    public boolean isConexo() {
        for (int i = 0; i < n; i++) {
            boolean[] conexiones = agregarConexiones(i, new boolean[n]);
            for (int aux = 0; aux < n; aux++) {
                if (!conexiones[aux]) return false;
            }
        }
        return true;
    }

    public boolean conexos(int v, int w) {
        final boolean[] conexiones = agregarConexiones(v, new boolean[n]);
        return conexiones[w];
    }

    public double costoMinimo(int v, int w) {
        final double[][] ponderaciones = floyd(aristas);
        return ponderaciones[v][w];
    }

    public double[][] floyd(double[][] ponderaciones) {
        double[][] pNext = ponderaciones;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int k = 0; k < n; k++) {
                        if (pNext[i][k] != -1 && pNext[k][j] != -1 && pNext[i][j] > pNext[i][k] + pNext[k][j])
                            pNext[i][j] = pNext[i][k] + pNext[k][j];
                    }
                }
            }
        }

        if(pNext.equals(ponderaciones)) return pNext;
        else return floyd(pNext);

    }

    public double excentricidad(int v) {
        final double[][] floyd = floyd(aristas);
        double valor = -1;
        for (int i = 0; i < n; i++) {
            if (floyd[i][v] > valor) valor = floyd[i][v];
        }
        return valor;
    }

    public int verticeCentral() {
        int verticeCentral = 0;
        double excentricidad = 999999999;

        final List<Integer> fuentes = fuentes();
        final List<Integer> sumideros = sumideros();

        for (int i = 0; i < n; i++) {
            final double nextEx = excentricidad(i);
            boolean isAislado = fuentes.contains(i) && sumideros.contains(i);
            if (nextEx != -1 && nextEx < excentricidad && !isAislado) {
                excentricidad = nextEx;
                verticeCentral = i;
            }
        }

        return verticeCentral;
    }

    public GrafoImpl grafoInverso() {
        double[][] aristasInv = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aristasInv[i][j] = aristas[j][i];
            }
        }

        GrafoImpl grafoInv = this;
        grafoInv.setAristas(aristasInv);

        return grafoInv;

    }

    public void setAristas(double[][] aristas) {
        this.aristas = aristas;
    }

    public void setVertices(Object[] vertices) {
        this.vertices = vertices;
    }

    public static void agregarVertices(GrafoImpl grafo, int qty) {
        for (int i = 1; i <= qty; i++) {
            Dummy dummy = new Dummy("dummy " + i);
            grafo.agregarVertice(dummy);

        }
    }

    public static void agregarAristas(GrafoImpl grafo) {
        int cantidadDeVertices = grafo.catidadVertices();
        Random random = new Random();
        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                double costo = random.nextInt(45);
                if (costo > 30) {
                    grafo.agregarArista(i, j, random.nextInt(45));
                }
            }
        }
    }

    public static GrafoImpl grafoRandom() {
        GrafoImpl grafo = new GrafoImpl();
        Random random = new Random();
        final int orden = random.nextInt(20);
        agregarVertices(grafo, orden);
        agregarAristas(grafo);
        return grafo;
    }

    private MatrizDijkstra dijkstra(int predecesor, double acumulado, boolean[] permanentes, MatrizDijkstra matriz){
        boolean hizoCambios = false;

        //Agrego el vertice a la permanencia y calculo el costo para sus sucesores
        permanentes[predecesor] = true;
        final List<Integer> listaSuc = getListaSuc(predecesor);
        for(Integer suc: listaSuc){
            if(!permanentes[suc] && (matriz.costo(suc)> acumulado + costoArista(predecesor, suc) || matriz.costo(suc) == -1)){
                matriz.setCosto(suc, acumulado + costoArista(predecesor, suc));
                matriz.setPredecesor(suc, predecesor);
                hizoCambios = true;
            }
        }

        //Selecciono el vertice para la proxima iteracion
        int proximo = 0;
        for (int i = 0; i < n; i++) {
            if(!permanentes[i]) proximo = i;
        }


        for (int i = 0; i < n; i++) {
            if(!permanentes[i] && matriz.costo(i) != -1 && matriz.costo(i)< matriz.costo(proximo)) proximo = i;
        }

        //Codicion de corte
        if(!hizoCambios) return matriz;
        else return dijkstra(proximo, acumulado + aristas[predecesor][proximo], permanentes, matriz);

    }

    public MatrizDijkstra getMatrizDijkstra(int v){
        return dijkstra(v, 0, new boolean[n], new MatrizDijkstra(n, v));
    }

    public double[][] getAristas() {
        return aristas;
    }

    public Object[] getVertices() {
        return vertices;
    }
}
