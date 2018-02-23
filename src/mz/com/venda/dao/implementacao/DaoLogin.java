package mz.com.venda.dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.repositorio.interfaces.RepositoryLogin;

@Repository
public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

	
	
	private static final long serialVersionUID = 1L;

	
	@Override
	public boolean autentico(String login, String senha) throws Exception {
		String sql="select count(1) as autentica from usuario where nomeusuario=? and senhausuario=?";
		
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql,new Object[]{login,senha});
		
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 :false;
	}

}
