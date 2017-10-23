package com.quijotelui.service

import com.quijotelui.model.Contribuyente
import com.quijotelui.repository.ContribuyenteDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContribuyenteServiceImpl : ContribuyenteService {

    @Autowired
    lateinit var controbuyenteDao : ContribuyenteDao

    @Override
    override fun getContribuyentes(): MutableList<Contribuyente> {
        return controbuyenteDao.getContribuyentes()
    }
}