package com.jeremietic.gestion_add.service;

import com.jeremietic.gestion_add.exception.StudentAlreadyExistsException;
import com.jeremietic.gestion_add.exception.StudentNotFoundException;
import com.jeremietic.gestion_add.model.Student;
import com.jeremietic.gestion_add.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {


     private final StudentRepository studentRepository;


    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student addStudent(Student student) {

        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+ " Existe deja");
        }
        return studentRepository.save(student);
    }




    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st ->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() ->new StudentNotFoundException("Desolé, cet Etudiant n'existe pas "));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Desolé, Etudiant non trouvé: "));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Désolé, etudiant non trouvé");
        }
        studentRepository.deleteById(id);
    }



    private boolean studentAlreadyExists(String email) {

        return studentRepository.findByEmail(email).isPresent();
    }

}
