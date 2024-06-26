package br;

import br.Generic.Dao.jdbc.ClienteDao;
import br.Generic.Dao.jdbc.IclienteDao;
import br.classe.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.service.Clienteservice;
import br.service.Iservice;

import java.sql.SQLException;
import java.util.List;

public class Testeclienteservice {

    private static Iservice iservice;
    public Cliente cliente = new Cliente();
    private static IclienteDao iclienteDao = new ClienteDao();

    public Testeclienteservice(){
        iservice = new Clienteservice(iclienteDao);
    }
    @Before
    public void init(){
        cliente.setNome("Camylee");
        cliente.setCodigo("12345678910");
    }

    @Test
    public void testecadastrar() throws SQLException {

        int Contcad = iservice.cadastra(cliente);
        Assert.assertTrue(Contcad == 1);

        Cliente clienteDb = iservice.buscar(cliente.getCodigo());
        Assert.assertNotNull(clienteDb);
        Assert.assertEquals(cliente.getNome(),clienteDb.getNome());
        Assert.assertEquals(cliente.getCodigo(),clienteDb.getCodigo());

        int Contex = iservice.excluir(cliente.getCodigo());
        Assert.assertTrue(Contex == 1);

    }
    @Test
    public void testeUpdate() throws SQLException {
        int num = iservice.cadastra(cliente);


        Cliente cliente2 = iservice.buscar(cliente.getCodigo());
        Assert.assertEquals(cliente.getCodigo(),cliente2.getCodigo());
        Assert.assertEquals(cliente.getNome(),cliente2.getNome());

        Cliente clienteup = new Cliente();
        clienteup.setCodigo("9876544");
        clienteup.setNome("Raul");

        int contup = iservice.atualizar(cliente.getCodigo(), clienteup);
        clienteup = iservice.buscar(clienteup.getCodigo());
        Assert.assertEquals(cliente2.getId(),clienteup.getId());
        Assert.assertNotEquals(cliente2.getCodigo(),clienteup.getCodigo());
        Assert.assertNotEquals(cliente2.getNome(),clienteup.getNome());


        int numex = iservice.excluir(clienteup.getCodigo());
        Assert.assertEquals(1,numex);
    }
    @Test
    public void testeExcluir() throws SQLException {
        int Contcad= iservice.cadastra(cliente);
        Assert.assertTrue(Contcad == 1);

        Cliente clientedb = iservice.buscar(cliente.getCodigo());
        Assert.assertNotNull(clientedb);
        Assert.assertEquals(clientedb.getNome(),cliente.getNome());
        Assert.assertEquals(clientedb.getNome(),cliente.getNome());

        int numex = iservice.excluir(cliente.getCodigo());
        Assert.assertTrue(1 == numex);

        clientedb = iservice.buscar(cliente.getCodigo());
        Assert.assertTrue(clientedb.getNome() == null);
        Assert.assertTrue(clientedb.getCodigo() == null);

    }
    @Test

    public void testebusca() throws SQLException {
        int Contcad= iservice.cadastra(cliente);
        Assert.assertTrue(Contcad == 1);

        Cliente clientedb = iservice.buscar(cliente.getCodigo());
        Assert.assertNotNull(clientedb);
        Assert.assertEquals(clientedb.getNome(),cliente.getNome());
        Assert.assertEquals(clientedb.getNome(),cliente.getNome());

        int numex = iservice.excluir(cliente.getCodigo());
        Assert.assertTrue(1 == numex);
    }
    @Test
    public void testebuscatodos() throws SQLException {
        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("9876544");
        cliente2.setNome("Raul");

        int contcad = 0;
        List<Cliente> clienteList = List.of(cliente,cliente2);
        for(Cliente cli :clienteList){
          contcad +=  iservice.cadastra(cli);
        }
        Assert.assertTrue(contcad==clienteList.size());

        List<Cliente> clientes = iservice.buscartodos();
        Assert.assertNotNull(clientes);
        Assert.assertEquals(clientes.size(),clienteList.size());
        int i ;
        for(i=0; i < clienteList.size();i++){
            Assert.assertEquals(clienteList.get(i).getCodigo(),clientes.get(i).getCodigo());
            Assert.assertEquals(clienteList.get(i).getNome(),clientes.get(i).getNome());
        }
        int contex = 0;
        for(Cliente cli :clienteList){
           contex+= iservice.excluir(cli.getCodigo());
        }
        Assert.assertTrue(contex==clienteList.size());

    }



}
