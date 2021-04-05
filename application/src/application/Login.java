package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login 
{
	ImageIcon icon = new ImageIcon(getClass().getResource("login_icon.png"));
	ImageIcon bkgrnd = new ImageIcon(getClass().getResource("Background.png"));
	ImageIcon logo = new ImageIcon(getClass().getResource("logo.png"));
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String NAME = "root";
	static final String PASSWORD = "rootpassword";
	static final String URL = "jdbc:mysql://localhost:3306/dbs_v1";
	static Connection connection;
	static String query;
	static Statement statement;
	
	public void login_show()
	{
		JFrame f = new JFrame("Login");
		f.setBounds(500,200,600,375);
		JLabel bg = new JLabel("",bkgrnd,JLabel.CENTER);
		bg.setBounds(0, 0, 600, 375);
		f.add(bg);
		f.setIconImage(icon.getImage());
		JLabel lg = new JLabel("",logo,JLabel.CENTER);
		lg.setBounds(30,80,200,200);
		bg.add(lg);
		JLabel l1 = new JLabel("Login");
		l1.setBounds(250, 05, 100, 100);
		l1.setFont(new Font("",Font.PLAIN,25));
		l1.setForeground(Color.lightGray);
		try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Rene Bieder  Milliard Light.otf"));
            l1.setFont(font.deriveFont(Font.BOLD, 30f));
        }
        catch(Exception e){}
		bg.add(l1);
		JLabel l2 = new JLabel();
		l2.setText("ID:");
		l2.setFont(new Font("",Font.PLAIN,14));
		l2.setForeground(Color.lightGray);
		l2.setBounds(280,130,70,20);
		bg.add(l2);
		JTextField tf = new JTextField();
		tf.setBounds(380, 130, 170, 20);
		bg.add(tf);
		JLabel l3 = new JLabel();
		l3.setText("Password:");
		l3.setFont(new Font("",Font.PLAIN,14));
		l3.setForeground(Color.lightGray);
		l3.setBounds(280,160,70,20);
		bg.add(l3);
		JLabel msg1 = new JLabel();
		msg1.setText("*Password must be within 6 and 14 characters");
		msg1.setBounds(270,260,300,20);
		msg1.setFont(new Font("Arial",Font.PLAIN,14));
		msg1.setForeground(Color.red);
		msg1.setVisible(false);
		bg.add(msg1);
		JLabel msg2 = new JLabel();
		msg2.setText("*Username or password cannot be empty");
		msg2.setBounds(290,260,300,20);
		msg2.setFont(new Font("Arial",Font.PLAIN,14));
		msg2.setForeground(Color.red);
		msg2.setVisible(false);
		bg.add(msg2);
		JLabel msg3 = new JLabel();
		msg3.setText("*Incorrect Password");
		msg3.setBounds(350,260,300,20);
		msg3.setFont(new Font("Arial",Font.PLAIN,14));
		msg3.setForeground(Color.red);
		msg3.setVisible(false);
		bg.add(msg3);
		JLabel msg4 = new JLabel();
		msg4.setText("*Incorrect ID");
		msg4.setBounds(350,260,300,20);
		msg4.setFont(new Font("Arial",Font.PLAIN,14));
		msg4.setForeground(Color.red);
		msg4.setVisible(false);
		bg.add(msg4);
		JPasswordField pswrd = new JPasswordField();
		pswrd.setBounds(380, 160, 170, 20);
		bg.add(pswrd);
		JButton lgin = new JButton("Login");
		lgin.setBounds(330,220,70,20);
		lgin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				msg1.setVisible(false);
				msg2.setVisible(false);
				msg3.setVisible(false);
				msg4.setVisible(false);
				
				String s = pswrd.getText().toString().trim();
				String t = tf.getText().toString().trim();
				if((s.length()==0||t.length()==0)&&!t.equals("0"))
				msg2.setVisible(true);
				else
				{
					if((s.length()<6||s.length()>14)&&!t.equals("0"))
					msg1.setVisible(true);
					else
					{
						try
						{
							Class.forName(DRIVER);
							connection = DriverManager.getConnection(URL,NAME,PASSWORD);
							statement = connection.createStatement();
							query="select * from employee where ID="+t+";";
							ResultSet result = statement.executeQuery(query);
							if(result.next())
							{
								if(result.getString("Password").equals(s))
								{
									f.hide();
									new Home().home_display(t,1);
									//System.out.println("Login successful!");
								}
								else
								{
									msg3.setVisible(true);
								}
							}
							else
							{
								msg4.setVisible(true);
							}
						}
						catch(Exception ef)
						{
							ef.printStackTrace();
						}
					}
				}
			}
		});
		bg.add(lgin);
		JButton rst = new JButton("Reset");
		rst.setBounds(450, 220, 70, 20);
		rst.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pswrd.setText("");
				tf.setText("");
				msg1.setVisible(false);
				msg2.setVisible(false);
				msg3.setVisible(false);
				msg4.setVisible(false);
			}
		});
		bg.add(rst);
		f.setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		Login a = new Login();
		a.login_show();
	}
}
