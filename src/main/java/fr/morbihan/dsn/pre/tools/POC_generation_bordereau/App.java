package fr.morbihan.dsn.pre.tools.POC_generation_bordereau;

/**
 * Hello world!
 *
 */

/**
 * @author Patrick PERCOT chef de projet département du Morbihan
 *
 */
public class App {
    public static void main( String[] args )    {
        System.out.println( "Hello World!" );
		// Construction d'un SIP pour les délibérations
		try {
			Deliberations.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
