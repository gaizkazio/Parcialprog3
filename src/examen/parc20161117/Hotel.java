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
	 * @return	conjunto de nombres de habitaciones ordenadas alfab�ticamente
	 */
	public TreeSet<String> getHabitaciones() {
		return habitaciones;
	}

	/** Devuelve el listado de las reservas de una habitaci�n
	 * @param nombreHabitacion	Habitaci�n a consultar
	 * @return	Reservas de esa habitaci�n, null si la habitaci�n no existe en el hotel
	 */
	public ArrayList<RangoFechas> getReservas( String nombreHabitacion ) {
		return reservas.get( nombreHabitacion );
	}
	
	/** A�ade la habitaci�n al hotel
	 * @param nombreHabitacion	Nombre de la habitaci�n
	 * @return	true si la habitaci�n es nueva. false si ya exist�a (y en ese caso no cambia nada)
	 */
	public boolean addHabitacion( String nombreHabitacion ) {
		boolean esNueva = habitaciones.add( nombreHabitacion );
		if (esNueva) reservas.put( nombreHabitacion, new ArrayList<RangoFechas>() );
		return esNueva;
	}

	/** A�ade una reserva 
	 * @param nombreHabitacion	Nombre de la habitaci�n, debe existir
	 * @param reserva
	 * @return	true si la reserva se ha a�adido correctamente
	 */
	public boolean addReserva( String nombreHabitacion, RangoFechas reserva ) {
		ArrayList<RangoFechas> lReservas = reservas.get( nombreHabitacion );
		if (lReservas==null) return false;  // Habitaci�n incorrecta
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

