# 🚗 Sistema de Controle de Abastecimento

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![H2](https://img.shields.io/badge/H2-Database-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Índice 

* [Sobre o Projeto](#-sobre-o-projeto)
* [Funcionalidades](#-funcionalidades)
* [Tecnologias](#-tecnologias)
* [Arquitetura](#-arquitetura)
* [Instalação](#-instalação)
* [Como Usar](#-como-usar)
* [API Endpoints](#-api-endpoints)
* [Testes](#-testes)
* [Documentação](#-documentação)

## 🎯 Sobre o Projeto

Sistema de gerenciamento de abastecimentos para frotas de veículos, desenvolvido com Spring Boot e Clean Architecture. Permite o controle e monitoramento dos abastecimentos realizados, com validações específicas para garantir a integridade dos dados.

## ✨ Funcionalidades

- ✅ Cadastro de abastecimentos
- 🔍 Filtro de pesquisa por placa
- 📊 Paginação dos resultados (5, 10, 15 registros)
- 🚫 Validações de negócio:
  - Data futura não permitida
  - Quilometragem maior que o último abastecimento
  - Placa em formato válido (AAA-1234 ou ABC1D23)
  - Valor total maior que zero

## 🛠 Tecnologias

- ![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-6DB33F?style=flat&logo=spring&logoColor=white)
- ![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=flat)
- ![Maven](https://img.shields.io/badge/Maven-3.8.1-C71A36?style=flat&logo=apache-maven&logoColor=white)
- ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?style=flat&logo=swagger&logoColor=black)

## 🏗 Arquitetura

O projeto segue os princípios da Clean Architecture:

```
src/
├── main/
│   ├── java/
│   │   └── com/transport/system/
│   │       ├── controller/    # Controllers da API
│   │       ├── service/       # Regras de negócio
│   │       ├── repository/    # Acesso a dados
│   │       ├── model/        # Entidades
│   │       └── dto/          # Objetos de transferência
│   └── resources/
│       └── application.yml   # Configurações
```

## 🚀 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/api-transport-system.git
```

2. Navegue até o diretório do projeto:
```bash
cd api-transport-system
```

3. Compile o projeto:
```bash
mvn clean install
```

## 💻 Como Usar

1. Inicie a aplicação:
```bash
mvn spring-boot:run
```

2. Acesse:
- API: http://localhost:8080/api
- Swagger: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

## 🔌 API Endpoints

### Abastecimentos

```http
POST /api/abastecimentos
```
```json
{
    "dataHora": "2024-01-08T10:00:00",
    "placa": "ABC-1234",
    "quilometragem": 1000,
    "valorTotal": 150.00
}
```

```http
GET /api/abastecimentos?placa=ABC&page=0&size=10
```

```http
DELETE /api/abastecimentos/{id}
```

## 🧪 Testes

Execute os testes unitários:
```bash
mvn test
```

Principais classes de teste:
- `AbastecimentoServiceTest`: Testes da camada de serviço
- `AbastecimentoControllerTest`: Testes da camada de controller

## 📚 Documentação

A documentação completa da API está disponível via Swagger UI:
- Local: http://localhost:8080/swagger-ui.html
- Especificação: http://localhost:8080/v3/api-docs

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Autores

* **Marco Oliveira** - *Desenvolvedor* - [GitHub](https://github.com/marco-oliveira-s10)

## 🤝 Contribuindo

1. Fork o projeto
2. Crie sua branch de feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

⌨️ com ❤️ por [Marco Oliveira](https://github.com/marco-oliveira-s10) 😊
