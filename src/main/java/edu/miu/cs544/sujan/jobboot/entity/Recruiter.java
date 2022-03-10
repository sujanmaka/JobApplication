package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Recruiter extends Company {
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Client.class)
    private List<Client> clients;
    public Recruiter() {
    }

    public Recruiter(String name, Address address) {
        super(name, address);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "clients=" + clients +
                '}';
    }
}