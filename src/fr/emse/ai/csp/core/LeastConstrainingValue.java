package fr.emse.ai.csp.core;

import java.util.ArrayList;

public class LeastConstrainingValue {

    public static ArrayList<Object> orderLeastConstrainingValue(Variable var, Assignment assignment, CSP csp) {

        ArrayList<Double> DisponilyList = new ArrayList<>();
        ArrayList<Object> ValueList = new ArrayList<>();
        //On crée la liste des variables non assignées différentes de la variable en cours de traitement
        ArrayList<Variable> unassignVar = new ArrayList<>();
        for (Variable vari : csp.getVariables()) {
            if (!assignment.variables.contains(var) && vari!=var) {
                unassignVar.add(var);
            }
        }
        //On cherche la valeur qui laisse le plus de possibilités aux autres variables qui lui sont liées par des contraintes
        double max = Double.NEGATIVE_INFINITY;
        Object minValue;
        for (Object value : csp.getDomain(var)) {
            //On calcule le nombre de disponibilités pour la value donnée
            double allDisponibility = 0;
            if (!assignment.variableToValue.contains(value)) {
                for(Variable vari : unassignVar) {
                    //Disponibilé pour une variable
                    double nbDisponibility = csp.getDomain(vari).size();
                    nbDisponibility -= (1+assignment.variables.size());


                    int DiffLine = Math.abs(Integer.parseInt(var.getName().split("_")[1]) - Integer.parseInt(vari.getName().split("_")[1]));
                    int LeftToColumn = (Integer) value;
                    int RightToColumn = csp.getDomain(vari).size() - (Integer) value;
                    if (DiffLine < LeftToColumn)
                        nbDisponibility -= 1;
                    if (DiffLine < RightToColumn)
                        nbDisponibility -=1;
                    allDisponibility += nbDisponibility;
                }
                DisponilyList.add(allDisponibility);
                ValueList.add(value);
            }

        }
        for (int i = 0; i<ValueList.size()-1;i++) {
            for ( int j = ValueList.size()-2; j>i;j--) {
                if (DisponilyList.get(j)<DisponilyList.get(j+1)) {
                    Object temp = ValueList.get(j);
                    double tempDouble = DisponilyList.get(j);
                    ValueList.set(j,ValueList.get(j+1));
                    ValueList.set(j+1,temp);
                    DisponilyList.set(j,DisponilyList.get(j+1));
                    DisponilyList.set(j,tempDouble);
                }
            }
        }
        return ValueList;
    }
}
