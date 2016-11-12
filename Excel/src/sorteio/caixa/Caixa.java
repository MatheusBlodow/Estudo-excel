package sorteio.caixa;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

public class Caixa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_NumeroUm;
	private JTextField textField_NumeroDois;
	private JTextField textField_NumeroTres;
	private JTextField textField_NumeroQuatro;
	private JTextField textField_NumeroCinco;
	private JTextField textField_NumeroSeis;
	private JTextField textField_NumeroConcurso;
	private static Caixa frame = new Caixa();
	private static Integer vetorCount[][] = new Integer[60][2];
	private JPanel panel_3;
	private JButton btnExportarXlsx;
	private JButton btnExportarTxt;
	private JButton btnSorteio;
	static DefaultListModel model = new DefaultListModel();
	private static JList list = new JList(model);
	private static Integer m[][] = new Integer[1][1];
	
	public Caixa(){
		initComponents();
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void initComponents(){
		
		getContentPane().add(list, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(null);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "N\u00FAmeros sorteados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		textField_NumeroUm = new JTextField();
		panel_3.add(textField_NumeroUm);
		textField_NumeroUm.setColumns(10);
		
		textField_NumeroDois = new JTextField();
		panel_3.add(textField_NumeroDois);
		textField_NumeroDois.setColumns(10);
		
		textField_NumeroTres = new JTextField();
		panel_3.add(textField_NumeroTres);
		textField_NumeroTres.setColumns(10);
		
		textField_NumeroQuatro = new JTextField();
		panel_3.add(textField_NumeroQuatro);
		textField_NumeroQuatro.setColumns(10);
		
		textField_NumeroCinco = new JTextField();
		panel_3.add(textField_NumeroCinco);
		textField_NumeroCinco.setColumns(10);
		
		textField_NumeroSeis = new JTextField();
		panel_3.add(textField_NumeroSeis);
		textField_NumeroSeis.setColumns(10);
		
		btnSorteio = new JButton("Sorteio");
		panel_3.add(btnSorteio);
		
		JButton btnValidarNmeros = new JButton("Validar N\u00FAmeros");
		panel_3.add(btnValidarNmeros);
		
		btnExportarTxt = new JButton("Exportar txt");
		panel_3.add(btnExportarTxt);
		
		btnExportarXlsx = new JButton("Exportar xlsx");
		panel_3.add(btnExportarXlsx);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Concurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_NumeroConcurso = new JTextField();
		panel_1.add(textField_NumeroConcurso);
		textField_NumeroConcurso.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		
		JButton btn_Ok = new JButton("OK");
		panel_2.add(btn_Ok);
		
		JButton btnVerificarOcorrencia = new JButton("Verificar Ocorr\u00EAncia");
		panel_2.add(btnVerificarOcorrencia);
		btnVerificarOcorrencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarOcorrencia(m);
			}
		});
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					localizarSorteio();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void localizarSorteio() throws Exception{
		 File file = new File("caixa.xlsx");
		   
	      FileInputStream fIP = new FileInputStream(file);
	      //Get the workbook instance for XLSX file
	      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
	      if(file.isFile() && file.exists())
	      {
	         System.out.println(
	         "openworkbook.xlsx file open successfully.");
	      }
	      else
	      {
	         System.out.println(
	         "Error to open openworkbook.xlsx file.");
	      }

			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();
			int m[][] = new int[sheet.getLastRowNum() + 1][7];
			
			int lin = 0;
			
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				int col = 0;
				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						System.out.print(cell.getStringCellValue() + " ");
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						Double numericCellValue = cell.getNumericCellValue();
						/* Variavel tipo classe Double com D maiusculo */
						int valor = numericCellValue.intValue();
						m[lin][col] = valor;
					} else {
						// throw new Exception("Erro");
					}
					col++;
				}
				lin++;
		}
	
		int lembro = Integer.parseInt(textField_NumeroConcurso.getText()) - 1;
		for (int i=0; i<1; i++) {
		   for (int j=0; j<m[i].length; j++) {
		      System.out.print(m[lembro][j] +" ");
		   }
		   textField_NumeroUm.setText(Integer.toString(m[lembro][1]));
		   textField_NumeroDois.setText(Integer.toString(m[lembro][2]));
		   textField_NumeroTres.setText(Integer.toString(m[lembro][3]));
		   textField_NumeroQuatro.setText(Integer.toString(m[lembro][4]));
		   textField_NumeroCinco.setText(Integer.toString(m[lembro][5]));
		   textField_NumeroSeis.setText(Integer.toString(m[lembro][6]));
		   
		   System.out.printf("\n");
		}
	}
	
	public void verificarOcorrencia(Integer [][] matrix){
		for (int i = 0; i < vetorCount.length; i++) {
			for (int j = 0; j < vetorCount[i].length; j++) {
				if (j == 0) {
					vetorCount[i][0] = i + 1;
				}
				if (j == 1) {
					vetorCount[i][1] = 0;
				}
			}
		}

		//Laço contagem ocorrencia de dezenas
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j > 0) {
					vetorCount[matrix[i][j] - 1][1]++;

				}

			}
		}

		boolean troca = true;
		
		//Laço ordenação das ocorrencias
		while (troca) {
			troca = false;
			for (int i = 0; i < vetorCount.length - 1; i++) {
				if (vetorCount[i][1] < vetorCount[i + 1][1]) {
					int var0 = vetorCount[i][0];
					int var1 = vetorCount[i][1];

					int var01 = vetorCount[i + 1][0];
					int var02 = vetorCount[i + 1][1];

					vetorCount[i][0] = var01;
					vetorCount[i][1] = var02;

					vetorCount[i + 1][0] = var0;
					vetorCount[i + 1][1] = var1;
					troca = true;
				}
			}

		}

		String lista = null;

//		System.out.println("DEPOIS");
		//Laço para adição das ocorrencias no JList
		for (int i = 0; i < vetorCount.length - 1; i++) {
			for (int j = 0; j < vetorCount[i].length; j++) {
				if (j == 0) {

					lista = vetorCount[i][0] + " - ";

				}
				if (j == 1) {

					lista += vetorCount[i][1] + "";
//					System.out.println(lista);
				}
			}

			model.add(i, lista);

		}
		
			
	}
	
	public static void main (String [] args){
		new Caixa();
	}
}
