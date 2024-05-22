package commons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Form1 extends JFrame{

	public Form1(Connection conn) {
		
		AbstractJasperReports.createReport(conn, "C:\\Users\\daniel\\Documents\\workspace\\JasperReport1\\src\\Reporte.jasper");
		initComponent();
	}
	
	public void initComponent(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100, 370, 164);
		getContentPane().setLayout(null);
		( (JPanel) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						
						AbstractJasperReports.showViewer();
				
					}
				});
		btnGenerar.setBounds(44, 31, 110, 46);
		getContentPane().add(btnGenerar);
		JButton btnExportarPDF = new JButton("Exportar PDF");
		btnExportarPDF.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						
						AbstractJasperReports.exportToPDF("C:\\Users\\daniel\\Documents\\workspace\\JasperReport1\\report.pdf");
				
					}
				});
		
		btnExportarPDF.setBounds(197, 31, 110, 46);
		getContentPane().add(btnExportarPDF);
		
		
		
	}

}
