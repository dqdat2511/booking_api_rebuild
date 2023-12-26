package meu.booking_rebuild.service.concretions;
import meu.booking_rebuild.model.AddressModel;
import meu.booking_rebuild.model.PassangerModel;
import meu.booking_rebuild.repository.PassangersRepository;
import meu.booking_rebuild.request.PassangerTripRequest;
import meu.booking_rebuild.service.abstractions.InterfacePassangerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PassangerService implements InterfacePassangerService {
    private final ModelMapper modelMapper;
    @Autowired
    private PassangersRepository passangersRepository;
    public PassangerService() {
        this.modelMapper = new ModelMapper();
    }
    @Override
    public PassangerTripRequest findPassangerById(UUID id) throws RuntimeException {
        return passangersRepository.findPassengerById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public boolean check(String phone) {
        boolean passengerOptional = passangersRepository.findPassangerModelByPhone(phone);
        return passengerOptional;
    }

    @Override
    public PassangerTripRequest updatePassangerById(PassangerTripRequest request, UUID id) throws RuntimeException {
        PassangerModel model = passangersRepository.findPassangerModelById(id);
        model.setName(request.getName());
        model.setPhone(request.getPhone());
        ArrayList<AddressModel> modelArrayList = new ArrayList<>();
        PassangerModel newModel = passangersRepository.save(model);
        PassangerTripRequest passangerDto = modelMapper.map(newModel, PassangerTripRequest.class);
        return passangerDto;
    }
}
