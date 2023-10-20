package com.crud.konsumen.test.salt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.konsumen.test.salt.model.Konsumen;
import com.crud.konsumen.test.salt.repository.KonsumenRepository;

@RestController
@RequestMapping("/api")
public class KonsumenController {
	@Autowired
	KonsumenRepository konsumenRepository;

	@GetMapping("/konsumen")
	public ResponseEntity<Map<String, Object>> getAllKonsumen(@RequestParam(required=false) String keyword) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Konsumen> konsumen = new ArrayList<Konsumen>();
			konsumen = konsumenRepository.findAll(keyword);
			if (konsumen.isEmpty()) {
				response.put("status", ResponseEntity.notFound().build().getStatusCodeValue());
				response.put("message", "Data tidak ditemukan");

				return ResponseEntity.status(404).body(response);
			}

			response.put("status", ResponseEntity.ok().build().getStatusCodeValue());
			response.put("message", "Sukses mengambil data");
			response.put("data", konsumen);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			System.out.println(e);
			response.put("status", ResponseEntity.internalServerError().build().getStatusCodeValue());
			response.put("message", "Gagal mengambil data");
//			response.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/konsumen/{id}")
	public ResponseEntity<Map<String, Object>> getTutorialById(@PathVariable("id") int id) {
		Map<String, Object> response = new HashMap<>();
		Konsumen konsumen = konsumenRepository.findById(id);

		if (konsumen != null) {
			response.put("status", ResponseEntity.ok().build().getStatusCodeValue());
			response.put("message", "Sukses mengambil data");
			response.put("data", konsumen);

			return ResponseEntity.ok().body(response);
		} else {
			response.put("status", ResponseEntity.notFound().build().getStatusCodeValue());
			response.put("message", "Data tidak ditemukan");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PostMapping("/konsumen")
	public ResponseEntity<Map<String, Object>> createKonsumen(@RequestBody Konsumen konsumen) {
		Map<String, Object> response = new HashMap<>();
		try {
			konsumenRepository.save(new Konsumen(konsumen.getId(), konsumen.getNama(), konsumen.getAlamat(),
					konsumen.getKota(), konsumen.getProvinsi(), konsumen.getTgl_registrasi(), konsumen.getStatus()));
			response.put("status", HttpStatus.CREATED.value());
			response.put("message", "Sukses menambahkan data");

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			System.out.println(e);
			response.put("status", ResponseEntity.internalServerError().build().getStatusCodeValue());
			response.put("message", "Gagal menambahkan data");
//			response.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@PutMapping("/konsumen/{id}")
	public ResponseEntity<Map<String, Object>> updateKonsumen(@PathVariable("id") int id,
			@RequestBody Konsumen konsumen) {
		Map<String, Object> response = new HashMap<>();
		Konsumen _konsumen = konsumenRepository.findById(id);

		if (_konsumen != null) {
			_konsumen.setId(id);
			_konsumen.setNama(konsumen.getNama());
			_konsumen.setAlamat(konsumen.getAlamat());
			_konsumen.setKota(konsumen.getKota());
			_konsumen.setProvinsi(konsumen.getProvinsi());
			_konsumen.setTgl_registrasi(konsumen.getTgl_registrasi());
			_konsumen.setStatus(konsumen.getStatus());

			konsumenRepository.update(_konsumen);
			response.put("status", HttpStatus.CREATED.value());
			response.put("message", "Sukses menambahkan data");

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			response.put("status", ResponseEntity.notFound().build().getStatusCodeValue());
			response.put("message", "Data tidak ditemukan dengan id:" + id);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("/konsumen/{id}")
	public ResponseEntity<Map<String, Object>> deleteKonsumen(@PathVariable("id") int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			int result = konsumenRepository.deleteById(id);
			if (result == 0) {
				response.put("status", ResponseEntity.notFound().build().getStatusCodeValue());
				response.put("message", "Data tidak ditemukan dengan id:" + id);

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			response.put("status", ResponseEntity.ok().build().getStatusCodeValue());
			response.put("message", "Sukses menghapus data");

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			System.out.println(e);
			response.put("status", ResponseEntity.internalServerError().build().getStatusCodeValue());
			response.put("message", "Gagal menambahkan data");
//			response.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

}
