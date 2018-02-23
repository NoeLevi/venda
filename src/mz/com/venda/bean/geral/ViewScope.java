package mz.com.venda.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope,Serializable{


	private static final long serialVersionUID = 1L;
	public static final String VIEW_SCOPE_CALLBACKS="viewScope.callBacks";
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		
		Object instance = getViewMap().get(name);
		
		if(instance==null){
			instance=objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	@Override
	public String getConversationId() {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);

			return facesRequestAttributes.getSessionId()+"-"+facesContext.getViewRoot().getViewId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void registerDestructionCallback(String arg0, Runnable runnable) {
				Map<String, Runnable> callBacks =(Map<String,Runnable>)getViewMap().get(VIEW_SCOPE_CALLBACKS);
				if(callBacks!=null){
					callBacks.put(VIEW_SCOPE_CALLBACKS, runnable);
					
				}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object remove(String arg0) {

		Object instance=getViewMap().remove(arg0);
		if(instance!=null){
			Map<String, Runnable> callBacks=(Map<String,Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if(callBacks!=null){
				callBacks.remove(arg0);
			}
		}	
		
		
		return instance;
	}

	@Override
	public Object resolveContextualObject(String arg0) {
FacesContext facesContext = FacesContext.getCurrentInstance();
FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
return facesRequestAttributes.resolveReference(arg0);
	}
	
	// getViewRoot()
	// Retorna o componente raiz ques esta associado a esta solicitacao (request)
	// Retorna um Map que atua como a interface para o armamento de dados
	
	private Map<String, Object> getViewMap(){
		
		return FacesContext.getCurrentInstance()!=null ? FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				: new HashMap<String, Object>();
	}

}
