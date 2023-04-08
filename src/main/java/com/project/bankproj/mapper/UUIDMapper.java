package com.project.bankproj.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UUIDMapper {
    default String toString(UUID uuid) {
        return uuid.toString();
    }

    default UUID fromString(String uuid) {
        return UUID.fromString(uuid);
    }
}