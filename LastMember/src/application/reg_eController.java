package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class reg_eController implements Initializable {

	@FXML
	TextField txtId, txtName, txtHakbun, txtMajor, txtPhone, txtAddress;
	@FXML
	PasswordField txtPwd1, txtPwd2;
	@FXML
	Button btnReg_e, btnClose, btnCancel;
	@FXML
	Label lblMsg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		viewMemberData();
	}

	public void btnReg_eAction(ActionEvent event) {
		DBConnection connNow = new DBConnection();
		Connection conn = connNow.getConnection();

		String sql = "UPDATE SMEMBER" + " SET" + " USERID=?," + " USERPWD=?," + " UNAME=?," + " UNUMBER=?,"
				+ " UPHONE=?," + " UMAJOR=?," + " UADDRESS=?" + " WHERE USERID=?";
//		System.out.println(sql);

		try {
			// 데이터베이스에서 회원정보를 수정합니다
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtId.getText());
			pstmt.setString(2, txtPwd1.getText());
			pstmt.setString(3, txtName.getText());
			pstmt.setString(4, txtHakbun.getText());
			pstmt.setString(5, txtPhone.getText());
			pstmt.setString(6, txtMajor.getText());
			pstmt.setString(7, txtAddress.getText());
			pstmt.setString(8, Main.global_user_id);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

			lblMsg.setText("회원정보가 수정되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCloseAction(ActionEvent event) {
		try {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("학생로그인");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCancelAction(ActionEvent event) {
		txtId.setText("");
		txtName.setText("");
		txtHakbun.setText("");
		txtMajor.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtPwd1.setText("");
		txtPwd2.setText("");
	}
	public void viewMemberData() {
		DBConnection connDB = new DBConnection();
		Connection conn = connDB.getConnection();

		String tmp_Id = Main.global_user_id;

		String sql = "SELECT * FROM SMEMBER where userid='" + tmp_Id + "'";
//		System.out.println(sql);

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 데이터베이스에 저장된 회원정보를 불러옵니다
			if (rs.next()) {
				txtName.setText(rs.getString("UNAME"));
				txtId.setText(rs.getString("USERID"));
				txtPwd1.setText(rs.getString("USERPWD"));
				txtPwd2.setText(rs.getString("USERPWD"));
				txtHakbun.setText(rs.getString("UNUMBER"));
				txtMajor.setText(rs.getString("UMAJOR"));
				txtPhone.setText(rs.getString("UPHONE"));
				txtAddress.setText(rs.getString("UADDRESS"));
			} else {
			
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
