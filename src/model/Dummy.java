package model;

public class Dummy {
    private String name;

    public Dummy(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
