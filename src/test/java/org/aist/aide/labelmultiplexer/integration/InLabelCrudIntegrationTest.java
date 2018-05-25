package org.aist.aide.labelmultiplexer.integration;

import org.aist.aide.labelmultiplexer.domain.models.InLabel;
import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.service.controllers.InLabelController;
import org.aist.aide.labelmultiplexer.service.repositories.InLabelRepository;
import org.aist.aide.labelmultiplexer.service.repositories.OutLabelRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InLabelCrudIntegrationTest {
    @Autowired
    private InLabelController inLabelController;

    @Autowired
    private OutLabelRepository outLabelRepository;

    @Autowired
    private InLabelRepository repo;

    @After
    public void tearDown() {
        repo.deleteAll();
        outLabelRepository.deleteAll();
    }

    @Test
    public void givenNoLabels_whenCreateInLabel_LabelIsCreated() {
        // act
        var id = inLabelController.create(new InLabel("Test")).getBody();

        // assert
        var label = repo.findById(id).get();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenOutLabelExists_whenCreateInLabel_LabelIsCreatedWithOutLabel() {
        // arrange
        var outLabel = new OutLabel("OutLabelThing");
        outLabelRepository.save(outLabel);

        // act
        var id = inLabelController.create(new InLabel("Test", outLabel)).getBody();

        // assert
        var label = repo.findById(id).get();
        Assert.assertEquals(outLabel.getId(), label.getLabel().getId());
    }

    @Test
    public void givenNoLabels_whenCreateInLabel_ReturnsValidId() {
        // act
        var id = inLabelController.create(new InLabel("Test")).getBody();

        // assert
        var label = inLabelController.get(id).getBody();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenLabelExists_whenUpdateInLabel_LabelIsUpdated() {
        // arrange
        var createdLabel = new InLabel("Test");
        repo.save(createdLabel);

        // act
        inLabelController.update(new InLabel(createdLabel.getId(), "Hello, World!", null));

        // assert
        var label = repo.findById(createdLabel.getId()).get();
        Assert.assertEquals("Hello, World!", label.getName());
    }

    @Test
    public void givenNoLabelExists_whenUpdateInLabel_NotFoundReturned() {
        // act
        var result = inLabelController.update(new InLabel(999, "Hello, World!", null));

        // assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenLabelExists_whenDeletingInLabel_LabelIsDeleted() {
        // arrange
        var createdLabel = new InLabel("Test");
        repo.save(createdLabel);

        // act
        inLabelController.delete(createdLabel.getId());

        // assert
        var labels = repo.findAll().iterator();
        Assert.assertEquals(false, labels.hasNext());
    }

    @Test
    public void givenNoLabelExists_whenDeletingInLabel_NotFoundRetrieved() {
        // act
        var result = inLabelController.delete(1);

        // assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenLabelsExist_whenGetById_LabelIsRetrieved() {
        // act
        var createdLabel = new InLabel("Test");
        repo.save(new InLabel("Hello"));
        repo.save(createdLabel);
        repo.save(new InLabel("Fault"));

        // assert
        var label = inLabelController.get(createdLabel.getId()).getBody();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenNoLabelsExist_whenGetById_NotFoundReturned() {
        // assert
        var label = inLabelController.get(1);
        Assert.assertEquals(HttpStatus.NOT_FOUND, label.getStatusCode());
    }

    @Test
    public void givenLabelsExist_whenGetByName_LabelIsRetrieved() {
        // act
        var createdLabel = new InLabel("Test");
        repo.save(new InLabel("Hello"));
        repo.save(createdLabel);
        repo.save(new InLabel("Fault"));

        // assert
        var label = inLabelController.getByName("Test").getBody();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenNoLabelsExist_whenGetByName_NotFoundReturned() {
        // assert
        var label = inLabelController.getByName("Hello, World!");
        Assert.assertEquals(HttpStatus.NOT_FOUND, label.getStatusCode());
    }

    @Test
    public void givenLabelsExist_whenGetAll_LabelsAreRetrieved() {
        // act
        repo.save(new InLabel("Test"));
        repo.save(new InLabel("Test2"));
        repo.save(new InLabel("Test3"));

        // assert
        var label = inLabelController.getAll();
        Assert.assertEquals(3, label.getBody().size());
    }

    @Test
    public void givenNoLabelsExist_whenGetAll_EmptyListRetrieved() {
        // assert
        var label = inLabelController.getAll();
        Assert.assertEquals(0, label.getBody().size());
    }
}
