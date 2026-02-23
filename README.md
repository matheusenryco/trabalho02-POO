# Sistema de Gerenciamento de Concessionária

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge)

Sistema desktop desenvolvido em Java para gerenciamento completo de uma concessionária de veículos, implementando operações CRUD (Create, Read, Update, Delete) com interface gráfica e persistência em banco de dados SQLite.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como trabalho acadêmico da disciplina de Programação Orientada a Objetos (POO). O sistema permite o gerenciamento de:

- **Clientes** - Cadastro e controle de clientes da concessionária
- **Funcionários** - Gerenciamento de equipe e suas atribuições
- **Veículos** - Controle do estoque de veículos disponíveis
- **Vendas** - Registro e acompanhamento de vendas realizadas

## 🚀 Funcionalidades

### Módulo de Clientes
- Cadastro de novos clientes
- Consulta de clientes cadastrados
- Alteração de dados de clientes
- Remoção de clientes
- Geração de relatórios

### Módulo de Funcionários
- Cadastro de funcionários
- Consulta de funcionários
- Atualização de dados funcionais
- Remoção de funcionários
- Relatórios de funcionários

### Módulo de Veículos
- Cadastro de veículos no estoque
- Consulta de veículos disponíveis
- Alteração de informações de veículos
- Remoção de veículos
- Relatórios de estoque

### Módulo de Vendas
- Registro de novas vendas
- Consulta de vendas realizadas
- Alteração de informações de vendas
- Cancelamento de vendas
- Relatórios de vendas

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão **MVC (Model-View-Controller)** com a seguinte estrutura:

```
trabalho02--POO/
├── src/
│   ├── Model/              # Modelos de dados
│   │   ├── Cliente.java
│   │   ├── Funcionario.java
│   │   ├── Veiculo.java
│   │   └── Vendas.java
│   │
│   ├── Controllers/        # Controladores de lógica de negócio
│   │   ├── ClienteController.java
│   │   ├── FuncionarioController.java
│   │   ├── VeiculoController.java
│   │   └── VendasController.java
│   │
│   ├── Banco/             # Camada de persistência
│   │   ├── SQLiteDriver.java
│   │   ├── ClienteBD.java
│   │   ├── FuncionarioBD.java
│   │   ├── VeiculoBD.java
│   │   └── VendasDB.java
│   │
│   ├── cadastro/          # Telas de cadastro
│   ├── consulta/          # Telas de consulta
│   ├── alterar/           # Telas de alteração
│   ├── remover/           # Telas de remoção
│   ├── relatorio/         # Telas de relatórios
│   ├── Inicio/            # Telas iniciais de cada módulo
│   └── OpcoesInicio.java  # Tela principal do sistema
```

## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem de programação principal
- **Swing** - Framework para interface gráfica (GUI)
- **SQLite** - Banco de dados relacional leve
- **JDBC** - Java Database Connectivity para acesso ao banco de dados
- **NetBeans** - IDE de desenvolvimento (arquivos .form)
- **Apache Ant** - Ferramenta de build (build.xml)

## 📦 Modelos de Dados

### Cliente
- Nome
- Telefone
- Email
- RG
- CPF

### Funcionário
- Número de Matrícula
- Nome
- Qualificação
- Descrição da Função
- Carga Horária Semanal

### Veículo
- Chassi
- Nome
- Cor
- Número de Marchas
- Número de Portas
- Marca
- Ano

### Vendas
- ID
- Data
- Valor
- Cliente (relacionamento)
- Funcionário (relacionamento)
- Veículo (relacionamento)

## 🔧 Pré-requisitos

Para executar este projeto, você precisará ter instalado:

- **Java JDK 8** ou superior
- **NetBeans IDE** (recomendado) ou outra IDE Java
- **SQLite JDBC Driver** (incluído no projeto)

## ⚙️ Instalação e Execução

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd trabalho02--POO
```

### 2. Abra o projeto no NetBeans
- Abra o NetBeans
- Vá em `File > Open Project`
- Selecione a pasta `trabalho02--POO`

### 3. Configure o banco de dados
O banco de dados SQLite será criado automaticamente na primeira execução. Certifique-se de que o arquivo de banco de dados está configurado corretamente em `SQLiteDriver.java`.

### 4. Execute o projeto
- No NetBeans: Clique com botão direito no projeto > `Run`
- Ou execute via linha de comando:
```bash
ant clean
ant compile
ant run
```

### 5. Usando o build.xml
```bash
# Limpar o projeto
ant clean

# Compilar
ant compile

# Gerar JAR
ant jar

# Executar
ant run
```

## 💻 Como Usar

1. **Tela Inicial**: Ao iniciar o sistema, você verá a tela de opções de gerenciamento
2. **Selecione um Módulo**: Escolha entre Clientes, Funcionários, Veículos ou Vendas
3. **Escolha uma Operação**: 
   - Cadastrar novo registro
   - Consultar registros existentes
   - Alterar informações
   - Remover registros
   - Gerar relatórios

## 📝 Padrões de Projeto Utilizados

- **MVC (Model-View-Controller)** - Separação entre lógica de negócio, apresentação e controle
- **DAO (Data Access Object)** - Camada de acesso a dados isolada (pacote Banco)
- **Singleton** (potencial) - Para conexões com banco de dados


## ✒️ Autor

Desenvolvido por Matheus Enryco e Murilo Bortoli


