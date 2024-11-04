package br.com.fiap.dao;

import br.com.fiap.to.ClienteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO extends Repository{
    public ArrayList<ClienteTO> findAll() {
        ArrayList<ClienteTO> clientes = new ArrayList<>();
        String sql = "select * from challenge_cliente order by codigo";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ClienteTO cliente = new ClienteTO();
                    cliente.setCodigo(rs.getLong("codigo"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    clientes.add(cliente);
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

    public ClienteTO findByCodigo(Long codigo) {
        ClienteTO cliente = new ClienteTO();
        String sql = "select * from challenge_cliente where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setCodigo(rs.getLong("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cliente;
    }

    public ClienteTO save(ClienteTO cliente) {
        String sql = "insert into challenge_cliente(nome, cpf, email) values(?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() > 0) {
                   return cliente;
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
        String sql = "delete from challenge_cliente where codigo = ?";
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

    public ClienteTO update(ClienteTO cliente) {
        String sql = "update challenge_cliente set nome = ?, cpf = ?, email = ? where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setLong(4, cliente.getCodigo());
            if (ps.executeUpdate() > 0) {
                return cliente;
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
