package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            if(teacherStudentMapping.containsKey(teacher)){
                List<String> list=teacherStudentMapping.get(teacher);
                list.add(student);
                teacherStudentMapping.put(teacher,list);
            }
            else {
                List<String> list= new ArrayList<>();
                list.add(student);
                teacherStudentMapping.put(teacher,list);
            }

        }
    }

    public Student findStudent(String student){
        // your code goes here
        Student student1 = null;
        for (String str : studentMap.keySet()) {

            if(str.equals(student)){
                student1=studentMap.get(str);
            }
        }

        return student1;

    }

    public Teacher findTeacher(String teacher){
        // your code goes here

        Teacher teacher1=null;
        for (String str: teacherMap.keySet()){
            if (str.equals(teacher)){
                teacher1=teacherMap.get(str);
            }

        }

        return teacher1;
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher

        List<String> list=teacherStudentMapping.get(teacher) ;

        return list;

    }

    public List<String> findAllStudents(){
        // your code goes here
        List<String> list=new ArrayList<>();

        for (String s:studentMap.keySet()){
            list.add(s);
        }
        return list;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        List<String>  list= findStudentsFromTeacher(teacher);


        for (String s: list){
            if(studentMap.containsKey(s)){
                studentMap.remove(s);
            }
        }
        teacherMap.remove(teacher);
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers(){
        // your code goes here
        Set<String> teacherList= teacherMap.keySet();
        for (String teacher:teacherList){
            deleteTeacher(teacher);
        }
    }
}