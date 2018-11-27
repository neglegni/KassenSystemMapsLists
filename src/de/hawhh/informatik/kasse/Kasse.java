package de.hawhh.informatik.kasse;

import java.util.*;
import java.util.stream.Collectors;

public class Kasse implements Iterable<Rechnung> {

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
        return rechnungen.stream().max(Comparator.comparing(Rechnung::betrag)).orElse(null);
    }

    /**
     * @return Summe aller Rechnungen
     */
    public int kassenStand() {
        int kassenStand = 0;
        for (Rechnung rechnung : rechnungen) {
            kassenStand = kassenStand + rechnung.betrag();
        }
        return kassenStand;
        // return rechnungen.stream().mapToInt(Rechnung::betrag).sum();
    }

    /**
     * erzeugt eine Tabelle deren Schlüssen die  Positionen und deren Werte Liste von Rechnungen sind.
     * Streaming API und foreach-Konstrukt um über Positionen zu iterieren nutzen.
     * @return
     */
    public Map<Position, List<Rechnung>> gruppiereNachProdukt() {
        Map<Position, List<Rechnung>> ergebnis = new HashMap<>();
        rechnungen.forEach(rechnung ->
                rechnung.forEach(position -> {
                    // es wird eine neue Rechnungs Liste erzeugt und unserer HashMap gibt in jedem Fall eine leere
                    // ArrayList aus auch wenn die position nicht gefunden wurde
                    List<Rechnung> listeRechnungen = ergebnis.getOrDefault(position, new ArrayList<>());
                    //positionen werden der rechnung hinzugefügt
                    listeRechnungen.add(rechnung);
                    //zu jeder position erhalten wir die liste der Rechnungen
                    ergebnis.put(position, listeRechnungen);
                })
        );
        return ergebnis;
    }

    /**
     * Methode gruppiereNachProdukt nutzen und
     * - Pipeline auf dem EntrySet der Tabelle erzeugen.
     * - Key-Val-Paare mit Collectors.toMap() auf Tabelle abbilden.
     * @return
     */
    public Map<Position, List<String>> gruppiereNachProduktNurRechnungsNummern() {
        Map<Position, List<String>> ergebnisRnr = this.gruppiereNachProdukt()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(Rechnung::getNr).collect(Collectors.toList())));
        return ergebnisRnr;
    }

    /**
     * Methode gruppiereNachProdukt nutzen und
     *      * - Pipeline auf dem EntrySet der Tabelle erzeugen.
     *      * - daraus das gesuchte Produkt berechnen.
     * @return Optional<Map.Entry<Position, List<Rechnung>>>  - Produkt das am häufigsten bestellt wurde.
     *
     */
    public Position beliebtestesProdukt() {
        Position ergebnisProd = this.gruppiereNachProdukt()
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .get()
                .getKey();
        return ergebnisProd;
    }

    /**
     *
     * @return am häufigsten bestellt
     */
    public List<Position> beliebtesteProdukte() {
        List<Position> ergebnisProd = this.gruppiereNachProdukt()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return ergebnisProd;
    }

    /**
     * Bildet die Tabelle 4.c auf eine Tabelle ab, deren Schlüssel die Positionen und dern Werte die Anzahl
     * der Rechnungen sind (vorgehen wie gruppierenNachProduktNurRechnungsNummern(). )
     * @return
     */
    public Map<Position,Integer> produktHaeufigkeiten(){
        Map<Position, Integer> ergebnisProdHauef = this.gruppiereNachProdukt()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().size()));
        return ergebnisProdHauef;
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
        return new IteratorRechnung();
    }

    private class IteratorRechnung implements Iterator<Rechnung> {

        // die nächste zu lesende Position.
        private int readCursor = 0;
        // Indikator für das lesen mit next().
        private boolean nextGiven = false;

        @Override
        public boolean hasNext() {
            return readCursor<rechnungen.size();
        }

        @Override
        public Rechnung next() {
            if (!hasNext()) throw new NoSuchElementException();
            nextGiven = true;
            return rechnungen.get(readCursor++);
        }

        @Override
        public void remove() {
            if (!nextGiven) throw new IllegalStateException("kein nächstes Element");
            //--, da der readCursor hinter dem letzten gelesenen ELem steht.
            rechnungen.remove(--readCursor);
            //letztes gelesenes ELement wurde gelöscht. nextGiven wird zurückgesetzt
            nextGiven = false;
        }
    }

}
