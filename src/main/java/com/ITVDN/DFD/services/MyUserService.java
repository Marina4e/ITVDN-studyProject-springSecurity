package com.ITVDN.DFD.services;

import com.ITVDN.DFD.entities.Role;
import com.ITVDN.DFD.entities.User;
import com.ITVDN.DFD.repositories.UserRepository;
import com.ITVDN.DFD.services.Interfaces.IMyUserService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class MyUserService implements IMyUserService {

    private final UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public void createUser(User user) {

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    @PostAuthorize("returnObject.username == authentication.name")
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
//    @PostFilter("filterObject.roles.size() > 1")
    public List<User> getList() {
        return userRepository.findAll();
    }
}
