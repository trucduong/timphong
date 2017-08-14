package web.business.services;

import core.service.invoke.ServiceBuilder;
import core.service.invoke.ServiceResult;
import core.service.invoke.ServiceResultHelper;
import service.auth.shared.dto.UserAccountDto;
import service.auth.shared.service.AuthServiceConst;

public class AuthService {
	public UserAccountDto getUser(long id) {
        ServiceBuilder builder = ServiceBuilder.newService(AuthServiceConst.SERVICE_CONFIG_NAME,
        		AuthServiceConst.USERACCOUNT_SERVICE, AuthServiceConst.READ);

        builder.addParam(UserAccountDto.ID, id);
        
        ServiceResult result = builder.get();
        if (ServiceResultHelper.isSuccess(result)) {
            return ServiceResultHelper.getValue(result, UserAccountDto.class);
        }
        
        return new UserAccountDto();
    }
}
