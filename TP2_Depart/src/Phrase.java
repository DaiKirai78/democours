// VOUS POUVEZ MODIFIER CE FICHIER
public class Phrase {
    private Mot premier, dernier;
    private int nbMots;

    public Phrase() {
        // N'hésitez pas à modifier ce constructeur au besoin.
        // Vos changements seront automatiquement appliqués au constructeur ci-dessous.
        premier = dernier = null;
        nbMots = 0;
    }

    public Phrase(String str) {
        // Cette fonction initialise la phrase en ajoutant chaque mot de 'str' un par un.
        // Vous devrez coder la méthode ajouter(Mot mot) pour que cela fonctionne.
        this();
        String[] mots = str.split("\s");
        for (String mot : mots)
            ajouter(new Mot(mot));
    }

    public void ajouter(String str) {
        ajouter(new Phrase(str));
    }

    public boolean inserer(String str, int indexMot) {
        return inserer(new Phrase(str), indexMot);
    }

    // AJOUTEZ VOTRE CODE CI-DESSOUS

    //Méthode pour ajouter une lettre à la fin du dernier mot.
    public void ajouter(char c) {
        if (c == ' ') {
            ajouter(new Mot(""));
        } else if (dernier == null) {
            ajouter(new Mot(Character.toString(c)));
        } else {
            dernier.ajouter(c);
        }
    }

    //Méthode pour ajouter un mot à la fin de la liste
    public void ajouter(Mot mot) {
        if (premier == null) {
            premier = mot;
            dernier = mot;
        } else {
            dernier.suivant = mot;
            dernier = mot;
        }
        nbMots++;
    }

    //Méthode pour ajouter une phrase à la fin d'une phrase.
    public void ajouter(Phrase autre) {
        Mot motActuel = autre.premier;
        while (motActuel != null) {
            ajouter(new Mot(motActuel.toString()));
            motActuel = motActuel.suivant;
        }
    }

    //Méthode pour insérer une lettre dans un mot
    public boolean inserer(char c, int indexMot, int indexLettre) {
        Mot mot = getMot(indexMot);
        if (mot == null)
            return false;

        if (indexLettre > mot.getLongueur() || indexLettre < 0)
            return false;
        mot.inserer(c, indexLettre);
        return true;
    }

    //Méthode pour insérer un mot dans une phrase.
    public boolean inserer(Mot mot, int indexMot) {
        if (indexMot < 0 || indexMot > nbMots) {
            return false;
        }
        if (indexMot == 0) {
            mot.suivant = premier;
            premier = mot;
            if (dernier == null) {
                dernier = mot;
            }
        } else if (indexMot == nbMots) {
            dernier.suivant = mot;
            dernier = mot;
        } else {
            Mot motActuel = premier;
            for (int i = 0; i < indexMot - 1; i++) {
                motActuel = motActuel.suivant;
            }
            mot.suivant = motActuel.suivant;
            motActuel.suivant = mot;
        }
        nbMots++;
        return true;
    }

    //Méthode pour insérer une phrase dans une autre phrase.
    public boolean inserer(Phrase autre, int indexMot) {
        if (indexMot < 0 || indexMot > nbMots)
            return false;

        if (autre.premier == null)
            return false;

        if (indexMot == 0) {
            autre.dernier.suivant = premier;
            premier = autre.premier;
            if (dernier == null) {
                dernier = autre.dernier;
            }
        } else if (indexMot == nbMots) {
            dernier.suivant = autre.premier;
            dernier = autre.dernier;
        } else {
            Mot motActuel = premier;
            for (int i = 0; i < indexMot - 1; i++) {
                motActuel = motActuel.suivant;
            }
            Mot motSuivant = motActuel.suivant;
            motActuel.suivant = autre.premier;
            autre.dernier.suivant = motSuivant;
        }
        nbMots += autre.nbMots;
        return true;
    }

    //Méthode qui retourne le nombre de lettres dans une phrase
    public int getLongueur() {
        int nombreLettreTotal = 0;
        for (Mot courant = premier; courant != null; courant = courant.suivant) {
            nombreLettreTotal = nombreLettreTotal + courant.getLongueur();
            if(courant.suivant == null)
                return nombreLettreTotal+1;
        }
        return 0;
    }

    //Méthode qui retourne le mot à l'index précisé.
    public Mot getMot(int indexMot) {
        int indexTmp = 0;
        for (Mot courant = premier; courant != null; courant = courant.suivant) {
            if (indexTmp == indexMot) {
                return courant;
            }
            indexTmp++;
        }
        return null;
    }

    //Méthode qui retourne la lettre d'un mot à un index précisé.
    public char getLettre(int indexMot, int indexLettre) {
        int indexTmp = 0;
        for (Mot courant = premier; courant != null; courant = courant.suivant) {
            if (indexTmp == indexMot)
                return courant.getLettre(indexLettre);
            indexTmp++;
        }
        return '?';
    }

    //Méthode qui retourne la lettre d'une phrase à un index précisé.
    public char getLettre(int indexLettre) {
        if (indexLettre < 0 || indexLettre >= getLongueur()) {
            return '?';
        }
        Mot motActuel = premier;
        int lettreActuelle = 0;
        while (motActuel != null) {
            for (int i = 0; i < motActuel.getLongueur(); i++) {
                if (lettreActuelle == indexLettre) {
                    return motActuel.getLettre(i);
                }
                lettreActuelle++;
            }
            lettreActuelle++; // Ajouter un espace
            motActuel = motActuel.suivant;
        }
        return '?';
    }

    //Getter pour obtenir le nombre de mots
    public int getNbMots() {
        return nbMots;
    }

    public String toString() {
        String resultat = "";
        Mot motActuel = premier;
        while (motActuel != null) {
            resultat += motActuel;
            motActuel = motActuel.suivant;
        }
        return resultat;
    }
}