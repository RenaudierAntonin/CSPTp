package fr.emse.ai.csp.nQueenProblem;

import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.Domain;
import fr.emse.ai.csp.core.Variable;
import fr.emse.ai.csp.core.NotTakeConstraint;

import java.util.ArrayList;

public class nQueenCSP extends CSP {

    private ArrayList<Variable> variableList = new ArrayList<>();

    public nQueenCSP(int n) {

        ArrayList<Object> objectList = new ArrayList();
        for (int i =0;i<n;i++) {
            Variable variable = new Variable("Q_" + i);
            variableList.add(variable);
            addVariable(variable);
            objectList.add(i);
        }

        Domain Column = new Domain(objectList.toArray());

        for (Variable var : getVariables())
            setDomain(var,Column);

        for (int i = 0;i<n-1;i++) {
            for (int j = i+1;j<n;j++) {
                addConstraint(new NotTakeConstraint(variableList.get(i),variableList.get(j)));
            }
        }
    }
}
