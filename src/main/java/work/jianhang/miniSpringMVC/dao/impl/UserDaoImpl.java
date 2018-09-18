package work.jianhang.miniSpringMVC.dao.impl;

import work.jianhang.miniSpringMVC.annotation.Repository;
import work.jianhang.miniSpringMVC.dao.UserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    public void insert() {
        System.out.println("execute UserDaoImpl.insert()");
    }
}
