package de.hawhh.informatik.kasse;

import java.util.Objects;

public class Position {

    private final String name;
    private final int preis;

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }

    public Position(String name, int preis){
        this.name = name;
        this.preis = preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return preis == position.preis &&
                Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, preis);
    }

    @Override
    public String toString() {
        return  name +
                "("  + preis +
                "€)";
    }
}
