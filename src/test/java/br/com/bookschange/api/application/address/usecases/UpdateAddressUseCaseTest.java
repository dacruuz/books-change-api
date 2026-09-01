package br.com.bookschange.api.application.address.usecases;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.UpdateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;
import br.com.bookschange.api.application.address.mappers.AddressMapper;
import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.application.address.ports.out.SaveAddressPortOut;
import br.com.bookschange.api.application.address.services.AddressNormalizer;
import br.com.bookschange.api.application.address.services.AddressValidator;
import br.com.bookschange.api.domain.models.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAddressUseCaseTest {

    @Mock private AddressMapper mapper;
    @Mock private AddressNormalizer normalizer;
    @Mock private AddressValidator validator;
    @Mock private FindAddressPortOut findAddressPortOut;
    @Mock private SaveAddressPortOut saveAddressPortOut;

    @InjectMocks
    UpdateAddressUseCase useCase;

    UUID uuid;
    Address address;
    UpdateAddressRequest request;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        address = new Address();
        request = new UpdateAddressRequest(
                "70680-642",
                "street",
                "000",
                "state",
                "country",
                "city",
                "complement",
                "neighborhood"
        );

        address.setZipCode(request.zipCode());
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setState(request.state());
        address.setCountry(request.country());
        address.setComplement(request.complement());
        address.setNeighborhood(request.neighborhood());
    }

    @Test
    @DisplayName("Deve atualizar um endereço com sucesso")
    void shouldUpdateAddressSuccessfully() {
        AddressResponse expectedResponse = mock(AddressResponse.class);

        when(findAddressPortOut.findByUuidOrThrow(uuid)).thenReturn(address);
        doNothing().when(validator).validateZipCode(request.zipCode());
        doNothing().when(mapper).updateAddressRequestToEntity(request, address);
        doNothing().when(normalizer).normalizeData(address);
        when(saveAddressPortOut.save(address)).thenReturn(address);
        when(mapper.entityToAddressResponse(address)).thenReturn(expectedResponse);

        AddressResponse result = useCase.update(uuid, request);

        ArgumentCaptor<Address> addressCaptor = ArgumentCaptor.forClass(Address.class);

        assertEquals(expectedResponse, result);
        verify(findAddressPortOut).findByUuidOrThrow(uuid);
        verify(validator).validateZipCode(request.zipCode());
        verify(mapper).updateAddressRequestToEntity(request, address);
        verify(normalizer).normalizeData(address);
        verify(saveAddressPortOut).save(addressCaptor.capture());
        verify(mapper).entityToAddressResponse(address);
    }
}