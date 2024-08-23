package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

public class DeletarAlunoUseCase {
    private final StudentsRepository repository;

    public DeletarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public void deletar(Aluno aluno) {
        repository.save(aluno);
    }
}
