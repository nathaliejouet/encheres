package fr.eni.ENIEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEnchere.ArticleEtat;
import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Enchere;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.ArticleDao;
import fr.eni.ENIEnchere.dal.CodesResultatDAL;
import fr.eni.ENIEnchere.dal.ConnectionProvider;
import fr.eni.ENIEnchere.dal.DaoFactory;
import fr.eni.ENIEnchere.dal.EnchereDao;
import fr.eni.ENIEnchere.dal.UtilisateurDao;

public class EnchereDaoJdbcImpl implements EnchereDao {
	
	
	private List<Enchere> listEncheres = new ArrayList<Enchere>();
	private UtilisateurDao uDao = new UtilisateurDaoJdbcImpl();
	private ArticleDao aDao = DaoFactory.getArticleDao();
	
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES set no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? and no_article = ?;";
	private static final String SELECT_BY_ARTICLE = "Select no_utilisateur, no_article, date_enchere, montant_enchere FROM Encheres WHERE no_article = ? Order by montant_enchere DESC";
	
	@Override
	public void add(Enchere e) throws BusinessException {

			try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES ( ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstt.setInt(1, e.getUtilisateur().getNo_utilisateur());
			pstt.setInt(2, e.getArticle().getNo_article());
			pstt.setTimestamp(3, Timestamp.valueOf(e.getDate_enchere()));
			pstt.setInt(4, e.getMontant_enchere());
			
			pstt.executeQuery();
			cnx.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void update(Enchere enchere) throws BusinessException {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstt2 = conn.prepareStatement(UPDATE_ENCHERE);

			pstt2.setInt(1, enchere.getUtilisateur().getNo_utilisateur());
			pstt2.setInt(2, enchere.getArticle().getNo_article());
			pstt2.setTimestamp(3, Timestamp.valueOf(enchere.getDate_enchere()));
			pstt2.setInt(4, enchere.getMontant_enchere());
			pstt2.setInt(5, enchere.getUtilisateur().getNo_utilisateur());
			pstt2.setInt(6, enchere.getArticle().getNo_article());
			pstt2.executeUpdate();

			pstt2.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void delete(Enchere e) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"DELETE FROM ENCHERES WHERE no_utilisateur=? AND no_article=?");
			pstt.setInt(1, e.getUtilisateur().getNo_utilisateur());
			pstt.setInt(2, e.getArticle().getNo_article());
			
			pstt.executeUpdate();
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteAll(Enchere e) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"DELETE FROM ENCHERES WHERE no_article=?");
			pstt.setInt(1, e.getArticle().getNo_article());
			
			pstt.executeUpdate();
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public List<Enchere> selectByFilter(String etat,String categorie,String nom) throws BusinessException {
		
		listEncheres.clear();
			
		if(etat==ArticleEtat.ALL) {
			return listEncheres = this.selectAll();
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"SELECT ENCHERES.no_utilisateur,ENCHERES.no_article,date_enchere,montant_enchere FROM ENCHERES"
					+ "  INNER JOIN ARTICLES_VENDUS"
					+ "  ON ENCHERES.no_article = ARTICLES_VENDUS.no_article"
					+ "  WHERE ARTICLES_VENDUS.no_categorie LIKE ? AND ARTICLES_VENDUS.etat_vente LIKE ?"
					+ "  AND ARTICLES_VENDUS.nom_article LIKE ?");
			
			pstt.setString(1, "%"+categorie+"%");
			pstt.setString(2, "%"+etat+"%");
			pstt.setString(3, "%"+nom+"%");
			ResultSet rs = pstt.executeQuery();
			
			while (rs.next()) {
				listEncheres.add(new Enchere(rs.getTimestamp(3).toLocalDateTime(), rs.getInt(4),uDao.select_user_by_no(rs.getInt(1)),aDao.selectById(rs.getInt(2))));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listEncheres;
	}
	
	@Override
	public List<Enchere> selectByFilter(String etat,String categorie,String nom, String no_user) throws BusinessException {
		
		listEncheres.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"SELECT ENCHERES.no_utilisateur,ENCHERES.no_article,date_enchere,montant_enchere FROM ENCHERES"
					+ "  INNER JOIN ARTICLES_VENDUS"
					+ "  ON ENCHERES.no_article = ARTICLES_VENDUS.no_article"
					+ "  WHERE ARTICLES_VENDUS.no_categorie LIKE ? AND ARTICLES_VENDUS.etat_vente LIKE ? "
					+ "	 AND ENCHERES.no_utilisateur LIKE ? AND ARTICLES_VENDUS.nom_article LIKE ?");
			
			pstt.setString(1, "%"+categorie+"%");
			pstt.setString(2, "%"+etat+"%");
			pstt.setString(3, "%"+no_user+"%");
			pstt.setString(4, "%"+nom+"%");
			ResultSet rs = pstt.executeQuery();
			while (rs.next()) {
				listEncheres.add(new Enchere(rs.getTimestamp(3).toLocalDateTime(), rs.getInt(4),uDao.select_user_by_no(rs.getInt(1)),aDao.selectById(rs.getInt(2))));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listEncheres;
	}

	
	/**
	 * Récupère une liste d'enchères pour un article triée du montant le plus
	 * élevé au montant le moins élevé
	 * 
	 * @throws BusinessException
	 */
	@Override
	public List<Enchere> selectByArticle(Article article) throws BusinessException {
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		UtilisateurDao Udao = DaoFactory.getUtilisateurDao();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE)) {
			pstmt.setInt(1, article.getNo_article());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Connexion r�ussie");
				enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"),
						Udao.select_user_by_no(rs.getInt("no_utilisateur")), article);
				listeEnchere.add(enchere);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeEnchere;
	}
	
	@Override
	public List<Enchere> selectByUtilisateur(Utilisateur u) throws BusinessException {
		listEncheres.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement("SELECT no_utilisateur,no_article,date_enchere,montant_enchere FROM ENCHERES WHERE no_utilisateur = ?");
			
			pstt.setInt(1, u.getNo_utilisateur());
			ResultSet rs = pstt.executeQuery();
			while (rs.next()) {
				listEncheres.add(new Enchere(rs.getTimestamp(3).toLocalDateTime(), rs.getInt(4),u,aDao.selectById(rs.getInt(2))));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listEncheres;
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		listEncheres.clear();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stt = cnx.createStatement();
			
			ResultSet rs = stt.executeQuery("SELECT no_utilisateur,no_article,date_enchere,montant_enchere FROM ENCHERES");
			
			
			while (rs.next()) {
				listEncheres.add(new Enchere(rs.getTimestamp(3).toLocalDateTime(), rs.getInt(4),uDao.select_user_by_no(rs.getInt(1)),aDao.selectById(rs.getInt(2))));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listEncheres;
	}
	
}
