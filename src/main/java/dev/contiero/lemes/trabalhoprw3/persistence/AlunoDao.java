package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.Aluno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AlunoDao {

    EntityManagerFactory factory;

    public AlunoDao() {
        this.factory = Persistence.createEntityManagerFactory("banco");
    }


    public Map<Aluno, Long> getAll() {
        String jpql = "SELECT a FROM Aluno a";
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        List<Aluno> resultList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        Map<Aluno, Long> alunos = new HashMap<>();
        for (Aluno aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }


    public Optional<Aluno> getById(long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Aluno aluno = em.find(Aluno.class, id);
        em.getTransaction().commit();
        em.close();
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }


    public Optional<Aluno> getByRa(String ra) {
        String jpql = "SELECT a FROM Aluno a WHERE a.ra = :ra";
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("ra", ra);
        List<Aluno> resultList = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }


    public Map<Aluno, Long> getByName(String name) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :name";
        EntityManager em = factory.createEntityManager();
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("name", name);
        System.out.println(query.getResultStream());
        List<Aluno> resultList = query.getResultList();
        Map<Aluno, Long> alunos = new HashMap<>();
        for (Aluno aluno : resultList) {
            alunos.put(aluno, aluno.getId());
        }
        return alunos;
    }


    public boolean save(Aluno aluno) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Aluno aluno) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Aluno aluno) {
        EntityManager em = factory.createEntityManager();
        boolean sucesso = false;
        try {
            em.getTransaction().begin();
            Aluno alunoToDelete = em.find(Aluno.class, aluno.getId());
            if (alunoToDelete != null) {
                em.remove(alunoToDelete);
                sucesso = true;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return sucesso;
    }
}
