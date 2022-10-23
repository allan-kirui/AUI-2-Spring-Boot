package pl.edu.pg.eti.kask.rpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.character.service.ProfessorService;
import pl.edu.pg.eti.kask.rpg.character.service.SubjectService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Component for interaction with university using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private ProfessorService professorService;
    private SubjectService subjectService;

    @Autowired
    public CommandLine(ProfessorService professorService, SubjectService subjectService) {
        this.professorService = professorService;
        this.subjectService = subjectService;
    }

    public void test() {
        System.out.println(subjectService.findAll());
    }

    @Override
    public void run(String... args) throws Exception {
        professorService.findAll().forEach(System.out::println);
        int choice = 0;
        while (choice != 6) {
            System.out.println("Press 1 to Display all Subjects:");
            System.out.println("Press 2 to Display all Professors:");
            System.out.println("Press 3 to Add professor to a Subject:");
            System.out.println("Press 4 to Delete an Professor:");
            System.out.println("Press 5 to Delete a Subject:");
            System.out.println("Press 6 to exit:");

            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextInt();  // Read university input

            // DISPLAY ALL SUBJECTS
            if (choice == 1) {
                System.out.println(subjectService.findAll());
            }
            // DISPLAY ALL PROFESSORS
            else if (choice == 2) {
                professorService.findAll().forEach(System.out::println);
            }
            // ADD professor TO A subject
            else if (choice == 3) {
                List<Subject> allSubjects = subjectService.findAll();
                int counter = 0;
                for (Subject subject : allSubjects) {
                    System.out.println(counter + " -- " + subject.getName());
                    counter++;
                }

                System.out.println("Pick subject number: ");
                int chosensubject = myObj.nextInt();
                myObj.nextLine();
                System.out.println("Give Professor name: ");
                String professorName = myObj.nextLine();
                System.out.println("Give Professor age: ");
                int professorAge = myObj.nextInt();
                myObj.nextLine();
                System.out.println("Give Professor education: ");
                String professorEducation = myObj.nextLine();
                System.out.println("Give Professor experience: ");
                int professorExperience = myObj.nextInt();
                myObj.nextLine();
                Professor newProfessor = Professor.builder()
                        .name(professorName)
                        .age(professorAge)
                        .education(professorEducation)
                        .experience(professorExperience)
                        .subject(allSubjects.get(chosensubject))
                        .build();

                professorService.create(newProfessor);
            } // DELETING A SPECIFIC professor
            else if (choice == 4) {
                List<Professor> allProfessors = professorService.findAll();
                for (Professor professor : allProfessors) {
                    System.out.println(professor.getId() + " -- " + professor.getName());
                }
                int chosenOne = myObj.nextInt();
                professorService.delete(chosenOne);

            } // DELETING A SPECIFIC subject
            else if (choice == 5) {
                List<Subject> allSubjects = subjectService.findAll();
                int counter = 0;
                for (Subject subject : allSubjects) {
                    System.out.println(counter + " -- " + subject.getName());
                    counter++;
                }
                System.out.println("Pick subject number to delete: ");
                int chosenSubject = myObj.nextInt();
                myObj.nextLine();
                subjectService.delete(allSubjects.get(chosenSubject).getName());
                List<Professor> allProfessors = professorService.findAll();
                for (Professor professor : allProfessors) {
                    if (Objects.equals(professor.getSubject().getName(), allSubjects.get(chosenSubject).getName())) {
                        professorService.delete(Math.toIntExact(professor.getId()));
                    }
                }
            }

        }
    }

}
