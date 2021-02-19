package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.SocketException;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import controller.VistaPrincipal_C;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;


public class Main extends Application {
	public VistaPrincipal_C controlador = new VistaPrincipal_C();
	public FTPClient cliente = new FTPClient();
	private Stage LoginStage;


	@Override
	public void start(Stage primaryStage) throws Exception {
		 this.LoginStage = primaryStage; 
		 VistaPrincipal_C controlador;
	        
	        FXMLLoader loader = new FXMLLoader();        
	        loader.setLocation(getClass().getResource("../view/VistaPrincipal_V.fxml"));
	        
	        Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	       
	        primaryStage.setTitle("Login");
	        primaryStage.setScene(scene);
	        
	        VistaPrincipal_C Controller = loader.getController();
	        
	        primaryStage.show();
	        
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	public boolean StartConnection(VistaPrincipal_C controlador) {
		this.controlador = controlador;
		boolean conectado = false;
		try {
			cliente.connect(controlador.getFtpserver_id().getText().toString());
			conectado = cliente.login(controlador.getUsr_id().getText().toString(),controlador.getPwd_id().getText().toString());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conectado;
	}
	

	public void PreparativosInicio() throws IOException {
		
		controlador.getConnection_button().setDisable(true);
		controlador.getDeletefile_button().setDisable(false);
		controlador.getDeletefolder_button().setDisable(false);
		controlador.getDownfile_button().setDisable(false);
		controlador.getConnection_button().setDisable(false);
		controlador.getNewfolder_button().setDisable(false);
		controlador.getAcceder_button().setDisable(false);
		controlador.getTextarea_id().setEditable(false);





		controlador.getMaindirectory_id().setText(cliente.printWorkingDirectory());
		mostrarTextArea(cliente.printWorkingDirectory());
	}
	
	
	public void mostrarTextArea(String path) throws IOException {
		controlador.getTextarea_id().clear();
		if(controlador.getAcceder_id().getText().equalsIgnoreCase(cliente.printWorkingDirectory())){
			controlador.getActualdirectory_id().setText(cliente.printWorkingDirectory());
		}
		else {
			controlador.getActualdirectory_id().appendText("/"+path);
		}
		controlador.getTextarea_id().appendText("(ROOT) - "+path+"\n");
		FTPFile[] files = cliente.listFiles(path);
		for(FTPFile f: files) {
			if(f.isDirectory()) {
				controlador.getTextarea_id().appendText("(DIR) - "+f.getName()+"\n");
			}
			else {
				controlador.getTextarea_id().appendText("(FILE) - "+f.getName()+"\n");
			}
		}

	}
	
	public void addFolder(String path) throws IOException {
		TextInputDialog dialog = new TextInputDialog("Carpeta");
		dialog.setTitle("Crear Carpeta");
		dialog.setContentText("Nombre de la carpeta: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    if(cliente.makeDirectory(result.get())) {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("CARPETA CREADA");
		    	alert.setContentText("La carpeta "+result.get()+" ha sido creada correctamente");
		    	alert.showAndWait();
		    }
		    else {
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("Error al crear la carpeta");
		    	alert.setContentText("Algo ha sucedido. La carpeta no ha sido creada");
		    	alert.showAndWait();
		    	
		    }
		}
		mostrarTextArea(path);
	}
	
	public void AccesoCarpeta() throws IOException {
		String path = controlador.getAcceder_id().getText();
		
		mostrarTextArea(path);
			
	}
	
	public void deleteFolder() throws IOException{
		TextInputDialog dialog = new TextInputDialog("Carpeta");
		dialog.setTitle("Borrar Carpeta");
		dialog.setContentText("Nombre de la carpeta: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    if(cliente.removeDirectory(result.get())) {
		    	Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Borrando");
		    	alert.setContentText("¿Quieres borrar "+result.get()+" ?");
		    	Optional<ButtonType> resultdelete = alert.showAndWait();
		    	if (resultdelete.get() == ButtonType.OK){
		    	} else {
		    		alert = new Alert(AlertType.ERROR);
			    	alert.setTitle("Error");
			    	alert.setHeaderText("Error al borrar la carpeta");
			    	alert.setContentText("Algo ha sucedido. La carpeta no ha sido borrada");
			    	alert.showAndWait();
		    	    
		    	}

		    }
		    else {
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("Error al borrar la carpeta");
		    	alert.setContentText("Algo ha sucedido. La carpeta no ha sido borrada");
		    	alert.showAndWait();
		    	
		    }
		}
		mostrarTextArea("/");
	}
	
	public void deleteFile() throws IOException{
		TextInputDialog dialog = new TextInputDialog("Carpeta");
		dialog.setTitle("Borrar Carpeta");
		dialog.setContentText("Nombre de la carpeta: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    if(cliente.deleteFile(result.get())) {
		    	Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Borrando");
		    	alert.setContentText("¿Quieres borrar "+result.get()+" ?");
		    	Optional<ButtonType> resultdelete = alert.showAndWait();
		    	if (resultdelete.get() == ButtonType.OK){
		    	} else {
		    		alert = new Alert(AlertType.ERROR);
			    	alert.setTitle("Error");
			    	alert.setHeaderText("Error al borrar el archivo");
			    	alert.setContentText("Algo ha sucedido. El archivo no ha sido borrada");
			    	alert.showAndWait();
		    	    
		    	}

		    }
		    else {
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("Error al borrar el archivo");
		    	alert.setContentText("Algo ha sucedido. El archivo no ha sido borrada");
		    	alert.showAndWait();
		    	
		    }
		}
		mostrarTextArea("/");
	}
	
	public void subirArchivo() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		cliente.setFileType(FTP.BINARY_FILE_TYPE);
		File archivo = fileChooser.showOpenDialog(LoginStage);
		String ruta = archivo.getAbsolutePath();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(ruta));
		if(cliente.storeFile(archivo.getName(), in)){
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Archivo Subido");
	    	alert.setContentText("El archivo "+archivo.getName()+" ha sido subido correctamente");
	    	alert.showAndWait();
			
		}
		mostrarTextArea("/");

	}
	
	
	public void descargarArchivo() throws IOException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setTitle("Bajar archivo");
		fileChooser.setInitialFileName(controlador.getAcceder_id().getText());
	    File selec = fileChooser.showSaveDialog(null);
	    if(selec != null) {
	        try {
	            if(cliente.retrieveFile(controlador.getAcceder_id().getText(), new FileOutputStream(selec.getAbsolutePath()))) {
	                JOptionPane.showMessageDialog(null, "Descargado");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }else {
	        JOptionPane.showMessageDialog(null, "sin archivo seleccionado");
	    }
	}
	public void CerrarTodo() {
		try {
			cliente.disconnect();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
