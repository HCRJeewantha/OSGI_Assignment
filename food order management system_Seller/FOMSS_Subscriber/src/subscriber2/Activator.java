package subscriber2;

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

import tester2.FOMSS_Services;

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
		System.out.println(">->->->->->->->->-food order management system <<seller>> subscriber service started.<-<-<-<-<-<-<-<-=");
		PackserviceReference = context.getServiceReference(FOMSS_Services.class.getName());
		@SuppressWarnings("unchecked")
		FOMSS_Services packServ = (FOMSS_Services) context.getService(PackserviceReference);

		
		System.out.println("--------------------------------------------");							
		
		this.mainGUI();
		frame.setVisible(true);
		packButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double payment=0;
				int type=0; 
				double pack=1;
				
				String inputtype="0";
				String inputPack ="0";
				
				
				try{
					
					inputtype = (JOptionPane.showInputDialog(
					            frame,
					            packServ.getService(),
					            ".........LION INN........ : ",
					            JOptionPane.QUESTION_MESSAGE));
						type=Integer.parseInt(inputtype);
						
						 inputPack = (JOptionPane.showInputDialog(
						            frame,
						            "Enter the number of packages? : ",
						            "...LION INN...",
						            JOptionPane.QUESTION_MESSAGE));
							
						 pack=Double.parseDouble( inputPack);
						
						
						payment = packServ.priceCalculator(type,pack);
					
						
						if(payment == -1 || payment<0 || payment == 0) {
							JOptionPane.showMessageDialog(frame, "There seems to be an error... Please try again!");
							frame.setVisible(false);
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(frame, "Thank You. Your Total is : Rs."+payment);
							JOptionPane.showMessageDialog(frame, " Payment Successful !");
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
	    JLabel back = new JLabel(new ImageIcon(this.getClass().getResource("/resources/kk.PNG")));
	    
	    GridBagLayout maingrid = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    back.setLayout(maingrid);
	    back.setSize(850,300);
	    packButton = new JButton(" NEXT");
	     
	    packButton.setBackground(new Color(165,42,42));
	    packButton.setFont(new Font("Century Gothic",Font.PLAIN,11));
	    packButton.setForeground(new Color(255,255,255));
	    packButton.setPreferredSize(new Dimension(100, 20));
	    
	
	    JLabel label = new JLabel(" LION INN ");
	    label.setForeground(new Color(255,255,255));
	    label.setFont(new Font("Garamond",Font.BOLD,60));
	    JLabel label1 = new JLabel("Enter The Delivery Details:");
	    label1.setForeground(new Color(255,255,255));
	    label1.setFont(new Font("Garamond",Font.BOLD,40));
	    
	    label.setBounds(4,60,500,60);
	    label1.setBounds(60,200,500,50);
	   
	    packButton.setBounds(180,400,300,30);
	 
	    frame.getContentPane().add(packButton);
 
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

