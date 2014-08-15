package model;

public class MatrizDijkstra {
    int verticeOrigen;
    int[] predecesor;
    double[] costo;

    public MatrizDijkstra(int orden, int verticeOrigen){
        this.verticeOrigen = verticeOrigen;
        predecesor = new int[orden];
        costo = new double[orden];
        for (int i = 0; i < orden; i++) {
            predecesor[i] = -1;
            costo[i] = -1;
        }
    }

    public int getPredecesor(int v){
        return predecesor[v];
    }

    public double costo(int v){
        return costo[v];
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public void setPredecesor(int v, int predecesor){
        this.predecesor[v] = predecesor;
    }

    public void setCosto(int v, double costo){
        this.costo[v] = costo;
    }

    public int[] getPredecesor() {
        return predecesor;
    }

    public double[] getCosto() {
        return costo;
    }


}
