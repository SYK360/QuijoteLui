package com.quijotelui.service

import com.quijotelui.model.Informacion
import com.quijotelui.repository.IInformacionDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InformacionServiceImpl : IInformacionService {

    @Autowired
    lateinit var informacionDao : IInformacionDao

    @Override
    override fun findByDocumento(documento: String): MutableList<Informacion> {
        return informacionDao.findByDocumento(documento)
    }

    @Override
    override fun correoByDocumento(documento: String): MutableList<Informacion> {
        return informacionDao.correoByDocumento(documento)
    }

}