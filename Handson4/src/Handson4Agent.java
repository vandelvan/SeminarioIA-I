
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;

public class Handson4Agent extends Agent{
    private Handson4GUI gui;
    SLR slr = new SLR();
    @Override
    protected void setup(){
        System.out.println("Starting Hands On 4 Agent");
        double [][] dataSet = {{23, 651}, {26, 762}, {30, 856}, {34, 1063}, {43, 1190}, {48, 1298}, {52, 1421}, {57, 1440}, {58, 1518}};
        slr = new SLR(dataSet);
        gui = new Handson4GUI(this);
        gui.showGui();
    }

    @Override
    protected void takeDown(){
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// Close the GUI
		gui.dispose();
        System.out.println("Hands On 4 Agent"+getAID().getName()+"Terminating");
    }

    public void predict(final double factor){
        addBehaviour(new OneShotBehaviour(){
            @Override
            public void action(){
                slr.predict(new double[]{factor});
            }
        });
    }
}
