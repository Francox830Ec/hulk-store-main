package com.hulk.store.security.repository.contracts;

import com.hulk.store.security.repository.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByEmployeeId(String employeeId);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

}
