package de.hawhh.informatik.maps;

import java.util.*;

public class MapUtils {


    /**
     * Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Key-Value Paare eingetragen werden,
     * die in m1 und m2 gleich sind. Gleiche Keys reichen nicht aus.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer, Integer> intersect(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        Map<Integer, Integer> ergebnis = new HashMap<>();

        for (Integer schluessel : m1.keySet()) {
            if (m2.containsKey(schluessel) && m2.get(schluessel).equals(m1.get(schluessel))) {
                ergebnis.put(schluessel, m2.get(schluessel));
            }
        }
        return ergebnis;
    }

    /**
     * Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Keys enthalten sind, die in m1 und m2 gleich sind.
     * Die Werte (ggf. unterschiedliche in m1 und m2) zu gleichen Keys werden in einer Menge gesammelt.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer, Set<Integer>> intersectJoinValues(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        Map<Integer, Set<Integer>> ergebnis = new HashMap<>();

        for (Integer schluessel1 : m1.keySet()) {
            Integer wert1 = m1.get(schluessel1);
            for (Integer schluessel2 : m2.keySet()) {
                Integer wert2 = m2.get(schluessel2);
                if (schluessel1.equals(schluessel2)) {
                    ergebnis.put(schluessel1, new HashSet<>(Arrays.asList(wert1)));
                    ergebnis.get(schluessel1).add(wert2);
                }
            }
        }
        return ergebnis;
    }

    /**
     * Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Key-Value Paare eingetragen werden,
     * - die in m1 und m2 gleich sind oder
     * - deren Key nur m1 oder nur im m2 vorkommt.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer, Integer> union(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        Map<Integer, Integer> ergebnis = new HashMap<>();
        if (m1.isEmpty()){
            ergebnis=m2;
        } else if (m2.isEmpty()){
            ergebnis=m1;
        }
        for (Integer schluessel1 : m1.keySet()) {
            Integer wert1 = m1.get(schluessel1);
            if (!m2.containsKey(schluessel1)) {
                ergebnis.put(schluessel1, wert1);
            }
            for (Integer schluessel2 : m2.keySet()) {
                Integer wert2 = m2.get(schluessel2);
                if (schluessel1.equals(schluessel2) && wert1.equals(wert2)) {
                    ergebnis.put(schluessel1, wert1);
                }
                if (!m1.containsKey(schluessel2)) {
                    ergebnis.put(schluessel2, wert2);
                }
            }
        }
        return ergebnis;
    }

    /**
     * Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Keys enthalten sind, die in m1 und m2 gleich sind.
     * Die Werte (ggf. unterschiedliche in m1 und m2) zu gleichen Keys werden in einer Menge gesammelt.
     * Für Keys, die nur m1 oder m2 vorkommen, ist der Wert der Ergebnis-Map eine Menge mit einem Element.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer, Set<Integer>> unionJoinValues(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        Map<Integer, Set<Integer>> ergebnis = new HashMap<Integer, Set<Integer>>();
        if (m1.isEmpty()){
            for (Integer schluessel2 : m2.keySet()){
                ergebnis.put(schluessel2,new HashSet<>(Arrays.asList(m2.get(schluessel2))));
            }
        } else if (m2.isEmpty()){
            for (Integer schluessel1 : m1.keySet()){
                ergebnis.put(schluessel1, new HashSet<>(Arrays.asList(m2.get(schluessel1))));
            }
        }
        for (Integer schluessel1 : m1.keySet()) {
            Integer wert1 = m1.get(schluessel1);
            if (!m2.containsKey(schluessel1)) {
                ergebnis.put(schluessel1, new HashSet<>(Arrays.asList(wert1)));
            }
            for (Integer schluessel2 : m2.keySet()) {
                Integer wert2 = m2.get(schluessel2);
                if (schluessel1.equals(schluessel2)) {
                    ergebnis.put(schluessel1, new HashSet<>(Arrays.asList(wert1)));
                    if (!wert1.equals(wert2)) {
                        ergebnis.get(schluessel1).add(wert2);
                    }
                }
                if (!m1.containsKey(schluessel2)) {
                    ergebnis.put(schluessel2, new HashSet<>(Arrays.asList(wert2)));
                }
            }
        }
        return ergebnis;
    }

    /**
     * Die Methode bildet die Differenz aus m1 und m2 in einer neuen Map ab.
     * Es werden nur die Paare in m1 ausgeschlossen, die mit Paaren in m2 gleich sind.
     * Gleiche Schlüssel reichen nicht aus.
     *
     * @param m1
     * @param m2
     * @return
     */
    // m1-m2 Differenz
    public static Map<Integer, Integer> complement(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        Map<Integer, Integer> ergebnis = new HashMap<>();

        for (Integer schluessel1 : m1.keySet()) {
            Integer wert1 = m1.get(schluessel1);
            ergebnis.put(schluessel1,wert1);
            for (Integer schluessel2 : m2.keySet()) {
                Integer wert2 = m2.get(schluessel2);
                if (schluessel1.equals(schluessel2) && wert1.equals(wert2)) {
                    ergebnis.remove(schluessel1);
                }
            }
        }
        return ergebnis;
    }

    /**
     * Die Methode prüft, ob m1 eine Teil-Map von m2 ist. Das ist nur der Fall, wenn alle Paare
     * in m1 auch in m2 enthalten sind. Gleiche Schlüssel reichen nicht aus.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean isSubMap(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        boolean istSubMap = true;
        for (Integer schluessel1 : m1.keySet()) {
            Integer wert1 = m1.get(schluessel1);
            if (!m2.containsKey(schluessel1)){
                return false;
            }
            if (!m2.get(schluessel1).equals(wert1)){
                return false;
            }
        }
        return istSubMap;
    }

}
