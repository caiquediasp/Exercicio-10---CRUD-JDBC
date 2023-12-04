package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Datashow;
import conexao.Conexao;

public class CrudDatashow {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	public void inserirDatashow(Datashow datashow) throws Exception {
		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "INSERT INTO Datashow (descricao) VALUES(?);";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, datashow.getDescricao());
			
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
	
	public List<Datashow> listarDatashow() throws Exception {
		ResultSet resultSet = null;
		List<Datashow> listaDatashow = new ArrayList<>();

		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM Datashow";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String descricao = resultSet.getString(2);

				listaDatashow.add(new Datashow(id, descricao));
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
		
		return listaDatashow;
	}
	
	public Datashow buscarPorId(int idDatashow) throws Exception {
		ResultSet resultSet = null;
		Datashow datashow = new Datashow();
		
		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM Datashow WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDatashow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				datashow.setId(resultSet.getInt(1));
				datashow.setDescricao(resultSet.getString(2));
				
				return datashow;
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
