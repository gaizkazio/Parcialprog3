package examen.parc20161117;

import java.util.*;

public class Hotel {
	private String nombre;
	private TreeSet<String> habitaciones;
	private HashMap<String,ArrayList<RangoFechas>> reservas;

	/** Crea un hotel nuevo
	 * @param nombre	Nombre del hotel
	 */
	public Hotel( String nombre ) {
		this.nombre = nombre;
		habitaciones = new TreeSet<>();
		reservas = new HashMap<>();
	}
	
	/** Devuelve el nombre del hotel
	 * @return	Nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/** Cambia el nombre del hotel
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** Devuelve los nombres de las habitaciones
	 * @return	conjunto de nombres de habitaciones ordenadas alfabéticamente
	 */
	public TreeSet<String> getHabitaciones() {
		return habitaciones;
	}

	/** Devuelve el listado de las reservas de una habitación
	 * @param nombreHabitacion	Habitación a consultar
	 * @return	Reservas de esa habitación, null si la habitación no existe en el hotel
	 */
	public ArrayList<RangoFechas> getReservas( String nombreHabitacion ) {
		return reservas.get( nombreHabitacion );
	}
	
	/** Añade la habitación al hotel
	 * @param nombreHabitacion	Nombre de la habitación
	 * @return	true si la habitación es nueva. false si ya existía (y en ese caso no cambia nada)
	 */
	public boolean addHabitacion( String nombreHabitacion ) {
		boolean esNueva = habitaciones.add( nombreHabitacion );
		if (esNueva) reservas.put( nombreHabitacion, new ArrayList<RangoFechas>() );
		return esNueva;
	}

	/** Añade una reserva 
	 * @param nombreHabitacion	Nombre de la habitación, debe existir
	 * @param reserva
	 * @return	true si la reserva se ha añadido correctamente
	 */
	public boolean addReserva( String nombreHabitacion, RangoFechas reserva ) {
		ArrayList<RangoFechas> lReservas = reservas.get( nombreHabitacion );
		if (lReservas==null) return false;  // Habitación incorrecta
		lReservas.add( reserva );
		return true;
	}

	@Override
	public String toString() {
		String ret = nombre;
		for (String hab : habitaciones) {
			ret += ("\n" + hab + "\t" + reservas.get( hab ));
		}
		return ret;
	}
}

