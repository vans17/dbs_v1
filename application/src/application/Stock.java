package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
public class Stock {
	
	ImageIcon bkgrd = new ImageIcon(getClass().getResource("stock_logo.jpg"));
	ImageIcon medc = new ImageIcon(getClass().getResource("medicines.png"));
	ImageIcon lgt1 = new ImageIcon(getClass().getResource("logout.png"));
	ImageIcon cmplogo = new ImageIcon(getClass().getResource("cmp_logo.png"));
	ImageIcon cstlogo = new ImageIcon(getClass().getResource("cst_logo.png"));
	ImageIcon trnslogo = new ImageIcon(getClass().getResource("trns_logo.jpg"));
	ImageIcon homelogo = new ImageIcon(getClass().getResource("home_logo.png"));
	ImageIcon rfrshlogo = new ImageIcon(getClass().getResource("refresh_logo.png"));
			
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
	
	public String extract_ID(String str)
	{
		StringBuffer g;
		int i,x=0;
		for(i=str.length()-1;i>=0;i--)
		{
			if(str.charAt(i)==':')
			{
				break;
			}
			else
			{
				x++;
			}
		}
		g = new StringBuffer(x);
		for(i=str.length()-x;i<str.length();i++)
		{
			g.insert(i-str.length()+x,str.charAt(i));
		}
		//System.out.println(g);
		return g.toString();
	}
	
	public String extract_Company(String str)
	{
		StringBuffer g;
		int i,x=0;
		for(i=0;i<str.length();i++)
		{
			if(str.charAt(i)==':')
			{
				break;
			}
			else
			{
				x++;
			}
		}
		g = new StringBuffer(x);
		for(i=0;i<x;i++)
		{
			g.insert(i,str.charAt(i));
		}
		//System.out.println(g);
		return g.toString();
	}
	
	public void show_stock(String str) throws ClassNotFoundException, SQLException
	{
		strg = str;
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query="select * from employee where ID="+str+";";
		result = statement.executeQuery(query);
		if(result.next())
		{
			str=result.getString("Name");
			pswrd=result.getString("Password");
		}
		Border blackline = BorderFactory.createLineBorder(new Color(53,0,102));
		JFrame f = new JFrame("Stock");
		f.setBounds(-10,-5,1920,1080);
		f.setIconImage(medc.getImage());
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
		p3.setBorder(blackline);
		p3.setBounds(550,300,640,260);
		p3.setBackground(new Color(255,255,255));
		p3.setLayout(null);
		p3.setVisible(true);
		bg.add(p3);
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
		l2.setText("Stock");
		l2.setFont(new Font("Times New Roman",Font.ITALIC,25));
		l2.setForeground(new Color(53,0,102));
		l2.setBounds(1470,75,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l2.setFont(font.deriveFont(Font.ITALIC, 25f));
        }
        catch(Exception e){}
		p0.add(l2);
		JLabel l3 = new JLabel("Select a medicine:");
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		l3.setForeground(new Color(53,0,102));
		l3.setBounds(490,175,1000,40);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l3.setFont(font.deriveFont(Font.BOLD, 25f));
        }
        catch(Exception e){}
		bg.add(l3);
		JButton hom = new JButton(homelogo);
		hom.setBounds(70,10,160,160);
		p1.add(hom);
		JLabel homl = new JLabel("Home");
		homl.setBounds(125,170,100,25);
		homl.setFont(new Font("",Font.PLAIN,20));
		homl.setForeground(new Color(53,0,102));
		p1.add(homl);
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
		JComboBox cbmed = new JComboBox();
		cbmed.setBounds(550,250,500,20);
		query="select * from medicine ;";
		result = statement.executeQuery(query);
		while(result.next())
		{
			cbmed.addItem(result.getString("Company")+":"+result.getString("Name")+":"+result.getString("Batch_no"));
		}//hemlo
		bg.add(cbmed);
		JLabel nmed = new JLabel();
		nmed.setBounds(10,10,500,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            nmed.setFont(font.deriveFont(Font.BOLD, 25f));
        }
		catch(Exception eg){}
		nmed.setVisible(false);
		p3.add(nmed);
		JLabel cmpmed = new JLabel("Company: ");
		cmpmed.setBounds(10,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cmpmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cmpmed.setVisible(false);
		p3.add(cmpmed);
		JLabel cmpmedt = new JLabel();
		cmpmedt.setBounds(140,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cmpmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cmpmedt.setVisible(false);
		p3.add(cmpmedt);
		JLabel btchmed = new JLabel("Batch no: ");
		btchmed.setBounds(10,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            btchmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		btchmed.setVisible(false);
		p3.add(btchmed);
		JLabel btchmedt = new JLabel();
		btchmedt.setBounds(140,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            btchmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		p3.add(btchmedt);
		JLabel DOMed = new JLabel("MFG Date: ");
		DOMed.setBounds(10,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOMed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		DOMed.setVisible(false);
		p3.add(DOMed);
		JLabel DOMedt = new JLabel();
		DOMedt.setBounds(140,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOMedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		p3.add(DOMedt);
		JLabel DOEed = new JLabel("Expiry Date: ");
		DOEed.setBounds(10,180,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOEed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		DOEed.setVisible(false);
		p3.add(DOEed);
		JLabel DOEedt = new JLabel();
		DOEedt.setBounds(140,180,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOEedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		p3.add(DOEedt);
		JLabel typeed = new JLabel("Type of drug: ");
		typeed.setBounds(10,220,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            typeed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		typeed.setVisible(false);
		p3.add(typeed);
		JLabel typeedt = new JLabel();
		typeedt.setBounds(140,220,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            typeedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		p3.add(typeedt);
		JLabel mrpmed = new JLabel("MRP: ");
		mrpmed.setBounds(400,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            mrpmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		mrpmed.setVisible(false);
		p3.add(mrpmed);
		JLabel mrpmedt = new JLabel();
		mrpmedt.setBounds(530,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            mrpmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		mrpmedt.setVisible(false);
		p3.add(mrpmedt);
		JLabel cosmed = new JLabel("Cost price: ");
		cosmed.setBounds(400,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cosmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cosmed.setVisible(false);
		p3.add(cosmed);
		JLabel cosmedt = new JLabel();
		cosmedt.setBounds(530,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cosmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cosmedt.setVisible(false);
		p3.add(cosmedt);
		JLabel qtymed = new JLabel("Qty. in Stock: ");
		qtymed.setBounds(400,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            qtymed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		qtymed.setVisible(false);
		p3.add(qtymed);
		JLabel qtymedt = new JLabel();
		qtymedt.setBounds(530,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            qtymedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		qtymedt.setVisible(false);
		p3.add(qtymedt);
		JButton refresh = new JButton(rfrshlogo);
		refresh.setBounds(1100,240,40,40);
		bg.add(refresh);
		JButton cart = new JButton("Show Cart");
		cart.setBounds(75, 665, 150, 50);
		cart.setFont(new Font("",Font.BOLD,16));
		p1.add(cart);
		JButton add_cart = new JButton("Add to cart");
		add_cart.setBounds(550,600,200,50);
		add_cart.setFont(new Font("",Font.BOLD,16));
		bg.add(add_cart);
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
		hom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Home().home_display(strg);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.hide();
			}
		});
		refresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Stock b = new Stock();
				String idmed1 = b.extract_ID(cbmed.getSelectedItem().toString());
				String cmpmed1 = b.extract_Company(cbmed.getSelectedItem().toString());
				query = "select * from medicine where Company = '"+cmpmed1+"' and Batch_no = '"+idmed1+"';";
				try {
					result = statement.executeQuery(query);
					if(result.next())
					{
						nmed.setText(result.getString("Name"));
						cmpmedt.setText(result.getString("Company"));
						btchmedt.setText(result.getString("Batch_no"));
						DOMedt.setText(result.getString("D_O_M"));
						DOEedt.setText(result.getString("D_O_E"));
						typeedt.setText(result.getString("Type"));
						mrpmedt.setText("Rs. "+result.getString("MRP"));
						cosmedt.setText("Rs. "+result.getString("Cost"));
						qtymedt.setText(result.getString("Quantity")+" units");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nmed.setVisible(true);
				cmpmed.setVisible(true);
				cmpmedt.setVisible(true);
				btchmed.setVisible(true);
				btchmedt.setVisible(true);
				DOMed.setVisible(true);
				DOMedt.setVisible(true);
				DOEed.setVisible(true);
				DOEedt.setVisible(true);
				typeed.setVisible(true);
				typeedt.setVisible(true);
				mrpmed.setVisible(true);
				mrpmedt.setVisible(true);
				cosmed.setVisible(true);
				cosmedt.setVisible(true);
				qtymed.setVisible(true);
				qtymedt.setVisible(true);
			}
		});
		cart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Cart().show_cart();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add_cart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Stock c = new Stock();
				try {
					new Cart().add_to_Cart(c.extract_Company(cbmed.getSelectedItem().toString()),c.extract_ID(cbmed.getSelectedItem().toString()));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		f.setResizable(false);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Stock st = new Stock();
		st.show_stock("0");
	}

}
