package ua.com.alevel.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.data.DataContainer;
import ua.com.alevel.entity.User;
import ua.com.alevel.servive.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody @Valid User user) {
        userService.create(user);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @GetMapping
    public ResponseEntity<DataContainer<List<User>>> readAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new DataContainer<>(userService.readAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<User>> readById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(userService.readById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DataContainer<User>> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(new DataContainer<>(userService.readByEmail(email)));
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
