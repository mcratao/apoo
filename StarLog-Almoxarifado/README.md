# Almoxarifado StarLog — CRUD Java 17 + SQLite

**Autor:** Thiago Macedo Lunardon  
**RA:** 25102653-2

## Requisitos

- JDK 17 ou superior.
- Maven (ou IDE com suporte a projetos Maven).
- Acesso à internet na primeira compilação, para baixar a dependência `org.xerial:sqlite-jdbc`.

## Executar (terminal na pasta do projeto)

```bash
mvn compile exec:java
```

No VS Code, IntelliJ ou Eclipse: abra a pasta como projeto Maven, aguarde o download das dependências e execute a classe `br.com.starlog.Main`.

## Organização

- `br.com.starlog.model.Produto`: entidade e validações.
- `br.com.starlog.dao.ProdutoDAO`: interface com cinco operações.
- `br.com.starlog.dao.ProdutoDAOSqlite`: criação da tabela e implementação JDBC com PreparedStatement.
- `br.com.starlog.config.ConexaoFactory`: conexão JDBC SQLite.
- `br.com.starlog.Main`: simulação Create, Read, Update, Delete e estado final.

## Banco de dados

A fábrica usa por padrão `jdbc:sqlite:starlog.db`. O `Main` substitui esse endereço **somente na execução de demonstração** por um novo arquivo físico temporário, para que cada simulação comece com banco vazio e termine com exatamente 1 produto, sem apagar dados de execuções anteriores. O caminho completo do banco é mostrado no terminal. O arquivo não é excluído pelo programa, embora o sistema operacional possa limpar sua pasta temporária posteriormente.

A tabela é criada automaticamente pelo construtor de `ProdutoDAOSqlite` via `CREATE TABLE IF NOT EXISTS`.

## Exigências atendidas

- Validação de nome, preço e estoque.
- ID autoincrementado recuperado com `getGeneratedKeys()`.
- Busca por ID com `Optional<Produto>`.
- `listarTodos()` com `ORDER BY nome ASC` e `lista.add(mapearLinha(rs))`.
- Atualização com quatro parâmetros: nome, preço, estoque, ID.
- Exclusão restrita a `WHERE id = ?`.
- `executeUpdate()` e retorno `afetadas > 0` em atualização e exclusão.
- Demonstração de ID inexistente sem exceção.
- Verificação automática do estado final com exatamente um produto.

**Nota:** esta entrega implementa a missão de console, não uma interface gráfica Swing. A DAO pode servir de base a uma interface com JTable posteriormente.
