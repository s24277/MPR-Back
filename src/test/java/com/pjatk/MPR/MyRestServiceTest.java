package com.pjatk.MPR;

import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.repository.CowRepository;
import com.pjatk.MPR.service.MyCowService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MyRestServiceTest {
    @Mock
    private CowRepository repository;
    private AutoCloseable openMocks;
    private MyCowService cowservice;
    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        cowservice = new MyCowService(repository);
    }
    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    public void findFinds(){
        String name = "Krieg";
        Cow cow = new Cow(1L,name,16);
        when(repository.findByName(name)).thenReturn(cow);

        Cow result = cowservice.getCowByName(name);
        assertEquals(cow,result);
    }
    @Test
    public void saveSaves() throws AlreadyExists {
        Long id = 1L;
        String name = "Krieg's mom";
        Integer age = 20;
        Cow cow = new Cow(id,name,age);
        ArgumentCaptor<Cow> captor = ArgumentCaptor.forClass(Cow.class);
        when(repository.save(captor.capture())).thenReturn(cow);

        cowservice.addCow(cow);
        Mockito.verify(repository, times(1))
        .save(Mockito.any());
        Cow cowFromSaveCall = captor.getValue();
        assertEquals(cow,cowFromSaveCall);
    }

    @Test
    public void addCowThrowsAlreadyExists() throws AlreadyExists {
        Cow cow = new Cow(1L,"Greg",3);
        when(repository.findByName("Greg")).thenReturn(new Cow());

        assertThrows(AlreadyExists.class,() -> cowservice.addCow(cow));
    }
}
