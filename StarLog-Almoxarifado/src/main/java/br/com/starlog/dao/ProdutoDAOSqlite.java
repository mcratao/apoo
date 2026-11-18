package br.com.starlog.dao;

import br.com.starlog.config.ConexaoFactory;
import br.com.starlog.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Implementacao JDBC do contrato ProdutoDAO. */
public class ProdutoDAOSqlite implements ProdutoDAO {

    public ProdutoDAOSqlite() {
        // DDL executado uma vez por instancia; IF NOT EXISTS preserva registros.
        String sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL,
                estoque INTEGER NOT NULL
            )
            """;
        try (Connection conn = ConexaoFactory.obterConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela produtos.", e);
        }
    }

    @Override
    public void salvar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto nao pode ser nulo.");
        }
        if (produto.getId() != null) {
            throw new IllegalArgumentException("Novo produto nao deve ter ID preenchido.");
        }
        String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());

            int afetadas = stmt.executeUpdate();
            if (afetadas != 1) {
                throw new SQLException("Insercao nao afetou exatamente uma linha.");
            }
            try (ResultSet chaves = stmt.getGeneratedKeys()) {
                if (!chaves.next()) {
                    throw new SQLException("Banco nao retornou o ID autoincrementado.");
                }
                produto.setId(chaves.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto.", e);
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        if (id == null) throw new IllegalArgumentException("ID nao pode ser nulo.");
        String sql = "SELECT id, nome, preco, estoque FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapearLinha(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID.", e);
        }
    }

    @Override
    public List<Produto> listarTodos() {
        String sql = "SELECT id, nome, preco, estoque FROM produtos ORDER BY nome ASC";
        List<Produto> lista = new ArrayList<>();
        try (Connection conn = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearLinha(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos.", e);
        }
    }

    @Override
    public boolean atualizar(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Produto e ID sao obrigatorios para atualizar.");
        }
        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?";
        try (Connection conn = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setLong(4, produto.getId());
            int afetadas = stmt.executeUpdate();
            return afetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto.", e);
        }
    }

    @Override
    public boolean excluir(Long id) {
        if (id == null) throw new IllegalArgumentException("ID nao pode ser nulo.");
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int afetadas = stmt.executeUpdate();
            return afetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto.", e);
        }
    }

    // Centraliza a conversao de ResultSet para entidade.
    private Produto mapearLinha(ResultSet rs) throws SQLException {
        return new Produto(rs.getLong("id"), rs.getString("nome"),
                rs.getDouble("preco"), rs.getInt("estoque"));
    }
}
