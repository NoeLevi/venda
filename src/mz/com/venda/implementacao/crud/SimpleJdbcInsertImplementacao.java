package mz.com.venda.implementacao.crud;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class SimpleJdbcInsertImplementacao extends SimpleJdbcInsert implements Serializable {

	public SimpleJdbcInsertImplementacao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
