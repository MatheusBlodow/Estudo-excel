
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

public class OpenWorkBook extends JFrame {
	private static JTextField fieldConcurso;
	private static JTextField fieldNum1;
	private static JTextField fieldNum2;
	private static JTextField fieldNum3;
	private static JTextField fieldNum4;
	private static JTextField fieldNum5;
	private static JTextField fieldNum6;
	private static Integer vetorCount[][] = new Integer[60][2];
	private JScrollPane scrollPane;
	private static JButton btnPreencherJlist;
	private JPanel panelBotoes;
	static DefaultListModel model = new DefaultListModel();
	private static JList listOrdenada = new JList(model);
	private static Integer matrix[][] = new Integer[1][1];

	// http://www.douglaspasqua.com/2011/12/20/java-tips-usando-jlist/

	public OpenWorkBook() {

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelJlist = new JPanel();
		panelPrincipal.add(panelJlist, BorderLayout.CENTER);
		panelJlist.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelJlist.add(scrollPane);
		scrollPane.setViewportView(listOrdenada);

		JPanel panelEntrada = new JPanel();
		panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
		panelEntrada.setLayout(new BorderLayout(0, 0));

		JPanel panelConcurso = new JPanel();
		panelEntrada.add(panelConcurso, BorderLayout.NORTH);
		panelConcurso.setBorder(new TitledBorder(null, "Concurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConcurso.setLayout(new BorderLayout(0, 0));

		fieldConcurso = new JTextField();
		panelConcurso.add(fieldConcurso);
		fieldConcurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						verificaConcurso(fieldConcurso.getText());
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		fieldConcurso.setColumns(10);
		
		panelBotoes = new JPanel();
		panelConcurso.add(panelBotoes, BorderLayout.EAST);
				
						JButton btnVerificar = new JButton("Verificar");
						panelBotoes.add(btnVerificar);
						btnVerificar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try {
									verificaConcurso(fieldConcurso.getText());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
		
				btnPreencherJlist = new JButton("Exibir ocorr\u00EAncia");
				panelBotoes.add(btnPreencherJlist);
				btnPreencherJlist.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						preencherJlist(matrix);
					}
				});

		JPanel panelDezenas = new JPanel();
		panelEntrada.add(panelDezenas, BorderLayout.SOUTH);
		panelDezenas.setBorder(new TitledBorder(null, "Dezenas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDezenas.setLayout(new GridLayout(1, 6, 0, 0));

		fieldNum1 = new JTextField();
		panelDezenas.add(fieldNum1);
		fieldNum1.setColumns(10);

		fieldNum2 = new JTextField();
		panelDezenas.add(fieldNum2);
		fieldNum2.setColumns(10);

		fieldNum3 = new JTextField();
		panelDezenas.add(fieldNum3);
		fieldNum3.setColumns(10);

		fieldNum4 = new JTextField();
		panelDezenas.add(fieldNum4);
		fieldNum4.setColumns(10);

		fieldNum5 = new JTextField();
		panelDezenas.add(fieldNum5);
		fieldNum5.setColumns(10);

		fieldNum6 = new JTextField();
		panelDezenas.add(fieldNum6);
		fieldNum6.setColumns(10);

		setSize(550, 256);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	// private static Integer matrix[][] = new Integer[1][7];
	

	public static void main(String args[]) throws Exception {

		new OpenWorkBook();

	}

	private static void verificaConcurso(String concurso) throws IOException {

		File file = new File("caixa.xlsx");
		FileInputStream fIP = new FileInputStream(file);
		// Get the workbook instance for XLSX file
		XSSFWorkbook workbook = new XSSFWorkbook(fIP);
//		if (file.isFile() && file.exists()) {
//			System.out.println("openworkbook.xlsx file open successfully.");
//		} else {
//			System.out.println("Error to open openworkbook.xlsx file.");
//		}

		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();
		matrix = new Integer[sheet.getLastRowNum() + 1][7];
		int linha = 0;
		int coluna = 0;

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			coluna = 0;
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();

				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double valor = cell.getNumericCellValue();
					matrix[linha][coluna] = valor.intValue();
				}

				coluna++;
			}

			linha++;
		}
		int conc = Integer.parseInt(concurso);

		for (int i = 0; i < matrix.length - 1; i++) {

			if (matrix[i][0] == conc) {
				fieldNum1.setText(String.valueOf(matrix[i][0]));
				fieldNum2.setText(String.valueOf(matrix[i][1]));
				fieldNum3.setText(String.valueOf(matrix[i][2]));
				fieldNum4.setText(String.valueOf(matrix[i][3]));
				fieldNum5.setText(String.valueOf(matrix[i][4]));
				fieldNum6.setText(String.valueOf(matrix[i][5]));
			}
		}

		// Laço inicializa matriz

	}

	private static void preencherJlist(Integer[][] matrix) {

		for (int i = 0; i < vetorCount.length; i++) {
			for (int j = 0; j < vetorCount[i].length; j++) {
				if (j == 0) {
					vetorCount[i][0] = i + 1;
					// System.out.println(vetorCount[i][0]);
				}
				if (j == 1) {
					vetorCount[i][1] = 0;
					// System.out.println(vetorCount[i][1]);
				}
			}
		}

		//Laço contagem ocorrencia de dezenas
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j > 0) {
					// System.out.println(i + "-" + j );
					vetorCount[matrix[i][j] - 1][1]++;

				}

			}
		}

//		for (int i = 0; i < vetorCount.length; i++) {
//			for (int j = 0; j < vetorCount[i].length; j++) {
//				if (j == 0) {
//
//					System.out.print(vetorCount[i][0] + " - ");
//
//				}
//				if (j == 1) {
//
//					System.out.println(vetorCount[i][1]);
//				}
//			}
//		}

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
		btnPreencherJlist.setEnabled(false);
		
		
		
	}
	
//	private void buscarDezenas(Integer[][] matrix){
//		
////	}
	
	
}