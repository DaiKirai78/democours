import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhraseTest {

    Mot merci, prince, an;
    Phrase chaine1, chaine2, chaine3;

    @Test
    public void testAjouter() {
        merci = new Mot("Merci");
        prince = new Mot("prince");
        chaine1 = new Phrase();
        chaine1.ajouter(merci);
        chaine1.ajouter(prince);
        chaine1.ajouter('s');
        chaine1.ajouter('s');
        chaine1.ajouter('e');
        assertEquals("Merci princesse ", chaine1.toString());
        assertEquals(2, chaine1.getNbMots());
        assertEquals(15, chaine1.getLongueur());
    }

    @Test
    public void testInsererLettre() {
        chaine2 = new Phrase("et un");
        an = new Mot("an");
        chaine2.inserer(an, 1);
        chaine2.inserer('s', 0, 1);
        chaine2.inserer('d', 1, 0);
        chaine2.inserer('s', 1, 3);
        assertEquals("est dans un ", chaine2.toString());
        assertEquals(3, chaine2.getNbMots());
        assertEquals(10, chaine2.getLongueur());
    }

    @Test
    public void ajouterPhrase() {
        chaine3.ajouter("mais");
        assertEquals("mais", chaine3.toString());
    }
    @Test
    public void testInsererPhrase() {
        chaine3 = new Phrase();
        chaine3.inserer(chaine1, 0);
        chaine3.inserer(',', 1, 9);
        chaine3.inserer("autre", 1);
        chaine3.inserer("Mari", 4);
        chaine3.ajouter('o');
        chaine3.ajouter(' ');
        chaine3.ajouter('s');
        chaine3.ajouter('t');
        chaine3.inserer('e', 5, 0);
        chaine3.ajouter("où?");
        assertEquals("mais Merci, autre Mari princesse où? ", chaine3.toString());
        assertEquals(9, chaine3.getNbMots());
        assertEquals(39, chaine3.getLongueur());
    }
}