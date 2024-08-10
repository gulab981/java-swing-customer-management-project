import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



class MainPage
{
	JFrame frm = null;
	Font fnt = new Font("Constantia",Font.ITALIC,22);
	JTextField nametxt,lasttxt,addresstxt,emailtxt,searchtxt,deletetxt,updnametxt,updaddresstxt,updemailtxt,updidtxt,updlasttxt = null;
	ImageIcon img = new ImageIcon("images/logout.png");
	JLabel image1,image2,image3,image4,namelbl,lastlbl,addresslbl,emaillbl,searchlbl,deletelbl,updidlbl,updnamelbl,updlastlbl,updemaillbl,updaddresslbl = null;
	JButton newbtn,registerbtn,cancelbtn,searchbtn,deletebtn,updsearchbtn,updatebtn,updcancelbtn = null;
	DefaultTableModel model = null;
	JTable table = null;
	JScrollPane pane = null;
	JTabbedPane tab = null;
	JPanel pnl1,pnl2,pnl3,pnl4 = null;
	Font fnt1 = new Font("Constantia",Font.ITALIC,25);
	Cursor c1 = new Cursor(Cursor.HAND_CURSOR);
	
	Connection connect = null;
	PreparedStatement prepare = null;
	ResultSet rs = null;
	String query = null;
	int result = 0;
	String db_name,db_last,db_email,db_address = null;
	int db_id = 0;
	
	Actions action = new Actions();

	MainPage()
	{
		image1 = new JLabel("",img,JLabel.CENTER);
		image1.setBounds(790,0,100,50);
		image1.setCursor(c1);
		image1.addMouseListener(action);
		
		namelbl = new JLabel("Customer Name:");
		namelbl.setBounds(50,50,180,25);
		namelbl.setFont(fnt1);
		
		nametxt = new JTextField("");
		nametxt.setBounds(50,80,250,30);
		nametxt.setFont(fnt);
		nametxt.setEditable(false);
		
		addresslbl = new JLabel("Address:");
		addresslbl.setBounds(550,50,110,25);
		addresslbl.setFont(fnt1);
		
		addresstxt = new JTextField();
		addresstxt.setBounds(550,80,250,30);
		addresstxt.setFont(fnt);
		addresstxt.setEditable(false);
		
		lastlbl = new JLabel("Last Name:");
		lastlbl.setBounds(50,180,180,25);
		lastlbl.setFont(fnt1);
		
		lasttxt = new JTextField();
		lasttxt.setBounds(50,210,250,30);
		lasttxt.setFont(fnt);
		lasttxt.setEditable(false);
		
		emaillbl = new JLabel("Customer Email:");
		emaillbl.setBounds(550,180,180,25);
		emaillbl.setFont(fnt1);
		
		emailtxt = new JTextField();
		emailtxt.setBounds(550,210,250,30);
		emailtxt.setFont(fnt);
		emailtxt.setEditable(false);
		
		newbtn = new JButton("New");
		newbtn.setBounds(240,270,100,30);
		newbtn.setFont(fnt);
		newbtn.addActionListener(action);
		
		registerbtn = new JButton("Register");
		registerbtn.setBounds(350,270,120,30);
		registerbtn.setFont(fnt);
		registerbtn.setEnabled(false);
		registerbtn.addActionListener(action);
		
		cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(480,270,120,30);
		cancelbtn.setFont(fnt);
		cancelbtn.setEnabled(false);
		cancelbtn.addActionListener(action);
		
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.add(image1);
		pnl1.add(nametxt);
		pnl1.add(namelbl);
		pnl1.add(addresslbl);
		pnl1.add(addresstxt);
		pnl1.add(lastlbl);
		pnl1.add(lasttxt);
		pnl1.add(emaillbl);
		pnl1.add(emailtxt);
		pnl1.add(newbtn);
		pnl1.add(registerbtn);
		pnl1.add(cancelbtn);
		
		//Searching Customer's Details
		
		searchlbl = new JLabel("Customer Name:");
		searchlbl.setBounds(20,10,180,25);
		searchlbl.setFont(fnt1);
		
		searchtxt = new JTextField();
		searchtxt.setBounds(20,45,250,30);
		searchtxt.setFont(fnt);
		
		searchbtn = new JButton("Search");
		searchbtn.setBounds(280,45,120,30);
		searchbtn.setFont(fnt1);
		searchbtn.addActionListener(action);
		
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Last Name");
		model.addColumn("Address");
		model.addColumn("Email");
		
		table = new JTable(model);
		
		pane = new JScrollPane(table);
		pane.setBounds(20,100,840,300);
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect_all = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
			if(connect_all != null){
				String select_all = "select id,name,last_name,address,email from customers";
				PreparedStatement prepare_all = connect_all.prepareStatement(select_all);
				ResultSet rs_all = prepare_all.executeQuery();
				int number = 0;
				
				while(rs_all.next() != false){
					db_id = rs_all.getInt("id");
					db_name = rs_all.getString("name");
					db_last = rs_all.getString("last_name");
					db_address = rs_all.getString("address");
					db_email = rs_all.getString("email");
					
					model.addRow(new Object[]{db_id,db_name,db_last,db_address,db_email});
					
					number = 1;
				}
				if(number == 0)
					JOptionPane.showMessageDialog(frm,"Sorry not user with this name","Warning",JOptionPane.INFORMATION_MESSAGE);
				
}
			
		}catch(Exception exp){
			JOptionPane.showMessageDialog(frm,exp.getMessage());
		}
		
		image2 = new JLabel("",img,JLabel.CENTER);
		image2.setBounds(790,0,100,50);
		image2.setCursor(c1);
		image2.addMouseListener(action);
		
		
		pnl2 = new JPanel();
		pnl2.setLayout(null);
		pnl2.add(image2);
		pnl2.add(searchlbl);
		pnl2.add(searchtxt);
		pnl2.add(searchbtn);
		pnl2.add(pane);
		
		//Deleting the Customer panel
		
		deletelbl = new JLabel("Customer ID:");
		deletelbl.setBounds(20,10,190,25);
		deletelbl.setFont(fnt1);
		
		deletetxt = new JTextField();
		deletetxt.setBounds(20,45,250,30);
		deletetxt.setFont(fnt);
		
		deletebtn = new JButton("Delete");
		deletebtn.setBounds(280,45,120,30);
		deletebtn.setFont(fnt1);
		deletebtn.addActionListener(action);
		
		image3 = new JLabel("",img,JLabel.CENTER);
		image3.setBounds(790,0,100,50);
		image3.setCursor(c1);
		image3.addMouseListener(action);
		
		pnl3 = new JPanel();
		pnl3.setLayout(null);
		pnl3.add(image3);
		pnl3.add(deletelbl);
		pnl3.add(deletetxt);
		pnl3.add(deletebtn);
		
		//Updating Customer Section
		
		updidlbl = new JLabel("Customer ID:");
		updidlbl.setBounds(20,10,180,25);
		updidlbl.setFont(fnt1);
		
		updidtxt = new JTextField();
		updidtxt.setBounds(20,45,250,30);
		updidtxt.setFont(fnt);
		
		updsearchbtn = new JButton("Search");
		updsearchbtn.setBounds(280,45,120,30);
		updsearchbtn.setFont(fnt1);
		updsearchbtn.addActionListener(action);
		
		updnamelbl = new JLabel("Customer Name:");
		updnamelbl.setBounds(50,100,180,25);
		updnamelbl.setFont(fnt1);
		
		updnametxt = new JTextField("");
		updnametxt.setBounds(50,130,250,30);
		updnametxt.setFont(fnt);
		updnametxt.setEditable(false);
		
		updaddresslbl = new JLabel("Address:");
		updaddresslbl.setBounds(550,100,110,25);
		updaddresslbl.setFont(fnt1);
		
		updaddresstxt = new JTextField();
		updaddresstxt.setBounds(550,130,250,30);
		updaddresstxt.setFont(fnt);
		updaddresstxt.setEditable(false);
		
		updlastlbl = new JLabel("Last Name:");
		updlastlbl.setBounds(50,230,180,25);
		updlastlbl.setFont(fnt1);
		
		updlasttxt = new JTextField();
		updlasttxt.setBounds(50,260,250,30);
		updlasttxt.setFont(fnt);
		updlasttxt.setEditable(false);
		
		updemaillbl = new JLabel("Customer Email:");
		updemaillbl.setBounds(550,230,180,25);
		updemaillbl.setFont(fnt1);
		
		updemailtxt = new JTextField();
		updemailtxt.setBounds(550,260,250,30);
		updemailtxt.setFont(fnt);
		updemailtxt.setEditable(false);
		
		updatebtn = new JButton("Update");
		updatebtn.setBounds(290,300,120,30);
		updatebtn.setFont(fnt);
		updatebtn.setEnabled(false);
		updatebtn.addActionListener(action);
		
		updcancelbtn = new JButton("Cancel");
		updcancelbtn.setBounds(420,300,120,30);
		updcancelbtn.setFont(fnt);
		updcancelbtn.setEnabled(false);
		updcancelbtn.addActionListener(action);
		
		image4 = new JLabel("",img,JLabel.CENTER);
		image4.setBounds(790,0,100,50);
		image4.setCursor(c1);
		image4.addMouseListener(action);
		
		pnl4 = new JPanel();
		pnl4.setLayout(null);
		pnl4.add(image4);
		pnl4.add(updidlbl);
		pnl4.add(updidtxt);
		pnl4.add(updsearchbtn);
		pnl4.add(updnamelbl);
		pnl4.add(updnametxt);
		pnl4.add(updaddresslbl);
		pnl4.add(updaddresstxt);
		pnl4.add(updlastlbl);
		pnl4.add(updlasttxt);
		pnl4.add(updemaillbl);
		pnl4.add(updemailtxt);
		pnl4.add(updatebtn);
		pnl4.add(updcancelbtn);
		
		tab = new JTabbedPane();
		tab.add(pnl1,"Register Customer");
		tab.add(pnl2,"Search Customer");
		tab.add(pnl3,"Delete Customer");
		tab.add(pnl4,"Edit Customer");
		

		frm = new JFrame("Main Page");
		frm.setSize(900,500);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.add(tab);
		frm.setResizable(false);
		frm.setVisible(true);
	}
	class Actions implements ActionListener,MouseListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();
			
			if(obj == newbtn){
				nametxt.setEditable(true);
				addresstxt.setEditable(true);
				lasttxt.setEditable(true);
				emailtxt.setEditable(true);
				registerbtn.setEnabled(true);
				cancelbtn.setEnabled(true);
			}else if(obj == cancelbtn){
				
				nametxt.setText("");
				addresstxt.setText("");
				emailtxt.setText("");
				lasttxt.setText("");
				
				nametxt.setEditable(false);
				addresstxt.setEditable(false);
				lasttxt.setEditable(false);
				emailtxt.setEditable(false);
				registerbtn.setEnabled(false);
				cancelbtn.setEnabled(false);
			}else if(obj == registerbtn){
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
					if(nametxt.getText().equals("") || addresstxt.getText().equals("") || lasttxt.getText().equals("") || emailtxt.getText().equals("")){
						JOptionPane.showMessageDialog(frm,"Fill up all the Text fields first","Warning",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						if(connect != null){
							query = "insert into customers(name,last_name,address,email) values(?,?,?,?)";
							prepare = connect.prepareStatement(query);
							prepare.setString(1,nametxt.getText());
							prepare.setString(2,lasttxt.getText());
							prepare.setString(3,addresstxt.getText());
							prepare.setString(4,emailtxt.getText());
							
							result = prepare.executeUpdate();
							
							if(result != 0){
								JOptionPane.showMessageDialog(frm,"Customer Registered Successfully !!","Message",JOptionPane.INFORMATION_MESSAGE);
								
								nametxt.setText("");
								addresstxt.setText("");
								emailtxt.setText("");
								lasttxt.setText("");
								
								nametxt.setEditable(false);
								addresstxt.setEditable(false);
								lasttxt.setEditable(false);
								emailtxt.setEditable(false);
								registerbtn.setEnabled(false);
								cancelbtn.setEnabled(false);
							}
						}
						else{
							JOptionPane.showMessageDialog(frm,"Sorry can't connect to Database..","Warning",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				catch(Exception exp){
					JOptionPane.showMessageDialog(frm,exp.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			else if(obj == searchbtn){
				if(searchtxt.getText().equals("")){
					JOptionPane.showMessageDialog(frm,"Enter the Name First to Search..","Warning",JOptionPane.INFORMATION_MESSAGE);
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
						
						if(connect != null){
							query = "select id,name,last_name,address,email from customers where name like '"+searchtxt.getText()+"%'";
							
							prepare = connect.prepareStatement(query);
							
							rs = prepare.executeQuery();
							
							int number = 0;
							
							while(rs.next() != false){
								db_id = rs.getInt("id");
								db_name = rs.getString("name");
								db_last = rs.getString("last_name");
								db_address = rs.getString("address");
								db_email = rs.getString("email");
								
								model.addRow(new Object[]{db_id,db_name,db_last,db_address,db_email});
								
								number = 1;
							}
							if(number == 0)
								JOptionPane.showMessageDialog(frm,"Sorry not user with this name","Warning",JOptionPane.INFORMATION_MESSAGE);
							
						}
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(frm,exp.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else if(obj == deletebtn){
				if(deletetxt.getText().equals(""))
					JOptionPane.showMessageDialog(frm,"First Enter the Customer ID","Warning",JOptionPane.INFORMATION_MESSAGE);
				else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
						
						if(connect != null){
							query = "delete from customers where id = "+deletetxt.getText();
							
							prepare = connect.prepareStatement(query);
							
							int ask = JOptionPane.showConfirmDialog(frm,"Are your sure you want to delete this Customer...");
							//System.out.println(ask);
							if(ask == 0){
								result = prepare.executeUpdate(query);
								if(result != 0){
									JOptionPane.showMessageDialog(frm,"Customer Deleted Successfully..","Message",JOptionPane.INFORMATION_MESSAGE);
									deletetxt.setText("");
								}
								else{
									JOptionPane.showMessageDialog(frm,"Customer Not Deleted..","Warning",JOptionPane.INFORMATION_MESSAGE);
								}
							}
							
						}
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(frm,exp.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}if(obj == updsearchbtn){
				try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
						
						if(connect != null){
							query = "select name,last_name,address,email from customers where id = "+Integer.parseInt(updidtxt.getText());
							
							prepare = connect.prepareStatement(query);
							
							rs = prepare.executeQuery();
							
							int number = 0;
							
							while(rs.next() != false){
								db_name = rs.getString("name");
								db_last = rs.getString("last_name");
								db_address = rs.getString("address");
								db_email = rs.getString("email");
								
								updnametxt.setText(db_name);
								updlasttxt.setText(db_last);
								updaddresstxt.setText(db_address);
								updemailtxt.setText(db_email);
								
								updnametxt.setEditable(true);
								updlasttxt.setEditable(true);
								updaddresstxt.setEditable(true);
								updemailtxt.setEditable(true);
								
								updatebtn.setEnabled(true);
								updcancelbtn.setEnabled(true);
								
								number = 1;
							}
							if(number == 0)
								JOptionPane.showMessageDialog(frm,"Sorry not user with this name","Warning",JOptionPane.INFORMATION_MESSAGE);
							
							
						}
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(frm,exp.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
					}
			}if(obj == updatebtn){
				try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/customer","root","");
						
						if(connect != null){
							query = "update customers set name=?,last_name=?,address = ?,email = ? where id = "+Integer.parseInt(updidtxt.getText());
							
							prepare = connect.prepareStatement(query);
							
							prepare.setString(1,updnametxt.getText());
							prepare.setString(2,updlasttxt.getText());
							prepare.setString(3,updaddresstxt.getText());
							prepare.setString(4,updemailtxt.getText());
							
							result = prepare.executeUpdate();
							
							if(result != 0){
								JOptionPane.showMessageDialog(frm,"Customer Information Updated Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
								
								updnametxt.setText("");
								updaddresstxt.setText("");
								updemailtxt.setText("");
								updlasttxt.setText("");
								updidtxt.setText("");
								
								updnametxt.setEditable(false);
								updaddresstxt.setEditable(false);
								updlasttxt.setEditable(false);
								updemailtxt.setEditable(false);
								updatebtn.setEnabled(false);
								updcancelbtn.setEnabled(false);
							}
							
						}
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(frm,exp.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
					}
			}
		}
		public void mouseClicked(MouseEvent event){
			Object obj = event.getSource();
			if(obj == image1 || obj == image2 || obj == image3 || obj == image4){
				frm.setVisible(false);
				LoginPage page = new LoginPage();
			}
		}
		public void mousePressed(MouseEvent eve){
			
		}
		public void mouseReleased(MouseEvent eve){
			
		}
		public void mouseEntered(MouseEvent eve){
			
		}
		public void mouseExited(MouseEvent eve){
			
		}
	}
	/*public static void main(String[] args){
		new MainPage();
	}
	*/
}			