package weatherviewer.exceptions;

public class ProviderNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
		public ProviderNotFoundException(String message){
			super(message);
		}
}
