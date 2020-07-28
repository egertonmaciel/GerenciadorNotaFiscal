package br.com.egerton.service;

import br.com.egerton.dto.RetornoAPI;
import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egerton Maciel
 */
public class NotaFiscalServiceTest {

    Gson gson;

    public NotaFiscalServiceTest() {
        gson = new Gson();
    }

    @Test
    public void testCadastrarJsonOk() {
        String json = "{\n"
                + "	\"numero\": 10,\n"
                + "    \"data\": \"1930-01-30 14:00:00\",\n"
                + "    \"valor\": 300.5,\n"
                + "    \"tomadoda\": {\n"
                + "    		\"id\": 1\n"
                + "	},\n"
                + "	\"prestadora\": {\n"
                + "		\"id\": 2\n"
                + "	}\n"
                + "}";
        NotaFiscalService instance = new NotaFiscalService();
        String resultado = instance.cadastrar(json);
        RetornoAPI retornoAPI = gson.fromJson(resultado, RetornoAPI.class);
        assertEquals(true, retornoAPI.isSucesso());
    }
    
    @Test
    public void testCadastrarCamposObrigatorios() {
        String json = "{}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Numero deve ser maior que 0\",\"Data é obrigatório\",\"Data é inválida\",\"Valor deve ser maior que 0\",\"Id Tomadora não existe\",\"Id Prestadora não existe\"]}";
        NotaFiscalService instance = new NotaFiscalService();
        String resultado = instance.cadastrar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testCadastrarValidaTomadoraPrestadora() {
        String json = "{\n"
                + "	\"numero\": 10,\n"
                + "    \"data\": \"1930-01-30 14:00:00\",\n"
                + "    \"valor\": 300.5,\n"
                + "    \"tomadoda\": {\n"
                + "    		\"id\": 2\n"
                + "	},\n"
                + "	\"prestadora\": {\n"
                + "		\"id\": 1\n"
                + "	}\n"
                + "}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Id Tomadora não existe\",\"Id Prestadora não existe\"]}";
        NotaFiscalService instance = new NotaFiscalService();
        String resultado = instance.cadastrar(json);
        assertEquals(jsonEsperado, resultado);
    }

    @Test
    public void testlistarByEmpresaJsonOk() {
        String json = "{\n"
                + "	\"id\": 1\n"
                + "}";
        NotaFiscalService instance = new NotaFiscalService();
        String resultado = instance.listarByEmpresa(json);
        RetornoAPI retornoAPI = gson.fromJson(resultado, RetornoAPI.class);
        assertEquals(true, retornoAPI.isSucesso());
    }

    @Test
    public void testlistarByEmpresaCamposObrigadorios() {
        String json = "{}";
        String jsonEsperado = "{\"sucesso\":false,\"mensagem\":\"Campos inválidos\",\"retorno\":[\"Id Empresa não existe\"]}";
        NotaFiscalService instance = new NotaFiscalService();
        String resultado = instance.listarByEmpresa(json);
        assertEquals(jsonEsperado, resultado);
    }
}
