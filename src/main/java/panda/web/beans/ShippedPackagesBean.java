package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.viewModels.PackageViewModel;
import panda.service.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ShippedPackagesBean {
    private List<PackageViewModel> packageViewModels;
    private PackageService packageService;
    private ModelMapper modelMapper;

    public ShippedPackagesBean() {
    }

    @Inject
    public ShippedPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initPackages();
    }

    private void initPackages() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.packageViewModels = this.packageService.findAllByStatus(Status.Shipped).stream()
                .map(p -> {
                    PackageViewModel packageViewModel = this.modelMapper.map(p, PackageViewModel.class);
                    packageViewModel.setRecipient(p.getRecipient().getUsername());
                    LocalDateTime curr = p.getEstimatedDeliveryTime();
                    packageViewModel.setEstimatedDeliveryTime(curr.format(formatter));
                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPackageViewModels() {
        System.out.println();
        return this.packageViewModels;
    }

    public void setPackageViewModels(List<PackageViewModel> packageViewModels) {
        this.packageViewModels = packageViewModels;
    }

    public void changeStatus(String id) throws IOException {

        this.packageService.updateStatus(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/packages/shipped.xhtml");
    }
}
