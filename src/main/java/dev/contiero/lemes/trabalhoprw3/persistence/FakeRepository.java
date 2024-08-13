package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeRepository implements StudentsRepository {
    private Map<AlunoDTO, Long> alunos = new HashMap<>();

    public FakeRepository() {
    }

    public FakeRepository(Map<AlunoDTO, Long> alunos) {
        this.alunos = alunos;
    }

    // TODO - getAll
    @Override
    public Map<AlunoDTO, Long> getAll() {
        return alunos;
    }

    // TODO - getById
    @Override
    public Optional<AlunoDTO> getById() {
        return Optional.empty();
    }

    // TODO - getByRa
    @Override
    public Optional<AlunoDTO> getByRa() {
        return Optional.empty();
    }

    // TODO - getByName
    @Override
    public Map<AlunoDTO, Long> getByName() {
        return Map.of();
    }

    // TODO - save
    @Override
    public boolean save(AlunoDTO aluno) {
        return false;
    }

    // TODO - update
    @Override
    public boolean update(AlunoDTO aluno) {
        return false;
    }

    // TODO - delete
    @Override
    public boolean delete(AlunoDTO aluno) {
        return false;
    }
}
