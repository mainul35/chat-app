package com.mainul35.chatapp.service;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final AuthRepository authRepository;

    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Set<Map<String, Object>> findUsers(String name) {
        Set<Map<String, Object>> userSet = new HashSet<>();
        List<Optional<AuthUser>> authUsers = new ArrayList<>();
        Set<String> list = structureName(name);
        list.iterator().forEachRemaining(s -> authUsers.addAll(authRepository.findByNameLike(s)));
        authUsers.stream().forEach(authUser -> userSet.addAll(Collections.singleton(authUser.get().getUserDetails())));
        return userSet;
    }

    private String addWildcard(String name) {
        return "%" + name + "%";
    }

    private Set<String> structureName(String name) {
        Set<String> combinations = new HashSet<>();
        name = name.trim();
        if(name.contains(" ")) {
            List<String> list = Arrays.asList(name.split(" "));
            Set<String> splits = list.stream().map(s -> s = addWildcard(s)).collect(Collectors.toSet());
            combinations.addAll(splits);
        } else {
            name = addWildcard(name);
            combinations.addAll(Arrays.asList(name));
        }
        return combinations;
    }
}
