package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {

    List<Package> findAllPackagesByStatus(Status status);
    List<Package> findAllPackagesByStatus(Status statusOne, Status statusTwo);

    void update(Package aPackage);
    Package findByName(String name);

}
