package mz.com.venda.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mz.com.venda.controller.CidadeController;
import mz.com.venda.geral.BeanManagedViewAbstract;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Cidade objectoSelecionado = new Cidade();

	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";

	private List<Cidade> list = new ArrayList<Cidade>();

	@Autowired
	private CidadeController cidadeController;

	@Override
	public String save() throws Exception {
		objectoSelecionado = cidadeController.merge(objectoSelecionado);
		return "";
	}

	@Override
	protected Class<Cidade> getClassImplement() {
		return Cidade.class;
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void saveNotReturn() throws Exception {
		list.clear();
		objectoSelecionado = cidadeController.merge(objectoSelecionado);
		list.add(objectoSelecionado);
		objectoSelecionado = new Cidade();
		sucesso();
	}

	@Override
	public String editar() throws Exception {

		return getUrl();
	}

	@Override
	public void excluir() throws Exception {
		objectoSelecionado = (Cidade) cidadeController.getSession().get(getClassImplement(),
				objectoSelecionado.getCid_codigo());
		cidadeController.delete(objectoSelecionado);
		list.remove(objectoSelecionado);
		novo();
		sucesso();

	}

	public List<Cidade> getList() throws Exception {
		list = cidadeController.findList(getClassImplement());
		return list;
	}

	@Override
	public String novo() throws Exception {
		setarVariaaveisNulas();
		return getUrl();
	}

	@Override
	public void setarVariaaveisNulas() throws Exception {
		list.clear();
		objectoSelecionado = new Cidade();
	}

	public String getUrl() {
		return url;
	}

	public Cidade getObjectoSelecionado() {
		return objectoSelecionado;
	}

	public void setObjectoSelecionado(Cidade objectoSeleciondao) {
		this.objectoSelecionado = objectoSeleciondao;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		
		super.setNomeRelatorioJasper("report_cidade");
		super.setNomeRelatorioSaida("report_cidade");
		super.setListDataBeanCollectionReport(cidadeController.findList(getClassImplement()));
		return super.getArquivoReport();
	}

	@Override
	public String redirecionarFindEntidate() throws Exception {
		setarVariaaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Cidade> getController() {
		return cidadeController;
	}
}
