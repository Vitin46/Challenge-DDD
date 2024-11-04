package br.com.fiap.dao;

import br.com.fiap.to.ProblemasTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasDAO extends Repository {
    public ArrayList<ProblemasTO> findAll() {
        ArrayList<ProblemasTO> clientes = new ArrayList<>();
        String sql = "select * from challenge_problemas order by codigo";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProblemasTO problemas = new ProblemasTO();
                    problemas.setCodigo(rs.getLong("codigo"));
                    problemas.setDescricao(rs.getString("descricao"));

                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return clientes;
    }

    public ProblemasTO findByCodigo(Long codigo) {
        ProblemasTO problemas = new ProblemasTO();
        String sql = "select * from challenge_problemas where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                problemas.setCodigo(rs.getLong("codigo"));
                problemas.setDescricao(rs.getString("descricao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return problemas;
    }

    public ProblemasTO save(ProblemasTO problemas) {
        String sql = "insert into challenge_problemas(descricao) values(?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, problemas.getDescricao());
            if (ps.executeUpdate() > 0) {
                return problemas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "delete from challenge_problemas where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ProblemasTO update(ProblemasTO problemas) {
        String sql = "update challenge_problemas set descricao = ? where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, problemas.getDescricao());
            ps.setLong(2, problemas.getCodigo());
            if (ps.executeUpdate() > 0) {
                return problemas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
}