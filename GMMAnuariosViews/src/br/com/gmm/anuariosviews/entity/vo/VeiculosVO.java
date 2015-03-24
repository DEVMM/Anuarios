package br.com.gmm.anuariosviews.entity.vo;

import java.util.Date;

public class VeiculosVO {
	private int idVeiculo;
	private String nomeVeiculo;
	private String tipoVeiculo;
	private String dtCadastroVeiculo;
	private String dtAlteracaoVeiculo;
	private int totalVisitasVeiculo;
	private int totalOportunidade;
	private int totalVisitasOportunidade;
	private String tipoEmpresa;
	private String uf;

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getNomeVeiculo() {
		return nomeVeiculo;
	}

	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getDtCadastroVeiculo() {
		return dtCadastroVeiculo;
	}

	public void setDtCadastroVeiculo(String dtCadastroVeiculo) {
		this.dtCadastroVeiculo = dtCadastroVeiculo;
	}

	public String getDtAlteracaoVeiculo() {
		return dtAlteracaoVeiculo;
	}

	public void setDtAlteracaoVeiculo(String dtAlteracaoVeiculo) {
		this.dtAlteracaoVeiculo = dtAlteracaoVeiculo;
	}

	public int getTotalVisitasVeiculo() {
		return totalVisitasVeiculo;
	}

	public void setTotalVisitasVeiculo(int totalVisitasVeiculo) {
		this.totalVisitasVeiculo = totalVisitasVeiculo;
	}

	public int getTotalOportunidade() {
		return totalOportunidade;
	}

	public void setTotalOportunidade(int totalOportunidade) {
		this.totalOportunidade = totalOportunidade;
	}

	public int getTotalVisitasOportunidade() {
		return totalVisitasOportunidade;
	}

	public void setTotalVisitasOportunidade(int totalVisitasOportunidade) {
		this.totalVisitasOportunidade = totalVisitasOportunidade;
	}

	/**
	 * @return the tipoEmpresa
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa the tipoEmpresa to set
	 */
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	

}
