package br.com.egerton.service;

import br.com.egerton.dto.RetornoAPI;
import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egerton Maciel
 */
public class EmpresaServiceTest {

    Gson gson;

    public EmpresaServiceTest() {
        gson = new Gson();
    }

    public String gerarCnpj() {
        String cnpj = "";
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        cnpj += ".";
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        cnpj += ".";
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        cnpj += "/0001-";
        cnpj += (int) (Math.random() * 10);
        cnpj += (int) (Math.random() * 10);
        return cnpj;
    }

    @Test
    public void testCadastrarJsonOk() {

        String json = "{\n"
                + "    \"nomeFantasia\": \"Empresa Um\",\n"
                + "    \"razaoSocial\": \"Empresa 1 LTDA\",\n"
                + "	\"cnpj\": \"" + gerarCnpj() + "\",\n"
                + "	\"tipo\": \"TOMADORA\"\n"
                + "}";
        EmpresaService instance = new EmpresaService();
        String result = instance.cadastrar(json);
        RetornoAPI retornoAPI = gson.fromJson(result, RetornoAPI.class);
        assertEquals(true, retornoAPI.isSucesso());
    }

    @Test
    public void testCadastrarCamposObrigatorios() {
        String json = "{}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Razão Social\",\"CNPJ\",\"Tipo de empresa não existe\"]}";
        EmpresaService instance = new EmpresaService();
        String resultado = instance.cadastrar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testEditarJsonOk() {
        String cnpj = gerarCnpj();
        String json = "{\n"
                + "    \"id\": 1,\n"
                + "    \"nomeFantasia\": \"Empresa Um\",\n"
                + "    \"razaoSocial\": \"Empresa 1 LTDA\",\n"
                + "	\"cnpj\": \"" + cnpj + "\",\n"
                + "	\"tipo\": \"TOMADORA\"\n"
                + "}";
        String jsonEsperado = "{\"sucesso\":true,\"mensagem\":\"Editado com Sucessos.\",\"retorno\":{\"id\":1,\"nomeFantasia\":\"Empresa Um\",\"razaoSocial\":\"Empresa 1 LTDA\",\"cnpj\":\"" + cnpj + "\",\"tipo\":\"TOMADORA\"}}";
        EmpresaService instance = new EmpresaService();
        String resultado = instance.editar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testEditarSemAlterarTipo() {
        String cnpj = gerarCnpj();
        String json = "{\n"
                + "    \"id\": 1,\n"
                + "    \"nomeFantasia\": \"Empresa Um\",\n"
                + "    \"razaoSocial\": \"Empresa 1 LTDA\",\n"
                + "	\"cnpj\": \"" + cnpj + "\",\n"
                + "	\"tipo\": \"PRESTADORA\"\n"
                + "}";
        String jsonEsperado = "{\"sucesso\":true,\"mensagem\":\"Editado com Sucessos.\",\"retorno\":{\"id\":1,\"nomeFantasia\":\"Empresa Um\",\"razaoSocial\":\"Empresa 1 LTDA\",\"cnpj\":\"" + cnpj + "\",\"tipo\":\"TOMADORA\"}}";
        EmpresaService instance = new EmpresaService();
        String resultado = instance.editar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testEditarCamposObrigatorios() {
        String json = "{}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Id não existe\",\"Razão Social\",\"CNPJ\"]}";
        EmpresaService instance = new EmpresaService();
        String resultado = instance.editar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testExcluirCamposObrigatorios() {
        String json = "{}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Id não existe\"]}";
        EmpresaService instance = new EmpresaService();
        String resultado = instance.excluir(json);
        assertEquals(jsonEsperado, resultado);
    }

}
