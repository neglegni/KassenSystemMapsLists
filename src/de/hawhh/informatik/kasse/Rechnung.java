package de.hawhh.informatik.kasse;

import java.util.*;

// TODO Iterierbar machen
public class Rechnung implements Iterable<Position> {

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

    //public int betrag(){
        // TODO
     //   return -1;
   // }

    @Override
    public String toString() {
        return "Rechnung[" +
                "nr='" + nr + '\'' +
                 positionen +
                ']';
    }


    /**
     * Nur Methoden des Java-Streamen API nutzen.
     *
    public int betrag(){
        return gesamtwert;
    }*/

    @Override
    public Iterator<Position> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<R> implements Iterator<Position> {

        // die nächste zu lesende Position.
        private int readCursor = 0;
        // Indikator für das lesen mit next().
        private boolean nextGiven = false;


        @Override
        public boolean hasNext() {
            return readCursor < positionen.size();
        }

        //liefert das nächste element und setzt nextGiven auf true.
        //sodass ein gültiger Zustand für das potentielle Löschen erreicht ist.
        @Override
        public Position next() {
            if (!hasNext()) throw new NoSuchElementException();
            nextGiven = true;
            return positionen.get(readCursor++);
        }

        public void remove() {
            if (!nextGiven) throw new IllegalStateException("kein nächstes Element");
            //--, da der readCursor hinter dem letzten gelesenen ELem steht.
            positionen.remove(--readCursor);
            //letztes gelesenes ELement wurde gelöscht. nextGiven wird zurückgesetzt
            nextGiven = false;
        }
    }
}
