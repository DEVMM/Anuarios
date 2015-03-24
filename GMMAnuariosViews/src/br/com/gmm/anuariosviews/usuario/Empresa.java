package br.com.gmm.anuariosviews.usuario;

import java.util.List;

import br.com.gmm.anuariosviews.facade.AnuariosViewsFacadeImpl;

public class Empresa {
public static void main(String[] args) {
	AnuariosViewsFacadeImpl facade = new AnuariosViewsFacadeImpl();
	List<Integer> ids = facade.getIdsEmpresa();
	
}
}
