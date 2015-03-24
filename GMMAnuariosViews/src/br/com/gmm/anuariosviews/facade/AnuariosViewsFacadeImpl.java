package br.com.gmm.anuariosviews.facade;

import java.util.List;

import br.com.gmm.anuariosviews.dao.AnuariosViewsDAOImpl;
import br.com.gmm.anuariosviews.dao.IAnuariosViewsDAO;
import br.com.gmm.anuariosviews.entity.vo.AgenciasVO;
import br.com.gmm.anuariosviews.entity.vo.CenpVO;
import br.com.gmm.anuariosviews.entity.vo.OportunidadesVO;
import br.com.gmm.anuariosviews.entity.vo.TrabalhosVO;
import br.com.gmm.anuariosviews.entity.vo.VeiculosVO;

public class AnuariosViewsFacadeImpl implements IAnuariosViewsFacade {
	private IAnuariosViewsDAO viewsDAO = null;

	@Override
	public List<VeiculosVO> getViewsVeiculos() {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.getViewsVeiculos();
	}

	@Override
	public List<AgenciasVO> getViewsAgencias() {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.getViewsAgencias();
	}

	
	@Override
	public List<TrabalhosVO> getViewsTrabalhoss() {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.getViewsTrabalhoss();
	}

	@Override
	public List<OportunidadesVO> getViewsOportunidades() {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.getViewsOportunidades();
	}

	@Override
	public Integer getidVeiculo(String nomeFantasia) {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.getIdVeiculo(nomeFantasia);
	}

	@Override
	public Integer insertUsuarioVeiculo(int idveiculo, String Login,
			String senha) {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.insertUsuarioVeiculo( idveiculo,  Login, senha);
	}

	@Override
	public Integer updateTipoPacote(int idveiculo, String tipoacote) {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		return viewsDAO.updateTipoPacote( idveiculo, tipoacote);
	}

	public void desativarVeiculo(int idVeiculo) {
		this.viewsDAO = new AnuariosViewsDAOImpl();
		viewsDAO.desativarVeiculo( idVeiculo);
		
	}

	public Integer getidEmpresa(String nomeFantasia, String executivo) {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.getidEmpresa( nomeFantasia, executivo);
	}

	public List<CenpVO> getCenp(String cnpj) {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.getCenp(cnpj);
	}

	public List<CenpVO> getCenps(List<String> cnpjs) {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.getCenp(cnpjs);
	}

	public boolean adicionaCenp(String cnpj) {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.adicionaCenp(cnpj);
	}

	public List<Integer> getIdsEmpresa() {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.getIdsEmpresa();
	}

	@Override
	public Integer getidEmpresaTipoempresa(String nomeFantasia,
			String tipoEmpresa) {
		this.viewsDAO = new AnuariosViewsDAOImpl();		
		return viewsDAO.getidEmpresaTipoempresa(nomeFantasia, tipoEmpresa);
	}

}
