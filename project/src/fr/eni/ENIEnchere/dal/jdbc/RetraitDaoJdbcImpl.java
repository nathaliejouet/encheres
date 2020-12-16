package fr.eni.ENIEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Retrait;
import fr.eni.ENIEnchere.dal.ConnectionProvider;
import fr.eni.ENIEnchere.dal.RetraitDao;

public class RetraitDaoJdbcImpl implements RetraitDao {
	public static final String SELECT_RETRAIT = "SELECT rue, code_postal, ville FROM RETRAITS WHERE no_article=?";

	@Override
	public Retrait select_retrait(int no_article) throws BusinessException {
		Retrait retrait = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT)) {
			pstmt.setInt(1, no_article);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				retrait = new Retrait(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrait;
	}

}
