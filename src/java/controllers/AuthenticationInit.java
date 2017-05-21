/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controllers;

import services.AuthenticationServiceChapter8Impl;
import java.util.Map;

import services.AuthenticationService;
import services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationInit implements Initiator {

	//services
	AuthenticationService authService = (AuthenticationService) new AuthenticationServiceChapter8Impl();
	
        @Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		UserCredential cre = authService.getUserCredential();
		if(cre==null || cre.isAnonymous()){
			Executions.sendRedirect("/login.zul");
		}
	}
}