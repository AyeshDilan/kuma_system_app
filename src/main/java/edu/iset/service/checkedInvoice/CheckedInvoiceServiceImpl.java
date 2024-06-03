package edu.iset.service.checkedInvoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.CheckedInvoiceEntity;
import edu.iset.dto.CheckedInvoice;
import edu.iset.repository.CheckedInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CheckedInvoiceServiceImpl implements CheckedInvoiceService{
    @Autowired
    private CheckedInvoiceRepository checkedInvoiceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //Save checked invoice in database
    @Override
    public void saveCheckedInvoice(CheckedInvoice checkedInvoice) {
        CheckedInvoiceEntity checkedInvoiceEntity = objectMapper.convertValue(checkedInvoice, CheckedInvoiceEntity.class);
        checkedInvoiceRepository.save(checkedInvoiceEntity);
    }

    //Get all checked invoice data
    @Override
    public List<CheckedInvoice> retriveAllCheckedInvoices() {
        List<CheckedInvoice> list = new ArrayList<>();
        Iterable<CheckedInvoiceEntity> checkedInvoiceEntityIterable = checkedInvoiceRepository.findAll();
        Iterator<CheckedInvoiceEntity> iterator = checkedInvoiceEntityIterable.iterator();
        if(iterator.hasNext()){
            do{
                CheckedInvoiceEntity entity = iterator.next();
                CheckedInvoice checkedInvoice = objectMapper.convertValue(entity, CheckedInvoice.class);
                list.add(checkedInvoice);
            }while (iterator.hasNext());
        }
        return list;
    }
}
