package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.PackageViewModel;
import panda.service.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class DetailsBean {

    private PackageViewModel packageViewModel;
    private PackageService packageService;
    private ModelMapper modelMapper;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init() {
        String packageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get("apackageId");

        PackageServiceModel packageServiceModel = this.packageService.findByIdServiceModel(packageId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime curr = packageServiceModel.getEstimatedDeliveryTime();

        this.packageViewModel = this.modelMapper.map(packageServiceModel, PackageViewModel.class);
        packageViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());
        if (curr != null) {
            packageViewModel.setEstimatedDeliveryTime(curr.format(formatter));
        }

       // this.packageViewModel = this.packageService.findById(packageId);

        if (packageViewModel.getStatus().equals(Status.Pending)) {
            packageViewModel.setEstimatedDeliveryTime("N/A");
        } else if (packageViewModel.getStatus().equals(Status.Delivered) || packageViewModel.getStatus().equals(Status.Acquired)) {
            packageViewModel.setEstimatedDeliveryTime("Delivered");
        }
    }

    public PackageViewModel getPackageViewModel() {
        return this.packageViewModel;
    }

    public void setPackageViewModel(PackageViewModel packageViewModel) {
        this.packageViewModel = packageViewModel;
    }
}
