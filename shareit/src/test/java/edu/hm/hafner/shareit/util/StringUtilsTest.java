package edu.hm.hafner.shareit.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet die Klasse {@link StringUtils}.
 *
 * @author Ulli Hafner
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
        assertFalse("Dieser String enthält nicht nur Whitespace", StringUtils.isBlank("Nicht nur Whitespace"));
//        assertTrue("Dieser String ist null", StringUtils.isBlank(null));
    }

    /**
     * Prüft, ob alle Zeichen vom Eingabestring,
     * die im String toBeRemoved festgelegt worden sind,
     * entfernt wurden. dlsjklö
     *
     */
    @Test
    public void testeZeichenAusEinemStringEntfernen() {
        assertEquals("Hao", StringUtils.strip("Hallo", "ll"));
        assertEquals("D i in T, dr kl", StringUtils.strip("Das ist ein Test, der klappt", "astep"));
        assertEquals("Auch mit Buchstaben, die nicht enthalten sind gehts.",
                StringUtils.strip("Auch mit Buchstaben, die nicht enthalten sind gehts.", "xyzwrj"));
        assertNotSame("Hallo", StringUtils.strip("Hallo", "ll"));
    }

    /**
     * Prüft, ob aus übergebenen String ein einzelner String nach
     * Vorgabe erzeugt wird.
     */
    @Test
    public void testeStringAusEinzelnenStringsZusammensetzen() {
        assertEquals("H, a, l, l, o", StringUtils.join("H", "a", "l", "l", "o"));
        assertEquals("H, a, (null), (null), o", StringUtils.join("H", "a", null, null, "o"));
        assertEquals(null, StringUtils.join());
    }

    /**
     * Prüft, ob das Passwort (String) der Vorgabe entspricht.
     */
    @Test
    public void testePasswortAufVorgabe() {
        assertTrue("Dieses Passwort sollte funktionieren", StringUtils.isSecure("Tobias98_ist_in_Ordnung!"));
        assertTrue("Dieses Passwort sollte funktionieren", StringUtils.isSecure("Abcdefg12345671#?18192021"));
        assertFalse("Dieses Passwort sollte nicht funktionieren", StringUtils.isSecure("ZuKleines!Passwort1"));
//        assertFalse("Dieses Passwort sollte nicht funktionieren", StringUtils.isSecure("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"));
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
    }

    /**
     * Prüft, ob es sich bei der übergebenen ISBN um eine korrekte 13 stellige ISBN handelt.
     * Der Benutzer darf Whitespace oder Minuszeichen zur Formatierung beliebig einset- zen.
     * Zur Prüfung der Gültigkeit langt die Validierung der Prüfziffer aus.
     */
    @Test
    public void testeIsbn13AufKorrektheit() {
       assertTrue("Diese ISBN13 sollte korrekt sein:", StringUtils.isVAlidIsbn13("978-3-12-732320-7"));
    }
}

