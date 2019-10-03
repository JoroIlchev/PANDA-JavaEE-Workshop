package panda.service;

import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.PackageViewModel;

import java.util.List;

public interface PackageService {

    void packageCreate(PackageServiceModel packageServiceModel);
    List<PackageServiceModel> findAllByStatus(Status status);
    List<PackageServiceModel> findAllByStatus(Status statusOne, Status statusTwo);
    void updateStatus(String id);
   // PackageViewModel findById(String id);
    PackageServiceModel findByIdServiceModel(String id);
    PackageServiceModel findByName(String name);
}
