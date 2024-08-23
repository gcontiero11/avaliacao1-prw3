package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;

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
    public Map<Aluno, Long> getAll() {
        String jpql = "SELECT a FROM Aluno a";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        List<Aluno> resultList = query.getResultList();

        Map<Aluno, Long> alunos = new HashMap<>();
        for (Aluno aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }

    @Override
    public Optional<Aluno> getById(long id) {
        Aluno aluno = em.find(Aluno.class, id);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    @Override
    public Optional<Aluno> getByRa(String ra) {
        String jpql = "SELECT a FROM Aluno a WHERE a.ra = :ra";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("ra", ra);

        List<Aluno> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public Map<Aluno, Long> getByName(String name) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :name";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("name", name);

        List<Aluno> resultList = query.getResultList();
        Map<Aluno, Long> alunos = new HashMap<>();
        for (Aluno aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }

    @Override
    public boolean save(Aluno aluno) {
        try {
            em.persist(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Aluno aluno) {
        try {
            em.merge(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Aluno aluno) {
        try {
            Aluno alunoToDelete = em.find(Aluno.class, aluno.getId());
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
