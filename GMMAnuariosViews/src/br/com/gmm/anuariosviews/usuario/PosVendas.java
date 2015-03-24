package br.com.gmm.anuariosviews.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class PosVendas {

	public static void main(String[] args) {
		Map<String, PosVendasVO> posVenda = null;
		try {
			posVenda =		readXls();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			File fileNew = new File("C:\\GMM\\tmp\\MAILING_POS_VENDAS.xls");
			HSSFWorkbook wbNew = new HSSFWorkbook();
			HSSFSheet sheetNew = wbNew.createSheet("Pos vendas");
			HSSFRow row;
			int rowNum = 0;
			row = sheetNew.createRow((short) rowNum);
			row.createCell(0).setCellValue(new HSSFRichTextString("PACOTE"));
			row.createCell(1).setCellValue(
					new HSSFRichTextString("TIPO DE EMPRESA"));
			row.createCell(2).setCellValue(new HSSFRichTextString("CATEGORIA"));
			row.createCell(3).setCellValue(new HSSFRichTextString("ID AGENCIA"));
			row.createCell(4).setCellValue(	new HSSFRichTextString("NOME FANTASIA"));
			row.createCell(5).setCellValue(
					new HSSFRichTextString("RAZAO SOCIAL"));
			row.createCell(6).setCellValue(new HSSFRichTextString("CNPJ"));
			row.createCell(7).setCellValue(
					new HSSFRichTextString("INSCRICAO ESTADUAL"));
			
			row.createCell(8).setCellValue(
					new HSSFRichTextString("INSCRICAO MUNICIPAL"));
			row.createCell(9).setCellValue(
					new HSSFRichTextString("TIPO LOGRADOURO"));
			row.createCell(10).setCellValue(
					new HSSFRichTextString("LOGRADOURO"));
			row.createCell(11).setCellValue(new HSSFRichTextString("NUMERO"));
			row.createCell(12).setCellValue(
					new HSSFRichTextString("COMPLEMENTO"));
			row.createCell(13).setCellValue(new HSSFRichTextString("EMAIL DA AGENCIA"));
			row.createCell(14).setCellValue(
					new HSSFRichTextString("TELEFONE COM PREFIXO"));
			row.createCell(15).setCellValue(new HSSFRichTextString("CEP"));
			row.createCell(16).setCellValue(new HSSFRichTextString("BAIRRO"));
			row.createCell(17).setCellValue(new HSSFRichTextString("CIDADE"));
			row.createCell(18).setCellValue(new HSSFRichTextString("LINK"));
			row.createCell(19).setCellValue(new HSSFRichTextString("UF"));
			row.createCell(20).setCellValue(new HSSFRichTextString("VENDEDOR"));
			row.createCell(21).setCellValue(
					new HSSFRichTextString("AUTORIZANTE"));
			row.createCell(22).setCellValue(new HSSFRichTextString("EMAIL"));
			row.createCell(23).setCellValue(new HSSFRichTextString("PI"));
			rowNum++;
			
			File fileNew2 = new File("C:\\GMM\\tmp\\MAILING_POS_VENDAS_NAO_ENCONTRADOS.xls");
			HSSFWorkbook wbNew2 = new HSSFWorkbook();
			HSSFSheet sheetNew2 = wbNew2.createSheet("Pos vendas");
			HSSFRow row2;
			int rowNum2 = 0;
			row2 = sheetNew2.createRow((short) rowNum2);
			row2.createCell(0).setCellValue(new HSSFRichTextString("PACOTE"));
			row2.createCell(1).setCellValue(
					new HSSFRichTextString("TIPO DE EMPRESA"));
			row2.createCell(2).setCellValue(new HSSFRichTextString("CATEGORIA"));
			row2.createCell(3).setCellValue(new HSSFRichTextString("ID AGENCIA"));
			row2.createCell(4).setCellValue(	new HSSFRichTextString("NOME FANTASIA"));
			row2.createCell(5).setCellValue(
					new HSSFRichTextString("RAZAO SOCIAL"));
			row2.createCell(6).setCellValue(new HSSFRichTextString("CNPJ"));
			row2.createCell(7).setCellValue(
					new HSSFRichTextString("INSCRICAO ESTADUAL"));
			
			row2.createCell(8).setCellValue(
					new HSSFRichTextString("INSCRICAO MUNICIPAL"));
			row2.createCell(9).setCellValue(
					new HSSFRichTextString("TIPO LOGRADOURO"));
			row2.createCell(10).setCellValue(
					new HSSFRichTextString("LOGRADOURO"));
			row2.createCell(11).setCellValue(new HSSFRichTextString("NUMERO"));
			row2.createCell(12).setCellValue(
					new HSSFRichTextString("COMPLEMENTO"));
			row2.createCell(13).setCellValue(new HSSFRichTextString("EMAIL DA AGENCIA"));
			row2.createCell(14).setCellValue(
					new HSSFRichTextString("TELEFONE COM PREFIXO"));
			row2.createCell(15).setCellValue(new HSSFRichTextString("CEP"));
			row2.createCell(16).setCellValue(new HSSFRichTextString("BAIRRO"));
			row2.createCell(17).setCellValue(new HSSFRichTextString("CIDADE"));
			row2.createCell(18).setCellValue(new HSSFRichTextString("LINK"));
			row2.createCell(19).setCellValue(new HSSFRichTextString("UF"));
			row2.createCell(20).setCellValue(new HSSFRichTextString("VENDEDOR"));
			row2.createCell(21).setCellValue(
					new HSSFRichTextString("AUTORIZANTE"));
			row2.createCell(22).setCellValue(new HSSFRichTextString("EMAIL"));
			row2.createCell(23).setCellValue(new HSSFRichTextString("PI"));
			rowNum2++;
			
			
			
			
			
			
			
			FileInputStream file = new FileInputStream(new File("C:\\GMM\\tmp\\POS VENDA AP2014 DIGITAL.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				String pacote =null;
				String tipoempresa =null;
				String categoria =null;
				String idagencia =null;
				String nomefantasia =null;
				String razaosocial =null;
				String cnpj =null;
				String incEstadual =null;
				String insMunicipal =null;
				String tipoLogradouro =null;
				String logradouro =null;
				String email =null;
				String numero =null;
				String complemento =null;
				String fone =null;
				String cep =null;
				String bairro =null;
				String cidade =null;
				String uf =null;
				String link =null;
				String vendedor =null;
				String autorizante =null;
				String emailAutorizante =null;
				String pi =null;
				
				
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
							pacote =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==1) {
							tipoempresa =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==2) {
							categoria =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==3) {
							idagencia =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==4) {
							nomefantasia=cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==5) {
							razaosocial =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==6) {
							cnpj =cell.getStringCellValue();
						}
						if (cell.getColumnIndex()==7) {
							incEstadual =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==8) {
							insMunicipal =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==9) {
							tipoLogradouro =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==10) {
							logradouro =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==11) {
							numero =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==12) {
							complemento =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==13) {
							email =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==14) {
							fone =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==15) {
							cep =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==16) {
							bairro =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==17) {
							cidade =cell.getStringCellValue();
						}		
						if (cell.getColumnIndex()==18) {
							link =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==19) {
							uf =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==20) {
							vendedor =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==21) {
							autorizante =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==22) {
							emailAutorizante =cell.getStringCellValue();
						}	
						if (cell.getColumnIndex()==23) {
							pi =cell.getStringCellValue();
						}	
						break;
						
					}
				}
				
				PosVendasVO  pvo = posVenda.get(nomefantasia.trim());
				if (null!=pvo) {
					
				
				row = sheetNew.createRow((short) rowNum);
				row.createCell(0).setCellValue(new HSSFRichTextString(pacote));
				row.createCell(1).setCellValue(
						new HSSFRichTextString(tipoempresa));
				row.createCell(2).setCellValue(new HSSFRichTextString(categoria));
				row.createCell(3).setCellValue(new HSSFRichTextString(pvo.getIdAgencia()));
				row.createCell(4).setCellValue(	new HSSFRichTextString(nomefantasia));
				row.createCell(5).setCellValue(
						new HSSFRichTextString(razaosocial));
				row.createCell(6).setCellValue(new HSSFRichTextString(pvo.getCnpj()));
				row.createCell(7).setCellValue(
						new HSSFRichTextString(pvo.getInscricaoEstadual()));
				
				row.createCell(8).setCellValue(
						new HSSFRichTextString(pvo.getInscricaoMunicipal()));
				row.createCell(9).setCellValue(
						new HSSFRichTextString(pvo.getTipoLogradouro()));
				row.createCell(10).setCellValue(
						new HSSFRichTextString(pvo.getLogradouro()));
				row.createCell(11).setCellValue(new HSSFRichTextString(pvo.getNumero()));
				row.createCell(12).setCellValue(
						new HSSFRichTextString(pvo.getComplemento()));
				row.createCell(13).setCellValue(new HSSFRichTextString(pvo.getEmail()));
				row.createCell(14).setCellValue(
						new HSSFRichTextString(pvo.getFone()));
				row.createCell(15).setCellValue(new HSSFRichTextString(pvo.getCep()));
				row.createCell(16).setCellValue(new HSSFRichTextString(pvo.getBairro()));
				row.createCell(17).setCellValue(new HSSFRichTextString(cidade));
				row.createCell(18).setCellValue(new HSSFRichTextString(pvo.getLink()));
				row.createCell(19).setCellValue(new HSSFRichTextString(uf));
				row.createCell(20).setCellValue(new HSSFRichTextString(vendedor));
				row.createCell(21).setCellValue(
						new HSSFRichTextString(autorizante));
				row.createCell(22).setCellValue(new HSSFRichTextString(emailAutorizante));
				row.createCell(23).setCellValue(new HSSFRichTextString(pi));
				rowNum++;
			}else{
				row2 = sheetNew2.createRow((short) rowNum2);
				row2.createCell(0).setCellValue(new HSSFRichTextString(pacote));
				row2.createCell(1).setCellValue(
						new HSSFRichTextString(tipoempresa));
				row2.createCell(2).setCellValue(new HSSFRichTextString(categoria));
				row2.createCell(3).setCellValue(new HSSFRichTextString("ID AGENCIA"));
				row2.createCell(4).setCellValue(	new HSSFRichTextString(nomefantasia));
				row2.createCell(5).setCellValue(
						new HSSFRichTextString(razaosocial));
				row2.createCell(6).setCellValue(new HSSFRichTextString("CNPJ"));
				row2.createCell(7).setCellValue(
						new HSSFRichTextString("INSCRICAO ESTADUAL"));
				
				row2.createCell(8).setCellValue(
						new HSSFRichTextString("INSCRICAO ESTADUAL"));
				row2.createCell(9).setCellValue(
						new HSSFRichTextString("TIPO LOGRADOURO"));
				row2.createCell(10).setCellValue(
						new HSSFRichTextString("LOGRADOURO"));
				row2.createCell(11).setCellValue(new HSSFRichTextString("NUMERO"));
				row2.createCell(12).setCellValue(
						new HSSFRichTextString("COMPLEMNTO"));
				row2.createCell(13).setCellValue(new HSSFRichTextString("EMAIL"));
				row2.createCell(14).setCellValue(
						new HSSFRichTextString("EMAIL DA AGENCIA"));
				row2.createCell(15).setCellValue(new HSSFRichTextString("CEP"));
				row2.createCell(16).setCellValue(new HSSFRichTextString("BAIRRO"));
				row2.createCell(17).setCellValue(new HSSFRichTextString(cidade));
				row2.createCell(18).setCellValue(new HSSFRichTextString("LINK"));
				row2.createCell(19).setCellValue(new HSSFRichTextString(uf));
				row2.createCell(20).setCellValue(new HSSFRichTextString(vendedor));
				row2.createCell(21).setCellValue(
						new HSSFRichTextString(autorizante));
				row2.createCell(22).setCellValue(new HSSFRichTextString(emailAutorizante));
				row2.createCell(23).setCellValue(new HSSFRichTextString(pi));
				rowNum2++;
				
				
			}
				
				
				
				
				
				
				
				
			}
			
			
			
			
			file.close();
			FileOutputStream output, output2;
			output = new FileOutputStream(fileNew);
			output2 = new FileOutputStream(fileNew2);
			wbNew.write(output);
			wbNew2.write(output2);
			output.flush();
			output2.flush();
			output.close();
			output2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String, PosVendasVO> readXls() throws IOException {
		Map<String, PosVendasVO> posVenda = new HashMap<String, PosVendasVO>();
		FileInputStream file = new FileInputStream(new File(
				"C:\\GMM\\tmp\\Agencia.xls"));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		PosVendasVO pv = null;
		while (rowIterator.hasNext()) {
			pv = new PosVendasVO();
			String cnpj = null;
			String idAgencia = null;
			String inscEstadual = null;
			String incMunicipal = null;
			String tipoLogradouro = null;
			String logradouro = null;
			String numero = null;
			String complemento = null;
			String email = null;
			String fone = null;
			String cep = null;
			String bairro = null;
			String link = null;
			String nomeFantasia = null;

			Row row1 = rowIterator.next();
			Iterator<Cell> cellIterator = row1.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (cell.getColumnIndex() == 0) {
						idAgencia = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 1) {
						link = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 2) {
						nomeFantasia = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 3) {
						cnpj = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 4) {
						inscEstadual = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 5) {
						incMunicipal = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 6) {
						tipoLogradouro = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 7) {
						logradouro = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 8) {
						numero = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 9) {
						complemento = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 10) {
						fone = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 11) {
						cep = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 12) {
						bairro = cell.getStringCellValue();
					}
					if (cell.getColumnIndex() == 13) {
						email = cell.getStringCellValue();
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getColumnIndex() == 0) {
						idAgencia =""+ cell.getNumericCellValue();
						idAgencia = idAgencia.replace(".", "");
						if (idAgencia.length()>3) {
							idAgencia = idAgencia.substring(0,idAgencia.length()-1);
						}
						
						System.out.println(idAgencia);
					}
					break;

				}
			}
			pv.setBairro(bairro);
			pv.setCep(cep);
			pv.setCnpj(cnpj);
			pv.setComplemento(complemento);
			pv.setEmail(email);
			pv.setFone(fone);
			pv.setIdAgencia(idAgencia);
			pv.setInscricaoEstadual(inscEstadual);
			pv.setInscricaoMunicipal(incMunicipal);
			pv.setLink(link);
			pv.setLogradouro(logradouro);
			pv.setNumero(numero);
			pv.setTipoLogradouro(tipoLogradouro);
			pv.setNomeFantasia(nomeFantasia.trim());
			posVenda.put(nomeFantasia.trim(), pv);
		}
		return posVenda;
	}
}
