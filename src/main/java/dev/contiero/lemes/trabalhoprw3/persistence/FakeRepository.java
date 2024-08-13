package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;


import java.util.Map;
import java.util.Optional;

public class FakeRepository implements StudentsRepository {
    private Map<AlunoDTO, Long> alunos;

    FakeRepository() {
    }

    FakeRepository(Map<AlunoDTO, Long> alunos) {

    }

    @Override
    public Map<AlunoDTO, Long> getAll() {
        return alunos;
    }

    @Override
    public Optional<AlunoDTO> getById() {
        return Optional.empty();
    }

    @Override
    public Optional<AlunoDTO> getByRa() {
        return Optional.empty();
    }

    @Override
    public Map<AlunoDTO, Long> getByName() {
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
