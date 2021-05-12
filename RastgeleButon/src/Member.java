import java.sql.SQLException;

import Helper.DbHelper;
import Helper.Metod_Helper;

public class Member extends user{

	DbHelper dbhelper = new DbHelper();

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int id, String name, String surname, String pass, String TC_No, String email) {
		super(id, name, surname, pass, TC_No, email);
		// TODO Auto-generated constructor stub
	}

	
	
	public boolean register(String tcno, String pass, String name) {

		int key = 0;

		boolean duplicate = false;
		String query = "insert into register (TC_No,Pass,Name) values (?,?,?)";

		try {
			connection = dbhelper.getConnection();

			statement = connection.createStatement();
			result = statement.executeQuery("Select * from register where TC_No = ' " + tcno + " ' ");


			while (result.next()) {
				duplicate = true;
				Metod_Helper.showMsg("Hasta zaten mevcut (TC)");

				break;

			}
			if (!duplicate) {

				pStatement = connection.prepareStatement(query);
				pStatement.setString(1, tcno);
				pStatement.setString(2, pass);
				pStatement.setString(3, name);
				pStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			dbhelper.showErrorMessage(e);
		}

		if (key == 1) {
			return true;
		} else
			return false;
	}
	
	
}
