//package edu.miu.cs544.sujan.jobboot.component;
//
//import edu.miu.cs544.sujan.jobboot.entity.Job;
//import edu.miu.cs544.sujan.jobboot.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Receiver {
//
//    @Autowired
//    private JobRepository jobRepository;
//
////    @JmsListener(destination = "${jms.brokerQueue}")
////    public void receive1(String message) {
////        System.out.println("Received 1 : " + message);
////    }
////
////    @JmsListener(destination = "${jms.brokerQueue}")
////    public void receive2(String message) {
////        System.out.println("Received 2 : " + message);
////    }
//
//    @JmsListener(destination = "${jms.brokerQueue}")
//    public void createJob(Job job) {
//        jobRepository.save(job);
//    }
//}
