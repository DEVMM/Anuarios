package br.com.gmm.anuariosviews.entity.vo;


public class TrabalhosVO {
	private int idAgencia;
	private int idCampanha;
	private String campanha;
	private String nomeAgencia;
	private String tipoAgencia;
	private String dtCadastro;
	private String dtCadastroAgencia;
	private String dtAlteracaoAgencia;
	private int totalVisitasAgencia;
	private int totalTrabalhos;
	private int totalVisitasTrabalho;
	private String tipoEmpresa;
	private String uf;
	
	public int getIdCampanha() {
		return idCampanha;
	}
	public void setIdCampanha(int idCampanha) {
		this.idCampanha = idCampanha;
	}
	
	
	public String getCampanha() {
		return campanha;
	}
	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}
	public int getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getTipoAgencia() {
		return tipoAgencia;
	}
	public void setTipoAgencia(String tipoAgencia) {
		this.tipoAgencia = tipoAgencia;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getDtCadastroAgencia() {
		return dtCadastroAgencia;
	}
	public void setDtCadastroAgencia(String dtCadastroAgencia) {
		this.dtCadastroAgencia = dtCadastroAgencia;
	}
	public String getDtAlteracaoAgencia() {
		return dtAlteracaoAgencia;
	}
	public void setDtAlteracaoAgencia(String dtAlteracaoAgencia) {
		this.dtAlteracaoAgencia = dtAlteracaoAgencia;
	}
	public int getTotalVisitasAgencia() {
		return totalVisitasAgencia;
	}
	public void setTotalVisitasAgencia(int totalVisitasAgencia) {
		this.totalVisitasAgencia = totalVisitasAgencia;
	}
	public int getTotalTrabalhos() {
		return totalTrabalhos;
	}
	public void setTotalTrabalhos(int totalTrabalhos) {
		this.totalTrabalhos = totalTrabalhos;
	}
	public int getTotalVisitasTrabalho() {
		return totalVisitasTrabalho;
	}
	public void setTotalVisitasTrabalho(int totalVisitasTrabalho) {
		this.totalVisitasTrabalho = totalVisitasTrabalho;
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
