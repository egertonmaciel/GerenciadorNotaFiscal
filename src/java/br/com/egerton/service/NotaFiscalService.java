package br.com.egerton.service;

import br.com.egerton.controller.NotaFiscalController;
import br.com.egerton.entity.Empresa;
import br.com.egerton.entity.NotaFiscal;
import br.com.egerton.dto.RetornoAPI;
import com.google.gson.Gson;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("notaFiscal/")
public class NotaFiscalService {

    Gson gson;
    NotaFiscalController notaFiscalController;

    public NotaFiscalService() {
        gson = new Gson();
        notaFiscalController = new NotaFiscalController();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cadastrar")
    public String cadastrar(String json) {
        NotaFiscal nota = gson.fromJson(json, NotaFiscal.class);
        RetornoAPI retornoAPI = notaFiscalController.cadastrar(nota);
        return gson.toJson(retornoAPI);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarByEmpresa")
    public String listarByEmpresa(String json) {
        Empresa empresa = gson.fromJson(json, Empresa.class);
        RetornoAPI retornoAPI = notaFiscalController.listarByEmpresa(empresa);
        return gson.toJson(retornoAPI);
    }

}
