
package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
public class Company {

ImageIcon logo = new ImageIcon(getClass().getResource("logo.png"));
ImageIcon bkgrd = new ImageIcon(getClass().getResource("new_company_bg.png"));
ImageIcon medc = new ImageIcon(getClass().getResource("medicines.png"));
ImageIcon lgt1 = new ImageIcon(getClass().getResource("logout.png"));
ImageIcon cmplogo = new ImageIcon(getClass().getResource("cmp_logo.png"));
ImageIcon cstlogo = new ImageIcon(getClass().getResource("cst_logo.png"));
ImageIcon trnslogo = new ImageIcon(getClass().getResource("trns_logo.jpg"));
ImageIcon homelogo = new ImageIcon(getClass().getResource("home_logo.png"));
ImageIcon editlogo = new ImageIcon(getClass().getResource("edit_logo.jpg"));
ImageIcon white_bg = new ImageIcon(getClass().getResource("white_bg.jpg"));
ImageIcon app_logo = new ImageIcon(getClass().getResource("app_logo.png"));

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


public void Company_display(String str) throws ClassNotFoundException, SQLException
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
	Company c = new Company();
	Border blackline = BorderFactory.createLineBorder(new Color(53,0,102));
	JFrame f = new JFrame("Company");
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
	p3.setBounds(500,240,1040,620);
	p3.setBackground(new Color(0,0,0,0));
	p3.setLayout(null);
	p3.setVisible(true);
	bg.add(p3);
	JLabel l0 = new JLabel();
	l0.setText("Welcome,");
	l0.setFont(new Font("Times New Roman",Font.BOLD,25));
	l0.setForeground(new Color(0,0,0));
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
	l1.setForeground(new Color(0,0,0));
	l1.setBounds(75,100,1000,40);
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l1.setFont(font.deriveFont(Font.BOLD, 25f));
    }
    catch(Exception e){}
	p0.add(l1);
	JLabel l2 = new JLabel();
	l2.setText(" Company ");
	l2.setFont(new Font("Times New Roman",Font.BOLD,25));
	l2.setForeground(new Color(53,0,102));
	l2.setBounds(1440,75,1000,40);
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l2.setFont(font.deriveFont(Font.ITALIC, 25f));
    }
    catch(Exception e){}
	
	p0.add(l2);
	JLabel l3 = new JLabel("Associated Companies");
	l3.setFont(new Font("Times New Roman",Font.BOLD,25));
	l3.setForeground(new Color(0,0,0));
	l3.setBounds(490,165,1000,40);
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
	medl.setForeground(new Color(0,0,0));
	p1.add(medl);
	JButton hom = new JButton(homelogo);
	hom.setBounds(245,10,160,160);
	p1.add(hom);
    JLabel aplog = new JLabel("",app_logo,JLabel.CENTER);
	aplog.setBounds(750,50,200,100);
	p0.add(aplog);
	JLabel cmpl = new JLabel("Home");
	cmpl.setBounds(283,170,100,25);
	cmpl.setFont(new Font("",Font.PLAIN,20));
	cmpl.setForeground(new Color(0,0,0));
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
	cstl1.setForeground(new Color(0,0,0));
	p1.add(cstl1);
	JLabel cstl2 = new JLabel("History");
	cstl2.setBounds(110,390,100,25);
	cstl2.setFont(new Font("",Font.PLAIN,20));
	cstl2.setForeground(new Color(0,0,0));
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
	trnsl.setForeground(new Color(0,0,0));
	p1.add(trnsl);
	JButton new_c= new JButton("Add Company");
	new_c.setBounds(590, 210, 180, 35);
	new_c.setFont(new Font("",Font.BOLD,16));
	bg.add(new_c);

	JButton del_c= new JButton("Remove Company");
	del_c.setBounds(710, 210, 180, 35);
	del_c.setFont(new Font("",Font.BOLD,16));
	//p1.add(del_c);
	
	JButton  add_contact= new JButton("Add contact-info"); 
	add_contact.setBounds(810, 210, 180, 35);
	add_contact.setFont(new Font("",Font.BOLD,16));
	bg.add(add_contact);
	
	JButton del_contact= new JButton("Delete contact-info"); 
	del_contact.setBounds(1250, 210, 180, 35);
	del_contact.setFont(new Font("",Font.BOLD,16));
	bg.add(del_contact);
	
	JButton  update_c= new JButton("Update Company"); 
	update_c.setBounds(1030, 210, 180, 35);
	update_c.setFont(new Font("",Font.BOLD,16));
	bg.add(update_c);
	
	JLabel l4 = new JLabel("Profile");
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l4.setFont(font.deriveFont(Font.BOLD, 24f));
    }
    catch(Exception e){}
	l4.setBounds(15,5,150,40);
	l4.setForeground(new Color(0,0,0));
	p2.add(l4);
	JLabel l5 = new JLabel("Name: "+str);
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l5.setFont(font.deriveFont(Font.BOLD, 20f));
    }
    catch(Exception e){}
	l5.setBounds(15,60,345,40);
	l5.setForeground(new Color(0,0,0));
	p2.add(l5);
	JLabel l6 = new JLabel("ID: "+result.getString("ID"));
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l6.setFont(font.deriveFont(Font.BOLD, 20f));
    }
    catch(Exception e){}
	l6.setBounds(15,90,345,40);
	l6.setForeground(new Color(0,0,0));
	p2.add(l6);
	JLabel l7 = new JLabel("Date of Joining: "+result.getString("Date_of_Joining"));
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l7.setFont(font.deriveFont(Font.BOLD, 20f));
    }
    catch(Exception e){}
	l7.setBounds(15,120,345,40);
	l7.setForeground(new Color(0,0,0));
	p2.add(l7);
	JLabel l8 = new JLabel("Salary: "+result.getString("Salary"));
	try{
        Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
        l8.setFont(font.deriveFont(Font.BOLD, 20f));
    }
    catch(Exception e){}
	l8.setBounds(15,150,345,40);
	l8.setForeground(new Color(0,0,0));
	p2.add(l8);
	
	
	
	//search button for company table
	JLabel dcl12 = new JLabel("Search by name:");
	dcl12.setBounds(530, 260, 180, 35);
	dcl12.setFont(new Font("Times New Roman",Font.BOLD,17));
	bg.add(dcl12);
	JTextField dctf1 = new JTextField();
	dctf1.setBounds(670, 260, 180, 35);
	dctf1.setFont(new Font("Times New Roman",Font.PLAIN,16));
	bg.add(dctf1);
	JButton search_b= new JButton("Search"); 
	search_b.setBounds(880, 260, 120, 35);
	search_b.setFont(new Font("",Font.BOLD,16));
	bg.add(search_b);
	
	
	Object[] columns = {"Company name","State","City","Pin"};
	JTable lgin = new JTable();
	lgin.setBounds(500,240,1040,320);
	lgin.setRowHeight(30);
	p3.add(lgin);
	DefaultTableModel lgint = new DefaultTableModel();
	lgint.setColumnIdentifiers(columns);
	lgin.setModel(lgint);
	JScrollPane sp = new JScrollPane(lgin);
	sp.setBounds(0,70,1040,220);
	p3.add(sp);
	Object[] row = new Object[4];
	
		query = "select * from company order by Name;";
		result = statement.executeQuery(query);
		while(result.next())
		{
			row[0]=result.getString("Name");
			row[1]=result.getString("State");
			row[2]=result.getString("City");
			row[3]=result.getString("Pin");
			lgint.addRow(row);
		}
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	search_b.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
//			if((dctf1.getText().toString().trim().length()==0))
//			checkname.setVisible(true);
//			else if ((dctf1.getText().toString().trim().length()!=10)){
//			 checknumber.setVisible(true);
//			}
				try {
					String a;
					a=dctf1.getText().toString().trim();
					if(a.length()==0) {	
						lgint.getDataVector().removeAllElements();
						lgint.fireTableDataChanged();
							query = "select * from company ;";
							result = statement.executeQuery(query);
							while(result.next())
							{
								row[0]=result.getString("Name");
								row[1]=result.getString("State");
								row[2]=result.getString("City");
								row[3]=result.getString("Pin");
								lgint.addRow(row);
							}
					}
					else {
						lgint.getDataVector().removeAllElements();
						lgint.fireTableDataChanged();
							query = "select * from company where Name like'%"+a+"%';";
							result = statement.executeQuery(query);
							while(result.next())
							{
								row[0]=result.getString("Name");
								row[1]=result.getString("State");
								row[2]=result.getString("City");
								row[3]=result.getString("Pin");
								lgint.addRow(row);
							}
					}
					f.setLayout(null);
					f.setVisible(true);
					f.setResizable(false);
					f.setIconImage(logo.getImage());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						      
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		);

	
	
	JLabel se  = new JLabel("Search :");
	se.setBounds(550, 550, 180, 35);
	se.setFont(new Font("Times New Roman",Font.BOLD,18));
	bg.add(se);
	JTextField se1 = new JTextField();
	se1.setBounds(650, 550, 180, 35);
	se1.setFont(new Font("Times New Roman",Font.PLAIN,16));
	bg.add(se1);
	JButton search_b1= new JButton("Search"); 
	search_b1.setBounds(850, 550, 120, 35);
	search_b1.setFont(new Font("",Font.BOLD,16));
	bg.add(search_b1);
	
	Object[] columns1 = {"Company ","Contact"};
	JTable Contact = new JTable();
	Contact.setBounds(500,240,440,220);
	Contact.setRowHeight(25);
	p3.add(Contact);
	DefaultTableModel contint = new DefaultTableModel();
	contint.setColumnIdentifiers(columns1);
	Contact.setModel(contint);
	JScrollPane sp1 = new JScrollPane(Contact);
	sp1.setBounds(0,360,440,220);
	p3.add(sp1);
	Object[] rowC = new Object[2];
	
		query = "select * from company_contact order by Company;";
		result = statement.executeQuery(query);
		while(result.next())
		{
			rowC[0]=result.getString("Company");
			rowC[1]=result.getString("Contact_no");
			contint.addRow(rowC);
		}
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	search_b1.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{

				try {
					String a;
					a=se1.getText().toString().trim();
					if(a.length()==0) {	
						contint.getDataVector().removeAllElements();
						contint.fireTableDataChanged();
							query = "select * from company_contact ;";
							result = statement.executeQuery(query);
							while(result.next())
							{
								rowC[0]=result.getString("Company");
								rowC[1]=result.getString("Contact_no");
								contint.addRow(rowC);
							}
					}
					else {
						contint.getDataVector().removeAllElements();
						contint.fireTableDataChanged();
							query = "select * from company_contact where Company like'%"+a+"%';";
							result = statement.executeQuery(query);
							while(result.next())
							{
								rowC[0]=result.getString("Company");
								rowC[1]=result.getString("Contact_no");
								contint.addRow(rowC);
							}
					}
					f.setLayout(null);
					f.setVisible(true);
					f.setResizable(false);
					f.setIconImage(logo.getImage());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						      
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		);
	
	del_contact.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame dcf = new JFrame("Delete contact info");
			dcf.setBounds(600,250,500,350);
			dcf.setIconImage(editlogo.getImage());
			JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
			bg1.setBounds(0, 0, 500, 350);
			dcf.add(bg1);
			JLabel checkname = new JLabel("*Name cannot be empty");
			checkname.setForeground(Color.red);
			checkname.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checkname.setBounds(120, 220, 300, 20);
			checkname.setVisible(false);
			bg1.add(checkname);
			JLabel checknumber = new JLabel("*Number should be 10 digits");
			checknumber.setForeground(Color.red);
			checknumber.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checknumber.setBounds(120, 220, 300, 20);
			checknumber.setVisible(false);
			bg1.add(checknumber);
			JLabel dcl = new JLabel("Company Name:");
			dcl.setBounds(50,30,300,30);
			dcl.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(dcl);
			JLabel dcl1 = new JLabel("Contact Number:");
			dcl1.setBounds(50,60,300,30);
			dcl1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(dcl1);
			JTextField dctf = new JTextField();
			dctf.setBounds(250,30,200,30);
			dctf.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(dctf);
			JTextField dctf1 = new JTextField();
			dctf1.setBounds(250,60,200,30);
			dctf1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(dctf1);
			
			JButton dsave = new JButton("Delete entry");
			dsave.setFont(new Font("",Font.BOLD,13));
			dsave.setBounds(220, 250, 125, 35);
			bg1.add(dsave);
			JButton cancel = new JButton("Cancel");
			cancel.setFont(new Font("",Font.BOLD,13));
			cancel.setBounds(360, 250, 105, 35);
			bg1.add(cancel);
			cancel.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dcf.hide();
				}
			});
			dsave.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((dctf.getText().toString().trim().length()==0))
					checkname.setVisible(true);
					else if ((dctf1.getText().toString().trim().length()!=10)){
					 checknumber.setVisible(true);
					}
					else {
						try {
							String a,a1;
							a=dctf.getText().toString().trim();
							a1=dctf1.getText().toString().trim();
							  String query = "delete from company_contact where Company=? and Contact_no=?";
								      PreparedStatement preparedStmt = connection.prepareStatement(query);
								      preparedStmt.setString (1, a);
								      preparedStmt.setString (2, a1);
	                       int checkifsure= JOptionPane.showConfirmDialog(null,"Are You Sure about Deleting the information");
	                          try{
	                              if(checkifsure==0){
	                           preparedStmt.executeUpdate();
	                            JOptionPane.showMessageDialog(null,"Company Contact Deleted Successfully","Success Operation",1);
	                          
	                              }
	                          }catch(Exception e1){
	                              JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",2);
	                          }  
							f.hide();
							Company cps = new Company();
							try {
								cps.Company_display(strg);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dcf.hide();}
					}
				});

			dcf.setResizable(false);
			dcf.setLayout(null);
			dcf.setVisible(true);
		}
	});
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
	add_contact.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame acf = new JFrame("Add contact info");
			acf.setBounds(600,250,500,350);
			acf.setIconImage(editlogo.getImage());
			JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
			bg1.setBounds(0, 0, 500, 350);
			acf.add(bg1);
			JLabel checkname = new JLabel("*Name cannot be empty");
			checkname.setForeground(Color.red);
			checkname.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checkname.setBounds(120, 220, 300, 20);
			checkname.setVisible(false);
			bg1.add(checkname);
			JLabel checknumber = new JLabel("*Number should be 10 digits");
			checknumber.setForeground(Color.red);
			checknumber.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checknumber.setBounds(120, 220, 300, 20);
			checknumber.setVisible(false);
			bg1.add(checknumber);
			JLabel acl = new JLabel("Company Name:");
			acl.setBounds(50,30,300,30);
			acl.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(acl);
			JLabel acl1 = new JLabel("Contact Number:");
			acl1.setBounds(50,60,300,30);
			acl1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(acl1);
			JTextField actf = new JTextField();
			actf.setBounds(250,30,200,30);
			actf.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(actf);
			JTextField actf1 = new JTextField();
			actf1.setBounds(250,60,200,30);
			actf1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(actf1);
			
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
					acf.hide();
				}
			});
			save.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((actf.getText().toString().trim().length()==0))
					checkname.setVisible(true);
					else if ((actf1.getText().toString().trim().length()!=10)){
					 checknumber.setVisible(true);
					}
					else {
					try {
						String a,a1;
						a=actf.getText().toString().trim();
						a1=actf1.getText().toString().trim();
						//int number= Integer.parseInt(a1);
						 String query = " insert into company_contact (Company,Contact_no)"
							        + " values (?, ?)";
							      PreparedStatement preparedStmt = connection.prepareStatement(query);
							      preparedStmt.setString (1, a);
							      preparedStmt.setString(2, a1);
							      preparedStmt.execute();
							 JOptionPane.showMessageDialog(null,"Contact info inserted Successfully","Success Operation",1);
						f.hide();
						Company cps = new Company();
						try {
							cps.Company_display(strg);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						 JOptionPane.showMessageDialog(null,"No company entry with this name","Error",2);
					}
					acf.hide();}
				}
			});
			acf.setResizable(false);
			acf.setLayout(null);
			acf.setVisible(true);
		}
	});
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	update_c.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame ucd = new JFrame("update company details");
			ucd.setBounds(600,250,500,350);
			ucd.setIconImage(editlogo.getImage());
			JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
			bg1.setBounds(0, 0, 500, 350);
			ucd.add(bg1);
			JLabel checkname = new JLabel("*Name cannot be empty");
			checkname.setForeground(Color.red);
			checkname.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checkname.setBounds(120, 220, 300, 20);
			checkname.setVisible(false);
			bg1.add(checkname);
			JLabel ucdl = new JLabel("Company Name:");
			ucdl.setBounds(50,30,300,30);
			ucdl.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdl);
			JLabel ucdl1 = new JLabel("State:");
			ucdl1.setBounds(50,60,100,30);
			ucdl1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdl1);
			JLabel ucdl2 = new JLabel("City :");
			ucdl2.setBounds(50,90,300,30);
			ucdl2.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdl2);
			JLabel ucdl3 = new JLabel("Pin Code :");
			ucdl3.setBounds(50,120,300,30);
			ucdl3.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdl3);
			JTextField ucdt = new JTextField();
			ucdt.setBounds(250,30,200,30);
			ucdt.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdt);
			JTextField ucdt1 = new JTextField();
			ucdt1.setBounds(250,60,200,30);
			ucdt1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdt1);
			JTextField ucdt2 = new JTextField();
			ucdt2.setBounds(250,90,200,30);
			ucdt2.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdt2);
			JTextField ucdt3 = new JTextField();
			ucdt3.setBounds(250,120,200,30);
			ucdt3.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ucdt3);
			
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
					ucd.hide();
				}
			});
			
			save.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((ucdt.getText().toString().trim().length()==0))
					checkname.setVisible(true);
					else {
					try {
						String a,a1;
						a=ucdt.getText().toString().trim();
						a1=ucdt1.getText().toString().trim();
						if(!a1.equals("")) {
						query = "update Company set State = '"+a1+"' where Name = '"+a+"';";
						statement.execute(query);
						}
						a1=ucdt2.getText().toString().trim();
						if(!a1.equals("")) {
						query = "update Company set City = '"+a1+"' where Name = '"+a+"';";
						statement.execute(query);
						}
						a1=ucdt3.getText().toString().trim();
						if(!a1.equals("")) {
						int pin =Integer.parseInt(a1);
						query = "update Company set Pin = '"+a1+"' where Name = '"+a+"';";
						statement.execute(query);
						}
						f.hide();
						Company cy = new Company();
						try {
							cy.Company_display(strg);
							JOptionPane.showMessageDialog(null,"Company Information Updated Successfully","Success Operation",1);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ucd.hide();}
				}
			});
            
			ucd.setResizable(false);
			ucd.setLayout(null);
			ucd.setVisible(true);
		}
	});
	
	
	del_c.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame rc = new JFrame("Remove company");
			rc.setBounds(600,250,500,350);
			rc.setIconImage(editlogo.getImage());
			JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
			bg1.setBounds(0, 0, 500, 350);
			rc.add(bg1);
			JLabel checkname = new JLabel("*Name cannot be empty");
			checkname.setForeground(Color.red);
			checkname.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checkname.setBounds(120, 220, 300, 20);
			checkname.setVisible(false);
			bg1.add(checkname);
			JLabel rcn = new JLabel("Company Name:");
			rcn.setBounds(50,30,300,30);
			rcn.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(rcn);
			JTextField rctf = new JTextField();
			rctf.setBounds(250,30,200,30);
			rctf.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(rctf);
			
			JButton save = new JButton("Delete entry");
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
					rc.hide();
				}
			});
			save.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((rctf.getText().toString().trim().length()==0))
					checkname.setVisible(true);
					else {
					try {
						String a;
						a=rctf.getText().toString().trim();
						  String query = "delete from company where name=?";
							      PreparedStatement preparedStmt = connection.prepareStatement(query);
							      preparedStmt.setString (1, a);
                       int checkifsure= JOptionPane.showConfirmDialog(null,"Are You Sure about Deleting the information");
                          try{
                              if(checkifsure==0){
                           preparedStmt.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Company Information Deleted Successfully","Success Operation",1);
                          
                              }
                          }catch(Exception e1){
                              JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",2);
                          }  
						f.hide();
						Company cps = new Company();
						try {
							cps.Company_display(strg);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					rc.hide();}
				}
			});
			rc.setResizable(false);
			rc.setLayout(null);
			rc.setVisible(true);
		}
	});
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	new_c.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame nc = new JFrame("Add company details");
			nc.setBounds(600,250,500,350);
			nc.setIconImage(editlogo.getImage());
			JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
			bg1.setBounds(0, 0, 500, 350);
			nc.add(bg1);
			JLabel checkname = new JLabel("*Name cannot be empty");
			checkname.setForeground(Color.red);
			checkname.setFont(new Font("Times New Roman",Font.PLAIN,16));
			checkname.setBounds(120, 220, 300, 20);
			checkname.setVisible(false);
			bg1.add(checkname);
			JLabel ncn = new JLabel("Company Name:");
			ncn.setBounds(50,30,300,30);
			ncn.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ncn);
			JLabel ncs = new JLabel("State:");
			ncs.setBounds(50,60,100,30);
			ncs.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ncs);
			JLabel ncc = new JLabel("City :");
			ncc.setBounds(50,90,300,30);
			ncc.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(ncc);
			JLabel npc = new JLabel("Pin Code :");
			npc.setBounds(50,120,300,30);
			npc.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(npc);
			JTextField nctf = new JTextField();
			nctf.setBounds(250,30,200,30);
			nctf.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(nctf);
			JTextField nctf1 = new JTextField();
			nctf1.setBounds(250,60,200,30);
			nctf1.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(nctf1);
			JTextField nctf2 = new JTextField();
			nctf2.setBounds(250,90,200,30);
			nctf2.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(nctf2);
			JTextField nctf3 = new JTextField();
			nctf3.setBounds(250,120,200,30);
			nctf3.setFont(new Font("Times New Roman",Font.PLAIN,16));
			bg1.add(nctf3);
			
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
					nc.hide();
				}
			});
			save.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((nctf.getText().toString().trim().length()==0))
					checkname.setVisible(true);
					else {
					try {
						String a,a1,a2,a3;
						int pincode;
						a=nctf.getText().toString().trim();
						a1=nctf1.getText().toString().trim();
						if(a1.equals(""))
						a1=" ";
						a2=nctf2.getText().toString().trim();
						if(a2.equals(""))
						a2=" ";
						a3=nctf3.getText().toString().trim();
						if(a3.equals(""))
						a3="0";
						pincode=Integer.parseInt(a3);
						  String query = " insert into company (Name, State, City,Pin)"
							        + " values (?, ?, ?, ?)";

							      // create the mysql insert preparedstatement
							      PreparedStatement preparedStmt = connection.prepareStatement(query);
							      preparedStmt.setString (1, a);
							      preparedStmt.setString (2, a1);
							      preparedStmt.setString (3, a2);
							      preparedStmt.setInt    (4, pincode);
							      preparedStmt.execute();

						
						f.hide();
						Company cps = new Company();
						try {
							cps.Company_display(strg);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					nc.hide();}
				}
			});
			nc.setResizable(false);
			nc.setLayout(null);
			nc.setVisible(true);
		}
	});
	f.setLayout(null);
	f.setVisible(true);
	f.setResizable(false);
	f.setIconImage(logo.getImage());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	JButton ret = new JButton("Logout");
	ret.setFont(new Font("",Font.BOLD,16));
	ret.setBounds(190, 665, 100, 50);
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
}

public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Company b = new Company();
	b.Company_display("0");
}


}
