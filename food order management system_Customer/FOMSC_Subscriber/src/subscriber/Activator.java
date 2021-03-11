package subscriber;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import tester.FOMSC_Services;

public class Activator implements BundleActivator {
	JFrame frame;
	JButton packButton;
	JButton packButton1;
	JButton packButton2;
	JButton packButton3;
	

	@SuppressWarnings("rawtypes")
	ServiceReference PackserviceReference;
	
	@Override																							
	public void start(BundleContext context) throws Exception {
		System.out.println("**************************");
		System.out.println(">->->->->->->->->-food order management system subscriber service started.<-<-<-<-<-<-<-<-=");
		PackserviceReference = context.getServiceReference(FOMSC_Services.class.getName());
		@SuppressWarnings("unchecked")
		FOMSC_Services packServ = (FOMSC_Services) context.getService(PackserviceReference);

		
		System.out.println("--------------------------------------------");							
		
		this.mainGUI();
		frame.setVisible(true);
		packButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double payment=0;
				int option=0; 
				double packet=1;
				
				String inputOption="0";
				String inputPersons ="0";
				
				
				try{
					
						inputOption = (JOptionPane.showInputDialog(
					            frame,
					            packServ.getService(),
					            ".........LION INN........ : ",
					            JOptionPane.QUESTION_MESSAGE));
						option=Integer.parseInt(inputOption);
						
						 inputPersons = (JOptionPane.showInputDialog(
						            frame,
						            "Enter the number of packet? : ",
						            "...LION INN...",
						            JOptionPane.QUESTION_MESSAGE));
							
						 packet=Double.parseDouble(inputPersons);
						
						
						payment = packServ.priceCalculator(option,packet);
					
						
						if(payment == -1 || payment<0 || payment == 0) {
							JOptionPane.showMessageDialog(frame, "There seems to be an error... Please try again!");
							frame.setVisible(false);
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(frame, "Thank You. Your Total is : Rs."+payment);
						}
						
					
				}catch(Exception ex) {
					System.out.println("Exception : "+ex);
					JOptionPane.showMessageDialog(frame, "Invalid input please try again...");
					frame.setVisible(false);
					frame.dispose();
				}
				
				
			}
		});
		
		
			
		System.out.println("--------------------------------------------");
	}

	
	
	public void mainGUI() {									
		frame = new JFrame("LION INN... ");
	    frame.setSize( 850, 300 );
	    frame.setLayout(new BorderLayout());
	    frame.setLocationRelativeTo(null);
	    
	    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    JLabel back = new JLabel(new ImageIcon(this.getClass().getResource("/resources/2.jpg")));
	    
	    GridBagLayout maingrid = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    back.setLayout(maingrid);
	    back.setSize(850,300);
	    packButton = new JButton(" Sri Lankan Foods ");
	    packButton1 = new JButton("Italian Foods");
	    packButton2 = new JButton("Thailand Foods");
	    packButton3 = new JButton("Japanese Foods");
	    
	    packButton.setBackground(new Color(165,42,42));
	    packButton.setFont(new Font("Century Gothic",Font.PLAIN,11));
	    packButton.setForeground(new Color(255,255,255));
	    packButton.setPreferredSize(new Dimension(100, 20));
	    
	    packButton1.setBackground(new Color(160,82,45));
	    packButton1.setFont(new Font("Century Gothic",Font.PLAIN,11));
	    packButton1.setForeground(new Color(255,255,255));
	    packButton1.setPreferredSize(new Dimension(100, 20));

	    packButton2.setBackground(new Color(165,42,42));
	    packButton2.setFont(new Font("Century Gothic",Font.PLAIN,11));
	    packButton2.setForeground(new Color(255,255,255));
	    packButton2.setPreferredSize(new Dimension(100, 20));

	    packButton3.setBackground(new Color(160,82,45));
	    packButton3.setFont(new Font("Century Gothic",Font.PLAIN,11));
	    packButton3.setForeground(new Color(255,255,255));
	    packButton3.setPreferredSize(new Dimension(100, 20));

	    JLabel label = new JLabel(" LION INN ");
	    label.setForeground(new Color(0,0,0));
	    label.setFont(new Font("Garamond",Font.BOLD,60));
	    JLabel label1 = new JLabel("Please Select package Type:");
	    label1.setForeground(new Color(0,0,0));
	    label1.setFont(new Font("Garamond",Font.BOLD,40));
	    
	    label.setBounds(4,60,500,60);
	    label1.setBounds(60,200,500,50);
	   
	    packButton.setBounds(180,400,300,30);
	    packButton1.setBounds(180,430,300,30);
	    packButton2.setBounds(180,460,300,30);
	    packButton3.setBounds(180,490,300,30);
	    
	    frame.getContentPane().add(packButton);
	    frame.getContentPane().add(packButton1);
	    frame.getContentPane().add(packButton2);
	    frame.getContentPane().add(packButton3);
	    
	    frame.getContentPane().add(label1);
	    frame.getContentPane().add(label);
	    frame.getContentPane().add(back);
	    
	    frame.pack();
	    frame.setVisible(false);
	}
															
	public void stop(BundleContext context) throws Exception {
		context.ungetService(PackserviceReference);
	}
}

