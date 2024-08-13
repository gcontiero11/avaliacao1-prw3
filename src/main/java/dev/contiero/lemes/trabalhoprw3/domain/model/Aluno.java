package dev.contiero.lemes.trabalhoprw3.domain.model;

import java.math.BigDecimal;

public class Aluno {
    private final long id;
    private String nome;
    private final String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    // TODO: ID autoincrement
    public Aluno(long id, String nome, String ra, String email, long id1, String nome1, String ra1, String email1) {
        this.id = id1;
        this.nome = nome1;
        this.ra = ra1;
        this.email = email1;
        nota1 = new BigDecimal(0);
        nota2 = new BigDecimal(0);
        nota3 = new BigDecimal(0);
    }

    public Aluno(long id, String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public long getId() {
        return id;
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
