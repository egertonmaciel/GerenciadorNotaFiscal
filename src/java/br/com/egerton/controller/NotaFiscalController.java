package br.com.egerton.controller;

import br.com.egerton.dao.NotaFiscalDAO;
import br.com.egerton.entity.Empresa;
import br.com.egerton.entity.NotaFiscal;
import br.com.egerton.dto.RetornoAPI;
import br.com.egerton.dto.RetornoSQL;
import br.com.egerton.dto.ValidaCampos;
import br.com.egerton.entity.TipoEmpresa;
import br.com.egerton.util.DataUtil;
import java.util.ArrayList;

public class NotaFiscalController {

    NotaFiscalDAO notaFiscalDAO;
    EmpresaController empresaController;

    public NotaFiscalController() {
        notaFiscalDAO = new NotaFiscalDAO();
        empresaController = new EmpresaController();
    }

    public RetornoAPI cadastrar(NotaFiscal notaFiscal) {
        RetornoAPI retorno = new RetornoAPI();

        ValidaCampos validaCampos = cadastrarValidaCampos(notaFiscal);
        if (!validaCampos.isValido()) {
            retorno.setSucesso(false);
            retorno.setRetorno(validaCampos.getValidacoes());
            retorno.setMensagem("Campos inválidos");
            return retorno;
        }

        RetornoSQL retornoSQL = notaFiscalDAO.cadastrar(notaFiscal);
        retorno.setSucesso(retornoSQL.isSucesso());
        retorno.setMensagem(retornoSQL.isSucesso() ? "Cadastrado com Sucessos." : retornoSQL.getMensagem());
        if (retornoSQL.isSucesso()) {
            int novoId = Integer.parseInt(retornoSQL.getValores().get(0).get(0));
            retorno.setRetorno(getById(novoId));
        }

        return retorno;
    }

    public RetornoAPI listarByEmpresa(Empresa empresa) {
        RetornoAPI retorno = new RetornoAPI();

        ValidaCampos validaCampos = listarByEmpresaValidaCampos(empresa);
        if (!validaCampos.isValido()) {
            retorno.setSucesso(false);
            retorno.setRetorno(validaCampos.getValidacoes());
            retorno.setMensagem("Campos inválidos");
            return retorno;
        }

        RetornoSQL retornoSQL = notaFiscalDAO.listarByEmpresa(empresa);
        retorno.setSucesso(retornoSQL.isSucesso());
        retorno.setMensagem(retornoSQL.getMensagem());
        if (retornoSQL.isSucesso()) {
            ArrayList<NotaFiscal> notas = new ArrayList<>();
            for (ArrayList<String> linha : retornoSQL.getValores()) {
                NotaFiscal nota = getEntity(linha);
                notas.add(nota);
            }
            retorno.setRetorno(notas);
        }
        return retorno;
    }

    private NotaFiscal getById(int id) {
        RetornoSQL retornoSQL = notaFiscalDAO.getById(id);
        NotaFiscal nota = new NotaFiscal();

        if (retornoSQL.isSucesso()) {
            for (ArrayList<String> linha : retornoSQL.getValores()) {
                nota = getEntity(linha);
            }
        }
        return nota;
    }

    private NotaFiscal getEntity(ArrayList<String> linha) {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(Integer.parseInt(linha.get(0)));
        nota.setNumero(Integer.parseInt(linha.get(1)));
        nota.setData(linha.get(2));
        nota.setValor(Double.parseDouble(linha.get(3)));
        nota.setTomadoda(empresaController.getById(Integer.parseInt(linha.get(4))));
        nota.setPrestadora(empresaController.getById(Integer.parseInt(linha.get(5))));
        return nota;
    }

    private ValidaCampos cadastrarValidaCampos(NotaFiscal nota) {
        ValidaCampos retorno = new ValidaCampos();

        ArrayList<Empresa> empresasTomadoras = new ArrayList<>();
        ArrayList<Empresa> empresasPrestadoras = new ArrayList<>();
        for (Empresa empresa : empresaController.listarTodas(true)) {
            if (empresa.getTipo().equals(TipoEmpresa.TOMADORA)) {
                empresasTomadoras.add(empresa);
            } else if (empresa.getTipo().equals(TipoEmpresa.PRESTADORA)) {
                empresasPrestadoras.add(empresa);
            }
        }

        retorno.addValidacoes(nota.getNumero() > 0 ? "" : "Numero deve ser maior que 0");
        retorno.addValidacoes(nota.getData() != null && !nota.getData().equals("") ? "" : "Data é obrigatório");
        retorno.addValidacoes(DataUtil.validaDatetime(nota.getData()) ? "" : "Data é inválida");
        retorno.addValidacoes(nota.getValor() != null && nota.getValor() > 0 ? "" : "Valor deve ser maior que 0");
        retorno.addValidacoes(empresasTomadoras.contains(nota.getTomadoda()) ? "" : "Id Tomadora não existe");
        retorno.addValidacoes(empresasPrestadoras.contains(nota.getPrestadora()) ? "" : "Id Prestadora não existe");

        retorno.setValido(retorno.getValidacoes().isEmpty());
        return retorno;
    }

    private ValidaCampos listarByEmpresaValidaCampos(Empresa empresa) {
        ValidaCampos retorno = new ValidaCampos();
        ArrayList<Empresa> empresas = empresaController.listarTodas(false);

        retorno.addValidacoes(empresas.contains(empresa) ? "" : "Id Empresa não existe");

        retorno.setValido(retorno.getValidacoes().isEmpty());
        return retorno;
    }

}
