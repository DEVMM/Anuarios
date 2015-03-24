package br.com.gmm.anuariosviews.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.gmm.anuariosviews.facade.AnuariosViewsFacadeImpl;

public class AdicionarCenp {
public static void main(String[] args) throws IOException {
	AnuariosViewsFacadeImpl facade = new AnuariosViewsFacadeImpl();
	FileInputStream file = new FileInputStream(new File("C:\\GMM\\tmp\\cenp2.xls"));
	int total=0, totalNad = 0;
	
	File fileOk = new File("C:\\GMM\\tmp\\NAO_CONSTA_NO_SEGMENTO_2.xls");
	HSSFWorkbook wbOk = new HSSFWorkbook();		
	HSSFSheet sheetOk = wbOk.createSheet("plan1");
	HSSFRow row;
	int rowNum = 0;
	row = sheetOk.createRow((short) rowNum);			
	row.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
	row.createCell(1).setCellValue(new HSSFRichTextString("RAZAOSOCIAL"));
	row.createCell(2).setCellValue(new HSSFRichTextString("NOMEFANTASIA"));
	rowNum++;
	
	HSSFWorkbook workbook = new HSSFWorkbook(file);
	HSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	while (rowIterator.hasNext()) {
		total++;
		String cnpj =null;
		String nomeFantasia =null;
		String razaoSocial =null;
		Row row1 = rowIterator.next();
		Iterator<Cell> cellIterator = row1.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				if (cell.getColumnIndex()==0) {
					cnpj =cell.getStringCellValue();
					break;
				}
				if (cell.getColumnIndex()==2) {
					nomeFantasia =cell.getStringCellValue();
					break;
				}
				if (cell.getColumnIndex()==1) {
					razaoSocial =cell.getStringCellValue();
					break;
				}
			}		
			
		}
		if(facade.adicionaCenp(cnpj)){
			System.out.println(cnpj+" adicionado");			
		}else{
			System.out.println(cnpj+" não adicionado");
			totalNad++;
			row = sheetOk.createRow((short) rowNum);	
			row.createCell(0).setCellValue(new HSSFRichTextString(cnpj));
			row.createCell(1).setCellValue(new HSSFRichTextString(razaoSocial));
			row.createCell(2).setCellValue(new HSSFRichTextString(nomeFantasia));
			rowNum++;
		}		
	}
	System.out.println("Total = "+total);
	System.out.println("Total não adicionados = "+totalNad);
	file.close();
	FileOutputStream output;
	output = new FileOutputStream(fileOk);	
	wbOk.write(output);
	output.flush();
	output.close();
}
}
