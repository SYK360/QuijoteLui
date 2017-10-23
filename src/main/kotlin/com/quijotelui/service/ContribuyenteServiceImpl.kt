package com.quijotelui.service

import com.quijotelui.model.Contribuyente
import com.quijotelui.repository.IContribuyenteDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContribuyenteServiceImpl : IContribuyenteService {

    @Autowired
    lateinit var controbuyenteDao : IContribuyenteDao

    @Override
    override fun findAll(): MutableList<Contribuyente> {
        return controbuyenteDao.findAll()
    }

    @Override
    override fun findByRUC(ruc: String): Contribuyente {
        return controbuyenteDao.findByRUC(ruc)
    }
}