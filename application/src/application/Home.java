package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;
public class Home {

	ImageIcon logo = new ImageIcon(getClass().getResource("logo.png"));
	ImageIcon bkgrd = new ImageIcon(getClass().getResource("home_bg.jpg"));
	ImageIcon medc = new ImageIcon(getClass().getResource("medicines.png"));
	ImageIcon lgt1 = new ImageIcon(getClass().getResource("logout.png"));
	ImageIcon cmplogo = new ImageIcon(getClass().getResource("cmp_logo.png"));
	ImageIcon cstlogo = new ImageIcon(getClass().getResource("cst_logo.png"));
	ImageIcon trnslogo = new ImageIcon(getClass().getResource("trns_logo.jpg"));
	ImageIcon editlogo = new ImageIcon(getClass().getResource("edit_logo.jpg"));
	ImageIcon white_bg = new ImageIcon(getClass().getResource("white_bg.jpg"));
	ImageIcon app_logo = new ImageIcon(getClass().getResource("app_logo.png"));
	ImageIcon cont_logo = new ImageIcon(getClass().getResource("cont_logo.png"));
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String NAME = "root";
	static final String PASSWORD = "rootpassword";
	static final String URL = "jdbc:mysql://localhost:3306/dbs_v1";
	static Connection connection;
	static String query;
	static String q1;
	static Statement statement;
	static Statement statement1;
	static ResultSet result;
	static ResultSet r1;
	static String strg;
	static String pswrd;
	
	
	public void home_display(String str,int status) throws ClassNotFoundException, SQLException
	{
		strg = str;
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		statement1 = connection.createStatement();
		query="select * from employee where ID="+str+";";
		result = statement.executeQuery(query);
		if(result.next()&&status==1)
		{
			query = "insert into login values("+result.getString("ID")+",curtime(),curdate(),'"+result.getString("Password")+"','"+result.getString("Name")+"');";
			statement.execute(query);
		}
		query="select * from employee where ID="+str+";";
		result = statement.executeQuery(query);
		if(result.next())
		{
			str=result.getString("Name");
			pswrd=result.getString("Password");
		}
		Home b = new Home();
		Border blackline = BorderFactory.createLineBorder(new Color(53,0,102));
		JFrame f = new JFrame("Home");
		f.setBounds(-10,-5,1920,1080);
		JLabel bg = new JLabel("",bkgrd,JLabel.CENTER);
		bg.setBounds(-50,-50,1920,1080);
		f.add(bg);
		JPanel p0 = new JPanel();
		p0.setBorder(blackline);
		p0.setBounds(0,0,1920,151);
		p0.setBackground(new Color(0,0,0,0));
		p0.setLayout(null);
		p0.setVisible(true);
		bg.add(p0);
		JPanel p1 = new JPanel();
		p1.setBorder(blackline);
		p1.setBounds(0,150,450,1080);
		p1.setBackground(new Color(0,0,0,0));
		p1.setLayout(null);
		p1.setVisible(true);
		bg.add(p1);
		JPanel p2 = new JPanel();
		p2.setBorder(blackline);
		p2.setBounds(60,445,375,200);
		p2.setBackground(new Color(0,0,0,0));
		p2.setLayout(null);
		p2.setVisible(true);
		p1.add(p2);
		JPanel p3 = new JPanel();
		p3.setBounds(500,240,1040,320);
		p3.setBorder(blackline);
		p3.setBackground(new Color(0,0,0,0));
		p3.setLayout(null);
		p3.setVisible(true);
		bg.add(p3);
		JLabel aplog = new JLabel("",app_logo,JLabel.CENTER);
		aplog.setBounds(750,50,200,100);
		p0.add(aplog);
		JLabel l0 = new JLabel();
		l0.setText("Welcome,");
		l0.setFont(new Font("Times New Roman",Font.BOLD,25));
		l0.setForeground(new Color(53,0,102));
		l0.setBounds(75,65,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l0.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}
		p0.add(l0);
		JLabel l1 = new JLabel();
		l1.setText(str);
		l1.setFont(new Font("Times New Roman",Font.BOLD,25));
		l1.setForeground(new Color(53,0,102));
		l1.setBounds(75,100,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l1.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}
		p0.add(l1);
		JButton new_acc = new JButton("Add new Employee");
		new_acc.setBounds(1250, 75, 160, 40);
		new_acc.addActionListener(new ActionListener()
		{
			

			public void actionPerformed(ActionEvent e)
			{
				JFrame ep = new JFrame("Create New Account");
				ep.setBounds(600,250,500,385);
				ep.setIconImage(editlogo.getImage());

				JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
				bg1.setBounds(0, 0, 500, 350);
				ep.add(bg1);
				JLabel msg1 = new JLabel();
				msg1.setText("*Password must be within 6 and 14 characters");
				msg1.setBounds(180, 320,300,20);
				msg1.setFont(new Font("Arial",Font.PLAIN,14));
				msg1.setForeground(Color.red);
				msg1.setVisible(false);
				bg1.add(msg1);
				JLabel msg2 = new JLabel();
				msg2.setText("*Username or password cannot be empty");
				msg2.setBounds(200, 320,300,20);
				msg2.setFont(new Font("Arial",Font.PLAIN,14));
				msg2.setForeground(Color.red);
				msg2.setVisible(false);
				bg1.add(msg2);
				JLabel err = new JLabel("*Password must be within 6 and 14 characters");
				err.setForeground(Color.red);
				err.setFont(new Font("Times New Roman",Font.PLAIN,16));
				err.setBounds(120, 220, 300, 20);
				err.setVisible(false);
				bg1.add(err);
				JLabel ni = new JLabel("Id:");
				ni.setBounds(50,30,300,30);
				ni.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ni);
				JLabel nn = new JLabel("Name:");
				nn.setBounds(50,60,300,30);
				nn.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nn);
				JLabel ns = new JLabel("Salary:");
				ns.setBounds(50,90,100,30);
				ns.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ns);
				JLabel ndb = new JLabel("DOB:");
				ndb.setBounds(50,120,300,30);
				ndb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndb);
				JLabel ndj = new JLabel("Date of Joining:");
				ndj.setBounds(50,150,300,30);
				ndj.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndj);
				JLabel np = new JLabel("Password:");
				np.setBounds(50,180,300,30);
				np.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(np);
				JLabel na = new JLabel("Address:");
				na.setBounds(50,210,300,30);
				na.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(na);
				JTextField edz = new JTextField();
				
				edz.setBounds(250,30,200,30);
				edz.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edz);
				JTextField eda = new JTextField();
				
				eda.setBounds(250,60,200,30);
				eda.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(eda);
				JTextField edb = new JTextField();
				edb.setBounds(250,90,200,30);
				
				edb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edb);
				JTextField edc = new JTextField();
				edc.setBounds(250,120,200,30);
				
				edc.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edc);
				JTextField edd = new JTextField();
				edd.setBounds(250,150,200,30);
				
				edd.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edd);
				JTextField ede = new JTextField();
				ede.setBounds(250,180,200,30);
				
				ede.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ede);
				JTextField edf = new JTextField();
				edf.setBounds(250,210,200,30);
				
				edf.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edf);
				JButton save = new JButton("Save Changes");
				save.setFont(new Font("",Font.BOLD,13));
				save.setBounds(220, 280, 125, 35);
				bg1.add(save);
				JButton cancel = new JButton("Cancel");
				cancel.setFont(new Font("",Font.BOLD,13));
				cancel.setBounds(360, 280, 105, 35);
				bg1.add(cancel);
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ep.hide();
					}
				});
				save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
						String s_id=edz.getText().toString().trim();
						String s_name=eda.getText().toString().trim();
						String s_sal=edb.getText().toString().trim();
						String s_dob=edc.getText().toString().trim();
						String s_doj=edd.getText().toString().trim();
						String s_pass=ede.getText().toString().trim();
						String s_add=edf.getText().toString().trim();
						
						if(s_name.length()==0) {
							
							msg2.setVisible(true);
						}
						else if((s_name.length()>0) &&!(s_pass.length()>=6  &&  s_pass.length()<=14)) {
							msg2.setVisible(false);
							msg1.setVisible(true);
							
						}
						else  {
							query="insert into dbs_v1.employee values('"+s_id+"','"+s_add+"','"+s_name+"','"+s_doj+"','"+s_dob+"','"+s_pass+"','"+s_sal+"'); ";
							try
							{
								statement.execute(query);
								
							}
							catch(Exception ef)
							{
								ef.printStackTrace();
							}
							ep.hide();
							
						}
						
						
						
						
									
						
						
						
						
						
					}
				});
				ep.setResizable(false);
				ep.setLayout(null);
				ep.setVisible(true);
			}
		});
		
		JLabel l2 = new JLabel();
		l2.setText("Home");
		l2.setFont(new Font("Times New Roman",Font.ITALIC,25));
		l2.setForeground(new Color(53,0,102));
		l2.setBounds(1470,75,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l2.setFont(font.deriveFont(Font.ITALIC, 25f));
        }
        catch(Exception e){}
		p0.add(l2);
		
		
		
		p0.add(new_acc);
		if(!strg.equals("0")) {
			new_acc.setVisible(false);
		}
			
		

		JLabel l3 = new JLabel("Login History");
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		l3.setForeground(new Color(53,0,102));
		l3.setBounds(490,175,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l3.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}
		
		
		
		
		
		bg.add(l3);
		JButton med = new JButton(medc);
		med.setBounds(70,10,160,160);
		p1.add(med);
		JLabel medl = new JLabel("Stock");
		medl.setBounds(125,170,100,25);
		medl.setFont(new Font("",Font.PLAIN,20));
		medl.setForeground(new Color(53,0,102));
		p1.add(medl);
		JButton cmp = new JButton(cmplogo);
		cmp.setBounds(245,10,160,160);
		p1.add(cmp);
		JLabel cmpl = new JLabel("Company");
		cmpl.setBounds(283,170,100,25);
		cmpl.setFont(new Font("",Font.PLAIN,20));
		cmpl.setForeground(new Color(53,0,102));
		p1.add(cmpl);
		JButton cst = new JButton(cstlogo);
		cst.setBounds(70,210,160,160);
		p1.add(cst);
		JLabel cstl1 = new JLabel("Customer");
		cstl1.setBounds(100,370,100,25);
		cstl1.setFont(new Font("",Font.PLAIN,20));
		cstl1.setForeground(new Color(53,0,102));
		p1.add(cstl1);
		JLabel cstl2 = new JLabel("History");
		cstl2.setBounds(110,390,100,25);
		cstl2.setFont(new Font("",Font.PLAIN,20));
		cstl2.setForeground(new Color(53,0,102));
		p1.add(cstl2);
		JButton trns = new JButton(trnslogo);
		trns.setBounds(245,210,160,160);
		p1.add(trns);
		JLabel trnsl = new JLabel("Transactions");
		trnsl.setBounds(265,370,150,25);
		trnsl.setFont(new Font("",Font.PLAIN,20));
		trnsl.setForeground(new Color(53,0,102));
		p1.add(trnsl);
		JLabel l4 = new JLabel("Profile");
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l4.setFont(font.deriveFont(Font.BOLD, 24f));
        }
        catch(Exception e){}
		l4.setBounds(15,5,150,40);
		l4.setForeground(new Color(53,0,102));
		p2.add(l4);
		JLabel l5 = new JLabel("Name: "+str);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l5.setFont(font.deriveFont(Font.BOLD, 20f));
        }
        catch(Exception e){}
		l5.setBounds(15,60,345,40);
		l5.setForeground(new Color(53,0,102));
		p2.add(l5);
		JLabel l6 = new JLabel("ID: "+result.getString("ID"));
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l6.setFont(font.deriveFont(Font.BOLD, 20f));
        }
        catch(Exception e){}
		l6.setBounds(15,90,345,40);
		l6.setForeground(new Color(53,0,102));
		p2.add(l6);
		JLabel l7 = new JLabel("Date of Joining: "+result.getString("Date_of_Joining"));
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l7.setFont(font.deriveFont(Font.BOLD, 20f));
        }
        catch(Exception e){}
		l7.setBounds(15,120,345,40);
		l7.setForeground(new Color(53,0,102));
		p2.add(l7);
		JLabel l8 = new JLabel("Salary: "+result.getString("Salary"));
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l8.setFont(font.deriveFont(Font.BOLD, 20f));
        }
        catch(Exception e){}
		l8.setBounds(15,150,345,40);
		l8.setForeground(new Color(53,0,102));
		p2.add(l8);
		JButton edit = new JButton("Edit Profile");
		edit.setBounds(75, 665, 150, 50);
		edit.setFont(new Font("",Font.BOLD,16));
		p1.add(edit);
		JButton ret = new JButton("Logout");
		ret.setFont(new Font("",Font.BOLD,16));
		ret.setBounds(300, 665, 100, 50);
		p1.add(ret);
		ret.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame lgt = new JFrame("Logout");
				lgt.setBounds(640,380,250,120);
				lgt.setIconImage(lgt1.getImage());
				JLabel msg = new JLabel("Are you sure you want to logout?");
				msg.setBounds(25,10,200,20);
				lgt.add(msg);
				JButton yes = new JButton("Yes");
				yes.setBounds(25, 40, 75, 30);
				lgt.add(yes);
				JButton no = new JButton("No");
				no.setBounds(135, 40, 75, 30);
				lgt.add(no);
				no.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						lgt.hide();
					}
				});
				yes.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						lgt.hide();
						f.hide();
						try {
							new Main().main(null);
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				lgt.setLayout(null);
				lgt.setVisible(true);
				lgt.setResizable(false);
			}
		});
		edit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				query = "select * from employee where ID = "+strg+";";
				try {
					result = statement.executeQuery(query);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				JFrame ep = new JFrame("Edit Profile");
				ep.setBounds(600,250,500,350);
				ep.setIconImage(editlogo.getImage());
				JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
				bg1.setBounds(0, 0, 500, 350);
				ep.add(bg1);
				JLabel err = new JLabel("*Password must be within 6 and 14 characters");
				err.setForeground(Color.red);
				err.setFont(new Font("Times New Roman",Font.PLAIN,16));
				err.setBounds(120, 220, 300, 20);
				err.setVisible(false);
				bg1.add(err);
				JLabel nn = new JLabel("New Name:");
				nn.setBounds(50,30,300,30);
				nn.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nn);
				JLabel ns = new JLabel("New Salary:");
				ns.setBounds(50,60,100,30);
				ns.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ns);
				JLabel ndb = new JLabel("New DOB:");
				ndb.setBounds(50,90,300,30);
				ndb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndb);
				JLabel ndj = new JLabel("New Date of Joining:");
				ndj.setBounds(50,120,300,30);
				ndj.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndj);
				JLabel np = new JLabel("New Password:");
				np.setBounds(50,150,300,30);
				np.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(np);
				JLabel na = new JLabel("New Address:");
				na.setBounds(50,180,300,30);
				na.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(na);
				JTextField eda = new JTextField();
				eda.setBounds(250,30,200,30);
				eda.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(eda);
				JTextField edb = new JTextField();
				edb.setBounds(250,60,200,30);
				edb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edb);
				JTextField edc = new JTextField();
				edc.setBounds(250,90,200,30);
				edc.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edc);
				JTextField edd = new JTextField();
				edd.setBounds(250,120,200,30);
				edd.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edd);
				JTextField ede = new JTextField();
				ede.setBounds(250,150,200,30);
				ede.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ede);
				JTextField edf = new JTextField();
				edf.setBounds(250,180,200,30);
				edf.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edf);
				try {
					if(result.next())
					{
						eda.setText(result.getString("Name"));
						edb.setText(result.getString("Salary"));
						edc.setText(result.getString("D_O_B"));
						edd.setText(result.getString("Date_of_Joining"));
						ede.setText(result.getString("Password"));
						edf.setText(result.getString("Address"));
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JButton save = new JButton("Save Changes");
				save.setFont(new Font("",Font.BOLD,13));
				save.setBounds(220, 250, 125, 35);
				bg1.add(save);
				JButton cancel = new JButton("Cancel");
				cancel.setFont(new Font("",Font.BOLD,13));
				cancel.setBounds(360, 250, 105, 35);
				bg1.add(cancel);
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ep.hide();
					}
				});
				save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if((ede.getText().toString().trim().length()<6||ede.getText().toString().trim().length()>14)&&!eda.getText().toString().trim().equals("admin"))
						err.setVisible(true);
						else {
						try {
							String a;
							a=eda.getText().toString().trim();
							if(a.equals(""))
							a=" ";
							query = "update employee set Name = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							a=edb.getText().toString().trim();
							if(a.equals(""))
							a="0";
							query = "update employee set Salary = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							a=ede.getText().toString().trim();
							if(a.equals(""))
							a=" ";
							query = "update employee set Password = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							a=edf.getText().toString().trim();
							if(a.equals(""))
							a=" ";
							query = "update employee set Address = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							a=edc.getText().toString().trim();
							if(a.equals(""))
							a="0000-00-00";
							query = "update employee set D_O_B = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							a=edd.getText().toString().trim();
							if(a.equals(""))
							a="0000-00-00";
							query = "update employee set Date_of_Joining = '"+a+"' where ID = '"+strg+"';";
							statement.execute(query);
							f.hide();
							Home asp = new Home();
							try {
								asp.home_display(strg,0);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ep.hide();}
					}
				});
				ep.setResizable(false);
				ep.setLayout(null);
				ep.setVisible(true);
			}
		});
		
		////////////////////////////////////////////////////////
		
		
		
		
		
		
		
	///////////////////////////////////////////////////////////////////	
		med.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Stock().show_stock(strg);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.hide();
			}
		});
		
		cmp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Company().Company_display(strg);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.hide();
			}
		});
		
		Object[] columns = {"ID","Time","Date","Name"};
		JTable lgin = new JTable();
		lgin.setBounds(500,240,1040,320);
		lgin.setRowHeight(25);
		p3.add(lgin);
		DefaultTableModel lgint = new DefaultTableModel();
		lgint.setColumnIdentifiers(columns);
		lgin.setModel(lgint);
		JScrollPane sp = new JScrollPane(lgin);
		sp.setBounds(0,0,1040,320);
		p3.add(sp);
		Object[] row = new Object[4];
		if(!strg.equals("0"))
		{
			query = "select * from login where ID = "+strg+";";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("ID");
				row[1]=result.getString("Time");
				row[2]=result.getString("Date");
				row[3]=result.getString("Name");
				lgint.addRow(row);
			}
		}
		else
		{
			query = "select * from login;";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("ID");
				row[1]=result.getString("Time");
				row[2]=result.getString("Date");
				row[3]=result.getString("Name");
				lgint.addRow(row);
			}
		}
		JButton cont = new JButton ("Add contact details");
		cont.setBounds(500,600,200,50);
		cont.setFont(new Font("",Font.BOLD,16));
		bg.add(cont);
		cont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame adcont = new JFrame("Contact Details");
				adcont.getContentPane().setBackground(Color.white);
				adcont.setIconImage(cont_logo.getImage());
				JLabel num = new JLabel("Enter your number:");
				num.setBounds(20,40,200,20);
				num.setFont(new Font("Times New Roman",Font.PLAIN,16));
				adcont.add(num);
				JTextField numtf = new JTextField();
				numtf.setBounds(170,40,200,20);
				numtf.setFont(new Font("Times New Roman",Font.PLAIN,16));
				adcont.add(numtf);
				JLabel noerr = new JLabel("*Invalid contact number");
				noerr.setBounds(160,60,200,30);
				noerr.setFont(new Font("Times New Roman",Font.PLAIN,16));
				noerr.setForeground(Color.red);
				noerr.setVisible(false);
				adcont.add(noerr);
				JLabel duperr = new JLabel("*Duplicate contact number");
				duperr.setBounds(160,60,200,30);
				duperr.setFont(new Font("Times New Roman",Font.PLAIN,16));
				duperr.setForeground(Color.red);
				duperr.setVisible(false);
				adcont.add(duperr);
				JButton savecont = new JButton("Add number");
				savecont.setBounds(150,100,120,30);
				adcont.add(savecont);
				JButton cnclcont = new JButton("Cancel");
				cnclcont.setBounds(290,100,80,30);
				adcont.add(cnclcont);
				savecont.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						noerr.setVisible(false);
						duperr.setVisible(false);
						String contact = numtf.getText().toString().trim();
						if(contact.length()<6||contact.length()>10)
						noerr.setVisible(true);
						else
						{
							query = "select * from employee_contact where ID = "+strg+" and Contact_no = "+contact+";";
							try {
								result = statement.executeQuery(query);
								if(!result.next())
								{
									query = "insert into employee_contact values ("+contact+","+strg+");";
									try {
										statement.execute(query);
										adcont.hide();
										f.hide();
										new Home().home_display(strg, 0);
									} catch (SQLException | ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else
								duperr.setVisible(true);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
				});
				cnclcont.addActionListener(new ActionListener()
				{

					public void actionPerformed(ActionEvent e) 
					{
						adcont.hide();
					}
					
				});
				adcont.setBounds(700,300,400,180);
				adcont.setLayout(null);
				adcont.setVisible(true);
				adcont.setResizable(false);
			}
		});
		JButton rmvcont = new JButton ("Remove selected contact");
		rmvcont.setBounds(500,700,250,50);
		rmvcont.setFont(new Font("",Font.BOLD,16));
		bg.add(rmvcont);
		if(!strg.equals("0"))
		{
			Object[] clmn = {"Contact"};
			JTable empcnt = new JTable();
			empcnt.setBounds(1000,600,100,100);
			empcnt.setRowHeight(25);
			bg.add(empcnt);
			DefaultTableModel empcntt = new DefaultTableModel();
			empcntt.setColumnIdentifiers(clmn);
			empcnt.setModel(empcntt);
			JScrollPane sp1 = new JScrollPane(empcnt);
			sp1.setBounds(1000,600,100,100);
			bg.add(sp1);
			query = "select * from employee_contact where ID = "+strg+";";
			result = statement.executeQuery(query);
			Object[] row1 = new Object[2];
			while(result.next())
			{
				row1[0] = result.getString("Contact_no");
				empcntt.addRow(row1);
			}
			rmvcont.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int i = empcnt.getSelectedRow();
					if(i>=0)
					{
						String no1;
						no1 = empcntt.getValueAt(i, 0).toString().trim();
						query = "delete from employee_contact where ID = "+strg+" and Contact_no = "+no1+";";
						try {
							empcntt.removeRow(i);
							statement.execute(query);
							f.hide();
							new Home().home_display(strg, 0);
						} catch (SQLException | ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
			});
		}
		else
		{
			Object[] clmn = {"Name","ID","Contact"};
			JTable empcnt = new JTable();
			empcnt.setBounds(1000,600,450,100);
			empcnt.setRowHeight(25);
			bg.add(empcnt);
			DefaultTableModel empcntt = new DefaultTableModel();
			empcntt.setColumnIdentifiers(clmn);
			empcnt.setModel(empcntt);
			JScrollPane sp1 = new JScrollPane(empcnt);
			sp1.setBounds(1000,600,450,100);
			bg.add(sp1);
			query = "select * from employee_contact;";
			result = statement.executeQuery(query);
			Object[] row1 = new Object[3];
			while(result.next())
			{
				q1 = "select * from employee where ID = "+result.getString("ID")+";";
				r1 = statement1.executeQuery(q1);
				if(r1.next())
				{
					row1[0] = r1.getString("Name");
				}
				row1[1] = result.getString("ID");
				row1[2] = result.getString("Contact_no");
				empcntt.addRow(row1);
			}
			rmvcont.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int i = empcnt.getSelectedRow();
					if(i>=0)
					{
						String id1,no1;
						id1 = empcntt.getValueAt(i, 1).toString().trim();
						no1 = empcntt.getValueAt(i, 2).toString().trim();
						query = "delete from employee_contact where ID = "+id1+" and Contact_no = "+no1+";";
						try {
							empcntt.removeRow(i);
							statement.execute(query);
							f.hide();
							new Home().home_display(strg, 0);
						} catch (SQLException | ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
			});
		}
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setIconImage(logo.getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Home b = new Home();
		b.home_display("0",0);
	}
}
