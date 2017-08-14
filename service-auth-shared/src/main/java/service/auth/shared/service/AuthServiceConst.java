package service.auth.shared.service;

import core.shared.utils.CRUDServiceConst;

public class AuthServiceConst extends CRUDServiceConst {
	public static final String SERVICE_CONFIG_NAME = "service.auth";
	
	public static final String USERACCOUNT_SERVICE = "/user-account";
	public static final String AUTH_SERVICE = "/auth";
	
	public static final String GET_ACCOUNT_BY_NAME = "/account/{name}";
	public static final String GET_ACCOUNT_BY_NAME__NAME = "name";
	
	public static final String UPDATE_PASSWORD = "/update-password/{name}";
	public static final String UPDATE_PERMISSIONS = "/update-permissions/{name}";
	
	public static final String PERMISSION_READ_ALL = "/permission/read-all";
	
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	
}
