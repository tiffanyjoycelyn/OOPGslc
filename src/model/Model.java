package model;

public class Model {
    protected String name;

    public Model(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}