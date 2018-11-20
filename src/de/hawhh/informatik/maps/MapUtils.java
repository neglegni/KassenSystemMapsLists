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
    public static Map<Integer,Integer> intersect(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
       //TODO
        return null;
    }

    /**
     * Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Keys enthalten sind, die in m1 und m2 gleich sind.
     * Die Werte (ggf. unterschiedliche in m1 und m2) zu gleichen Keys werden in einer Menge gesammelt.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer,Set<Integer>> intersectJoinValues(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        //TODO
        return null;
    }

    /**
     *Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Key-Value Paare eingetragen werden,
     * - die in m1 und m2 gleich sind oder
     * - deren Key nur m1 oder nur im m2 vorkommt.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer,Integer> union(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        //TODO
        return null;
    }

    /**
     *Die Methode erzeugt aus m1 und m2 eine neue Map, in der alle Keys enthalten sind, die in m1 und m2 gleich sind.
     * Die Werte (ggf. unterschiedliche in m1 und m2) zu gleichen Keys werden in einer Menge gesammelt.
     * Für Keys, die nur m1 oder m2 vorkommen, ist der Wert der Ergebnis-Map eine Menge mit einem Element.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Map<Integer,Set<Integer>> unionJoinValues(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        //TODO
        return null;
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
    public static Map<Integer,Integer> complement(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        //TODO
        return null;
    }

    /**
     * Die Methode prüft, ob m1 eine Teil-Map von m2 ist. Das ist nur der Fall, wenn alle Paare
     * in m1 auch in m2 enthalten sind. Gleiche Schlüssel reichen nicht aus.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean isSubMap(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        //TODO
        return false;
    }

}
