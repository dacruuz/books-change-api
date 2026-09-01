package br.com.bookschange.api.application.address.usecases;

import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;
import br.com.bookschange.api.application.address.mappers.AddressMapper;
import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.domain.models.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAddressUseCaseTest {

    @Mock private AddressMapper mapper;
    @Mock private FindAddressPortOut findAddressPortOut;

    @InjectMocks
    FindAddressUseCase useCase;

    @Test
    @DisplayName("Deve buscar um endereço pelo uuid com sucesso")
    void shouldFindAddressByUuidSuccessfully() {
        UUID uuid = UUID.randomUUID();
        Address address = new Address();
        address.setUuid(uuid);
        AddressResponse expectedResponse = mock(AddressResponse.class);

        when(findAddressPortOut.findByUuidOrThrow(uuid)).thenReturn(address);
        when(mapper.entityToAddressResponse(address)).thenReturn(expectedResponse);

        AddressResponse result = useCase.findByUuid(uuid);

        assertEquals(expectedResponse, result);
        assertEquals(uuid, address.getUuid());
        verify(findAddressPortOut).findByUuidOrThrow(any());
        verify(mapper).entityToAddressResponse(any());
    }
}