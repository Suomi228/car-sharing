package org.example.carsharing.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {<S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
    List<T> findAll();
    Iterable<T> findAllById(Iterable<ID> ids);
    T findById(ID id);
    boolean existsById(ID id);
}
