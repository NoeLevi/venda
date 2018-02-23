package mz.com.venda.geral;

import org.springframework.stereotype.Component;

import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.report.util.BeanReportView;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView{


	private static final long serialVersionUID = 1L;

	protected abstract Class<?> getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();

}
