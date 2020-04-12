package ua.com.alevel.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.data.DataContainer;
import ua.com.alevel.entity.User;
import ua.com.alevel.servive.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody @Valid User user) {
//        userService.create(user);
//        return ResponseEntity.ok(new DataContainer<>(true));
//    }

    @GetMapping
//    public String readAll(@RequestParam int page, @RequestParam int size) {
    public String readAll(Model model) {
        model.addAttribute("users", userService.readAll());
//        return ResponseEntity.ok(new DataContainer<>(userService.readAll(page, size)));
        return "users";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DataContainer<User>> readById(@PathVariable Long id) {
//        return ResponseEntity.ok(new DataContainer<>(userService.readById(id)));
//    }
//
//    @GetMapping("/email/{email}")
//    public ResponseEntity<DataContainer<User>> findByEmail(@PathVariable String email) {
//        return ResponseEntity.ok(new DataContainer<>(userService.readByEmail(email)));
//    }
//
//    @PutMapping
//    public void update(@RequestBody User user) {
//        userService.update(user);
//    }
//
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        System.out.println("id = " + id);
        model.addAttribute("user", userService.readById(id));
        return "update_user";
    }

    @PostMapping("/update")
    public String update(User user) {
        System.out.println("user = " + user.toString());
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("id = " + id);
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/add")
    public String create(User user) {
        System.out.println("user = " + user.toString());
        userService.create(user);
        return "redirect:/users";
    }
}
