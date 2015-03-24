package br.com.gmm.anuariosviews.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hamcrest.core.IsEqual;

import br.com.gmm.anuariosviews.facade.AnuariosViewsFacadeImpl;

public class UsuarioAnuarios {
public static void main(String[] args) {
	AnuariosViewsFacadeImpl facade = new AnuariosViewsFacadeImpl();
	
	
	
	
	try {
		File fileOk = new File("C:\\GMM\\tmp\\MARCIA_LOCALIZADOS.xls");
		HSSFWorkbook wbOk = new HSSFWorkbook();		
		HSSFSheet sheetOk = wbOk.createSheet("plan1");
		HSSFRow row;
		int rowNum = 0;
		row = sheetOk.createRow((short) rowNum);			
		row.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
		row.createCell(1).setCellValue(new HSSFRichTextString("RAZAO SOCIAL"));
		row.createCell(2).setCellValue(new HSSFRichTextString("NOME FANTASIA"));
		row.createCell(3).setCellValue(new HSSFRichTextString("EMAIL EMPRESA"));
		row.createCell(4).setCellValue(new HSSFRichTextString("TIPO EMPRESA"));
		row.createCell(5).setCellValue(new HSSFRichTextString("NOME EXECUTIVO"));
		row.createCell(8).setCellValue(new HSSFRichTextString("URL"));
		rowNum++;
		
		File fileNOk = new File("C:\\GMM\\tmp\\MARCIA_NAO_LOCALIZADOS.xls");
		HSSFWorkbook wbNOk = new HSSFWorkbook();		
		HSSFSheet sheetNOk = wbNOk.createSheet("plan1");
		HSSFRow row2;
		int rowNum2 = 0;
		row2 = sheetNOk.createRow((short) rowNum2);			
		row2.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
		row2.createCell(1).setCellValue(new HSSFRichTextString("RAZAO SOCIAL"));
		row2.createCell(2).setCellValue(new HSSFRichTextString("NOME FANTASIA"));
		row2.createCell(3).setCellValue(new HSSFRichTextString("EMAIL EMPRESA"));
		row2.createCell(4).setCellValue(new HSSFRichTextString("TIPO EMPRESA"));
		row2.createCell(5).setCellValue(new HSSFRichTextString("NOME EXECUTIVO"));
		row2.createCell(8).setCellValue(new HSSFRichTextString("URL"));
		rowNum2++;
		
		
		

		FileInputStream file = new FileInputStream(new File("C:\\GMM\\tmp\\acaoimbativelmarcia.xls"));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			String cnpj =null;
			String razao =null;
			String nomeFantasia =null;
			String emailempresa =null;
			String tipoEmpresa =null;
			String uf =null;
			String nemoexecutivocompleto =null;
			String nemoexecutivo =null;
			String vendedor =null;
			String pl =null;
			String autorizante =null;
			String email =null;
			int idVeiculo = 0;
			int idEmpresa = 0;
			Row row1 = rowIterator.next();
			Iterator<Cell> cellIterator = row1.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				// case Cell.CELL_TYPE_BOOLEAN:
				// System.out.print(cell.getBooleanCellValue() + "\t\t");
				// break;
				// case Cell.CELL_TYPE_NUMERIC:
				// System.out.print(cell.getNumericCellValue() + "\t\t");
				// break;
				case Cell.CELL_TYPE_STRING:
					if (cell.getColumnIndex()==0) {
						cnpj =cell.getStringCellValue();
					}
					if (cell.getColumnIndex()==1) {
						razao =cell.getStringCellValue();
					}
					if (cell.getColumnIndex()==2) {
						nomeFantasia =cell.getStringCellValue();
					}
					if (cell.getColumnIndex()==3) {
						emailempresa =cell.getStringCellValue();
					}
					if (cell.getColumnIndex()==4) {
						tipoEmpresa =cell.getStringCellValue();
					}
					if (cell.getColumnIndex()==5) {
						nemoexecutivo =cell.getStringCellValue();
					}
							
					break;
					
				}
			}
			
			idEmpresa = facade.getidEmpresaTipoempresa(nomeFantasia, tipoEmpresa);
			if (idEmpresa!=0) {
				
				row = sheetOk.createRow((short) rowNum);	
				row.createCell(0).setCellValue(new HSSFRichTextString(cnpj));
				row.createCell(1).setCellValue(new HSSFRichTextString(razao));
				row.createCell(2).setCellValue(new HSSFRichTextString(nomeFantasia));
				row.createCell(3).setCellValue(new HSSFRichTextString(emailempresa));
				row.createCell(4).setCellValue(new HSSFRichTextString(tipoEmpresa));
				row.createCell(5).setCellValue(new HSSFRichTextString(nemoexecutivo));
				row.createCell(8).setCellValue(new HSSFRichTextString("http://portfoliodeveiculos.meioemensagem.com.br/portfolio/veiculos/"+nomeFantasia.toUpperCase()+"/"+idEmpresa+"/home"));
				rowNum++;
//				facade.insertUsuarioVeiculo(idVeiculo, login, senha);
//				facade.updateTipoPacote(idVeiculo, pacote);
				
//				facade.desativarVeiculo(idVeiculo);
			}else{
				row2 = sheetNOk.createRow((short) rowNum2);	
				row2.createCell(0).setCellValue(new HSSFRichTextString(cnpj));
				row2.createCell(1).setCellValue(new HSSFRichTextString(razao));
				row2.createCell(2).setCellValue(new HSSFRichTextString(nomeFantasia));
				row2.createCell(3).setCellValue(new HSSFRichTextString(emailempresa));
				row2.createCell(4).setCellValue(new HSSFRichTextString(tipoEmpresa));
				row2.createCell(5).setCellValue(new HSSFRichTextString(nemoexecutivo));				
				row2.createCell(8).setCellValue(new HSSFRichTextString(""));
				rowNum2++;
			}
			
		}
		file.close();
		FileOutputStream output;
		output = new FileOutputStream(fileOk);	
		wbOk.write(output);
		output.flush();
		output.close();
		FileOutputStream output2;
		output2 = new FileOutputStream(fileNOk);	
		wbNOk.write(output2);
		output2.flush();
		output2.close();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
}
}
