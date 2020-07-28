# Gerenciador Nota Fiscal

Código desenvolvido na IDE NetBeans 8.1
Versão do compilador Java 8
Servidor Web GlassFish 4.1.1 (Java EE 7)
Banco de Dados MySQL 5.7

Script do banco de dados no arquivo: script.sql
Configuração de conexão com o banco de dados: br.com.egerton.connection.ConexaoLocal;

URLs da API:

## Empresa
http://localhost:8080/GerenciadorNotaFiscal/api/empresa/cadastrar
	Exemplo de Input:
	{
		"nomeFantasia": "Empresa Um",
		"razaoSocial": "Empresa 1 LTDA",
		"cnpj": "91.762.660/0001-30",
		"tipo": "PRESTADORA"
	}
	
	Exemplo de Output:
	{
		"sucesso": true,
		"mensagem": "Cadastrado com Sucessos.",
		"retorno": {
			"id": 12,
			"nomeFantasia": "Empresa Um",
			"razaoSocial": "Empresa 1 LTDA",
			"cnpj": "91.762.660/0001-30",
			"tipo": "PRESTADORA"
		}
	}

http://localhost:8080/GerenciadorNotaFiscal/api/empresa/editar
	Exemplo de Input:
	{
		"id": 12,
		"nomeFantasia": "Empresa Um",
		"razaoSocial": "Empresa 1 LTDA",
		"cnpj": "91.762.660/0001-30"
	}
	
	Exemplo de Output:
	{
		"sucesso": true,
		"mensagem": "Editado com Sucessos.",
		"retorno": {
			"id": 12,
			"nomeFantasia": "Empresa Um",
			"razaoSocial": "Empresa 1 LTDA",
			"cnpj": "91.762.660/0001-30",
			"tipo": "PRESTADORA"
		}
	}
	

http://localhost:8080/GerenciadorNotaFiscal/api/empresa/excluir
	Exemplo de Input:
	{
		"id": 1,
		"nomeFantasia": "Empresa Um",
		"razaoSocial": "Empresa 1 LTDA",
		"cnpj": "91.762.660/0001-30"
	}
	
	Exemplo de Output:
	{
		"sucesso": true,
		"mensagem": "Excluído com Sucessos."
	}

## Nota Fiscal

http://localhost:8080/GerenciadorNotaFiscal/api/notaFiscal/cadastrar
	Exemplo de Input:
	{
		"numero": 10,
		"data": "2020-01-01 12:00:00",
		"valor": 300.50,
		"tomadoda": {
			"id": 1
		},
		"prestadora": {
			"id": 2
		}
	}
	
	Exemplo de Output:
	{
		"sucesso": true,
		"mensagem": "Cadastrado com Sucessos.",
		"retorno": {
			"id": 11,
			"numero": 10,
			"data": "2020-01-01 12:00:00.0",
			"valor": 300.5,
			"tomadoda": {
				"id": 1,
				"nomeFantasia": "Empresa Um",
				"razaoSocial": "Empresa 1 LTDA",
				"cnpj": "65.592.548/0001-03",
				"tipo": "TOMADORA"
			},
			"prestadora": {
				"id": 2,
				"nomeFantasia": "Empresa Dois",
				"razaoSocial": "Empresa 2 LTDA",
				"cnpj": "0942229480-0001-34",
				"tipo": "PRESTADORA"
			}
		}
	}
	

http://localhost:8080/GerenciadorNotaFiscal/api/notaFiscal/listarByEmpresa
	Exemplo de Input:
	{
		"id": 1,
		"nomeFantasia": "Empresa Um",
		"razaoSocial": "Empresa 1 LTDA",
		"cnpj": "91.762.660/0001-30",
		"tipo": "PRESTADORA"
	}
	
	Exemplo de Output:
	{
		"sucesso": true,
		"mensagem": "Sucesso.",
		"retorno": [
			{
				"id": 1,
				"numero": 10,
				"data": "2020-01-22 11:11:00.0",
				"valor": 300.5,
				"tomadoda": {
					"id": 1,
					"nomeFantasia": "Empresa Um",
					"razaoSocial": "Empresa 1 LTDA",
					"cnpj": "65.592.548/0001-03",
					"tipo": "TOMADORA"
				},
				"prestadora": {
					"id": 2,
					"nomeFantasia": "Empresa Dois",
					"razaoSocial": "Empresa 2 LTDA",
					"cnpj": "0942229480-0001-34",
					"tipo": "PRESTADORA"
				}
			},
			{
				"id": 2,
				"numero": 10,
				"data": "2020-01-22 11:11:00.0",
				"valor": 300.5,
				"tomadoda": {
					"id": 1,
					"nomeFantasia": "Empresa Um",
					"razaoSocial": "Empresa 1 LTDA",
					"cnpj": "65.592.548/0001-03",
					"tipo": "TOMADORA"
				},
				"prestadora": {
					"id": 3,
					"nomeFantasia": "Empresa Tres",
					"razaoSocial": "Empresa 3 LTDA",
					"cnpj": "0942229481-0001-34",
					"tipo": "PRESTADORA"
				}
			},
			{
				"id": 3,
				"numero": 10,
				"data": "1930-01-30 14:00:00.0",
				"valor": 300.5,
				"tomadoda": {
					"id": 1,
					"nomeFantasia": "Empresa Um",
					"razaoSocial": "Empresa 1 LTDA",
					"cnpj": "65.592.548/0001-03",
					"tipo": "TOMADORA"
				},
				"prestadora": {
					"id": 2,
					"nomeFantasia": "Empresa Dois",
					"razaoSocial": "Empresa 2 LTDA",
					"cnpj": "0942229480-0001-34",
					"tipo": "PRESTADORA"
				}
			}
		]
	}






