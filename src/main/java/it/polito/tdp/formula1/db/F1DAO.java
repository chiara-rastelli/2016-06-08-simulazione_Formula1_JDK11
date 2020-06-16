package it.polito.tdp.formula1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Driver;
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Season;


public class F1DAO {

	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Season> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Integer> getAllYears() {
		
		String sql = "	SELECT YEAR FROM seasons ORDER BY YEAR ASC" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Integer> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(rs.getInt("year"));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Circuit> getAllCircuitsByYear(Integer year) {
		
		String sql = 	"SELECT distinct c.circuitId, c.circuitRef, c.name, c.location, c.country, c.lat, c.lng, c.alt, c.url " + 
						"FROM seasons s, races r, circuits c " + 
						"WHERE s.year = r.year " + 
						"AND s.year = ? " + 
						"AND r.circuitId = c.circuitId" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, year);
			ResultSet rs = st.executeQuery() ;
			
			List<Circuit> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Circuit(rs.getInt("circuitId"), rs.getString("circuitRef"), rs.getString("name"), rs.getString("location"),
						rs.getString("country"), rs.getDouble("lat"), rs.getDouble("lng"), rs.getInt("alt"), rs.getString("url")));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	

	
	public List<Driver> getAllDriversByRace(Integer year, Circuit c) {
		
		String sql = 	"	SELECT d.driverId, driverRef, number, CODE, forename, surname, dob, nationality, d.url " + 
						"	FROM races r, drivers d, driverstandings ds " + 
						"	WHERE r.year = ? " + 
						"	AND r.circuitId = ? " + 
						"	AND ds.raceId = r.raceId " + 
						"	AND d.driverId = ds.driverId " + 
						"	GROUP BY r.raceId, d.driverId";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, year);
			st.setInt(2, c.getCircuitId());
			ResultSet rs = st.executeQuery() ;
			
			List<Driver> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Driver(rs.getInt("driverId"), rs.getString("driverRef"), rs.getInt("number"), rs.getString("code"), rs.getString("forename"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate(), rs.getString("nationality"), rs.getString("url")));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	
}
