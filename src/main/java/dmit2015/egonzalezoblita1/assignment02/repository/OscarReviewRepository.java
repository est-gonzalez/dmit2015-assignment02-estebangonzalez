package dmit2015.egonzalezoblita1.assignment02.repository;

import dmit2015.egonzalezoblita1.assignment02.entity.OscarReview;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional

// @author: Esteban Gonzalez
// @version: February 4, 2022
public class OscarReviewRepository {

    @PersistenceContext(unitName = "hsqldatabase-jpa-pu")
    private EntityManager em;

    public void add(OscarReview newOscarReview) {
        em.persist(newOscarReview);
    }

    public void update(OscarReview updatedOscarReview) {
        Optional<OscarReview> optionalOscarReview = findById(updatedOscarReview.getId());
        if (optionalOscarReview.isPresent()) {
            OscarReview existingOscarReview = optionalOscarReview.get();
            existingOscarReview.setCategory(updatedOscarReview.getCategory());
            existingOscarReview.setNominee(updatedOscarReview.getNominee());
            existingOscarReview.setReview(updatedOscarReview.getReview());
            existingOscarReview.setUsername(updatedOscarReview.getUsername());
            em.merge(existingOscarReview);
            em.flush();
        }
    }

    public void remove(Long id) {
        Optional<OscarReview> optionalOscarReview= findById(id);
        if (optionalOscarReview.isPresent()) {
            OscarReview existingOscarReview = optionalOscarReview.get();
            em.remove(existingOscarReview);
            em.flush();
        }
    }

    public Optional<OscarReview> findById(Long id) {
        Optional<OscarReview> optionalOscarReview = Optional.empty();
        try {
            OscarReview querySingleResult = em.find(OscarReview.class, id);
            if (querySingleResult != null) {
                optionalOscarReview= Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalOscarReview;
    }

    public List<OscarReview> findAll() {
        return em.createQuery(
                "SELECT ti FROM OscarReview ti"
                , OscarReview.class)
                .getResultList();
    }

}