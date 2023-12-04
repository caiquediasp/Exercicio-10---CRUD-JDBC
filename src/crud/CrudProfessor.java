package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Professor;
import conexao.Conexao;

public class CrudProfessor {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	public void inserirProfessor(Professor professor) throws Exception {
		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "INSERT INTO Professor (nome, disciplina) VALUES(?, ?);";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, professor.getNome());
			preparedStatement.setString(2, professor.getDisciplina());
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Professor> listarProfessor() throws Exception {
		ResultSet resultSet = null;
		List<Professor> listaProfessor = new ArrayList<>();

		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM Professor";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String nome = resultSet.getString(2);
				String disciplina = resultSet.getString(3);

				listaProfessor.add(new Professor(id, nome, disciplina));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaProfessor;
	}
	
	public Professor buscarPorNome(String nomeProfessor) throws Exception {
		ResultSet resultSet = null;
		Professor professor = new Professor();
		
		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM Professor WHERE nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nomeProfessor);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				professor.setId(resultSet.getInt(1));
				professor.setNome(resultSet.getString(2));
				professor.setDisciplina(resultSet.getString(3));
				
				return professor;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
