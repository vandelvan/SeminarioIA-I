

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class Handson5Agent extends Agent{
    private Handson5GUI gui;
    MLR mlr = new MLR();
    @Override
    protected void setup(){
        System.out.println("Starting Hands On 5 Agent");
        gui = new Handson5GUI(this);
        double [][] dataSet = {
                    {1, 23, 651},
                    {2, 26, 762},
                    {3, 30, 856},
                    {4, 34, 1063},
                    {5, 43, 1190},
                    {6, 48, 1298},
                    {7, 52, 1421},
                    {8, 57, 1440},
                    {9, 58, 1518}
                };
        mlr = new MLR(dataSet);
        gui.showGui();
    }
    
    @Override
    protected void takeDown(){
		gui.dispose();
        System.out.println("Hands On 5 Agent"+getAID().getName()+"Terminating");
    }
    
    public void predict(final double factor1, final double factor2){
        addBehaviour(new OneShotBehaviour(){
            @Override
            public void action(){
                mlr.predictFor(new double[]{factor1, factor2});
            }
        });
    }
}
