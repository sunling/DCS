package mc.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.dao.CertificateDAO;
import mc.dao.DoctorDAO;
import mc.dao.UserDAO;
import mc.model.Certificate;
import mc.model.Doctor;
import mc.model.User;

@Service
@Transactional
public class DoctorService {

	private final UserDAO userDAO;
	private final DoctorDAO doctorDAO;
	private final CertificateDAO certificateDAO;
	@Autowired
	DoctorDAO ddao;
	public DoctorService(UserDAO userDAO,DoctorDAO doctorDAO,CertificateDAO certificateDAO) {
		this.userDAO = userDAO;
		this.doctorDAO = doctorDAO;
		this.certificateDAO = certificateDAO;
	}
	
	public void regDoctor(User user, Doctor doctor,List<Certificate> certificates){
		user = userDAO.save(user);
		doctor.setUser(user);
		doctorDAO.save(doctor);
		for (Certificate certificate : certificates) {
			certificate.setDoctor(doctor);
			certificateDAO.save(certificate);
		}
	}

	public Doctor findDoctorByUser(User user) {
		return ddao.findByUser(user);
	}
	

	
	public List<Doctor> findAll(){
		return ddao.findAll();
	}
	
	public Doctor findById(int id) {
		return ddao.findById(id);
	}

}
