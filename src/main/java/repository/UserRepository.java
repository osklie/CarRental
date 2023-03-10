package repository;

import domain.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long userId);

    @Override
    User save(User user);

    @Override
    void deleteById(Long userId);
}