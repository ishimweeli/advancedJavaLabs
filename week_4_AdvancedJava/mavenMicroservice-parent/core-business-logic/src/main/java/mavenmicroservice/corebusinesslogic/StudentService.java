package mavenmicroservice.corebusinesslogic;

//import com.example.corebusinesslogic.model.Student;
//import com.example.dataaccesslayer.StudentRepository;
import amalitech.model.Student;
import amalitech.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String registerStudent(Student student) {
        String studentNumber = UUID.randomUUID().toString();
        student.setStudentNumber(studentNumber);
        studentRepository.save(student);
        return studentNumber;
    }

    public Student getStudentDetails(String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
