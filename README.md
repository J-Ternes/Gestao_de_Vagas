# Aplicação de Gerenciamento de Vagas de RH
Esta é uma aplicação Spring Boot que permite o gerenciamento de vagas de Recursos Humanos (RH).
 
## Requisitos
- Java 17 ou superior
- Maven
- Banco de dados (Postgres)

## Configuração
1.Clone o repositório:
'git clone https://github.com/danileao/gestao_vagas_rocket.git'

2.Configure o banco de dados editando o arquivo application.properties e fornecendo as configurações necessárias:
'''spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=admin
spring.datasource.password=admin'''

3.Construa a aplicação:
'mvn clean install'

4.Inicie a aplicação
'mvn spring-boot:run'
