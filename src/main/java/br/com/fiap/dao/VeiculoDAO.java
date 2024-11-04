package br.com.fiap.dao;

import br.com.fiap.to.VeiculoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO extends Repository {
    public ArrayList<VeiculoTO> findAll() {
        ArrayList<VeiculoTO> veiculos = new ArrayList<>();
        String sql = "select * from challenge_veiculo order by codigo";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    VeiculoTO veiculo = new VeiculoTO();
                    veiculo.setCodigo(rs.getLong("codigo"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculos.add(veiculo);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return veiculos;
    }

    public VeiculoTO findByCodigo(Long codigo) {
        VeiculoTO veiculo = new VeiculoTO();
        String sql = "select * from challenge_veiculo where codigo = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                veiculo.setCodigo(rs.getLong("codigo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
            } else {
                return null;
            }
        }catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return veiculo;
    }

    public VeiculoTO save(VeiculoTO veiculo) {
        String sql = "insert into challenge_veiculo(marca,modelo,placa) values(?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            if (ps.executeUpdate() > 0) {
                return veiculo;
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
        String sql = "delete from challenge_veiculo where codigo = ?";
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

    public VeiculoTO update(VeiculoTO veiculo) {
        String sql = "update challenge_veiculo set marca = ?, modelo = ?, placa = ? where codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            ps.setLong(4, veiculo.getCodigo());
            if (ps.executeUpdate() > 0) {
                return veiculo;
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
