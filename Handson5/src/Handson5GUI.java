

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Handson5GUI extends JFrame {	
	private Handson5Agent myAgent;
	
	private JTextField x1value, x2value;
	
	public Handson5GUI(Handson5Agent a) {
            super(a.getLocalName());

            myAgent = a;

            JPanel p = new JPanel();
            p.setLayout(new GridLayout(2, 2));
            p.add(new JLabel("x1:"));
            x1value = new JTextField(15);
            p.add(x1value);
            p.add(new JLabel("x2:"));
            x2value = new JTextField(15);
            p.add(x2value);
            getContentPane().add(p, BorderLayout.CENTER);

            JButton addButton = new JButton("Predict");
            addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                            try {
                                    String x1 = x1value.getText().trim();
                                    String x2 = x2value.getText().trim();
                                    myAgent.predict(Double.parseDouble(x1), Double.parseDouble(x2));
                                    x1value.setText("");
                                    x2value.setText("");
                            }
                            catch (Exception e) {
                                    JOptionPane.showMessageDialog(Handson5GUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                    }
            } );
            p = new JPanel();
            p.add(addButton);
            getContentPane().add(p, BorderLayout.SOUTH);

            // Make the agent terminate when the user closes 
            // the GUI using the button on the upper right corner	
            addWindowListener(new	WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                            myAgent.doDelete();
                    }
            } );

            setResizable(true);
	}
	
	public void showGui() {
            pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = (int)screenSize.getWidth() / 2;
            int centerY = (int)screenSize.getHeight() / 2;
            setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
            super.setVisible(true);
	}	
}

