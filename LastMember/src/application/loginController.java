package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController implements Initializable {

	@FXML
	Button btnLogin, btnReg, btnCancel, btnClose;
	@FXML
	TextField txtId, txtPwd;
	@FXML
	Label lblOk;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void btnLoginAction(ActionEvent event) {
		DBConnection connDB = new DBConnection();
		Connection conn = connDB.getConnection();
		try {

			String tmp_Id = txtId.getText();
			String tmp_Pwd = txtPwd.getText();

			String sql = "SELECT COUNT(*) FROM SMEMBER where userid='" + tmp_Id + "' and userpwd='" + tmp_Pwd + "'";
//			System.out.println(sql);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (rs.getInt(1) > 0) {
					Main.global_user_id = tmp_Id;
					
					Stage stage = (Stage) btnLogin.getScene().getWindow();			
					Parent root = FXMLLoader.load(getClass().getResource("reg_e.fxml"));
					Scene scene = new Scene(root, 600, 400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setTitle("학생정보수정");
					stage.setScene(scene);
					stage.show();
					
//					System.out.println("레코드 존재 OK");
				} else {
//					System.out.println("레코드 존재 NO");
				}
			}

			stmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결 Error");
		}
	}

	public void btnRegAction(ActionEvent event) {
		try {			 
			Stage stage = (Stage) btnReg.getScene().getWindow();			
			Parent root = FXMLLoader.load(getClass().getResource("reg.fxml"));
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("학생등록");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCancelAction(ActionEvent event) {
		txtId.setText("");
		txtPwd.setText("");
	}

	public void btnCloseAction(ActionEvent event) {
		Stage stage =(Stage) btnClose.getScene().getWindow();
		stage.close();
	}
	

}
