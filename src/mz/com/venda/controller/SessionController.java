package mz.com.venda.controller;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public interface SessionController extends Serializable{

	
	void addSession(String KeyLoginUser,HttpSession httpSession);
	
	void invalidateSession(String KeyLoginUser);
}
