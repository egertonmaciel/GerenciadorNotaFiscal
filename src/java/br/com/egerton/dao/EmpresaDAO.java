package br.com.egerton.dao;

import br.com.egerton.connection.Conexao;
import br.com.egerton.connection.ConexaoLocal;
import br.com.egerton.entity.Empresa;
import br.com.egerton.dto.RetornoSQL;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    Conexao conexao = new ConexaoLocal();

    public RetornoSQL cadastrar(Empresa empresa) {
        String sql = "insert into empresa values (null,?,?,?,?,1)";
        ArrayList<Object> parametros = new ArrayList<>();
        parametros.add(empresa.getNomeFantasia());
        parametros.add(empresa.getRazaoSocial());
        parametros.add(empresa.getCnpj());
        parametros.add(empresa.getTipo().toString());

        RetornoSQL retorno = conexao.update(sql, parametros);

        return retorno;
    }

    public RetornoSQL editar(Empresa empresa) {
        String sql = "update empresa\n"
                + "set\n"
                + "nome_fantasia = ?,\n"
                + "razao_social = ?,\n"
                + "cnpj = ?\n"
                + "where id = ?";

        List<Object> parametros = new ArrayList<>();
        parametros.add(empresa.getNomeFantasia());
        parametros.add(empresa.getRazaoSocial());
        parametros.add(empresa.getCnpj());
        parametros.add(empresa.getId());

        RetornoSQL retorno = conexao.update(sql, parametros);

        return retorno;
    }

    public RetornoSQL excluir(Empresa empresa) {
        String sql = "update empresa set ativo = 0 where id = ?";

        RetornoSQL retorno = conexao.update(sql, empresa.getId());

        return retorno;
    }

    public RetornoSQL listarTodos(boolean somenteAtivas) {
        String sql = "select * from empresa";
        sql += somenteAtivas ? " where ativo = 1" : "";
        RetornoSQL retorno = conexao.select(sql);
        return retorno;
    }

    public RetornoSQL getById(int id) {
        String sql = "select * from empresa where id = ?";
        RetornoSQL retorno = conexao.select(sql, id);
        return retorno;
    }
}
