package com.crud.konsumen.test.salt.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crud.konsumen.test.salt.model.Konsumen;

@Repository
public class JdbcKonsumenRepository implements KonsumenRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Konsumen> findAll(String keyword) {

		String sql = "SELECT * FROM konsumen where id::TEXT ilike '%" + keyword + "%' or nama ilike '%" + keyword
				+ "%' or alamat ilike '%" + keyword + "%' or kota ilike '%" + keyword + "%' or provinsi ilike '%"
				+ keyword + "%' " + "or tgl_registrasi::TEXT ilike '%" + keyword + "%' or status ilike '%" + keyword
				+ "%'";

		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Konsumen.class));
	}

	@Override
	public int save(Konsumen konsumen) {
		// Set time to 00:00
		Calendar tgl_registrasi = Calendar.getInstance();
		tgl_registrasi.setTime(konsumen.getTgl_registrasi());
		tgl_registrasi.set(Calendar.HOUR, 0);
		tgl_registrasi.set(Calendar.MINUTE, 0);
		tgl_registrasi.set(Calendar.SECOND, 0);
		tgl_registrasi.set(Calendar.HOUR_OF_DAY, 0);
		return jdbcTemplate.update(
				"INSERT INTO konsumen (nama, alamat, kota, provinsi, tgl_registrasi, status) VALUES(?,?,?,?,?,?)",
				new Object[] { konsumen.getNama(), konsumen.getAlamat(), konsumen.getKota(), konsumen.getProvinsi(),
						tgl_registrasi, konsumen.getStatus() });
	}

	@Override
	public Konsumen findById(int id) {
		try {
			Konsumen konsumen = jdbcTemplate.queryForObject("SELECT * FROM konsumen WHERE id=?",
					BeanPropertyRowMapper.newInstance(Konsumen.class), id);

			return konsumen;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int update(Konsumen konsumen) {
//		  Calendar tgl_registrasi = Calendar.getInstance();
//		  if(konsumen.getTgl_registrasi().toString().contains("T")) {
//			  System.out.println("aaaa");
//			  tgl_registrasi.setTime(konsumen.getTgl_registrasi());
//			  tgl_registrasi.set(Calendar.HOUR, 0);
//			  tgl_registrasi.set(Calendar.MINUTE, 0);
//			  tgl_registrasi.set(Calendar.SECOND, 0);
//			  tgl_registrasi.set(Calendar.MILLISECOND, 0); 
//		      tgl_registrasi.set(Calendar.HOUR_OF_DAY, 0); 
//		  } else { 
//			  System.out.println("bbb");
//			  tgl_registrasi.setTime(konsumen.getTgl_registrasi());
//			  System.out.println(tgl_registrasi);
//		  }
		return jdbcTemplate.update(
				"UPDATE konsumen SET nama=?, alamat=?, kota=?, provinsi=?, tgl_registrasi=?, status=? WHERE id=?",
				new Object[] { konsumen.getNama(), konsumen.getAlamat(), konsumen.getKota(), konsumen.getProvinsi(),
						konsumen.getTgl_registrasi(), konsumen.getStatus(), konsumen.getId() });
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM konsumen WHERE id=?", id);
	}

}
