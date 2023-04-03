package com.project.bankproj.service;

import com.project.bankproj.entity.User;
import com.project.bankproj.repository.UserRepository;
import com.project.bankproj.service.interfaces.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findByLogin(login);
    }
}