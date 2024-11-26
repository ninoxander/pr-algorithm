package ninoxit.pr;

import jade.core.Agent;
import ninoxit.pr.behaviours.PRBehaviour;

public class PRAgent extends Agent {
    @Override
    public void setup() {
        // Acá se puede cambiar el degree de la regresión
        addBehaviour(new PRBehaviour(1));
    }
}