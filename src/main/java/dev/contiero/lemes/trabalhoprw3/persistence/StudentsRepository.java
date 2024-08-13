package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;

import java.util.Map;
import java.util.Optional;

public interface StudentsRepository {
    Map<AlunoDTO, Long> getAll();

    Optional<AlunoDTO> getById();

    Optional<AlunoDTO> getByRa();

    Map<AlunoDTO, Long> getByName();

    boolean save(AlunoDTO aluno);

    boolean update(AlunoDTO aluno);

    boolean delete(AlunoDTO aluno);


}
