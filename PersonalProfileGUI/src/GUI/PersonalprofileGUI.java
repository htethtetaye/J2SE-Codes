package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PersonalprofileGUI extends JFrame implements ActionListener {
	Connection con;
	String gen;

	JLabel lbp = new JLabel("Personal Profile Entry");
	JLabel lbname = new JLabel("Name");
	JLabel lbnrc = new JLabel("NRC Number");
	JLabel lbfather = new JLabel("Father Name");
	JLabel lbdate = new JLabel("Date OF Bitrh");
	JLabel lbplace = new JLabel("Place of Birth");
	JLabel lbgender = new JLabel("Gender");
	JLabel lbaddress = new JLabel("Address");
	JLabel lbphone = new JLabel("Phone");

	JTextField txtname = new JTextField(20);
	JTextField txtnrc = new JTextField(20);
	JTextField txtfather = new JTextField(20);
	JTextField txtphone = new JTextField(20);

	JTextArea txtaddress = new JTextArea(20, 20);
	JScrollPane scoraddress = new JScrollPane(txtaddress);

	String day1[] = new String[32];
	String month1[] = new String[13];
	String year1[] = new String[100];

	JComboBox cday;
	JComboBox cmonth;
	JComboBox cyear;

	String place[] = { "Yangon", "Bago", "Mandalay", "Pyinoolwin", "Naypyitaw" };
	JComboBox cplace = new JComboBox(place);

	JButton btnsave = new JButton("Save");
	
	JButton btnscancel = new JButton("Cancel");

	JRadioButton r1 = new JRadioButton("Male", true);
	JRadioButton r2 = new JRadioButton("Feale");

	public PersonalprofileGUI() {
		setSize(700, 500);
		setLocation(200, 200);

		input();
		cday = new JComboBox(day1);
		cmonth = new JComboBox(month1);
		cyear = new JComboBox(year1);

		JPanel p2 = new JPanel(new GridLayout(1, 3));
		p2.add(cday);
		p2.add(cmonth);
		p2.add(cyear);

		ButtonGroup g = new ButtonGroup();
		g.add(r1);
		g.add(r2);
		JPanel p3 = new JPanel();
		p3.add(r1);
		p3.add(r2);

		JPanel p1 = new JPanel(new GridLayout(8, 2));
		p1.add(lbname);
		p1.add(txtname);
		p1.add(lbnrc);
		p1.add(txtnrc);
		p1.add(lbfather);
		p1.add(txtfather);
		p1.add(lbdate);
		p1.add(p2);
		p1.add(lbplace);
		p1.add(cplace);
		p1.add(lbgender);
		p1.add(p3);
		p1.add(lbaddress);
		p1.add(scoraddress);
		p1.add(lbphone);
		p1.add(txtphone);
		p1.setBorder(BorderFactory.createEtchedBorder());

		JPanel p4 = new JPanel();
		p4.add(lbp);
		p4.setBorder(BorderFactory.createEtchedBorder());

		JPanel p5 = new JPanel();
		p5.add(btnsave);
		p5.add(btnscancel);

		setTitle("User Profile Information");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(p1, BorderLayout.CENTER);
		c.add(p4, BorderLayout.NORTH);
		c.add(p5, BorderLayout.SOUTH);

		btnsave.addActionListener(this);
		btnscancel.addActionListener(this);
		this.setVisible(true);

	}
	private void input() {
		day1[0] = "Day";
		month1[0] = "Month";
		year1[0] = "Year";

		for (int i = 1; i < day1.length; i++) {

			day1[i] = "" + i;
		}

		for (int i = 1; i < month1.length; i++) {
			month1[i] = "" + i;
		}

		for (int i = 1; i < year1.length; i++) {
			year1[i] = "" + (i + 1940);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name=txtname.getText();
		String nrc=txtnrc.getText();
		String fname=txtfather.getText();
		String day=cday.getSelectedItem().toString();
		String month=cmonth.getSelectedItem().toString();
		String year=cyear.getSelectedItem().toString();
		String place=cplace.getSelectedItem().toString();
		String address=txtaddress.getText();
		String phone=txtphone.getText();
		if(r1.isSelected()) {
			gen="male";
		}else if (r2.isSelected()) {
			gen="female";
		}
		

	if(e.getSource()==btnsave) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String dob=day + "-"+month+"-"+year;
			PreparedStatement pre=con.prepareStatement("insert into Profile values(?,?,?,?,?,?,?,?)");
			pre.setString(1, name);
			pre.setString(2, nrc);
			pre.setString(3, fname);
			pre.setString(4, dob);
			pre.setString(5, place);
			pre.setString(6, gen);
			pre.setString(7, address );
			pre.setString(8, phone);
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "Information is saved!!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	else if(e.getSource()==btnscancel) {
	txtname.setText(" ");
	txtnrc.setText(" ");
	txtfather.setText(" ");
	cday.setSelectedItem(" ");
	cmonth.setSelectedItem(" ");
	cyear.setSelectedItem(" ");
	cplace.setSelectedItem(" ");
	txtaddress.setText(" ");
	txtphone.setText(" ");
	if(r1.isSelected()) {
		gen=" ";
	}else if (r2.isSelected()) {
		gen=" ";
	}
		
	}
}
	public static void main(String[] args) {
		new PersonalprofileGUI();

	}
}
