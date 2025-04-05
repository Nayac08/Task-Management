package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import models.Board;
import models.NodeList;

public class BoardUI{
	public Board board;
	public HBox boardGUI;
	
	@FXML private Button addNodeListButton;
	
	public BoardUI() {
		try {
			this.boardGUI = FXMLLoader.load(getClass().getResource("Board.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void updateBoard() throws IOException{
    }

    @FXML
    public void handleAddNodeList() throws IOException{
    	NodeListUI nodeListUI = new NodeListUI(new NodeList(0, "New List"));
    	boardGUI.getChildren().add(nodeListUI.getNodeListGUI());
    }

    public void removeListFromBoard(NodeList list){
    }
    
    public void sortBoardLists(){
    }

    public void loadBoardData(){
    }

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public HBox getBoardGUI() {
		return boardGUI;
	}

	public void setBoardGUI(HBox boardGUI) {
		this.boardGUI = boardGUI;
	}
}