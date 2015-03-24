package br.com.gmm.anuariosviews.views;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.gmm.anuariosviews.entity.vo.AgenciasVO;
import br.com.gmm.anuariosviews.entity.vo.OportunidadesVO;
import br.com.gmm.anuariosviews.entity.vo.TrabalhosVO;
import br.com.gmm.anuariosviews.entity.vo.VeiculosVO;
import br.com.gmm.anuariosviews.facade.AnuariosViewsFacadeImpl;
import br.com.gmm.anuariosviews.facade.IAnuariosViewsFacade;
import br.com.gmm.core.util.EnviarEmaill;
import br.com.gmm.core.util.GmmDateUtil;
import br.com.gmm.core.util.GmmFunctions;

public class Views implements Job{
	Logger log = Logger.getLogger(Views.class);
	
	public void views(){
		
		
	}
	
	public void viewsAgencias(){
		IAnuariosViewsFacade viewsFacade = new AnuariosViewsFacadeImpl();
		
		List<AgenciasVO> agenciasVOs = viewsFacade.getViewsAgencias();
		List<VeiculosVO> veiculosVOs = viewsFacade.getViewsVeiculos();
		List<TrabalhosVO> trabalhosVOs = viewsFacade.getViewsTrabalhoss();
		List<OportunidadesVO> oportunidadesVOs = viewsFacade.getViewsOportunidades();
		try {
			
			String absoluteDiskPath = getUrl();
			GmmFunctions.apagarArquivo(absoluteDiskPath, "zip");
			GmmFunctions.apagarArquivo(absoluteDiskPath, "xls");
			File file = new File(absoluteDiskPath + "/Views_Anuarios.xls");

			HSSFWorkbook wb = new HSSFWorkbook();
			CellStyle cellStyle = wb.createCellStyle();
			CreationHelper createHelper = wb.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			HSSFSheet sheet = wb.createSheet("VEICULOS");
			HSSFRow row;
			int rowNum = 0;
			row = sheet.createRow((short) rowNum);
			row.createCell(0).setCellValue(new HSSFRichTextString("ID_VEICULO"));
			row.createCell(1).setCellValue(new HSSFRichTextString("NOME_VEICULO"));
			row.createCell(2).setCellValue(new HSSFRichTextString("TIPO_VEICULO"));
			row.createCell(3).setCellValue(new HSSFRichTextString("DATA_CADASTRO_VEICULO"));
			row.createCell(4).setCellValue(new HSSFRichTextString("DATA_ALTERACAO_VEICULO"));
			row.createCell(5).setCellValue(new HSSFRichTextString("TOTAL_VISITA_VEICULO"));
			row.createCell(6).setCellValue(new HSSFRichTextString("TOTAL_OPORTUNIDADES"));
			row.createCell(7).setCellValue(new HSSFRichTextString("TOTAL_VISITAS_OPORTUNIDADES"));
			row.createCell(8).setCellValue(new HSSFRichTextString("TIPO_EMPRESA"));
			row.createCell(9).setCellValue(new HSSFRichTextString("UF"));
			rowNum++;

			for (VeiculosVO vo : veiculosVOs) {
				row = sheet.createRow(rowNum);				
				row.createCell(0).setCellValue(new HSSFRichTextString(""+vo.getIdVeiculo()));
				row.createCell(1).setCellValue(new HSSFRichTextString(""+vo.getNomeVeiculo()));
				row.createCell(2).setCellValue(new HSSFRichTextString(""+vo.getTipoVeiculo()));
//				row.createCell(3).setCellValue(new HSSFRichTextString(""+vo.getDtCadastroVeiculo()));
				
				
				Cell cell = row.createCell(3);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtCadastroVeiculo()))
				cell.setCellValue(GmmDateUtil.converteString2Date(vo.getDtCadastroVeiculo()));				
				cell.setCellStyle(cellStyle);				
//				row.createCell(4).setCellValue(new HSSFRichTextString(""+vo.getDtAlteracaoVeiculo()));
				Cell cell4 = row.createCell(4);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtAlteracaoVeiculo()))
				cell4.setCellValue(GmmDateUtil.converteString2Date(vo.getDtAlteracaoVeiculo()));
				cell4.setCellStyle(cellStyle);
				row.createCell(5).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasVeiculo()));
				row.createCell(6).setCellValue(new HSSFRichTextString(""+vo.getTotalOportunidade()));
				row.createCell(7).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasOportunidade()));				
				row.createCell(8).setCellValue(new HSSFRichTextString(""+vo.getTipoEmpresa()));
				row.createCell(9).setCellValue(new HSSFRichTextString(""+vo.getUf()));
				rowNum++;
			}
			HSSFSheet sheet2 = wb.createSheet("AGÊNCIAS");
			HSSFRow row2;
			int rowNum2 = 0;
			row2 = sheet2.createRow((short) rowNum2);
			row2.createCell(0).setCellValue(new HSSFRichTextString("ID_AGENCIA"));
			row2.createCell(1).setCellValue(new HSSFRichTextString("NOME_AGENCIA"));
			row2.createCell(2).setCellValue(new HSSFRichTextString("TIPO_AGENCIA"));
			row2.createCell(3).setCellValue(new HSSFRichTextString("DATA_CADASTRO_AGENCIA"));
			row2.createCell(4).setCellValue(new HSSFRichTextString("DATA_ALTERACAO_AGENCIA"));
			row2.createCell(5).setCellValue(new HSSFRichTextString("TOTAL_VISITA_AGENCIA"));
			row2.createCell(6).setCellValue(new HSSFRichTextString("TOTAL_TRABALHOS"));
			row2.createCell(7).setCellValue(new HSSFRichTextString("TOTAL_VISITAS_TRABALHOS"));
			row.createCell(8).setCellValue(new HSSFRichTextString("TIPO_EMPRESA"));
			row.createCell(9).setCellValue(new HSSFRichTextString("UF"));
			rowNum2++;
			for (AgenciasVO vo : agenciasVOs) {
				row2 = sheet2.createRow(rowNum2);				
				row2.createCell(0).setCellValue(new HSSFRichTextString(""+vo.getIdAgencia()));
				row2.createCell(1).setCellValue(new HSSFRichTextString(""+vo.getNomeAgencia()));
				row2.createCell(2).setCellValue(new HSSFRichTextString(""+vo.getTipoAgencia()));
//				row2.createCell(3).setCellValue(new HSSFRichTextString(""+vo.getDtCadastroAgencia()));
				Cell cellAg = row2.createCell(3);
				
				if(null!=GmmDateUtil.converteString2Date(vo.getDtCadastroAgencia()))
				cellAg.setCellValue(GmmDateUtil.converteString2Date(vo.getDtCadastroAgencia()));
				cellAg.setCellStyle(cellStyle);
//				row2.createCell(4).setCellValue(new HSSFRichTextString(""+vo.getDtAlteracaoAgencia()));
				Cell cellAg2 = row2.createCell(4);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtAlteracaoAgencia()))
				cellAg2.setCellValue(GmmDateUtil.converteString2Date(vo.getDtAlteracaoAgencia()));
				cellAg2.setCellStyle(cellStyle);
				
				row2.createCell(5).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasAgencia()));
				row2.createCell(6).setCellValue(new HSSFRichTextString(""+vo.getTotalTrabalhos()));
				row2.createCell(7).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasTrabalho()));	
				row2.createCell(8).setCellValue(new HSSFRichTextString(""+vo.getTipoEmpresa()));
				row2.createCell(9).setCellValue(new HSSFRichTextString(""+vo.getUf()));
				rowNum2++;
			}
			HSSFSheet sheet3 = wb.createSheet("TRABALHOS");
			HSSFRow row3;
			int rowNum3 = 0;
			row3 = sheet3.createRow((short) rowNum3);
			row3.createCell(0).setCellValue(new HSSFRichTextString("ID_AGENCIA"));
			row3.createCell(1).setCellValue(new HSSFRichTextString("ID_CAMPANHA"));
			row3.createCell(2).setCellValue(new HSSFRichTextString("TITULO"));
			row3.createCell(3).setCellValue(new HSSFRichTextString("NOME_AGENCIA"));
			row3.createCell(4).setCellValue(new HSSFRichTextString("TIPO_AGENCIA"));
			row3.createCell(5).setCellValue(new HSSFRichTextString("DATA_CADASTRO_AGENCIA"));
			row3.createCell(6).setCellValue(new HSSFRichTextString("DATA_ALTERACAO_AGENCIA"));
			row3.createCell(7).setCellValue(new HSSFRichTextString("TOTAL_VISITA_AGENCIA"));
			row3.createCell(8).setCellValue(new HSSFRichTextString("TOTAL_TRABALHOS"));
			row3.createCell(9).setCellValue(new HSSFRichTextString("TOTAL_VISITAS_TRABALHOS"));
			row3.createCell(10).setCellValue(new HSSFRichTextString("TIPO_EMPRESA"));
			row3.createCell(11).setCellValue(new HSSFRichTextString("UF"));
			rowNum3++;
			for (TrabalhosVO vo : trabalhosVOs) {
				row3 = sheet3.createRow(rowNum3);				
				row3.createCell(0).setCellValue(new HSSFRichTextString(""+vo.getIdAgencia()));
				row3.createCell(1).setCellValue(new HSSFRichTextString(""+vo.getIdCampanha()));
				row3.createCell(2).setCellValue(new HSSFRichTextString(""+vo.getCampanha()));
				row3.createCell(3).setCellValue(new HSSFRichTextString(vo.getNomeAgencia()!=null?""+vo.getNomeAgencia():""));
				row3.createCell(4).setCellValue(new HSSFRichTextString(vo.getTipoAgencia()!=null?""+vo.getTipoAgencia():""));
//				row3.createCell(5).setCellValue(new HSSFRichTextString(""+vo.getDtCadastroAgencia()));
				Cell cellTra = row3.createCell(5);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtCadastroAgencia()))
				cellTra.setCellValue(GmmDateUtil.converteString2Date(vo.getDtCadastroAgencia()));
				cellTra.setCellStyle(cellStyle);
				
//				row3.createCell(6).setCellValue(new HSSFRichTextString(""+vo.getDtAlteracaoAgencia()));
				Cell cellTra2 = row3.createCell(6);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtAlteracaoAgencia()))
				cellTra2.setCellValue(GmmDateUtil.converteString2Date(vo.getDtAlteracaoAgencia()));
				cellTra2.setCellStyle(cellStyle);
				row3.createCell(7).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasAgencia()));
				row3.createCell(8).setCellValue(new HSSFRichTextString(""+vo.getTotalTrabalhos()));
				row3.createCell(9).setCellValue(new HSSFRichTextString(""+vo.getTotalVisitasTrabalho()));			
				row3.createCell(10).setCellValue(new HSSFRichTextString(""+vo.getTipoEmpresa()));
				row3.createCell(11).setCellValue(new HSSFRichTextString(""+vo.getUf()));			
				rowNum3++;
			}
			
			HSSFSheet sheet4 = wb.createSheet("OPORTUNIDADES");
			HSSFRow row4;
			int rowNum4 = 0;
			row4 = sheet4.createRow((short) rowNum4);
			row4.createCell(0).setCellValue(new HSSFRichTextString("NOME_VEICULO"));
			row4.createCell(1).setCellValue(new HSSFRichTextString("NOME_OPORTUNIDADE"));
			row4.createCell(2).setCellValue(new HSSFRichTextString("TOTAL_VIEWS"));
			row4.createCell(3).setCellValue(new HSSFRichTextString("DATA_CADASTRO"));
			row4.createCell(4).setCellValue(new HSSFRichTextString("DATA_VALIDADE"));
			row4.createCell(5).setCellValue(new HSSFRichTextString("TIPO_EMPRESA"));
			row4.createCell(6).setCellValue(new HSSFRichTextString("UF"));
			rowNum4++;
			for (OportunidadesVO vo : oportunidadesVOs) {
				row4 = sheet4.createRow(rowNum4);				
				row4.createCell(0).setCellValue(new HSSFRichTextString(""+vo.getNomeVeiculo()));
				row4.createCell(1).setCellValue(new HSSFRichTextString(""+vo.getNomeOportunidade()));
				row4.createCell(2).setCellValue(new HSSFRichTextString(""+vo.getTotalViews()));
//				row4.createCell(3).setCellValue(new HSSFRichTextString(""+vo.getDtCadastro()));
				Cell cellOpo = row4.createCell(3);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtCadastro()))
				cellOpo.setCellValue(GmmDateUtil.converteString2Date(vo.getDtCadastro()));
				cellOpo.setCellStyle(cellStyle);
//				row4.createCell(4).setCellValue(new HSSFRichTextString(""+vo.getDtValidade()));	
				Cell cellOpo2 = row4.createCell(4);
				if(null!=GmmDateUtil.converteString2Date(vo.getDtValidade()))
				cellOpo2.setCellValue(GmmDateUtil.converteString2Date(vo.getDtValidade()));
				cellOpo2.setCellStyle(cellStyle);
				row4.createCell(5).setCellValue(new HSSFRichTextString(""+vo.getTipoEmpresa()));
				row4.createCell(6).setCellValue(new HSSFRichTextString(""+vo.getUf()));
				rowNum4++;
			}
			
			

			FileOutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.flush();
			output.close();
			EnviarEmaill email = new EnviarEmaill("Relatórios de Views do Anuários");
			email.enviaEmail("Relatórios de Views do Anuários", "portfoliodeveiculos@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
			email.enviaEmail("Relatórios de Views do Anuários", "portfoliodeagencias@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
			email.enviaEmail("Relatórios de Views do Anuários", "digital@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
			email.enviaEmail("Relatórios de Views do Anuários", "dev@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
//			email.enviaEmail("Relatórios de Views do Anuários", "gleite@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
//			email.enviaEmail("Relatórios de Views do Anuários", "rlemes@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
//			email.enviaEmail("Relatórios de Views do Anuários", "fsimoes@grupomm.com.br", "Segue anexo o relatório semanal de Views do Anuários", absoluteDiskPath + "/Views_Anuarios.xls", "Views_Anuarios.xls");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		viewsAgencias();
		
	}
	public static void main(String[] args) {
		Views views = new Views();
		
		
		views.viewsAgencias();
	}
	private static String getUrl(){
		String pool = System.getenv("HOMEDRIVE")+File.separatorChar+"GMM"+File.separatorChar+"tmp"+File.separatorChar;		
		return pool;		
	}
}
