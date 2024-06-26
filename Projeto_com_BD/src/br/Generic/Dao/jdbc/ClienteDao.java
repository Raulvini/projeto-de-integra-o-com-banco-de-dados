package br.Generic.Dao.jdbc;

import br.classe.Cliente;
import br.conexao.jdbc.Conexaofactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements  IclienteDao{
    @Override
    public Integer cadastra(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Conexaofactory.getConection();
            String sql = getSQIsert();
            stm = conn.prepareStatement(sql);
            paramentros(stm,cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
            stm.close();
        }


    }

    @Override
    public Integer atualizar(String id,Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Conexaofactory.getConection();
            String sql = getSQUpdate();
            stm = conn.prepareStatement(sql);
            paramentrosUp(stm,cliente,id);

            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
            stm.close();
        }
    }

    @Override
    public Integer excluir(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Conexaofactory.getConection();
            String sql = getSQexcluir();
            stm = conn.prepareStatement(sql);
            paramentrosEx(stm,id);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
            stm.close();

        }
    }

    @Override
    public Cliente buscar(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try {
            conn = Conexaofactory.getConection();
            stm = conn.prepareStatement("SELECT * FROM TB_Cliente WHERE Codigo = ?");
            stm.setString(1,id);
            rs = stm.executeQuery();;
            if(rs.next()) {
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));

            }

            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
            stm.close();
            rs.close();
        }


    }

    @Override
    public List<Cliente> buscartodos() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();
        try {
            conn = Conexaofactory.getConection();
            stm = conn.prepareStatement("SELECT * FROM TB_CLIENTE");
            rs = stm.executeQuery();

            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("Codigo"));
                cliente.setNome(rs.getString("Nome"));
                lista.add(cliente);
            }
            return lista;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
            stm.close();
            rs.close();

        }
    }



    private String getSQIsert(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CLIENTE (ID,NOME,CODIGO) ");
        sb.append("VALUES(nextval('SQ_CLIENTE'),?,?);");
        return sb.toString();
    }
    private void paramentros(PreparedStatement stm,Cliente cliente) throws SQLException {
        stm.setString(1,cliente.getNome());
        stm.setString(2,cliente.getCodigo());
    }
    private String getSQUpdate(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CLIENTE SET NOME = ?, CODIGO = ? ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }
    private void paramentrosUp(PreparedStatement stm,Cliente cliente,String id) throws SQLException {
        stm.setString(1,cliente.getNome());
        stm.setString(2,cliente.getCodigo());
        stm.setString(3,id);
    }

    private String getSQexcluir(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_CLIENTE");
        sb.append(" WHERE CODIGO = ?");
        return sb.toString();
    }
    private void paramentrosEx(PreparedStatement stm,String id) throws SQLException {
        stm.setString(1,id);

    }
}
