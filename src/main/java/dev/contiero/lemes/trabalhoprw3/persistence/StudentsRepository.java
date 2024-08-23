package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;

import java.util.Map;
import java.util.Optional;

public interface StudentsRepository {
    Map<Aluno, Long> getAll();

    Optional<Aluno> getById(long id);

    Optional<Aluno> getByRa(String ra);

    Map<Aluno, Long> getByName(String name);

    boolean save(Aluno aluno);

    boolean update(Aluno aluno);

    boolean delete(Aluno aluno);

}
