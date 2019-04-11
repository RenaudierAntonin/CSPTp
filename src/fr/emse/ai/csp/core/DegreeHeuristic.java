package fr.emse.ai.csp.core;

import java.util.ArrayList;

/**
 * Cette classe n'est pas utilisée car elle est sans conséquence sur le choix des variables dans ce problèmes
 * On l'utilise comme critère que choix en cas d'égalité des variables sur le MRV
 */
public class DegreeHeuristic {

    public static Variable selectDegreeHeuristic(Assignment assignment, CSP csp, Variable var1, Variable var2) {


        //On est sensé prendre la variable avec le plus contrainte avec les variables
        //Cependant elles ont toutes le même nombre de contrainte ici
        //Donc c'est inutile pour ce problème
        double constraintVar1 = csp.getConstraints(var1).size();
        double constraintVar2 = csp.getConstraints(var2).size();
        if (constraintVar1 > constraintVar2)
            return var1;
        else
            return var2;
    }
}
