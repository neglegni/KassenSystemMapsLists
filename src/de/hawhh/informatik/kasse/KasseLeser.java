package de.hawhh.informatik.kasse;

import de.hawhh.informatik.kasse.Kasse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
         * Scanner: Rechnungen Zeilenweise verarbeiten
         * Zeilen Mittels split und regex zerlegen (führende u endende Leerzeichen, Leerzeilen überlesen.)
         *
         * pro Zeile 1 Rechnungsobjekt indem die Position hinzugefügt wird
         *
         * Sammeln der Rechnungsobjekt im Klassenobjekt --> als ergebnis der methode zurückgeben
         *
         * */

        sc.close();
        return kasse;
    }

}
