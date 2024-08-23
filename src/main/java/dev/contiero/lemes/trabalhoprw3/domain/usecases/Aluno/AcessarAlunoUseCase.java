package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

import java.util.Map;
import java.util.Optional;

public class AcessarAlunoUseCase {
    private final StudentsRepository repository;

    public AcessarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Aluno, Long> acessarTodos() {
        return repository.getAll();
    }

    public Aluno acessarPorId(long id) {
        Optional<Aluno> aluno = repository.getById(id);
        return aluno.orElse(null);
    }

    public Aluno acessarPorRA(String ra) {
        return repository.getByRa(ra).orElse(null);
    }

    public Map<Aluno, Long> acessarPeloNome(String name) {

        return repository.getByName(name);
    }
}
