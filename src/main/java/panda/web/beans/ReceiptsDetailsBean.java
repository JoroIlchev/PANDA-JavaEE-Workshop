package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.viewModels.ReceiptViewModel;
import panda.service.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class ReceiptsDetailsBean {

    private ReceiptViewModel viewModel;
    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public ReceiptsDetailsBean() {
    }

    @Inject
    public ReceiptsDetailsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init() {
        String receiptId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get("receiptId");
        this.viewModel = this.modelMapper.map(this.receiptService.findById(receiptId), ReceiptViewModel.class);

    }

    public ReceiptViewModel getViewModel() {
        return this.viewModel;
    }

    public void setViewModel(ReceiptViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime curr = this.viewModel.getIssuedOn();

        return curr.format(formatter);
    }
}
