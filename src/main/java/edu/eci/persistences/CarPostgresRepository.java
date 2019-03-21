package edu.eci.persistences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUCarRepository;

@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements IUCarRepository {

	private String dbUrl = null;

	@Autowired
	private DataSource dataSource2;

	@Override
	public List<Car> findAll() {
		String query = "SELECT * FROM cars;";
		List<Car> cars = new ArrayList<>();

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			connection.close();
			while (rs.next()) {
				Car car = new Car();
				car.setLicensePlate(rs.getString("licensePlate"));
				car.setBrand(rs.getString("brand"));
				cars.add(car);
			}
			return cars;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public Car find(String id) {
		String query = "SELECT * WHERE licensePlate = " + id + " FROM cars;";
		Car car = new Car();

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			connection.close();
			car.setLicensePlate(rs.getString("licensePlate"));
			car.setBrand(rs.getString("brand"));
			return car;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public String save(Car entity) {
		String query = "INSERT INTO cars(licensePlate, brand) VALUES(" + entity.getLicensePlate() + ", "
				+ entity.getBrand() + ");";

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query);
			connection.close();
			return entity.getLicensePlate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Car entity) {
		
		String query = "UPDATE cars SET brand = " + entity.getBrand() + " WHERE licensePlate = " + entity.getLicensePlate() + ";";

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(Car o) {
		String query = "DELETE FROM cars WHERE licensePlate = " + o.getLicensePlate() + ";";

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

	}

	@Override
	public void remove(String id) {
		String query = "DELETE FROM cars WHERE licensePlate = " + id + ";";

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

	}

	@Override
	public Car getCarByLicense(String licensePlate) {
		String query = "SELECT * WHERE	name = " + licensePlate + " FROM cars;";
		Car car = new Car();

		try (Connection connection = dataSource2.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			connection.close();
			car.setLicensePlate(rs.getString("licensePlate"));
			car.setBrand(rs.getString("brand"));
			return car;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Bean
	public DataSource dataSource2() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}

}
