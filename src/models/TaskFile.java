package models;

import java.io.File;

public class TaskFile {
	private int id;
	private MainInterface mainInterface;
	private Board board;
	private File file;
	
	public TaskFile(int id, String title, MainInterface mainInterface) {
		this.id = id;
		setFile(null);
		setMainInterface(mainInterface);
		setBoard(new Board(id, title));
	}
	
	public TaskFile(int id, File file, MainInterface mainInterface) {
		this.id = id;
		setFile(file);
		setMainInterface(mainInterface);
		setBoard(CSVToBoard());
	}
	
	public Board CSVToBoard() {
		return null;
	}
	
	public File BoardToCSV() {
		return null;
	}
	
	public void importFile() {
		
	}
	
	public void exportFile() {
		
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public MainInterface getMainInterface() {
		return mainInterface;
	}

	public void setMainInterface(MainInterface mainInterface) {
		this.mainInterface = mainInterface;
	}
}
