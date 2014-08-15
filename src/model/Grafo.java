package model;

import java.util.List;

public interface Grafo {
    public void agregarVertice(Object x);
    public void agregarArista(int v, int w, double costo);
    public void eliminarArista(int v, int w);
    public void eliminarVertice(int v);
    public boolean hayArista(int v, int w);
    public int orden();
    public int cantAristas();
    public Object verVertice(int v);
    public List getListaSuc(int v);
    public double costoArista(int v, int w);
}
