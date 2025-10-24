package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.TestDataFactory.getSetEntity;
import static de.andwari.memory.backend.TestDataFactory.getSetsFromClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.SetMapper;
import de.andwari.memory.backend.mapper.SetMapperImpl;
import de.andwari.memory.backend.web.client.ScryfallClient;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SetUpdateServiceTest {

    @Mock
    private ScryfallClient scryfallClient;
    @Mock
    private SetRepository setRepository;
    @Mock
    private SetMapper setMapper;

    private final SetMapperImpl setMapperImpl = new SetMapperImpl();

    @InjectMocks
    private SetUpdateService setUpdateService;

    @Captor
    private ArgumentCaptor<SetEntity> setCaptor;

    @Test
    void updateSets() {
        when(setMapper.toEntity(any())).thenAnswer(invocation ->
            setMapperImpl.toEntity(invocation.getArgument(0)));
        when(scryfallClient.getAllSets()).thenReturn(getSetsFromClient());
        when(setRepository.findByScryfallId("0"))
                .thenReturn(Optional.of(getSetEntity(0, "set-name")));
        when(setRepository.findByScryfallId("1"))
                .thenReturn(Optional.of(getSetEntity(1, "some-other-set-name")));

        setUpdateService.updateSets();

        verify(setRepository, times(2))
                .save(setCaptor.capture());

        setCaptor.getAllValues().stream()
                .filter(set -> set.getScryfallId().equals("1"))
                .findFirst()
                .ifPresentOrElse(
                        set -> assertEquals("set-name", set.getName()),
                        Assertions::fail);
        setCaptor.getAllValues().stream()
                .filter(set -> set.getScryfallId().equals("2"))
                .findFirst()
                .ifPresentOrElse(
                        set -> assertEquals("set-name", set.getName()),
                        Assertions::fail);
    }
}