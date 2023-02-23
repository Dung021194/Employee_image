package com.example.employeeimage.repository;

import com.example.employeeimage.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EmployeeRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;
    public List<Employee> findAll() {
        String query = "SELECT e FROM Employee AS e";
        TypedQuery<Employee> strQuery = entityManager.createQuery(query,Employee.class);
        return strQuery.getResultList();
    }

    public Employee findById(Long id) {
        String query = "SELECT e FROM Employee AS e WHERE e.id = :id";
        TypedQuery<Employee> strQuery = entityManager.createQuery(query, Employee.class);
        strQuery.setParameter("id", id);
        return strQuery.getSingleResult();
    }

    public void create(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }


    public void update(Employee employee){
        Long id = employee.getId();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employeeUpdate = findById(id);
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setAge(employee.getAge());
            if (employee.getImagePath() != null){
                employeeUpdate.setImagePath(employee.getImagePath());
            }
            session.saveOrUpdate(employeeUpdate);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteById(Long employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "delete from Employee where id = :employeeId";
            int deletedEntities = session.createQuery(hql)
                    .setLong("employeeId", employeeId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
