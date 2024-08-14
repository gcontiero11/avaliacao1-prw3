package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.domain.usecases.utils.Converter;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

import java.util.Map;

public class AcessarAlunoUseCase {
    private final StudentsRepository repository;

    public AcessarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Aluno, Long> acessarTodos() {
        repository.getAll();
        return null;
    }

    public Aluno acessarPorId(long id) {
        repository.getById(id);
        return null;
    }

    public Aluno acessarPorRA(String ra) {
        repository.getByRa(ra);
        return null;
    }

    public Map<Aluno, Long> acessarPeloNome(String name) {
        repository.getByName(name);
        return null;
    }
}
