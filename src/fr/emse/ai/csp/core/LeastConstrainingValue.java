package fr.emse.ai.csp.core;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Classe représentant l'heuristique LeastConstrainingValue
 */
public class LeastConstrainingValue {

    /**
     * Méthode qui trie les valeurs en fonction de leur contrainte
     * @param var Variable choisie
     * @param assignment Assignement déjà réalisé
     * @param csp CSP du problème
     * @return Retourne la liste des valeurs orientées selon leur contrainte
     */
    public static ArrayList<Object> orderLeastConstrainingValue(Variable var, Assignment assignment, CSP csp) {

        /* On peut également stocker les variables dans une priorityQueue afin de les tier à l'ajout, cependant cela ne semble pas améliorer le temps de calcul
        ValueDisponibilityComparator comparator = new ValueDisponibilityComparator();

        //On crée une queue de priorité qui va directement trier les valeurs à l'ajout dans la liste
        PriorityQueue<ValueDisponibility> valueDisponibilityList = new PriorityQueue<ValueDisponibility>(csp.getVariables().size(),comparator);
        */
        ArrayList<Double> DisponibilityList = new ArrayList<>();
        ArrayList<Object> ValueList = new ArrayList<>();
        //On crée la liste des variables non assignées différentes de la variable en cours de traitement
        ArrayList<Variable> unassignVar = new ArrayList<>();
        for (Variable vari : csp.getVariables()) {
            if (!assignment.variables.contains(var) && vari!=var) {
                unassignVar.add(var);
            }
        }
        //On calcule par valeur le nombre de possibilités laissées autres variables qui lui sont liées par des contraintes
        for (Object value : csp.getDomain(var)) {
            //On calcule le nombre de disponibilités pour la value donnée
            double allDisponibility = 0;
            if (!assignment.variableToValue.contains(value)) {
                for(Variable vari : unassignVar) {
                    //Disponibilé pour une variable
                    double nbDisponibility = csp.getDomain(vari).size();
                    nbDisponibility -= (1+assignment.variables.size());

                    //On calcule le nombre de disponibilitée bloquée par les diagonales
                    //On calcul la différence de ligne
                    int DiffLine = Math.abs(Integer.parseInt(var.getName().split("_")[1]) - Integer.parseInt(vari.getName().split("_")[1]));
                    int LeftToColumn = (Integer) value;
                    int RightToColumn = csp.getDomain(vari).size() - (Integer) value;
                    if (DiffLine < LeftToColumn)
                        nbDisponibility -= 1;
                    if (DiffLine < RightToColumn)
                        nbDisponibility -=1;
                    allDisponibility += nbDisponibility;
                }
                /* On crée et on ajoute les valeurs dans la PriorityQueue
                ValueDisponibility valueDisponibility = new ValueDisponibility(allDisponibility,value);
                valueDisponibilityList.add(valueDisponibility);
                */
                DisponibilityList.add(allDisponibility);
                ValueList.add(value);
            }

        }

        //On trie la liste des des valeurs en fonction des disponibilités
        for (int i = 0; i<ValueList.size()-1;i++) {
            for ( int j = ValueList.size()-2; j>i;j--) {
                if (DisponibilityList.get(j)<DisponibilityList.get(j+1)) {
                    Object temp = ValueList.get(j);
                    double tempDouble = DisponibilityList.get(j);
                    ValueList.set(j,ValueList.get(j+1));
                    ValueList.set(j+1,temp);
                    DisponibilityList.set(j,DisponibilityList.get(j+1));
                    DisponibilityList.set(j,tempDouble);
                }
            }
        }

        /* On récupère les valeurs qui ont été triées
        for (int i = 0; i<valueDisponibilityList.size();i++) {
            ValueList.add(valueDisponibilityList.remove().getValue());
        }
            */
        return ValueList;
    }
}
