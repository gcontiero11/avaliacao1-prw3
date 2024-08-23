package dev.contiero.lemes.trabalhoprw3.domain.usecases.utils;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;
import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;
import dev.contiero.lemes.trabalhoprw3.persistence.StudentsRepository;

import java.util.Optional;

public class Converter {

    private final StudentsRepository repository;

    public Converter(StudentsRepository repository) {
        this.repository = repository;
    }

    public static AlunoDTO toDto(Aluno aluno) {
        if (aluno == null) {
            return null;
        }
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getRa(),
                aluno.getEmail(),
                aluno.getNota1(),
                aluno.getNota2(),
                aluno.getNota3()
        );
    }

    public Aluno fromDto(AlunoDTO alunoDto) {
        if (alunoDto == null) {
            return null;
        }

        Optional<AlunoDTO> alunoExistenteOptional = repository.getById(alunoDto.getId());
        if (alunoExistenteOptional.isPresent()) {
            return fromDtoToAluno(alunoExistenteOptional.get());
        }

        Optional<AlunoDTO> alunoPorRaOptional = repository.getByRa(alunoDto.getRa());
        if (alunoPorRaOptional.isPresent()) {
            return fromDtoToAluno(alunoPorRaOptional.get());
        }

        Aluno novoAluno = new Aluno(
                alunoDto.getNome(),
                alunoDto.getRa(),
                alunoDto.getEmail(),
                alunoDto.getNota1(),
                alunoDto.getNota2(),
                alunoDto.getNota3()
        );

        novoAluno.setId(alunoDto.getId());

        repository.save(toDto(novoAluno));

        return novoAluno;
    }

    private Aluno fromDtoToAluno(AlunoDTO alunoDto) {
        return new Aluno(
                alunoDto.getNome(),
                alunoDto.getRa(),
                alunoDto.getEmail(),
                alunoDto.getNota1(),
                alunoDto.getNota2(),
                alunoDto.getNota3()
        );
    }
}
