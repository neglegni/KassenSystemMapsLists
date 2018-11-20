package de.hawhh.informatik.kasse;

import java.util.*;

// TODO Iterierbar machen
public class Rechnung  {

    private final String nr;
    private final List<Position> positionen;

    public Rechnung(String nr){
        this.nr = nr;
        this.positionen = new ArrayList<Position>();
    }

    public String getNr() {
        return nr;
    }

    public void add(Position pos) {
        this.positionen.add(pos);
    }

    public int betrag(){
        // TODO
        return -1;
    }

    @Override
    public String toString() {
        return "Rechnung[" +
                "nr='" + nr + '\'' +
                 positionen +
                ']';
    }

    /**
     * Nur Methoden des Java-Streamen API nutzen.
     */
    public int betrag(){
        return gesamtwert;
    }

}
