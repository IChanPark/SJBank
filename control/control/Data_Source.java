package control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Data_Source {
	private DataSource ds;
	
	private Data_Source() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:/comp/env/mmm");
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private static class Holder {
        public static final Data_Source Source = new Data_Source();
    }
	
	public static Data_Source getInstance() {
        return Holder.Source;
    }
	
	public DataSource getDs(){
		return ds;
	}
}
