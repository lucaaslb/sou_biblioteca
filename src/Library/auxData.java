/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package Library;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Classe com metodos de calculo de data*/
public class auxData {

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public String getDateTime() {
		Date date = new Date();
		return df.format(date);
	}

	public int getDaysRunning(String hoje) throws ParseException {
		df.setLenient(false);
		Date d1 = df.parse(hoje);
	//	System.out.println(d1);
		Date d2 = df.parse(getDateTime());
	//	System.out.println(d2);
		int dt = (int) ((d2.getTime() - d1.getTime()) + 3600000); // 1 hora para
															// compensar horário
															// de verão
		//System.out.println(dt / 86400000L);

		dt = (int) (dt / 86400000L);
		
		return dt;
	}

	
	
	public int days(int dt){		
		return 7-dt; 		
	}
	 public int daysMulta(int x){
		 return (x + (-2*x)); 
	 }
	 /* calculo de 2 reais por dia de atraso */
	 public String multa(int x){
		 DecimalFormat df = new DecimalFormat("R$ 0.00");
		 x = daysMulta(x);
		 double multa = x * 2.00;
		 return  df.format(multa);
	 }
}
