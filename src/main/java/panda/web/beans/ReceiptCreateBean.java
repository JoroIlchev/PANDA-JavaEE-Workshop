package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.bindingModels.ReceiptBindingModel;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.service.PackageService;
import panda.service.ReceiptService;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class ReceiptCreateBean {

    private ReceiptService receiptService;
    private ReceiptBindingModel receiptBindingModel;
    private ModelMapper modelMapper;
    private UserService userService;
    private PackageService packageService;

    public ReceiptCreateBean() {
    }

    @Inject
    public ReceiptCreateBean(ReceiptService receiptService, ModelMapper modelMapper, UserService userService, PackageService packageService) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.packageService = packageService;
        this.init();
    }

    private void init() {
        this.receiptBindingModel = new ReceiptBindingModel();
    }

    public ReceiptBindingModel getReceiptBindingModel() {
        return this.receiptBindingModel;
    }

    public void setReceiptBindingModel(ReceiptBindingModel receiptBindingModel) {
        this.receiptBindingModel = receiptBindingModel;
    }

    public void create(Double fee, String recipient, String aPackage) {
        BigDecimal finalFee = BigDecimal.valueOf(fee * 2.67);
        this.receiptBindingModel.setFee(finalFee);
        this.receiptBindingModel.setIssuedOn(LocalDateTime.now());
        this.receiptBindingModel.setRecipient(recipient);
        this.receiptBindingModel.setaPackage(aPackage);

        ReceiptServiceModel receiptServiceModel = this.modelMapper.map(this.receiptBindingModel, ReceiptServiceModel.class);
        receiptServiceModel.setRecipient(this.userService.findByUsername(this.receiptBindingModel.getRecipient()));
        receiptServiceModel.setaPackage(this.packageService.findByIdServiceModel(this.receiptBindingModel.getaPackage()));
        this.receiptService.receiptCreate(receiptServiceModel);


    }
}
