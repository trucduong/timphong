package service.auth.dao;

import org.springframework.stereotype.Repository;

import core.dao.dao.BaseDao;
import service.auth.entities.UserProfile;

@Repository
public class UserProfileDao extends BaseDao<UserProfile> {

	private static final long serialVersionUID = 1L;

}
