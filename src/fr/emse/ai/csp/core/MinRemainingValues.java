package fr.emse.ai.csp.core;

import java.util.ArrayList;

/**
 * Classe modélisant l'heuristique MinRemainingValues
 */
public class MinRemainingValues {

    /**
     * Méthode qui choisi la variable qui a le moins de valeurs possibles
     * @param assignment Assignement déjà réalisé
     * @param csp CSP du problème
     * @return Retourne la variable choisie
     */
    public static Variable selectMinRemainingValues(Assignment assignment, CSP csp) {

        //On crée la liste des variables non assignées
        ArrayList<Variable> unassignVar = new ArrayList<>();
        for (Variable var : csp.getVariables()) {
            if (!assignment.variables.contains(var)) {
                unassignVar.add(var);
            }
        }

        // On initialise la variable qui a le minimum de valeur possible
        Variable varMin = unassignVar.get(0);
        //On initialise la taille du domaine
        int minDomainsize = 1000;
        //On parcourt la liste des variables non assignées
        for (Variable var : unassignVar) {
            //On prend la taille actuelle du domaine de la variable
            int i = csp.getDomain(var).size();
            // On parcourt les variables assignées
            for (Variable assignVar : assignment.variables) {
                NotTakeConstraint constraint = new NotTakeConstraint(assignVar,var);
                //On regarde si elle est en contrainte avec une variable assignée
                if (!constraint.isSatisfiedWith(assignment)) {
                    //Si c'est le cas on la retire du domaine
                    i -= 1;
                }
            }
            //On prend cette variable si son domaine est plus petit
            if (i < minDomainsize) {
                varMin = var;
                minDomainsize = i;
            }
            /*
            On n'utilise pas le Degree Heuristic car dans ce problème toutes les variables ont le même nombre de contrainte.
            Donc il ne permet pas de faire de sélection
            else if (i == minDomainsize) {
                varMin = DegreeHeuristic(assignment,csp,varMin,var);
                minDomainsize = i;
            }
            */
        }
        return varMin;
    }
}
