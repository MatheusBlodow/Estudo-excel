package br.com.excel;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;
public class CreateWorkBook
{
   public static void main(String[] args)throws Exception
   {
      //Create Blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("planilha");
      //Create file system using specific name
      FileOutputStream out = new FileOutputStream(
      new File("createworkbook.xlsx"));
      //write operation workbook using file out object
      workbook.write(out);
      out.close();
      System.out.println("createworkbook.xlsx written successfully");
   }
}