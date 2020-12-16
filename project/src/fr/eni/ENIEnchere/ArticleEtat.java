package fr.eni.ENIEnchere;

import java.time.format.DateTimeFormatter;

public class ArticleEtat {

	public static final String CR = "CR";
	public static final String EC = "EC";
	public static final String VD = "VD";
	public static final String RT = "RT";
	public static final String NonOuver = "NO";
	public static final String Ouvert = "OV";
	public static final String ALL = "";
	
	public static final DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
}
