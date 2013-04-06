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
     * @return  true: String besteht nur aus Whitespace
     *          false: String besteht nicht nur aus Whitspacefjdasklöfj
     */
    public static boolean isBlank(final String eingabe) {

        if(eingabe.trim().length() == 0) {
            return true;
        }
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
     * @param toBeRemoved
     * @return newString
     */
    public static String strip(final String eingabe, final String toBeRemoved) {

        StringBuffer newString = new StringBuffer(eingabe);
        char[] toRemoveChars = toBeRemoved.toCharArray();

        for(int i=0; i<newString.length(); i++) {
            for(int j=0; j<toRemoveChars.length; j++) {

                if(newString.charAt(i) == toRemoveChars[j]) {
                    newString.deleteCharAt(i);
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
     * Konkateniert die übergebe- nen Strings zu einem neuen String.
     * Die einzelnen Elemente werden durch ein Komma getrennt.
     * Mindestens ein Element muss übergeben werden.
     * Einzelne Elemente können null sein, und sollen dann durch „(null)“ dargestellt werden.
     * Vor dem ersten, bzw. nach dem letzten Element darf kein Komma erscheinen.
     *
     * @param elements
     * @return newString
     */
    public static String join(final String... elements) {

        if(elements.length < 1) {
            return null;
        }

        StringBuffer newString = new StringBuffer();

        for(int i=0; i<elements.length; i++) {

            if(elements[i] == null) {
                newString.append("("+elements[i]+")");
            }
            else {
                newString.append(elements[i]);
            }

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
     * @return  true: PasswortEingabe ist korrekt
     *          false: PasswortEingabe ist nicht korrekt
     */

    /**
     * Es fehlt die 10 verschiedene Zeichen erkennung
     *
     *
     */

    public static boolean isSecure(final String passwortEingabe) {

        if(passwortEingabe.length() < 20) {
            return false;
        }
        else {
            Pattern p = Pattern.compile("^[[A-Z][a-z][0-9][\\p{Punct}]]{20,}$");
            Matcher m = p.matcher(passwortEingabe);

            if(!m.matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 10 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einsetzen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus. 3-680-08783-7
     *
     * @param isbnEingabe
     * @return  true: ISBN10 ist korrekt
     *          false: ISBN10 ist nicht korrekt
     */

    /**
     * Verstehe nicht ganz warum du es so aufwendig gestaltest...
     *
     *
     */
    public static boolean isValidIsbn10(final String isbnEingabe) {

        if(isbnEingabe == null || isbnEingabe.length() <= 10) {
            return false;
        }

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(isbnEingabe);
        String string = "";

        while(m.find()) {
            string = string.concat(isbnEingabe.substring(m.start(), m.end()));
        }

        if(string.length() < 10) {
            return false;
        }

        int[] intArray = new int[string.length()];

        for(int i=0; i<string.length(); i++) {
            intArray[i] = Character.digit(string.charAt(i), 10);
        }

        int check_digit = intArray[intArray.length-1];
        int compare_digit = 0;

        for(int j=0; j<intArray.length; j++) {

            if(j<intArray.length-1) {
                compare_digit += ((j+1)*intArray[j]);
            }
        }

        compare_digit = compare_digit % 11;

        if(check_digit != compare_digit) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 13 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einset- zen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     *
     * @param isbnEingabe
     * @return  true: ISBN13 ist korrekt
     *          false: ISBN13 ist nicht korrekt
     */
    public static boolean isVAlidIsbn13(final String isbnEingabe) {

        if(isbnEingabe == null || isbnEingabe.length() < 13) {
            return false;
        }

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(isbnEingabe);
        String string = "";

        return true;
    }
}
