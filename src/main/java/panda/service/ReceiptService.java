package panda.service;

import panda.domain.models.serviceModels.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {
    void receiptCreate(ReceiptServiceModel receiptServiceModel);
    ReceiptServiceModel findById(String id);
}
