package fr.emse.ai.csp.nQueenProblem;

import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.Domain;
import fr.emse.ai.csp.core.Variable;
import fr.emse.ai.csp.core.NotTakeConstraint;

import java.util.ArrayList;

/**
 * Classe qui modélise le CSP du problème des N reines
 */
public class nQueenCSP extends CSP {

    /**
     * Liste des variables
     */
    private ArrayList<Variable> variableList = new ArrayList<>();

    /**
     * Constructeur de la classe
     * @param n Nombre de reine à créer
     */
    public nQueenCSP(int n) {

        // Liste des Reines
        // Chaque reine est sous la forme Q_i ou i représente son numéro de ligne, ainsi on limite les possibilités
        ArrayList<Object> objectList = new ArrayList();
        for (int i =0;i<n;i++) {
            Variable variable = new Variable("Q_" + i);
            variableList.add(variable);
            addVariable(variable);
            objectList.add(i);
        }

        // Domaine du problème c'est à dire la liste des valeurs possibles. Ce sont des entiers i de 0 à n représentant chacun une colonne
        Domain Column = new Domain(objectList.toArray());

        //On relie les variables à son domaine
        for (Variable var : getVariables())
            setDomain(var,Column);

        //On ajoute les contraintes
        //Les reines sont toutes contraintes 2 à 2
        for (int i = 0;i<n-1;i++) {
            for (int j = i+1;j<n;j++) {
                addConstraint(new NotTakeConstraint(variableList.get(i),variableList.get(j)));
            }
        }
    }
}
