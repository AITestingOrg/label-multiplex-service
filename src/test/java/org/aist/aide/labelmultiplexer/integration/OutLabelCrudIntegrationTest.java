package org.aist.aide.labelmultiplexer.integration;

import org.aist.aide.labelmultiplexer.domain.models.OutLabel;
import org.aist.aide.labelmultiplexer.service.controllers.OutLabelController;
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
public class OutLabelCrudIntegrationTest {
    @Autowired
    private OutLabelController outLabelController;

    @Autowired
    private OutLabelRepository repo;

    @After
    public void tearDown() {
        repo.deleteAll();
    }

    @Test
    public void givenNoLabels_whenCreateOutLabel_LabelIsCreated() {
        // act
        var id = outLabelController.create(new OutLabel("Test")).getBody();

        // assert
        var label = repo.findById(id).get();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenNoLabels_whenCreateOutLabel_ReturnsValidId() {
        // act
        var id = outLabelController.create(new OutLabel("Test")).getBody();

        // assert
        var label = outLabelController.get(id).getBody();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenLabelExists_whenUpdateOutLabel_LabelIsUpdated() {
        // arrange
        var createdLabel = new OutLabel("Test");
        repo.save(createdLabel);

        // act
        outLabelController.update(new OutLabel(createdLabel.getId(), "Hello, World!", null));

        // assert
        var label = repo.findById(createdLabel.getId()).get();
        Assert.assertEquals("Hello, World!", label.getName());
    }

    @Test
    public void givenNoLabelExists_whenUpdateOutLabel_NotFoundReturned() {
        // act
        var result = outLabelController.update(new OutLabel(1, "Hello, World!", null));

        // assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenLabelExists_whenDeletingOutLabel_LabelIsDeleted() {
        // arrange
        var createdLabel = new OutLabel("Test");
        repo.save(createdLabel);

        // act
        outLabelController.delete(createdLabel.getId());

        // assert
        var labels = repo.findAll().iterator();
        Assert.assertEquals(false, labels.hasNext());
    }

    @Test
    public void givenNoLabelExists_whenDeletingOutLabel_NotFoundRetrieved() {
        // act
        var result = outLabelController.delete(1);

        // assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenLabelsExist_whenGetById_LabelIsRetrieved() {
        // act
        var createdLabel = new OutLabel("Test");
        repo.save(new OutLabel("Hello"));
        repo.save(createdLabel);
        repo.save(new OutLabel("Fault"));

        // assert
        var label = outLabelController.get(createdLabel.getId()).getBody();
        Assert.assertEquals("Test", label.getName());
    }

    @Test
    public void givenNoLabelsExist_whenGetById_NotFoundReturned() {
        // assert
        var label = outLabelController.get(1);
        Assert.assertEquals(HttpStatus.NOT_FOUND, label.getStatusCode());
    }

    @Test
    public void givenLabelsExist_whenGetAll_LabelsAreRetrieved() {
        // act
        repo.save(new OutLabel("Test"));
        repo.save(new OutLabel("Test2"));
        repo.save(new OutLabel("Test3"));

        // assert
        var label = outLabelController.getAll();
        Assert.assertEquals(3, label.getBody().size());
    }

    @Test
    public void givenNoLabelsExist_whenGetAll_EmptyListRetrieved() {
        // assert
        var label = outLabelController.getAll();
        Assert.assertEquals(0, label.getBody().size());
    }
}