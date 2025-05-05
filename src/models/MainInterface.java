package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import enums.FileType;


public class MainInterface {
	private int idxTaskFile;
	private List<TaskFile> taskFiles;

	public MainInterface() {
		setIdxTaskFile(0);
		setTaskFiles(new ArrayList<>());
	}

	public void addTaskFile(String title,FileType fileType, MainInterface mainInterface) {
		taskFiles.add(new TaskFile(idxTaskFile, title, fileType, mainInterface));
		idxTaskFile++;
	}

	public void addTaskFile(JSONObject jsonObject, MainInterface mainInterface) {
		taskFiles.add(new TaskFile(idxTaskFile, jsonObject, mainInterface));
		idxTaskFile++;
	}

	public TaskFile findTaskFile(int id) {
		for (TaskFile taskFile: taskFiles) {
			if (taskFile.getId() == id) {
				return taskFile;
			}
		}
		return null;
	}

	public void deleteTaskFile(int id) {
		for (TaskFile taskFile: taskFiles) {
			if (taskFile.getId() == id) {
				taskFiles.remove(taskFile);
				break;
			}
		}
	}

	public int getIdxTaskFile() {
		return idxTaskFile;
	}

	public void setIdxTaskFile(int idxTaskFile) {
		this.idxTaskFile = idxTaskFile;
	}

	public List<TaskFile> getTaskFiles() {
		return taskFiles;
	}

	public void setTaskFiles(List<TaskFile> taskFile) {
		this.taskFiles = taskFile;
	}
}
