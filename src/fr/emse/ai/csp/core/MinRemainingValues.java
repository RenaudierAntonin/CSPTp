package fr.emse.ai.csp.core;

import java.util.ArrayList;

public class MinRemainingValues {

    public static Variable selectMinRemainingValues(Assignment assignment, CSP csp) {
        ArrayList<Variable> unassignVar = new ArrayList<>();
        for (Variable var : csp.getVariables()) {
            if (!assignment.variables.contains(var)) {
                unassignVar.add(var);
            }
        }

        Variable varMin = unassignVar.get(0);
        int minDomainsize = 1000;
        for (Variable var : unassignVar) {
            int i = csp.getDomain(var).size();
            for (Variable assignVar : assignment.variables) {
                NotTakeConstraint constraint = new NotTakeConstraint(assignVar,var);
                if (!constraint.isSatisfiedWith(assignment)) {
                    i -= 1;
                }
            }
            if (i < minDomainsize) {
                varMin = var;
                minDomainsize = i;
            }
        }
        return varMin;
    }
}