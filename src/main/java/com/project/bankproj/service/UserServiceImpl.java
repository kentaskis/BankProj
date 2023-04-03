package com.project.bankproj.service;

import com.project.bankproj.entity.User;
import com.project.bankproj.entity.enums.Role;
import com.project.bankproj.repository.UserRepository;
import com.project.bankproj.service.interfaces.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User create() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);

        User user = new User(UUID.randomUUID(), "kentaskis", "kentaskis", roles, null, null);
        return userRepository.save(user);
    }
}