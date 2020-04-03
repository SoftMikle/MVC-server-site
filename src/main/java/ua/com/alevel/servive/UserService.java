package ua.com.alevel.servive;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.entity.User;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.exception.FieldErrorModel;
import ua.com.alevel.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User user) {
        if (user.getId() != null) {
            throw new BadRequestException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.BAD_REQUEST.getReasonPhrase(), "id can not be not null")));
        }
        User user1 = readByEmail(user.getEmail());
        if (user1 != null) {
            throw new BadRequestException(Collections.singletonList(
                    new FieldErrorModel("email", HttpStatus.CONFLICT.getReasonPhrase(), "this email is exist")));
        }
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> readAll(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        if (users.getTotalElements() == 0) {
            return Collections.emptyList();
        }
        return users.getContent();
    }

    public User readById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User readByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(User user) {
        User user1 = userRepository.findById(user.getId()).orElse(null);
        if (user1 == null) {
            return;
        }
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void delete(Long id) {
        User user1 = userRepository.findById(id).orElse(null);
        if (user1 == null) {
            return;
        }
        userRepository.deleteById(id);
    }
}
