package mz.com.venda.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import mz.com.venda.model.InformacaoRevisao;
import mz.com.venda.model.Usuario;
import mz.com.venda.utils.UtilVenda;

@Service
public class CustomListener implements RevisionListener, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {
		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntity;

		Long CodigoUsuario = UtilVenda.getThreadLocal().get();

		Usuario usuario = new Usuario();
		if (CodigoUsuario != null && CodigoUsuario != 0L) {
			usuario.setCodigo(CodigoUsuario);

			informacaoRevisao.setUsuario(usuario);
		}

	}

}
