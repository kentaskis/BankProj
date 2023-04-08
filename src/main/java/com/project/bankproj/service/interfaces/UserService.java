package com.project.bankproj.service.interfaces;

import com.project.bankproj.entity.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    Optional<User> getByLogin(@NonNull String login);
}