package mz.com.venda.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mz.com.venda.controller.ProvinciaController;
import mz.com.venda.geral.BeanManagedViewAbstract;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Provincia;
@Controller
@Scope(value="session")
@ManagedBean(name="provinciaBeanView")
public class ProvinciaBeanView  extends BeanManagedViewAbstract{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProvinciaController provinciaController;
	
	public List<SelectItem> getProvincias() throws Exception{
		
		return provinciaController.getListProvincia();
		
	}

	@Override
	protected Class<Provincia> getClassImplement() {
		// TODO Auto-generated method stub
		return Provincia.class;
	}

	@Override
	protected InterfaceCrud<Provincia> getController() {
		// TODO Auto-generated method stub
		return provinciaController;
	}
	
}
