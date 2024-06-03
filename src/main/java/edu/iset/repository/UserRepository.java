package edu.iset.repository;

import edu.iset.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    <UsreEntity> Iterable<UsreEntity> findAllByPosition(String position);

    Optional<UserEntity> findByEmail(String email);
}
