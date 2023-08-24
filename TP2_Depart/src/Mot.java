// VOUS POUVEZ MODIFIER CE FICHIER
public class Mot {
    private char[] lettres;
    private int nbLettres;
    private int capacite;
    public Mot suivant = null;

    public Mot() {
        lettres = new char[0];
        nbLettres = 0;
        capacite = 0;
    }

    public Mot(String str) {
        assert str.indexOf(' ') == -1 : "Un mot ne peut contenir d'espaces";
        lettres = str.toCharArray();
        nbLettres = str.length();
        capacite = nbLettres;
    }

    // AJOUTEZ VOTRE CODE CI-DESSOUS
    //Méthode toString qui retourne le mot avec un espace ou sans, selon s'il en a déjà un ou non (Certains en ont déjà).
    @Override
    public String toString() {
        char[] result = new char[nbLettres];
        for (int i = 0; i < nbLettres; i++) {
            result[i] = lettres[i];
            if (result[nbLettres-1] == ' ')
                return new String(result);
        }
        return new String(result) + " ";
    }

    //Obtenir la longueur d'un mot
    public int getLongueur() {
        return nbLettres;
    }

    //Obtenir le caractère à l'index spécifié
    public char getLettre(int index) {
        for (int i = 0; i < nbLettres; i++)
            if (i == index)
                return lettres[i];
        return '?';
    }

    //Ajouter une lettre à la fin du mot
    public void ajouter(char lettre) {
        if (nbLettres == capacite) {
            capacite = (capacite == 0) ? 1 : (capacite * 2 + 1);
            char[] newLettres = new char[capacite];
            for (int i = 0; i < nbLettres; i++) {
                newLettres[i] = lettres[i];
            }
            lettres = newLettres;
        }
        lettres[nbLettres] = lettre;
        nbLettres++;
    }

    //Ajouter une lettre à l'index spécifié
    public boolean inserer(char lettre, int index) {
        if (index < 0 || index > nbLettres) {
            return false;
        }
        if (nbLettres == capacite) {
            capacite = (capacite == 0) ? 1 : (capacite * 2 + 1);
            char[] newLettres = new char[capacite];
            for (int i = 0; i < index; i++) {
                newLettres[i] = lettres[i];
            }
            newLettres[index] = lettre;
            for (int i = index; i < nbLettres; i++) {
                newLettres[i+1] = lettres[i];
            }
            lettres = newLettres;
        } else {
            for (int i = nbLettres; i > index; i--) {
                lettres[i] = lettres[i-1];
            }
            lettres[index] = lettre;
        }
        nbLettres++;
        return true;
    }
}