package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.domain.models.viewModels.ReceiptViewModel;
import panda.service.PackageService;
import panda.service.ReceiptService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptsByUserBean {

    private List<ReceiptViewModel> receiptViewModels;
    private UserService userService;
    private ModelMapper modelMapper;
    private ReceiptService receiptService;


    public ReceiptsByUserBean() {
    }

    @Inject
    public ReceiptsByUserBean(UserService userService, ModelMapper modelMapper, ReceiptService receiptService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.receiptService = receiptService;

        this.init();
    }

    private void init() {
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true))
                .getAttribute("username");
        UserServiceModel userServiceModel = this.userService.findByUsername(username);

        this.receiptViewModels = userServiceModel.getReceipts().stream()
                .map(r -> {
                    ReceiptServiceModel receiptServiceModel = this.receiptService.findById(r.getId());
                    LocalDateTime curr = r.getIssuedOn();

                    return this.modelMapper.map(receiptServiceModel, ReceiptViewModel.class);
                }).collect(Collectors.toList());

    }

    public List<ReceiptViewModel> getReceiptViewModels() {
        return this.receiptViewModels;
    }

    public void setReceiptViewModels(List<ReceiptViewModel> receiptViewModels) {
        this.receiptViewModels = receiptViewModels;
    }

    public String getData(ReceiptViewModel receiptViewModel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime curr = receiptViewModel.getIssuedOn();

        return curr.format(formatter);
    }
}
