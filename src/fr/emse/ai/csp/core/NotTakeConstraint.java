package fr.emse.ai.csp.core;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

public class NotTakeConstraint implements Constraint {

    private Variable var1;
    private Variable var2;
    private List<Variable> scope;


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

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        Object value1 = assignment.getAssignment(var1);
        Object value2 = assignment.getAssignment(var2);
        if (value1 == null || value2 == null) {
            return true;
        }
        int line1 = Integer.valueOf(var1.getName().split("_")[1]);
        int line2 = Integer.valueOf(var2.getName().split("_")[1]);
        boolean differentColumn = value1!=value2;
        boolean differentDiag = Math.abs(line1 - line2) != Math.abs((Integer) value1 - (Integer) value2);
        return (differentColumn && differentDiag);
    }
}
