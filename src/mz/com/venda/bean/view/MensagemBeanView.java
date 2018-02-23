package mz.com.venda.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mz.com.venda.geral.BeanManagedViewAbstract;
import mz.com.venda.interfac.crud.InterfaceCrud;

@Controller
@Scope(value="session")
@ManagedBean(name="mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
 @Override
public String novo() throws Exception {
System.out.println("chamou metodo vovo");

return "";
}
@Override
protected Class<?> getClassImplement() {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected InterfaceCrud<?> getController() {
	// TODO Auto-generated method stub
	return null;
}
} 
