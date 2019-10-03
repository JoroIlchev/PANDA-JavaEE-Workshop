package panda.repository;

import panda.domain.entities.Receipt;

public interface ReceiptRepository extends GenericRepository<Receipt, String> {
    @Override
    Receipt save(Receipt entity);
}
