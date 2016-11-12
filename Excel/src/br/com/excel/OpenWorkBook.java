package br.com.excel;
import java.io.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.*;
public class OpenWorkBook
{
   public static void main(String args[])throws Exception
   {
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

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					System.out.print(cell.getStringCellValue() + "     ");
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					
					Double numericCellValue = cell.getNumericCellValue();
					
					System.out.print(numericCellValue.intValue() + "      ");
				} else {
					throw new Exception("Erro");
				}
			}
			System.out.println();
		}
   }
}