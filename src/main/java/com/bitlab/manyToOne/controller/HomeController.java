package com.bitlab.manyToOne.controller;

import com.bitlab.manyToOne.models.Operator;
import com.bitlab.manyToOne.models.User;
import com.bitlab.manyToOne.repositories.OperatorRepository;
import com.bitlab.manyToOne.services.OperatorService;
import com.bitlab.manyToOne.services.RolesService;
import com.bitlab.manyToOne.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bitlab.manyToOne.models.ApplicationRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bitlab.manyToOne.services.ApplicationRequestService;
import com.bitlab.manyToOne.config.*;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRequestService applicationRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private OperatorService operatorService;


    @GetMapping("/")
    public String homePage(Model model){
        List<ApplicationRequest> task =  applicationRequestService.getTasks();
        model.addAttribute("task",task);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newRequest", new ApplicationRequest());
        return "ex";
    }

    @PostMapping("/add-request")
    public String addRequest(@ModelAttribute("newRequest") ApplicationRequest newRequest) {
        User newUser = new User();
        newUser.setFullName(newRequest.getUser().getFullName());
        newRequest.setUser(userService.addUser(newUser));
        newRequest.setHandled(false);
        applicationRequestService.addNewTask(newRequest);
        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        ApplicationRequest task = applicationRequestService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("operators", task.getOperators());
        return "details";
    }

    @PostMapping("/make-done/{id}")
    public String makeDone(
            @PathVariable Long id,
            @RequestParam("selectedOperators") List<Long> selectedOperatorIds) {
        ApplicationRequest task = applicationRequestService.getTaskById(id);
        if (task != null) {
            List<Operator> selectedOperators = operatorService.getOperatorById(selectedOperatorIds);
            task.setOperators((Set<Operator>) selectedOperators);
            task.setHandled(true);
            applicationRequestService.updateTask(task);
        }
        return "redirect:/details/" + id;
    }

    @PostMapping("/delete-task/{id}")
    public String deletePage(@PathVariable Long id){
        applicationRequestService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/admin-panel")
    public String admin(Model model){
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", rolesService.getRoles());
        return "admin";
    }

    @PostMapping("/add-role")
    public String addRoleToUser(@RequestParam("user_id") Long userId,
                                @RequestParam("role_id") Long roleId) {
        userService.addRole(userId, roleId);
        return "redirect:/admin-panel";
    }
    //@PostMapping("/delete-role")

    @PostMapping("/delete-role")
    public String deleteRoleToUser(@RequestParam("user_id") Long userId,
                                @RequestParam("role_id") Long roleId) {
        userService.deleteRole(userId, roleId);
        return "redirect:/admin-panel";
    }
}
//  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"