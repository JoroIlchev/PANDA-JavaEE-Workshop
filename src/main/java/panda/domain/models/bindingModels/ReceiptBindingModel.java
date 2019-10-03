package panda.domain.models.bindingModels;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptBindingModel {

    private String id;
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private String recipient;
    private String aPackage;

    public ReceiptBindingModel() {
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

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(String aPackage) {
        this.aPackage = aPackage;
    }
}
