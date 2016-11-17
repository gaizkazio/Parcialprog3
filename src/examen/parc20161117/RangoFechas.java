package examen.parc20161117;

import java.util.Date;
import java.util.GregorianCalendar;

public class RangoFechas {
	private int fechaInicio;
	private int fechaFin;
	
	/** Construye un nuevo rango de fechas
	 * @param fechaInicio	Fecha de inicio del rango, en un entero con formato aaaammdd
	 * @param fechaFin		Fecha de fin del rango, en un entero con formato aaaammdd. Debe ser igual o posterior a la de inicio
	 * @throws NullPointerException	Si hay un error en el rango de fechas
	 */
	public RangoFechas(int fechaInicio, int fechaFin) throws NullPointerException {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		if (fechaInicio>fechaFin) throw new NullPointerException( "Error en creación de RangoFechas incorrecto: [" + fechaInicio + "," + fechaFin + "]" );
	}

	/** Construye un nuevo rango de fechas
	 * @param fecInicio	Fecha de inicio del rango
	 * @param fecFin		Fecha de fin del rango. Debe ser igual o posterior a la de inicio
	 * @throws NullPointerException	Si hay un error en el rango de fechas
	 */
	public RangoFechas( Date fecInicio, Date fecFin ) throws NullPointerException {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( fecInicio );
		this.fechaInicio = gc.get(GregorianCalendar.YEAR) * 10000 + (gc.get(GregorianCalendar.MONTH)) * 100 + gc.get(GregorianCalendar.DAY_OF_MONTH);
		gc.setTime( fecFin );
		this.fechaFin = gc.get(GregorianCalendar.YEAR) * 10000 + (gc.get(GregorianCalendar.MONTH)) * 100 + gc.get(GregorianCalendar.DAY_OF_MONTH);
		if (fechaInicio>fechaFin) throw new NullPointerException( "Error en creación de RangoFechas incorrecto: [" + fechaInicio + "," + fechaFin + "]" );
	}

	/** Devuelve la fecha de inicio
	 * @return	En un entero con formato aaaammdd
	 */
	public int getFechaInicio() {
		return fechaInicio;
	}

	/** Cambia la fecha de inicio
	 * @param fechaInicio	En un entero con formato aaaammdd
	 * @throws NullPointerException	Si hay un error en el rango de fechas
	 */
	public void setFechaInicio(int fechaInicio) throws NullPointerException {
		this.fechaInicio = fechaInicio;
		if (fechaInicio>fechaFin) throw new NullPointerException( "Error en creación de RangoFechas incorrecto: [" + fechaInicio + "," + fechaFin + "]" );
	}

	/** Devuelve la fecha de fin
	 * @return	En un entero con formato aaaammdd
	 */
	public int getFechaFin() {
		return fechaFin;
	}

	/** Cambia la fecha de fin
	 * @param fechaFin	En un entero con formato aaaammdd
	 */
	public void setFechaFin(int fechaFin) throws NullPointerException {
		this.fechaFin = fechaFin;
		if (fechaInicio>fechaFin) throw new NullPointerException( "Error en creación de RangoFechas incorrecto: [" + fechaInicio + "," + fechaFin + "]" );
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RangoFechas)) return false;
		RangoFechas rf = (RangoFechas) obj;
		return fechaInicio==rf.fechaInicio && fechaFin==rf.fechaFin;
	}

	@Override
	public String toString() {
		return "{" + fechaConFormato( fechaInicio ) + " - " + fechaConFormato( fechaFin ) + "}";
	}
	
	/** Comprueba si el rango de fechas incluye a otro dado
	 * @param rf	Segundo rango de fechas
	 * @return	true si this incluye a rf (fecha de inicio anterior o igual, fecha de fin posterior o igual)
	 */
	public boolean incluyeA( RangoFechas rf ) {
		return fechaInicio<=rf.fechaInicio && fechaFin>=rf.fechaFin;
	}
	
	/** Devuelve la duración en días del rango
	 * @return	0 si el día de inicio y fin es el mismo, 1 si es el siguiente y así sucesivamente
	 */
	public int duracion() {
		return fechaFin - fechaInicio;
	}
	
	/** Indica si una fecha en formato entero es correcta
	 * @param fecha	Fecha en formato entero aaaammdd  (mínimo 19700101, máximo 29991231)
	 * @return	true si la fecha es correcta, false en caso contrario
	 */
	public static boolean fechaCorrecta( int fecha ) {
		if (fecha<19700101) return false;
		if (fecha>29991231) return false;
		int mes = fecha%10000; mes = mes/100; if (mes<1 || mes>12) return false;
		int dia = fecha%100; if (dia<1 || dia>31) return false;
		if (dia>30 && (mes==4 || mes==6 || mes==9 || mes==11)) return false;
		int anyo = fecha/1000;
		boolean esBisiesto = (anyo%4==0 && ((anyo%100!=0) || anyo%400==0)); 
		if (dia>28 && mes==2 && !esBisiesto) return false;
		if (dia>29 && mes==2 && esBisiesto) return false;
		return true;
	}
	
	/** Indica si una fecha en un string con formato entero es correcta
	 * @param fecha	Fecha en un string con formato entero aaaammdd  (mínimo 19700101, máximo 29991231)
	 * @return	true si la fecha es correcta, false en caso contrario
	 */
	public static boolean fechaCorrecta( String fecha ) {
		try {
			int fecInt = Integer.parseInt( fecha );
			return fechaCorrecta( fecInt );
		} catch (Exception e) {
			return false;
		}
	}
	
	/** Devuelve la fecha en un entero partiendo de un string con formato aaaammdd
	 * @param fecha	Fecha en un string con formato entero aaaammdd  (mínimo 19700101, máximo 29991231)
	 * @return	fecha numérica (aaaammdd) si es correcta, -1 en caso contrario
	 */
	public static int fechaDesdeString( String fecha ) {
		try {
			int fecInt = Integer.parseInt( fecha );
			if (fechaCorrecta( fecInt )) return fecInt;
		} catch (Exception e) {}
		return -1;
	}
	
	/** Devuelve la fecha de hoy
	 * @return	Fecha de hoy en formato aaaammdd
	 */
	public static int fechaDeHoy() {
		GregorianCalendar gc = new GregorianCalendar();
		return gc.get(GregorianCalendar.YEAR) * 10000 + (gc.get(GregorianCalendar.MONTH)+1) * 100 + gc.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	/** Devuelve la fecha indicada en formato dd/mm/aaaa
	 * @param fecha	Fecha entera en formato aaaammdd
	 * @return	fecha string en formato dd/mm/aaaa
	 */
	public static String fechaConFormato( int fecha ) {
		return String.format( "%1$02d/%2$02d/%3$04d", fecha%100, (fecha%10000)/100, fecha/10000);
	}

}
