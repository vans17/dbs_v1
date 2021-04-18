package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;
public class Transaction{

	ImageIcon logo = new ImageIcon(getClass().getResource("logo.png"));
	ImageIcon bkgrd = new ImageIcon(getClass().getResource("home_bg.jpg"));
	ImageIcon medc = new ImageIcon(getClass().getResource("medicines.png"));
	ImageIcon lgt1 = new ImageIcon(getClass().getResource("logout.png"));
	ImageIcon cmplogo = new ImageIcon(getClass().getResource("cmp_logo.png"));
	ImageIcon cstlogo = new ImageIcon(getClass().getResource("cst_logo.png"));
	ImageIcon trnslogo = new ImageIcon(getClass().getResource("trns_logo.jpg"));
	ImageIcon editlogo = new ImageIcon(getClass().getResource("edit_logo.jpg"));
	ImageIcon white_bg = new ImageIcon(getClass().getResource("white_bg.jpg"));
	ImageIcon homel= new ImageIcon(getClass().getResource("home_logo.png"));
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String NAME = "root";
	static final String PASSWORD = "rootpassword";
	static final String URL = "jdbc:mysql://localhost:3306/dbs_v1";
	static Connection connection;
	static String query;
	static Statement statement;
	static ResultSet result;
	static String strg;
	static String pswrd;
	
	
	public void transaction_display(String str,int status) throws ClassNotFoundException, SQLException
	{
		strg = str;
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query="select * from employee where ID="+str+";";
		result = statement.executeQuery(query);
		
		query="select * from employee where ID="+str+";";
		result = statement.executeQuery(query);
		if(result.next())
		{
			str=result.getString("Name");
			pswrd=result.getString("Password");
		}
		
		Border blackline = BorderFactory.createLineBorder(new Color(53,0,102));
		JFrame f = new JFrame("Transactions");
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
		p3.setBounds(500,240,1000,250);
		p3.setBorder(blackline);
		//p3.setBackground(new Color(0,0,0,0));
		p3.setLayout(null);
		p3.setVisible(true);
		bg.add(p3);
		JPanel p4 = new JPanel();
		p4.setBounds(500,580,1000,250);
		p4.setBorder(blackline);
		p4.setLayout(null);
		p4.setVisible(true);
		bg.add(p4);
		
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
		
		
		JLabel l2 = new JLabel();
		l2.setText("Transactions");
		l2.setFont(new Font("Times New Roman",Font.ITALIC,25));
		l2.setForeground(new Color(53,0,102));
		l2.setBounds(1380,75,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l2.setFont(font.deriveFont(Font.ITALIC, 25f));
        }
        catch(Exception e){}
		p0.add(l2);
		
		
		
		
		

		JLabel l3 = new JLabel("Medicines Bought");
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		l3.setForeground(new Color(53,0,102));
		l3.setBounds(490,175,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l3.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}

		bg.add(l3);
		JLabel l41 = new JLabel("Medicines Sold");
		l41.setFont(new Font("Times New Roman",Font.BOLD,25));
		l41.setForeground(new Color(53,0,102));
		l41.setBounds(500,520,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l41.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}

		bg.add(l41);
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
		JLabel cmpl = new JLabel("Company");
		cmpl.setBounds(283,170,100,25);
		cmpl.setFont(new Font("",Font.PLAIN,20));
		cmpl.setForeground(new Color(53,0,102));
		p1.add(cmpl);
		JButton cst = new JButton(cstlogo);
		cst.setBounds(70,210,160,160);
		p1.add(cst);
		cst.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {

	            try {
	            	new Customer().customer_display(strg,1);
	            	f.hide();
	            } catch (Exception E) {
	                // TODO Auto-generated catch block
	                E.printStackTrace();
	            }
	        }
	    });
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
		JButton hme = new JButton(homel);
		hme.setBounds(245,210,160,160);
		p1.add(hme);
		hme.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Home().home_display(strg, 0);
					f.hide();
				}
				catch(Exception er){
					er.printStackTrace();
					
				}
				
				
			}
		});
		JLabel trnsl = new JLabel("Home");
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
		
		JButton ret = new JButton("Logout");
		ret.setFont(new Font("",Font.BOLD,16));
		ret.setBounds(200, 665, 100, 50);
		p1.add(ret);
		ret.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame lgt = new JFrame("Logout");
				lgt.setBounds(540,380,250,120);
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
				query = "drop table if exists cart;";
				try {
					statement.execute(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lgt.setLayout(null);
				lgt.setVisible(true);
				lgt.setResizable(false);
			}
		});
		
		

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
		Object[] columns = {"Batch No","Purchase date","Transaction ID","Amount","Quantity","Buyer ID","Company"};
		JTable lgin = new JTable();
		lgin.setBounds(500,240,1000,250);
		lgin.setRowHeight(25);
		p3.add(lgin);
		DefaultTableModel lgint = new DefaultTableModel();
		lgint.setColumnIdentifiers(columns);
		lgin.setModel(lgint);
		JScrollPane sp = new JScrollPane(lgin);
		sp.setBounds(0,0,1000,250);
		p3.add(sp);
		Object[] row = new Object[7];
		if(!strg.equals("0"))
		{
			query = "select * from purchase_log where Buyer_ID = "+strg+";";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("Batch_no");
				row[1]=result.getString("Purchase_date");
				row[2]=result.getString("Transcation_ID");
				row[3]=result.getString("Amount");
				row[4]=result.getString("Quantity");
				row[5]=result.getString("Buyer_ID");
				row[6]=result.getString("Company");
				lgint.addRow(row);
			}
		}
		else
		{
			query = "select * from purchase_log;";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("Batch_no");
				row[1]=result.getString("Purchase_date");
				row[2]=result.getString("Transcation_ID");
				row[3]=result.getString("Amount");
				row[4]=result.getString("Quantity");
				row[5]=result.getString("Buyer_ID");
				row[6]=result.getString("Company");
				lgint.addRow(row);
			}
		}
		Object[] columns1 = {"Batch No.","Sale date","Transaction ID","Amount","Quantity","Seller ID","Buyer name"};
		JTable lgin1 = new JTable();
		lgin1.setBounds(500,550,1000,250);
		lgin1.setRowHeight(25);
		p4.add(lgin1);
		DefaultTableModel lgint1 = new DefaultTableModel();
		lgint1.setColumnIdentifiers(columns1);
		lgin1.setModel(lgint1);
		JScrollPane sp1 = new JScrollPane(lgin1);
		sp1.setBounds(0,0,1000,250);
		p4.add(sp1);
		Object[] row1 = new Object[7];
		if(!strg.equals("0"))
		{
			query = "select * from sales_log where Seller_ID = "+strg+";";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row1[0]=result.getString("Batch_no");
				row1[1]=result.getString("Sale_date");
				row1[2]=result.getString("Sales_transaction_ID");
				row1[3]=result.getString("Amount");
				row1[4]=result.getString("Quantity");
				row1[5]=result.getString("Seller_ID");
				row1[6]=result.getString("Buyer_name");
				lgint1.addRow(row1);
			}
		}
		else
		{
			query = "select * from sales_log;";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row1[0]=result.getString("Batch_no");
				row1[1]=result.getString("Sale_date");
				row1[2]=result.getString("Sales_transaction_ID");
				row1[3]=result.getString("Amount");
				row1[4]=result.getString("Quantity");
				row1[5]=result.getString("Seller_ID");
				row1[6]=result.getString("Buyer_name");
				lgint1.addRow(row1);
			}
		}

		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setIconImage(logo.getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}


	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new Transaction().transaction_display("0", 1);;
	}
}
