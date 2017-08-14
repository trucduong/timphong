package service.auth.web.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.common.encode.PasswordEncoder;
import core.common.exception.CommonException;
import core.service.invoke.ServiceResult;
import core.service.security.AuthorizationUser;
import core.service.service.BaseService;
import core.service.utils.ServiceErrorCode;
import service.auth.dao.PermissionDao;
import service.auth.dao.UserAccountDao;
import service.auth.entities.AuthPermission;
import service.auth.entities.UserAccount;
import service.auth.shared.dto.AuthPermissionDto;
import service.auth.shared.dto.AuthorizationUserDto;
import service.auth.shared.dto.PermissionStatus;
import service.auth.shared.dto.UserAccountDto;
import service.auth.shared.service.AuthServiceConst;

@RestController
@RequestMapping(AuthServiceConst.AUTH_SERVICE)
public class AuthService extends BaseService {
	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private UserAccountDao accountDao;

	@RequestMapping(value = AuthServiceConst.PERMISSION_READ_ALL, method = RequestMethod.GET)
	public ServiceResult readAll() throws CommonException {
		List<AuthPermission> entities = permissionDao.getAllData();

		if (entities == null || entities.isEmpty()) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		List<AuthPermissionDto> dtos = new ArrayList<>();
		for (AuthPermission e : entities) {
			if (PermissionStatus.VISIBLE.equals(e.getStatus())) {
				AuthPermissionDto d = new AuthPermissionDto();
				e.unBind(d);
				dtos.add(d);
			}
		}

		return success(dtos);
	}

	@Override
	protected Class<?> getThis() {
		return this.getClass();
	}

	@RequestMapping(value = AuthServiceConst.LOGIN, method = RequestMethod.POST)
	public ServiceResult login(@RequestBody UserAccountDto user) throws CommonException {
		List<UserAccount> accounts = accountDao.getAllDataByColumn(UserAccount.LOGIN_NAME, user.getLoginName());
		if (accounts.isEmpty()) {
			return error(ServiceErrorCode.LOGIN_ERROR);
		}
		
		UserAccount account = accounts.get(0);
		if (!PasswordEncoder.isValid(user.getPassword(), account.getPassword())) {
			return error(ServiceErrorCode.LOGIN_ERROR);
		}

		AuthorizationUser authUser = new AuthorizationUser();
		authUser.setLoginName(user.getLoginName());
		authUser.setLoggedTime(new Date());

		List<AuthPermission> permissions = permissionDao.getAllData();
		if (!permissions.isEmpty()) {
			authUser.setPermissions(new ArrayList<String>());
			for (AuthPermission authPermission : permissions) {
				authUser.getPermissions().add(authPermission.getAction());
			}
		}

		//return success(new AuthorizationUserDto(authUser));
		return success(new AuthorizationUserDto());
	}

	@RequestMapping(value = AuthServiceConst.LOGOUT, method = RequestMethod.POST)
	public ServiceResult logout(@RequestBody String authToken) throws CommonException {
		// AuthorizationUser authUser = AuthorizationUser.fromString(authToken);
		// if (!authUser.isAuthenticated()) {
		// return error(ServiceErrorCode.LOGOUT_ERROR);
		// }

		return success(true);
	}
}
