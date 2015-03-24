package br.com.gmm.anuariosviews.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javassist.expr.NewArray;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.gmm.anuariosviews.entity.vo.CenpVO;
import br.com.gmm.anuariosviews.facade.AnuariosViewsFacadeImpl;

public class Cenp {
	public static void main(String[] args) {
		AnuariosViewsFacadeImpl facade = new AnuariosViewsFacadeImpl();
		List<String> cnpjs = new ArrayList<String>();
		List<String> cnpjs2 = new ArrayList<String>();
		
		
		
		try {
			File fileOk = new File("C:\\GMM\\tmp\\CENP_CONSTA_NO_MAILING_E_NA_BASE_ANUARIOS.xls");
			HSSFWorkbook wbOk = new HSSFWorkbook();		
			HSSFSheet sheetOk = wbOk.createSheet("plan1");
			HSSFRow row;
			int rowNum = 0;
			row = sheetOk.createRow((short) rowNum);			
			row.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
			row.createCell(1).setCellValue(new HSSFRichTextString("RAZAOSOCIAL"));
			row.createCell(2).setCellValue(new HSSFRichTextString("NOMEFANTASIA"));
			row.createCell(3).setCellValue(new HSSFRichTextString("EMAIL_EMPRESA"));			
			row.createCell(4).setCellValue(new HSSFRichTextString("SEGMENTO"));
			row.createCell(5).setCellValue(new HSSFRichTextString("TIPO EMPRESA"));
			row.createCell(6).setCellValue(new HSSFRichTextString("TIPO LOGARDOURO"));
			row.createCell(7).setCellValue(new HSSFRichTextString("LOGARDOURO"));
			row.createCell(8).setCellValue(new HSSFRichTextString("NUMERO"));
			row.createCell(9).setCellValue(new HSSFRichTextString("COMPLEMENTO"));
			row.createCell(10).setCellValue(new HSSFRichTextString("BAIRRO"));
			row.createCell(11).setCellValue(new HSSFRichTextString("CIDADE"));
			row.createCell(12).setCellValue(new HSSFRichTextString("UF"));			
			row.createCell(13).setCellValue(new HSSFRichTextString("CEP"));
			row.createCell(14).setCellValue(new HSSFRichTextString("TELEFONE"));
			rowNum++;
			
			File fileNOk = new File("C:\\GMM\\tmp\\CENP_CONSTA_NO_MAILING_E_NAO_NA_BASE_ANUARIOS.xls");
			HSSFWorkbook wbNOk = new HSSFWorkbook();		
			HSSFSheet sheetNOk = wbNOk.createSheet("plan1");
			HSSFRow row2;
			int rowNum2 = 0;
			row2 = sheetNOk.createRow((short) rowNum2);			
			row2.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
			row2.createCell(1).setCellValue(new HSSFRichTextString("RAZAOSOCIAL"));
			row2.createCell(2).setCellValue(new HSSFRichTextString("NOMEFANTASIA"));
			row2.createCell(3).setCellValue(new HSSFRichTextString("NOME_CONTATO"));
			row2.createCell(4).setCellValue(new HSSFRichTextString("EMAIL"));
			row2.createCell(5).setCellValue(new HSSFRichTextString("TELEFONE"));
			row2.createCell(6).setCellValue(new HSSFRichTextString("ENDEREÇO"));
			row2.createCell(7).setCellValue(new HSSFRichTextString("CEP"));
			row2.createCell(8).setCellValue(new HSSFRichTextString("CIDADE/ESTADO"));
			row2.createCell(9).setCellValue(new HSSFRichTextString("GRUPO"));
			rowNum2++;
			
			
			File fileOk3 = new File("C:\\GMM\\tmp\\CENP_NAO_CONSTA_NO_MAILING_E_CONSTA_NA_BASE_ANUARIOS.xls");
			HSSFWorkbook wbOk3 = new HSSFWorkbook();		
			HSSFSheet sheetOk3 = wbOk3.createSheet("plan1");
			HSSFRow row3;
			int rowNum3 = 0;
			row3 = sheetOk3.createRow((short) rowNum3);			
			row3.createCell(0).setCellValue(new HSSFRichTextString("CNPJ"));
			row3.createCell(1).setCellValue(new HSSFRichTextString("RAZAOSOCIAL"));
			row3.createCell(2).setCellValue(new HSSFRichTextString("NOMEFANTASIA"));
			row3.createCell(3).setCellValue(new HSSFRichTextString("EMAIL_EMPRESA"));			
			row3.createCell(4).setCellValue(new HSSFRichTextString("SEGMENTO"));
			row3.createCell(5).setCellValue(new HSSFRichTextString("TIPO EMPRESA"));
			row3.createCell(6).setCellValue(new HSSFRichTextString("TIPO LOGARDOURO"));
			row3.createCell(7).setCellValue(new HSSFRichTextString("LOGARDOURO"));
			row3.createCell(8).setCellValue(new HSSFRichTextString("NUMERO"));
			row3.createCell(9).setCellValue(new HSSFRichTextString("COMPLEMENTO"));
			row3.createCell(10).setCellValue(new HSSFRichTextString("BAIRRO"));
			row3.createCell(11).setCellValue(new HSSFRichTextString("CIDADE"));
			row3.createCell(12).setCellValue(new HSSFRichTextString("UF"));			
			row3.createCell(13).setCellValue(new HSSFRichTextString("CEP"));
			row3.createCell(14).setCellValue(new HSSFRichTextString("TELEFONE"));
			row3.createCell(15).setCellValue(new HSSFRichTextString("PORTE"));
			rowNum3++;
			
			
			

			FileInputStream file = new FileInputStream(new File("C:\\GMM\\tmp\\cenp.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				String cnpj =null;
				String razao =null;
				String nomeFantasia =null;
				String emailempresa =null;
				String telefone =null;
				String endereco =null;
				String cep =null;
				String cidaedEstado =null;
				String contato =null;
				String grupo =null;
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
						
						if (cell.getColumnIndex()==1) {
							razao =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==2) {
							nomeFantasia =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==3) {
							grupo =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==4) {
							contato =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==5) {
							emailempresa =cell.getStringCellValue();
						}
						
						if (cell.getColumnIndex()==6) {
							telefone =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==7) {
							endereco =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==8) {
							cep =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==9) {
							cidaedEstado =cell.getStringCellValue();
						}	
						break;
						
					case Cell.CELL_TYPE_NUMERIC:
						if (cell.getColumnIndex()==0) {
							cnpj =""+String.format ("%014d", (long)cell.getNumericCellValue());													
							cnpjs.add(cnpj);
							cnpjs2.add(cnpj.substring(0, 8));
						}
						if (cell.getColumnIndex()==6) {
							telefone =""+String.format ("%010d", (long)cell.getNumericCellValue());
						}
						if (cell.getColumnIndex()==3) {
							grupo =""+String.format ("%01d", (long)cell.getNumericCellValue());
						}
						if (cell.getColumnIndex()==8) {
							cep =""+String.format ("%08d", (long)cell.getNumericCellValue());
						}
						
					}
				}
				CenpVO cenpvo=null;
				List<CenpVO> list = new ArrayList<CenpVO>();
				if(null!=cnpj&&cnpj.length()>7)	{
//					cnpj = cnpj.substring(0, 8);
					list = facade.getCenp(cnpj.substring(0, 8));
				}
				if(list.isEmpty()){
					row2 = sheetNOk.createRow((short) rowNum2);	
					row2.createCell(0).setCellValue(new HSSFRichTextString(cnpj));
					row2.createCell(1).setCellValue(new HSSFRichTextString(razao));
					row2.createCell(2).setCellValue(new HSSFRichTextString(nomeFantasia));
					row2.createCell(3).setCellValue(new HSSFRichTextString(contato));
					row2.createCell(4).setCellValue(new HSSFRichTextString(emailempresa));
					row2.createCell(5).setCellValue(new HSSFRichTextString(telefone));
					row2.createCell(6).setCellValue(new HSSFRichTextString(endereco));
					row2.createCell(7).setCellValue(new HSSFRichTextString(cep));
					row2.createCell(8).setCellValue(new HSSFRichTextString(cidaedEstado));
					row2.createCell(9).setCellValue(new HSSFRichTextString(grupo));
					rowNum2++;					
				}
				for (CenpVO cenpVO2 : list) {
					cenpvo = cenpVO2;					
				if (cenpvo!=null) {					
					row = sheetOk.createRow((short) rowNum);	
					row.createCell(0).setCellValue(new HSSFRichTextString(cenpvo.getCnpj()));
					row.createCell(1).setCellValue(new HSSFRichTextString(cenpvo.getRazaoSocial()));
					row.createCell(2).setCellValue(new HSSFRichTextString(cenpvo.getNomeFantasia()));
					row.createCell(3).setCellValue(new HSSFRichTextString(cenpvo.getEmail()));
					row.createCell(4).setCellValue(new HSSFRichTextString(cenpvo.getTipoSegmento()));
					row.createCell(5).setCellValue(new HSSFRichTextString(cenpvo.getTipoDeEmpresa()));
					row.createCell(6).setCellValue(new HSSFRichTextString(cenpvo.getTipoLogradouro()));
					row.createCell(7).setCellValue(new HSSFRichTextString(cenpvo.getLogradouro()));
					row.createCell(8).setCellValue(new HSSFRichTextString(cenpvo.getNumero()));
					row.createCell(9).setCellValue(new HSSFRichTextString(cenpvo.getComplemento()));
					row.createCell(10).setCellValue(new HSSFRichTextString(cenpvo.getBairro()));
					row.createCell(11).setCellValue(new HSSFRichTextString(cenpvo.getCidade()));
					row.createCell(12).setCellValue(new HSSFRichTextString(cenpvo.getUf()));
					row.createCell(13).setCellValue(new HSSFRichTextString(cenpvo.getCep()));
					row.createCell(14).setCellValue(new HSSFRichTextString(cenpvo.getTelefone()));
					row.createCell(14).setCellValue(new HSSFRichTextString(cenpvo.getPorteEmpresa()));
					rowNum++;
				}else{
					row2 = sheetNOk.createRow((short) rowNum2);	
					row2.createCell(0).setCellValue(new HSSFRichTextString(cnpj));
					row2.createCell(1).setCellValue(new HSSFRichTextString(razao));
					row2.createCell(2).setCellValue(new HSSFRichTextString(nomeFantasia));
					row2.createCell(3).setCellValue(new HSSFRichTextString(contato));
					row2.createCell(4).setCellValue(new HSSFRichTextString(emailempresa));
					row2.createCell(5).setCellValue(new HSSFRichTextString(telefone));
					row2.createCell(6).setCellValue(new HSSFRichTextString(endereco));
					row2.createCell(7).setCellValue(new HSSFRichTextString(cep));
					row2.createCell(8).setCellValue(new HSSFRichTextString(cidaedEstado));
					row2.createCell(9).setCellValue(new HSSFRichTextString(grupo));
					rowNum2++;					
				}
				}
				
			}
			
			List<CenpVO> cenps = facade.getCenps(cnpjs);
			
			/*
			boolean inserir = false;
			for (CenpVO vo:new ArrayList<CenpVO>(cenps)){
				for (String cn : cnpjs) {					
					if(!(cn.length()>7 && vo.getCnpj().length()>7 && cn.contains(vo.getCnpj().substring(0, 8)))){
//						cenps.remove(vo);
						inserir = true;
						cenps2.add(vo);
					}
				}
			}
			
			*/
			for (CenpVO cenpvo : cenps) {				
				if (!cnpjs2.contains(cenpvo.getCnpj().substring(0, 8))) {
				row3 = sheetOk3.createRow((short) rowNum3);	
				row3.createCell(0).setCellValue(new HSSFRichTextString(cenpvo.getCnpj()));
				row3.createCell(1).setCellValue(new HSSFRichTextString(cenpvo.getRazaoSocial()));
				row3.createCell(2).setCellValue(new HSSFRichTextString(cenpvo.getNomeFantasia()));
				row3.createCell(3).setCellValue(new HSSFRichTextString(cenpvo.getEmail()));
				row3.createCell(4).setCellValue(new HSSFRichTextString(cenpvo.getTipoSegmento()));
				row3.createCell(5).setCellValue(new HSSFRichTextString(cenpvo.getTipoDeEmpresa()));
				row3.createCell(6).setCellValue(new HSSFRichTextString(cenpvo.getTipoLogradouro()));
				row3.createCell(7).setCellValue(new HSSFRichTextString(cenpvo.getLogradouro()));
				row3.createCell(8).setCellValue(new HSSFRichTextString(cenpvo.getNumero()));
				row3.createCell(9).setCellValue(new HSSFRichTextString(cenpvo.getComplemento()));
				row3.createCell(10).setCellValue(new HSSFRichTextString(cenpvo.getBairro()));
				row3.createCell(11).setCellValue(new HSSFRichTextString(cenpvo.getCidade()));
				row3.createCell(12).setCellValue(new HSSFRichTextString(cenpvo.getUf()));
				row3.createCell(13).setCellValue(new HSSFRichTextString(cenpvo.getCep()));
				row3.createCell(14).setCellValue(new HSSFRichTextString(cenpvo.getTelefone()));
				row3.createCell(15).setCellValue(new HSSFRichTextString(cenpvo.getPorteEmpresa()));
				rowNum3++;
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
			
			
			FileOutputStream output3;
			output3 = new FileOutputStream(fileOk3);	
			wbOk3.write(output3);
			output3.flush();
			output3.close();
			System.out.println("Finalizado!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
