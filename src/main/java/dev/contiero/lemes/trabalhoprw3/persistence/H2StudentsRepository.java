package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class H2StudentsRepository implements StudentsRepository {

    private final EntityManager em;

    public H2StudentsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Map<AlunoDTO, Long> getAll() {
        String jpql = "SELECT a FROM AlunoDTO a";
        TypedQuery<AlunoDTO> query = em.createQuery(jpql, AlunoDTO.class);
        List<AlunoDTO> resultList = query.getResultList();

        Map<AlunoDTO, Long> alunos = new HashMap<>();
        for (AlunoDTO aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }

    @Override
    public Optional<AlunoDTO> getById(long id) {
        AlunoDTO aluno = em.find(AlunoDTO.class, id);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    @Override
    public Optional<AlunoDTO> getByRa(String ra) {
        String jpql = "SELECT a FROM AlunoDTO a WHERE a.ra = :ra";
        TypedQuery<AlunoDTO> query = em.createQuery(jpql, AlunoDTO.class);
        query.setParameter("ra", ra);

        List<AlunoDTO> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public Map<AlunoDTO, Long> getByName(String name) {
        String jpql = "SELECT a FROM AlunoDTO a WHERE a.nome = :name";
        TypedQuery<AlunoDTO> query = em.createQuery(jpql, AlunoDTO.class);
        query.setParameter("name", name);

        List<AlunoDTO> resultList = query.getResultList();
        Map<AlunoDTO, Long> alunos = new HashMap<>();
        for (AlunoDTO aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }

    @Override
    public boolean save(AlunoDTO aluno) {
        try {
            em.persist(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AlunoDTO aluno) {
        try {
            em.merge(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(AlunoDTO aluno) {
        try {
            AlunoDTO alunoToDelete = em.find(AlunoDTO.class, aluno.getId());
            if (alunoToDelete != null) {
                em.remove(alunoToDelete);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
