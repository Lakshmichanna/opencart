package utilities;



	import java.io.File;
	import java.io.FileInputStream;

	import jxl.Sheet;
	import jxl.Workbook;
	import jxl.write.WritableSheet;
	import jxl.write.WritableWorkbook;

	public class ExcelWB {

		public static Workbook book;

		public static Sheet sheet;

		public static WritableWorkbook wbook;

		public static WritableSheet wsheet;

		// create excel connection to read the data

		public static void excelConnection(String filename) throws Exception {
			File file = new File(filename);

			FileInputStream filein = new FileInputStream(file);

			book = Workbook.getWorkbook(filein);

		}

		public static void sheet(String sheetname) {
			if (book.getSheet(sheetname) != null) {
				sheet = book.getSheet(sheetname);
			}
		}
		// get the number of rows

		public static int rowcount() {
			return sheet.getRows();

		}

		// get the number of columns

		public static int columncount() {
			return sheet.getColumns();

		}

		// read the data from particular method
		public static String readdata(int colnum, int rownum) {

			return sheet.getCell(colnum, rownum).getContents();
		}

		public static void close() {
			book.close();

		}
		public static Object[][] getcontent(String filename, String sheetname) throws Exception

		{
			excelConnection(filename);
			sheet(sheetname);
			 int row = rowcount();
			//out.println(row);
			int col = columncount();
			//out.println(col);

			String[][] data = new String[row - 1][col];
			
			for (int r = 1; r < row; r++) {
				for (int c = 0; c < col; c++) {

					data[r - 1][c] = readdata(c, r);
				}
			}

			return data;

		}


	}

	
	
	

