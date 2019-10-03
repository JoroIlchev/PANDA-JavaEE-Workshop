package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.PackageViewModel;
import panda.repository.PackageRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private final ModelMapper modelMapper;
    private final PackageRepository packageRepository;

    @Inject
    public PackageServiceImpl(ModelMapper modelMapper, PackageRepository packageRepository) {
        this.modelMapper = modelMapper;
        this.packageRepository = packageRepository;

    }

    @Override
    public void packageCreate(PackageServiceModel packageServiceModel) {
        Package aPackage = this.modelMapper.map(packageServiceModel, Package.class);
        aPackage.setStatus(Status.Pending);
        this.packageRepository.save(aPackage);

    }

    @Override
    public List<PackageServiceModel> findAllByStatus(Status status) {
        return this.packageRepository.findAllPackagesByStatus(status).stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageServiceModel> findAllByStatus(Status statusOne, Status statusTwo) {
        return this.packageRepository.findAllPackagesByStatus(statusOne, statusTwo).stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(String id) {
        Package aPackage = this.packageRepository.findById(id);
        this.changeStatus(aPackage);
        this.changeDate(aPackage);
        this.packageRepository.update(aPackage);

    }

//    @Override
//    public PackageViewModel findById(String id) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        Package aPackage = this.packageRepository.findById(id);
//        LocalDateTime curr = aPackage.getEstimatedDeliveryTime();
//        PackageViewModel packageViewModel = this.modelMapper.map(aPackage, PackageViewModel.class);
//        packageViewModel.setRecipient(aPackage.getRecipient().getUsername());
//        if (curr != null) {
//            packageViewModel.setEstimatedDeliveryTime(curr.format(formatter));
//        }
//        return packageViewModel;
//    }

    @Override
    public PackageServiceModel findByIdServiceModel(String id) {
        return this.modelMapper.map(this.packageRepository.findById(id), PackageServiceModel.class);
    }

    @Override
    public PackageServiceModel findByName(String name) {
        return this.modelMapper.map(this.packageRepository.findByName(name), PackageServiceModel.class);
    }

    private void changeDate(Package aPackage) {
        long days = (System.currentTimeMillis() % 21) + 20;
        aPackage.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(days));
    }

    private void changeStatus(Package aPackage) {
        int index = aPackage.getStatus().ordinal();
        int nexIndex = index + 1;
        Status[] statuses = Status.values();
        aPackage.setStatus(statuses[nexIndex]);
    }

}
