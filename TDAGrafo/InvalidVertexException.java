package TDAGrafo;

public class InvalidVertexException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Lanza el mensaje de error correspondiente
	 * @param msg mensaje a mostrar
	 */
	public InvalidVertexException(String msg) {
		super(msg);
	}
}
