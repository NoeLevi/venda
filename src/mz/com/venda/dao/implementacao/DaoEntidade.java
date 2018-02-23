package mz.com.venda.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.model.Usuario;
import mz.com.venda.repositorio.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Usuario> 
implements RepositoryEntidade {

	 
	private static final long serialVersionUID = 1L;
	
	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		
		SqlRowSet rowSet=super.getJdbcTemplate()
		.queryForRowSet("select userultimoacesso from usuario where inactivo is false"
				+ " and nomeusuario= ?", new Object[]{name});
		return rowSet.next()? rowSet.getDate("userultimoacesso"):null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql ="update usuario set userultimoacesso=current_timestamp where inactivo is false and nomeusuario = ?";
	super.getSimpleJdbcTemplate().update(sql, login);
	
	}

	@Override
	public boolean existeUsuario(String user_login) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("select count(1)>=1 from usuario where nomeusuario ='").append(user_login).append("'");
		
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
