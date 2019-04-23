package fr.emse.ai.csp.fourQueenProblem;

import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.Domain;
import fr.emse.ai.csp.core.NotTakeConstraint;
import fr.emse.ai.csp.core.Variable;

/**
 * Classe qui modélise le problème des 4 reines
 * Même approche que pour les N reines
 */
public class fourQueenCSP extends CSP {
    public static final Variable Q0 = new Variable("Q_0");
    public static final Variable Q1 = new Variable("Q_1");
    public static final Variable Q2 = new Variable("Q_2");
    public static final Variable Q3 = new Variable("Q_3");

    public static final int Zero = 0;
    public static final int One = 1;
    public static final int Two = 2;
    public static final int Three = 3;

    public fourQueenCSP() {
        addVariable(Q0);
        addVariable(Q1);
        addVariable(Q2);
        addVariable(Q3);

        Domain Column = new Domain(new Object[]{Zero,One,Two,Three});

        for (Variable var : getVariables())
            setDomain(var,Column);

        addConstraint(new NotTakeConstraint(Q0,Q1));
        addConstraint(new NotTakeConstraint(Q0,Q2));
        addConstraint(new NotTakeConstraint(Q0,Q3));
        addConstraint(new NotTakeConstraint(Q1,Q2));
        addConstraint(new NotTakeConstraint(Q1,Q3));
        addConstraint(new NotTakeConstraint(Q2,Q3));

    }
}
