package mz.com.venda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Provincia;

@Controller
public class ProvinciaController extends ImplementacaoCrud<Provincia> implements InterfaceCrud<Provincia> {


	private static final long serialVersionUID = 1L;
	
	public List<SelectItem> getListProvincia() throws Exception{
	
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Provincia> provincia=super.findListByQueryDinamica(" from Provincia");
		
		for(Provincia provincias:provincia){
			
			list.add(new SelectItem(provincias, provincias.getProv_nome()));
		}
		return list;
	}

}
