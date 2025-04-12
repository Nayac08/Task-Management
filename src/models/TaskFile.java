package models;

import java.io.File;

import interfaces.Displayable;

public class TaskFile {
	private int id;
	private MainInterface mainInterface;
	private Displayable display;
	private File file;

	public TaskFile(int id, String title, MainInterface mainInterface) {
		this.id = id;
		setFile(null);
		setMainInterface(mainInterface);
		setDisplay(new TeamDisplay(id, title));
	}

	public TaskFile(int id, File file, MainInterface mainInterface) {
		this.id = id;
		setFile(file);
		setMainInterface(mainInterface);
		setDisplay(CSVToBoard());
	}

	public Displayable CSVToBoard() {
		return null;
	}

	public File BoardToCSV() {
		return null;
	}

	public void importFile() {

	}

	public void exportFile() {

	}

	public Displayable getDisplay() {
		return display;
	}

	public void setDisplay(Displayable display) {
		this.display = display;
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
