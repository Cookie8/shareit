package edu.hm.hafner.shareit.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet die Klasse {@link StringUtils}.
 *
 * @author Tobias Kuchenbecker & Thorben Knichwitz
 */
public class StringUtilsTest {
    /**
     * Prüft, ob die beiden gültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeGueltigeLeereStrings() {
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(null));
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(""));
    }

    /**
     * Prüft, ob ungültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeLeereStringsMitZeichen() {
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty(" "));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("\t"));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("Hallo"));
    }

    /**
     * Prüft, ob der übergebene String nur Whitespace enthält.
     */
    @Test
    public void testeStringAufWhitespace() {
        assertTrue("Dieser String enthält nur Whitespace", StringUtils.isBlank("     "));
        assertTrue("Dieser String enthält nur Whitespace", StringUtils.isBlank("\t"));
        assertFalse("Dieser String enthält nicht nur Whitespace", StringUtils.isBlank("Nicht nur Whitespace"));
    }


    /**
     * Prüft, ob alle Zeichen vom Eingabestring,
     * die im String toBeRemoved festgelegt worden sind,
     * entfernt wurden.
     *
     */
    @Test
    public void testeZeichenAusEinemStringEntfernen() {
        assertEquals("Dieser Test sollte klappen", "Hao", StringUtils.strip("Hallo", "ll"));
        assertEquals("Dieser Test sollte klappen", "D i in T, dr kl", StringUtils.strip("Das ist ein Test, der klappt", "astep"));
        assertEquals("Dieser Test sollte klappen", "Auch mit Buchstaben, die nicht enthalten sind gehts.",
                StringUtils.strip("Auch mit Buchstaben, die nicht enthalten sind gehts.", "xyzwrj"));
        assertNotSame("Dieser Test sollte nicht klappen", "Hallo", StringUtils.strip("Hallo", "ll"));
    }

    /**
     * Prüft, ob aus übergebenen String ein einzelner String nach
     * Vorgabe erzeugt wird.
     */
    @Test
    public void testeStringAusEinzelnenStringsZusammensetzen() {
        assertEquals("Dieser Test sollte funktionieren", "H, a, l, l, o", StringUtils.join("H", "a", "l", "l", "o"));
        assertEquals("Dieser Test sollte funktionieren", "H, a, (null), (null), o", StringUtils.join("H", "a", null, null, "o"));
        assertEquals("Dieser Test sollte funktionieren", null, StringUtils.join());
        assertNotSame("Dieser Test sollte nicht funktionieren", "Hallo", StringUtils.join("H", "a", "l", "l", "o"));
    }

    /**
     * Prüft, ob das Passwort (String) der Vorgabe entspricht.
     */
    @Test
    public void testePasswortAufVorgabe() {
        assertTrue("Dieses Passwort sollte funktionieren", StringUtils.isSecure("Das_Passwort1289!_ist_OK"));
        assertTrue("Dieses Passwort sollte funktionieren", StringUtils.isSecure("Tobias98_ist_in_Ordnung!"));
        assertTrue("Dieses Passwort sollte funktionieren", StringUtils.isSecure("Abcdefg12345671#?18192021"));
        assertFalse("Dieses Passwort sollte nicht funktionieren", StringUtils.isSecure("ZuKleines!Passwort1"));
        assertFalse("Dieses Passwort sollte nicht funktionieren", StringUtils.isSecure("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"));
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 10 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einset- zen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     */
    @Test
    public void testeIsbn10AufKorrektheit() {
        assertTrue("Diese ISBN10 sollte korrekt sein:", StringUtils.isValidIsbn10("3-680-08783-7"));
        assertTrue("Diese ISBN10 sollte korrekt sein:", StringUtils.isValidIsbn10("3-680   -  0878  3 7"));
        assertTrue("Diese ISBN10 sollte korrekt sein:", StringUtils.isValidIsbn10("3-446-19313-8"));
        assertTrue("Diese ISBN10 sollte korrekt sein:", StringUtils.isValidIsbn10("0-7475-5100-6"));
        assertTrue("Diese ISBN10 sollte korrekt sein:", StringUtils.isValidIsbn10("1-57231-422-2"));
        assertFalse("Diese ISBN10 sollte falsch sein:", StringUtils.isValidIsbn10("3-680-08783-7-88888-989898989"));
        assertFalse("Sollte false zurückbekommen, da nichts übergeben wurde:", StringUtils.isValidIsbn10(""));
        assertTrue("Diese ISBN10 sollte korrekt sein", StringUtils.isValidIsbn10("3-499-13599-X"));
        assertFalse("Sollte false zurückbekommen, da nichts übergeben wurde:", StringUtils.isValidIsbn10(null));
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 13 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einset- zen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     */
    @Test
    public void testeIsbn13AufKorrektheit() {
       assertTrue("Diese ISBN13 sollte korrekt sein:", StringUtils.isVAlidIsbn13("978-3-12-732320-7"));
       assertTrue("Diese ISBN13 sollte korrekt sein:", StringUtils.isVAlidIsbn13("9 7  8 - 3- 1 2-73 2 3 20 - 7"));
       assertTrue("Diese ISBN13 sollte korrekt sein:", StringUtils.isVAlidIsbn13("97 8 3 82 7 31 7 1 0 0"));
       assertFalse("Diese ISBN13 sollte nicht korrekt sein", StringUtils.isVAlidIsbn13("939394855943984837483748374384837"));
       assertFalse("Sollte false zurückbekommen, da nichts übergeben wurde:", StringUtils.isValidIsbn10(null));
       assertFalse("Sollte false zurückbekommen, da nichts übergeben wurde:", StringUtils.isValidIsbn10(""));
    }
}

