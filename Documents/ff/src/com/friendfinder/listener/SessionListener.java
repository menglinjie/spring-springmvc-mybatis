package com.friendfinder.listener;

import java.util.*;

import javax.servlet.http.*;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	//保存当前登录用户名
	private static Map<HttpSession, String> loginUsers = new HashMap<HttpSession, String>();
	public static String SESSION_LOGIN_NAME = "id";
	 
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if(se.getName().equals(SESSION_LOGIN_NAME)){
			loginUsers.put(se.getSession(), se.getValue().toString());
		}	
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if(se.getName().equals(SESSION_LOGIN_NAME)){
			loginUsers.remove(se.getSession());
		}	
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		if(se.getName().equals(SESSION_LOGIN_NAME)){
			loginUsers.put(se.getSession(), se.getValue().toString());
		}	
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(((HttpSessionBindingEvent) se).getName().equals(SESSION_LOGIN_NAME)){
			try{
				loginUsers.remove(se.getSession());
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}	
	
	public static final boolean isLoginUser(String userName){
        Set<HttpSession> keys = loginUsers.keySet();
        for(HttpSession key: keys){
            if(loginUsers.get(key).equals(userName)){
                return true;
            }
        }
        return false;
    }
}
