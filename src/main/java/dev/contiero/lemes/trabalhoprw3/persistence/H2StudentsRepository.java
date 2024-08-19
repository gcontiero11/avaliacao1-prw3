package dev.contiero.lemes.trabalhoprw3.persistence;

import dev.contiero.lemes.trabalhoprw3.domain.model.AlunoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class H2StudentsRepository implements StudentsRepository {

    @Override
    public Map<AlunoDTO, Long> getAll() {
        String sql = "SELECT * FROM Aluno";
        Map<AlunoDTO, Long> alunos = new HashMap<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AlunoDTO aluno = mapResultSetToAlunoDTO(rs);
                alunos.put(aluno, aluno.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public Optional<AlunoDTO> getById(long id) {
        String sql = "SELECT * FROM Aluno WHERE Id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AlunoDTO aluno = mapResultSetToAlunoDTO(rs);
                    return Optional.of(aluno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<AlunoDTO> getByRa(String ra) {
        String sql = "SELECT * FROM Aluno WHERE Ra = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AlunoDTO aluno = mapResultSetToAlunoDTO(rs);
                    return Optional.of(aluno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Map<AlunoDTO, Long> getByName(String name) {
        String sql = "SELECT * FROM Aluno WHERE Nome = ?";
        Map<AlunoDTO, Long> alunos = new HashMap<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AlunoDTO aluno = mapResultSetToAlunoDTO(rs);
                    alunos.put(aluno, aluno.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public boolean save(AlunoDTO aluno) {
        String sql = "INSERT INTO Aluno (Id, Nome, Ra, Email, Nota1, Nota2, Nota3) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getRa());
            stmt.setString(4, aluno.getEmail());
            stmt.setBigDecimal(5, aluno.getNota1());
            stmt.setBigDecimal(6, aluno.getNota2());
            stmt.setBigDecimal(7, aluno.getNota3());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(AlunoDTO aluno) {
        String sql = "UPDATE Aluno SET Nome = ?, Ra = ?, Email = ?, Nota1 = ?, Nota2 = ?, Nota3 = ? WHERE Id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getRa());
            stmt.setString(3, aluno.getEmail());
            stmt.setBigDecimal(4, aluno.getNota1());
            stmt.setBigDecimal(5, aluno.getNota2());
            stmt.setBigDecimal(6, aluno.getNota3());
            stmt.setLong(7, aluno.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(AlunoDTO aluno) {
        String sql = "DELETE FROM Aluno WHERE Id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, aluno.getId());

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private AlunoDTO mapResultSetToAlunoDTO(ResultSet rs) throws SQLException {
        return new AlunoDTO(
                rs.getLong("Id"),
                rs.getString("Nome"),
                rs.getString("Ra"),
                rs.getString("Email"),
                rs.getBigDecimal("Nota1"),
                rs.getBigDecimal("Nota2"),
                rs.getBigDecimal("Nota3")
        );
    }
}
