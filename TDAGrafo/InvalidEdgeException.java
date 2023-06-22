package TDAGrafo;

public class InvalidEdgeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Lanza el mensaje de error correspondiente
	 * @param msg mensaje a mostrar
	 */
	public InvalidEdgeException(String msg) {
		super(msg);
	}
}
