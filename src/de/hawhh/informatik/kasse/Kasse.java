package de.hawhh.informatik.kasse;

import java.util.*;

// TODO Iterierbar machen
public class Kasse implements Iterable<Position> {

    private final String name;
    private final List<Rechnung> rechnungen;

    public Kasse(String name) {
        this.name = name;
        this.rechnungen = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void add(Rechnung rechnung) {
        this.rechnungen.add(rechnung);
    }

    /**
     * @return Rechnung größter betrag
     */
    public Rechnung hoechsteRechnung() {
        // TODO
        return null;
    }

    /**
     * @return Summe aller Rechnungen
     */
    public int kassenStand() {
        // TODO
        return -1;
    }

    /**
     * erzeugt eine Tabelle deren Schlüssen die  Positionen und deren Werte Liste von Rechnungen sind.
     * Streaming API und foreach-Konstrukt um über POsitionen zu iterieren nutzen.
     * @return
     */
    public Map<Position, List<Rechnung>> gruppiereNachProdukt() {
        // TODO
        return null;
    }

    /**
     * Methode gruppiereNachProdukt nutzen und
     * - Pipeline auf dem EntrySet der Tabelle erzeugen.
     * - Key-Val-Paare mit Collectors.toMap() auf Tabelle abbilden.
     * @return
     */
    public Map<Position, List<String>> gruppiereNachProduktNurRechnungsNummern() {
        // TODO
        return null;
    }

    /**
     * Methode gruppiereNachProdukt nutzen und
     *      * - Pipeline auf dem EntrySet der Tabelle erzeugen.
     *      * - daraus das gesuchte Produkt berechnen.
     * @return Optional<Map.Entry<Position, List<Rechnung>>>  - Produkt das am häufigsten bestellt wurde.
     *
     */
    public Position beliebtestesProdukt() {
        // TODO
        return null;
    }

    /**
     *
     * @return am häufigsten bestellt
     */
    public List<Position> beliebtesteProdukte() {
      // TODO
      return null;
    }

    /**
     * Bildet die Tabelle 4.c auf eine Tabelle ab, deren Schlüssel die Positionen und dern Werte die Anzahl
     * der Rechnungen sind (vorgehen wie gruppierenNachProduktNurRechnungsNummern(). )
     * @return
     */
    public Map<Position,Integer> produktHaeufigkeiten(){
      // TODO
        return null;
    }

    @Override
    public String toString() {
        return "Kasse{" +
                "name='" + name + '\'' +
                ", rechnungen=" + rechnungen +
                '}';
    }


    @Override
    public Iterator<Rechnung> iterator() {
        return new IteratorRechnung<>();
    }

    private class IteratorRechnung implements Iterator<Rechnung> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Rechnung next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

}
