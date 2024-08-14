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

    // Converte um objeto Aluno para AlunoDTO
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

    // Converte um objeto AlunoDTO para Aluno, verificando se já existe no sistema
    public Aluno fromDto(AlunoDTO alunoDto) {
        if (alunoDto == null) {
            return null;
        }

        // Verifica se o aluno já existe no repositório pelo ID
        Optional<AlunoDTO> alunoExistenteOptional = repository.getById(alunoDto.getId());
        if (alunoExistenteOptional.isPresent()) {
            return fromDtoToAluno(alunoExistenteOptional.get()); // Converte o AlunoDTO existente para Aluno e retorna
        }

        // Verifica se já existe um aluno com o mesmo RA no repositório
        Optional<AlunoDTO> alunoPorRaOptional = repository.getByRa(alunoDto.getRa());
        if (alunoPorRaOptional.isPresent()) {
            return fromDtoToAluno(alunoPorRaOptional.get()); // Converte o AlunoDTO existente para Aluno e retorna
        }

        // Se não existe, cria um novo objeto Aluno
        Aluno novoAluno = new Aluno(
                alunoDto.getNome(),
                alunoDto.getRa(),
                alunoDto.getEmail(),
                alunoDto.getNota1(),
                alunoDto.getNota2(),
                alunoDto.getNota3()
        );

        // Define o ID do novo aluno para coincidir com o do DTO
        novoAluno.setId(alunoDto.getId());

        // Salva o novo aluno no repositório
        repository.save(toDto(novoAluno));

        return novoAluno;
    }

    // Método auxiliar para converter AlunoDTO para Aluno
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
