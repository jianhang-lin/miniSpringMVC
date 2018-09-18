package work.jianhang.miniSpringMVC.controller;

import work.jianhang.miniSpringMVC.Service.UserService;
import work.jianhang.miniSpringMVC.annotation.Controller;
import work.jianhang.miniSpringMVC.annotation.Qualifier;
import work.jianhang.miniSpringMVC.annotation.RequestMapping;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert() {
        userService.insert();
    }
}
