package dmit2015.egonzalezoblita1.assignment02.repository;

import dmit2015.egonzalezoblita1.assignment02.entity.OscarReview;
import dmit2015.egonzalezoblita1.assignment02.repository.OscarReviewRepository;
import dmit2015.egonzalezoblita1.assignment02.config.ApplicationConfig;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.swing.text.html.Option;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ArquillianExtension.class)

class OscarReviewRepositoryIT {

    @Inject
    private OscarReviewRepository _oscarReviewRepository;

    static OscarReview currentOscarReview;

    @Order(2)
    @Test
    void shouldCreate() {
        currentOscarReview = new OscarReview();
        currentOscarReview.setCategory("Film");
        currentOscarReview.setNominee("Spider-man");
        currentOscarReview.setReview("This year's best movie!");
        currentOscarReview.setUsername("Spider-man-Friend");
        _oscarReviewRepository.add(currentOscarReview);

        Optional<OscarReview> optionalOscarReview = _oscarReviewRepository.findById(currentOscarReview.getId());
        assertTrue(optionalOscarReview.isPresent());
        OscarReview existingOscarReview = optionalOscarReview.get();
        assertNotNull(existingOscarReview);
        assertEquals(currentOscarReview.getCategory(), existingOscarReview.getCategory());
        assertEquals(currentOscarReview.getNominee(), existingOscarReview.getNominee());
        assertEquals(currentOscarReview.getReview(), existingOscarReview.getReview());
        assertEquals(currentOscarReview.getUsername(), existingOscarReview.getUsername());
    }

    @Order(3)
    @Test
    void shouldFindOne() {
        final long oscarReviewId = currentOscarReview.getId();
        Optional<OscarReview> optionalOscarReview = _oscarReviewRepository.findById(oscarReviewId);
        assertTrue(optionalOscarReview.isPresent());
        OscarReview existingOscarReview = optionalOscarReview.get();
        assertNotNull(existingOscarReview);
        assertEquals(currentOscarReview.getCategory(), existingOscarReview.getCategory());
        assertEquals(currentOscarReview.getNominee(), existingOscarReview.getNominee());
        assertEquals(currentOscarReview.getReview(), existingOscarReview.getReview());
        assertEquals(currentOscarReview.getUsername(), existingOscarReview.getUsername());
//        long createdDateTimeDifference = currentOscarReview.getCreatedDateTime().until(existingOscarReview.getCreatedDateTime(), ChronoUnit.MINUTES);
//        assertEquals(0, createdDateTimeDifference);
//        long lastModifiedDateTimeDifference = currentOscarReview.getLastModifiedDateTime().until(existingOscarReview.getLastModifiedDateTime(), ChronoUnit.MINUTES);
//        assertEquals(0, lastModifiedDateTimeDifference);
    }

    @Order(1)
    @Test
    void shouldFindAll() {
        List<OscarReview> queryResultList = _oscarReviewRepository.findAll();
        assertEquals(4, queryResultList.size());

        OscarReview firstOscarReview = queryResultList.get(0);
        assertEquals("Film", firstOscarReview.getCategory());
        assertEquals("House of Gucci", firstOscarReview.getNominee());
        assertEquals("Thrilling to watch", firstOscarReview.getReview());
        assertEquals("GucciLover", firstOscarReview.getUsername());

        OscarReview lastOscarReview = queryResultList.get(queryResultList.size() -1);
        assertEquals("Film", firstOscarReview.getCategory());
        assertEquals("Eternals", firstOscarReview.getNominee());
        assertEquals("Not as good as Endgame", firstOscarReview.getReview());
        assertEquals("HarshReviewer", firstOscarReview.getUsername());

        queryResultList.forEach(System.out::println);
    }

    @Order(4)
    @Test
    void shouldUpdate() {
        currentOscarReview.setCategory("Film");
        currentOscarReview.setNominee("The Matrix Resurrections");
        currentOscarReview.setReview("Not very good");
        currentOscarReview.setUsername("TruthTeller");
        _oscarReviewRepository.update(currentOscarReview);

        Optional<OscarReview> optionalOscarReview = _oscarReviewRepository.findById(currentOscarReview.getId());
        assertTrue(optionalOscarReview.isPresent());
        OscarReview updatedOscarReview = optionalOscarReview.get();
        assertNotNull(updatedOscarReview);
        assertEquals(currentOscarReview.getCategory(), updatedOscarReview.getCategory());
        assertEquals(currentOscarReview.getNominee(), updatedOscarReview.getNominee());
        assertEquals(currentOscarReview.getReview(), updatedOscarReview.getReview());
        assertEquals(currentOscarReview.getUsername(), updatedOscarReview.getUsername());

    }

    @Order(5)
    @Test
    void shouldDelete() {
        final long oscarReviewId = currentOscarReview.getId();
        Optional<OscarReview> optionalOscarReview = _oscarReviewRepository.findById(oscarReviewId);
        assertTrue(optionalOscarReview.isPresent());
        OscarReview existingOscarReview = optionalOscarReview.get();
        assertNotNull(existingOscarReview);
        _oscarReviewRepository.remove(existingOscarReview.getId());
        optionalOscarReview = _oscarReviewRepository.findById(oscarReviewId);
        assertTrue(optionalOscarReview.isEmpty());
    }
}