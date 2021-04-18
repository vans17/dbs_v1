package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.*;
public class Cart {

	ImageIcon cart = new ImageIcon(getClass().getResource("cart_logo.png"));
	ImageIcon cart_bg = new ImageIcon(getClass().getResource("cart_bg.jpg"));
	ImageIcon white_bg = new ImageIcon(getClass().getResource("white_bg.jpg"));
	ImageIcon logo = new ImageIcon(getClass().getResource("app_logo.png"));
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String NAME = "root";
	static final String PASSWORD = "rootpassword";
	static final String URL = "jdbc:mysql://localhost:3306/dbs_v1";
	static Connection connection;
	static String query,query1;
	static Statement statement,statement1;
	static ResultSet result,result1,result2;
	static String strg;
	static String pswrd;
	
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
	
	public void add_to_Cart(String cmpname,String btchno,String str) throws ClassNotFoundException, SQLException
	{
		strg=str;
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		statement1 = connection.createStatement();
		query = "create table if not exists cart"+strg+" (company varchar(45),batch_no int,name varchar(45) ,quantity int,mrp int, primary key(batch_no));";
		statement.execute(query);
		query = "select * from medicine where Batch_no="+btchno+";";
		result = statement.executeQuery(query);
		String name="",mrp="",bno="",company="";
		int qty=1;
		if(result.next())
		{
			name = result.getString("Name");
			mrp = result.getString("MRP");
			bno = btchno;
			company = result.getString("Company");
			qty = Integer.parseInt(result.getString("Quantity"));
		}
		query = "select * from cart"+strg+" where batch_no = "+bno+";";
		result = statement.executeQuery(query);
		if(result.next())
		{
			int abcd=1;
			query = "select quantity from cart"+strg+" where batch_no = "+bno+";";
			result = statement.executeQuery(query);
			if(result.next())
			abcd = result.getInt("quantity");
			if(qty-abcd>0)
			++abcd;
			query = "update cart"+strg+" set quantity = "+abcd+" where batch_no = "+bno+";";
		}
		else
		{
			query = "insert into cart"+strg+" values('"+company+"',"+bno+",'"+name+"',1,"+mrp+");";
		}
		statement.execute(query);
	}
	
	public void show_cart(String str) throws ClassNotFoundException, SQLException
	{
		Border blackline = BorderFactory.createLineBorder(new Color(0,0,0));
		Object[] columns = {"Company","Name","Batch no.","Quantity","MRP"};
		JFrame f = new JFrame("Cart");
		f.setBounds(550,150,600,450);
		f.setIconImage(cart.getImage());
		JLabel bg = new JLabel("",cart_bg,JLabel.CENTER);
		bg.setBounds(0,0,600,450);
		f.add(bg);
		JTable medt = new JTable();
		medt.setBounds(30,25,460,300);
		bg.add(medt);
		DefaultTableModel medt1 = new DefaultTableModel();
		medt1.setColumnIdentifiers(columns);
		medt.setModel(medt1);
		medt.setRowHeight(25);
		Object[] row = new Object[5];
		JScrollPane sp = new JScrollPane(medt);
		sp.setBackground(Color.white);
		sp.setBounds(30,25,460,300);
		bg.add(sp);
		JButton co = new JButton("Checkout");
		co.setBounds(330,350,100,40);
		bg.add(co);
		JButton clr = new JButton("Clear cart");
		clr.setBounds(460,350,100,40);
		JButton add = new JButton("+");
		add.setBounds(512,50,50,50);
		bg.add(add);
		JButton rmv = new JButton("-");
		rmv.setBounds(512,115,50,50);
		bg.add(rmv);
		JButton dlt = new JButton("Delete");
		dlt.setBounds(497,180,80,40);
		bg.add(dlt);
		JLabel jl = new JLabel("No items selected");
		jl.setBounds(220,150,300,20);
		jl.setFont(new Font("Times New Roman",Font.PLAIN,20));
		jl.setForeground(Color.red);
		bg.add(jl);
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		statement1 = connection.createStatement();
		query = "select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'cart"+str+"';";
		result = statement.executeQuery(query);
		if(result.next())
		{
			jl.setVisible(false);
			query = "select * from cart"+str+";";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("company");
				row[1]=result.getString("name");
				row[2]=result.getString("batch_no");
				row[3]=result.getString("quantity");
				row[4]=result.getString("mrp");
				medt1.addRow(row);
			}
		}
		else
		{
			sp.setVisible(false);
			medt.setVisible(false);
			add.setVisible(false);
			rmv.setVisible(false);
			dlt.setVisible(false);
			clr.setVisible(false);
			co.setVisible(false);
		}
		//JButton co= new JButton("C");
		//c//o.setBounds(330,350,100,40);
		//bg.add(co);
		
		co.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame cst_dtl = new JFrame("Customer details");
				cst_dtl.setBounds(600,250,500,350);
				JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
				cst_dtl.add(bg1);
				JLabel nl = new JLabel("Contact number: ");
				nl.setBounds(100,30,300,30);
				nl.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(nl);
				JTextField eda = new JTextField();
				eda.setBounds(230,30,200,25);
				eda.setFont(new Font("Times New Roman",Font.PLAIN,16));
				bg1.add(eda);
				JLabel msg1 = new JLabel("*Fields cannot be empty");
				msg1.setBounds(100,60,300,30);
				msg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg1.setForeground(Color.red);
				msg1.setVisible(false);
				bg1.add(msg1);
				JLabel msg2 = new JLabel("*Customer has to be created");
				msg2.setBounds(100,60,300,30);
				msg2.setFont(new Font("Times New Roman",Font.PLAIN,16));
				msg2.setForeground(Color.red);
				msg2.setVisible(false);
				bg1.add(msg2);
				JButton crt = new JButton("Create customer");
				crt.setBounds(140, 200, 200, 30);
				crt.setFont(new Font("Times New Roman",Font.BOLD,16));
				bg1.add(crt);
				JButton prt = new JButton("Print Invoice and Confirm Order");
				prt.setFont(new Font("",Font.BOLD,13));
				prt.setBounds(20, 260, 265, 35);
				bg1.add(prt);
				JButton cancel = new JButton("Cancel");
				cancel.setFont(new Font("",Font.BOLD,13));
				cancel.setBounds(330, 260, 105, 35);
				bg1.add(cancel);
				cancel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						cst_dtl.hide();
					}
				});
				crt.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JFrame cst_dtl = new JFrame("Customer details");
						cst_dtl.setBounds(600,250,500,350);
						JLabel bg1 = new JLabel("",white_bg,JLabel.CENTER);
						cst_dtl.add(bg1);
						JLabel nl = new JLabel("Name: ");
						nl.setBounds(100,60,300,30);
						nl.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(nl);
//						JLabel cidl = new JLabel("Customer ID: ");
//						cidl.setBounds(100,60,300,30);
//						cidl.setFont(new Font("Times New Roman",Font.PLAIN,16));
//						bg1.add(cidl);
						JLabel al = new JLabel("Age: ");
						al.setBounds(100,90,300,30);
						al.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(al);
						JLabel cnol = new JLabel("Contact number: ");
						cnol.setBounds(100,120,300,30);
						cnol.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(cnol);
						JLabel adl = new JLabel("Address: ");
						adl.setBounds(100,150,300,30);
						adl.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(adl);
						JTextField eda = new JTextField();
						eda.setBounds(230,60,200,25);
						eda.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(eda);
//						JTextField edb = new JTextField();
//						edb.setBounds(230,60,200,25);
//						edb.setFont(new Font("Times New Roman",Font.PLAIN,16));
//						bg1.add(edb);
						JTextField edc = new JTextField();
						edc.setBounds(230,90,200,25);
						edc.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(edc);
						JTextField edd = new JTextField();
						edd.setBounds(230,120,200,25);
						edd.setFont(new Font("Times New Roman",Font.PLAIN,16));
						bg1.add(edd);
						JTextArea ta1 = new JTextArea();
						ta1.setBounds(230, 150, 200, 75);
						ta1.setFont(new Font("Times New Roman",Font.PLAIN,16));
						ta1.setBorder(blackline);
						bg1.add(ta1);
						JLabel msg1 = new JLabel("Enter contact properly");
						msg1.setBounds(210, 225, 200, 30);
						msg1.setFont(new Font("Times New Roman",Font.PLAIN,16));
						msg1.setForeground(Color.red);
						msg1.setVisible(false);
						bg1.add(msg1);
						JButton save = new JButton("Save changes");
						save.setFont(new Font("",Font.BOLD,13));
						save.setBounds(100, 260, 245, 35);
						bg1.add(save);
						JButton cancel = new JButton("Cancel");
						cancel.setFont(new Font("",Font.BOLD,13));
						cancel.setBounds(360, 260, 105, 35);
						bg1.add(cancel);
						cancel.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								cst_dtl.hide();
							}
						});
						save.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								String w1,w2 = null,w4 = null,w5 = null,w3 = null;
								w1 = eda.getText().toString().trim();
								//w2 = edb.getText().toString().trim(); 
								w3 = edc.getText().toString().trim();
								w4 = edd.getText().toString().trim();
								w5 = ta1.getText().toString().trim();
								if(w1.length()==0)
								{
									msg1.setVisible(true);
								}
								else {
									query= "INSERT INTO customer (Name, Age, Contact_no, Address) VALUES('"+w1+"',"+w3+","+w4+",'"+w5+"');";
									//query = "insert into customer values('"+w1+"','"+w2+"',"+w3+","+w4+",'"+w5+"');";
									try {
										statement1.execute(query);
									JOptionPane.showMessageDialog(null,"Customer details inserted Successfully","Success Operation",1);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								cst_dtl.hide();
								
							}
						});
						cst_dtl.setVisible(true);
						cst_dtl.setLayout(null);
						cst_dtl.setResizable(false);
						
					}
				});
				prt.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int qty1=1;
						msg1.setVisible(false);
						String w1,w2 = null,w4 = null,w5 = null,w3 = null;
						Integer i3;
						w1 = eda.getText().toString().trim();
//						w2 = edb.getText().toString().trim();
//						w3 = edc.getText().toString().trim();
//						w4 = edd.getText().toString().trim();
//						w5 = ta1.getText().toString().trim();
						int ch=0;
						if(w1.length()!=0 ) {
						query="select * from customer where Contact_no = "+w1+";";
						try {
							result = statement.executeQuery(query);
							if(!result.next()) {
								msg2.setVisible(true);
								ch=1;
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					   } 
						 if(w1.length()==0 || w1.length()!=10)
						{   
							msg1.setVisible(true);
						}	
						else if(ch==0)
						{   
							Rectangle ps = new Rectangle(PageSize.A4);
							ps.setBackgroundColor(new BaseColor(230, 236, 255));
							Document doc = new Document(ps);
						try {
							query="select * from customer where Contact_no = "+w1+";";
							result = statement.executeQuery(query);
							if(result.next()) {
								w2=result.getString("Customer_ID");
								i3=result.getInt("Age");
								w4=result.getString("Name");
							    w5=result.getString("Address");
							    w3=String.valueOf(i3);
							}
						
							Cart a = new Cart();
							String trnsid = "";
							query = "select curdate();";
							result = statement.executeQuery(query);
							if(result.next())
							trnsid+=a.extract_Date(result.getString("curdate()"));
							query = "select curtime();";
							result = statement.executeQuery(query);
							if(result.next())
							trnsid+=a.extract_Time(result.getString("curtime()"));
							trnsid+=w2;
							String file = "src\\Invoices\\"+w4+"_"+w2+"_Invoice.pdf";
							PdfWriter.getInstance(doc, new FileOutputStream(file));
							doc.open();
							PdfPCell cell = new PdfPCell();
							Phrase phrase = new Phrase();
							Paragraph space = new Paragraph(" ");
							Image img = Image.getInstance("src\\application\\app_logo.png");
							img.scaleToFit(150,90);
							img.setAlignment(Image.ALIGN_CENTER);
							doc.add(img);
							BaseFont bs = BaseFont.createFont("src\\Fonts\\Rene Bieder  Milliard Light.otf",BaseFont.WINANSI, true);
							com.itextpdf.text.Font hdr = new com.itextpdf.text.Font(bs,16,com.itextpdf.text.Font.BOLD|com.itextpdf.text.Font.UNDERLINE);
							com.itextpdf.text.Font fo1 = new com.itextpdf.text.Font(bs,10,com.itextpdf.text.Font.NORMAL);
							PdfPTable t3 = new PdfPTable(1);
							phrase = new Phrase("Invoice\n\n",hdr);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							t3.addCell(cell).setBorderWidth(0);
							doc.add(t3);
							String date12="",time12="";
							query = "select curdate();";
							result = statement.executeQuery(query);
							if(result.next())
							date12=result.getString("curdate()").trim();
							query = "select curtime();";
							result = statement.executeQuery(query);
							if(result.next())
							time12=result.getString("curtime()").trim();
							PdfPTable t2 = new PdfPTable(2);
							t2.setWidthPercentage(100);
							t2.setSpacingBefore(10);
							t2.setSpacingAfter(10);
							phrase = new Phrase("Customer ID:  "+w2,fo1);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							t2.addCell(cell).setBorderWidth(0);
							phrase = new Phrase("Date:  "+date12,fo1);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t2.addCell(cell).setBorderWidth(0);
							phrase = new Phrase("Transaction ID:  "+trnsid,fo1);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							t2.addCell(cell).setBorderWidth(0);
							phrase = new Phrase("Time:  "+time12,fo1);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t2.addCell(cell).setBorderWidth(0);
							doc.add(t2);
							com.itextpdf.text.Font fo = new com.itextpdf.text.Font(bs,14,com.itextpdf.text.Font.BOLD);
							com.itextpdf.text.Font fo2 = new com.itextpdf.text.Font(bs,10,com.itextpdf.text.Font.BOLD);	
							phrase.setTabSettings(new TabSettings(100f));
							PdfPTable t0 = new PdfPTable(4);
							t0.setWidthPercentage(100);
							t0.setSpacingAfter(10);
							phrase = new Phrase("Patient:\n\n",fo);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Shop:\n\n",fo);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Name:",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase(w4,fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Invoice no:",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							double se = Math.random()*100000;
							phrase = new Phrase(""+(int)se,fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Age:",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase(w3,fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Gst no:",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("27BUUPD4244A1ZS",fo1);
							cell = new PdfPCell(phrase);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Contact no:",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase(w1,fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Contact no: ",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("9167906278\n25512472\n7314090777",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Address:",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase(w5,fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Address:",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							phrase = new Phrase("Shop-7&8, Plot-12, Sector-48\nLakhani's Palm View,\nNerul(W), Navi Mumbai\400706",fo1);
							cell = new PdfPCell(phrase);
							cell.setRowspan(3);
							t0.addCell(cell).setBorder(0);
							doc.add(t0);
							PdfPTable t = new PdfPTable(6);
							t.setWidthPercentage(100);
							t.setSpacingBefore(10);
							t.setSpacingAfter(10);
							com.itextpdf.text.Font f0 = new com.itextpdf.text.Font(bs,14,com.itextpdf.text.Font.BOLD);
							f0.setColor(BaseColor.WHITE);
							com.itextpdf.text.Font f1 = new com.itextpdf.text.Font(bs,10,com.itextpdf.text.Font.NORMAL);
							phrase = new Phrase("Bill",f0);
							cell = new PdfPCell(phrase);
							cell.setColspan(6);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell);
							phrase = new Phrase("#",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase("Company",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase("Description",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase("Quantity",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase("MRP",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase("Amount",f0);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell).setBorderWidth(1);
							query = "select * from cart"+str+";";
							result = statement.executeQuery(query);
							long s=0,i=1;
							while(result.next())
							{
								phrase = new Phrase(""+i+".",f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								phrase = new Phrase(result.getString("company").toString(),f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								phrase = new Phrase(result.getString("name").toString(),f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								phrase = new Phrase(result.getString("quantity").toString(),f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								phrase = new Phrase(result.getString("mrp").toString(),f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								long x = Integer.parseInt(result.getString("mrp"))*Integer.parseInt(result.getString("quantity"));
								phrase = new Phrase(""+x+"/-",f1);
								cell = new PdfPCell(phrase);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(new BaseColor(230,230,230));
								t.addCell(cell);
								s+=x;
								i++;
								query = "select * from medicine where Batch_no = "+result.getString("batch_no").toString()+";";
								result1 = statement1.executeQuery(query);
								if(result1.next())
								qty1=Integer.parseInt(result1.getString("Quantity"));
								query = "update medicine set Quantity = "+(qty1-Integer.parseInt(result.getString("quantity").toString()))+" where Batch_no = "+result.getString("batch_no").toString()+";";
								statement1.execute(query);
							}
							Paragraph p1 = new Paragraph();
							phrase = new Phrase("Subtotal\n",f1);
							p1.add(phrase);
							phrase = new Phrase("Discount\n",f1);
							p1.add(phrase);
							phrase = new Phrase("Tax",f1);
							p1.add(phrase);
							cell = new PdfPCell(p1);
							cell.setColspan(5);
							cell.setRowspan(3);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(230,230,230));
							t.addCell(cell);
							p1 = new Paragraph();
							phrase = new Phrase(""+s+".00/-\n",f1);
							p1.add(phrase);
							phrase = new Phrase("10%\n",f1);
							p1.add(phrase);
							phrase = new Phrase("18%",f1);
							p1.add(phrase);
							cell = new PdfPCell(p1);
							cell.setColspan(1);
							cell.setRowspan(3);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(230,230,230));
							t.addCell(cell);
							com.itextpdf.text.Font ttl = new com.itextpdf.text.Font(bs,14,com.itextpdf.text.Font.BOLD);
							phrase = new Phrase("Total\n",ttl);
							cell = new PdfPCell(phrase);
							cell.setColspan(5);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(255,255,204));
							t.addCell(cell).setBorderWidth(1);
							double fin = (s*108);
							double fin1 = BigDecimal.valueOf(fin/100).setScale(2,RoundingMode.HALF_UP).doubleValue();
							phrase = new Phrase(""+fin1+"/-\n",ttl);
							cell = new PdfPCell(phrase);
							cell.setColspan(1);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBackgroundColor(new BaseColor(255,255,204));
							t.addCell(cell).setBorderWidth(1);
							phrase = new Phrase(" ");
							cell = new PdfPCell(phrase);
							cell.setColspan(6);
							cell.setBackgroundColor(new BaseColor(77, 121, 255));
							t.addCell(cell);
							doc.add(t);
							com.itextpdf.text.Font ty = new com.itextpdf.text.Font(bs,14,com.itextpdf.text.Font.NORMAL);
							ty.setColor(BaseColor.RED);
							com.itextpdf.text.Font sign = new com.itextpdf.text.Font(bs,10,com.itextpdf.text.Font.NORMAL);
							phrase = new Phrase("Special Instructions:",ttl);
							doc.add(phrase);
							PdfPTable t5 = new PdfPTable(1);
							t5.setWidthPercentage(100);
							phrase = new Phrase("\n\n\n\n\n\n\n\n\n\n");
							cell = new PdfPCell(phrase);
							t5.addCell(cell).setBorderWidth(2);
							doc.add(t5);
							PdfPTable t4 = new PdfPTable(4);
							t4.setWidthPercentage(100);
							t4.setSpacingAfter(50);
							t4.setSpacingBefore(10);
							phrase = new Phrase("\n\nWish you a speedy recovery! Take Care!\n\n",ty);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setColspan(3);
							cell.setRowspan(5);
							t4.addCell(cell).setBorderWidth(0);
							phrase = new Phrase("Pharmacist:\n\n",sign);
							cell = new PdfPCell(phrase);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							t4.addCell(cell).setBorderWidth(0);
							phrase = new Phrase("");
							cell = new PdfPCell(phrase);
							cell.setRowspan(4);
							t4.addCell(cell);
							doc.add(t4);
							doc.close();
							query = "select * from customer where Customer_ID = '"+w2+"';";
							result = statement.executeQuery(query);
							if(!result.next())
							{
								query = "insert into customer values('"+w1+"','"+w2+"',"+w3+","+w4+",'"+w5+"');";
								statement1.execute(query);
							}
							query = "select * from cart"+str+";";
							result = statement.executeQuery(query);
							while(result.next())
							{
								query = "insert into customer_history values ('"+trnsid+"','"+date12+"',"+result.getString("batch_no")+","+fin1+",'"+time12+"','"+w2+"');";
								statement1.execute(query);
							}
							query = "select * from cart"+str+";";
							result = statement.executeQuery(query);
							while(result.next())
							{
								query = "insert into sales_log values ("+result.getString("batch_no")+",'"+date12+"','"+trnsid+"',"+fin1+","+result.getString("quantity")+","+str+",'"+w4+"');";
								statement1.execute(query);
							}
						} catch (IOException | DocumentException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						query = "drop table if exists cart"+str+";";
						try {
							statement.execute(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						  JOptionPane.showMessageDialog(null," Invoice generated ","Success Operation",1);
						cst_dtl.hide();
						f.hide();}
					}
				});
				cst_dtl.setVisible(true);
				cst_dtl.setLayout(null);
				cst_dtl.setResizable(false);
			}
		});
		
		clr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				query = "drop table if exists cart"+str+";";
				try {
					statement.execute(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jl.setVisible(true);
				sp.setVisible(false);
				medt.setVisible(false);
				add.setVisible(false);
				rmv.setVisible(false);
				dlt.setVisible(false);
				clr.setVisible(false);
				co.setVisible(false);
			}
		});
		dlt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = medt.getSelectedRow();
				if(i>=0)
				{
					String bno1;
					bno1 = medt1.getValueAt(i, 2).toString().trim();
					medt1.removeRow(i);
					query = "delete from cart"+str+" where batch_no = "+bno1+";";
					try {
						statement.execute(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = medt.getSelectedRow();
				if(i>=0)
				{
					query = "select * from medicine where Batch_no = "+medt1.getValueAt(i, 2).toString().trim()+";";
					int a = Integer.parseInt(medt1.getValueAt(i, 3).toString().trim()),qty=1;
					try {
						result = statement.executeQuery(query);
						if(result.next())
						qty=Integer.parseInt(result.getString("Quantity"));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(qty-a>0)
					++a;
					medt1.setValueAt(a, i, 3);
					String bno1,quantity1;
					bno1 = medt1.getValueAt(i, 2).toString().trim();
					quantity1 = medt1.getValueAt(i, 3).toString().trim();
					query = "update cart"+str+" set quantity = "+quantity1+" where batch_no = "+bno1+";";
					try {
						statement.execute(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		rmv.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = medt.getSelectedRow();
				if(i>=0)
				{
					int a = Integer.parseInt(medt1.getValueAt(i, 3).toString().trim());
					if(a>1)
					{
						--a;
						medt1.setValueAt(a, i, 3);
						String bno1,quantity1;
						bno1 = medt1.getValueAt(i, 2).toString().trim();
						quantity1 = medt1.getValueAt(i, 3).toString().trim();
						query = "update cart"+str+" set quantity = "+quantity1+" where batch_no = "+bno1+";";
						try {
							statement.execute(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						String bno1,quantity1;
						bno1 = medt1.getValueAt(i, 2).toString().trim();
						quantity1 = medt1.getValueAt(i, 1).toString().trim();
						query = "delete from cart"+str+" where batch_no = "+bno1+";";
						try {
							statement.execute(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						medt1.removeRow(i);
					}
				}
			}
		});
		bg.add(clr);
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Cart a = new Cart();
		a.show_cart("0");
	}

}
