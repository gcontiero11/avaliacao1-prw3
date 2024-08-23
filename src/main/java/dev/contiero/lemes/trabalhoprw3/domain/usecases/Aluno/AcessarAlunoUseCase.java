package dev.contiero.lemes.trabalhoprw3.domain.usecases.Aluno;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AcessarAlunoUseCase {
    private final StudentsRepository repository;

    public AcessarAlunoUseCase(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Aluno, Long> acessarTodos() {
        Map<AlunoDTO, Long> alunoDTOs = repository.getAll();
        Map<Aluno, Long> alunos = new HashMap<>();

        for (Map.Entry<AlunoDTO, Long> entry : alunoDTOs.entrySet()) {
            Aluno aluno = converter.fromDto(entry.getKey());
            alunos.put(aluno, entry.getValue());
        }
        return alunos;
    }

    public Aluno acessarPorId(long id) {
        Optional<AlunoDTO> alunoDTO = repository.getById(id);
        return alunoDTO.map(converter::fromDto).orElse(null);
    }

    public Aluno acessarPorRA(String ra) {
        Optional<AlunoDTO> alunoDTO = repository.getByRa(ra);
        return alunoDTO.map(converter::fromDto).orElse(null);
    }

    public Map<Aluno, Long> acessarPeloNome(String name) {
        Map<AlunoDTO, Long> alunoDTOs = repository.getByName(name);
        Map<Aluno, Long> alunos = new HashMap<>();

        for (Map.Entry<AlunoDTO, Long> entry : alunoDTOs.entrySet()) {
            Aluno aluno = converter.fromDto(entry.getKey());
            alunos.put(aluno, entry.getValue());
        }

        return alunos;
    }
}
