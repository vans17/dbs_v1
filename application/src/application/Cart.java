package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
public class Cart {

	ImageIcon cart = new ImageIcon(getClass().getResource("cart_logo.png"));
	
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
		JFrame f = new JFrame("Cart");
		f.setBounds(550,150,600,450);
		f.setIconImage(cart.getImage());
		JTable medt = new JTable();
		medt.setBounds(30,25,460,330);
		f.add(medt);
		JScrollPane sp = new JScrollPane(medt);
		sp.setBounds(30,25,460,330);
		Class.forName(DRIVER);
		f.add(sp);
		JButton co = new JButton("Checkout");
		co.setBounds(360,360,100,40);
		f.add(co);
		JButton clr = new JButton("Clear cart");
		clr.setBounds(470,360,100,40);
		JButton add = new JButton("+");
		add.setBounds(512,50,50,50);
		f.add(add);
		JButton rmv = new JButton("-");
		rmv.setBounds(512,115,50,50);
		f.add(rmv);
		JButton dlt = new JButton("Delete");
		dlt.setBounds(497,180,80,40);
		f.add(dlt);
		JLabel jl = new JLabel("No items selected");
		jl.setBounds(220,150,300,20);
		jl.setFont(new Font("Times New Roman",Font.PLAIN,20));
		jl.setForeground(Color.red);
		f.add(jl);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query = "select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'cart';";
		result = statement.executeQuery(query);
		if(result.next())
		{
			jl.setVisible(false);
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
				query = "delete from cart;";
			}
		});
		f.add(clr);
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
	}
	
	public void checkout() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL,NAME,PASSWORD);
		statement = connection.createStatement();
		query = "drop table cart";
		statement.execute(query);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Cart a = new Cart();
		a.show_cart();
	}

}
