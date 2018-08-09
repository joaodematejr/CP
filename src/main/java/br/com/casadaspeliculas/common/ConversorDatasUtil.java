package br.com.casadaspeliculas.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversorDatasUtil {

	/**
	 * Método que retorna uma data incial para uma consulta HQL, usando na
	 * clásula between para obter os resultados corretos por conta dos campos do
	 * tipo datetime.
	 * 
	 * @param data
	 * @return retorna a dataInicial preenchiada com horário default para
	 *         consulta
	 */
	public static Date getDataInicial(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * Método que retorna uma data final para uma consulta HQL, usando na
	 * clásula between para obter os resultados corretos por conta dos campos do
	 * tipo datetime.
	 * 
	 * @param data
	 * @return retorna a dataFinal preenchiada com horário default para consulta
	 */
	public static Date getDataFinal(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static String formataData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy (HH:mm:ss)");
		return sdf.format(data);
	}

	public static String formataDataSemHora(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}

	/**
	 * Converte uma String para um objeto Date. Caso a String seja vazia ou
	 * nula, retorna null - para facilitar em casos onde formulários podem ter
	 * campos de datas vazios.
	 * 
	 * @param data
	 *            String no formato dd/MM/yyyy a ser formatada
	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
	 * @throws Exception
	 *             Caso a String esteja no formato errado
	 */
	public static Date formataData(String data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

}
