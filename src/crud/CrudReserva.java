package crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Professor;
import modelo.Datashow;
import modelo.Reserva;
import conexao.Conexao;

public class CrudReserva {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	public void cadastrarReserva(Professor professor, Datashow datashow, LocalDate dataReserva) throws Exception {
		ResultSet resultSet = null;
		List<Reserva> listaTeste = new ArrayList<>();
		
		try {
			connection = Conexao.getDatabaseConnection();
			
			String sql = "SELECT * FROM reserva WHERE id_professor = ? AND dataReseva = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, professor.getId());
			preparedStatement.setDate(2, java.sql.Date.valueOf(dataReserva));
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int id_professor = resultSet.getInt(2);
				int id_datashow = resultSet.getInt(3);
				Date data = resultSet.getDate(4);
				
				listaTeste.add(new Reserva(id, id_professor, id_datashow, data.toLocalDate()));
			}
			
			if (listaTeste.isEmpty()) {
				sql = "SELECT * FROM reserva WHERE id_datashow = ? AND dataReseva = ?";
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, datashow.getId());
				preparedStatement.setDate(2, java.sql.Date.valueOf(dataReserva));
				
				while (resultSet.next()) {
					int id = resultSet.getInt(1);
					int id_professor = resultSet.getInt(2);
					int id_datashow = resultSet.getInt(3);
					Date data = resultSet.getDate(4);
					
					listaTeste.add(new Reserva(id, id_professor, id_datashow, data.toLocalDate()));
				}
				
				if(listaTeste.isEmpty()) {
					sql = "INSERT INTO reserva (id_professor, id_datashow, dataReserva) VALUES(?, ?, ?);";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, professor.getId());
					preparedStatement.setInt(2, datashow.getId());
					preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));;
						
					preparedStatement.executeUpdate();
				} else {
					System.out.println("O Datashow ja possui uma reserva para esta data!");
				}
			} else {
				System.out.println("O Professor ja possui uma reserva para esta data!");
			}
			
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
	
	public List<Reserva> buscarPorData(LocalDate dataConsulta) throws Exception {
		ResultSet resultSet = null;
		List<Reserva> listaReservas = new ArrayList<>();

		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM reserva where dataReseva = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, java.sql.Date.valueOf(dataConsulta));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int id_professor = resultSet.getInt(2);
				int id_datashow = resultSet.getInt(3);
				Date data = resultSet.getDate(4);
				
				listaReservas.add(new Reserva(id, id_professor, id_datashow, data.toLocalDate()));
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
		
		return listaReservas;
	}
		
	
	public List<Reserva> listarReservas() throws Exception {
		ResultSet resultSet = null;
		List<Reserva> listaReservas = new ArrayList<>();

		try {
			connection = Conexao.getDatabaseConnection();
			String sql = "SELECT * FROM reserva";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int id_professor = resultSet.getInt(2);
				int id_datashow = resultSet.getInt(3);
				Date data = resultSet.getDate(4);
				
				listaReservas.add(new Reserva(id, id_professor, id_datashow, data.toLocalDate()));
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
		
		return listaReservas;
	}
	
	public void cancelarReserva(Professor professor, LocalDate dataCancelar) throws Exception { 
		ResultSet resultSet = null;
		Reserva reserva = new Reserva();
		
		try {
			connection = Conexao.getDatabaseConnection();
			
			String sql = "SELECT * FROM reserva WHERE id_professor = ? AND dataReseva = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, professor.getId());
			preparedStatement.setDate(2, java.sql.Date.valueOf(dataCancelar));
			
			Date data = null;
			data = (resultSet.getDate(4));
			
			if(data != null) {
				if(data.toLocalDate().isBefore(dataCancelar)) {
					sql = "DELETE FROM reserva WHERE id_professor = ? and dataReserva = ?;";
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, professor.getId());
					preparedStatement.setDate(2, java.sql.Date.valueOf(dataCancelar));
					
					preparedStatement.executeUpdate();
				} else {
					System.out.println("Nao e possivel cancelar a reserva, pois a data e retroativa!");
				}
			} else {
				System.out.println("Nao possui reserva nesta data!");
			}

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
	
}
