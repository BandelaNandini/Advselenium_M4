package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author B.Nandini This is a reusable class to work with Excel file
 */
public class ExcelFileUtility {

	public Workbook wb;

	/**
	 * This is a reusable method to fetch data from Excel file
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcel(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex).toString();
		return data;
	}

	/**
	 * This is a reusable method to update the data in existing row to Excel file
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcel_ExistingRow(String sheetname, int rowindex, int cellindex, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Cell c = wb.getSheet(sheetname).getRow(rowindex).createCell(cellindex);
		c.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		wb.write(fos);
	}

	/**
	 * This is a reusable method to update the data in new row to Excel file
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcel_NewRow(String sheetname, int rowindex, int cellindex, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Cell c = wb.getSheet(sheetname).createRow(rowindex).createCell(cellindex);
		c.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		wb.write(fos);
	}

	/**
	 * This is a reusable method to close the excel file
	 * 
	 * @throws IOException
	 */
	public void closeExcelFile() throws IOException {
		wb.close();
	}
}
