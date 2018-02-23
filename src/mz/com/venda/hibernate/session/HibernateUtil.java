package mz.com.venda.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import mz.com.venda.implementacao.crud.VariavelConexaoUtil;

/**
 * Responsavel por estabelecer a conexao com hibernate
 * @author Levy
 *
 */
public class HibernateUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE="java:/comp/env/jdbc/datasource";
 
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * responsavel por ler o arquivo de configuracao hibernate.cfg.xml
	 * @return
	 */
	
	private static SessionFactory buildSessionFactory() {
		// TODO Auto-generated method stub
		try{
			if(sessionFactory==null){
				
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			
			return sessionFactory;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new  ExceptionInInitializerError("Erro ao criar conexaoo session factory");
		}
		
	}
	
	/**
	 * retorna sessionfactory corrente
	 * 
	* @return sessionFaactory
	 */
	public static SessionFactory getSessionFactory (){
		
		return sessionFactory;
	}
	/**
	 * retorna a sessao do  sessionfactory 				
	 * 
	* @return sessionFaactory
	 */
	public static Session  getCurrentSession(){
		
		
		return getSessionFactory().getCurrentSession();
	}
	
	/**
	 * Abre a nova sessao no SessionFactory
	 * return Session
	 * 
	 */
	
	public static Session openSession(){
		if(sessionFactory == null){
			buildSessionFactory();
			
		}
		
		return sessionFactory.openSession();
	}
	
	/**
	 * obtem o provedor da connexao configurada
	 * @return connection sql
	 * @throws SQLException
	 */
	
	public static Connection getConnectionProvider() throws SQLException{
		
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}
	
	/**
	 * 
	 * @return connection no Initialcontext java:/comp/env/jdbc/datasource
	 * @throws Exception
	 */
	public static Connection  getConnection() throws Exception{
		InitialContext context = new InitialContext();
				
				DataSource  da = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		return da.getConnection();
	}
	 
	
	public DataSource getDataSouceJndi() throws NamingException{
		InitialContext context = new InitialContext();
				 
		return (DataSource)context.lookup(VariavelConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		
	}
}
