package panda.domain.models.viewModels;

import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.serviceModels.UserServiceModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptViewModel {

    private String id;
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private UserViewModel recipient;
    private PackageViewModel aPackage;

    public ReceiptViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDateTime getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public UserViewModel getRecipient() {
        return this.recipient;
    }

    public void setRecipient(UserViewModel recipient) {
        this.recipient = recipient;
    }

    public PackageViewModel getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(PackageViewModel aPackage) {
        this.aPackage = aPackage;
    }
}
