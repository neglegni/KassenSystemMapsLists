package de.hawhh.informatik.kasse;

import de.hawhh.informatik.kasse.Kasse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KasseLeser {

    private String uri;
    public KasseLeser(String uriAsString){
            this.uri = uriAsString;
    }

    public Kasse leseKasse() throws IOException {
        Scanner sc = new Scanner(new URL(uri).openStream(), StandardCharsets.UTF_8);
        Kasse kasse = new Kasse("Ristorante Nero");

        // TODO Rechnungen einlesen
        /**
         * Scanner: Rechnungen Zeilenweise verarbeiten */
        String zeile;
        // solang es eine nächste Zeile gibt
        while (sc.hasNextLine()) {
            // weise diese dem String zeile hinzu
            zeile = sc.nextLine();
            // bereinige zeilen von Zeilenumbrüchen --> Zeilen werden als empty erkannt
            String zeileOhneUmbruch = zeile.replaceAll("\\n","");
            // wenn zeile nicht leer ist
            if (!zeileOhneUmbruch.isEmpty()) {
                // zerteile die zeile bei trennzeichen
                String[] einzelteile = zeileOhneUmbruch.split("\\|\\|");
                // Erstes Element der Einzelteile ist die Rechnungsnummer.
                Rechnung rechnung = new Rechnung(einzelteile[0].trim());
                // jedes element der einzelteile
                for (String elem: einzelteile){
                    // wenn das element zeichen ";" enthält
                    if (elem.contains(";")){
                        // teile die positionen am trennzeichen
                        String[] posAry = elem.split(";");
                        // neue position wird erstellt, parameter vorher von leerzeichen befreit und typkonform gemacht.
                        Position pos = new Position(posAry[0].trim(),Integer.parseInt(posAry[1].trim()));
                        // füge neue pos zur rechnung hinzu
                        rechnung.add(pos);
                    }
                }
            }
        }
        // schließe scanner
        sc.close();
        // gib kasse zurück
        return kasse;
    }

}
