package fr.eni.ENIEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Categorie;
import fr.eni.ENIEnchere.dal.CategorieDao;
import fr.eni.ENIEnchere.dal.ConnectionProvider;

public class CategorieDaoJdbcImpl implements CategorieDao {

	private List<Categorie> listCategories = new ArrayList<Categorie>();

	
	@Override
	public List<Categorie> selectAll() throws BusinessException {
		listCategories.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stt = cnx.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM CATEGORIES");
			
			while (rs.next()) {
				listCategories.add(new Categorie(rs.getInt(1), rs.getString(2)));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listCategories;
	}

	@Override
	public Categorie selectCategorieById(int no_categorie) throws BusinessException {
		
		Categorie c =null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement("SELECT * FROM CATEGORIES WHERE no_categorie=? ");
			pstt.setInt(1, no_categorie);
			
			ResultSet rs = pstt.executeQuery();
			
			while (rs.next()) {
				c = new Categorie(rs.getInt(1), rs.getString(2));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
