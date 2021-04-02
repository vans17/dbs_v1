package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;
public class Cart {

	ImageIcon cart = new ImageIcon(getClass().getResource("cart_logo.png"));
	ImageIcon cart_bg = new ImageIcon(getClass().getResource("cart_bg.jpg"));
	
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
	
	public void add_to_Cart(String cmpname,String btchno) throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query = "create table if not exists cart (name varchar(45) ,quantity int,mrp int, primary key(name,mrp));";
		statement.execute(query);
		query = "select * from medicine where Batch_no='"+btchno+"' and company='"+cmpname+"';";
		result = statement.executeQuery(query);
		String name="",mrp="";
		if(result.next())
		{
			name = result.getString("Name");
			mrp = result.getString("MRP");
		}
		query = "select * from cart where name = '"+name+"' and mrp = "+mrp+";";
		result = statement.executeQuery(query);
		if(result.next())
		{
			String as,df;
			as = result.getString("name");
			df = result.getString("mrp");
			int abcd=1;
			query = "select quantity from cart where name = '"+as+"' and mrp ="+df+";";
			result = statement.executeQuery(query);
			if(result.next())
			abcd = result.getInt("quantity");
			++abcd;
			query = "update cart set quantity = "+abcd+" where name = '"+as+"' and mrp = "+df+";";
		}
		else
		{
			query = "insert into cart values('"+name+"',1,"+mrp+");";
		}
		statement.execute(query);
	}
	
	public void show_cart() throws ClassNotFoundException, SQLException
	{
		Object[] columns = {"Name","Quantity","MRP"};
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
		Object[] row = new Object[4];
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
		query = "select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'cart';";
		result = statement.executeQuery(query);
		if(result.next())
		{
			jl.setVisible(false);
			query = "select * from cart;";
			result = statement.executeQuery(query);
			while(result.next())
			{
				row[0]=result.getString("name");
				row[1]=result.getString("quantity");
				row[2]=result.getString("mrp");
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
		co.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Cart a = new Cart();
				try {
					a.checkout();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//f.hide();
			}
		});
		clr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				query = "drop table if exists cart;";
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
				medt1.removeRow(i);
			}
		});
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = medt.getSelectedRow();
				if(i>=0)
				{
					int a = Integer.parseInt(medt1.getValueAt(i, 1).toString());
					++a;
					medt1.setValueAt(a, i, 1);
					String name1,quantity1,mrp1;
					name1 = medt1.getValueAt(i, 0).toString();
					quantity1 = medt1.getValueAt(i, 1).toString();
					mrp1 = medt1.getValueAt(i, 2).toString();
					query = "update cart set quantity = "+quantity1+" where name = '"+name1+"' and mrp = "+mrp1+";";
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
					int a = Integer.parseInt(medt1.getValueAt(i, 1).toString());
					if(a>1)
					{
						--a;
						medt1.setValueAt(a, i, 1);
						String name1,quantity1,mrp1;
						name1 = medt1.getValueAt(i, 0).toString();
						quantity1 = medt1.getValueAt(i, 1).toString();
						mrp1 = medt1.getValueAt(i, 2).toString();
						query = "update cart set quantity = "+quantity1+" where name = '"+name1+"' and mrp = "+mrp1+";";
						try {
							statement.execute(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						String name1,quantity1,mrp1;
						name1 = medt1.getValueAt(i, 0).toString();
						quantity1 = medt1.getValueAt(i, 1).toString();
						mrp1 = medt1.getValueAt(i, 2).toString();
						query = "delete from cart where name = '"+name1+"' and mrp = "+mrp1+";";
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
	
	public void checkout() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query = "drop table if exists cart;";
		statement.execute(query);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Cart a = new Cart();
		a.show_cart();
	}

}
