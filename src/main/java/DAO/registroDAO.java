package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.registro;

public class registroDAO {

	private Connection connection;

	public registroDAO(Connection connection) {
		this.connection = connection;
	}

	public List<registro> listar() throws SQLException {
		List<registro> resgistros = new ArrayList<>();

		String sql = ("SELECT ID, NOME, IDADE, RG, CPF, DATA_DE_NASCIMENTO FROM CADASTRO");

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {

				while (rst.next()) {
					registro registro = new registro(rst.getInt(1), rst.getString(2), rst.getInt(3), rst.getString(4),
							rst.getString(5), rst.getDate(6));

					resgistros.add(registro);
				}
			}
		}
		return resgistros;
	}

	public void Inserir(registro registros) throws SQLException {

		String sql = "INSERT INTO CADASTRO (NOME, IDADE, RG, CPF, DATA_DE_NASCIMENTO) VALUES (?, ?, ?, ?, ?)";

		connection.setAutoCommit(false);

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, registros.getNome());
			pstm.setInt(2, registros.getIdade());
			pstm.setString(3, registros.getRg());
			pstm.setString(4, registros.getCpf());
			pstm.setDate(5, registros.getDataNasc());
			pstm.execute();

			connection.commit();

			try (ResultSet rst = pstm.getGeneratedKeys()) {

				while (rst.next()) {
					Integer id = rst.getInt(1);
					System.out.println("\nO id criado foi :" + id);
				}
			}
			System.out.println("\nRegistro inserido: " + registros);
		}
	}

	public void deletar(Integer id) throws SQLException {

		String sql = "DELETE FROM CADASTRO WHERE ID = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();

			Integer linhasAfetadas = pstm.getUpdateCount();
			System.out.println("\nQuantidade de linha afetadas: " + linhasAfetadas);
			System.out.println("");
		}
	}

	public void alterar(String nome, Integer idade, String rg, String cpf, Date DataNasc, Integer id)
			throws SQLException {

		String sql = "UPDATE CADASTRO SET NOME = ?, IDADE = ?, RG = ?, CPF = ?, DATA_DE_NASCIMENTO = ? WHERE ID = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			connection.setAutoCommit(false);

			pstm.setString(1, nome);
			pstm.setInt(2, idade);
			pstm.setString(3, rg);
			pstm.setString(4, cpf);
			pstm.setDate(5, DataNasc);
			pstm.setInt(6, id);
			pstm.execute();

			connection.commit();

			Integer linhaUpdate = pstm.getUpdateCount();
			System.out.println("\nLinhas afetadas: " + linhaUpdate);

		}
	}
}