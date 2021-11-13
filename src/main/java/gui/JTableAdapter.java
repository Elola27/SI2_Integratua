package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import domain.BezeroErregistratuAdapter;
import domain.Bezeroa;
import domain.PronostikoaContainer;

public class JTableAdapter extends JFrame {

	private JPanel contentPane;
	private JTable taula;
	private DefaultTableModel taulaModel;
	private String[] columnNamesApustuak= new String[] {"ID","Irabazi posiblea","Pronostiko kopurua","Asmatutakoak"};
	/**
	 * Launch the application.
	 */
	
	private static Bezeroa user;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					JTableAdapter frame = new JTableAdapter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JTableAdapter() {
		BLFacade blFacade= new BLFacadeImplementation();
		user = blFacade.getBezeroa("b");
		setTitle("Ikusi apustuak " + user.getErabiltzaileIzena() + " bezeroarena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//taula=new JTable();
		
		/*taulaModel=new DefaultTableModel();
		taulaModel.addColumn("ID");
		taulaModel.addColumn("Irabazi posibleak");
		taulaModel.addColumn("Pronostiko kopurua");
		taulaModel.addColumn("Asmatutakoak");
		//taula.setModel(taulaModel);
		//taula=new JTable(taulaModel);
		taula=new JTable(taulaModel);
		contentPane.add(taula);
		//taulaModel.setColumnCount(4);	
		BezeroErregistratuAdapter adaptadorea=new BezeroErregistratuAdapter(user.getApustuak());
		Vector<Object> row;
		for (int i=0; i<adaptadorea.getRowCount();i++) {
			row = new Vector<Object>();
			row.add(adaptadorea.getValueAt(i, 0));
			row.add(adaptadorea.getValueAt(i, 1));
			row.add(adaptadorea.getValueAt(i, 2));
			row.add(adaptadorea.getValueAt(i, 3));
			taulaModel.addRow(row);
		}
		//contentPane.add(taula);
		
		setVisible(true);
		
		
		/*taula=new JTable(adaptadorea);
		//taula.setModel(taulaModel);
		taula.setBounds(80,80, 250, 300);
		getContentPane().add(taula,BorderLayout.CENTER);*/
	
		BezeroErregistratuAdapter adaptadorea=new BezeroErregistratuAdapter(user.getApustuak());
		//taula=new JTable(adaptadorea);
		taula=new JTable(adaptadorea);
		taula.setBounds(80,80, 250, 300);
		getContentPane().add(taula,BorderLayout.CENTER);

	}

}
