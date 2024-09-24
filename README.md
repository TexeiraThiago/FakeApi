# FakeApi

FakeApi é uma API REST simples criada para simular endpoints e fornecer dados fictícios para fins de testes e desenvolvimento.
Ideal para desenvolvedores que precisam de dados mockados em seus projetos.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Swagger**
- **Docker**

## Funcionalidades Principais
- CRUD completo para entidades simuladas.
- Endpoints personalizados para retornar dados mockados.
- Documentação automática de API com Swagger.

## Como Executar o Projeto

### Requisitos
- **Java 17** ou superior.
- **Maven** instalado.
- **Docker** instalado

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/TexeiraThiago/FakeApi.git
    ```
2. Navegue até o diretório do projeto:
   ```bash
   cd FakeApi
   ```
3. Abra com o sua IDE:
4. Execute o container na pasta docker para instalar o banco de dados
    ```bash
   docker-compose up -d 
   ``` 
5. Configure suas variáveis de ambiente do docker-compose e do application.properties

6. Execute aplicação

Acesse a documentação da API no Swagger: http://localhost:{PORT}/swagger-ui/index.html