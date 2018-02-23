package mz.com.venda.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mz.com.venda.geral.BeanManagedViewAbstract;
import mz.com.venda.geral.controller.UsuarioController;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Usuario;

@Controller
@Scope(value="session")
@ManagedBean(name="entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract{

	@Autowired
	private ContextoBean contextoBean;
	@Autowired

	private UsuarioController usuarioController;

	private static final long serialVersionUID = 1L;

	public String getUsuarioLogadoSecurity(){
		
		return contextoBean.getAuthentication().getName().toUpperCase() ;
	}
	
	public Date getUltimoAcesso() throws Exception {
		
		return  contextoBean.getUserLogado().getUserUltimoacesso();
		
	}

	@Override
	protected Class<Usuario> getClassImplement() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}

	@Override
	protected InterfaceCrud<Usuario> getController() {
		// TODO Auto-generated method stub
		return usuarioController;
	}
}
 