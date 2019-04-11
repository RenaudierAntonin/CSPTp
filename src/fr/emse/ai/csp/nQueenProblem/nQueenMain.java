package fr.emse.ai.csp.nQueenProblem;

import fr.emse.ai.csp.core.*;

import java.util.Scanner;

public class nQueenMain {

    public static void main (String[] args) {
        Scanner src = new Scanner(System.in);
        int n;
        System.out.println("Rentrez le nombre de reines de que vous souhaitez placer sur l'échéquier : ");
        n = src.nextInt();
        System.out.println("Vous avez choisi " +n +" reines");
        nQueenCSP map = new nQueenCSP(n);
        BacktrackingStrategy bts = new BacktrackingStrategy();
        MinConflictsStrategy mcs = new MinConflictsStrategy(100);
        bts.addCSPStateListener(new CSPStateListener(){
            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                //System.out.println("Assignment evolved : " + assignment);
            }

            @Override
            public void stateChanged(CSP csp) {
                //System.out.println("CSP evolved : " + csp);
            }
        });
        double startBTS = System.currentTimeMillis();
        Assignment solBTS = bts.solve(map);
        double endBTS = System.currentTimeMillis();
        solBTS.display();
        System.out.println("Time to solve with Backtracking = " + (endBTS - startBTS));
        /*double startMCS = System.currentTimeMillis();
        Assignment solMCS = mcs.solve(map);
        double endMCS = System.currentTimeMillis();
        solMCS.display();
        System.out.println("Time to solve with Min Conflit Strategy = " + (endMCS - startMCS));
        */
    }
}
