package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
public class Stock {
	
	ImageIcon bkgrd = new ImageIcon(getClass().getResource("stock_logo.jpg"));
	ImageIcon madc = new ImageIcon(getClass().getResource("medicines.png"));
	ImageIcon lgt1 = new ImageIcon(getClass().getResource("logout.png"));
	ImageIcon cmplogo = new ImageIcon(getClass().getResource("cmp_logo.png"));
	ImageIcon cstlogo = new ImageIcon(getClass().getResource("cst_logo.png"));
	ImageIcon trnslogo = new ImageIcon(getClass().getResource("trns_logo.jpg"));
	ImageIcon homelogo = new ImageIcon(getClass().getResource("home_logo.png"));
	ImageIcon rfrshlogo = new ImageIcon(getClass().getResource("refresh_logo.png"));
	ImageIcon panelbg1 = new ImageIcon(getClass().getResource("cart_bg.jpg"));
	ImageIcon app_logo = new ImageIcon(getClass().getResource("app_logo.png"));
	ImageIcon white_bg = new ImageIcon(getClass().getResource("white_bg.jpg"));
	ImageIcon white_bg1 = new ImageIcon(getClass().getResource("white_bg1.jpg"));
	ImageIcon warning = new ImageIcon(getClass().getResource("warning.png"));
			
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
		return g.toString().trim();
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
		return g.toString().trim();
	}
	
	public String extract_Date(String str)
	{
		StringBuffer g = new StringBuffer(8);
		int i,j=0;
		for(i=0;i<str.length();i++)
		{
			if(str.charAt(i)!='-')
			{
				g.insert(j,str.charAt(i));
				j++;
			}
		}
		return g.toString().trim();
	}
	
	public String extract_Time(String str)
	{
		StringBuffer g = new StringBuffer(6);
		int i,j=0;
		for(i=0;i<str.length();i++)
		{
			if(str.charAt(i)!=':')
			{
				g.insert(j,str.charAt(i));
				j++;
			}
		}
		return g.toString().trim();
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
		f.setIconImage(madc.getImage());
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
		p3.setBounds(550,300,603,260);
		p3.setBackground(new Color(255,255,255));
		p3.setLayout(null);
		p3.setVisible(true);
		bg.add(p3);
		JLabel panelbg = new JLabel("",panelbg1,JLabel.CENTER);
		panelbg.setBounds(-19,1,640,258);
		p3.add(panelbg);
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
		JButton trns = new JButton(trnslogo);
		trns.setBounds(245,210,160,160);
		p1.add(trns);
		trns.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Transaction().transaction_display(strg, 1);
					f.hide();
				}
				catch(Exception err) {}
				
			}
		});
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
		nmed.setBounds(50,10,500,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            nmed.setFont(font.deriveFont(Font.BOLD, 25f));
        }
		catch(Exception eg){}
		nmed.setVisible(false);
		panelbg.add(nmed);
		JLabel cmpmed = new JLabel("Company: ");
		cmpmed.setBounds(50,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cmpmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cmpmed.setVisible(false);
		panelbg.add(cmpmed);
		JLabel cmpmedt = new JLabel();
		cmpmedt.setBounds(180,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cmpmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cmpmedt.setVisible(false);
		panelbg.add(cmpmedt);
		JLabel btchmed = new JLabel("Batch no: ");
		btchmed.setBounds(50,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            btchmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		btchmed.setVisible(false);
		panelbg.add(btchmed);
		JLabel btchmedt = new JLabel();
		btchmedt.setBounds(180,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            btchmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		panelbg.add(btchmedt);
		JLabel DOMed = new JLabel("MFG Date: ");
		DOMed.setBounds(50,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOMed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		DOMed.setVisible(false);
		panelbg.add(DOMed);
		JLabel DOMedt = new JLabel();
		DOMedt.setBounds(180,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOMedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		panelbg.add(DOMedt);
		JLabel DOEed = new JLabel("Expiry Date: ");
		DOEed.setBounds(50,180,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOEed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		DOEed.setVisible(false);
		panelbg.add(DOEed);
		JLabel DOEedt = new JLabel();
		DOEedt.setBounds(180,180,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            DOEedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		panelbg.add(DOEedt);
		JLabel typeed = new JLabel("Type of drug: ");
		typeed.setBounds(50,220,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            typeed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		typeed.setVisible(false);
		panelbg.add(typeed);
		JLabel typeedt = new JLabel();
		typeedt.setBounds(180,220,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            typeedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		panelbg.add(typeedt);
		JLabel mrpmed = new JLabel("MRP: ");
		mrpmed.setBounds(400,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            mrpmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		mrpmed.setVisible(false);
		panelbg.add(mrpmed);
		JLabel mrpmedt = new JLabel();
		mrpmedt.setBounds(530,60,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            mrpmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		mrpmedt.setVisible(false);
		panelbg.add(mrpmedt);
		JLabel cosmed = new JLabel("Cost price: ");
		cosmed.setBounds(400,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cosmed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cosmed.setVisible(false);
		panelbg.add(cosmed);
		JLabel cosmedt = new JLabel();
		cosmedt.setBounds(530,100,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            cosmedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		cosmedt.setVisible(false);
		panelbg.add(cosmedt);
		JLabel qtymed = new JLabel("Qty. in Stock: ");
		qtymed.setBounds(400,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            qtymed.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		qtymed.setVisible(false);
		panelbg.add(qtymed);
		JLabel qtymedt = new JLabel();
		qtymedt.setBounds(530,140,200,30);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            qtymedt.setFont(font.deriveFont(Font.BOLD, 18f));
        }
		catch(Exception eg){}
		qtymedt.setVisible(false);
		panelbg.add(qtymedt);
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
		JButton add_med = new JButton("Add new medicine");
		add_med.setBounds(1200,300,200,50);
		add_med.setFont(new Font("",Font.BOLD,16));
		bg.add(add_med);
		JButton updt_med = new JButton("Update medicine data");
		updt_med.setBounds(1200,375,200,50);
		updt_med.setFont(new Font("",Font.BOLD,16));
		bg.add(updt_med);
		JButton buy_med = new JButton("Buy from company");
		buy_med.setBounds(1200,450,200,50);
		buy_med.setFont(new Font("",Font.BOLD,16));
		bg.add(buy_med);
		JLabel expmsg = new JLabel("*This product has expired");
		expmsg.setBounds(380,190,200,30);
		expmsg.setFont(new Font("Times New Roman",Font.PLAIN,18));
		expmsg.setForeground(Color.red);
		expmsg.setVisible(false);
		panelbg.add(expmsg);
		JLabel qtymsg = new JLabel("*Stock for this product is over");
		qtymsg.setBounds(350,190,250,30);
		qtymsg.setFont(new Font("Times New Roman",Font.PLAIN,18));
		qtymsg.setForeground(Color.red);
		qtymsg.setVisible(false);
		panelbg.add(qtymsg);
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
					new Home().home_display(strg,0);
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
				expmsg.setVisible(false);
				qtymsg.setVisible(false);
				String dt="",expdate="",qty="";
				Stock b = new Stock();
				String idmed1 = b.extract_ID(cbmed.getSelectedItem().toString().trim());
				query = "select * from medicine where Batch_no = '"+idmed1+"';";
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
						expdate = result.getString("D_O_E");
						qty=result.getString("Quantity");
						nmed.setForeground(null);
						qtymed.setForeground(null);
						qtymedt.setForeground(null);
						DOEed.setForeground(null);
						DOEedt.setForeground(null);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				query = "select curdate();";
				try {
					result = statement.executeQuery(query);
					if(result.next())
					dt=result.getString("curdate()");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int dt1,expdt1;
				dt1 = Integer.parseInt(b.extract_Date(dt));
				expdt1 = Integer.parseInt(b.extract_Date(expdate));
				if(dt1>expdt1)
				{
					DOEed.setForeground(Color.red);
					DOEedt.setForeground(Color.red);
					nmed.setForeground(Color.red);
					expmsg.setVisible(true);
				}
				else
				expmsg.setVisible(false);
				if(Integer.parseInt(qty)==0)
				{
					nmed.setForeground(Color.red);
					qtymed.setForeground(Color.red);
					qtymedt.setForeground(Color.red);
					qtymsg.setVisible(true);
				}
				else
				qtymsg.setVisible(false);
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
		cart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					new Cart().show_cart(strg);
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
				String dt="",expdate="",qty="";
				String idmed1 = c.extract_ID(cbmed.getSelectedItem().toString().trim());
				query = "select * from medicine where Batch_no = '"+idmed1+"';";
				try {
					result = statement.executeQuery(query);
					if(result.next())
					{
						expdate = result.getString("D_O_E");
						qty = result.getString("Quantity");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				query = "select curdate();";
				try {
					result = statement.executeQuery(query);
					if(result.next())
					dt=result.getString("curdate()");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int dt1,expdt1;
				dt1 = Integer.parseInt(c.extract_Date(dt));
				expdt1 = Integer.parseInt(c.extract_Date(expdate));
				if(dt1>expdt1)
				{
					JFrame exper = new JFrame("Error");
					exper.setBounds(600,400,355,100);
					exper.setIconImage(warning.getImage());
					exper.getContentPane().setBackground(Color.white);
					JLabel emsg1 = new JLabel();
					emsg1.setText("This product has expired and cannot be added to cart!");
					emsg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
					exper.add(emsg1);
					exper.setVisible(true);
					exper.setLayout(null);
					exper.setResizable(false);
				}
				else if(Integer.parseInt(qty)==0)
				{
					JFrame qtyer = new JFrame("Error");
					qtyer.setBounds(600,400,355,100);
					qtyer.setIconImage(warning.getImage());
					qtyer.getContentPane().setBackground(Color.white);
					JLabel bg5 = new JLabel("",JLabel.CENTER);
					bg5.setBounds(0,0,355,100);
					bg5.setBackground(new Color(0,0,0,0));
					qtyer.add(bg5);
					JLabel emsg2 = new JLabel();
					emsg2.setText("The stock for this product is over!");
					emsg2.setFont(new Font("Times New Roman",Font.PLAIN,16));
					emsg2.setBounds(60,20,250,20);
					bg5.add(emsg2);
					qtyer.setVisible(true);
					qtyer.setLayout(null);
					qtyer.setResizable(false);
				}
				else
				{
					try {
						new Cart().add_to_Cart(c.extract_Company(cbmed.getSelectedItem().toString().trim()),c.extract_ID(cbmed.getSelectedItem().toString().trim()),strg);
						JOptionPane.showMessageDialog(null,"Added to cart successfully","Success Operation",1);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add_med.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame amed = new JFrame("Add New Medicine");
				amed.setBounds(600,250,500,435);
				amed.setIconImage(madc.getImage());
				JLabel bg1 = new JLabel("",white_bg1,JLabel.CENTER);
				bg1.setBounds(0, 0, 500, 435);
				amed.add(bg1);
				JLabel nn = new JLabel("Name: ");
				nn.setBounds(50,20,300,30);
				nn.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nn);
				JLabel nbn = new JLabel("Batch no.: ");
				nbn.setBounds(50,50,300,30);
				nbn.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nbn);
				JLabel nt = new JLabel("Type: ");
				nt.setBounds(50,80,300,30);
				nt.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nt);
				JLabel nc = new JLabel("Company name: ");
				nc.setBounds(50,110,300,30);
				nc.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nc);
				JLabel ncst = new JLabel("Cost: ");
				ncst.setBounds(50,140,300,30);
				ncst.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ncst);
				JLabel ndom = new JLabel("D.O.M: ");
				ndom.setBounds(50,170,300,30);
				ndom.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndom);
				JLabel ndoe = new JLabel("D.O.E: ");
				ndoe.setBounds(50,200,300,30);
				ndoe.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndoe);
				JLabel nm = new JLabel("MRP: ");
				nm.setBounds(50,230,300,30);
				nm.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nm);
				JLabel nq = new JLabel("Quantity: ");
				nq.setBounds(50,260,300,30);
				nq.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nq);
				JTextField ada = new JTextField();
				
				ada.setBounds(250,20,200,30);
				ada.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ada);
				JTextField adb = new JTextField();
				adb.setBounds(250,50,200,30);
				
				adb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adb);
				JTextField adc = new JTextField();
				adc.setBounds(250,80,200,30);
				
				adc.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adc);
				JTextField add = new JTextField();
				add.setBounds(250,110,200,30);
				
				add.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(add);
				JTextField ade = new JTextField();
				ade.setBounds(250,140,200,30);
				
				ade.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ade);
				JTextField adf = new JTextField();
				adf.setBounds(250,170,200,30);
				
				adf.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adf);
				JTextField adg = new JTextField();
				adg.setBounds(250,200,200,30);
				
				adg.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adg);
				JTextField adh = new JTextField();
				adh.setBounds(250,230,200,30);
				
				adh.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adh);
				JTextField adi = new JTextField();
				adi.setBounds(250,260,200,30);
				
				adi.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(adi);
				JLabel msg1 = new JLabel("*Register the Company first");
				msg1.setBounds(200,310,500,20);
				msg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg1.setForeground(Color.red);
				msg1.setVisible(false);
				bg1.add(msg1);
				JLabel msg2 = new JLabel("*Duplicate Batch no.");
				msg2.setBounds(200,310,500,20);
				msg2.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg2.setForeground(Color.red);
				msg2.setVisible(false);
				bg1.add(msg2);
				JLabel msg3 = new JLabel("*Invalid Batch no.");
				msg3.setBounds(200,310,500,20);
				msg3.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg3.setForeground(Color.red);
				msg3.setVisible(false);
				bg1.add(msg3);
				JButton save = new JButton("Save Changes");
				save.setFont(new Font("",Font.BOLD,13));
				save.setBounds(220, 350, 125, 35);
				bg1.add(save);
				save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						msg1.setVisible(false);
						msg2.setVisible(false);
						msg3.setVisible(false);
						query = "select * from company where Name = '"+add.getText().toString().trim()+"';";
						try {
							result = statement.executeQuery(query);
							if(result.next())
							{
								String a;
								a=adb.getText().toString().trim();
								if(a.length()==0)
								msg3.setVisible(true);
								else
								{query = "select * from medicine where Batch_no="+adb.getText().toString().trim()+";";
								result = statement.executeQuery(query);
								if(!result.next())
								{
									String aa,ac,ae,af,ag,ah,ai;
									aa=ada.getText().toString().trim();
									if(aa.length()==0)
									aa=" ";
									ac=adc.getText().toString().trim();
									if(ac.length()==0)
									ac=" ";
									ae=ade.getText().toString().trim();
									if(ae.length()==0)
									ae="0";
									af=adf.getText().toString().trim();
									if(af.length()==0)
									af="0000-00-00";
									ag=adg.getText().toString().trim();
									if(ag.length()==0)
									ag="0000-00-00";
									ah=adh.getText().toString().trim();
									if(ah.length()==0)
									ah="0";
									ai=adi.getText().toString().trim();
									if(ai.length()==0)
									ai="0";
									query = "insert into medicine values('"+aa+"',"+adb.getText().toString().trim()+",'"+ac+"','"+add.getText().toString().trim()+"',"+ae+",'"+af+"','"+ag+"',"+ai+","+ah+");";
									statement.execute(query);
									amed.hide();
									f.hide();
									try {
										new Stock().show_stock("0");
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else
								{
									msg2.setVisible(true);
								}
							}}
							else
							{
								msg1.setVisible(true);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.setFont(new Font("",Font.BOLD,13));
				cancel.setBounds(360, 350, 105, 35);
				bg1.add(cancel);
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						amed.hide();
					}
				});
				amed.setVisible(true);
				amed.setLayout(null);
				amed.setResizable(false);
			}
		});
		updt_med.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame edmed = new JFrame("Update Medicine Data");
				edmed.setBounds(600,250,500,435);
				edmed.setIconImage(madc.getImage());
				JLabel bg1 = new JLabel("",white_bg1,JLabel.CENTER);
				bg1.setBounds(0, 0, 500, 435);
				edmed.add(bg1);
				JLabel nn1 = new JLabel("New name: ");
				nn1.setBounds(50,20,300,30);
				nn1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nn1);
				JLabel nbn1 = new JLabel("Batch no.: ");
				nbn1.setBounds(50,50,300,30);
				nbn1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nbn1);
				JLabel nt1 = new JLabel("New type: ");
				nt1.setBounds(50,80,300,30);
				nt1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nt1);
				JLabel nc1 = new JLabel("Company name: ");
				nc1.setBounds(50,110,300,30);
				nc1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nc1);
				JLabel ncst1 = new JLabel("New cost: ");
				ncst1.setBounds(50,140,300,30);
				ncst1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ncst1);
				JLabel ndom1 = new JLabel("New D.O.M: ");
				ndom1.setBounds(50,170,300,30);
				ndom1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndom1);
				JLabel ndoe1 = new JLabel("New D.O.E: ");
				ndoe1.setBounds(50,200,300,30);
				ndoe1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ndoe1);
				JLabel nm1 = new JLabel("New MRP: ");
				nm1.setBounds(50,230,300,30);
				nm1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nm1);
				JLabel nq1 = new JLabel("New quantity: ");
				nq1.setBounds(50,260,300,30);
				nq1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nq1);
				JTextField eda = new JTextField();
				
				eda.setBounds(250,20,200,30);
				eda.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(eda);
				JTextField edb = new JTextField();
				edb.setBounds(250,50,200,30);
				
				edb.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edb);
				JTextField edc = new JTextField();
				edc.setBounds(250,80,200,30);
			
				edc.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edc);
				JTextField edd = new JTextField();
				edd.setBounds(250,110,200,30);
				
				edd.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edd);
				JTextField ede = new JTextField();
				ede.setBounds(250,140,200,30);
				
				ede.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(ede);
				JTextField edf = new JTextField();
				edf.setBounds(250,170,200,30);
				
				edf.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edf);
				JTextField edg = new JTextField();
				edg.setBounds(250,200,200,30);
				
				edg.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edg);
				JTextField edh = new JTextField();
				edh.setBounds(250,230,200,30);
				
				edh.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edh);
				JTextField edi = new JTextField();
				edi.setBounds(250,260,200,30);
			
				edi.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(edi);
				JLabel msg1 = new JLabel("*Register the Company first");
				msg1.setBounds(200,310,500,20);
				msg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg1.setForeground(Color.red);
				msg1.setVisible(false);
				bg1.add(msg1);
				JLabel msg2 = new JLabel("*Invalid Batch no.");
				msg2.setBounds(200,310,500,20);
				msg2.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg2.setForeground(Color.red);
				msg2.setVisible(false);
				bg1.add(msg2);
				JButton save = new JButton("Save Changes");
				save.setFont(new Font("",Font.BOLD,13));
				save.setBounds(220, 350, 125, 35);
				bg1.add(save);
				save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						msg1.setVisible(false);
						msg2.setVisible(false);
						query = "select * from company where Name = '"+edd.getText().toString().trim()+"';";
						try {
							result = statement.executeQuery(query);
							if(result.next())
							{
								String a;
								a=edb.getText().toString().trim();
								if(a.length()==0)
								msg2.setVisible(true);
								else
								{query = "select * from medicine where Batch_no = "+edb.getText().toString().trim()+";";
								result = statement.executeQuery(query);
								if(result.next()&&!a.equals(" "))
								{
									a=eda.getText().toString().trim();
									if(a.length()==0)
									a=" ";
									query = "update medicine set Name = '"+a+"' where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edc.getText().toString().trim();
									if(a.length()==0)
									a=" ";
									query = "update medicine set Type = '"+a+"' where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=ede.getText().toString().trim();
									if(a.length()==0)
									a="0";
									query = "update medicine set Cost = "+a+" where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edf.getText().toString().trim();
									if(a.length()==0)
									a="0000-00-00";
									query = "update medicine set D_O_M = '"+a+"' where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edg.getText().toString().trim();
									if(a.length()==0)
									a="0000-00-00";
									query = "update medicine set D_O_E = '"+a+"' where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edh.getText().toString().trim();
									if(a.length()==0)
									a="0";
									query = "update medicine set MRP = "+a+" where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edi.getText().toString().trim();
									if(a.length()==0)
									a="0";
									query = "update medicine set Quantity = "+a+" where Batch_no = "+edb.getText().toString().trim()+" ;";
									statement.execute(query);
									edmed.hide();
									f.hide();
									try {
										new Stock().show_stock("0");
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else
								{
									msg2.setVisible(true);
								}}
							}
							else
							{
								msg1.setVisible(true);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.setFont(new Font("",Font.BOLD,13));
				cancel.setBounds(360, 350, 105, 35);
				bg1.add(cancel);
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						edmed.hide();
					}
				});
				edmed.setVisible(true);
				edmed.setLayout(null);
				edmed.setResizable(false);
			}
		});
		buy_med.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame bymed = new JFrame("Buy From Company");
				bymed.setBounds(600,250,500,435);
				bymed.setIconImage(madc.getImage());
				JLabel bg2 = new JLabel("",white_bg1,JLabel.CENTER);
				bg2.setBounds(0, 0, 500, 435);
				JLabel nn11 = new JLabel("Medicine name: ");
				nn11.setBounds(50,20,300,30);
				nn11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nn11);
				JLabel nbn11 = new JLabel("Batch no.: ");
				nbn11.setBounds(50,50,300,30);
				nbn11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nbn11);
				JLabel nt11 = new JLabel("Type: ");
				nt11.setBounds(50,80,300,30);
				nt11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nt11);
				JLabel nc11 = new JLabel("Company name: ");
				nc11.setBounds(50,110,300,30);
				nc11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nc11);
				JLabel ncst11 = new JLabel("Cost: ");
				ncst11.setBounds(50,140,300,30);
				ncst11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(ncst11);
				JLabel ndom11 = new JLabel("D.O.M: ");
				ndom11.setBounds(50,170,300,30);
				ndom11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(ndom11);
				JLabel ndoe11 = new JLabel("D.O.E: ");
				ndoe11.setBounds(50,200,300,30);
				ndoe11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(ndoe11);
				JLabel nm11 = new JLabel("MRP: ");
				nm11.setBounds(50,230,300,30);
				nm11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nm11);
				JLabel nq11 = new JLabel("Quantity: ");
				nq11.setBounds(50,260,300,30);
				nq11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(nq11);
				JTextField eda1 = new JTextField();
				
				eda1.setBounds(250,20,200,30);
				eda1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(eda1);
				JTextField edb1 = new JTextField();
				edb1.setBounds(250,50,200,30);
				
				edb1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edb1);
				JTextField edc1 = new JTextField();
				edc1.setBounds(250,80,200,30);
			
				edc1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edc1);
				JTextField edd1 = new JTextField();
				edd1.setBounds(250,110,200,30);
				
				edd1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edd1);
				JTextField ede1 = new JTextField();
				ede1.setBounds(250,140,200,30);
				
				ede1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(ede1);
				JTextField edf1 = new JTextField();
				edf1.setBounds(250,170,200,30);
				
				edf1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edf1);
				JTextField edg1 = new JTextField();
				edg1.setBounds(250,200,200,30);
				
				edg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edg1);
				JTextField edh1 = new JTextField();
				edh1.setBounds(250,230,200,30);
				
				edh1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edh1);
				JTextField edi1 = new JTextField();
				edi1.setBounds(250,260,200,30);
			
				edi1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg2.add(edi1);
				JLabel msg11 = new JLabel("*Register the Company first");
				msg11.setBounds(200,310,500,20);
				msg11.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg11.setForeground(Color.red);
				msg11.setVisible(false);
				bg2.add(msg11);
				JLabel msg31 = new JLabel("*Invalid Batch no.");
				msg31.setBounds(200,310,500,20);
				msg31.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg31.setForeground(Color.red);
				msg31.setVisible(false);
				bg2.add(msg31);
				JButton save1 = new JButton("Buy");
				save1.setFont(new Font("",Font.BOLD,13));
				save1.setBounds(220, 350, 105, 35);
				bg2.add(save1);
				save1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						msg11.setVisible(false);
						msg31.setVisible(false);
						query = "select * from company where Name = '"+edd1.getText().toString().trim()+"';";
						try {
							result = statement.executeQuery(query);
							if(result.next())
							{
								String a;
								a=edb1.getText().toString().trim();
								if(a.length()==0)
								msg31.setVisible(true);
								else
								{query = "select * from medicine where Batch_no="+edb1.getText().toString().trim()+";";
								result = statement.executeQuery(query);
								if(!result.next())
								{
									Stock c = new Stock();
									String aa,ac,ae,af,ag,ah,ai;
									aa=eda1.getText().toString().trim();
									if(aa.length()==0)
									aa=" ";
									ac=edc1.getText().toString().trim();
									if(ac.length()==0)
									ac=" ";
									ae=ede1.getText().toString().trim();
									if(ae.length()==0)
									ae="0";
									af=edf1.getText().toString().trim();
									if(af.length()==0)
									af="0000-00-00";
									ag=edg1.getText().toString().trim();
									if(ag.length()==0)
									ag="0000-00-00";
									ah=edh1.getText().toString().trim();
									if(ah.length()==0)
									ah="0";
									ai=edi1.getText().toString().trim();
									if(ai.length()==0)
									ai="0";
									long amount = Integer.parseInt(ae)*Integer.parseInt(ai);
									String cdt="00000000",ct="000000";
									query = "select curdate();";
									result = statement.executeQuery(query);
									if(result.next())
									cdt = c.extract_Date(result.getString("curdate()"));
									query = "select curtime();";
									result = statement.executeQuery(query);
									if(result.next())
									ct = c.extract_Time(result.getString("curtime()"));
									query = "insert into medicine values('"+aa+"',"+edb1.getText().toString().trim()+",'"+ac+"','"+edd1.getText().toString().trim()+"',"+ae+",'"+af+"','"+ag+"',"+ai+","+ah+");";
									statement.execute(query);
									query = "insert into purchase_log values("+edb1.getText().toString().trim()+",curdate(),'"+cdt+ct+strg+"',"+amount+","+ai+","+strg+",'"+edd1.getText().toString().trim()+"');";
									statement.execute(query);
									bymed.hide();
									f.hide();
									try {
										new Stock().show_stock("0");
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else
								{
									Stock c = new Stock();
									a=ede1.getText().toString().trim();
									if(a.length()==0)
									a="0";
									query = "update medicine set Cost = "+a+" where Batch_no = "+edb1.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edf1.getText().toString().trim();
									if(a.length()==0)
									a="0000-00-00";
									query = "update medicine set D_O_M = '"+a+"' where Batch_no = "+edb1.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edg1.getText().toString().trim();
									if(a.length()==0)
									a="0000-00-00";
									query = "update medicine set D_O_E = '"+a+"' where Batch_no = "+edb1.getText().toString().trim()+" ;";
									statement.execute(query);
									a=edi1.getText().toString().trim();
									if(a.length()==0)
									a="0";
									query = "update medicine set Quantity = "+a+" where Batch_no = "+edb1.getText().toString().trim()+" ;";
									statement.execute(query);
									long amount = Integer.parseInt(ede1.getText().toString().trim())*Integer.parseInt(edi1.getText().toString().trim());
									String cdt="00000000",ct="000000";
									query = "select curdate();";
									result = statement.executeQuery(query);
									if(result.next())
									cdt = c.extract_Date(result.getString("curdate()"));
									query = "select curtime();";
									result = statement.executeQuery(query);
									if(result.next())
									ct = c.extract_Time(result.getString("curtime()"));
									query = "insert into purchase_log values("+edb1.getText().toString().trim()+",curdate(),'"+cdt+ct+strg+"',"+amount+","+edi1.getText().toString().trim()+","+strg+",'"+edd1.getText().toString().trim()+"');";
									statement.execute(query);
									bymed.hide();
									f.hide();
									try {
										new Stock().show_stock("0");
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}}
							else
							{
								msg11.setVisible(true);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				JButton cancel1 = new JButton("Cancel");
				cancel1.setFont(new Font("",Font.BOLD,13));
				cancel1.setBounds(360, 350, 105, 35);
				bg2.add(cancel1);
				cancel1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						bymed.hide();
					}
				});
				bymed.add(bg2);
				bymed.setVisible(true);
				bymed.setLayout(null);
				bymed.setResizable(true);
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
