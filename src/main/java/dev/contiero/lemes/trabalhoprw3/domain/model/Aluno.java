package dev.contiero.lemes.trabalhoprw3.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Aluno {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Aluno() {}

    public Aluno(String nome, String ra, String email) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = BigDecimal.ZERO;
        this.nota2 = BigDecimal.ZERO;
        this.nota3 = BigDecimal.ZERO;
    }

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;
        return id == aluno.id || ra.equals(aluno.ra);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + ra.hashCode();
        return result;
    }
}
