package controller;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TreeView;

public class VistaPrincipal_C {
	
	
	@FXML
	private TextField ftpserver_id;
	@FXML
	private TextField usr_id;
	@FXML
	private TextField acceder_id;
	@FXML
	private PasswordField pwd_id;
	@FXML
	private TextArea textarea_id;
	@FXML
	private Button upfile_button;
	@FXML
	private Button downfile_button;
	@FXML
	private Button deletefile_button;
	@FXML
	private Button newfolder_button;
	@FXML
	private Button deletefolder_button;
	@FXML
	private Button exit_button;
	@FXML
	private Button connection_button;
	@FXML
	private TextField actualdirectory_id;
	@FXML
	private TextField maindirectory_id;
	

	public Main modelo;
	public VistaPrincipal_C() {
		
	}
	
	public void Connection() throws IOException {
		modelo = new Main();
		if(modelo.StartConnection(this)) {
			modelo.PreparativosInicio();
			
		}
	}
	
	public void crearCarpeta() throws IOException {
		modelo.addFolder(actualdirectory_id.getText());
	}
	
	public void Salir() {
		Main modelo = new Main();
		modelo.CerrarTodo();
	}
	
	public void abrirCarpeta() throws IOException {
		modelo.AccesoCarpeta();
		
	}
	
	public void borrarCarpeta() throws IOException {
		modelo.deleteFolder();
	}
	
	public void borrarArchivo() throws IOException {
		modelo.deleteFile();
	}
	
	public void addFile() throws IOException {
		modelo.subirArchivo();
	}
	public void downloadFile() throws IOException {
		modelo.descargarArchivo();
	}
	
	public TextField getFtpserver_id() {
		return ftpserver_id;
	}
	public void setFtpserver_id(TextField ftpserver_id) {
		this.ftpserver_id = ftpserver_id;
	}
	public TextField getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(TextField usr_id) {
		this.usr_id = usr_id;
	}
	public PasswordField getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(PasswordField pwd_id) {
		this.pwd_id = pwd_id;
	}

	public TextArea getTextarea_id() {
		return textarea_id;
	}

	public void setTextarea_id(TextArea textarea_id) {
		this.textarea_id = textarea_id;
	}

	public TextField getAcceder_id() {
		return acceder_id;
	}

	public void setAcceder_id(TextField acceder_id) {
		this.acceder_id = acceder_id;
	}

	public Button getUpfile_button() {
		return upfile_button;
	}
	public void setUpfile_button(Button upfile_button) {
		this.upfile_button = upfile_button;
	}
	public Button getDownfile_button() {
		return downfile_button;
	}
	public void setDownfile_button(Button downfile_button) {
		this.downfile_button = downfile_button;
	}
	public Button getDeletefile_button() {
		return deletefile_button;
	}
	public void setDeletefile_button(Button deletefile_button) {
		this.deletefile_button = deletefile_button;
	}
	public Button getNewfolder_button() {
		return newfolder_button;
	}
	public void setNewfolder_button(Button newfolder_button) {
		this.newfolder_button = newfolder_button;
	}
	public Button getDeletefolder_button() {
		return deletefolder_button;
	}
	public void setDeletefolder_button(Button deletefolder_button) {
		this.deletefolder_button = deletefolder_button;
	}
	public Button getExit_button() {
		return exit_button;
	}
	public void setExit_button(Button exit_button) {
		this.exit_button = exit_button;
	}
	public Button getConnection_button() {
		return connection_button;
	}
	public void setConnection_button(Button connection_button) {
		this.connection_button = connection_button;
	}
	public TextField getActualdirectory_id() {
		return actualdirectory_id;
	}
	public void setActualdirectory_id(TextField actualdirectory_id) {
		this.actualdirectory_id = actualdirectory_id;
	}
	public TextField getMaindirectory_id() {
		return maindirectory_id;
	}
	public void setMaindirectory_id(TextField maindirectory_id) {
		this.maindirectory_id = maindirectory_id;
	}
	
	

	


}
