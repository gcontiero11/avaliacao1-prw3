package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;


import java.util.Map;

public class FakeRepository {
    private Map<Aluno, Long> alunos;

    FakeRepository() {
    }

    FakeRepository(Map<Aluno, Long> alunos) {

    }
}
