package mz.com.venda.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mz.com.venda.hibernate.session.HibernateUtil;
import mz.com.venda.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {


	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Autowired
	private JdbcTemplateImplementacao jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImplementacao simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImplementacao simpleJdbcInsert;

	@Autowired
	private SimpleJdbcClassImplementacao simpleJdbcClass;

	public SimpleJdbcCall getSimpleJdbcClass() {
		return simpleJdbcClass;
	}

	@Override
	public void save(T obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	// roda instantaneamente o sql no banco de dado
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void persist(T obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validarTransaction();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validarSessionFactory();

		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validarSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select distinct(entidade) from ").append(entidade.getSimpleName()).append(" entidade ");

		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validarSessionFactory();
		Object obs = sessionFactory.getCurrentSession().load(entidade.getClass(), id);
		return obs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findPorId(Class<T> entidade, Long id) throws Exception {
		validarSessionFactory();
		T obs = (T) sessionFactory.getCurrentSession().load(entidade.getClass(), id);
		return obs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String dinamica) throws Exception {
		validarSessionFactory();
		List<T> lista = new ArrayList<T>();

		lista = sessionFactory.getCurrentSession().createQuery(dinamica).list();
		return lista;
	}

	@Override
	public void executarUpdateQueryDinamica(String dinamica) throws Exception {
		validarSessionFactory();

		sessionFactory.getCurrentSession().createQuery(dinamica).executeUpdate();
		executeFlushSession();

	}

	@Override
	public void executarUpdateSQLDinamica(String dinamica) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(dinamica).executeUpdate();

		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object obs) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().evict(obs);
	}

	@Override
	public Session getSession() throws Exception {
		validarSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validarSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		// TODO Auto-generated method stub
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		// TODO Auto-generated method stub
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegisto(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from entity").append(table);
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String sql) throws Exception {
		validarSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(sql.toString());
		return queryReturn;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<T> findListByQueryDinamnica(String query, int inicio, int fim) throws Exception {
		validarSessionFactory();

		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query.toString()).setFirstResult(inicio)
				.setMaxResults(fim).list();

		return null;
	}

	private void validarSessionFactory() {
		if (sessionFactory == null) {

			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validarTransaction();
	}

	private void validarTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {

			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	@SuppressWarnings("unused")
	private void commitProcessoAjax() {

		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	@SuppressWarnings("unused")
	private void rollBackProcessoAjax() {

		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListSQLDinamicaArray(String sql) throws Exception {
		validarSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public T findIniqueByQueryDinamica(String sql) throws Exception {

		validarSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().createQuery(sql.toString()).uniqueResult();
		return obj;
	}

	public T findInuqueByProperty(Class<T> usuario, Object valor, String atributo, String condicao) throws Exception {

		validarSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select entity from ").append(usuario.getSimpleName()).append("  entity where entity.")
				.append(atributo).append(" = '").append(valor).append("' ").append(condicao);

		T obj = (T) this.findIniqueByQueryDinamica(query.toString());
		return obj;
	}

}
