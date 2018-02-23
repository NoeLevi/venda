package mz.com.venda.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import mz.com.venda.hibernate.session.HibernateUtil;
import mz.com.venda.listener.ContextLoaderListenerVendaUtils;
import mz.com.venda.model.Usuario;
import mz.com.venda.utils.UtilVenda;

@WebFilter(filterName = "conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {

	private static SessionFactory sf;
	private static final long serialVersionUID = 1L;

	// executa apenas uma vez
	// executado quando a aplicacao esta sendo iniciada

	@Override
	protected void initFilterBean() throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// JDBC SPRING

		BasicDataSource sprinDataSource = (BasicDataSource) ContextLoaderListenerVendaUtils.getBean("springDataSource");
		DefaultTransactionDefinition def= new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(sprinDataSource);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			
			request.setCharacterEncoding("UTF-8");// define a coodificacao
			
			// capturar o usuario que faz a operacao
			
			HttpServletRequest request2=(HttpServletRequest) request;
			
			HttpSession httpSession =request2.getSession();
			Usuario userlogadoSessao=(Usuario)httpSession.getAttribute("userLogadoSessao");
			
			if(userlogadoSessao!=null){
				UtilVenda.getThreadLocal().set(userlogadoSessao.getCodigo());
			}
			sf.getCurrentSession().beginTransaction();
			// antes do executar acao(request)
			filterChain.doFilter(request, response);// executa nossa acao no servidor

			// depois de executar acao(reposta)
			transactionManager.commit(status);
			if(sf.getCurrentSession().getTransaction().isActive()){
				sf.getCurrentSession().flush();
				sf.getCurrentSession().getTransaction().commit();
				
			}
			
			if(sf.getCurrentSession().isOpen()){
				sf.getCurrentSession().close();
				
			}
			
			response.setCharacterEncoding("UTF-8");
			
			response.setContentType("text/html; charset=UTF-8");
			

		}catch(Exception e){
			transactionManager.rollback(status);
			
			e.printStackTrace();
			
			if(sf.getCurrentSession().getTransaction().isActive()){
				sf.getCurrentSession().getTransaction().rollback();
			}
			
			if(sf.getCurrentSession().isOpen()){
				sf.getCurrentSession().close();
			}
		}finally{
			if(sf.getCurrentSession().isOpen()){
				
				if(sf.getCurrentSession().beginTransaction().isActive()){
					
					sf.getCurrentSession().flush();
					sf.getCurrentSession().clear();
				}
				
				if(sf.getCurrentSession().isOpen()){
					sf.getCurrentSession().close();
				}
			}
		}
		
		
		
	}

}
