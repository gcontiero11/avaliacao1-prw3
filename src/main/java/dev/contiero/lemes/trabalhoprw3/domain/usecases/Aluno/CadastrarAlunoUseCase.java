package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

public class CadastrarAlunoUseCase {
    private final StudentsRepository repository;

    public CadastrarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public void salvar(Aluno aluno) {
        repository.save(aluno);
    }
}
