package mz.com.venda.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable{
	
	//salva os dados
	void save(T obj) throws Exception;
	
	//persiste
	void persist(T obj) throws Exception;
	
	// salva ou actualiza
	void saveOrUpdate(T obj) throws Exception;
	
	// realiza update/autualizacao de dados
	void update(T obj) throws Exception;
	
	// realiza delete de dados
	void delete (T obj) throws Exception;
	
	//  salvar e retornar objecto em estado persistente
	T merge (T obj) throws Exception;
	
	// carrega a lista de uma determinada classe
	List<T> findList(Class<T> obs) throws Exception;
	
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	
	T findPorId(Class<T> entidade, Long id) throws Exception;

	List<T> findListByQueryDinamica(String dinamica) throws Exception;
// executa update com HQL
	void executarUpdateQueryDinamica(String dinamica) throws Exception;
	//executa update com SQL
	void executarUpdateSQLDinamica(String dinamica) throws Exception;
	
	// limpa a sessao do hibernate
	void clearSession() throws Exception;
	
	//retira um objecto da sessao
	void evict (Object obs ) throws Exception;
	
	//retorna a sessao dentro da interface
	Session getSession() throws Exception;
	
	//
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	List<Object []> getListSQLDinamicaArray(String sql) throws Exception;

	
	// JDBC do Spring
	JdbcTemplate getJdbcTemplate(); 
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	
	Long totalRegisto(String table) throws Exception;
	// query do hibernate
	Query obterQuery(String sql) throws Exception;
	// carregamento dinamico com JSF e PrimeFaces
	List<T> findListByQueryDinamnica(String query, int inicio, int fim) throws Exception;
}
