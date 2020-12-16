package fr.eni.ENIEnchere.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEnchere.ArticleEtat;
import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.ArticleDao;
import fr.eni.ENIEnchere.dal.CategorieDao;
import fr.eni.ENIEnchere.dal.ConnectionProvider;
import fr.eni.ENIEnchere.dal.DaoFactory;
import fr.eni.ENIEnchere.dal.RetraitDao;
import fr.eni.ENIEnchere.dal.UtilisateurDao;

public class ArticleDaoJdbcImpl implements ArticleDao {

	private List<Article> listArticles = new ArrayList<Article>();
	private UtilisateurDao uDao = new UtilisateurDaoJdbcImpl();
	private CategorieDao cDao = DaoFactory.getCategorieDao();
	private RetraitDao rDao = DaoFactory.getRetraitDao();
	
	private static final String SELECT_ARTICLES = "SELECT * FROM articles_vendus ";
	
	@Override
	public void add(Article a) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"INSERT INTO ARTICLES_VENDUS (nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente"
					+ ",no_utilisateur,no_categorie,etat_vente,image) VALUES"
					+ " (?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstt.setString(1, a.getNom_article());
			pstt.setString(2, a.getDescription());
			pstt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_enchere()));
			pstt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_enchere()));
			pstt.setInt(5, a.getPrix_initial());
			pstt.setInt(6, a.getPrix_vente());
			pstt.setInt(7, a.getUtilisateur().getNo_utilisateur());
			pstt.setInt(8, a.getCategorieArticle().getNo_categorie());
			pstt.setString(9, a.getEtat_vente());
			pstt.setString(10, a.getImage());
			
			pstt.executeUpdate();
			
			ResultSet rs = pstt.getGeneratedKeys();
			
			while(rs.next()) {
				a.setNo_article(rs.getInt(1));
			}
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public void update(Article a) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"UPDATE ARTICLES_VENDUS set nom_article=? ,description=?,date_debut_enchere=?,date_fin_enchere=?,prix_initial=? ,prix_vente=?"
					+",no_utilisateur=?,no_categorie=?,etat_vente=?,image=? where no_article = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstt.setString(1, a.getNom_article());
			pstt.setString(2, a.getDescription());
			pstt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_enchere()));
			pstt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_enchere()));
			pstt.setInt(5, a.getPrix_initial());
			pstt.setInt(6, a.getPrix_vente());
			pstt.setInt(7, a.getUtilisateur().getNo_utilisateur());
			pstt.setInt(8, a.getCategorieArticle().getNo_categorie());
			pstt.setString(9, a.getEtat_vente());
			pstt.setString(10, a.getImage());
			pstt.setInt(11, a.getNo_article());
			pstt.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public void delete(Article a) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"DELETE FROM ARTICLES_VENDUS WHERE no_article=?");
			pstt.setInt(1, a.getNo_article());
			
			pstt.executeUpdate();
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public Article selectById(int id) throws BusinessException {
		Article a = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_article=?");
			pstt.setInt(1, id);
			
			ResultSet rs = pstt.executeQuery();
			
			
			while (rs.next()) {
				a = new Article(id, rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), uDao.select_user_by_no(rs.getInt(8)), cDao.selectCategorieById(rs.getInt(9)));
				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public List<Article> selectByEtat(String etat) throws BusinessException {
		listArticles.clear();
		
		if(etat==ArticleEtat.ALL) {
			return listArticles = this.selectAll();
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"SELECT * FROM ARTICLES_VENDUS WHERE etat_vente=?");
			
			pstt.setString(1, etat);
			ResultSet rs = pstt.executeQuery();
			
			while (rs.next()) {
				Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), uDao.select_user_by_no(rs.getInt(8)), cDao.selectCategorieById(rs.getInt(9)));
				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
				listArticles.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listArticles;
	}

	@Override
	public List<Article> selectByUtilisateur(Utilisateur u) throws BusinessException {
		listArticles.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=?");
			
			pstt.setInt(1, u.getNo_utilisateur());
			ResultSet rs = pstt.executeQuery();
			
			while (rs.next()) {
				Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), u, cDao.selectCategorieById(rs.getInt(9)));
				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
				listArticles.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listArticles;
	}

	@Override
	public List<Article> selectAll() throws BusinessException {

		listArticles.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stt = cnx.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM ARTICLES_VENDUS");
			
			while (rs.next()) {
				Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), uDao.select_user_by_no(rs.getInt(8)), cDao.selectCategorieById(rs.getInt(9)));
				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
				listArticles.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listArticles;
	}
	

	@Override
	public void pc_updateArticle() throws SQLException {
 
        String sql = "{call updateArticle}";
        
        try (Connection cnx = ConnectionProvider.getConnection()) {
        	CallableStatement statement = cnx.prepareCall(sql);
        	statement.execute();
        	statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }

	@Override
	public List<Article> selectAchatByFilter(String etat, String categorie, String nom, String no_user,Boolean isVendeur) throws BusinessException {

		listArticles.clear();
		int index = 0;
		StringBuffer sql = new StringBuffer();
			sql.append("SELECT ARTICLES_VENDUS.* FROM ARTICLES_VENDUS ");
			sql.append("INNER JOIN ENCHERES ");
			sql.append("ON ENCHERES.no_article = ARTICLES_VENDUS.no_article ");
			sql.append("WHERE ARTICLES_VENDUS.no_categorie LIKE ? AND ARTICLES_VENDUS.etat_vente LIKE ? ");
			sql.append("AND ARTICLES_VENDUS.nom_article LIKE ? AND ENCHERES.no_utilisateur LIKE ? ");
			if(etat.equals(ArticleEtat.Ouvert)){
				sql.append("AND CURRENT_TIMESTAMP BETWEEN date_debut_enchere AND date_fin_enchere ");
				etat="%%";
				index =4;
			}
			if (isVendeur ) {
				sql.append("OR ARTICLES_VENDUS.no_utilisateur LIKE ? ");
				if (index ==0) {
					index=4;
				}else {
					index=5;
				}
			}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			
			
			PreparedStatement pstt = cnx.prepareStatement(new String(sql));
			pstt.setString(1, categorie);
			pstt.setString(2, etat);
			pstt.setString(3, nom);
			pstt.setString(4, no_user);
			if (isVendeur ) {
				pstt.setString(index, no_user);
			}
			if(etat.equals(ArticleEtat.Ouvert)){
				pstt.setTimestamp(index, Timestamp.valueOf(LocalDateTime.now()));
			}
			ResultSet rs = pstt.executeQuery();
			while (rs.next()) {
				Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), uDao.select_user_by_no(rs.getInt(8)), cDao.selectCategorieById(rs.getInt(9)));

				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
				listArticles.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listArticles;
	}
	
	@Override
	public List<Article> selectVenteByFilter(String etat, String categorie, String nom, String no_user) throws BusinessException {

		listArticles.clear();
		int index = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ARTICLES_VENDUS.* FROM ARTICLES_VENDUS");
		sql.append("  WHERE ARTICLES_VENDUS.no_categorie LIKE ? AND ARTICLES_VENDUS.etat_vente LIKE ? ");
		sql.append(" AND ARTICLES_VENDUS.nom_article LIKE ? AND ARTICLES_VENDUS.no_utilisateur LIKE ?");
		if (etat.equals(ArticleEtat.NonOuver)) {
			sql.append(" AND ? < date_debut_enchere");
			etat=ArticleEtat.CR;
			index = 5;
		}
		if (etat.equals(ArticleEtat.VD)) {
			sql.append(" AND ? > date_fin_enchere");
			etat="%%";
			if (index ==0) {
				index=5;
			}else {
				index=6;
			}
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstt = cnx.prepareStatement(new String(sql));
			pstt.setString(1, categorie);
			pstt.setString(2, etat);
			pstt.setString(3, "%"+nom+"%");
			pstt.setString(4, no_user);
			if (etat.equals(ArticleEtat.CR)) {
				pstt.setTimestamp(index, Timestamp.valueOf(LocalDateTime.now()));
			}if(etat.equals("%%") && index !=0) {
				pstt.setTimestamp(index, Timestamp.valueOf(LocalDateTime.now()));
			}
			ResultSet rs = pstt.executeQuery();
			while (rs.next()) {
				Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
						rs.getInt(6), rs.getInt(7), rs.getString(10), uDao.select_user_by_no(rs.getInt(8)), cDao.selectCategorieById(rs.getInt(9)));

				a.setListeEnchere(DaoFactory.getEnchereDao().selectByArticle(a));
				listArticles.add(a);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listArticles;
	}

}
