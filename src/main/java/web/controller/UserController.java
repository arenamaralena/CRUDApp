package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "/edit";
    }

    @PostMapping("/edit_ready")
    public String update(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "/delete";
    }

    @PostMapping(value = "/delete_ready")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
