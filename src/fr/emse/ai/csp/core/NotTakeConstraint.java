package fr.emse.ai.csp.core;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de savoir si la contrainte de non prise entre deux reines est respectée
 */
public class NotTakeConstraint implements Constraint {

    private Variable var1;
    private Variable var2;
    private List<Variable> scope;

    /**
     * Constructeur de la classe
     * @param var1 variable 1, c'est à dire la première reine
     * @param var2 variable 2, c'est à dire la deuxième reine
     */
    public NotTakeConstraint(Variable var1, Variable var2) {

        this.var1 = var1;
        this.var2 = var2;
        scope = new ArrayList<Variable>(2);
        scope.add(var1);
        scope.add(var2);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    /**
     * Méthode permettant de savoir si les variables assignées respectent les contraintes
     * @param assignment Liste des valeurs assignées aux différentes variables
     * @return True si les contraintes sont respectées, False sinon
     */
    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        //Valeur assignée à la variable 1
        Object value1 = assignment.getAssignment(var1);
        //Valeur assignée à la variable 2
        Object value2 = assignment.getAssignment(var2);
        //On retourne true si l'une des deux est nulle
        if (value1 == null || value2 == null) {
            return true;
        }
        //On récupère la ligne qui est stockée dans le nom de la variable
        int line1 = Integer.valueOf(var1.getName().split("_")[1]);
        int line2 = Integer.valueOf(var2.getName().split("_")[1]);
        //Les valeurs représentent les colonnes, on vérifie qu'elles sont différents
        boolean differentColumn = value1!=value2;
        //On vérifie que les deux reines ne sont pas sur la même diagonale
        boolean differentDiag = Math.abs(line1 - line2) != Math.abs((Integer) value1 - (Integer) value2);
        return (differentColumn && differentDiag);
    }
}
