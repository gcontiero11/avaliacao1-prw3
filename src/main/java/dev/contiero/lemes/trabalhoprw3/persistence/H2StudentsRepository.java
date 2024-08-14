package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;

import java.util.Map;
import java.util.Optional;

public class H2StudentsRepository implements StudentsRepository{
    @Override
    public Map<AlunoDTO, Long> getAll() {
        return Map.of();
    }

    @Override
    public Optional<AlunoDTO> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<AlunoDTO> getByRa(String ra) {
        return Optional.empty();
    }

    @Override
    public Map<AlunoDTO, Long> getByName(String name) {
        return Map.of();
    }

    @Override
    public boolean save(AlunoDTO aluno) {
        return false;
    }

    @Override
    public boolean update(AlunoDTO aluno) {
        return false;
    }

    @Override
    public boolean delete(AlunoDTO aluno) {
        return false;
    }
}
