package br.com.gmm.anuariosviews.dao;

import java.util.List;

import br.com.gmm.anuariosviews.entity.vo.AgenciasVO;
import br.com.gmm.anuariosviews.entity.vo.CenpVO;
import br.com.gmm.anuariosviews.entity.vo.OportunidadesVO;
import br.com.gmm.anuariosviews.entity.vo.TrabalhosVO;
import br.com.gmm.anuariosviews.entity.vo.VeiculosVO;
import br.com.gmm.core.exception.GmmFacadeException;
import br.com.gmm.core.exception.GmmSqlException;

public interface IAnuariosViewsDAO {
public List<VeiculosVO> getViewsVeiculos() throws GmmFacadeException, GmmSqlException;
public List<AgenciasVO> getViewsAgencias() throws GmmFacadeException, GmmSqlException;
public List<TrabalhosVO> getViewsTrabalhoss() throws GmmFacadeException, GmmSqlException;
public List<OportunidadesVO> getViewsOportunidades() throws GmmFacadeException, GmmSqlException;
public Integer getIdVeiculo(String nomeFantasia);
public Integer insertUsuarioVeiculo(int idveiculo, String login, String senha);
public Integer updateTipoPacote(int idveiculo, String tipoacote);
public void desativarVeiculo(int idVeiculo);
public Integer getidEmpresa(String nomeFantasia, String executivo);
public Integer getidEmpresaTipoempresa(String nomeFantasia, String tipoEmpresa);
public List<CenpVO> getCenp(String cnpj);
public List<CenpVO> getCenp(List<String> cnpjs);
public boolean adicionaCenp(String cnpj);
public List<Integer> getIdsEmpresa();

}
