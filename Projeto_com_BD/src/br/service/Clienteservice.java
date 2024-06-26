package br.service;

import br.Generic.Dao.jdbc.IclienteDao;
import br.classe.Cliente;

import java.sql.SQLException;
import java.util.List;

public class Clienteservice implements Iservice{
    private IclienteDao clienteDao;
    public Clienteservice(IclienteDao cliente){
        this.clienteDao = cliente;
    }
    @Override
    public Integer cadastra(Cliente cliente) throws SQLException {
        return clienteDao.cadastra(cliente);
    }

    @Override
    public Integer atualizar(String id,Cliente cliente) throws SQLException {
        return clienteDao.atualizar(id, cliente);
    }

    @Override
    public Integer excluir(String id) throws SQLException {
        return clienteDao.excluir(id);
    }

    @Override
    public Cliente buscar(String id) throws SQLException {
        return clienteDao.buscar(id);
    }

    @Override
    public List<Cliente> buscartodos() throws SQLException {
        return clienteDao.buscartodos();
    }
}
