package Connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class connectionFactory {

	public DataSource dataSource;

	public connectionFactory() {

		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/registros?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("admin01");
		comboPooledDataSource.setPassword("wallace05219020");
		
		comboPooledDataSource.setMaxPoolSize(3);
		comboPooledDataSource.setMinPoolSize(1);

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperaConexao() throws SQLException {
		return this.dataSource.getConnection();
	}
}
