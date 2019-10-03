package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.domain.models.viewModels.PackageViewModel;
import panda.service.PackageService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<PackageViewModel> pendingPackages;
    private List<PackageViewModel> shippedPackages;
    private List<PackageViewModel> deliveredPackages;
    private List<PackageViewModel> acquiredPackages;
    private UserService userService;
    private PackageService packageService;
    private ModelMapper modelMapper;


    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper, PackageService packageService) {
        this.userService = userService;
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init() {
        String adminRole = (String) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true))
                .getAttribute("role");
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true))
                .getAttribute("username");
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        this.pendingPackages = getByStatus(userServiceModel, "Pending");
        this.shippedPackages = getByStatus(userServiceModel, "Shipped");
        this.deliveredPackages = getByStatus(userServiceModel, "Delivered");
        if (adminRole.equals("Admin")){
            this.acquiredPackages = getByStatus(userServiceModel, Status.Acquired.toString());
        }
//        else {
//            this.pendingPackages = getCollect(Status.Pending);
//            this.shippedPackages = getCollect(Status.Shipped);
//            this.deliveredPackages = getCollect(Status.Delivered);
//            this.acquiredPackages = getCollect(Status.Acquired);
//        }

    }

    private List<PackageViewModel> getCollect(Status status) {
        return this.packageService.findAllByStatus(status).stream()
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    private List<PackageViewModel> getByStatus(UserServiceModel userServiceModel, String status) {
        return userServiceModel.getPackages().stream()
                .filter(p -> p.getStatus().name().equals(status))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPendingPackages() {
        return this.pendingPackages;
    }

    public void setPendingPackages(List<PackageViewModel> pendingPackages) {
        this.pendingPackages = pendingPackages;
    }

    public List<PackageViewModel> getShippedPackages() {
        return this.shippedPackages;
    }

    public void setShippedPackages(List<PackageViewModel> shippedPackages) {
        this.shippedPackages = shippedPackages;
    }

    public List<PackageViewModel> getDeliveredPackages() {
        return this.deliveredPackages;
    }

    public void setDeliveredPackages(List<PackageViewModel> deliveredPackages) {
        this.deliveredPackages = deliveredPackages;
    }

    public List<PackageViewModel> getAcquiredPackages() {
        return this.acquiredPackages;
    }

    public void setAcquiredPackages(List<PackageViewModel> acquiredPackages) {
        this.acquiredPackages = acquiredPackages;
    }
}
