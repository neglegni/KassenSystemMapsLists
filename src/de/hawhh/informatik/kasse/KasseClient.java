package de.hawhh.informatik.kasse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class KasseClient {

    // ACHTUNG: DIESEN PRAEFIX GGF AUF DIE EIGENE PROJEKTSTRUKTUR ANPASSEN
    private static final String RESOURCE_PREFIX = "out/production/A3-WS1819/";
    private static final String RESOURCE_NAME= "rechnungen.sv";

    public static void main(String[] args) throws IOException {
        String rechnungenUri = Paths.get( RESOURCE_PREFIX +RESOURCE_NAME).toAbsolutePath().toUri().toString();
        Kasse kasse = new KasseLeser(rechnungenUri).leseKasse();

        System.out.println("\nKASSE UND RECHNUNGEN\n");
        System.out.println(kasse.getName());
        //  TODO Fehler da Kasse und Rechnung noch nicht iterierbar sind
       /** for (Rechnung rechnung : kasse){
            for(Position p : rechnung) {
                System.out.println(p);
            }
        }*/

        System.out.println("\nHOECHSTE RECHNUNG\n");
        System.out.println(kasse.hoechsteRechnung());

        System.out.println("\nKASSENSTAND\n");
        System.out.println(kasse.kassenStand());

        System.out.println("\nGRUPPIEREN NACH PRODUKT\n");
        Map<Position,List<Rechnung>> mplr = kasse.gruppiereNachProdukt();
        for (Map.Entry<Position,List<Rechnung>> entry: mplr.entrySet()) {
            System.out.println(entry.getKey() +" => " + entry.getValue());
        }
        System.out.println("\nGRUPPIEREN NACH PRODUKT NUR RECHNUNGSNUMMERN\n");
        Map<Position,List<String>> mprnr = kasse.gruppiereNachProduktNurRechnungsNummern();
        for (Map.Entry<Position,List<String>> entry: mprnr.entrySet()) {
            System.out.println(entry.getKey() +" => " + entry.getValue());
        }

        System.out.println("\nPRODUKTHAEUFIGKEITEN\n");
        Map<Position,Integer> mpi = kasse.produktHaeufigkeiten();
        for (Map.Entry<Position,Integer> entry: mpi.entrySet()) {
            System.out.println(entry.getKey() +" => " + entry.getValue());
        }

        System.out.println("\nBELIEBTESTES PRODUKT: " +   kasse.beliebtestesProdukt());
        System.out.println("\nBELIEBTESTE PRODUKTE: " + kasse.beliebtesteProdukte());
    }
}
