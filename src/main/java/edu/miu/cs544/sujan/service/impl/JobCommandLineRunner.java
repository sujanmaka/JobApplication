package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.*;
import edu.miu.cs544.sujan.enums.Location;
import edu.miu.cs544.sujan.repository.ApplicationRepository;
import edu.miu.cs544.sujan.repository.CompanyRepository;
import edu.miu.cs544.sujan.repository.InterviewRepository;
import edu.miu.cs544.sujan.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Transactional
public class JobCommandLineRunner implements CommandLineRunner {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public void run(String... args) throws Exception {
        setData();
    }

    private void setData() {
        ScreeningInterview screeningInterview = new ScreeningInterview(LocalDate.now(), "6418191456", "smaka@miu.edu", "sujan", "passed");
        interviewRepository.save(screeningInterview);

        TechnicalInterview technicalInterview = new TechnicalInterview(LocalDate.now(), "6517892354", "jenny@miu.edu", 20, Location.IN_PERSON, Arrays.asList(new Question("What is your weakness?"), new Question("What is your strength?")));
        interviewRepository.save(technicalInterview);

        HiringManagerInterview hiringManagerInterview = new HiringManagerInterview(LocalDate.now(), "2341222334", "srk@miu.edu", 10, LocalDate.now());
        interviewRepository.save(hiringManagerInterview);


        Job job1 = new Job("Developer", 200000);
        Job job2 = new Job("Manager", 200000);
        job1.setInterviews(Arrays.asList(technicalInterview, hiringManagerInterview));
        job2.setInterviews(Arrays.asList(screeningInterview));
        Skill skill1 = new Skill("Coding", "4 years", "Backend expertise", "Java");
        Skill skill2 = new Skill("Management", "10 years", "Managing skill", "English");
        Skill skill3 = new Skill("Designing", "5 years", "Designing skill", "CSS/HTML");
        Skill skill4 = new Skill("Quality Assurance", "2 years", "QC skill", "English");
        job1.setSkills(Arrays.asList(skill1, skill3));
        job2.setSkills(Arrays.asList(skill2, skill4));

        Company company = new Company("Google Technology LLC", new Address("1000 N 4th street", "Fairfield", "52557", "IA"));

        Client client1 = new Client("Renegrade Insurance LLC", new Address("57 kilvert", "Rhode Island", "34211", "MA"), "To revolutionize insurance", "For no reason", "www.rengadeinsurance.com");
        companyRepository.save(client1);

        Client client2 = new Client("Facebook LLC", new Address("123 New Road", "Palo Alto", "32900", "CA"), "Social network", "Marketing and Advertisement", "www.fb.com");
        companyRepository.save(client2);

        Recruiter recruiter = new Recruiter("Alpha Beta Recruiter", new Address("123 New Street", "New York", "23000", "NY"));
        recruiter.setClients(Arrays.asList(client1, client2));
        companyRepository.save(recruiter);

        job1.setCompany(company);
        Application application1 = new Application(LocalDate.now(), "2.0", job1);
//        Application application2 = new Application(LocalDate.now(), "3.0", job2);
        applicationRepository.save(application1);
//        applicationRepository.save(application2);

    }
}
