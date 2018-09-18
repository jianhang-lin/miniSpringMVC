package work.jianhang.miniSpringMVC.Service.impl;

import work.jianhang.miniSpringMVC.Service.UserService;
import work.jianhang.miniSpringMVC.annotation.Qualifier;
import work.jianhang.miniSpringMVC.annotation.Service;
import work.jianhang.miniSpringMVC.dao.UserDao;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {


    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public void insert() {
        System.out.println("UserServiceImpl.insert start");
        userDao.insert();
        System.out.println("UserServiceImpl.insert end");
    }
}
