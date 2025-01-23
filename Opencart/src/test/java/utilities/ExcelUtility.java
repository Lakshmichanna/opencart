package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.streaming.DeferredSXSSFSheet;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

/*For OOXML (.xlsx) files, use XSSFWorkbook (part of poi-ooxml).
For OLE2 (.xls) files, use HSSFWorkbook (part of poi).
 **
 *
if (excelFilePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);  // For .xlsx files
            } else if (excelFilePath.endsWith(".xls")) {
 }               workbook = new HSSFWorkbook(fis);  // For .xls files
 */

public class ExcelUtility {


	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	public CellStyle style;


	public ExcelUtility(String path) {
		this.path = path;
		
	}

	



	public void getsheet(String sheetname) throws IOException{


		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);

	}

	public int getRowCount(String sheetname) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();		
		return rowcount;


	}

	public int getCellcount(String sheetname, int rownum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();		
		return cellcount;

	}


	public String getCellData(String sheetname, int rownum,int colnum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);


		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of the cell as a string 
		}

		catch(Exception e) {
			data ="";
		}
		workbook.close();
		fi.close();
		return data;

	}


	public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException {

		File xlfile = new File(path);
		if(!xlfile.exists()) {

			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);

		}

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetname)==-1) // if sheet not exists then create a new sheet 

			workbook.createSheet(sheetname);
		sheet = workbook.getSheet(sheetname);
		if(sheet.getRow(rownum)==null) // if row not exists then create a new row 
			sheet.createRow(rownum);
		row =sheet.getRow(rownum);
		cell =row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();



	}

	public String getCellData(int RowNum, int ColNum) throws Exception{

		try{

			cell = sheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getRawValue();
			/*switch (cell.getCellType()) {
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
				CellData = String.valueOf(cell.getRawValue());
				Thread.sleep(500);
				set.debugLogging("Read from excel: "+cell.getNumericCellValue(),"Info");
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
				CellData = cell.getStringCellValue();
				Thread.sleep(500);
				set.debugLogging("Read from excel: "+cell.getStringCellValue(),"Info");
				break;
			}*/
			return CellData;

		}catch (Exception e){

			return"";
		}
	}

	public String[][] getdata(String sheetname) throws Exception{


		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		System.out.println(workbook.getProperties());
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(rowcount).getLastCellNum();

		System.out.println("Row & column count:" + rowcount +   colcount);

		String[][] casedata = new String[rowcount][colcount];

		for(int r=1;r<rowcount;r++) {

			for(int c=0;c<colcount;c++) {

				casedata[r-1][c] = getCellData(r, c);

				System.out.println("Cell data :" + getCellData(r, c));

			}
		}


		return casedata;



	}

	
	
	public String[][] getxlsdata(String sheetname) throws IOException {
		
		FileInputStream file = new FileInputStream(path);
		try {
		HSSFWorkbook book = new HSSFWorkbook(file);
		HSSFSheet sheet = book.getSheet(sheetname);
		int trows = sheet.getLastRowNum();
			System.out.println("No.of Rows"+ trows);	
		int tcols = sheet.getRow(0).getLastCellNum();
			System.out.print("No.of Columns"+ tcols);
			
			String[][] casedata = new String[trows][tcols];
		for(int r=1;r<=trows;r++) {
			HSSFRow row = sheet.getRow(r);
			for(int c=0;c<=tcols;c++) {
				
				casedata[r-1][c] = getCellData(r, c);
				/*HSSFCell cel = row.getCell(c);
				if(cel!=null) {
				System.out.print(cel.toString()+"\t");
				}
				else {
					System.out.print("NUll"+"\t");
				}*/
			}
			//System.out.println();
		}
		return casedata;
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

