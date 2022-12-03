


import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;


public class Handson6Agent extends Agent{
    private Handson6GUI gui;
    Gradient gradient = new Gradient();
    
    @Override
    protected void setup(){
        System.out.println("Starting Hands On 6 Agent");
        double [][] dataSet = {{23, 651}, {26, 762}, {30, 856}, {34, 1063}, {43, 1190}, {48, 1298}, {52, 1421}, {57, 1440}, {58, 1518}};
        
        gradient = new Gradient(dataSet);
        
        gui = new Handson6GUI(this);
        gui.showGui();
    }
    @Override
    protected void takeDown(){
		gui.dispose();
        System.out.println("Hands On 6 Agent "+getAID().getName()+"Terminating");
    }
    public void predict(final double x){
        addBehaviour(new OneShotBehaviour(){
            @Override
            public void action(){
                gradient.predictFor(x);
            }
        });
    }
    public int getNumberOfFactors(){
        return gradient.getBetas().length;
    }
}
