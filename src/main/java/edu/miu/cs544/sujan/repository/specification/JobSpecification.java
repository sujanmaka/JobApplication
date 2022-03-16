package edu.miu.cs544.sujan.repository.specification;

import edu.miu.cs544.sujan.entity.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JobSpecification {

    public static Specification<Job> jobsWithCertainSalaryAndCompanyInCertainState(double salary, String state) {
        return (root, query, criteriaBuilder) -> {
            Predicate salaryPredicate = criteriaBuilder.greaterThan(root.get("salary"), salary);
            Predicate statePredicate = criteriaBuilder.equal(root.get("company").get("address").get("state"), state);
            return criteriaBuilder.and(salaryPredicate, statePredicate);
        };
    }
}
