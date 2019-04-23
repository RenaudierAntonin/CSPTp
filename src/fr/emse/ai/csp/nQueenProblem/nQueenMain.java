package fr.emse.ai.csp.nQueenProblem;

import fr.emse.ai.csp.core.*;

import java.util.Scanner;

/**
 * Classe qui permet de résoudre le problème des N Reines avec le N de votre choix
 * On utilise la Backtracking Strategy ou la MinConflicts Strategy
 */
public class nQueenMain {

    public static void main (String[] args) {
        Scanner src = new Scanner(System.in);
        int n;
        System.out.println("Rentrez le nombre de reines de que vous souhaitez placer sur l'échéquier : ");
        n = src.nextInt();
        System.out.println("Vous avez choisi " +n +" reines");
        nQueenCSP mapBTS = new nQueenCSP(n);
        nQueenCSP mapMCS = new nQueenCSP(n);
        BacktrackingStrategy bts = new BacktrackingStrategy();
        MinConflictsStrategy mcs = new MinConflictsStrategy(100000);
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
        //Calcul du temps de résolution pour la Backtracking Strategy
        double startBTS = System.currentTimeMillis();
        Assignment solBTS = bts.solve(mapBTS);
        double endBTS = System.currentTimeMillis();
        solBTS.display();
        System.out.println("Time to solve with Backtracking = " + (endBTS - startBTS));


        //Calcul du temps de résolution pour la MinConflict Strategy
        double startMCS = System.currentTimeMillis();
        Assignment solMCS = mcs.solve(mapMCS);
        double endMCS = System.currentTimeMillis();
        solMCS.display();
        System.out.println("Time to solve with Min Conflit Strategy = " + (endMCS - startMCS));

    }
}
