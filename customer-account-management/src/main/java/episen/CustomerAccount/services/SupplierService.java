package episen.CustomerAccount.services;


import back.models.CustomerAccountModel.Supplier;
import episen.CustomerAccount.respositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() { return supplierRepository.findAll();
    }
}
