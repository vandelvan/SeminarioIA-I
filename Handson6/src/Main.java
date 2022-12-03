
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {
    ContainerController container;

    public static void main(String[] args){
        Main jadeApp = new Main();
        jadeApp.run();
    }

    public void run(){
        //Create the JADE environment
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");
        container = runtime.createMainContainer(profile);

        //Call the RMA GUI
        try{
            AgentController agentController = container.createNewAgent("Hands On 6 Agent", "Handson6Agent", null);
            agentController.start();
        } catch(StaleProxyException e){
            e.printStackTrace();
        }
    }
}
