package br.com.egerton.dao;

import br.com.egerton.connection.Conexao;
import br.com.egerton.connection.ConexaoLocal;
import br.com.egerton.entity.Empresa;
import br.com.egerton.entity.NotaFiscal;
import br.com.egerton.dto.RetornoSQL;
import java.util.ArrayList;

public class NotaFiscalDAO {

    Conexao conexao = new ConexaoLocal();

    public RetornoSQL cadastrar(NotaFiscal notaFiscal) {
        String sql = "insert into nota_fiscal values (null,?,?,?,?,?)";
        ArrayList<Object> parametros = new ArrayList<>();
        parametros.add(notaFiscal.getNumero());
        parametros.add(notaFiscal.getData());
        parametros.add(notaFiscal.getValor());
        parametros.add(notaFiscal.getTomadoda().getId());
        parametros.add(notaFiscal.getPrestadora().getId());

        RetornoSQL retorno = conexao.update(sql, parametros);

        return retorno;
    }

    public RetornoSQL listarByEmpresa(Empresa empresa) {
        String sql = "select * from nota_fiscal where tomadora = ? or prestadora = ?";
        ArrayList<Object> parametros = new ArrayList<>();
        parametros.add(empresa.getId());
        parametros.add(empresa.getId());
        RetornoSQL retorno = conexao.select(sql, parametros);
        return retorno;
    }

    public RetornoSQL getById(int id) {
        String sql = "select * from nota_fiscal where id = ?";
        RetornoSQL retorno = conexao.select(sql, id);
        return retorno;
    }

}
