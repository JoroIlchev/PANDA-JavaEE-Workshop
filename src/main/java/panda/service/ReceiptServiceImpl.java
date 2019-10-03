package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Receipt;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.repository.ReceiptRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

public class ReceiptServiceImpl implements ReceiptService {

    private final ModelMapper modelMapper;
    private final ReceiptRepository receiptRepository;

    @Inject
    public ReceiptServiceImpl(ModelMapper modelMapper, ReceiptRepository receiptRepository) {
        this.modelMapper = modelMapper;
        this.receiptRepository = receiptRepository;

    }
    @Override
    public void receiptCreate(ReceiptServiceModel receiptServiceModel) {

        Receipt receipt = this.modelMapper.map(receiptServiceModel, Receipt.class);

        this.receiptRepository.save(receipt);
    }

    @Override
    public ReceiptServiceModel findById(String id) {
        return this.modelMapper.map(this.receiptRepository.findById(id), ReceiptServiceModel.class);
    }
}
