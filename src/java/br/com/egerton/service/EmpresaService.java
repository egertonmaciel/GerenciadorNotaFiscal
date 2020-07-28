package br.com.egerton.service;

import br.com.egerton.controller.EmpresaController;
import br.com.egerton.entity.Empresa;
import br.com.egerton.dto.RetornoAPI;
import com.google.gson.Gson;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("empresa/")
public class EmpresaService {

    Gson gson;
    EmpresaController empresaController;

    public EmpresaService() {
        gson = new Gson();
        empresaController = new EmpresaController();  
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cadastrar")
    public String cadastrar(String json) {
        Empresa empresa = gson.fromJson(json, Empresa.class);
        RetornoAPI retornoAPI = empresaController.cadastrar(empresa);
        return gson.toJson(retornoAPI);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("editar")
    public String editar(String json) {
        Empresa empresa = gson.fromJson(json, Empresa.class);
        RetornoAPI retornoAPI = empresaController.editar(empresa);
        return gson.toJson(retornoAPI);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("excluir")
    public String excluir(String json) {
        Empresa empresa = gson.fromJson(json, Empresa.class);
        RetornoAPI retornoAPI = empresaController.excluir(empresa);
        return gson.toJson(retornoAPI);
    }

}
