package com.mainul35.chatapp.repository;

import com.mainul35.chatapp.entity.security.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AuthRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByEmailIgnoreCase(String email);

    Optional<AuthUser> findByUsernameIgnoreCase(String username);

    Optional<AuthUser> findByPhone(String phone);

    List<Optional<AuthUser>> findByNameLike(String name);
}
