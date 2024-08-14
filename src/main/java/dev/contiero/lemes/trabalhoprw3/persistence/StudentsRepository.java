package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;

import java.util.Map;
import java.util.Optional;

public interface StudentsRepository {
    Map<AlunoDTO, Long> getAll();

    Optional<AlunoDTO> getById(long id);

    Optional<AlunoDTO> getByRa(String ra);

    Map<AlunoDTO, Long> getByName(String name);

    boolean save(AlunoDTO aluno);

    boolean update(AlunoDTO aluno);

    boolean delete(AlunoDTO aluno);


}
