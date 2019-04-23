package fr.emse.ai.csp.core;

import java.util.Comparator;

/**
 * Cette classe permet de faire un comparator entre deux valeurs selon leur disponibilités
 */
public class ValueDisponibilityComparator implements Comparator {

    /**
     * Fonction qui permet de comparer deux valeurs en fonction de leur disponibilité
     * @param o1 Couple value disponibility 1
     * @param o2 Couple value disponibility 2
     * @return Retourne 1 si 1 est plus grand que 2, 0 si 1 égal 2 et -1 sinon
     */
    public int compare(Object o1, Object o2) {
        double d1 = ((ValueDisponibility) o1).getDisponibility();
        double d2 = ((ValueDisponibility) o2).getDisponibility();
        if (d1<d2) {
            return -1;
        }
        else if (d1==d2) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
