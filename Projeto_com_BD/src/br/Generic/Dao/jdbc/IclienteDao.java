package br.Generic.Dao.jdbc;

import br.classe.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IclienteDao {
    public Integer cadastra(Cliente cliente) throws SQLException;
    public Integer atualizar(String id,Cliente cliente) throws SQLException;
    public Integer excluir(String id) throws SQLException;
    public Cliente buscar(String id) throws SQLException;
    public List<Cliente> buscartodos() throws SQLException;
}
