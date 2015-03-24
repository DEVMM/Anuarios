package br.com.gmm.anuariosviews.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.gmm.anuariosviews.entity.vo.AgenciasVO;
import br.com.gmm.anuariosviews.entity.vo.CenpVO;
import br.com.gmm.anuariosviews.entity.vo.OportunidadesVO;
import br.com.gmm.anuariosviews.entity.vo.TrabalhosVO;
import br.com.gmm.anuariosviews.entity.vo.VeiculosVO;
import br.com.gmm.anuariosviews.session.HibernateSessionFactory;
import br.com.gmm.core.exception.GmmFacadeException;
import br.com.gmm.core.exception.GmmSqlException;
import br.com.gmm.core.util.GmmDateUtil;

public class AnuariosViewsDAOImpl implements IAnuariosViewsDAO {

	@Override
	public List<VeiculosVO> getViewsVeiculos() throws GmmFacadeException,
			GmmSqlException {
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		StringBuilder sb = new StringBuilder();		
		sb.append("select vei.idempresa as idveiculo, ete.nomefantasia as nome_veiculo, vei.tipopacote as tipo_veiculo, ");
		sb.append("to_char(vei.datacadastro, 'dd/mm/yyyy hh24:mi:ss') as data_cadastro_veiculo, ");
		sb.append("case when vei.dataalteracao is null then to_char(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("when ui.dataalteracao > vei.dataalteracao then to_char(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("else to_char(vei.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') end as data_alteracao_veiculo, ");
		sb.append("(select count ( * ) from ami_veiculovisita vis where vis.idveiculo = vei.idempresa) as total_visitas_veiculo, ");
		sb.append("(select count ( * ) from ami_oportunidade opt where opt.idveiculo = vei.idempresa) as total_oportunidades, ");
		sb.append("(select count ( * ) from ami_oportunidadevisita where idoportunidade in (select id from ami_oportunidade where idveiculo = vei.idempresa)) as total_visitas_oportunidade, ");
		sb.append("ete.idtipoempresa AS tipo_empresa,  cidade.uf ");
		sb.append("from empresatipoempresa ete ");
		sb.append("inner join ami_veiculo vei on ete.idempresa = vei.idempresa ");
		sb.append("left join ami_veiculoimagemui ui on ete.idempresa = ui.idveiculo ");
		sb.append("LEFT JOIN cep INNER JOIN cidade ON cep.idcidade = cidade.idcidade ON cep.idcep = ete.idendereco ");
		sb.append("where ete.ativo = 1 ");
		sb.append("group by ete.nomefantasia, vei.idempresa, vei.tipopacote, vei.datacadastro, vei.dataalteracao, ui.dataalteracao,  ete.idtipoempresa,  cidade.uf ");
		sb.append("order by vei.idempresa ");
		
		List<VeiculosVO> veiculosVOs = new ArrayList<VeiculosVO>();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		for (Object rows : sqlQuery.list()) {
			VeiculosVO vo = new VeiculosVO();
			Object[] row = (Object[]) rows;
			java.math.BigDecimal idVeiculo = (java.math.BigDecimal) row[0];	
			String nomeVeiculo = (String) row[1];
			String tipoVeiculo = (String) row[2];
			String dtCadastroVeiculo =   GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String)  row[3]));
			String dtAlteracaoVeiculo =   GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String)  row[4]));
			java.math.BigDecimal totalVisitasVeiculo = (java.math.BigDecimal) row[5];	
			java.math.BigDecimal totalOportunidade= (java.math.BigDecimal) row[6];
			java.math.BigDecimal totalVisitasOportunidades = (java.math.BigDecimal) row[7];
			vo.setDtAlteracaoVeiculo(dtAlteracaoVeiculo);
			vo.setDtCadastroVeiculo(dtCadastroVeiculo);
			vo.setIdVeiculo(idVeiculo.intValue());
			vo.setNomeVeiculo(nomeVeiculo);
			vo.setTipoVeiculo(tipoVeiculo);
			vo.setTotalOportunidade(totalOportunidade.intValue());
			vo.setTotalVisitasOportunidade(totalVisitasOportunidades.intValue());
			vo.setTotalVisitasVeiculo(totalVisitasVeiculo.intValue());
			vo.setTipoEmpresa((String) row[8]);
			vo.setUf((String) row[9]);
			veiculosVOs.add(vo);
		}
		HibernateSessionFactory.closeSession(session);
		return veiculosVOs;
	}

	@Override
	public List<AgenciasVO> getViewsAgencias() throws GmmFacadeException,
			GmmSqlException {
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		StringBuilder sb = new StringBuilder();		
		
		sb.append("select apo.idagencia, ete.nomefantasia as nome_agencia, apo.tipoagencia as tipo_agencia, ");
		sb.append("to_char(apo.datacadastro, 'dd/mm/yyyy hh24:mi:ss') as data_cadastro_agencia, ");
		sb.append("case when apo.dataalteracao is null then to_char(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("when ui.dataalteracao > apo.dataalteracao then to_char(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("else to_char(apo.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') end as data_alteracao_agencia, ");
		sb.append("(select count ( * ) from apo_agenciapropagandavisita vis where vis.idagencia = apo.idagencia) as total_visitas_agencia, ");
		sb.append("(select count ( * ) from apo_agenciatrabalho tra where tra.idagencia = apo.idagencia) as total_trabalhos, ");
		sb.append("(select count ( * ) from apo_agenciatrabalhovisita where idtrabalho in (select id from apo_agenciatrabalho where idagencia = apo.idagencia)) as total_visitas_trabalho, ");
		sb.append("ete.idtipoempresa as tipo_empresa, cidade.uf ");
		sb.append("from empresatipoempresa ete ");
		sb.append("inner join empresaagencia ea on ete.idempresa = ea.idempresa ");		
		sb.append("inner join apo_agenciapropagandaonline apo on ete.idempresa = apo.idagencia ");
		sb.append("left join apo_agenciaconfiguracaosite ui on ui.idagencia = ete.idempresa ");
		sb.append("left join cep inner join cidade on cep.idcidade = cidade.idcidade on cep.idcep = ete.idendereco ");
		sb.append("where ete.ativo = 1 ");
		sb.append("and ea.visivelonline = 1 ");
		sb.append("group by ete.nomefantasia, apo.idagencia, apo.tipoagencia, apo.datacadastro, apo.dataalteracao, ui.dataalteracao, ete.idtipoempresa,  cidade.uf ");
		sb.append("order by apo.idagencia ");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		List<AgenciasVO> list = new ArrayList<AgenciasVO>();
		for (Object rows : sqlQuery.list()) {
			AgenciasVO vo = new AgenciasVO();
			Object[] row = (Object[]) rows;
			java.math.BigDecimal idVeiculo = (java.math.BigDecimal) row[0];	
			String nomeAgencia = (String) row[1];
			String tipoAgencia = (String) row[2];
			String dtCadastroAgencia =  GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String) row[3]));
			String dtAlteracaoAgencia =   GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String) row[4]));
			java.math.BigDecimal totalVisitasAgencia = (java.math.BigDecimal) row[5];	
			java.math.BigDecimal totalTrabalhos= (java.math.BigDecimal) row[6];
			java.math.BigDecimal totalVisitasTrabalhos = (java.math.BigDecimal) row[7];
			vo.setDtAlteracaoAgencia(dtAlteracaoAgencia);
			vo.setDtCadastroAgencia(dtCadastroAgencia);
			vo.setIdAgencia(idVeiculo.intValue());
			vo.setNomeAgencia(nomeAgencia);
			vo.setTipoAgencia(tipoAgencia);
			vo.setTotalTrabalhos(totalTrabalhos.intValue());
			vo.setTotalVisitasAgencia(totalVisitasAgencia.intValue());
			vo.setTotalVisitasTrabalho(totalVisitasTrabalhos.intValue());
			vo.setTipoEmpresa((String) row[8]);
			vo.setUf((String) row[9]);
			list.add(vo);
		}
		HibernateSessionFactory.closeSession(session);
		return list;
	}

	

	@Override
	public List<TrabalhosVO> getViewsTrabalhoss() throws GmmFacadeException,
			GmmSqlException {
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT apo.idagencia,  ags.id AS ID_CAMPANHA,  ags.titulo,  ete.nomefantasia AS nome_agencia,  apo.tipoagencia  AS tipo_agencia, ");
		sb.append("TO_CHAR(apo.datacadastro, 'dd/mm/yyyy hh24:mi:ss') AS data_cadastro_agencia, ");
		sb.append("CASE   WHEN apo.dataalteracao IS NULL   THEN TO_CHAR(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("WHEN ui.dataalteracao > apo.dataalteracao   THEN TO_CHAR(ui.dataalteracao, 'dd/mm/yyyy hh24:mi:ss') ");
		sb.append("ELSE TO_CHAR(apo.dataalteracao, 'dd/mm/yyyy hh24:mi:ss')  END AS data_alteracao_agencia, ");
		sb.append("(SELECT COUNT ( * )  FROM apo_agenciapropagandavisita vis  WHERE vis.idagencia = apo.idagencia  ) AS total_visitas_agencia, ");
		sb.append("(SELECT COUNT ( * )  FROM apo_agenciatrabalho tra  WHERE tra.idagencia = apo.idagencia  ) AS total_trabalhos, ");
		sb.append("(SELECT COUNT ( * )  FROM apo_agenciatrabalhovisita  WHERE idtrabalho IN  (SELECT id FROM apo_agenciatrabalho WHERE idtrabalho = ags.id    )  ) AS total_visitas_trabalho ");
		sb.append(", ete.idtipoempresa as tipo_empresa, cidade.uf ");		
		sb.append("from empresatipoempresa ete ");
		sb.append("INNER JOIN empresaagencia ea ON ete.idempresa = ea.idempresa ");
		sb.append("INNER JOIN apo_agenciapropagandaonline apo ON ete.idempresa = apo.idagencia ");
		sb.append("LEFT JOIN apo_agenciaconfiguracaosite ui ON ui.idagencia = ete.idempresa ");
		sb.append("INNER JOIN apo_agenciatrabalho ags ON ags.idagencia     = ete.idempresa ");
		sb.append("left join cep inner join cidade on cep.idcidade = cidade.idcidade on cep.idcep = ete.idendereco ");
		sb.append("where ete.ativo = 1 ");
		sb.append("and ea.visivelonline = 1 ");
		sb.append("GROUP BY ete.nomefantasia,  ags.id,  ags.titulo,  apo.idagencia,  apo.tipoagencia,  apo.datacadastro,  apo.dataalteracao,  ui.dataalteracao, ete.idtipoempresa,  cidade.uf  ORDER BY apo.idagencia,ags.id,  ags.titulo ");
//		sb.append("order by apo.idagencia ");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		List<TrabalhosVO> list = new ArrayList<TrabalhosVO>();
		for (Object rows : sqlQuery.list()) {
			TrabalhosVO vo = new TrabalhosVO();
			Object[] row = (Object[]) rows;
			java.math.BigDecimal idAgencia = (java.math.BigDecimal) row[0];	
			java.math.BigDecimal idCampanha = (java.math.BigDecimal) row[1];	
			String campanha =  (String) row[2];
			String nomeAgencia = (String) row[3];
			String tipoAgencia = (String) row[4];
//			String dtCadastro =  GmmDateUtil.converteDate2StringCompleta((java.sql.Timestamp)  row[5]);
			
			
			String dtCadastroAgencia =   GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String)  row[5]));
			String dtAlteracaoAgencia =   GmmDateUtil.converteDate2StringCompleta(GmmDateUtil.converteString2Date((String)  row[6]));
			java.math.BigDecimal totalVisitasAgencia = (java.math.BigDecimal) row[7];	
			java.math.BigDecimal totalTrabalhos= (java.math.BigDecimal) row[8];
			java.math.BigDecimal totalVisitasTrabalhos = (java.math.BigDecimal) row[9];
//			vo.setDtCadastro(dtCadastro);
			vo.setIdCampanha(idCampanha.intValue());
			vo.setCampanha(campanha);
			vo.setDtAlteracaoAgencia(dtAlteracaoAgencia);
			vo.setDtCadastroAgencia(dtCadastroAgencia);
			vo.setIdAgencia(idAgencia.intValue());
			vo.setNomeAgencia(nomeAgencia);
			vo.setTipoAgencia(tipoAgencia);
			vo.setTotalTrabalhos(totalTrabalhos.intValue());
			vo.setTotalVisitasAgencia(totalVisitasAgencia.intValue());
			vo.setTotalVisitasTrabalho(totalVisitasTrabalhos.intValue());
			vo.setTipoEmpresa((String) row[10]);
			vo.setUf((String) row[11]);
			list.add(vo);
		}
		HibernateSessionFactory.closeSession(session);
		return list;
	}

	@Override
	public List<OportunidadesVO> getViewsOportunidades()
			throws GmmFacadeException, GmmSqlException {
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		StringBuilder sb = new StringBuilder();
		sb.append("select ete.nomefantasia as nome_veiculo, opt.titulo as nome_oportunidade, count(vis.id) as total_views, ");
		sb.append("to_char(opt.datacadastro, 'dd/mm/yyyy') as data_cadastro, to_char(opt.validade, 'dd/mm/yyyy') as data_validade ");
		sb.append(", ete.idtipoempresa as tipo_empresa, cidade.uf ");
		sb.append("from empresatipoempresa ete ");
		sb.append("inner join ami_oportunidade opt on ete.idempresa = opt.idveiculo ");
		sb.append("left join ami_oportunidadevisita vis on opt.id = vis.idoportunidade ");
		sb.append("left join cep inner join cidade on cep.idcidade = cidade.idcidade on cep.idcep = ete.idendereco ");
		sb.append("group by ete.nomefantasia, opt.titulo, opt.datacadastro, opt.validade, ete.idtipoempresa,  cidade.uf ");
		sb.append("order by ete.nomefantasia, opt.datacadastro, opt.titulo ");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		List<OportunidadesVO> oportunidadesVOs = new ArrayList<OportunidadesVO>();
		for (Object rows : sqlQuery.list()) {
			OportunidadesVO vo = new OportunidadesVO();
			Object[] row = (Object[]) rows;
			String nomeVeiculo =  (String) row[0];
			String nomeOportunidade =  (String) row[1];
			java.math.BigDecimal totalViews = (java.math.BigDecimal) row[2];	
			String dtCadastro =  (String)  row[3];
			String dtValidade =   (String)  row[4];
			vo.setDtCadastro(dtCadastro);
			vo.setDtValidade(dtValidade);
			vo.setNomeOportunidade(nomeOportunidade);
			vo.setNomeVeiculo(nomeVeiculo);
			vo.setTotalViews(totalViews.intValue());
			vo.setTipoEmpresa((String) row[5]);
			vo.setUf((String) row[6]);
			oportunidadesVOs.add(vo);
		}
		HibernateSessionFactory.closeSession(session);
		return oportunidadesVOs;
		
	}

	@Override
	public Integer getIdVeiculo(String nomeFantasia) {
		StringBuilder sb = new StringBuilder();
		sb.append("select veiculo.idempresa from ami_veiculo veiculo inner join empresatipoempresa ete on ete.idempresa = veiculo.idempresa inner join empresa on empresa.cnpj = ete.cnpj where ete.nomefantasia like '"+nomeFantasia+"'");
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		BigDecimal tot = new BigDecimal(0);
		for (Object rows : sqlQuery.list()) {
			 tot =  (BigDecimal) rows;
		}
		HibernateSessionFactory.closeSession(session);
		return tot.intValue();
	}

	@Override
	public Integer insertUsuarioVeiculo(int idveiculo, String login,
			String senha) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ami_usuario(id, datacadastro, tipousuario, email, idusuario, idveiculo, senha) " +
				"values(SEQ_AMI_USUARIO.NEXTVAL, SYSDATE, 'UsuarioVeiculo', '"+login+"', 1, "+idveiculo+", '"+senha+"')");
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		 sqlQuery.executeUpdate();
		 HibernateSessionFactory.closeSession(session);
		 return 1;
		 
		 
	}

	@Override
	public Integer updateTipoPacote(int idveiculo, String tipoacote) {
		StringBuilder sb = new StringBuilder();
		sb.append("update ami_veiculo set tipopacote = '"+tipoacote+"' where idempresa = "+idveiculo);
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		 sqlQuery.executeUpdate();
		 HibernateSessionFactory.closeSession(session);
		 return 1;
	}

	@Override
	public void desativarVeiculo(int idVeiculo) {
		StringBuilder sb = new StringBuilder();
		sb.append("update ami_veiculo set ativo = 0 where idempresa = "+idVeiculo);
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		 sqlQuery.executeUpdate();
		 HibernateSessionFactory.closeSession(session);
		
	}

	@Override
	public Integer getidEmpresa(String nomeFantasia, String executivo) {		
		StringBuilder sb = new StringBuilder();
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		sb.append("select ete.idempresa from empresatipoempresa ete ");
		sb.append("inner join apo_agenciapropagandaonline apo on apo.idagencia =  ete.idempresa ");
		sb.append("inner join executivoempresa ee on ee.idempresa = ete.idempresa ");
		sb.append("inner join executivo on executivo.idexecutivo = ee.idexecutivo ");
		sb.append("where  UPPER(trim(ete.nomefantasia)) like UPPER(trim(:nomeEM)) ");
		sb.append("and UPPER(executivo.nomeexecutivo) = UPPER(:nomoeEX) ");
//		sb.append("and ete.ativo = 1 ");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		sqlQuery.setString("nomeEM", nomeFantasia);
		sqlQuery.setString("nomoeEX", executivo);
		BigDecimal tot = new BigDecimal(0);
		for (Object rows : sqlQuery.list()) {
			 tot =  (BigDecimal) rows;
		}
		HibernateSessionFactory.closeSession(session);
		return tot.intValue();
	}

	@Override
	public List<CenpVO> getCenp(String cnpj) {
		StringBuilder sb = new StringBuilder();
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		sb.append("select ete.nomefantasia, empresa.razaosocial, ete.email, ete.cnpj,  ");
		sb.append(" decode(ete.idtipoempresa, 'EmpresaRepresentante', 'REPRESENTANTES', ");
		sb.append(" 'EmpresaOutdoor', 'OUTDOOR','EmpresaOutro', 'OUTRO','EmpresaInternet', 'INTERNET', ");
		sb.append(" 'EmpresaFornecedor', 'FORNECEDOR','EmpresaMidiaExterior', 'MÍDIA EXTERIOR', ");
		sb.append(" 'EmpresaCinema', 'CINEMA','EmpresaTvAberta', 'TV ABERTA', 'EmpresaRadio', 'RÁDIO', ");
		sb.append(" 'EmpresaAgencia', 'AGÊNCIA','EmpresaRevista', 'REVISTA','EmpresaGuiaLista', 'GUIA', ");
		sb.append(" 'EmpresaAnunciante', 'ANUNCIANTE','EmpresaJornal', 'JORNAL','EmpresaTvAssinatura', 'TV POR ASSINATURA')  ");
		sb.append(" as tipo_empresa,segmento.nome as segmento, cep.bairro, cep.cep, cep.logradouro, cep.tipologradouro, ete.complemento, ");
		sb.append(" ete.numero,cidade.localidade, cidade.uf, ETE.TELEFONE, pe.nome from empresatipoempresa ete  ");
		sb.append(" inner join empresaagencia ea on ete.idempresa = ea.idempresa ");
		sb.append(" inner join apo_agenciapropagandaonline apo on apo.idagencia =  ete.idempresa ");
		sb.append(" inner join empresaagenciasegmento eas on eas.idagencia = apo.idagencia ");
		sb.append(" inner join segmento on segmento.idsegmento = eas.idsegmento ");
		sb.append(" inner join porteempresa pe on pe.idporteempresa = ete.idporteempresa ");
		sb.append(" left join empresa on empresa.cnpj = ete.cnpj ");
		sb.append(" left join cep on cep.idcep = ete.idendereco ");
		sb.append(" left join cidade on cidade.idcidade = cep.idcidade ");
		sb.append(" where ete.cnpj like '%"+cnpj+"%' and ete.ativo = 1");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		List<CenpVO> list = new ArrayList<CenpVO>();
		CenpVO c = null;
		for (Object rows : sqlQuery.list()) {
			c = new CenpVO();
			Object[] row = (Object[]) rows;
			c.setNomeFantasia((String) row[0]);
			c.setRazaoSocial((String) row[1]);
			c.setEmail((String) row[2]);
			c.setCnpj((String) row[3]);
			c.setTipoDeEmpresa((String) row[4]);
			c.setTipoSegmento((String) row[5]);
			c.setBairro((String) row[6]);
			c.setCep((String) row[7]);
			c.setLogradouro((String) row[8]);
			c.setTipoLogradouro((String) row[9]);
			c.setComplemento((String) row[10]);
			c.setNumero((String) row[11]);
			c.setCidade((String) row[12]);
			c.setUf((String) row[13]);
			c.setTelefone((String) row[14]);
			c.setPorteEmpresa((String) row[15]);
			list.add(c);
		}		
		HibernateSessionFactory.closeSession(session);
		return list;
	}

	@Override
	public List<CenpVO> getCenp(List<String> cnpjs) {
		StringBuilder sb = new StringBuilder();
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		sb.append("select ete.nomefantasia, empresa.razaosocial, ete.email, lpad(ete.cnpj, 14, '0') as cnpj,  ");
		sb.append(" decode(ete.idtipoempresa, 'EmpresaRepresentante', 'REPRESENTANTES', ");
		sb.append(" 'EmpresaOutdoor', 'OUTDOOR','EmpresaOutro', 'OUTRO','EmpresaInternet', 'INTERNET', ");
		sb.append(" 'EmpresaFornecedor', 'FORNECEDOR','EmpresaMidiaExterior', 'MÍDIA EXTERIOR', ");
		sb.append(" 'EmpresaCinema', 'CINEMA','EmpresaTvAberta', 'TV ABERTA', 'EmpresaRadio', 'RÁDIO', ");
		sb.append(" 'EmpresaAgencia', 'AGÊNCIA','EmpresaRevista', 'REVISTA','EmpresaGuiaLista', 'GUIA', ");
		sb.append(" 'EmpresaAnunciante', 'ANUNCIANTE','EmpresaJornal', 'JORNAL','EmpresaTvAssinatura', 'TV POR ASSINATURA')  ");
		sb.append(" as tipo_empresa,segmento.nome as segmento, cep.bairro, lpad(cep.cep,8,'0') as cep, cep.logradouro, cep.tipologradouro, ete.complemento, ");
		sb.append(" ete.numero,cidade.localidade, cidade.uf, ETE.TELEFONE, pe.nome from empresatipoempresa ete  ");
		sb.append(" inner join empresaagencia ea on ete.idempresa = ea.idempresa ");
		sb.append(" inner join apo_agenciapropagandaonline apo on apo.idagencia =  ete.idempresa ");
		sb.append(" inner join empresaagenciasegmento eas on eas.idagencia = apo.idagencia ");
		sb.append(" inner join segmento on segmento.idsegmento = eas.idsegmento ");
		sb.append(" inner join porteempresa pe on pe.idporteempresa = ete.idporteempresa ");
		sb.append(" left join empresa on empresa.cnpj = ete.cnpj ");
		sb.append(" left join cep on cep.idcep = empresa.idendereco ");
		sb.append(" left join cidade on cidade.idcidade = cep.idcidade ");
//		sb.append(" where ete.cnpj not in(:cn) ");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
//		sqlQuery.setParameterList("cn", cnpjs);
		List<CenpVO> cenpVOs = new ArrayList<CenpVO>();
		CenpVO c = null;
		for (Object rows : sqlQuery.list()) {
			c = new CenpVO();
			Object[] row = (Object[]) rows;
			c.setNomeFantasia((String) row[0]);
			c.setRazaoSocial((String) row[1]);
			c.setEmail((String) row[2]);
			c.setCnpj((String) row[3]);
			c.setTipoDeEmpresa((String) row[4]);
			c.setTipoSegmento((String) row[5]);
			c.setBairro((String) row[6]);
			c.setCep((String) row[7]);
			c.setLogradouro((String) row[8]);
			c.setTipoLogradouro((String) row[9]);
			c.setComplemento((String) row[10]);
			c.setNumero((String) row[11]);
			c.setCidade((String) row[12]);
			c.setUf((String) row[13]);
			c.setTelefone((String) row[14]);
			c.setPorteEmpresa((String) row[15]);
			cenpVOs.add(c);
		}		
		HibernateSessionFactory.closeSession(session);
		return cenpVOs;
	}

	@Override
	public boolean adicionaCenp(String cnpj) {
		int retorno = 0; 
		java.math.BigDecimal idAgencia  = new BigDecimal(0);
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		StringBuilder sb = new StringBuilder();
		sb.append("select idempresa from empresatipoempresa where cnpj =:par");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		sqlQuery.setString("par", cnpj);
		for (Object rows : sqlQuery.list()) {			
			Object row = (Object) rows;
			idAgencia = (java.math.BigDecimal) row;	
			sqlQuery = session.createSQLQuery("update empresaagenciasegmento set cenp = 1 where idagencia =:par1");
			sqlQuery.setInteger("par1", idAgencia.intValue());
			retorno = sqlQuery.executeUpdate();
		}
		HibernateSessionFactory.closeSession(session);
		return retorno>0?true:false;
	}

	@Override
	public List<Integer> getIdsEmpresa() {
		 List<Integer> ls = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		sb.append("select cnpj from empresa");
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		for (Object row : sqlQuery.list()) {
			String cnpj = (String) row;
			sqlQuery = session.createSQLQuery("update empresa set id_empresa = MMH.SEQ_EMPRESA_CNPJ.nextVal WHERE CNPJ = '"+cnpj+"'");
		ls.add(sqlQuery.executeUpdate());
		}	
		HibernateSessionFactory.closeSession(session);
		return ls;
	}

	@Override
	public Integer getidEmpresaTipoempresa(String nomeFantasia,
			String tipoEmpresa) {
		StringBuilder sb = new StringBuilder();
		sb.append("select veiculo.idempresa from ami_veiculo veiculo inner join empresatipoempresa ete on ete.idempresa = veiculo.idempresa inner join empresa on empresa.cnpj = ete.cnpj where upper(ete.nomefantasia) like upper(:par1)");
		sb.append(" and upper(veiculo.tipoempresa) like upper('"+tipoEmpresa+"')");
		Session session = HibernateSessionFactory.getSessionAnuariosViews();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		sqlQuery.setString("par1", nomeFantasia);
		BigDecimal tot = new BigDecimal(0);
		for (Object rows : sqlQuery.list()) {
			 tot =  (BigDecimal) rows;
		}
		HibernateSessionFactory.closeSession(session);
		return tot.intValue();
	}

}
