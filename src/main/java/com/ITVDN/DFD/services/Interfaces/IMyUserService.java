package com.ITVDN.DFD.services.Interfaces;

import com.ITVDN.DFD.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMyUserService {
    @Transactional
    boolean userExists(String username);

    @Transactional
    void createUser(User user);

    @Transactional
    User get(Long id);

    @Transactional
    List<User> getList();
}
