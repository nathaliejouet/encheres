package fr.eni.ENIEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Categorie;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.CodesResultatDAL;
import fr.eni.ENIEnchere.dal.ConnectionProvider;
import fr.eni.ENIEnchere.dal.UtilisateurDao;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {

	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_PSEUDO = "SELECT pseudo FROM UTILISATEURS";
	private static final String SELECT_EMAIL = "SELECT email FROM UTILISATEURS";
	private static final String SELECT_BY_LOGIN = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_USER = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE no_utilisateur=?;";

	@Override
	public void add(Utilisateur utilisateur) throws BusinessException {

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstt2 = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

			pstt2.setString(1, utilisateur.getPseudo());
			pstt2.setString(2, utilisateur.getNom());
			pstt2.setString(3, utilisateur.getPrenom());
			pstt2.setString(4, utilisateur.getEmail());
			pstt2.setString(5, utilisateur.getTelephone());
			pstt2.setString(6, utilisateur.getRue());
			pstt2.setString(7, utilisateur.getCode_postal());
			pstt2.setString(8, utilisateur.getVille());
			pstt2.setString(9, utilisateur.getMot_de_passe());
			pstt2.setInt(10, utilisateur.getCredit());
			pstt2.setBoolean(11, utilisateur.isAdministrateur());

			pstt2.executeUpdate();

			ResultSet rs = pstt2.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}
			rs.close();

			pstt2.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}

	@Override
	public List<String> selectPseudo() {

		List<String> listePseudo = new ArrayList<String>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(SELECT_PSEUDO);

			while (rs.next()) {
				String pseudo = rs.getString(1);
				listePseudo.add(pseudo);
			}

			rs.close();
			stt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listePseudo;
	}

	@Override
	public List<String> selectEmail() {

		List<String> listeEmail = new ArrayList<String>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(SELECT_EMAIL);

			while (rs.next()) {
				String email = rs.getString(1);
				listeEmail.add(email);
			}

			rs.close();
			stt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeEmail;
	}

	@Override
	public Utilisateur selectByLogin(String login, String password) throws BusinessException {

		Utilisateur u = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN)) {
			pstmt.setString(1, login);
			pstmt.setString(2, login);
			pstmt.setString(3, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getBoolean(12));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Utilisateur utilisateur) throws BusinessException {

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstt2 = conn.prepareStatement(UPDATE_USER);

			pstt2.setString(1, utilisateur.getPseudo());
			pstt2.setString(2, utilisateur.getNom());
			pstt2.setString(3, utilisateur.getPrenom());
			pstt2.setString(4, utilisateur.getEmail());
			pstt2.setString(5, utilisateur.getTelephone());
			pstt2.setString(6, utilisateur.getRue());
			pstt2.setString(7, utilisateur.getCode_postal());
			pstt2.setString(8, utilisateur.getVille());
			pstt2.setString(9, utilisateur.getMot_de_passe());
			pstt2.setInt(10, utilisateur.getCredit());
			pstt2.setInt(11, utilisateur.getNo_utilisateur());
			pstt2.executeUpdate();

			pstt2.close();
			conn.commit();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {

		Utilisateur u = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO)) {
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Connexion réussie ..");
				u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getBoolean(12));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public Utilisateur select_user_by_no(int no_utilisateur) throws BusinessException {
		Utilisateur u = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER)) {
			pstmt.setInt(1, no_utilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getBoolean(12));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Utilisateur selectByArticle(Article a) throws BusinessException {
		Utilisateur u = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstt = cnx.prepareStatement(
					"SELECT UTILISATEURS.* FROM UTILISATEURS "
					+ "INNER JOIN ENCHERES "
					+ "ON UTILISATEURS.no_utilisateur = ENCHERES.no_utilisateur "
					+ "WHERE ENCHERES.no_article = ?");
			pstt.setInt(1, a.getNo_article());
			
			ResultSet rs = pstt.executeQuery();
			
			
			while (rs.next()) {
				u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getBoolean(12));
			}
			cnx.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return u;
	}
	
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> listeUser = new ArrayList<Utilisateur>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stt = cnx.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM Utilisateurs");
			
			while (rs.next()) {
				listeUser.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),null,
						rs.getInt(11), rs.getBoolean(12)));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeUser;
	}
	
}
