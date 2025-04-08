package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Board;
import models.NodeList;

public class BoardUI{
	private Board board;
	
	@FXML private HBox boardGUI;
	@FXML private VBox addListZone;
	@FXML private VBox addListNodeDetail;
	@FXML private TextArea titleArea;
	@FXML private Button addListNodeButton;
	
	public BoardUI() {
		this.board = new Board(0, "StarterBoard");
		loadInitialFXML();
	}
	
    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Board.fxml"));
            loader.setController(this);
            this.boardGUI = loader.load();
            handleHideAddListDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void updateGUI() {
    	boardGUI.getChildren().removeIf(child -> child != addListZone);
    	for (NodeList nodeList: board.getNodeLists()) {
    		NodeListUI nodeListUI;
			try {
				nodeListUI = new NodeListUI(nodeList);
				nodeListUI.updateGUI();
				boardGUI.getChildren().add(nodeListUI.getNodeListGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Node firstChild = boardGUI.getChildren().remove(0);
    	boardGUI.getChildren().add(firstChild);
    }
    
    @FXML
    public void handleAddNodeListToBoard() {
    	System.out.println("Click");
    	board.addNodeList(new NodeList(0, titleArea.getText()));
    	titleArea.setText("");
    	handleHideAddListDetailButton();
    	updateGUI();
    }
    
    @FXML
    public void handleShowAddListDetailButton() {
    	addListNodeDetail.setVisible(true);
    	addListNodeDetail.setManaged(true);
    	addListNodeButton.setVisible(false);
    	addListNodeButton.setManaged(false);
    }
    
    @FXML
    public void handleHideAddListDetailButton() {
    	addListNodeDetail.setVisible(false);
    	addListNodeDetail.setManaged(false);
    	addListNodeButton.setVisible(true);
    	addListNodeButton.setManaged(true);
    }

    public void removeListFromBoard(NodeList list){
    	updateGUI();
    }
    
    public void sortBoardLists(){
    	updateGUI();
    }

    public void loadBoardData(){
    	updateGUI();
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