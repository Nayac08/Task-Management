package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Board;
import models.NodeList;

public class BoardUI{
	private Board board;
	private int idxListNode = 0;

	@FXML private VBox boardGUI;
	@FXML private Text displayName;
	@FXML private HBox displayZone;
	@FXML private VBox addListZone;
	@FXML private VBox addListNodeDetail;
	@FXML private TextArea titleArea;
	@FXML private Button addListNodeButton;

	public BoardUI(Board board) {
		setBoard(board);
		loadInitialFXML();
        displayName.setText(board.getName());
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Board.fxml"));
            loader.setController(this);
            setBoardGUI(loader.load());
            this.boardGUI = loader.load();
            handleHideAddListDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	Node firstChild = displayZone.getChildren().removeLast();
    	displayZone.getChildren().clear();
    	for (NodeList nodeList: board.getNodeLists()) {
    		NodeListUI nodeListUI;
			try {
				nodeListUI = new NodeListUI(nodeList);
				nodeListUI.updateGUI();
				displayZone.getChildren().add(nodeListUI.getNodeListGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	displayZone.getChildren().add(firstChild);
    	handleHideAddListDetailButton();
    }

    @FXML
    public void handleAddNodeListToBoard() {
    	board.addNodeList(new NodeList(idxListNode, board, titleArea.getText()));
    	idxListNode++;
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
    
    @FXML
    public void handleClearDisplay() {
    	Main.mainInterfaceUI.getDisplayContainer().getChildren().clear();
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

	public VBox getBoardGUI() {
		return boardGUI;
	}

	public void setBoardGUI(VBox boardGUI) {
		this.boardGUI = boardGUI;
	}
}