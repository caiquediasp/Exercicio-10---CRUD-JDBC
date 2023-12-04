package modelo;

import java.time.LocalDate;

public class Reserva {
	private int id;
	private int id_professor;
	private int id_datashow;
	private LocalDate data;
	
	public Reserva(){}

	public Reserva(int id, int id_professor, int id_datashow, LocalDate data) {
		super();
		this.id = id;
		this.id_professor = id_professor;
		this.id_datashow = id_datashow;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}

	public int getId_datashow() {
		return id_datashow;
	}

	public void setId_datashow(int id_datashow) {
		this.id_datashow = id_datashow;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
