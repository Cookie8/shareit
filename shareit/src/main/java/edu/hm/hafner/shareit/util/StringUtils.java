package edu.hm.hafner.shareit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enthält nützliche Funktionen, die auf {@link String} Objekten arbeiten.
 *
 * @author Ulli Hafner
 */
public final class StringUtils {

    /**
     * Prüft, ob der übergebene String leer ist, d.h. kein Zeichen enthält.
     *
     * @param value
     *            der zu prüfende String
     * @return <code>true</code> falls der String kein Zeichen enthält oder <code>null</code> ist, <code>false</code>
     *         andernfalls.
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Creates a new instance of {@link StringUtils}.
     *
     * @author Ulli Hafner
     */
    private StringUtils() {
        // prevents instantiation
    }

    /**
     * Prüft, ob der übergebene String nur Whitespace enthält.
     *
     * @param eingabe
     *              der zu prüfende String
     * @return  <code>true</code> falls der String nur Whitespace enthält
     *          <code>false</code> andernfalls.
     *
     * @author Cookie
     */
    public static boolean isBlank(final String eingabe) {

        // Falls der String nur Whitespace enthielt, hat er nach "tim()" die Länge 0.
        if(eingabe.trim().length() == 0) {
            return true;
        }
        // Falls der String nicht nur Whitspace enthielt.
        else {
            return false;
        }
    }

    /**
     * Thorben´s Ansatz
     */

    public static boolean isBlankt(final String input){
        return input.trim().isEmpty();
    }

    /**
     * Entfernt alle Zeichen vom Eingabestring, die im String toBeRemoved festgelegt worden sind.
     *
     * @param eingabe
     *              der zubearbeitende String
     * @param toBeRemoved
     *              der String mit den zuentfernenden Zeichen
     * @return newString
     *              der bearbeitete String
     *
     * @author Cookie
     */
    public static String strip(final String eingabe, final String toBeRemoved) {

        /*
         * Hilfsvariablen, um die zulöschenden Zeichen im
         * übergebenen String zu finden/löschen.
         */
        StringBuffer newString = new StringBuffer(eingabe);
        char[] toRemoveChars = toBeRemoved.toCharArray();

        // Schleife über String, der bearbeitet werden soll
        for(int i=0; i<newString.length(); i++) {

            // Schleife über Arry mit Zeichen, die gelöscht werden sollen
            for(int j=0; j<toRemoveChars.length; j++) {

                // Wenn der String das aktuelle Zeichen an der Stelle enthält
                if(newString.charAt(i) == toRemoveChars[j]) {

                    // Wird es gelöscht
                    newString.deleteCharAt(i);

                    /*
                     * Durch das Löschen eines Zeichen verkürzt sich die Länge
                     * des String. Damit alle Zeichen des Strings geprüft werden
                     * muss der Zähler verringert werden.
                     */
                    i--;
                }
            }
        }
        return newString.toString();
    }

    /**
     * Thorben´s Ansatz
     */
    public static String stript(final String input, final String toBeRemoved){
       String output =  input.replaceAll(toBeRemoved, "");
        return output;
    }

    /**
     * Konkateniert die übergebenen Strings zu einem neuen String.
     * Die einzelnen Elemente werden durch ein Komma getrennt.
     * Mindestens ein Element muss übergeben werden.
     * Einzelne Elemente können null sein, und sollen dann durch „(null)“ dargestellt werden.
     * Vor dem ersten, bzw. nach dem letzten Element darf kein Komma erscheinen.
     *
     * @param elements
     *              Strings, die zu einem einzelnen String zusammengesetzt werden
     * @return newString
     *              der neue, zusammengesetzte String
     *
     * @author Cookie
     */
    public static String join(final String... elements) {

        // falls kein String-Element übergeben wurde
        if(elements.length < 1) {
            return null;
        }

        // Hilfsvariable, um den neuen String zubauen
        StringBuffer newString = new StringBuffer();

        // Schleife über die übergebenen String-Elemente
        for(int i=0; i<elements.length; i++) {

            // Für null-wertige Elemente
            if(elements[i] == null) {
                newString.append("("+elements[i]+")");
            }

            // Elemente sonst
            else {
                newString.append(elements[i]);
            }

            // Für jedes Element, außer dem letzten
            if(i < elements.length-1) {
                newString.append(", ");
            }
        }
        return newString.toString();
    }

    /**
     * Thorben´s Ansatz
     *
     * ist derselbe, gesehen bei wikibooks
     */


    /**
     * Prüft, ob das übergebene Passwort sicher ist.
     * Sichere Passwörter sind mindestens 20 Zeichen lang und enthalten
     * mindestens einen Kleinbuchstaben, einen Großbuchstaben, eine Zahl und ein Sonderzeichen.
     * Zusätzlich müssen mindestens 10 verschiedene Zeichen im Passwort verwendet werden.
     *
     * @param passwortEingabe
     *                      Das Passwort zur Überprüfung
     * @return  <code>true</code> falls Passwort korrekt
     *          <code>falls</code> andernfalls
     *
     * @author Cookie
     */

    /**
     * Es fehlt die 10 verschiedene Zeichen erkennung
     *
     *
     */

    public static boolean isSecure(final String passwortEingabe) {

        // Wenn übergebener String zu kurz ist
        if(passwortEingabe.length() < 20) {
            return false;
        }
        else {

            /* Hilfsvariablen, um übergebenen String zu prüfen */
            char[] passwordToChars = passwortEingabe.toCharArray(); // einzelne Zeichen des Strings

            int hasUpperCase = 0;   // Zähler für Großbuchstaben -> muss min. 1 sein nach Prüfung
            int hasLowerCase = 0;   // Zähler für Kleinbuchstaben -> muss min. 1 sein nach Prüfung
            int hasDigit = 0;       // Zähler für Zahlen -> muss min. 1 sein nach Prüfung
            int hasSpecialCharacter = 0; // Zähler für Sonderzeichen -> muss min. 1 sein nach Prüfung
            int counter = 0;    // Zähler für Anzahl verschiedener Zeichen -> muss min. 10 nach Prüfung

            // 1. Schleife über die einzelnen Zeichen des Passwortes
            for(int i=0; i<passwordToChars.length; i++) {

                // Hilfsvariable, um Sonderzeichen zu finden
                String helpString = String.valueOf(passwordToChars[i]);

                // 2. Schleife über die einzelnen Zeichen des Passwortes, um Anzahl verschiedener Zeichen zu prüfen.
                for(int j=0; j<passwordToChars.length; j++) {

                    // Anzahl verschiedener Zeichen erhöhen
                    if(passwordToChars[i] == passwordToChars[j]) {
                        counter++;
                    }
                }

                // Anzahl Großbuchstaben erhöhen
                if(Character.isUpperCase(passwordToChars[i])) {
                    hasUpperCase++;
                }

                // Anzahl Kleinbuchstaben erhöhen
                else if(Character.isLowerCase(passwordToChars[i])) {
                    hasLowerCase++;
                }

                // Anzahl Zahlen erhöhen
                else if(Character.isDigit(passwordToChars[i])) {
                    hasDigit++;
                }

                // Anzahl Sonderzeichen erhöhen
                else if(helpString.matches("[\\p{Punct}]+")) {
                    hasSpecialCharacter++;
                }
            }

            /*
             * Wenn Anzahl
             *      verschiedener Zeichen,
             *      Großbuchstaben,
             *      Kleinbuchstaben,
             *      Zahlen,
             *      Sonderzeichen
             * NICHT erreicht sind.
             */
            if((counter<10) || (hasUpperCase<1) || (hasLowerCase<1) || (hasDigit<1) || (hasSpecialCharacter<1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 10 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einsetzen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     *
     * @param isbnEingabe
     *                  Die zu prüfende ISBN
     * @return  <code>ture</code> ISBN10 ist korrekt
     *          <code>falls</code> andernfalls
     *
     * @author Cookie
     */

    /**
     * Verstehe nicht ganz warum du es so aufwendig gestaltest...
     *
     *
     */
    public static boolean isValidIsbn10(final String isbnEingabe) {

        // Wenn Länge der ISBN nicht passt
        if(isbnEingabe == null || isbnEingabe.length() <= 10) {
            return false;
        }

        // Zahlen der ISBN in Hilfs-Array speichern
        int[] intArray = getIsbnCharactersToIntArray(isbnEingabe);

        // Hilfs-Variablen zur Berechnung der ISBN-Prüfziffer
        int check_digit = intArray[intArray.length-1];
        int compare_digit = 0;

        // 1. Schritt zur Berechnung der ISBN-Prüfziffer: Berechnung der Summe der ersten neun Zahlen
        for(int j=0; j<intArray.length-1; j++) {
            compare_digit += ((j+1)*intArray[j]);
        }

        // 2. Schritt zur Berechnung der ISBN-Prüfziffer: Berechnung des Divisions-Rests
        compare_digit = compare_digit % 11;

        return compareDigits(check_digit, compare_digit);
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 13 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einset- zen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     *
     * @param isbnEingabe
     *                  die zu prüfende ISBN
     * @return  <code>true</code> ISBN13 ist korrekt
     *          <code>false</code> andernfalls
     *
     * @author Cookie
     */
    public static boolean isVAlidIsbn13(final String isbnEingabe) {

        // Wenn Länge der ISBN nicht passt.
        if(isbnEingabe == null || isbnEingabe.length() < 13) {
            return false;
        }

        // Zahlen der ISBN in Hilfs-Array speichern
        int[] intArray = getIsbnCharactersToIntArray(isbnEingabe);

        // Hilfs-Variablen zur Berechnung der ISBN-Prüfziffer
        int check_digit = intArray[intArray.length-1];
        int compare_digit = 0;

        // 1. Schritt zur Berechnung der ISBN-Prüfziffer: Berechnung der Summe der ersten zwölf Zahlen
        for(int j=0; j<intArray.length; j++) {

            switch(j) {

                // Alle Zahlen, die mit eins multipliziert werden
                case 0:
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                    compare_digit += (intArray[j] * 1);
                    break;

                // Alle Zahlen, die mit drei multipliziert werden
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                    compare_digit += (intArray[j] * 3);
                    break;
            }
        }

        // 2. Schritt zur Berechnung der ISBN-Prüfziffer: Erhalt der letzten Zahl des Summen-Ergebnisses aus Schritt 1
        Integer i = compare_digit;
        String st = i.toString();
        char[] ch = st.toCharArray();
        String st2 = String.valueOf(ch[ch.length-1]);
        int i2 = Integer.parseInt(st2);


        // Bestimmen der ISBN-Prüfziffer
        if(i2==0) {
            compare_digit = 0;
        }
        else {
            for(int toCheck=1; toCheck<10; toCheck++) {
                if((i2+toCheck)==10) {
                    compare_digit = toCheck;
                    break;
                }
            }
        }

        return compareDigits(check_digit, compare_digit);
    }

    /**
     * Prüft, ob übergebene ISBN-Prüfziffer mit der errechneten übereinstimmt.
     *
     * @param check_digit
     *          Die übergebene ISBN-Prüfziffer
     * @param compare_digit
     *          Die errechnete ISBN-Prüfziffer
     * @return  <code>true</code> ISBN-Prüfziffern stimmen überein
     *          <code>false</code> andernfalls
     *
     * @author Cookie
     */
    private static boolean compareDigits(final int check_digit, final int compare_digit) {
        if(check_digit != compare_digit) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Schreibt die Zahlen aus dem übergebenen ISBN-String in ein (int-) Array.
     *
     * @param isbnEingabe
     *                  Der übergebene ISBN-String
     * @return intArray
     *                  Array der ISBN-Zahlen
     *
     * @author Cookie
     */
    private static int[] getIsbnCharactersToIntArray(final String isbnEingabe) {

        // Hilfe, um Zahlen aus der übergebenen ISBN zu filtern
        Pattern p = Pattern.compile("[0-9Xx]+");
        Matcher m = p.matcher(isbnEingabe);
        String helpString = "";

        // Nur Zahlen und "X bzw x" aus der übergebenen ISBN zur Weiterverarbeitung nutzen
        while(m.find()) {
            helpString = helpString.concat(isbnEingabe.substring(m.start(), m.end()));
        }

        // Hilfs-Array zur Überprüfung der ISBN-Prüfziffer
        int[] intArray = new int[helpString.length()];

        // Hilfs-Array mit Zahlen der ISBN füllen
        for(int i=0; i<helpString.length(); i++) {

            // Falls übergebene ISBN-Prüfziffer ein "X bzw. x" enthält
            if(helpString.charAt(i) == 'x' || helpString.charAt(i) == 'X') {
                intArray[i] = 10;
            }
            else {
                intArray[i] = Character.digit(helpString.charAt(i), 10);
            }
        }
        return intArray;
    }
}
