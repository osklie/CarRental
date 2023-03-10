package repository;

import domain.dto.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {
    @Override
    List<Model> findAll();

    @Override
    Optional<Model> findById(Long modelId);

    @Override
    Model save(Model model);

    @Override
    void deleteById(Long modelId);
}