package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

public class AlterarAlunoUseCase {
    private final StudentsRepository repository;

    public AlterarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public void alterar(Aluno aluno) {
        repository.update(aluno);
    }
}
