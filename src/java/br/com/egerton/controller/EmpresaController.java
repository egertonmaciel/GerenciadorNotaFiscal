package br.com.egerton.controller;

import br.com.egerton.dao.EmpresaDAO;
import br.com.egerton.entity.Empresa;
import br.com.egerton.dto.RetornoAPI;
import br.com.egerton.dto.RetornoSQL;
import br.com.egerton.dto.ValidaCampos;
import br.com.egerton.entity.TipoEmpresa;
import java.util.ArrayList;

public class EmpresaController {

    EmpresaDAO empresaDAO;

    public EmpresaController() {
        empresaDAO = new EmpresaDAO();
    }

    public RetornoAPI cadastrar(Empresa empresa) {
        RetornoAPI retornoAPI = new RetornoAPI();

        ValidaCampos validaCampos = cadastrarValidaCampos(empresa);
        if (!validaCampos.isValido()) {
            retornoAPI.setSucesso(false);
            retornoAPI.setRetorno(validaCampos.getValidacoes());
            retornoAPI.setMensagem("Campos inválidos");
            return retornoAPI;
        }

        RetornoSQL retornoSQL = empresaDAO.cadastrar(empresa);
        retornoAPI.setSucesso(retornoSQL.isSucesso());
        retornoAPI.setMensagem(retornoSQL.isSucesso() ? "Cadastrado com Sucessos." : retornoSQL.getMensagem());
        if (retornoSQL.isSucesso()) {
            int novoId = Integer.parseInt(retornoSQL.getValores().get(0).get(0));
            retornoAPI.setRetorno(getById(novoId));
        }
        return retornoAPI;
    }

    public RetornoAPI editar(Empresa empresa) {
        RetornoAPI retornoAPI = new RetornoAPI();

        ValidaCampos validaCampos = editarValidaCampos(empresa);
        if (!validaCampos.isValido()) {
            retornoAPI.setSucesso(false);
            retornoAPI.setRetorno(validaCampos.getValidacoes());
            retornoAPI.setMensagem("Campos inválidos");
            return retornoAPI;
        }

        RetornoSQL retornoSQL = empresaDAO.editar(empresa);
        retornoAPI.setSucesso(retornoSQL.isSucesso());
        retornoAPI.setMensagem(retornoSQL.isSucesso() ? "Editado com Sucessos." : retornoSQL.getMensagem());
        if (retornoSQL.isSucesso()) {
            retornoAPI.setRetorno(getById(empresa.getId()));
        }

        return retornoAPI;
    }

    public RetornoAPI excluir(Empresa empresa) {
        RetornoAPI retornoAPI = new RetornoAPI();

        ValidaCampos validaCampos = excluirValidaCampos(empresa);
        if (!validaCampos.isValido()) {
            retornoAPI.setSucesso(false);
            retornoAPI.setRetorno(validaCampos.getValidacoes());
            retornoAPI.setMensagem("Campos inválidos");
            return retornoAPI;
        }

        RetornoSQL retornoSQL = empresaDAO.excluir(empresa);
        retornoAPI.setSucesso(retornoSQL.isSucesso());
        retornoAPI.setMensagem(retornoSQL.isSucesso() ? "Excluído com Sucessos." : retornoSQL.getMensagem());

        return retornoAPI;
    }

    public Empresa getById(int id) {
        RetornoSQL retornoSQL = empresaDAO.getById(id);
        Empresa empresa = new Empresa();

        if (retornoSQL.isSucesso()) {
            for (ArrayList<String> linha : retornoSQL.getValores()) {
                empresa = getEntity(linha);
            }
        }
        return empresa;
    }

    public ArrayList<Empresa> listarTodas(Boolean somenteAtivas) {
        RetornoSQL retornoSQL = empresaDAO.listarTodos(somenteAtivas);
        ArrayList<Empresa> empresas = new ArrayList<>();

        if (retornoSQL.isSucesso()) {
            for (ArrayList<String> linha : retornoSQL.getValores()) {
                Empresa empresa = getEntity(linha);
                empresas.add(empresa);
            }
        }
        return empresas;
    }

    private Empresa getEntity(ArrayList<String> linha) {
        Empresa empresa = new Empresa();
        empresa.setId(Integer.parseInt(linha.get(0)));
        empresa.setNomeFantasia(linha.get(1));
        empresa.setRazaoSocial(linha.get(2));
        empresa.setCnpj(linha.get(3));
        empresa.setTipo(Enum.valueOf(TipoEmpresa.class, linha.get(4)));
        return empresa;
    }

    private ValidaCampos cadastrarValidaCampos(Empresa empresa) {
        ValidaCampos retorno = new ValidaCampos();

        retorno.addValidacoes(empresa.getRazaoSocial() != null && !empresa.getRazaoSocial().equals("") ? "" : "Razão Social");
        retorno.addValidacoes(empresa.getCnpj() != null && !empresa.getCnpj().equals("") ? "" : "CNPJ");
        retorno.addValidacoes(validaCnpjExiste(empresa.getCnpj()) ? "CNPJ já existe" : "");
        retorno.addValidacoes(validaTipoEmpresa(empresa.getTipo()) ? "" : "Tipo de empresa não existe");

        retorno.setValido(retorno.getValidacoes().isEmpty());
        return retorno;
    }

    private boolean validaCnpjExiste(String cnpj) {
        for (Empresa empresa : listarTodas(true)) {
            if (empresa.getCnpj().equals(cnpj)) {
                return true;
            }
        }
        return false;
    }

    private boolean validaTipoEmpresa(TipoEmpresa tipo) {
        for (TipoEmpresa t : TipoEmpresa.values()) {
            if (t.equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    private ValidaCampos editarValidaCampos(Empresa empresa) {
        ValidaCampos retorno = new ValidaCampos();
        ArrayList<Empresa> empresas = listarTodas(true);

        retorno.addValidacoes(empresas.contains(empresa) ? "" : "Id não existe");
        retorno.addValidacoes(empresa.getRazaoSocial() != null && !empresa.getRazaoSocial().equals("") ? "" : "Razão Social");
        retorno.addValidacoes(empresa.getCnpj() != null && !empresa.getCnpj().equals("") ? "" : "CNPJ");

        retorno.setValido(retorno.getValidacoes().isEmpty());
        return retorno;
    }

    private ValidaCampos excluirValidaCampos(Empresa empresa) {
        ValidaCampos retorno = new ValidaCampos();
        ArrayList<Empresa> empresas = listarTodas(true);

        retorno.addValidacoes(empresas.contains(empresa) ? "" : "Id não existe");

        retorno.setValido(retorno.getValidacoes().isEmpty());
        return retorno;
    }
}
