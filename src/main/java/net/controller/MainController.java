package net.controller;

import net.model.User;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"users"})
    public ModelAndView getAllUsers(@RequestParam(required = false) Integer page) {
        List<User> userList = userService.listUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(userList);
        pagedListHolder.setPageSize(7);

        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            page = 1;
        }
        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }
        return modelAndView;
    }
    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());

        return "addUser";
    }
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }

        return "redirect:/users";
    }
    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);

        return "redirect:/users";
    }
    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());

        return "editUser";
    }
    @RequestMapping(value = "/users/findList/", method = RequestMethod.POST)
    public String findList(@RequestParam String user, ModelMap model) {
        model.addAttribute("user", this.userService.findList(user));

        return "findUsersPage";
    }

}
