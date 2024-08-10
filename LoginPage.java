import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

class LoginPage
{
	
		Actions action = new Actions();
	
		Font fnt1 = new Font("Constantia",Font.ITALIC,25);
		Font fnt2 = new Font("Constantia",Font.ITALIC,30);
		//Color clr1 = new Color();
	
		JFrame frm = null;
		JPanel pnl = null;
		ImageIcon icon = new ImageIcon("images/login1.png");
		JButton log,cancel = null;
		JLabel namelbl,passlbl,custlbl,image = null;
		JTextField nametxt = null;
		JPasswordField ps = null;
		Connection con = null;
		ResultSet  rs = null;
		Statement st = null;
		String dbname;
		String dbpass;
		
	LoginPage()
	{	
		custlbl = new JLabel("Customer Login Page");
		custlbl.setBounds(260,30,300,35);
		custlbl.setFont(fnt2);
		
		namelbl = new JLabel("Username:");
		namelbl.setBounds(370,220,120,30);
		namelbl.setFont(fnt1);
		
		nametxt = new JTextField();
		nametxt.setBounds(510,220,250,30);
		nametxt.setFont(fnt1);
		
		passlbl = new JLabel("Password: ");
		passlbl.setBounds(370,270,120,30);
		passlbl.setFont(fnt1);
		
		ps = new JPasswordField();
		ps.setBounds(510,270,250,30);
		
		log = new JButton("Login");
		log.setBounds(510,320,120,35);
		log.setFont(fnt1);
		log.setFocusable(false);
		log.addActionListener(action);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(640,320,120,35);
		cancel.setFont(fnt1);
		cancel.setFocusable(false);
		cancel.addActionListener(action);
		
		image = new JLabel("",icon,JLabel.CENTER);
		image.setBounds(20,70,400,400);
		
		pnl = new JPanel();
		pnl.setLayout(null);
		pnl.add(image);
		pnl.add(nametxt);
		pnl.add(ps);
		pnl.add(namelbl);
		pnl.add(passlbl);
		pnl.add(log);
		pnl.add(cancel);
		pnl.add(custlbl);
		
		
		
		frm = new JFrame("Login Page");
		frm.setSize(800,600);
		frm.add(pnl);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
		frm.setResizable(false);
		frm.setVisible(true);
	}
	class Actions implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();
			if(obj == log)
			{
				String name = nametxt.getText();
				String pass = ps.getText();
				String query = "select * from users where username = '"+name+"' and password = '"+pass+"'";
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
					st = con.createStatement();
					rs = st.executeQuery(query);
					
					while(rs.next() != false)
					{
						dbname = rs.getString("username");
						dbpass = rs.getString("password");
					}
					if(name.equals(dbname) && pass.equals(dbpass))
					{						
						nametxt.setText("");
						ps.setText("");
						frm.setVisible(false);
						MainPage mp = new MainPage();
					}
					else
					{
						JOptionPane.showMessageDialog(frm,"Sorry Username or password is wrong","Warning",JOptionPane.ERROR_MESSAGE);
						nametxt.setText("");
						ps.setText("");
					}
				}
				catch(SQLException sqlexp)
				{
					System.out.println("Sorry SQLException occured...");
				}
				catch(ClassNotFoundException notfound)
				{
					System.out.println("Sorry class not found...");
				}
				catch(Exception exp)
				{
					System.out.println("This is General Exception....");
				}
				finally
				{
					try
					{
						rs.close();
						st.close();
						con.close();
					}
					catch(Exception ex)
					{
						System.out.println("Sorry");
					}
				}
				
				
			}
			if(obj == cancel)
			{
				System.exit(0);
			}
		}
	}
	
	
	
}