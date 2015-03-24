package br.com.gmm.anuariosviews.facade;

import java.util.List;

import br.com.gmm.anuariosviews.entity.vo.AgenciasVO;
import br.com.gmm.anuariosviews.entity.vo.CenpVO;
import br.com.gmm.anuariosviews.entity.vo.OportunidadesVO;
import br.com.gmm.anuariosviews.entity.vo.TrabalhosVO;
import br.com.gmm.anuariosviews.entity.vo.VeiculosVO;

public interface IAnuariosViewsFacade {
	public List<VeiculosVO> getViewsVeiculos();
	public List<AgenciasVO> getViewsAgencias();
	public List<TrabalhosVO> getViewsTrabalhoss();
	public List<OportunidadesVO> getViewsOportunidades();
	public Integer getidVeiculo(String nomeFantasia);
	public Integer insertUsuarioVeiculo(int idveiculo, String Login, String senha);
	public Integer updateTipoPacote(int idveiculo, String tipoacote);
	public void desativarVeiculo(int idVeiculo);
	public Integer getidEmpresa(String nomeFantasia, String executivo);
	public List<CenpVO> getCenp(String cnpj);
	public List<CenpVO> getCenps(List<String> cnpjs);
	public boolean adicionaCenp(String cnpj);
	public List<Integer> getIdsEmpresa();
	public Integer getidEmpresaTipoempresa(String nomeFantasia, String tipoEmpresa);
}
