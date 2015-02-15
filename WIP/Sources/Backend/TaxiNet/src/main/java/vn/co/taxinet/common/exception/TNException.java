package vn.co.taxinet.common.exception;

public class TNException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * @author hieu.daotrung
	 */
	private String message;

	public TNException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
